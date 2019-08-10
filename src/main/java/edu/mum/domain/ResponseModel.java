package edu.mum.domain;

import com.alibaba.fastjson.JSON;

public class ResponseModel implements IResponseModel {
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

    @Override
    public IResponseModel deserialize(String string) {
        return JSON.parseObject(string, this.getClass());
    }
}
