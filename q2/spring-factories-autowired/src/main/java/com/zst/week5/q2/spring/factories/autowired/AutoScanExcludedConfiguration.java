package com.zst.week5.q2.spring.factories.autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoScanExcludedConfiguration {
    @Bean
    public Service service() {
        return new Service();
    }
}
