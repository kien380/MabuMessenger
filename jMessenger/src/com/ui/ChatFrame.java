package com.ui;

import com.socket.History;
import com.socket.Message;
import com.socket.SocketClient;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import oracle.jrockit.jfr.JFR;

public class ChatFrame extends javax.swing.JFrame {

    public SocketClient client;
    public int port;
    public String serverAddr, username, password;
    public Thread clientThread;
    public DefaultListModel model;
    public File file;
    public HistoryFrame historyFrame;
    public History hist;
    public LoginFrame loginUI;
    
    public ChatFrame() {
        initComponents();
        this.setSize(600, 470);
        this.setResizable(false);
        
        this.setTitle("Chat box");
        this.ChatPanel.setLayout(new BoxLayout(ChatPanel, BoxLayout.Y_AXIS));
        this.ChatPanel.setSize(407, 264);
        this.panelStickers.setVisible(false);
        
        model.addElement("All");
        jList1.setSelectedIndex(0);
        
        jTextField6.setEditable(false);
        
        this.addWindowListener(new WindowListener() {

            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) { try{ client.send(new Message("message", username, ".bye", "SERVER")); clientThread.stop();  }catch(Exception ex){} }
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        
        
        new LoginFrame(ChatFrame.this).setVisible(true);
        this.setVisible(false);
    }
    
    public boolean isWin32(){
        return System.getProperty("os.name").startsWith("Windows");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelStickers = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelStickerList = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        buttonAttachments = new javax.swing.JButton();
        buttonHistory = new javax.swing.JButton();
        buttonSticker = new javax.swing.JButton();
        buttonYahoo = new javax.swing.JButton();
        buttonKnock = new javax.swing.JButton();
        buttonBuzz = new javax.swing.JButton();
        buttonSmile = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ScrollChatPanel = new javax.swing.JScrollPane();
        ChatPanel = new javax.swing.JPanel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        panelStickers.setBackground(new java.awt.Color(153, 0, 153));
        panelStickers.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                panelStickersFocusLost(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBar(null);

        panelStickerList.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/resource/drawable/icon/smile.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelStickerListLayout = new javax.swing.GroupLayout(panelStickerList);
        panelStickerList.setLayout(panelStickerListLayout);
        panelStickerListLayout.setHorizontalGroup(
            panelStickerListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStickerListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(271, Short.MAX_VALUE))
        );
        panelStickerListLayout.setVerticalGroup(
            panelStickerListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStickerListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(panelStickerList);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Stickers");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/resource/drawable/icon/close.png"))); // NOI18N
        jLabel3.setAlignmentX(0.5F);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelStickersLayout = new javax.swing.GroupLayout(panelStickers);
        panelStickers.setLayout(panelStickersLayout);
        panelStickersLayout.setHorizontalGroup(
            panelStickersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStickersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelStickersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelStickersLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelStickersLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))))
        );
        panelStickersLayout.setVerticalGroup(
            panelStickersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelStickersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelStickersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelStickersLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panelStickers);
        panelStickers.setBounds(77, 110, 270, 190);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(10, 45, 579, 10);

        jList1.setModel((model = new DefaultListModel()));
        jScrollPane2.setViewportView(jList1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(427, 66, 138, 264);

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField4);
        jTextField4.setBounds(10, 341, 358, 41);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/resource/drawable/icon/send.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(378, 341, 40, 40);

        jLabel7.setText("History File :");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(24, 15, 60, 14);
        getContentPane().add(jTextField6);
        jTextField6.setBounds(88, 12, 338, 20);

        jButton7.setText("...");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(432, 11, 70, 23);

        jButton8.setText("Show");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8);
        jButton8.setBounds(508, 11, 81, 23);

        buttonAttachments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/resource/drawable/icon/attachments.png"))); // NOI18N
        buttonAttachments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAttachmentsActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAttachments);
        buttonAttachments.setBounds(10, 389, 46, 40);

        buttonHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/resource/drawable/icon/history.png"))); // NOI18N
        buttonHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHistoryActionPerformed(evt);
            }
        });
        getContentPane().add(buttonHistory);
        buttonHistory.setBounds(62, 389, 46, 40);

        buttonSticker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/resource/drawable/icon/sticker.png"))); // NOI18N
        buttonSticker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStickerActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSticker);
        buttonSticker.setBounds(114, 389, 46, 40);

        buttonYahoo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/resource/drawable/icon/yahoo.png"))); // NOI18N
        buttonYahoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonYahooActionPerformed(evt);
            }
        });
        getContentPane().add(buttonYahoo);
        buttonYahoo.setBounds(166, 389, 46, 40);

        buttonKnock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/resource/drawable/icon/knock.png"))); // NOI18N
        buttonKnock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKnockActionPerformed(evt);
            }
        });
        getContentPane().add(buttonKnock);
        buttonKnock.setBounds(218, 389, 46, 40);

        buttonBuzz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/resource/drawable/icon/buzz.png"))); // NOI18N
        buttonBuzz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuzzActionPerformed(evt);
            }
        });
        getContentPane().add(buttonBuzz);
        buttonBuzz.setBounds(270, 389, 46, 40);

        buttonSmile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/resource/drawable/icon/smile.png"))); // NOI18N
        buttonSmile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSmileActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSmile);
        buttonSmile.setBounds(322, 389, 46, 40);

        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("ISE Entertainment");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(478, 389, 87, 14);

        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("© Mabu Messenger!");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(467, 409, 98, 14);

        javax.swing.GroupLayout ChatPanelLayout = new javax.swing.GroupLayout(ChatPanel);
        ChatPanel.setLayout(ChatPanelLayout);
        ChatPanelLayout.setHorizontalGroup(
            ChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );
        ChatPanelLayout.setVerticalGroup(
            ChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        ScrollChatPanel.setViewportView(ChatPanel);

        getContentPane().add(ScrollChatPanel);
        ScrollChatPanel.setBounds(10, 66, 407, 264);

        background.setBackground(new java.awt.Color(153, 0, 153));
        background.setOpaque(true);
        getContentPane().add(background);
        background.setBounds(0, 0, 600, 440);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        sendMessage();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        JFileChooser jf = new JFileChooser();
        jf.showDialog(this, "Select File");
        String historyFile = "";
        if(!jf.getSelectedFile().getPath().isEmpty()){
            historyFile = jf.getSelectedFile().getPath();
            if(this.isWin32()){
                historyFile = historyFile.replace("/", "\\");
            }
            jTextField6.setText(historyFile);
            jTextField6.setEditable(false);
            jButton7.setEnabled(false);
            jButton8.setEnabled(true);
            hist = new History(historyFile);
                    
            historyFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            historyFrame.setVisible(false);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        File historyFile = new File("src/com/resource/data/History.xml");
        String historyPath = "";
        try {
            historyPath = historyFile.getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
            historyPath = historyPath.replace("\\", "/");
//        if(this.isWin32()) {
//            historyPath = historyPath.replace("/", "\\");
//        }
        hist = new History(historyPath);
        historyFrame = new HistoryFrame(hist);
        historyFrame.setLocation(this.getLocation());
        historyFrame.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void buttonAttachmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAttachmentsActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showDialog(this, "Select File");
        file = fileChooser.getSelectedFile();
        
        if(file != null){
            if(!file.getName().isEmpty()){
                sendFile();
            }
        }
    }//GEN-LAST:event_buttonAttachmentsActionPerformed

    private void buttonHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHistoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonHistoryActionPerformed

    private void buttonStickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStickerActionPerformed
        panelStickers.setVisible(true);
    }//GEN-LAST:event_buttonStickerActionPerformed

    private void buttonYahooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonYahooActionPerformed
        String target = jList1.getSelectedValue().toString();
        playSound("yahoo");
        client.send(new Message("sound", username, "yahoo", target));
        client.send(new Message("message", username, "Sticker::yahoo.png", target));
    }//GEN-LAST:event_buttonYahooActionPerformed

    private void buttonKnockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKnockActionPerformed
        String target = jList1.getSelectedValue().toString();
        playSound("knock");
        client.send(new Message("sound", username, "knock", target));
        client.send(new Message("message", username, "Sticker::knock.png", target));
    }//GEN-LAST:event_buttonKnockActionPerformed

    private void buttonBuzzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuzzActionPerformed
        String target = jList1.getSelectedValue().toString();
        playSound("buzz");
        client.send(new Message("sound", username, "buzz", target));
        client.send(new Message("message", username, "Sticker::buzz.png", target));
    }//GEN-LAST:event_buttonBuzzActionPerformed

    private void buttonSmileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSmileActionPerformed
        String target = jList1.getSelectedValue().toString();
        client.send(new Message("message", username, "Sticker::smile.png", target));
    }//GEN-LAST:event_buttonSmileActionPerformed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void panelStickersFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelStickersFocusLost
        panelStickers.setVisible(false);
    }//GEN-LAST:event_panelStickersFocusLost

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        panelStickers.setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        panelStickers.setVisible(false);
        String msg = jTextField4.getText();
        String target = jList1.getSelectedValue().toString();
        client.send(new Message("message", username, "Sticker::smile.png", target));
    }//GEN-LAST:event_jLabel1MouseClicked

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch(Exception ex){
            System.out.println("Look & Feel exception");
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatFrame().setVisible(false);
            }
        });
    }
    
    private void sendFile()
    {
        long size = file.length();
        if(size < 120 * 1024 * 1024){
            client.send(new Message("upload_req", username, file.getName(), jList1.getSelectedValue().toString()));
        }
        else{
            System.out.println("[Application > Me] : File is size too large\n");
        }
    }
    
    public void login(String username, String password, String address, String port) {
        // Connect to server
        this.serverAddr = address; 
        this.port = Integer.parseInt(port);
        
        try{
            client = new SocketClient(this, loginUI);
            clientThread = new Thread(client);
            clientThread.start();
            client.send(new Message("test", "testUser", "testContent", "SERVER"));
            
            // If connect successed
            // Then Login
            this.username = username;
            this.password = password;
            
            client.send(new Message("login", username, password, "SERVER"));
            this.setTitle("Chat Box - " + username);
        }
        catch(Exception ex){
            System.out.println("[Application > Me] : Server not found\n");            
            // Back to login frame
            this.setVisible(false);
            loginUI.setVisible(true);
        }
    }
    
    private void sendMessage() {
        String msg = jTextField4.getText();
        String target = jList1.getSelectedValue().toString();
        
        if(!msg.isEmpty() && !target.isEmpty()){
            jTextField4.setText("");
            client.send(new Message("message", username, msg, target));
        }
    }
    
    private void playSound(String audioFile)
    {
        try {
            // Open an audio input stream.
            File soundFile = new File("src/com/resource/sound/" + audioFile + ".wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel ChatPanel;
    public javax.swing.JScrollPane ScrollChatPanel;
    private javax.swing.JLabel background;
    private javax.swing.JButton buttonAttachments;
    private javax.swing.JButton buttonBuzz;
    private javax.swing.JButton buttonHistory;
    private javax.swing.JButton buttonKnock;
    private javax.swing.JButton buttonSmile;
    private javax.swing.JButton buttonSticker;
    private javax.swing.JButton buttonYahoo;
    public javax.swing.JButton jButton4;
    public javax.swing.JButton jButton7;
    public javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTextField jTextField4;
    public javax.swing.JTextField jTextField6;
    private javax.swing.JPanel panelStickerList;
    private javax.swing.JPanel panelStickers;
    // End of variables declaration//GEN-END:variables
}
