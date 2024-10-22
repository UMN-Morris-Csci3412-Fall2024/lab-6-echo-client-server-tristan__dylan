package echoserver;

import java.net.*;
import java.nio.Buffer;
import java.io.*;

public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        String server;
        // Use "127.0.0.1", i.e., localhost, if no server is specified.
        if (args.length == 0) {
            server = "127.0.0.1";
        } else {
            server = args[0];
        }

        try {
            // connect to server
            Socket socket = new Socket(server, portNumber);

            // Get the input stream so we can read from that socket
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Print all input received from server
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            socket.close();

            // provide some minimal error handling
        } catch (ConnectException ce) {
            System.out.println("We were unable to connect to " + server);
            System.out.println("You should make sure the server is running.");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}

// [kalvo007@moonbus src]$ javac echoserver/EchoClient.java 
// [kalvo007@moonbus src]$ java echoserver.EchoClient 
// Tue Oct 22 11:36:27 CDT 2024