package lab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.*;

/**
 *
 * @author Henning
 * @author Amanda
 */
public class ChatClient implements Runnable{
    private Socket socket;
    private BufferedReader read;
    private BufferedWriter write;
    private String username;

    public ChatClient(Socket socket, String username){
        try{
            this.socket = socket;
            this.username = username;
            this.read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            System.err.println(e);
        }
    }

    public void messageSender(){
        try{
            Scanner scanner = new Scanner(System.in);
            while(this.socket.isConnected()){
                String message = scanner.nextLine();
                write.write(message);
                write.newLine();
                write.flush();
            }
        }catch(IOException e){
            System.err.println(e);
        }
    }

    @Override
    public void run(){
        String msg;
        while(!socket.isConnected()){
            try{
                msg = read.readLine();
                System.out.println(msg);
            }catch(IOException e){
                System.err.println(e);
            }
        }
    }

    public static void main(String[] args){
        //TODO Om server kopplar bort/crashar, medelande till klient
        try{
            System.out.println("Användarnamn:");
            Scanner scanner = new Scanner(System.in);
            System.out.println("test 1");
            Socket socket = new Socket("local host", 8080);
            System.out.println("test 2");
            ChatClient client = new ChatClient(socket, scanner.nextLine());
            System.out.println("test 3");


            //TODO thread to listen
            //TODO thread to write
            client.messageSender();
        }catch (IOException e){
            System.err.println(e);
        }
    }
}