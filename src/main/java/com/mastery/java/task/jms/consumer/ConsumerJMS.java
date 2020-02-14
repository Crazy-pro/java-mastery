package com.mastery.java.task.jms.consumer;

import org.springframework.jms.core.support.JmsGatewaySupport;
import com.mastery.java.task.entities.TransferableEntity;

import java.util.Map;

public class ConsumerJMS extends JmsGatewaySupport {

    public TransferableEntity receiveMessage() {
        Map map = (Map) getJmsTemplate().receiveAndConvert();
        TransferableEntity entity = new TransferableEntity((Long) map.get("departmentId"), (String) map.get("jobTitle"));
        return entity;
    }

}
