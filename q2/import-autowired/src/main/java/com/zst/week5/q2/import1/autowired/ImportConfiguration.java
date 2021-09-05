package com.zst.week5.q2.import1.autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 使用Import注解加载Service类作为Bean
 *
 * 在Import注解的注释中，提到了Import注解虽然可以用来创建Bean，但是通常来说是用来处理
 * 配置相关的Bean的创建的，所以根据Import注解的设计目的来说还是不太适合用来创建普通的Bean
 */
@Component
@Import(Service.class)
public class ImportConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ImportConfiguration.class);

    @Autowired
    private Service service;

    @PostConstruct
    public void postConstruct() {
        logger.info("调用注入的Bean=" + service.getDateTime());
    }
}
