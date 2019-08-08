package edu.mum.domain;

public class ResponseModel {
    private boolean success;
    private String payload;

    public ResponseModel(boolean success, String payload) {
        this.success = success;
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "success=" + success +
                ", payload='" + payload + '\'' +
                '}';
    }
}
