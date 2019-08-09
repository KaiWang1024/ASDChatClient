package edu.mum.request;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Client implements Subject {
    private static Client instance = null;
    private Socket socket;
    private PrintStream printStream;
    private BufferedReader reader;
    private Thread readerThread;
    private List<Observer> observers;
    private final Object MUTEX = new Object();

    private Client() {
        this.socket = new Socket();
    }

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public void initializeSocket(int localPort) throws IOException {
        // init observers
        observers = new ArrayList<Observer>();
        // bind port
        socket.bind(new InetSocketAddress(Inet4Address.getLocalHost(), localPort));
        // load time
//        socket.setSoTimeout(10 * 1000);
        socket.setReuseAddress(true);
        socket.setTcpNoDelay(true);
        socket.setKeepAlive(true);
        socket.setReceiveBufferSize(64 * 1024 * 1024);
        socket.setSendBufferSize(64 * 1024 * 1024);
        socket.setPerformancePreferences(1, 1, 1);
    }

    public void connect(SocketAddress address, int var2) throws IOException {
        socket.connect(address, var2);

        // write stream
        OutputStream outputStream = socket.getOutputStream();
        printStream = new PrintStream(outputStream);

        // buffer reader from socket
        InputStream inputStream = socket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));

        readerThread = new Thread(this::read);
        readerThread.start();
    }

    public void write(String content) throws IOException {
        printStream.println(content);
    }

    public void read() {
        try {
            while (true) {
                String response = reader.readLine();
                System.out.println("receive: " + response);
                notifyObservers(response);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (printStream != null) {
                printStream.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (socket != null) {
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attach(Observer observer) {
        synchronized (MUTEX) {
            if (!observers.contains(observer))
                observers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        synchronized (MUTEX) {
            int i = observers.indexOf(observer);
            if (i >= 0)
                observers.remove(i);
        }
    }

    @Override
    public void notifyObservers(String msg) {
        synchronized (MUTEX) {
            int n = observers.size();
            for (int i = 0; i < n; ++i) {
                Observer observer = (Observer)observers.get(i);
                observer.update(msg);
            }
        }
    }
}
