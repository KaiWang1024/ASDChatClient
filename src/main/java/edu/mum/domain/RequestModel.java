package edu.mum.domain;

public class RequestModel {
    private String cmd;
    private String to;
    private String from;
    private boolean isBroadcast;
    private String payload;

    public RequestModel(String cmd, String to, String from, boolean isBroadcast, String payload) {
        this.cmd = cmd;
        this.to = to;
        this.from = from;
        this.isBroadcast = isBroadcast;
        this.payload = payload;
    }

    public RequestModel(String cmd) {
        this.cmd = cmd;
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
    public String toString() {
        return "RequestModel{" +
                "cmd='" + cmd + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", isBroadcast=" + isBroadcast +
                ", payload='" + payload + '\'' +
                '}';
    }
}
