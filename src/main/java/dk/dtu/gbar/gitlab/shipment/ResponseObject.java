package dk.dtu.gbar.gitlab.shipment;

public class ResponseObject {
    int errorCode;
    String errorMessage;
    Client client;
    Container container;
    ClientList clientList;
    ContainerList containerList;

    public ResponseObject(int errorCode, ContainerList containerList) {
        this.errorCode = errorCode;
        this.containerList = containerList;
    }

    public ResponseObject(int errorCode, ClientList clientList) {
        this.errorCode = errorCode;
        this.clientList = clientList;
    }

    public ResponseObject(int errorCode, Container container) {
        this.errorCode = errorCode;
        this.container = container;
    }

    public ResponseObject(int errorCode, Client client) {
        this.errorCode = errorCode;
        this.client = client;
    }

    public ResponseObject(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
