package lab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Henning
 * @author Amanda
 */
public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientList = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;

    public ClientHandler(Socket socket){
        try{
            this.socket= socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName = bufferedReader.readLine();
            clientList.add(this);
        }catch(IOException e){
            System.err.println(e);
        }
    }

    public String getUserName() {
        return this.userName;
    }

    @Override
    public void run(){
        String msg;
        while(socket.isConnected()){
            try{
                msg = bufferedReader.readLine();
                sendMessageToAll(msg);
                System.out.println(msg);
            }catch(IOException e){
                System.out.println("A user has left");
                clientList.remove(this);
                try{
                    socket.close();
                    bufferedReader.close();
                    bufferedWriter.close();
                    break;
                }catch(Exception er){
                    System.err.println(er);
                }
                System.err.println(e);
            }
        }
    }

    public void sendMessageToAll(String msg){
        for(ClientHandler currentClient : clientList){
            try{
                System.out.println("for");
                if(currentClient.socket!=this.socket){
                    System.out.println("if");
                    currentClient.bufferedWriter.write(msg);
                    currentClient.bufferedWriter.newLine();
                    currentClient.bufferedWriter.flush();
                }
            }catch(IOException e){
                System.err.println(e);
            }
        }
    }
}