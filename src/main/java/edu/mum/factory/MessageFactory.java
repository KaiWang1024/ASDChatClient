package edu.mum.factory;

import edu.mum.domain.AbstractRequestModel;
import edu.mum.domain.IRequestModel;
import edu.mum.domain.RequestModel;
import edu.mum.factory.IModelFactory;

public class MessageFactory implements IModelFactory {
    public AbstractRequestModel createRegisterMessage() {
        RequestModel model = new RequestModel("register");
        model.setTo("");
        model.setBroadcast(false);
        return model;
    }

    public AbstractRequestModel createLoginMessage() {
        RequestModel model = new RequestModel("login");
        model.setBroadcast(false);
        model.setTo("");
        return model;
    }

    public AbstractRequestModel createSendMessage() {
        return new RequestModel("send");
    }

    public AbstractRequestModel createGroupMessage() {
        RequestModel model = new RequestModel("group");
        model.setTo("");
        model.setBroadcast(false);
        return model;
    }

    public AbstractRequestModel createUserListMessage() {
        RequestModel model = new RequestModel("userList");
        model.setTo("");
        model.setBroadcast(false);
        model.setPayload("");
        return model;
    }

    public AbstractRequestModel createGroupListMessage() {
        RequestModel model = new RequestModel("groupList");
        model.setTo("");
        model.setBroadcast(false);
        model.setPayload("");
        return model;
    }
}
