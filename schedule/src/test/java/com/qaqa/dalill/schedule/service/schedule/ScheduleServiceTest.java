package com.qaqa.dalill.schedule.service.schedule;

import com.qaqa.dalill.schedule.model.schedule.ScheduleDetail;
import com.qaqa.dalill.schedule.repository.ScheduleDetailMongoRepository;
import com.qaqa.dalill.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleServiceTest {

    @Autowired
    private ScheduleService service;
    @Autowired
    private ScheduleRepository repository;
    @Autowired
    private ScheduleDetailMongoRepository detailRepository;

    @Test
    void testCreate() {
        repository.findAll().forEach(schedule -> {
            UUID connectUuid = schedule.getConnect_uuid();
            System.out.println(connectUuid.toString());

            ScheduleDetail scheduleDetail = detailRepository.findByConnectUuid(connectUuid.toString());
            assertNotNull(scheduleDetail);
        });
    }
}