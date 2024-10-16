package com.qaqa.dalill.schedule.model.block;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemoBlock {
    @Id
    private Long id;

    private String content;

    private String start_time;
    private String end_time;
    private int y_position;
}