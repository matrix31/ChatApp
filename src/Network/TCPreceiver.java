
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

package Network;


import Config.csv_read;
import static View.View.jAreaConv;
import java.io.ByteArrayOutputStream;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPreceiver extends Thread {

    public Socket socket;
    private DataInputStream in ;
    
    private final String checkAT = "+++AT!AR:2:OK\r\n";
    private final String adoptedRemoteAdr="+++AT:7:RADDR";
    private String str;
    private String str_sub ;
    private String fileName ;
   
    private final byte txt_byte = 0x01 ;
    private final byte file_byte = 0x02 ;
    private byte byteType;
 
    private boolean first_fragment = true ;
    
    private int size ;
    private int byteFileSize;
 
    private float ratio; 
    

    // Constructor : take current socket in argument
    public TCPreceiver(Socket socket) {
        this.socket=socket;
    }
    
    // Listening Thread
   @Override
    public void run(){
        
        ByteArrayOutputStream recept = new ByteArrayOutputStream();
        byte [] fileSizeArray = new byte[4];
        
        while (true){
         
            try {
                while  ( socket.getInputStream().available()>0){
                  
                    
                    DataInputStream  input = new DataInputStream(socket.getInputStream());
                    byte[] ByteArray = new byte[socket.getInputStream().available()];
                    input.read(ByteArray);
                    byteType = ByteArray[0];
                    
                    /* first packet received */
                    if (first_fragment){
                        
                        /* Data received from file */
                        if (file_byte == byteType){
                            
                            /* Get de size of the file from the Header */
                            System.arraycopy(ByteArray,1,fileSizeArray,0,4);
                            ByteBuffer bytebuff = ByteBuffer.allocate(4);
                            bytebuff = ByteBuffer.wrap(fileSizeArray);
                            size = bytebuff.getInt();
                            
                            /* Get de name of the file from the Header */
                            byteFileSize = ByteArray[5];
                            byte [] byteFileName = new byte[byteFileSize];
                            System.arraycopy(ByteArray,6,byteFileName,0,byteFileSize);
                            fileName = new String(byteFileName);
                            
                            /* Reception */
                            recept.write(ByteArray, byteFileSize+6,ByteArray.length-(byteFileSize+6));
                            byte[] FILE = recept.toByteArray();
                            
                            /* Progress in %tage */
                            ratio = ((float) FILE.length ) / ((float) size);
                            DecimalFormat df = new DecimalFormat("#.##");
                            System.out.print(" ChatApp > "+df.format(ratio*100)+" % of the file received\r");
                            
                            first_fragment = false ;
                            
                            /* if no fragmantation induced by the hardware */
                            if ( FILE.length == size){
                                
                                /* File creation */
                                File file = new File("/home/ubiquity/Downloads",fileName);
                                FileOutputStream recvFile = new FileOutputStream(file);
                                recvFile.write(FILE);
                                recvFile.close();
                                
                                
                                recept.close();
                                recept.reset();
                                
                                size = 0;
                                ratio = 0;
                                first_fragment = true ;
                                
                                System.out.println(" ChatApp > File reception sucessful - the file has been saved under "+fileName);
                            }
                        }
                        
                        /* Data for conversation to be displayed */
                        if (byteType == txt_byte){
                            
                            str = new String(ByteArray) ; // Byte to String
                            str_sub = str.substring(1); // Delete the type byte (first byte)
                            
                            csv_read adr = new csv_read();
  
                            jAreaConv.append("["+adr.getAdr()+"] : "+str_sub+"\n"); // display text
                            jAreaConv.setCaretPosition(jAreaConv.getDocument().getLength()); // auto scroll when adding text
                        }
                    }
                    
                    /* fragments number x received */
                    else if (byteType != txt_byte ) {
                        
                        /* Reception */
                        recept.write(ByteArray, 0,ByteArray.length);
                        byte[] FILE = recept.toByteArray();
                        
                        
                        /* Progress in %tage */
                        ratio = ((float) FILE.length ) / ((float) size);
                        DecimalFormat df = new DecimalFormat("#.##");
                        System.out.print(" ChatApp > "+df.format(ratio*100)+" % of the file received\r");
                        
                        /* Wait the last fragment */
                        if ( FILE.length == size){
                            
                            /* File creation */
                            File file = new File("/home/ubiquity/Downloads",fileName);
                            FileOutputStream recvFile = new FileOutputStream(file);
                            recvFile.write(FILE);
                            recvFile.close();
                            
                            /* Reset ByteArrayOutputStream */
                            recept.close();
                            recept.reset();
                            
                            first_fragment = true ;
                            size = 0;
                            ratio = 0;
                            
                            System.out.println(" ChatApp > File reception sucessful - the file has been saved under "+fileName);
                            
                        }
                    }
                    
                    /*
                    System.out.println("----------------------------------------------------------------------------------");
                    System.out.println("\n");
                    System.out.println("Fragment numero "+i+" : "+ Arrays.toString(ByteArray));
                    System.out.println("\n");
                    System.out.println("Taille du fichier sur 4 bytes : "+ Arrays.toString(fileSizeArray));
                    System.out.println("Taille du fichier "+size);
                    System.out.println("nombre byte du fichier recus jusqu'a maintenant "+byte_nb);
                    System.out.println("Index tableau "+arrayIndex);
                    */
                    
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(TCPreceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

/*******************************************************************************************************/
                      
    
                /*
                int t = ByteArray.length -1 ;
                byte [] test = new byte[t] ;
                System.arraycopy(ByteArray,1,test,0,t); // new array without the header
                Inflater decompressor = new Inflater(); // decompression of the file
                decompressor.setInput(test);
                ByteArrayOutputStream bos = new ByteArrayOutputStream(test.length);
                
                byte[] buf = new byte[8192];
                while (!decompressor.finished()) {
                try {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
                } catch (DataFormatException e) {
                }
                }
                try {
                bos.close();
                } catch (IOException e) {
                }
                byte[] decompressedData = bos.toByteArray();
                FileOutputStream fileOut = new FileOutputStream("/home/ubiquity/Downloads/img.jpeg");
                System.out.println("I have received a file");
                fileOut.write(decompressedData);
                System.out.println(Arrays.toString(decompressedData));
                fileOut.close();
                }
                
                else {
                str = new String(ByteArray) ; // convert it in string
                if (str.equals(checkAT)){
                System.out.println(" ChatApp > Remote Address has been set correctly");
                System.out.println(" ChatApp > You can Chat\n");
                }
                }
                System.out.println("nb de byte recu : "+ i);
                
                
                
                /*
                if(str.substring(0,13).equals(adoptedRemoteAdr)){
                System.out.println();
                
                System.out.println("Remote modem "+str.substring(14,15)+" send you a message while you were not connected");
                jAreaConv.append("["+socket.getInetAddress()+"] : "+str.substring(17)+"\n"); // display text
                }
                */
                
                
                
                
                
                
                //     ByteArrayOutputStream bos = new ByteArrayOutputStream(t);
                
                
                //  while(socket.getInputStream().available() >0) {
                //    bos.write(test);
                
                
                
                // }
                
                
                //   byte[] Fragment = bos.toByteArray();
                
                
                
                
                
                
                
                
                
                /*
                byte[] Data = new byte[2192]; // a changer avec taille totale
                
                for ( int j = 0 ; j < 2 ; j++){
                
                System.arraycopy(Fragement,0,Data,j*1024,Fragement.length);
                System.out.println(Arrays.toString(Data));
                }
                
                ByteArrayInputStream bin = new ByteArrayInputStream(Data);
                BufferedImage imageReceived = ImageIO.read(bin);
                File fichier = new File("/home/ubiquity/Downloads/img.jpeg");
                ImageIO.write(imageReceived, "jpeg",fichier);
                System.out.println("I have received a file");
                System.out.println(Arrays.toString(Data));
                
                //  System.out.println(Arrays.toString(decompressedData));
                //  FileOutputStream fileOut = new FileOutputStream("/home/ubiquity/Downloads/img.jpeg");
                //fileOut.write(decompressedData);
                
                
                System.out.println("nb de byte recu : "+ i);
                
                
                
                
                
                int sizeInt = (byteSize & 0xFF) ;
                System.out.println("taille fichier "+ sizeInt);
                System.out.println(byteSize);
                */
 
                // alimenter le tableau finale ici
                // quand fin attente construire image
           

         
   
                   
            

             
                    

                    
    

   

             
             
            
            


           
    
  

  
       
                  
                  
                  
                  
                  
                  
              
                     
                     
      


        
            
                    
                    
                    
                    
                    
                    
                    
                    
                    
  
        

    

                
                
                
                
                
                
   
        
    
 


