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
        app.readCommand();
    }

    private void login() throws IOException {
        Console console = System.console();
        String username = console.readLine("Username: ");
        String password = new String(console.readPassword("Password: "));
        this.client.login(username, password);
        this.readCommand();
    }

    private void readCommand() throws IOException {
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        String readLine = "";

        do {
            readLine = input.readLine();

            if (readLine.equals("/help")) {
                printHelp();
            } else if (readLine.equals("/users")) {
                getOnlineUsers();
            } else if (readLine.equals("/groups")) {
                getGroupList();
            } else {
                executeCommand(readLine);
            }

        } while (!readLine.equals("exit") && !readLine.equals("/quit"));
    }

    private void executeCommand(String command) {
        int separator = command.indexOf(" ");
        if (separator > 0 && command.startsWith("/")) {
            String cmd = command.substring(1, separator);
            String payload = command.substring(separator);
            if (cmd.equals("register")) {
                // register a new user
                registerUser(payload);
            } else {
                // send message
                sendMessage(cmd, payload);
            }
        } else {
            System.out.println("Wrong command");
        }
    }

    private void registerUser(String payload) {
        String[] args = payload.split(" ");
        String username = "";
        String password = "";
        int i = 0;
        while (i < args.length) {
            if (args[i].equals("-u")) {
                username = args[++i];
                ++i;
            } else if (args[i].equals("-p")) {
                password = args[++i];
                ++i;
            } else {
                ++i;
            }
        }
//        System.out.println("register user");
//        System.out.println("username: " + username);
//        System.out.println("password: " + password);
        this.client.register(username, password);
    }

    private void sendMessage(String to, String payload) {
        boolean isBroadcast = payload.indexOf(" -g") >= 0 ? true : false;
        String msg = isBroadcast ? payload.replaceAll(" -g", "") : payload;
//        System.out.println("Is group chat: " + isBroadcast);
//        System.out.println("Send to: " + to);
//        System.out.println("Message: " + msg);
        this.client.sendMessage(to, msg, isBroadcast);
    }

    public static void printHelp() {
        System.out.println("/help \t\t\t\t\t Check command format.");
        System.out.println("/quit \t\t\t\t\t Quit app");
        System.out.println("/username [-g] message \t\t Send 'message' to 'username'. Use [-g] to send a message to a group, here 'username' is the group name.");
        System.out.println("/register -u username -p password \t Register a new user.");
        System.out.println("/users \t\t\t\t\tGet online user list.");
        System.out.println("/groups\t\t\t\t\tGet group list");
    }

    private void getOnlineUsers() {
        this.client.getOnlineUsers();
    }

    private void getGroupList() {
        this.client.getGroupList();
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
