package com.mastery.java.task.jms.converter;

import com.mastery.java.task.entities.TransferableEntity;
import org.springframework.jms.support.converter.*;

import javax.jms.*;

public class EntityMessageConverter implements MessageConverter {

    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        TransferableEntity entity = (TransferableEntity) object;
        MapMessage message = session.createMapMessage();
        message.setLong("departmentId", entity.getDepartmentId());
        message.setString("jobTitle", entity.getJobTitle());
        return message;
    }

    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        MapMessage mapMessage = (MapMessage) message;
        TransferableEntity entity = new TransferableEntity(mapMessage.getLong("departmentId"), mapMessage.getString("jobTitle"));
        return entity;
    }

}
