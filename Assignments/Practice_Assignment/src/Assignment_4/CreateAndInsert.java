package Assignment_4;

import java.sql.*;

public class CreateAndInsert {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "Harsh@SQL12");
            Statement stmt = con.createStatement();

            // 1. Create Table
            String createTable = "CREATE TABLE IF NOT EXISTS Students (" +
                                 "eno INT PRIMARY KEY, " +
                                 "name VARCHAR(50), " +
                                 "branch VARCHAR(10), " +
                                 "per INT, " +
                                 "yop INT, " +
                                 "sem INT)";
            stmt.executeUpdate(createTable);
            System.out.println("Table 'Students' created successfully.");

            // 2. Insert Records using PreparedStatement
            String insertQuery = "INSERT INTO Students VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            
            // Adding a sample record
            pstmt.setInt(1, 101);
            pstmt.setString(2, "Harsh");
            pstmt.setString(3, "CSE");
            pstmt.setInt(4, 80);
            pstmt.setInt(5, 2025);
            pstmt.setInt(6, 7);
            
            pstmt.executeUpdate();
            System.out.println("Record inserted successfully.");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
