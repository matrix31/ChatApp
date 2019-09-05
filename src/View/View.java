
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

package View;

import Config.csv_read;
import Network.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import static javax.swing.JComponent.WHEN_FOCUSED;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class View extends javax.swing.JFrame{

   static TCPclient tcpclient;
  Border redline = BorderFactory.createLineBorder(Color.red);

   public View() throws FileNotFoundException {
        initComponents();

        // Display parameters
        jAreaConv.setLineWrap(true);
        jAreaConv.setWrapStyleWord(true);
        jAreaConv.setEditable(false);
       // jAreaConv.setAutoscrolls(true);

        jSend.setLineWrap(true);
        jSend.setWrapStyleWord(true);
           
    }
    // Interface components
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jClose = new javax.swing.JButton();
        jBoxModem = new javax.swing.JComboBox<>();
        jChat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jAreaConv = new javax.swing.JTextArea();
        jFile = new javax.swing.JLabel();
        jSendIcon = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jSend = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jAdr = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);

        jPanel1.setBackground(java.awt.Color.white);

        jClose.setText("Close");
        jClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCloseActionPerformed(evt);
            }
        });

        jBoxModem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        jBoxModem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBoxModemActionPerformed(evt);
            }
        });

        jChat.setText("Begin Chatting");
        jChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChatActionPerformed(evt);
            }
        });

        jAreaConv.setEditable(false);
        jAreaConv.setColumns(20);
        jAreaConv.setRows(5);
        jScrollPane1.setViewportView(jAreaConv);

        jFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/clip.png"))); // NOI18N
        jFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jFileMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFileMouseReleased(evt);
            }
        });

        jSendIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/paper-plane.png"))); // NOI18N
        jSendIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jSendIconMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jSendIconMouseReleased(evt);
            }
        });

        jSend.setBackground(new java.awt.Color(239, 239, 239));
        jSend.setColumns(20);
        jSend.setRows(5);
        jSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSendMouseClicked(evt);
            }
        });
        jSend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSendKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jSend);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/profile-user(1).png"))); // NOI18N

        jAdr.setBackground(new java.awt.Color(183, 208, 243));
        jAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAdrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSendIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jChat)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBoxModem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jAdr, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jClose))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChat, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jClose))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jAdr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jBoxModem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSendIcon)
                        .addGap(24, 24, 24)
                        .addComponent(jFile))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChatActionPerformed
      
 
       try {
          jSend.setText("Type something here...");
          jSend.setForeground(Color.lightGray);
          String  remoteAdr = jBoxModem.getSelectedItem().toString();
          DataOutputStream out = new DataOutputStream(tcpclient.socket.getOutputStream());
          
          String ATdrop_buff = "+++ATZ4"+"\n"; // clear the buffer before set up a new remote Address
          String ATadr = "+++AT!AR"+remoteAdr+"\n" ;
          

         tcpclient.SendAT(ATdrop_buff);
         tcpclient.SendAT(ATadr);
         
          
          csv_read read = new csv_read();
          jAdr.setText(read.getAdr());
          
       } catch (IOException ex) {
           Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
       } 
       
    }//GEN-LAST:event_jChatActionPerformed

    private void jBoxModemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBoxModemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBoxModemActionPerformed

    private void jCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCloseActionPerformed
   
  
       try {
           tcpclient.Close(); // close the socket
       } catch (IOException ex) {
           Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
       }
       
  

    }//GEN-LAST:event_jCloseActionPerformed

    private void jSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSelectFileActionPerformed
        

    }//GEN-LAST:event_jSelectFileActionPerformed

    private void jAdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAdrActionPerformed
     
 
    }//GEN-LAST:event_jAdrActionPerformed

    private void jSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSendMouseClicked
        jSend.setText("");
        jSend.setForeground(Color.BLACK);
    }//GEN-LAST:event_jSendMouseClicked

    private void jSendIconMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSendIconMousePressed
        // When clicked take the text written from the jSend write it in the display text area and send it to remote host
        jSendIcon.setVisible(false);
        String Text = jSend.getText();
        tcpclient.SendMessage(Text+"\n");
        jSend.setText("");
        jAreaConv.append("[Me] : "+Text+"\n");
        jAreaConv.append("\n");
        jAreaConv.setCaretPosition(jAreaConv.getDocument().getLength()); // auto scroll when adding text 
       
    }//GEN-LAST:event_jSendIconMousePressed

    private void jSendIconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSendIconMouseReleased
       jSendIcon.setVisible(true);
    }//GEN-LAST:event_jSendIconMouseReleased

    private void jFileMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFileMousePressed
        jFile.setVisible(false);
        JFileChooser popMenu = new JFileChooser("/home/ubiquity/Downloads"); // to do : make csv file

        popMenu.setDialogTitle("Choose a file to send");
        popMenu.setFileSelectionMode(JFileChooser.FILES_ONLY);
        popMenu.setMultiSelectionEnabled(false);
  
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG, PNG & PDF", "jpeg", "png","pdf"); 
        popMenu.setFileFilter(filter); //desactiver le type par défault ??
        
        
        // cancel button 
        int result = popMenu.showDialog(null,"Send");
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = popMenu.getSelectedFile();
            try {
                jFile.setVisible(true);
                tcpclient.SendFile(selectedFile); // Perform the sending of the file selected
            }
            catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (result == JFileChooser.CANCEL_OPTION) {
            jFile.setVisible(true);
 
        }
    }//GEN-LAST:event_jFileMousePressed

    private void jFileMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFileMouseReleased
        jFile.setVisible(true);
    }//GEN-LAST:event_jFileMouseReleased

    private void jSendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSendKeyPressed
        if (evt.getKeyChar() == '\n'){
            int condition = WHEN_FOCUSED;  
            // get our maps for binding from the chatEnterArea JTextArea
            InputMap inputMap = jSend.getInputMap(condition);
            KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

            // tell input map that we are handling the enter key
            inputMap.put(enterStroke, enterStroke.toString());
            String Text = jSend.getText();
            tcpclient.SendMessage(Text+"\n");
            jSend.setText("");
            jAreaConv.append("[Me] : "+Text+"\n");
            jAreaConv.append("\n");
            
        }
    }//GEN-LAST:event_jSendKeyPressed
    
    // PB : enlever line feed lorsque saut de ligne 
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new View().setVisible(true); // displaying interface
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
                new Thread() {
                public void run() {
                    try {
                        System.out.println("\033[H\033[2J");  // clean the console 
                        System.out.flush();
                        csv_read adr_socket = new csv_read();
                        adr_socket.read(); 
                        tcpclient = new TCPclient(csv_read.list.get(0).get(0),9200); // create Client Socket on IPadr,Port
                        String myRemoteAdr = adr_socket.list.get(0).get(1);
                        jBoxModem.removeItem(myRemoteAdr); // remove myRemoteAddress from the list of remote modem
                         
                    }
                    catch (ClassNotFoundException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                   TCPreceiver thread = new TCPreceiver(tcpclient.socket) ;
                   // Create the thread in charge of listening input streams
                    thread.start();
                }
               }.start();
            
}
             });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField jAdr;
    public static javax.swing.JTextArea jAreaConv;
    public static javax.swing.JComboBox<String> jBoxModem;
    private javax.swing.JButton jChat;
    private javax.swing.JButton jClose;
    private javax.swing.JLabel jFile;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTextArea jSend;
    private javax.swing.JLabel jSendIcon;
    // End of variables declaration//GEN-END:variables

}
