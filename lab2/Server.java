import java.net.*;
import java.io.*;

public class Server{

    public static void main(String []args) throws IOException{
        int port = 8080;
        ServerSocket server = new ServerSocket(port);

        while(true){
            Socket client = server.accept();
            OutputStream clientOutput =client.getOutputStream();
            clientOutput.write("HTTP/1.1 200\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("<b>Guessing Game<b>".getBytes());
            clientOutput.flush();
        }
    }

}