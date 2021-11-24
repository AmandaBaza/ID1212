package lab2;
import java.io.*;
import java.net.*;
public class HttpClient{
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 8080);
        String file = "index.html";
        PrintStream outData = new PrintStream(socket.getOutputStream());
        outData.println("GET /" + file + " HTTP/1.1");
        socket.shutdownOutput();

        BufferedReader inData =
            new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = "";
        while( (str = inData.readLine()) != null){
            System.out.println(str);
        }
        socket.close();
    }
}