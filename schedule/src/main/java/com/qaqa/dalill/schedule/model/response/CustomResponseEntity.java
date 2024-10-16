package com.qaqa.dalill.schedule.model.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CustomResponseEntity<E> {
    private int status;
    private E message;
}
