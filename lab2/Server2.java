import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server2 {

    ArrayList<GuessingGame> gameSesseions;  //Create an array of guessing Game Class
    private int cookieID;
    ServerSocket serverSocket;
    BufferedReader request;
    PrintWriter response;

    public Server2(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server has started...");
        gameSesseions = new ArrayList<>();
        cookieID = 1;
    }

    public void startServer() throws IOException{
        while (true) {
            System.out.println("Waiting for client to connect");
            Socket clientSocket = serverSocket.accept();

            request = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            response = new PrintWriter(clientSocket.getOutputStream());


            // Read and extract info from request
            String[] cookieHead;
            String[] value;
            int guess =0; //If guess is 0 = no guess was made.
            int cookieID = 0; // 0=no coockieID.
            int contentLength=0;

            StringBuilder sb = new StringBuilder();
            String lineToRead = request.readLine();

            while(!lineToRead.isEmpty()){
                System.out.println(lineToRead); //Debug

                if(lineToRead.contains("Cookie: ")){
                    cookieHead = lineToRead.split(" ");
                    value = cookieHead[1].split("=");
                    cookieID = Integer.parseInt(value[1]);
                }

                if(lineToRead.contains("Content-Length:")){
                    contentLength = Integer.parseInt(lineToRead.split(" ")[1]);
                }              
                
                lineToRead = request.readLine();
            }

            
            
           if(contentLength!=0){
                char[] charBody = new char[contentLength];
                request.read(charBody,0,contentLength);
                String stringBody = new String(charBody);
                guess = Integer.parseInt(stringBody.split("=")[1]);
                

            }

            
            int responseChoise;
            System.out.println("Cookie ID" + cookieID);
            System.out.println("content length: " +contentLength);
            System.out.println("Debug guess: "+guess);
            
            
            String htmlRespone;


            htmlRespone = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<title>My Guessing Game</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<text>I am thinking of a number between 1 - 100, take a guess:</text><br>\n" +
            "<text>Your last guess was "+guess+"</text>\n" +
            "<form action=\"http://localhost:8080/\" method=\"post\" >\n" +
            "<input type=\"text\" name=\"Make a guess\">\n" +
            "<input type=\"submit\" value=\"send\">\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";


            response.println("HTTP/1.1 200 OK");
            response.println("Content-Type: text/html");
            response.println("Content-Length: " + htmlRespone.length());
            response.println("\r\n");
            response.println(htmlRespone);
            response.flush();

            request.close();
            response.close();

            clientSocket.close();


        }
    }


    private int generateID() {
        return cookieID++;
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        Server2 server = new Server2(port);
        server.startServer();

    }
}
