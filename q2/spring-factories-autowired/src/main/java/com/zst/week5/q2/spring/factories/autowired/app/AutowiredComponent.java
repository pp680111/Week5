package com.zst.week5.q2.spring.factories.autowired.app;

import com.zst.week5.q2.spring.factories.autowired.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AutowiredComponent {
    private static final Logger logger = LoggerFactory.getLogger(AutowiredComponent.class);

    @Autowired
    private Service service;

    @PostConstruct
    public void postConstruct() {
        logger.info("调用注入的Bean=" + service.getDateTime());
    }
}
