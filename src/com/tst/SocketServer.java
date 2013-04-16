package com.tst;
import com.tst.feedingestor.parsers.JSONParser;

import java.io.*;
import java.net.*;
import java.util.Random;

public class SocketServer implements Runnable {

    protected ServerSocket socket;

    public SocketServer(int port) {
        try {
            this.socket = new ServerSocket(port);
        } catch(UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void run() {
        try {
            Random rand = new Random();
            System.out.println("Waiting for connections.");
            Socket socket = this.socket.accept();
            System.out.println("Connected!");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while(true) {
                int sleepVal = rand.nextInt(2000);
                Thread.sleep(sleepVal);
                String outVal = "{\"yeah\":\"yeah\"}carp[{\"foo\": \"bar\", \"baz\":[1,2,3,4,5]}, {\"a\":1}, {\"b\":2}]carp";
                out.println(outVal);
                System.out.println("Broadcasted: " + outVal);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
