package echoserver;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static final int portNumber = 6013;
    
    public static void main(String[] args) {
        try {
            // Start listening on portNumber
            ServerSocket sock = new ServerSocket(portNumber);

            while (true) {
                // Wait until someone accepts
                Socket client = sock.accept();
                System.out.println("Got request!");

                // Read a single byte from the client
                //Read(sock.getInp)
                InputStream input = client.getInputStream();
                OutputStream output = client.getOutputStream();
                
                //int data = input.read();
                // System.out.println("Received byte:" + data);

                int data;
                
                while ((data = input.read()) != -1) {
                    System.out.println("Received byte:" + data);
                    output.write(data);
                }


                // Construct a writer so we can write to the socket, thereby
                // sending something back to the client.
                // PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

                // Close socket when done
                client.close();
            }
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}

// [kalvo007@moonbus echoserver]$ javac -d out EchoServer.java
// [kalvo007@moonbus echoserver]$ java -cp out echoserver.EchoServer
// Got request!