package com.zst.week5.q2.manual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 手动定义BeanDefinition，然后注册到BeanFactory中
 */
@Component
public class ManualBeanCreator {
    private static final Logger logger = LoggerFactory.getLogger(ManualBeanCreator.class);

    @Autowired
    private DefaultListableBeanFactory listableBeanFactory;
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void postConstruct() {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Entity.class)
                .addPropertyValue("name", "Nameless")
                .addPropertyValue("no", "Zero")
                .getBeanDefinition();
        /*
          此处Spring的官方文档里提到的操作方式是调用ApplicationContext.getBeanFactory的方式
          获取DefaultListableBeanFactory对象，但是不知道为什么当前版本里的ApplicationContext并没有此方法，
          文档的版本的使用的Spring版本是对的上的。。。。。。
         */
        listableBeanFactory.registerBeanDefinition("user", beanDefinition);

        Entity user = applicationContext.getBean(Entity.class);
        logger.info(String.format("获取手动创建的Bean=[no:%s, name:%s]", user.getNo(), user.getName()));
    }
}
