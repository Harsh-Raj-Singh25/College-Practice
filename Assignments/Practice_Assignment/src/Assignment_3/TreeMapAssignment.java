package Assignment_3;

import java.util.*;

public class TreeMapAssignment {
    public static void main(String[] args) {
        // --- CONSTRUCTORS ---
        TreeMap<Integer, String> tm = new TreeMap<>(); // 1. Default (Natural Order)
        TreeMap<Integer, String> tmComp = new TreeMap<>(Collections.reverseOrder()); // 2. Comparator
        TreeMap<Integer, String> tmCopy = new TreeMap<>(tm); // 3. From Map

        // --- 15 METHODS ---
        tm.put(10, "Z");
        tm.put(5, "A");
        tm.put(20, "M");

        System.out.println("First Key: " + tm.firstKey());  
        System.out.println("Last Key: " + tm.lastKey());   
        
        System.out.println("Lower than 10: " + tm.lowerKey(10)); 
        System.out.println("Higher than 10: " + tm.higherEntry(10));  
        
        System.out.println("Ceiling 8: " + tm.ceilingKey(8));  
        System.out.println("Floor 8: " + tm.floorKey(8));      
        
        System.out.println("Poll First: " + tm.pollFirstEntry());  
        System.out.println("Poll Last: " + tm.pollLastEntry());    
        
        System.out.println("Descending: " + tm.descendingMap());  
        System.out.println("HeadMap (10): " + tm.headMap(10));   
        System.out.println("SubMap (5, 20): " + tm.subMap(5, 20)); 
        
        tm.replace(10, "New Value");        
        tm.navigableKeySet();                 
        tm.firstEntry();                      
        tm.size();                            
    }
}
