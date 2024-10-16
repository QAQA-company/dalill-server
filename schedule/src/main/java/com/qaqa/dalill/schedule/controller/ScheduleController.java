package com.qaqa.dalill.schedule.controller;

import com.qaqa.dalill.schedule.model.response.CustomResponseEntity;
import com.qaqa.dalill.schedule.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/new")
    public CustomResponseEntity newSchedule() {
        return scheduleService.newSchedule();
    }

    @PatchMapping("/change-name")
    public CustomResponseEntity changeScheduleName(@RequestParam("uuid") String uuid, @RequestParam("name") String name) {
        UUID newUuid = UUID.fromString(uuid);
        return scheduleService.changeName(newUuid, name);
    }

}
