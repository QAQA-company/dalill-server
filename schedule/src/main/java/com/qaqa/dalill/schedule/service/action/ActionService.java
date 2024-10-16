package com.qaqa.dalill.schedule.service.action;

import com.qaqa.dalill.schedule.service.schedule.ScheduleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ScheduleDetailService scheduleDetailService;

    /**
     * 이벤트 시작
     * Redis에 이벤트 ID 저장 및 마지막 활동 시간 기록
     * @return Mono<String> 이벤트 ID
     */
    public Mono<String> startEvent() {
        String eventId = UUID.randomUUID().toString();
        long currentTime = Instant.now().toEpochMilli();

        redisTemplate.opsForValue().set(getEventIdKey(), eventId);
        redisTemplate.opsForValue().set(getLastActivityKey(eventId), String.valueOf(currentTime));

        return Mono.just(eventId);
    }

    /**
     * 이벤트 업데이트
     * 이벤트 도중 변경사항 발생 시 data Redis에 추가 및 마지막 활동 시간 갱신
     * @return Mono<Void>
     */
    public Mono<Void> updateEvent(Object data) {
        String eventId = getCurrentEventIdFromRedis();
        long currentTime = Instant.now().toEpochMilli();

        redisTemplate.opsForList().rightPush(getUpdatesKey(eventId), data);
        redisTemplate.opsForValue().set(getLastActivityKey(eventId), String.valueOf(currentTime));

        return Mono.empty();
    }

    /**
     * 이벤트 실시간 업데이트 확인
     * @return Flux<Object> 실시간 업데이트 스트림
     */
    public Flux<Object> getUpdates() {
        String eventId = getCurrentEventIdFromRedis();

        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> Flux.fromIterable(Objects.requireNonNull(redisTemplate.opsForList()
                        .range(getUpdatesKey(eventId), 0, -1)))
                );
    }

    /**
     * 이벤트 종료
     * Redis 데이터를 MongoDB로 이전하고, Redis 데이터 삭제
     */
    public void stopEvent() {
        String eventId = getCurrentEventIdFromRedis();
        String lastActivityTimeStr = (String) redisTemplate.opsForValue().get(getLastActivityKey(eventId));
        long lastActivityTime = Long.parseLong(lastActivityTimeStr);
        long currentTime = Instant.now().toEpochMilli();

        if ((currentTime - lastActivityTime) > TimeUnit.MINUTES.toMillis(1)) {
            var updates = redisTemplate.opsForList().range(getUpdatesKey(eventId), 0, -1);

            if (updates != null && !updates.isEmpty()) {
                scheduleDetailService.update(updates);

                redisTemplate.delete(getUpdatesKey(eventId));
                redisTemplate.delete(getLastActivityKey(eventId));
                redisTemplate.delete(getEventIdKey());
            }
        }
    }

    /**
     * Redis에서 현재 저장된 이벤트 ID 가져오기
     * @return String 이벤트 ID
     */
    private String getCurrentEventIdFromRedis() {
        return (String) redisTemplate.opsForValue().get(getEventIdKey());
    }

    /**
     * 이벤트 ID Redis 키 생성 메서드
     * @return String Redis 이벤트 ID 키
     */
    private String getEventIdKey() {
        return "eventId";
    }

    /**
     * 마지막 활동 시간 Redis 키 생성 메서드
     * @param eventId 이벤트 ID
     * @return String Redis 마지막 활동 시간 키
     */
    private String getLastActivityKey(String eventId) {
        return eventId + "_lastActivityTime";
    }

    /**
     * 업데이트 리스트 Redis 키 생성 메서드
     * @param eventId 이벤트 ID
     * @return String Redis 업데이트 리스트 키
     */
    private String getUpdatesKey(String eventId) {
        return eventId + "_updates";
    }
}
