package com.zst.week5.q2.spring.factories.autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Service {
    public String getDateTime() {
        return DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
    }
}
