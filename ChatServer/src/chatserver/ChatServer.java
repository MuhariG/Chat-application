/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabika
 */
public class ChatServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vector<ClientConnection> clients = new Vector<>();

        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("Várom a kliensek kapcsolódását...");

            while (true) {

                Socket s = ss.accept();

                //ezen olvasunk a klienstől
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String kliensNev = br.readLine();

                //ezen lehet küldeni
                PrintWriter pw = new PrintWriter(s.getOutputStream());

                ClientConnection cc = new ClientConnection(kliensNev, br, pw);
                clients.add(cc);

                ReaderThread rt = new ReaderThread(cc, clients);
                rt.start();

                System.out.println(kliensNev + " kapcsolódott");
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
