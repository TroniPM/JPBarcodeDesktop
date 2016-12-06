/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.troni.jpbarcode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Morgan
 */
public class CustomThread extends Thread {

    public static ServerSocket welcomeSocket = null;

    private static boolean conexaoIsOpen = false;
    public static boolean error = false;

    public static boolean getConexaoIsOpen() {
        return conexaoIsOpen;
    }

    public void run() {
        abrirConexao();
    }

    public void fecharConexao() {
        try {
            welcomeSocket.close();
            conexaoIsOpen = false;
            System.out.println("SERVER: CLOSED");
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirConexao() {
        try {
            welcomeSocket = new ServerSocket(1613);
        } catch (IOException ex) {
            error = true;
            JOptionPane.showMessageDialog(JFramePrincipal.jFrame, "Aparentemente uma instancia do programa já está aberta.\tVerifique no systray (ícones ao lado do relógio) se não há uma instância aberta.\tEssa instância será encerrada.");
            System.exit(0);
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        String clientSentence;
        String capitalizedSentence;
        conexaoIsOpen = true;
        while (conexaoIsOpen) {
            Danfe nfe = new Danfe();
            System.out.println("SERVER: OPEN");
            try {
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                clientSentence = inFromClient.readLine();
                
                System.out.println("STRING RECEBIDA: "+clientSentence);

                nfe = Danfe.convertFromString(clientSentence);
                outToClient.writeBytes(nfe.convertFromString(clientSentence).toString() + "\n");

                //sending to jframe
                if (nfe.getNumeracao().equals(nfe.getKey())) {
                    // Para APP testar conexão sem aparecer erro
                } else if (nfe.getKey().equals(((JFramePrincipal) JFramePrincipal.jFrame).key)) {
                    ((JFramePrincipal) JFramePrincipal.jFrame).receiver(nfe);
                } else {
                    //JOptionPane.showMessageDialog(JFramePrincipal.jFrame, "Tentativa de conexão com Chave Incorreta.");
                }

            } catch (SocketException ex) {
                error = true;
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                error = true;
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
