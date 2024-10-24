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

            // get input from the user and set up output
            InputStream userInput= System.in;
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

        //     byte[] buffer = new byte[1024];
        //     int bytesRead;
            
        //     while ((bytesRead = userInput.read(buffer)) != -1) {
        //      //send user input to the sever
        //      out.write(buffer, 0, bytesRead);
        //      out.flush();

        //      bytesRead = in.read(buffer);
        //      System.out.write(buffer, 0, bytesRead);
        // }

                int data; 
                while((data = userInput.read()) != -1) {
                    // send user input to the server
                    out.write(data);
                    out.flush();

                    // Read and print the server response
                    int serverResponse = in.read();
                    System.out.write(serverResponse);
                    System.out.flush();
                }

            // provide some minimal error handling
            socket.close();
        } catch (ConnectException ce) {
            System.out.println("We were unable to connect to " + server);
            System.out.println("You should make sure the server is running.");
        } catch (SocketException se) {
            System.out.println("Connection was reset. Please check the server status.");
            System.err.println(se);
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}

// [kalvo007@moonbus src]$ javac echoserver/EchoClient.java 
// [kalvo007@moonbus src]$ java echoserver.EchoClient 
// Tue Oct 22 11:36:27 CDT 2024