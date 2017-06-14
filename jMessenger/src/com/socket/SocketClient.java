package com.socket;

import com.ui.ChatFrame;
import com.ui.LoginFrame;
import com.ui.MessageBox;
import java.io.*;
import java.net.*;
import java.util.Date;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import java.io.PrintWriter;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;

public class SocketClient implements Runnable{
    
    public int port;
    public String serverAddr;
    public Socket socket;
    public LoginFrame loginUI;
    public ChatFrame chatUI;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    public History hist;
    
    public SocketClient(ChatFrame chat, LoginFrame login) throws IOException{
        chatUI = chat; loginUI = login;
        this.serverAddr = chatUI.serverAddr; this.port = chatUI.port;
        socket = new Socket(InetAddress.getByName(serverAddr), port);
            
        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
        
        hist = chatUI.hist;
    }

    @Override
    public void run() {
        boolean keepRunning = true;
        while(keepRunning){
            try {
                Message msg = (Message) In.readObject();
                System.out.println("Incoming : "+msg.toString());
                
                if(msg.type.equals("message")){
                    chatUI.ChatPanel.add(new MessageBox(msg.sender, msg.recipient, msg.content, chatUI.username));
                    JScrollBar scroll = chatUI.ScrollChatPanel.getVerticalScrollBar();
                    int maximum = scroll.getMaximum();
                    scroll.setValue(maximum);
                                            
                    if(!msg.content.equals(".bye") && !msg.sender.equals(chatUI.username)){
                        String msgTime = (new Date()).toString();
                        
                        try{
                            chatUI.hist.addMessage(msg, msgTime);
                            DefaultTableModel table = (DefaultTableModel) chatUI.historyFrame.jTable1.getModel();
                            table.addRow(new Object[]{msg.sender, msg.content, msg.recipient, msgTime});
                        }
                        catch(Exception ex){
                            System.out.println("Write to history error");
                        }  
                    }
                }
                else if(msg.type.equals("login")){
                    if(msg.content.equals("TRUE")){                        
                        chatUI.jButton4.setEnabled(true); 
                        System.out.println("[SERVER > Me] : Login Successful\n");
                    }
                    else{
                        System.out.println("[SERVER > Me] : Login Failed\n");
                        // Back to login frame
                        loginUI.setVisible(true);
                        chatUI.setVisible(false);
                        JOptionPane.showMessageDialog(loginUI, 
                                                      "Username or password is Incorrect",
                                                      "Login failed", 
                                                      JOptionPane.WARNING_MESSAGE);
                    }
                }
                else if(msg.type.equals("test")){
                    
                }
                else if(msg.type.equals("newuser")){
                    if(!msg.content.equals(chatUI.username)){
                        boolean exists = false;
                        for(int i = 0; i < chatUI.model.getSize(); i++){
                            if(chatUI.model.getElementAt(i).equals(msg.content)){
                                exists = true; break;
                            }
                        }
                        if(!exists){ chatUI.model.addElement(msg.content); }
                    }
                }
                else if(msg.type.equals("signup")){
                    if(msg.content.equals("TRUE")){
                        chatUI.jButton4.setEnabled(true);
                        System.out.println("[SERVER > Me] : Singup Successful\n");
                    }
                    else{
                        System.out.println("[SERVER > Me] : Signup Failed\n");
                    }
                }
                else if(msg.type.equals("signout")){
                    if(msg.content.equals(chatUI.username)){
                        System.out.println("["+ msg.sender +" > Me] : Bye\n");
                        chatUI.jButton4.setEnabled(false); 
                        
                        for(int i = 1; i < chatUI.model.size(); i++){
                            chatUI.model.removeElementAt(i);
                        }
                        
                        chatUI.clientThread.stop();
                    }
                    else{
                        chatUI.model.removeElement(msg.content);
                        System.out.println("["+ msg.sender +" > All] : "+ msg.content +" has signed out\n");
                    }
                }
                else if(msg.type.equals("upload_req")){
                    
                    if(JOptionPane.showConfirmDialog(chatUI, ("Accept '"+msg.content+"' from "+msg.sender+" ?")) == 0){
                        
                        JFileChooser jf = new JFileChooser();
                        jf.setSelectedFile(new File(msg.content));
                        int returnVal = jf.showSaveDialog(chatUI);
                       
                        String saveTo = jf.getSelectedFile().getPath();
                        if(saveTo != null && returnVal == JFileChooser.APPROVE_OPTION){
                            Download dwn = new Download(saveTo, chatUI);
                            Thread t = new Thread(dwn);
                            t.start();
                            //send(new Message("upload_res", (""+InetAddress.getLocalHost().getHostAddress()), (""+dwn.port), msg.sender));
                            send(new Message("upload_res", chatUI.username, (""+dwn.port), msg.sender));
                        }
                        else{
                            send(new Message("upload_res", chatUI.username, "NO", msg.sender));
                        }
                    }
                    else{
                        send(new Message("upload_res", chatUI.username, "NO", msg.sender));
                    }
                }
                else if(msg.type.equals("upload_res")){
                    if(!msg.content.equals("NO")){
                        int port  = Integer.parseInt(msg.content);
                        String addr = msg.sender;
                        
                        
                        Upload upl = new Upload(addr, port, chatUI.file, chatUI);
                        Thread t = new Thread(upl);
                        t.start();
                    }
                    else{
                        System.out.println("[SERVER > Me] : "+msg.sender+" rejected file request\n");
                    }
                }
                else if(msg.type.equals("sound")) {                    
                    try { 
                        if(msg.recipient.equals(chatUI.username) || msg.recipient.equals("All"))
                        {
                            // Open an audio input stream.           
                            File soundFile = new File("src/com/resource/sound/" + msg.content + ".wav");
                            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
                            // Get a sound clip resource.
                            Clip clip = AudioSystem.getClip();
                            // Open audio clip and load samples from the audio input stream.
                            clip.open(audioIn);
                            clip.start();
                        }
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    
                }
                else if(msg.type.equals("sticker")) {
                    chatUI.ChatPanel.add(new MessageBox(msg.sender, msg.recipient, msg.content, chatUI.username));
                    JScrollBar scroll = chatUI.ScrollChatPanel.getVerticalScrollBar();
                    int maximum = scroll.getMaximum();
                    scroll.setValue(maximum);
                }
                else{
                    System.out.println("[SERVER > Me] : Unknown message type\n");
                }
            }
            catch(Exception ex) {
                keepRunning = false;
                System.out.println("[Application > Me] : Connection Failure\n");
                chatUI.jButton4.setEnabled(false);
                
                for(int i = 1; i < chatUI.model.size(); i++){
                    chatUI.model.removeElementAt(i);
                }
                
                chatUI.clientThread.stop();
                
                System.out.println("Exception SocketClient run()");
                ex.printStackTrace();
            }
        }
    }
    
    public void send(Message msg){
        try {
            Out.writeObject(msg);
            Out.flush();
            System.out.println("Outgoing : "+msg.toString());
            
            if(msg.type.equals("message") && !msg.content.equals(".bye")){
                String msgTime = (new Date()).toString();
                try{
                    hist.addMessage(msg, msgTime);               
                    DefaultTableModel table = (DefaultTableModel) chatUI.historyFrame.jTable1.getModel();
                    table.addRow(new Object[]{"Me", msg.content, msg.recipient, msgTime});
                }
                catch(Exception ex){}
            }
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }
    
    public void closeThread(Thread t){
        t = null;
    }
}
