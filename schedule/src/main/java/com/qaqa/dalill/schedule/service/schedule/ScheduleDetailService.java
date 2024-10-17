package com.qaqa.dalill.schedule.service.schedule;

import com.qaqa.dalill.schedule.model.response.CustomResponseEntity;
import com.qaqa.dalill.schedule.model.schedule.ScheduleDetail;

import java.util.UUID;

public interface ScheduleDetailService {
    CustomResponseEntity save(UUID connector);
    void update(Object update);
    ScheduleDetail get(UUID uuid);
}
