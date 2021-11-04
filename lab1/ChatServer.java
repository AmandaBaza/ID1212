package lab1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Henning
 * @author Amanda
 */
public class ChatServer {
    private ServerSocket serverSocket;

    public ChatServer(ServerSocket serverSocket){
        this.serverSocket= serverSocket;
    }
    public void startServer(){
        try{
            while(true){
                Socket socket = serverSocket.accept();
                lab1.ClientHandler client = new lab1.ClientHandler(socket);
                Thread thread = new Thread(client);
                System.out.println("Användare " + client.getUserName() +" har anslutit.");
                thread.start();
            }
        }catch(IOException e){
            System.err.println(e);
        }
    }

    public static void main(String args[]) throws IOException{
        ServerSocket serverSocket = new ServerSocket(12341);
        ChatServer server = new ChatServer(serverSocket);
        System.out.println("Starting server...");
        server.startServer();
    }
}