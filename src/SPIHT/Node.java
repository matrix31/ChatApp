


package SPIHT;


public class Node {
    int i,j; //hold the position of the root of the set
    char type; //type of the set (A or B)
    Node next; //link to the next node in the list 
    boolean valid;

    public Node( int i , int j , char type){
        this.i=i;
        this.j=j;
        this.type=type;
        next=null;
        valid=true;
    }
    public Node ( int i,int j,char type, Node last){
        this(i,j,type);
        if( last != null)
        last.next=this;
    }
    public void invalidate(){ 
        valid=false;
    }
    
  } //end of class Node

   
