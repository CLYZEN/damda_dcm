package kr.damda.dcm.exception;

/* Iot Exception */
public class DeviceAlreadyRegisteredException extends RuntimeException {

    public DeviceAlreadyRegisteredException(String message) {
        super(message);
    }
}
