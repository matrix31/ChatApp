/*
 * 
 */
package SPIHT;




import java.io.DataOutputStream;
import java.io.IOException;



public class SPIHT {
    
    private int dim ;               //side of theimage
    private int nrLIP,nrLSP;        //number of elements in the LIP and LSP
    private int lungCod; ;          //code length = bpp *dim*dim
    private float bpp;              //keeps the target bitrate in bits per pixel
    
    private double[]tab;             //transformed image (theDWTcoefficients)
    private float[] LIP;            //List of Insignicant Pixels
    private float[] LSP;            //List of Significant Pixels
    private float n ;               //currentthreshold
    private List LIS;          //Lis tof insignificant sets
    private DataOutputStream dos ;
    
       //Stream the image code will be written to
    
    
    /* Constructor allocates memory and initializes thefields */
    public SPIHT ( double[] tab, float bpp, int dim, DataOutputStream dos){
        this.dos = dos;
        lungCod = (int)(bpp*dim*dim);           //length of the code at the target bitrate
        this.tab = tab ;
        this.dim = dim ;
        this.bpp = bpp ;
        float[] LIP = new float[dim*dim] ;
        float[] LSP = new float[dim*dim] ;
        LIS= new List();
        nrLIP = 0 ; 
        nrLSP = 0 ;
      
   
    }
    /* end of constructor */
    
    
    /* Veries if the quadtrees with there roots in the direct descendents of coefficient(i,j)are zero trees */
    public boolean SD(int i,int j){
        return ( i<dim/2 && j<dim/2) && (genTree(2*i,2*j) || genTree(2*i,2*j+1) || genTree(2*i+1,2*j) || genTree(2*i+1,2*j+1));   
    } 
    /* end of SD */
    
    
    /* Verifies if the quadtrees with there roots in the descendents sof the descendents of coeficient(i,j) are zero trees */
    public boolean SL(int i,int j){
        return( i<dim/2 && j<dim/2) && (SD(2*i,2*j) || SD(2*i,2*j+1) || SD(2*i+1,2*j) || SD(2*i+1,2*j+1));
    }
    /* end of SL */
    
    
    /* Verifies if the quadtree with the root in coefficient(i,j)is a zero tree */
    public boolean genTree(int i,int j){
        
        if( Math.abs(tab[dim*i+j]) >= n ){
            return true;
        }
        else {
            return SD(i,j);
        }  
    } 
    /* end of genTree */
    
    /* Ccomputes the initial threshold */
    public double retMax(){
        double max=0;
        for ( int i = 0 ; i < dim*dim ; i++ ){
            if ( Math.abs(tab[i]) > max ){
                max = Math.abs(tab[i]);    
            }   
        }  
        double a = Math.log(max) / Math.log((double) 2);        
        double power = (double) Math.floor(a);
        double resultDouble = Math.pow((double)2, power);
        return (float) resultDouble ;
    } 
    /* end of retMax */  
    
    /*   code the coefficient at position(k,l) */
    public void treatCoef(int k,int l)throws EndCoding, IOException{   
        double coef = tab [dim*k+l];
        if ( Math.abs(coef) >= n){
            putBit(true);                                   //code SN(k,l)=1
            LSP[nrLSP++] = (float) (Math.abs(coef)-n);               //add coefficient(k,l) to the LSP
            putBit(coef < 0);                             //code the sign of the coefficients       
        }
        else{
            putBit(false);                              //code SN(k,l)=0
            LIP[nrLIP++] = (float) coef;          
        }   
    }
    /* end of treatCoef */
    
    
    public void codeImage() throws EndCoding,IOException{ 
        noBitsCodif = 0;
        // number of coefficient added to the LSP at the previous passes
        int noLSPPrev = 0;
        double n = retMax();                      //initializethethreshold
        // write thefile header
        dos.writeInt(dim);                 //sideoftheimage
        dos.writeFloat((float) tab[0]);            //general average 
        dos.writeFloat((float) n);                 //initial threshold
        dos.writeFloat(bpp);               //encoding bitrate
        
        //step1 : Initialization
        
        //Initialize the LIP 
        LIP[nrLIP++] = (float) tab[1];
        LIP[nrLIP++] = (float) tab[dim];
        LIP[nrLIP++] = (float) tab[dim+1];
        
        //Initialize the LIS
        LIS.add(0,1,'A');
        LIS.add(1,0,'A');
        LIS.add(1,1,'A');
        
        do{
            //Step2:Sort
            for(int i= 0 ; i < nrLIP ; i++){
                // Verifies if the element vas not eliminated from LIP 
                if(LIP[i]!=Float.POSITIVE_INFINITY)
                    if( Math.abs(LIP[i]) >= n ){
                        putBit(true);
                        //move the coefficient to the LSP
                        LSP[nrLSP++]= (float) Math.abs( ( LIP[i]) - n);
                        //The previous bit 1 indicates the most significant bit of the
                        //coefficient. This coefficient will not be processed in the refinement
                        //step. Next code the sign of the coefficient.
                        putBit(LIP[i]<0);
                        //disables the coefficient in the LIP
                        LIP[i]=Float.POSITIVE_INFINITY;
                    }
                    else{
                        putBit(false);       //the coefficient remains in significant 
                        boolean nextBit;        //holds the next bit of the code
                        //process the LIS
                        for( LIS.start() ; LIS.current != null ; LIS.next()){
                            if(!LIS.current.valid)
                                continue; //step over the eliminated sets
                                if(LIS.current.type=='A'){
                                    nextBit=SD(LIS.current.i,LIS.current.j);
                                    putBit(nextBit);
                                    if(nextBit){
                                    //process the descendents of (i,j)
                                        treatCoef(2*LIS.current.i,2*LIS.current.j);
                                        treatCoef(2*LIS.current.i+1,2*LIS.current.j);
                                        treatCoef(2*LIS.current.i,2*LIS.current.j+1);
                                        treatCoef(2*LIS.current.i+1,2*LIS.current.j+1);
                                        
                                        if(4*LIS.current.i + 3 < dim && 4*LIS.current.j + 3 < dim){
                                        //if the direct descendents of the current node have descendents
                                            LIS.current.type='B';
                                            LISTypeB();
                                        }
                                        else{
                                            LIS.current.invalidate();
                                        }
                                    } // end of if(nexBit)
                                } // end of if(LIS.current.type=='A')
                                else {
                                    //the set is of type B
                                    LISTypeB();
                                }
                        } //end the process of LIS
                    }
            }
                        
            //step 3:
                        
            for( int i=0 ; i< noLSPPrev ; i++){
                 if( LSP[i] >= n){
                     putBit(true);
                     LSP[i] -= n ;
                 }
                 else {
                    putBit(false);
                    noLSPPrev = nrLSP ;// No of coeficients that will be processed at
                    // the next crossing of step3
                    //step4 : Update the threshold n/=2;
                }
            }
        }while(true);
    } // end of codeImage
    
    public void LISTypeB()throws EndCoding, IOException{
        //code a set of typeB
       boolean nextBit = SL(LIS.current.i,LIS.current.j);
       putBit(nextBit);
       if(nextBit){
           LIS.add(2*LIS.current.i,2*LIS.current.j,'A');
           LIS.add(2*LIS.current.i+1,2*LIS.current.j,'A');
           LIS.add(2*LIS.current.i,2*LIS.current.j+1,'A');
           LIS.add(2*LIS.current.i+1,2*LIS.current.j+1,'A');
           LIS.current.invalidate();
       }
    }
    int noBitsCodif; //length of the code
    byte byteCod;//holdsmaximum8bitsoftheco de
    
    
    public void putBit(boolean theBit)throws EndCoding,IOException{
        //puts the next bit in the image code 
        byte mask=(byte)(1<<7 - noBitsCodif % 8);
        if(theBit){
            byteCod |= mask;
        }
        else{
            byteCod &= ~mask;
            noBitsCodif++ ; 
        }
        if(noBitsCodif % 8 == 0){
            dos.writeByte(byteCod);
        }
        if(noBitsCodif >= lungCod){
            dos.close();
            throw new EndCoding("End of image coding");
        }
    } // end of putBit
      
    } // end of class SPIHT

  
                 

// cast double to float s