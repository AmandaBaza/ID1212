package lab2;
import java.net.*;
import java.io.*;
import java.util.StringTokenizer;

public class Server{

    public static void main(String []args) throws IOException{
        int port = 8080;
        ServerSocket server = new ServerSocket(port);

        while(true){
            Socket client = server.accept();

            BufferedReader buffread = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String read = buffread.readLine();

            StringTokenizer tokens = new StringTokenizer(read," ???????!!!!!!!!!!!????????????");
            //this or...
            OutputStream clientOutput =client.getOutputStream();
            clientOutput.write("HTTP/1.1 200\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("<b>Guessing Game<b>".getBytes());
            clientOutput.write(("<form>" +
                    "<label for=\"guess\">Guess a number:</label><br><input type=\"number\" id=\"guess\" name=\"guess\"><br>" +
                    "<form>").getBytes());
            clientOutput.flush();

            tokens.nextToken(); // The word GET
            String requestedDocument = tokens.nextToken();
            //this?
            while( (read = buffread.readLine()) != null && read.length() > 0){
                if(read.contains("/?guess=")){
                    String[] arrOfStr = read.split("/?guess=", 2);
                    String userGuess = arrOfStr[1];
                    System.out.println(userGuess);
                }
            }
            PrintStream response = new PrintStream(client.getOutputStream());
            response.println("HTTP/1.1 200 OK");
            response.println("Server: Trash 0.1 Beta");
            if(requestedDocument.indexOf(".html") != -1)
                response.println("Content-Type: text/html");
            if(requestedDocument.indexOf(".gif") != -1)
                response.println("Content-Type: image/gif");
            //TODO set Cookie
            response.println();

        }
    }
    /*void handleRequest(request){
        if(request == null){return;}
        if(request.httpMethod.equals(HTTP_GET_METHOD)){
            System.out.println("Test");
        }
    }*/
}