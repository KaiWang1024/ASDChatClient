package edu.mum;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.mum.domain.RequestModel;
import edu.mum.domain.ResponseModel;
import edu.mum.request.Listener;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App implements Listener
{
    private ASDChatClient client;

    public void setClient(ASDChatClient client) {
        this.client = client;
    }

    public static void main( String[] args ) throws IOException {
        App app = new App();
        ASDChatClient client = new ASDChatClient(app);
        app.setClient(client);
        app.login();
    }

    public void login() throws IOException {
        Console console = System.console();
        String username = console.readLine("Username: ");
        String password = new String(console.readPassword("Password: "));
        this.client.login(username, password);
        this.readCommand();
    }

    public void readCommand() throws IOException {
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        String readLine = "";

        do {
            readLine = input.readLine();

            if (readLine.equals("/help")) {
                printHelp();
            } else {
                int separator = readLine.indexOf(" ");
                if (separator > 0 && readLine.startsWith("/")) {
                    String to = readLine.substring(1, separator);
                    String payload = readLine.substring(separator + 1);
                    this.client.sendMessage(to, payload, false);
                } else {
                    System.out.println("Wrong command");
                }
            }

        } while (!readLine.equals("exit") && !readLine.equals("/quit"));
    }

    public static void printHelp() {
        System.out.println("/help \t\t\t\t Check command format.");
        System.out.println("/quit \t\t\t\t Quit app");
        System.out.println("/username message \t\t\t Send 'message' to 'username'");
    }
    @Override
    public void receiveResponse(ResponseModel response) {
        System.out.println("Error: " + response.getPayload());
    }

    @Override
    public void receiveMessage(RequestModel message) {
        System.out.println(message.getFrom() + ": " + message.getPayload());
    }
}
