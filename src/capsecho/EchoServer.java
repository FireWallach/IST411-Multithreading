/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capsecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dylanwallach
 */
public class EchoServer {

    public static void main(String[] args) {
        Socket clientSocket;
        try (ServerSocket serverSocket = new ServerSocket(6000)) {
            System.out.println("Waiting for connection.....");
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Connected to client");
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                Thread thread = new Thread(new ConnectedUser(clientSocket, br,out));
                thread.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
