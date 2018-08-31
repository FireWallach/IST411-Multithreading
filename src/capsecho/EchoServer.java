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

/**
 *
 * @author dylanwallach
 */
public class EchoServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6000)) {
            System.out.println("Waiting for connection.....");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected to client");
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(
                            clientSocket.getOutputStream(), true)) {
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    inputLine = inputLine.toUpperCase();
                    System.out.println("Server: " + inputLine);
                    out.println(inputLine);
                }
            }
        } catch (IOException ex) {}
    }
}
