package com.qaqa.dalill.schedule.model.block;

import jakarta.persistence.*;

public class MemoBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String start_time;
    private String end_time;
    private int y_position;
}