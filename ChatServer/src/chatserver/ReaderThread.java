/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabika
 */
public class ReaderThread extends Thread {

    ClientConnection cc;  //akitől olvas
    Vector<ClientConnection> clients;  //mindenki más akinek szétküldi

    public ReaderThread(ClientConnection cc, Vector<ClientConnection> clients) {
        this.cc = cc;
        this.clients = clients;
    }

    @Override
    public void run() {
        String message = "";

        while (true) {
            try {
                message = cc.getBr().readLine();
                String toSend = cc.getName()+message;
                
                System.out.println(toSend);
                
                for (ClientConnection c : clients) {
                    if(c!=cc) {
                        c.getPw().println(toSend);
                        c.getPw().flush();
                    }
                }
                
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

}
