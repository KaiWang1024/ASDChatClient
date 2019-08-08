package edu.mum.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.mum.domain.RequestModel;
import edu.mum.domain.ResponseModel;

import java.io.IOException;

public class ClientManager implements Observer {
    private Listener listener;

    public ClientManager() {
        ClientDirector director = new ClientDirector(new SocketClientBuilder());
        try {
            director.constructClient();
            Client client = director.getClient();
            client.attach(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void sendRequest(RequestModel request) {
        String msg = JSON.toJSONString(request);
        try {
            Client.getInstance().write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String content) {
        JSONObject object = JSON.parseObject(content);
        if (object.containsKey("cmd")) {
            // receive message
            RequestModel msg = JSON.parseObject(content, RequestModel.class);
            listener.receiveMessage(msg);
        } else {
            // server response
            ResponseModel response = JSON.parseObject(content, ResponseModel.class);
            listener.receiveResponse(response);
        }

    }
}
