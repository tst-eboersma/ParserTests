package com.tst;


import com.tst.feedingestor.feeds.ASCIIFeed;

import java.io.IOException;

public class Main {
    public static int SERVER_PORT = 5471;

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SocketServer server = new SocketServer(SERVER_PORT);
                System.out.println("Server Starting");
                server.run();
                System.out.println("Not blocked!");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I'm running!");
                try {
                    Thread.sleep(1000);
                    ASCIIFeed feed = new ASCIIFeed("localhost", SERVER_PORT, "carp");
                    feed.run();
                } catch (Exception ex) {
                    System.out.println("Exited!");
                    System.out.println(ex.getMessage());
                }
            }
        });
        t.start();
        t2.start();
    }
}
