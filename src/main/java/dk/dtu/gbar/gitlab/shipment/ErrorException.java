package dk.dtu.gbar.gitlab.shipment;

public class ErrorException extends Exception {

	protected ErrorException(String errorMessage) {
		super(errorMessage);
	}

}
