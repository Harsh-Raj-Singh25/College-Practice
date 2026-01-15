public class StringMethods {
    public static void main(String[] args) {
        String s = " Hello World ";
        
        System.out.println("Length : " + s.length());
        System.out.println("Empty? " + s.isEmpty());
        System.out.println("CharAt 1? " + s.charAt(1));
        System.out.println("Contains Java? " + s.contains("Java"));
        System.out.println("Index of W: " + s.indexOf('W'));
        System.out.println("Trimmed: '" + s.trim() + "'");
        System.out.println("Substring: " + s.substring(7, 11));
        System.out.println("Replace: " + s.replace("Java", "Selenium"));
        
        String[] words = s.trim().split(" ");
        System.out.println("Split count: " + words.length);
        System.out.println("Joined: " + String.join("-", "2025", "12", "19"));
    }
}