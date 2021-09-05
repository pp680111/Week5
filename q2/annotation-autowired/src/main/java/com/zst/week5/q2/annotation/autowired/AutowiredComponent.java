package com.zst.week5.q2.annotation.autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 使用@Component注解的类，被Spring创建为Bean，并注入了所需要的Service类型的Bean
 */
@Component
public class AutowiredComponent {
    private static final Logger logger = LoggerFactory.getLogger(AutowiredComponent.class);

    @Autowired
    private Service service;

    @PostConstruct
    public void postConstruct() {
        logger.info("调用自动注入的Bean的结果===" + service.getDateTime());
    }
}
