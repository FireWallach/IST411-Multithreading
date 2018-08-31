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
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author dylanwallach
 */
public class EchoClient {

    public static void main(String[] args) {
        try {
            System.out.println("Waiting for connection.....");
            InetAddress localAddress = InetAddress.getLocalHost();

            try (Socket clientSocket = new Socket(localAddress, 6000);
                    PrintWriter out = new PrintWriter(
                            clientSocket.getOutputStream(), true);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                                    clientSocket.getInputStream()))) {
                System.out.println("Connected to server");
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.print("Enter text: ");
                    String inputLine = scanner.nextLine();
                    if ("quit".equalsIgnoreCase(inputLine)) {
                        break;
                    }
                    out.println(inputLine);
                    String response = br.readLine();
                    System.out.println("Server response: " + response);
                }
            }
        } catch (IOException ex) {
            // Handle exceptions
        }
    }

}
