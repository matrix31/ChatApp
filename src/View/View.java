
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

package View;

import Config.csv_read;
import Network.*;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class View extends javax.swing.JFrame{

   static TCPclient tcpclient;

   public View() throws FileNotFoundException {
        initComponents();

        // Display parameters
        jAreaConv.setLineWrap(true);
        jAreaConv.setWrapStyleWord(true);
        jAreaConv.setEditable(false);

        jSend.setLineWrap(true);
        jSend.setWrapStyleWord(true);
       
        

    }
    // Interface components
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jAreaConv = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jSend = new javax.swing.JTextArea();
        Send = new javax.swing.JButton();
        jBoxModem = new javax.swing.JComboBox<>();
        jChat = new javax.swing.JButton();
        jFile = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jAreaConv.setEditable(false);
        jAreaConv.setColumns(20);
        jAreaConv.setRows(5);
        jScrollPane1.setViewportView(jAreaConv);

        jSend.setColumns(20);
        jSend.setRows(5);
        jScrollPane2.setViewportView(jSend);

        Send.setText("Send");
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
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

        jFile.setText("Send file");
        jFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "File 1", "File 2", "File 3", "File 4", "File 5" }));

        jClose.setText("Close");
        jClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Send)
                .addGap(0, 22, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jBoxModem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jFile))
                    .addComponent(jChat))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jClose))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jClose)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBoxModem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jChat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFile)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Send)
                        .addGap(28, 28, 28)))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendActionPerformed

        // When clicked take the text written from the jSend text area and send it to remote host
        String Text = jSend.getText();
        tcpclient.SendMessage(Text+"\n");
        jSend.setText("");
        jAreaConv.append("[Me] : "+Text+"\n");
    }//GEN-LAST:event_SendActionPerformed

    private void jChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChatActionPerformed
      
       
       try {
          String  remoteAdr = jBoxModem.getSelectedItem().toString();
          DataOutputStream out = new DataOutputStream(tcpclient.socket.getOutputStream());
          
          String ATdrop_buff = "+++ATZ4"+"\n"; // clear the buffer before set up a new remote Address
          String ATadr = "+++AT!AR"+remoteAdr+"\n" ;
          

          byte[] ATtableADR = ATadr.getBytes();
          byte[] ATtableBUF = ATdrop_buff.getBytes();
 
          out.flush();
          out.write(ATtableBUF);
          out.write(ATtableADR);
          out.flush();
          
       } catch (IOException ex) {
           Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
       } 
       
    }//GEN-LAST:event_jChatActionPerformed

    private void jBoxModemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBoxModemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBoxModemActionPerformed

    private void jFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileActionPerformed

       try {
           tcpclient.SendFile(); // Perform the sending of the file selected
       } catch (IOException ex) {
           Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jFileActionPerformed

    private void jCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCloseActionPerformed
       try {
           tcpclient.Close(); // close the socket
       } catch (IOException ex) {
           Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
       }
                      
                           
                   
                    

    }//GEN-LAST:event_jCloseActionPerformed

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
    private javax.swing.JButton Send;
    public static javax.swing.JTextArea jAreaConv;
    public static javax.swing.JComboBox<String> jBoxModem;
    private javax.swing.JButton jChat;
    private javax.swing.JButton jClose;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JButton jFile;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jSend;
    // End of variables declaration//GEN-END:variables

}
