package com.qaqa.dalill.schedule.model.schedule;

import com.qaqa.dalill.schedule.model.block.MemoBlock;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.UUID;

@RedisHash("scheduleDetail")
public class ScheduleDetailRedis {
    @Id
    private UUID uuid;
    @Indexed(unique = true)
    private UUID connect_uuid;
    private List<MemoBlock> memo_blocks;
}
