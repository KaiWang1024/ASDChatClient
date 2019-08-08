package edu.mum;

import edu.mum.domain.MessageFactory;
import edu.mum.domain.RequestModel;
import edu.mum.request.ClientManager;
import edu.mum.request.Listener;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class ASDChatClient {
    private ClientManager manager;
    private String username;

    public ASDChatClient(Listener listener) {
        manager = new ClientManager();
        manager.setListener(listener);
    }

    public void register(String username, String password) {
        this.username = username;
        RequestModel msg = MessageFactory.createRegisterMessage();
        msg.setFrom(username);
        msg.setPayload(hashSum(password));
        manager.sendRequest(msg);
    }

    public void login(String username, String password) {
        this.username = username;
        RequestModel msg = MessageFactory.createLoginMessage();
        msg.setFrom(username);
        msg.setPayload(hashSum(password));
        manager.sendRequest(msg);
    }

    public void sendMessage(String to, String msg, boolean broadcast) {
        RequestModel request = MessageFactory.createSendMessage();
        request.setFrom(this.username);
        request.setTo(to);
        request.setPayload(msg);
        request.setBroadcast(broadcast);
        manager.sendRequest(request);
    }

    public void getOnlineUsers() {
        RequestModel request = MessageFactory.createUserListMessage();
        request.setFrom(this.username);
        manager.sendRequest(request);
    }

    public void createGroup(String groupName, List<String> members) {
        RequestModel request = MessageFactory.createGroupMessage();
        request.setFrom(groupName);
        request.setPayload(String.join(",", members));
        manager.sendRequest(request);
    }

    private String hashSum(String input) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
