package edu.mum.domain;

public class MessageFactory {
    public static RequestModel createRegisterMessage() {
        RequestModel model = new RequestModel("register");
        model.setTo("");
        model.setBroadcast(false);
        return model;
    }

    public static RequestModel createLoginMessage() {
        RequestModel model = new RequestModel("login");
        model.setBroadcast(false);
        model.setTo("");
        return model;
    }

    public static RequestModel createSendMessage() {
        return new RequestModel("send");
    }

    public static RequestModel createGroupMessage() {
        RequestModel model = new RequestModel("group");
        model.setTo("");
        model.setBroadcast(false);
        return model;
    }

    public static RequestModel createUserListMessage() {
        RequestModel model = new RequestModel("userList");
        model.setTo("");
        model.setBroadcast(false);
        model.setPayload("");
        return model;
    }
}
