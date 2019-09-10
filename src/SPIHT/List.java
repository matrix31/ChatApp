/*
 * 
 */
package SPIHT;


public class List {
    
    Node first = null ;
    Node last=null ;
    Node current = first ;

    
    public void add( int i , int j,char tip){ //add a new node in the list
        Node a = new Node(i,j,tip,last);
        last=a;
        if( first == null){
            first = last ;
                 
    } 
    }
    
        
    public void start(){
        current = first ;
    } //select thefirst node in the list
    
    public Node next(){                    //select the next node in the list 
        if(current!=null){
            current=current.next;
        }
        return current;
    }
}
