package com.mastery.java.task.jms;

import org.springframework.beans.factory.annotation.Autowired;
import com.mastery.java.task.entities.TransferableEntity;
import com.mastery.java.task.services.EmployeeService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private EmployeeService employeeService;

    @JmsListener(destination = "MessageQueue")
    public void receiveMessage(final TransferableEntity entity) {
        System.out.print("Received message: \n" + entity);
        employeeService.updateJobTitleByDepartmentId(entity.getDepartmentId(), entity.getJobTitle());
    }

}
