package server;

import server.client.Client;
import server.client.ClientGUI;
import server.server.Server;
import server.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        Client client =  new Client(server);
        ServerWindow serverWindow = new ServerWindow(server);
        ClientGUI c1 = new ClientGUI(serverWindow);
        //ClientGUI c2 = new ClientGUI(serverWindow);
    }
}
