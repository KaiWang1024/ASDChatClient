package edu.mum.request;

import edu.mum.domain.RequestModel;
import edu.mum.domain.ResponseModel;

public interface Listener {
    public void receiveResponse(ResponseModel response);
    public void receiveMessage(RequestModel message);
}
