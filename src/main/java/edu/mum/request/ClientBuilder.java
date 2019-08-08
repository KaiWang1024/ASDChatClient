package edu.mum.request;

import java.io.IOException;

public interface ClientBuilder {
    public void initClient() throws IOException;
    public void connect() throws IOException;
    public Client getClient();
}
