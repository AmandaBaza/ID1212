/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author henni
 */
public class ClientHandler implements Runnable {

    public ArrayList<ClientHandler> clientList = new ArrayList<>();
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
    @Override
    public void run(){
        String msg;

        while(!socket.isConnected()){
            try{
                msg = bufferedReader.readLine();
                sendMessageToAll(msg);

            }catch(IOException e){
                System.err.println(e);
            }
        }

    }


    public void sendMessageToAll(String msg){
        for(ClientHandler currentClient : clientList){
            try{
                if(currentClient.socket!=this.socket){
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