import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class UDPServer {
    public static void main(String[] args) {
        final int port = 2345;
        System.out.println("Listening on port " + port + "...");

        while (true) {
            try (DatagramSocket socket = new DatagramSocket(port)) {
                // Read and check number sequence
                int expected = 0;
                byte[] data = new byte[4];
                DatagramPacket rcvPacket = new DatagramPacket(data, data.length);
                while (true) {
                    int received;
                    socket.receive(rcvPacket); // IOException here has no special meaning like with TCP, so it doesn't need to be handled seperately
                    received = ByteBuffer.wrap(rcvPacket.getData()).getInt();

                    if (Math.abs(expected - received) > received / 2) {
                        expected = received;
                        System.out.println("New sequence started at " + received);
                    }

                    if (received == expected) {
                        expected++;
                    } else {
                        System.out.println("Expected: " + expected + ", Received: " + received);
                        expected = received + 1;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
