package com.troni.jpbarcode;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {

    public static String ipServer = "127.0.0.1";

    public static void main(String argv[]) {
        //System.out.println(InetAddress.getLocalHost().getHostAddress().toString());
        String sentence = null;
        String modifiedSentence = null;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = null;
        try {
            clientSocket = new Socket(ipServer, 1613);
        } catch (IOException ex) {
            System.out.println("Conexão fechada");
        }
        DataOutputStream outToServer = null;
        try {
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader inFromServer = null;
        try {
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.print("CLIENT: ");

        //sentence = inFromUser.readLine();
        //Danfe nfe = new Danfe("11112222333344445555666677778888999900001234", "192.168.0.106", "pclqu");
        Danfe nfe = new Danfe("11112222333344445555666677778888999900001234", "192.168.0.106", "139ac");

        sentence = nfe.convertToString();
        try {
            outToServer.writeBytes(sentence + "\n");
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            outToServer.flush();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //Para ler do servidor (resposta)
            modifiedSentence = inFromServer.readLine();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Para imprimir resposta no cliente
        System.out.println(modifiedSentence);

        try {
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
