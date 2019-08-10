package edu.mum.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.mum.domain.IRequestModel;
import edu.mum.domain.RequestModel;
import edu.mum.domain.ResponseModel;

import java.io.IOException;
import java.lang.reflect.Type;

public class ClientManager implements Observer {
    private Listener listener;
    private IRequestModel requestModel;

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

    public void sendRequest(IRequestModel request) {
        requestModel = request;
        String msg = request.jsonString();
        try {
            Client.getInstance().write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disConnect() {
        Client.getInstance().close();
    }

    public void reconnect() {
        ClientDirector director = new ClientDirector(new SocketClientBuilder());
        try {
            director.constructClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String content) {
        JSONObject object = JSON.parseObject(content);
        if (object.containsKey("cmd")) {
            // receive message
            listener.receiveMessage(this.requestModel.deserialize(content));
        } else {
            // server response
            listener.receiveResponse(this.requestModel.getResponse().deserialize(content));
        }
    }
}
