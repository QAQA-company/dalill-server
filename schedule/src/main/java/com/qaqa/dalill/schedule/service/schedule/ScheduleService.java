package com.qaqa.dalill.schedule.service.schedule;

import com.qaqa.dalill.schedule.model.response.CustomResponseEntity;

import java.util.UUID;

public interface ScheduleService {
    CustomResponseEntity newSchedule();
    CustomResponseEntity changeName(UUID uuid, String name);
}
