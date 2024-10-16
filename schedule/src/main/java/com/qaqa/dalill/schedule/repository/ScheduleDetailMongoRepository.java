package com.qaqa.dalill.schedule.repository;

import com.qaqa.dalill.schedule.model.schedule.ScheduleDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleDetailMongoRepository extends MongoRepository<ScheduleDetail, UUID> {
}
