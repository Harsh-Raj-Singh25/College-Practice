package Assignment_3;

import java.util.*;

public class VectorAssignment {
    public static void main(String[] args) {
        // --- CONSTRUCTORS ---
        Vector<Integer> v1 = new Vector<>();             // 1. Default
        Vector<Integer> v2 = new Vector<>(30);          // 2. Initial Capacity
        Vector<Integer> v3 = new Vector<>(10, 5);       // 3. Capacity + Increment
        Vector<Integer> v4 = new Vector<>(v1);          // 4. From Collection

        // --- 15 METHODS ---
        v1.addElement(10);                    
        v1.add(20);                            
        v1.insertElementAt(5, 0);              
        
        System.out.println("Capacity: " + v1.capacity()); 
        System.out.println("First: " + v1.firstElement()); 
        System.out.println("Last: " + v1.lastElement());   
        
        v1.setElementAt(100, 1);               
        v1.removeElement(Integer.valueOf(20)); 
        v1.removeElementAt(0);               
        
        v1.setSize(10);                        
        System.out.println("Element at 0: " + v1.elementAt(0)); 
        
        Enumeration<Integer> en = v1.elements(); 
        v1.copyInto(new Integer[10]);         
        v1.removeAllElements();              
        v1.retainAll(v2);                    
    }
}
