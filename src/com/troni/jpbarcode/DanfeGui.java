/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.troni.jpbarcode;

import static com.troni.jpbarcode.JFramePrincipal.jPanel1;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PMateus
 */
public class DanfeGui {

    private JTextField jTextField;
    private JButton btn;
    private JLabel label, hora;

    private int[] y = null;
    private final Danfe nfe;

    public DanfeGui(Danfe nfe, int[] y) {
        this.y = y;
        this.nfe = nfe;

        jTextField = new JTextField();
        btn = new JButton();
        label = new JLabel();
        hora = new JLabel();

        jTextField.setSize(354, 21);
        jTextField.setEditable(false);
        jTextField.setFont(new java.awt.Font("Tahoma", 0, 12));
        jTextField.setText(nfe.getNumeroDanfeFormatted());

        label.setText("Danfe:");
        hora.setText(nfe.getData());

        btn.setText("Copiar");
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFramePrincipal.sendClipboard(nfe.getNumeracao());
            }
        });

    }

    public void goDown() {
        y[0] += JFramePrincipal.offset;
        y[1] += JFramePrincipal.offset;
        y[2] += JFramePrincipal.offset;
        y[3] += JFramePrincipal.offset;

        plot();
    }

    public void plot() {
        
         JFramePrincipal.jPanel1.add(label,
         new org.netbeans.lib.awtextra.AbsoluteConstraints(
         JFramePrincipal.danfeLabelX, y[0], -1, -1));
         JFramePrincipal.jPanel1.add(jTextField,
         new org.netbeans.lib.awtextra.AbsoluteConstraints(
         JFramePrincipal.jTextX, y[1], 367, -1));
         JFramePrincipal.jPanel1.add(hora,
         new org.netbeans.lib.awtextra.AbsoluteConstraints(
         JFramePrincipal.labelHoraX, y[2], -1, -1));
         JFramePrincipal.jPanel1.add(btn,
         new org.netbeans.lib.awtextra.AbsoluteConstraints(
         JFramePrincipal.btnX, y[3], -1, -1));
         
    }

    public void print() {
        System.out.println("-> " + nfe.getNumeracao() + " " + y[0] + " " + y[1] + " " + y[2] + " " + y[3]);
    }
}
