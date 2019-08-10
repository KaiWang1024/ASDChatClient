package edu.mum.factory;

import edu.mum.domain.AbstractRequestModel;
import edu.mum.domain.IRequestModel;

public interface IModelFactory {
    public abstract AbstractRequestModel createRegisterMessage();
    public abstract AbstractRequestModel createLoginMessage();
    public abstract AbstractRequestModel createSendMessage();
    public abstract AbstractRequestModel createUserListMessage();
    public abstract AbstractRequestModel createGroupListMessage();
    public abstract AbstractRequestModel createGroupMessage();
}
