
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

package Network;


import Config.csv_read;
import static View.View.jAreaConv;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import javax.imageio.ImageIO;
import static jdk.nashorn.internal.objects.NativeRegExp.test;




public class TCPreceiver extends Thread implements Serializable{

    public Socket socket;
    public DataInputStream in ;
    
    public String str;
    public String checkAT = "+++AT!AR:2:OK\r\n";
    public String adoptedRemoteAdr="+++AT:7:RADDR";
    public String str_sub ;
    
    public byte[] txt_byte = {0x01} ;
    public byte[] file_byte = {0x02} ;
    
    boolean first_fragment = true ;
   
    public byte byteType;
    public byte byteSize ;
    
    public int size ; 
    public int byte_nb = 0 ;

    // Constructor : take current socket in argument
    public TCPreceiver(Socket socket) {
        this.socket=socket;
    }
    
    // Listening Thread
   @Override
    
    
    public void run(){
        
        byte [] file_size = new byte[4];
        
        
 
           try{
                
            while (true){
                 

              
                    while  ( socket.getInputStream().available()>0){
                          
           
                        DataInputStream  input = new DataInputStream(socket.getInputStream());
                        byte[] ByteArray = new byte[socket.getInputStream().available()];
                        input.read(ByteArray);
                        
                        for ( byte a :ByteArray ){
                            byte_nb++ ;      
                        }
                        
                        if (first_fragment){
                            System.arraycopy(ByteArray,1,file_size,0,4);     
                            ByteBuffer bytebuff = ByteBuffer.allocate(4);
                            bytebuff = ByteBuffer.wrap(file_size);
                            size = bytebuff.getInt();                       // file size 
                            first_fragment = false ; 
                        }
                        System.out.println("Taille du fichier"+size);
                        
                        
                        System.out.println("numero byte "+byte_nb);
                        
                        
                        
                        /*
                        
                        
                        if (first_fragment){
                            byteType = ByteArray[0];                    // Type of Byte ; stream from conversation [1] or file stream [2]
                              if ( byteType == file_byte[0]){
                                  int fragmentLength = ByteArray.length - 5 ;
                                  byte [] ByteFile = new byte[size] ;
                               //   System.arraycopy(ByteArray,5,ByteFile,0,fileLength); // Byte File array without header
                                  first_fragment = false ; 
                              }
                              else {
                                  int fileLength = ByteArray.length ;
                                  byte [] ByteFile = new byte[fileLength] ;
                                  System.arraycopy(ByteArray,0,ByteFile,0,fileLength);
                              }
                            */      
                                
                                
      
                        
                            
                          
                        System.out.println("Byte du fichier : "+ Arrays.toString(ByteArray));
                        System.out.println("Header : "+ Arrays.toString(file_size));
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    
                        
                    //    System.out.println(Arrays.toString(ByteArray)); // compressed array
                        
                        
                      //  System.out.println(element); 
                      /*
                        
                        if (element == txt_header[0]){
                            System.out.println("I have received a message");
                            System.out.println();
                            
                            str = new String(ByteArray) ; // convert it in string
                            
                            str_sub = str.substring(1); // delete the header character
                            csv_read adr = new csv_read();
                            jAreaConv.append("\n");
                            jAreaConv.append("["+adr.getAdr()+"] : "+str_sub+"\n"); // display text
                            jAreaConv.setCaretPosition(jAreaConv.getDocument().getLength()); // auto scroll when adding text 
                               
                        }
                      */
                      
                      
                          
                          
                          
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
                    
                      
                } 
                      
                     // alimenter le tableau finale ici
                     // quand fin attente construire image
                   
            

            }
                    
            } catch (IOException ex) {
            Logger.getLogger(TCPreceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                    
    }   
   

             
             
            
            


           
    
  

  
       
                  
                  
                  
                  
                  
                  
              
                     
                     
      


        
            
                    
                    
                    
                    
                    
                    
                    
                    
                    
  
        

    

                
                
                
                
                
                
   
        
    
 


