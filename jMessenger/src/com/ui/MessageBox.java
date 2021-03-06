/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author USER
 */
public class MessageBox extends javax.swing.JPanel {

    /**
     * Creates new form MessageBox
     */
    public MessageBox(String sender, String recipient, String message, String username) {
        initComponents();
        
        init();
        
        
        if(sender.equals(username))
        {
            labelSender.setText("Me > " + recipient + " :");   
//            labelMessage.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
//            this.setAlignmentX(JPanel.RIGHT_ALIGNMENT);            
            panelMessageBox.setBackground(new Color(94,138,198));
        } else {   
            if(recipient.equals(username)) {                
                labelSender.setText(sender + " > Me :"); 
            } else {
                labelSender.setText(sender + " :"); 
            }
        }
        
        
        if(message.contains("Sticker::"))
        {
            String icon = message.substring(9);
            labelMessage.setText("");
            labelMessage.setIcon(new ImageIcon(getClass().getResource("/com/resource/drawable/sticker/" + icon)));
            panelMessageBox.setBackground(new Color(255,255,255,0));
        } else {      
            if(message.length() > 50) {
                // Auto enter if message too long
                String enterMessage = "";
                for(int i = 0; i < message.length(); i++)
                {
                    enterMessage += message.charAt(i);
                    if(i % 50 == 0 && i > 0) {
                        enterMessage += '\n';
                    }
                }
                labelMessage.setText(enterMessage);  
            } else {
                labelMessage.setText(message);  
            }
        }
    }

    private void init()
    {
        panelMessageBox.setBorder(new EmptyBorder(0,0,0,0));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelSender = new javax.swing.JLabel();
        panelMessageBox = new javax.swing.JPanel();
        labelMessage = new javax.swing.JLabel();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setCursor(new java.awt.Cursor(java.awt.Cursor.W_RESIZE_CURSOR));
        setOpaque(false);

        labelSender.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        labelSender.setForeground(new java.awt.Color(153, 153, 153));
        labelSender.setText("jLabel1");
        labelSender.setAlignmentY(0.0F);

        panelMessageBox.setBackground(new java.awt.Color(153, 0, 153));
        panelMessageBox.setForeground(new java.awt.Color(153, 0, 153));

        labelMessage.setForeground(new java.awt.Color(255, 255, 255));
        labelMessage.setText("jLabel2");

        javax.swing.GroupLayout panelMessageBoxLayout = new javax.swing.GroupLayout(panelMessageBox);
        panelMessageBox.setLayout(panelMessageBoxLayout);
        panelMessageBoxLayout.setHorizontalGroup(
            panelMessageBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMessageBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMessageBoxLayout.setVerticalGroup(
            panelMessageBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelSender)
            .addComponent(panelMessageBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(labelSender, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelMessageBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelMessage;
    private javax.swing.JLabel labelSender;
    private javax.swing.JPanel panelMessageBox;
    // End of variables declaration//GEN-END:variables
}
