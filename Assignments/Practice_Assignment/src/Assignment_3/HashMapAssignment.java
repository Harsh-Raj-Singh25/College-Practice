package Assignment_3;

import java.util.*;

public class HashMapAssignment {
    public static void main(String[] args) {
        // --- CONSTRUCTORS ---
        HashMap<Integer, String> map = new HashMap<>(); // 1. Default
        HashMap<Integer, String> capMap = new HashMap<>(32); // 2. Capacity
        HashMap<Integer, String> loadMap = new HashMap<>(16, 0.5f); // 3. Capacity + Load Factor
        HashMap<Integer, String> copyMap = new HashMap<>(map); // 4. From Map

        // --- 15 METHODS ---
        map.put(1, "Chrome");                 
        map.putIfAbsent(2, "Firefox");         
        map.putAll(copyMap);                 
        
        System.out.println("Value for 1: " + map.get(1)); 
        System.out.println("Default: " + map.getOrDefault(3, "Edge"));  
        
        System.out.println("Has Key 2? " + map.containsKey(2));  
        System.out.println("Has Value? " + map.containsValue("Safari"));  
        
        map.replace(1, "Brave");              
        map.remove(2);                        //  remove(Object key)
        
        Set<Integer> keys = map.keySet();      
        Collection<String> vals = map.values();  
        Set<Map.Entry<Integer, String>> entries = map.entrySet();  
        
        System.out.println("Size: " + map.size());  
        map.compute(1, (k, v) -> v + " Browser");  
        map.clear();                         
    }
}
