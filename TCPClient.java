import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        int portNumber;
        String hostname;
        if(args.length < 2){
            System.out.println("Usage: java TCPClient <port> <hostname_or_address>");
        }
        try{
            portNumber = Integer.parseInt(args[0]);
        }catch (NumberFormatException e){
            System.out.println("Please provide a viable portnumber");
            return;
        }
        hostname = args[1];
        Socket clientSocket = new Socket(hostname, portNumber);
        DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
        for(int i = 0; i <= 1000000; i++){
            dataOut.writeInt(i);
            dataOut.flush();
        }
        clientSocket.close();
    }
}
