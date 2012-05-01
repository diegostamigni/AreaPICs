package eu.areamobile.apis.hw.pics.exceptions;

/**
 * Created by AreaMobile
 * Date: 30/04/12
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class UnknownDeviceException extends Exception {
    public UnknownDeviceException() {
    }

    public UnknownDeviceException(String detailMessage) {
        super(detailMessage);
    }
}
