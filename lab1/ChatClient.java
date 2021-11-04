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

    public ChatClient(Socket socket){
        try{
            this.socket = socket;
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
        }catch(Exception e){
            System.out.println("Lost connection to server");
            try{
                socket.close();
            }catch(Exception ex){System.out.println(ex);}
            System.exit(1);
        }
    }

    @Override
    public void run(){
        String msg;
        System.out.println("run");
        while(socket.isConnected()){
            System.out.println("while");
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
            Socket socket = new Socket("localhost", 12341);
            ChatClient client = new ChatClient(socket);

            Thread thread = new Thread(client);
            client.messageSender();
            thread.start();
        }catch (IOException e){
            System.err.println(e);
        }
    }
}