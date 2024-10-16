package com.qaqa.dalill.schedule.repository;

import com.qaqa.dalill.schedule.model.schedule.ScheduleDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleDetailRedisRepository extends CrudRepository<ScheduleDetail, UUID> {
}
