package net.homenet;

import org.springframework.dao.DataIntegrityViolationException;

public class VehicleNoDuplicateException extends DataIntegrityViolationException {
    public VehicleNoDuplicateException(String msg) {
        super(msg);
    }

    public VehicleNoDuplicateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
