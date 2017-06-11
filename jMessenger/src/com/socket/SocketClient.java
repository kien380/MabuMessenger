package com.socket;

import com.ui.ChatFrame;
import com.ui.LoginFrame;
import java.io.*;
import java.net.*;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
                    if(msg.recipient.equals(chatUI.username)){
                        chatUI.jTextArea1.append("["+msg.sender +" > Me] : " + msg.content + "\n");
                    }
                    else{
                        chatUI.jTextArea1.append("["+ msg.sender +" > "+ msg.recipient +"] : " + msg.content + "\n");
                    }
                                            
                    if(!msg.content.equals(".bye") && !msg.sender.equals(chatUI.username)){
                        String msgTime = (new Date()).toString();
                        
                        try{
                            hist.addMessage(msg, msgTime);
                            DefaultTableModel table = (DefaultTableModel) chatUI.historyFrame.jTable1.getModel();
                            table.addRow(new Object[]{msg.sender, msg.content, "Me", msgTime});
                        }
                        catch(Exception ex){}  
                    }
                }
                else if(msg.type.equals("login")){
                    if(msg.content.equals("TRUE")){                        
                        chatUI.jButton4.setEnabled(true); 
                        chatUI.jTextArea1.append("[SERVER > Me] : Login Successful\n");
                    }
                    else{
                        chatUI.jTextArea1.append("[SERVER > Me] : Login Failed\n");
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
                    chatUI.jButton7.setEnabled(true);
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
                        chatUI.jTextArea1.append("[SERVER > Me] : Singup Successful\n");
                    }
                    else{
                        chatUI.jTextArea1.append("[SERVER > Me] : Signup Failed\n");
                    }
                }
                else if(msg.type.equals("signout")){
                    if(msg.content.equals(chatUI.username)){
                        chatUI.jTextArea1.append("["+ msg.sender +" > Me] : Bye\n");
                        chatUI.jButton4.setEnabled(false); 
                        
                        for(int i = 1; i < chatUI.model.size(); i++){
                            chatUI.model.removeElementAt(i);
                        }
                        
                        chatUI.clientThread.stop();
                    }
                    else{
                        chatUI.model.removeElement(msg.content);
                        chatUI.jTextArea1.append("["+ msg.sender +" > All] : "+ msg.content +" has signed out\n");
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
                        chatUI.jTextArea1.append("[SERVER > Me] : "+msg.sender+" rejected file request\n");
                    }
                }
                else{
                    chatUI.jTextArea1.append("[SERVER > Me] : Unknown message type\n");
                }
            }
            catch(Exception ex) {
                keepRunning = false;
                chatUI.jTextArea1.append("[Application > Me] : Connection Failure\n");
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
