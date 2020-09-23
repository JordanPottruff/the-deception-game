package main;

import main.actions.CreateGameAction;
import server.WebServer;

import java.io.IOException;

public class Main {

    private static final String ADDRESS = "localhost";
    private static final int PORT = 8000;
    private static final int BACKLOG = 100;

    public static void main(String[] args) throws IOException {
        WebServer server = new WebServer(ADDRESS, PORT, BACKLOG);
        server.addAction(new CreateGameAction());
        server.start();
    }
}
