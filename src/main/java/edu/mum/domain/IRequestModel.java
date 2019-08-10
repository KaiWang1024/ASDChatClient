package edu.mum.domain;

public interface IRequestModel {
    String cmd = "";
    String to = "";
    String from = "";
    boolean isBroadcast = false;
    String payload = "";
    IResponseModel response = null;

    public String jsonString();
    public IRequestModel deserialize(String string);
    public IResponseModel getResponse();
}
