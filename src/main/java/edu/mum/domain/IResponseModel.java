package edu.mum.domain;

public interface IResponseModel {
    boolean success = false;
    String payload = "";

    public IResponseModel deserialize(String string);
}
