package com.qaqa.dalill.schedule.service.schedule;

import com.qaqa.dalill.schedule.model.response.CustomResponseEntity;
import com.qaqa.dalill.schedule.model.schedule.Schedule;
import com.qaqa.dalill.schedule.repository.ScheduleRepository;
import com.qaqa.dalill.schedule.service.messaging.UserListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDetailService scheduleDetailService;
    private final UserListener userListener;

    /**
     * 새로운 스케줄을 생성합니다.
     * @return CustomResponseEntity
     */
    @Override
    public CustomResponseEntity newSchedule() {
        UUID connector = UUID.randomUUID();
        UUID useruuid = userListener.getUseruuid();

        Schedule schedule = Schedule.builder()
                .name("새로운 일정")
                .userUuid(useruuid)
                .connect_uuid(connector)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        scheduleRepository.save(schedule);
        scheduleDetailService.save(connector);

        return CustomResponseEntity.builder()
                .status(201)
                .message("Create schedule")
                .build();
    }

    @Override
    public CustomResponseEntity changeName(UUID uuid, String name) {
        Schedule schedule = scheduleRepository.findById(uuid).orElseThrow(RuntimeException::new);
        schedule.changeName(name);
        return CustomResponseEntity.builder()
                .status(200)
                .message("Update schedule")
                .build();
    }
}
