/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capsecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dylanwallach
 */
public class ConnectedUser implements Runnable {

    private BufferedReader br;
    private PrintWriter out;
    private Socket clientSocket;

    public ConnectedUser(Socket clientSocket, BufferedReader br, PrintWriter out) {
        this.clientSocket = clientSocket;
        this.br = br;
        this.out = out;
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                inputLine = inputLine.toUpperCase();
                System.out.println("Server: " + inputLine);
                out.println(inputLine);
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectedUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
