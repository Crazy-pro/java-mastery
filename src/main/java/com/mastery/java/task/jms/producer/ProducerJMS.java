package com.mastery.java.task.jms.producer;

import org.springframework.jms.core.support.JmsGatewaySupport;
import com.mastery.java.task.entities.TransferableEntity;

import java.util.HashMap;
import java.util.Map;

public class ProducerJMS extends JmsGatewaySupport {

    public void sendMessage(final TransferableEntity entity) {
        System.out.println("Producer sends:\n" + entity);
        Map map = new HashMap();
        map.put("departmentId", entity.getDepartmentId());
        map.put("jobTitle", entity.getJobTitle());
        getJmsTemplate().convertAndSend(map);
    }

}
