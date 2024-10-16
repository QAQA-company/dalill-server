package com.qaqa.dalill.schedule.model.schedule;

import com.qaqa.dalill.schedule.model.block.MemoBlock;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collation = "block")
public class ScheduleDetail {
    @Id
    @Indexed(unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private UUID connect_uuid;
    private List<MemoBlock> memo_blocks;
}
