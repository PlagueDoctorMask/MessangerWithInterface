package server.server;

import server.client.ClientGUI;
import server.client.Client;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class Server {

    private boolean work;

    //private final Client client;
    private static final String LOG_PATH = "src/server/server/log.txt";
    private List<ClientGUI> clientGUIList;

    private JTextArea log;

    Client client = new Client(this);
    public boolean connectUser(ClientGUI clientGUI){
        if (!work){
            return false;
        }
        clientGUIList.add(clientGUI);
        return true;
    }

    public void message(String text){
        if (!work){
            return;
        }
        appendLog(text);
        answerAll(text);
        saveInLog(text);
    }

    private void answerAll(String text){
        for (ClientGUI clientGUI: clientGUIList){
            client.answerFromServer(text);
        }
    }

    private void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void disconnectUser(ClientGUI clientGUI){
        clientGUIList.remove(clientGUI);
        if (clientGUI != null){
            client.disconnectFromServer();
        }
    }

    private String readLog(){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getLog() {
        return readLog();
    }

    private void appendLog(String text){
        log.append(text + "\n");
    }
}
