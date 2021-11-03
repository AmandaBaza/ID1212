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
                System.out.println("Ny användare har anslutit.");
                ClientHandler client = new ClientHandler(socket);
                Thread thread = new Thread(client);
                thread.start();

            }
        }catch(IOException e){
            System.err.println(e);
        }
    }


    public static void main(String args[]) throws IOException{
        ServerSocket serverSocket = new ServerSocket(8080);
        ChatServer server = new ChatServer(serverSocket);
        server.startServer();
    }
}