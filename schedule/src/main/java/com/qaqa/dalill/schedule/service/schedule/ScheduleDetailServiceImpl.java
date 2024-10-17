package com.qaqa.dalill.schedule.service.schedule;

import com.qaqa.dalill.schedule.model.response.CustomResponseEntity;
import com.qaqa.dalill.schedule.model.schedule.ScheduleDetail;
import com.qaqa.dalill.schedule.repository.ScheduleDetailMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleDetailServiceImpl implements ScheduleDetailService {

    private final ScheduleDetailMongoRepository mongoRepository;

    @Override
    public CustomResponseEntity save(UUID connector) {
        ScheduleDetail scheduleDetail = ScheduleDetail.builder()
                .uuid(UUID.randomUUID())
                .build();

        mongoRepository.save(scheduleDetail);

        return CustomResponseEntity.builder()
                .status(200)
                .message("Saved")
                .build();
    }

    @Override
    public void update(Object update) {
        log.info("Update schedule detail");
        ScheduleDetail scheduleDetail = (ScheduleDetail) update;
        mongoRepository.save(scheduleDetail);
    }

    @Override
    public ScheduleDetail get(UUID uuid) {
        log.info("Get schedule detail");
        return mongoRepository.findById(uuid).orElseThrow(RuntimeException::new);
    }
}
