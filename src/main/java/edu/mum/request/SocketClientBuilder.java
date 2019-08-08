package edu.mum.request;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;

public class SocketClientBuilder implements ClientBuilder {
    private Client client;

    @Override
    public void initClient() throws IOException {
        client = Client.getInstance();
        client.initializeSocket(7878);
    }

    @Override
    public void connect() throws IOException {
        client.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 20000), 10000);
    }

    @Override
    public Client getClient() {
        return client;
    }
}
