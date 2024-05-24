import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        final int port = 2345;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listening on port " + port + "...");

        while (true) {
            try (Socket socket = serverSocket.accept()) {
                DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                System.out.println("Connection accepted");

                // Read and check number sequence
                int expected = 0;
                while (true) {
                    int received;
                    try {
                        received = dataIn.readInt();
                    } catch (IOException e) {
                        System.out.println("Connection closed unexpectedly");
                        break;
                    }

                    if (received == expected) {
                        expected++;
                        //System.out.println(received);
                    } else {
                        System.out.println("Expected: " + expected + ", Received: " + received);
                        expected = received + 1;
                    }

                    if (received == 1000000) {
                        socket.close();
                        System.out.println("Connection closed by server");
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        serverSocket.close();
    }
}
