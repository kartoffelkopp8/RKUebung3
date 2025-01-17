import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class UDPClient {
    public static void main(String[] args) throws UnknownHostException {
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
        InetAddress address = InetAddress.getByName(hostname);
        try (DatagramSocket socket = new DatagramSocket()) {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            for (int i = 0; i <= 1000000; i++) {
                buffer.putInt(0, i);
                DatagramPacket packet = new DatagramPacket(
                    buffer.array(), 4,
                    address, port
                );
                socket.send(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
