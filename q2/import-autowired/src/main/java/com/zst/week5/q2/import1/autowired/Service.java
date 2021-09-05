package com.zst.week5.q2.import1.autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Service {
    public String getDateTime() {
        return DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
    }
}
