package com.qaqa.dalill.schedule.controller;

import com.qaqa.dalill.schedule.service.action.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/action")
@RequiredArgsConstructor
public class ActionController {

    private final ActionService actionService;

    /**
     * 이벤트 시작
     * @return Mono<String> 생성된 Event ID
     */
    @PostMapping("/start")
    public Mono<String> startEvent() {
        return actionService.startEvent();
    }

    /**
     * 이벤트 업데이트
     * 이벤트 도중 변경사항 발생 시 요청 바디에서 데이터를 받아 Redis에 저장
     * @param data 클라이언트로부터 받은 데이터
     * @return Mono<Void>
     */
    @PostMapping("/update")
    public Mono<Void> updateEvent(@RequestBody Object data) {
        return actionService.updateEvent(data);
    }

    /**
     * 이벤트 실시간 업데이트 확인
     * Server-Sent Events (SSE)를 통해 실시간 업데이트 전송
     * @return Flux<Object> 이벤트의 실시간 업데이트 스트림
     */
    @GetMapping(value = "/updates", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> getUpdates() {
        return actionService.getUpdates();
    }

    /**
     * 이벤트 종료
     * 이벤트가 종료되면 Redis의 데이터를 MongoDB로 이전
     */
    @PostMapping("/stop")
    public Mono<Void> stopEvent() {
        actionService.stopEvent();
        return Mono.empty();
    }
}
