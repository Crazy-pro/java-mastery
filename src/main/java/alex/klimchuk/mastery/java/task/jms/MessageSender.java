package alex.klimchuk.mastery.java.task.jms;

import org.springframework.beans.factory.annotation.Autowired;
import alex.klimchuk.mastery.java.task.entities.TransferableEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Copyright Alex Klimchuk (c) 29.02.2020.
 */
@Component
public class MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final TransferableEntity entity) {
        LOGGER.debug("Data sent to message queue! \n" + entity);
        jmsTemplate.convertAndSend("MessageQueue", entity);
    }

}
