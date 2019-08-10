package edu.mum.domain;

public abstract class AbstractRequestModel implements IRequestModel {
    private String cmd;
    private String to;
    private String from;
    private boolean isBroadcast;
    private String payload;
    private IResponseModel response;

    public abstract String getCmd();
    public abstract void setCmd(String cmd);
    public abstract String getTo();
    public abstract void setTo(String to);
    public abstract String getFrom();
    public abstract void setFrom(String from);
    public abstract boolean isBroadcast();
    public abstract void setBroadcast(boolean broadcast);
    public abstract String getPayload();
    public abstract void setPayload(String payload);
    public abstract IResponseModel getResponse();
    public abstract void setResponse(IResponseModel response);
}
