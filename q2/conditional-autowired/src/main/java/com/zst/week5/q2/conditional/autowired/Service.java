package com.zst.week5.q2.conditional.autowired;

import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ConditionalOnNotWebApplication
@org.springframework.stereotype.Service
public class Service {
    public String getDateTime() {
        return DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
    }
}
