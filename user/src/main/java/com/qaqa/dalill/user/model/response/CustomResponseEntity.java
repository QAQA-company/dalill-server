package com.qaqa.dalill.user.model.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class CustomResponseEntity<E> {
    protected int status;
    protected E message;
}
