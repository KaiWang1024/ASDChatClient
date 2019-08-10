package edu.mum.domain;

import com.alibaba.fastjson.JSON;

public class RequestModel extends AbstractRequestModel {
    private String cmd;
    private String to;
    private String from;
    private boolean isBroadcast;
    private String payload;
    private IResponseModel response;

    public RequestModel(String cmd, String to, String from, boolean isBroadcast, String payload) {
        this.cmd = cmd;
        this.to = to;
        this.from = from;
        this.isBroadcast = isBroadcast;
        this.payload = payload;
        this.response = new ResponseModel(true, "");
    }

    public RequestModel(String cmd) {
        this.cmd = cmd;
        this.response = new ResponseModel(true, "");
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean isBroadcast() {
        return isBroadcast;
    }

    public void setBroadcast(boolean broadcast) {
        isBroadcast = broadcast;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public IResponseModel getResponse() {
        return this.response;
    }

    @Override
    public void setResponse(IResponseModel response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "cmd='" + cmd + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", isBroadcast=" + isBroadcast +
                ", payload='" + payload + '\'' +
                '}';
    }

    @Override
    public String jsonString() {
        return JSON.toJSONString(this);
    }

    @Override
    public RequestModel deserialize(String string) {
        return JSON.parseObject(string, this.getClass());
    }
}
