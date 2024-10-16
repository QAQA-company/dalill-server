package com.qaqa.dalill.schedule.repository;

import com.qaqa.dalill.schedule.model.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    List<Schedule> findAllByUserUuid(UUID userId);
}
