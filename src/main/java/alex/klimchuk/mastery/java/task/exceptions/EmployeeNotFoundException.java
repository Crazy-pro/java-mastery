package alex.klimchuk.mastery.java.task.exceptions;

/**
 * Copyright Alex Klimchuk (c) 29.02.2020.
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException() {
        super();
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }

    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
