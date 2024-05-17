import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        String hostname;
        int port;
        if(args.length < 2){
            System.out.println("Usage: java TCPClient <port> <hostname_or_address>");
        }
        try{
            port = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){
            System.out.println("Please provide a viable portnumber");
            return;
        }
        hostname = args[1];
            InetAddress ipAdress = InetAddress.getByName(hostname);
        try (DatagramSocket out = new DatagramSocket()) {
            for (int i = 0; i <= 1000000; i++) {
                DatagramPacket send = new DatagramPacket(
                        ByteBuffer.allocate(4).putInt(i).array(), 4, ipAdress, port);
                out.send(send);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
