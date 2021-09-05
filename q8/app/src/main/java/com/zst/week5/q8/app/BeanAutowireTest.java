package com.zst.week5.q8.app;

import com.zst.week5.q8.student.klass.school.starter.pojo.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BeanAutowireTest {
    @Autowired
    private School school;

    @PostConstruct
    public void postConstruct() {
        school.ding();
    }
}
