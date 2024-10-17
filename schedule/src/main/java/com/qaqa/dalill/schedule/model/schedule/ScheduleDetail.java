package com.qaqa.dalill.schedule.model.schedule;

import com.qaqa.dalill.schedule.model.block.MemoBlock;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "block")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDetail {
    @Id
    private UUID uuid;
    @Indexed(unique = true)
    private UUID connectUuid;
    private List<MemoBlock> memoBlocks;
}
