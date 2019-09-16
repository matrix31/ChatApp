/*
 * 
 */
package View;


import static View.View.tcpclient;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import static javax.swing.JComponent.WHEN_FOCUSED;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.Border;


/**
 *
 * @author franck
 */
public class ATConsole extends javax.swing.JFrame {

    /**
     * Creates new form ATConsole
     */
    public ATConsole() {
        initComponents();
        jATdisplay.setLineWrap(true);
        jATdisplay.setWrapStyleWord(true);
        jATdisplay.setEditable(false);
        Insets in = new Insets(0,30,0,30); 
        jATdisplay.setMargin(in);
        
        jSendAT.setLineWrap(true);
        jSendAT.setWrapStyleWord(true);
        
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // hidding windows
       
  
        
        
        
        
       Border border = BorderFactory.createLineBorder(Color.white);
     //  jATdispaly.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(0, 0, 0, 0)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jATdisplay = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jSendAT = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuAT = new javax.swing.JMenu();
        jItemManuals = new javax.swing.JMenuItem();
        jItemWebSite = new javax.swing.JMenuItem();
        jItemClose = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        On = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jATdisplay.setColumns(20);
        jATdisplay.setRows(5);
        jATdisplay.setBorder(null);
        jATdisplay.setFocusable(false);
        jATdisplay.setMargin(new java.awt.Insets(30, 30, 0, 0));
        jScrollPane1.setViewportView(jATdisplay);

        jSendAT.setBackground(new java.awt.Color(239, 239, 239));
        jSendAT.setColumns(20);
        jSendAT.setRows(5);
        jSendAT.setBorder(null);
        jSendAT.setCaretColor(new java.awt.Color(255, 255, 255));
        jSendAT.setMargin(new java.awt.Insets(30, 30, 0, 0));
        jSendAT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSendATMouseClicked(evt);
            }
        });
        jSendAT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSendATKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jSendAT);

        jMenuAT.setText("Settings");

        jItemManuals.setText("See Manuals");
        jItemManuals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jItemManualsActionPerformed(evt);
            }
        });
        jMenuAT.add(jItemManuals);

        jItemWebSite.setText("EvoLogic Website");
        jItemWebSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jItemWebSiteActionPerformed(evt);
            }
        });
        jMenuAT.add(jItemWebSite);

        jItemClose.setText("Close");
        jItemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jItemCloseActionPerformed(evt);
            }
        });
        jMenuAT.add(jItemClose);

        jMenu1.setText("Rescaling");

        On.setText("On");
        On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnActionPerformed(evt);
            }
        });
        jMenu1.add(On);

        jMenuItem1.setText("Off");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuAT.add(jMenu1);

        jMenuBar1.add(jMenuAT);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 79, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 215, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jItemManualsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jItemManualsActionPerformed
        String ManScript = "./ChatApp/Scripts/manuals.sh";
        
        Runtime r = Runtime.getRuntime();
        Process pr;
        try {
            pr = r.exec(ManScript);
            pr.waitFor();// wait fot the end ;
        } catch (IOException ex) {
            Logger.getLogger(ATConsole.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ATConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jItemManualsActionPerformed

    private void jSendATKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSendATKeyPressed
        if (evt.getKeyChar() == '\n'){
            int condition = WHEN_FOCUSED;
            // get our maps for binding from the chatEnterArea JTextArea
            InputMap inputMap = jSendAT.getInputMap(condition);
            KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

            // tell input map that we are handling the enter key
            inputMap.put(enterStroke, enterStroke.toString());
            String Text = jSendAT.getText();
            try {
                tcpclient.SendAT(Text+"\n");
            } catch (IOException ex) {
                Logger.getLogger(ATConsole.class.getName()).log(Level.SEVERE, null, ex);
            }
            jSendAT.setText("");
            jATdisplay.append("    Me >   "+Text+"\n");
            jATdisplay.append("\n");
            jATdisplay.setCaretPosition(jATdisplay.getDocument().getLength()); // auto scroll when adding text
        }
    }//GEN-LAST:event_jSendATKeyPressed

    private void jItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jItemCloseActionPerformed
        this.setVisible(false);
        
    }//GEN-LAST:event_jItemCloseActionPerformed

    private void jItemWebSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jItemWebSiteActionPerformed
         String WebScript = "./ChatApp/Scripts/website.sh";
        
        Runtime r = Runtime.getRuntime();
        Process pr;
        try {
            pr = r.exec(WebScript);
            pr.waitFor();// wait fot the end ;
        } catch (IOException ex) {
            Logger.getLogger(ATConsole.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ATConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jItemWebSiteActionPerformed

    private void jSendATMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSendATMouseClicked
        jSendAT.setText("");
        jSendAT.setForeground(Color.BLACK);
    }//GEN-LAST:event_jSendATMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OnActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ATConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ATConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ATConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ATConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ATConsole ATconsole = new ATConsole();
                ATconsole.setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem On;
    public static javax.swing.JTextArea jATdisplay;
    private javax.swing.JMenuItem jItemClose;
    private javax.swing.JMenuItem jItemManuals;
    private javax.swing.JMenuItem jItemWebSite;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenuAT;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTextArea jSendAT;
    // End of variables declaration//GEN-END:variables
}
