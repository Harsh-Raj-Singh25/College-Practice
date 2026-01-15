package Assignment_3;

import java.util.*;

public class ArrayListAssignment {
    public static void main(String[] args) {
        // --- CONSTRUCTORS ---
        // 1. Default constructor (Initial capacity 10)
        ArrayList<String> list = new ArrayList<>();
        // 2. Constructor with initial capacity
        ArrayList<String> capacityList = new ArrayList<>(20);
        // 3. Constructor using another collection
        ArrayList<String> listFromCollection = new ArrayList<>(Arrays.asList("A", "B"));

        // --- 15 METHODS ---
        list.add("Java");                    
        list.add(0, "Selenium");             
        list.addAll(listFromCollection);      
        
        System.out.println("Size: " + list.size());
        System.out.println("At index 1: " + list.get(1)); 
        System.out.println(" current list:"+list);
        
        list.set(1, "Python");             
        System.out.println("Contains Java? " + list.contains("Java")); 
        System.out.println("Index of Python: " + list.indexOf("Python")); 
        
        list.remove("A");                     
        list.remove(2);                      
        
        System.out.println("Is Empty? " + list.isEmpty()); 
        
        Object[] arr = list.toArray();       
        list.ensureCapacity(50);             
        list.trimToSize();                   
        list.clear();                         
        
        System.out.println("Final list: " + list);
    }
}