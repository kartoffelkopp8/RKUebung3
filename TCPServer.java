import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        final int portNumber = 2345;
        ServerSocket inSocket = new ServerSocket(portNumber);
        System.out.println("Server listening on port: " + portNumber + "...");

        while (true) {
            try {
                Socket acceptSocket = inSocket.accept();
                DataInputStream dataIn = new DataInputStream(acceptSocket.getInputStream());
                while (true) {
                    int expected = 0;
                    try {
                        int received = dataIn.readInt();
                        if(received == expected){
                            System.out.println(received);
                            expected++;
                        }else {
                            System.out.println("Missing Numbers: ");
                            for (int i = expected; i < received; i++){
                                System.out.println(i);
                            }
                        }
                    } catch (IOException b) {
                        System.out.println("Connection closed by Client");
                        acceptSocket.close();
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }
}
