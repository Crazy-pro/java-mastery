package com.mastery.java.task.jms;

import org.springframework.beans.factory.annotation.Autowired;
import com.mastery.java.task.entities.TransferableEntity;
import com.mastery.java.task.services.EmployeeService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Component
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private EmployeeService employeeService;

    @JmsListener(destination = "MessageQueue")
    public void receiveMessage(final TransferableEntity entity) {
        LOGGER.info("Received message: \n" + entity);
        employeeService.updateJobTitleByDepartmentId(entity.getDepartmentId(), entity.getJobTitle());
    }

}
