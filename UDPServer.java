package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        final int port = 2345;
        try(DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("Server listening on port: " + port + "...");

            while (true) {
                byte[] data = new byte[8];
                DatagramPacket rcvPacket = new DatagramPacket(data, data.length);
                serverSocket.receive(rcvPacket);
                int number = ByteBuffer.wrap(rcvPacket.getData()).getInt();
                System.out.println(number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
