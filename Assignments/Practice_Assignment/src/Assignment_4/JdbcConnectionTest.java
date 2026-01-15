package Assignment_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement; // Corrected Import

public class JdbcConnectionTest {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try { // Removed the '*'
            //Register Driver (Optional in newer JDBC versions, but good practice)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish Connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "Harsh@SQL12"); 

            if (con != null) {
                System.out.println("Connection Successful");
                
                // 3. Create Statement and Execute Query (Database Testing Example)
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM students LIMIT 1"); // Change table name as per your DB
                
                while(rs.next()) {
                    System.out.println("Data found: " + rs.getString(1));
                }
            }

        } catch (Exception e) {
            System.out.println("Unable to connect");
            e.printStackTrace();
        } finally {
            // Always close resources in finally block to avoid memory leaks
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}