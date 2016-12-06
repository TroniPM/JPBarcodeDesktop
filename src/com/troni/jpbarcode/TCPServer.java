package com.troni.jpbarcode;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {

    public static void main(String args[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(1613);
        Danfe nfe = new Danfe();

        System.out.println("SERVER: ");

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();

            System.out.println("SERVER: ");
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            //capitalizedSentence = clientSentence.toUpperCase() + '\n';
            nfe = Danfe.convertFromString(clientSentence);
            outToClient.writeBytes(nfe.convertFromString(clientSentence).toString() + "\n");
            //System.out.println(nfe.toString());

            Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection testData = new StringSelection(nfe.getNumeracao());
            c.setContents(testData, testData);
        }
    }
}
