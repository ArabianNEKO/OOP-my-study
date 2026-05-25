package gameproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    private String serverIp;
    private int serverPort;
    private PongGame pongGame;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String ip, int port, PongGame pongGame) {
        this.serverIp = ip;
        this.serverPort = port;
        this.pongGame = pongGame;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(serverIp, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String gameState;
            while ((gameState = in.readLine()) != null) {
                pongGame.updateGameState(gameState);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendGameAction(String action) {
        if (out != null) {
            out.println(action);
        }
    }
}