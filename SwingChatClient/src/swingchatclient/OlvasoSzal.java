/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingchatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Gabika
 */
public class OlvasoSzal extends Thread {

    private BufferedReader br;
    private JTextArea taUzenetek;

    public OlvasoSzal(BufferedReader br, JTextArea taUzenetek) {
        this.br = br;
        this.taUzenetek = taUzenetek;

        setDaemon(true);
    }

    @Override
    public void run() {
        String sor = "";
        while (true) {

            try {
                sor = br.readLine();
                taUzenetek.append(sor + "\n");

            } catch (IOException ex) {
                taUzenetek.append(ex.toString() + "\n");
            }
        }
    }

}
