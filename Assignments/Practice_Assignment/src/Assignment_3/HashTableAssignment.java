package Assignment_3;

import java.util.*;

public class HashTableAssignment {
    public static void main(String[] args) {
        // --- CONSTRUCTORS ---
        Hashtable<String, String> ht = new Hashtable<>(); // 1. Default
        Hashtable<String, String> ht2 = new Hashtable<>(20); // 2. Capacity

        // --- 15 METHODS ---
        ht.put("QA", "Harsh");                
        ht.put("Dev", "Amit");
        
        System.out.println("Value: " + ht.get("QA"));  
        System.out.println("Is Empty: " + ht.isEmpty());  
        
        Enumeration<String> e = ht.keys();     
        Enumeration<String> v = ht.elements(); 
        
        System.out.println("Contains Harsh? " + ht.contains("Harsh"));  
        System.out.println("Has Key QA? " + ht.containsKey("QA")); 
        
      //  ht.rehash();                          
        ht.remove("Dev");                     
        
        System.out.println("Size: " + ht.size());  
        ht.toString();                  
        ht.equals(ht2);                      
        ht.hashCode();                      
        ht.putAll(ht2);                        
        ht.clear();                    
    }
}
