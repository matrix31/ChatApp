
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

package View;

import Config.csv_read;
import Network.*;
import static Network.TCPreceiver.clickable;
import static View.ATConsole.jSendAT;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import static javax.swing.JComponent.WHEN_FOCUSED;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class View extends javax.swing.JFrame{

   static TCPclient tcpclient;
   String plus = "+++";
   private int plusCpt = 0 ; 
   private final byte plusByte = 0x2b ;
    private boolean triplePlus = false ;
  

   public View() throws FileNotFoundException {
        initComponents();

        // Display parameters
        jAreaConv.setLineWrap(true);
        jAreaConv.setWrapStyleWord(true);
        jAreaConv.setEditable(false);
       // jAreaConv.setAutoscrolls(true);

        jSend.setLineWrap(true);
        jSend.setWrapStyleWord(true);
        
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // closing socket
 
           
    }
    // Interface components
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jFrame1 = new javax.swing.JFrame();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jBoxModem = new javax.swing.JComboBox<>();
        jChat = new javax.swing.JButton();
        jFile = new javax.swing.JLabel();
        jSendIcon = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jSend = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jAdr = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jAreaConv = new javax.swing.JTextArea();
        jConsole = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenu3.setText("File");
        jMenuBar3.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar3.add(jMenu4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);

        jPanel1.setBackground(java.awt.Color.white);

        jBoxModem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        jBoxModem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBoxModemActionPerformed(evt);
            }
        });

        jChat.setText("Begin");
        jChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChatActionPerformed(evt);
            }
        });

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

        jAreaConv.setEditable(false);
        jAreaConv.setColumns(20);
        jAreaConv.setRows(5);
        jAreaConv.setFocusable(false);
        jScrollPane1.setViewportView(jAreaConv);

        jConsole.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Console.png"))); // NOI18N
        jConsole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jConsoleMouseClicked(evt);
            }
        });

        jLabel1.setText("Scale");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(jBoxModem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(31, 31, 31)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jAdr, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jChat)
                        .addGap(231, 231, 231)
                        .addComponent(jLabel1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFile)
                            .addComponent(jSendIcon))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jConsole)
                        .addGap(34, 34, 34))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jChat))
                            .addComponent(jConsole))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jAdr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addComponent(jLabel3))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jBoxModem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jSendIcon)
                        .addGap(18, 18, 18)
                        .addComponent(jFile))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSelectFileActionPerformed
        

    }//GEN-LAST:event_jSelectFileActionPerformed

    private void jAdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAdrActionPerformed

    }//GEN-LAST:event_jAdrActionPerformed

    private void jSendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSendKeyPressed
     
            if (evt.getKeyChar() == '\n'){
                try {
                    int condition = WHEN_FOCUSED;
                    // get our maps for binding from the chatEnterArea JTextArea
                    InputMap inputMap = jSend.getInputMap(condition);
                    KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

                    // tell input map that we are handling the enter key
                    inputMap.put(enterStroke, enterStroke.toString());
                    String Text = jSend.getText();



                    /* avoid String text to be interpreted as AT command */
                    /*
                    char[] charArray = Text.toCharArray();
                    for ( int i = 0 ; i < charArray.length ; i++){
                        if (charArray[i] == plusByte){
                            plusCpt++; 
                        }
                        if (plusCpt == 3){
                            triplePlus = true;
                            charArray[i-2] = '&';
                            charArray[i-1] = '1';
                            charArray[i] ='A';
                        }

                    }
                    plusCpt = 0 ;

                    if ( triplePlus == false){
                    */
                            tcpclient.SendMessage(Text+"\n");
                            jSend.setText("");
                            jAreaConv.append("[Me] : "+Text+"\n");
                            jAreaConv.append("\n");
                            jAreaConv.setCaretPosition(jAreaConv.getDocument().getLength()); // auto scroll when adding text

                    //}
                   // triplePlus = false ; 




                } catch (IOException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        
    }//GEN-LAST:event_jSendKeyPressed

    private void jSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSendMouseClicked
        jSend.setText("");
        jSend.setForeground(Color.BLACK);
    }//GEN-LAST:event_jSendMouseClicked

    private void jSendIconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSendIconMouseReleased
        jSendIcon.setVisible(true);
    }//GEN-LAST:event_jSendIconMouseReleased

    private void jSendIconMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSendIconMousePressed
      
        try {
           // When clicked take the text written from the jSend write it in the display text area and send it to remote host
           jSendIcon.setVisible(false);
           String Text = jSend.getText();
           
            /* avoid String text to be interpreted as AT command */
                char[] charArray = Text.toCharArray();
                for ( int i = 0 ; i < charArray.length ; i++){
                    if (charArray[i] == plusByte){
                        plusCpt++; 
                    }
                    if (plusCpt == 3){
                        triplePlus = true;
                    }
                      
                }
     
                plusCpt = 0 ;
           
                if ( triplePlus == false){
                        tcpclient.SendMessage(Text+"\n");
                        jSend.setText("");
                        jAreaConv.append("[Me] : "+Text+"\n");
                        jAreaConv.append("\n");
                        jAreaConv.setCaretPosition(jAreaConv.getDocument().getLength()); // auto scroll when adding text
      
                }
                triplePlus = false ; 
        
          
           
       } catch (IOException ex) {
           Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        

    }//GEN-LAST:event_jSendIconMousePressed

    private void jFileMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFileMouseReleased
        jFile.setVisible(true);
    }//GEN-LAST:event_jFileMouseReleased

    private void jFileMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFileMousePressed
       
            jFile.setVisible(false);
            JFileChooser popMenu = new JFileChooser("./ChatApp/Files/toSend"); 

            popMenu.setDialogTitle("Choose a file to send");
            popMenu.setFileSelectionMode(JFileChooser.FILES_ONLY);
            popMenu.setMultiSelectionEnabled(false);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, PDF & TIF", "jpeg", "png","pdf","jpg","tif");
            popMenu.setFileFilter(filter); //desactiver le type par défault ??

            // cancel button
            int result = popMenu.showDialog(null,"Send");
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File selectedFile = popMenu.getSelectedFile();
                    String name = selectedFile.getName();

                    jFile.setVisible(true);




                    BufferedImage image = ImageIO.read(selectedFile);
  
              BufferedImage tThumbImage = new BufferedImage( 500, 300, BufferedImage.TYPE_INT_RGB );
              Graphics2D tGraphics2D = tThumbImage.createGraphics(); //create a graphics object to paint to
              tGraphics2D.setBackground( Color.WHITE );
              tGraphics2D.setPaint( Color.WHITE );
              tGraphics2D.fillRect( 0, 0, 500, 300 );
              tGraphics2D.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
              tGraphics2D.drawImage( image, 0, 0, 500, 300, null ); //draw the image scaled





                    File file = new File("./ChatApp/Files/toSend/RescaleFile.jpeg");

                    ImageIO.write( tThumbImage, "JPG", file ); //write the image to a file
                    
                    /* deal with name : concatenate  */


              tcpclient.SendFile(file); // Perform the sending of the file selected
                } catch (IOException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }





            } else if (result == JFileChooser.CANCEL_OPTION) {
                jFile.setVisible(true);

            }
        
    }//GEN-LAST:event_jFileMousePressed

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

    private void jConsoleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jConsoleMouseClicked
        if (clickable){                             // avoid the sending of AT commands while a file is received
        ATConsole console = new ATConsole();
        console.setVisible(true);
        jSendAT.setText("Type AT commands here...");
        jSendAT.setForeground(Color.lightGray);
        }
    }//GEN-LAST:event_jConsoleMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        ScalingOption scale = new ScalingOption();
        scale.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked
    
    public void  MenuBarTest()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(java.awt.Color.WHITE);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Open");

        menu.add(menuItem);
        menuBar.add(menu);

        setContentPane(contentPane);
        setJMenuBar(menuBar);
        setSize(200, 200);
        setVisible(true);
    }
    
   
    
    
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
                    View view = new View(); // displaying interface
                    view.setVisible(true);
               
                    
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
    private javax.swing.JLabel jConsole;
    private javax.swing.JLabel jFile;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTextArea jSend;
    private javax.swing.JLabel jSendIcon;
    // End of variables declaration//GEN-END:variables

}
