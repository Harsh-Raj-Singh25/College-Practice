package Assignment_4;

import java.sql.*;

public class DeleteStudents {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "Harsh@SQL12");
            
            // SQL DELETE with multiple conditions
            String query = "DELETE FROM Students WHERE yop = 2024 AND branch = 'Civil'";
            
            Statement stmt = con.createStatement();
            int deletedRows = stmt.executeUpdate(query);
            
            System.out.println("Deleted " + deletedRows + " records of Civil 2024 batch.");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}