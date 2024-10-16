package com.qaqa.dalill.schedule.model.schedule;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_schedule")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(length = 20)
    private String name;
    private UUID connect_uuid;
    private UUID userUuid;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public void changeName(String name) {
        this.name = name;
    }
}
