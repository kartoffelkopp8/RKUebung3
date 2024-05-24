import java.io.DataOutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java TCPClient <hostname_or_address> <port>");
            return;
        }

        // Parse arguments
        String hostname;
        int port;
        hostname = args[0];
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid integer as a portnumber");
            return;
        }

        // Send data
        try (Socket socket = new Socket(hostname, port)) {
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
            for (int i = 0; i <= 1000000; i++) { // +1, um den Verbindungsabbrich vom Server mitzubekommen
                dataOut.writeInt(i);
                dataOut.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
