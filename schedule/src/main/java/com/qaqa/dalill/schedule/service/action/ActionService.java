package com.qaqa.dalill.schedule.service.action;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionService {
    public final ApplicationEventPublisher applicationEventPublisher;
}
