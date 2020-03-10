package com.mastery.java.task.jms;

import org.springframework.beans.factory.annotation.Autowired;
import com.mastery.java.task.entities.TransferableEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Component
public class MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final TransferableEntity entity) {
        LOGGER.info("Data sent to message queue! \n" + entity);
        jmsTemplate.convertAndSend("MessageQueue", entity);
    }

}
