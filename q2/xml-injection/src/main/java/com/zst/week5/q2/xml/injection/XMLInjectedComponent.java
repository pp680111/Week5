package com.zst.week5.q2.xml.injection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLInjectedComponent {
    private static final Logger logger = LoggerFactory.getLogger(XMLInjectedComponent.class);

    private Entity entity;

    public void initMethod() {
        logger.info(String.format("注入的Bean的内容=[no:%s, name:%s])", entity.getNo(), entity.getName()));
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
