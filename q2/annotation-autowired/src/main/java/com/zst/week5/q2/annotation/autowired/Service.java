package com.zst.week5.q2.annotation.autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 作为Service的Bean，被Spring扫描到并创建
 */
@org.springframework.stereotype.Service
public class Service {
    public String getDateTime() {
        return DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
    }
}
