package edu.mum.request;

import edu.mum.domain.IRequestModel;
import edu.mum.domain.IResponseModel;
import edu.mum.domain.RequestModel;
import edu.mum.domain.ResponseModel;

public interface Listener {
    public void receiveResponse(IResponseModel response);
    public void receiveMessage(IRequestModel model);
}
