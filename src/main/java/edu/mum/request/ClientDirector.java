package edu.mum.request;

import java.io.IOException;

public class ClientDirector {
    private ClientBuilder builder = null;

    public ClientDirector(ClientBuilder builder) {
        this.builder = builder;
    }

    public void constructClient() throws IOException {
        builder.initClient();
        builder.connect();
    }

    public Client getClient() {
        return builder.getClient();
    }
}
