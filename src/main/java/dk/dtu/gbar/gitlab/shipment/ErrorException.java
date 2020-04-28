package dk.dtu.gbar.gitlab.shipment;

public class ErrorException extends Exception {

	public ErrorException(String errorMessage) {
		super(errorMessage);
	}

}
