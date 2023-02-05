package edu.tum.ase.ase23.payload.request;

public class EmailRequest {
    String customerId;

    public EmailRequest(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
