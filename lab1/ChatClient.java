package lab1;

/**
 *
 * @author Henning
 * @author Amanda
 */
public class ChatClient {
    private Socket socket;
    private BufferedReader read;
    private BufferedWriter write;
    private String username;

    public ChatClient(Socket socket, String username){
        try{
            this.socket = socket;
            this.username = username;
            this.read = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            this.write = new BufferedWriter(new InputStreamWriter(socket.getOutputStream()))
        }catch (IOExeption e){
            System.err.println(e);
        }

        public void messageSender(){
            try{
                Scanner scanner = new Scanner(System.in);
                while(this.socket.isConnected){
                    String message = scanner.getLine();
                    write.write(message);
                    write.newLine();
                    write.flush();
                }
            }catch(IOException e){
                System.err.println(e);
            }
        }
        public void messageListener() implements Runnable(){
            @Override
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

    }
    main(){
        //thread listen
        //thread write
    }
}
