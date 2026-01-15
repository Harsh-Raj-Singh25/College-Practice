package Assignment_4;

import java.sql.*;

public class UpdatePercentage {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String pass = "Harsh@SQL12";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();

            // STEP 1: Fix the schema (Only do this once if your table is wrong)
            // We ensure 'branch' exists by defining the table correctly
            String dropTable = "DROP TABLE IF EXISTS Students";
            stmt.executeUpdate(dropTable);

            String createTable = "CREATE TABLE Students (" +
                                 "eno INT PRIMARY KEY, " +
                                 "name VARCHAR(50), " +
                                 "branch VARCHAR(10), " + // THE MISSING COLUMN
                                 "per INT, " + 
                                 "yop INT, " + 
                                 "sem INT)";
            stmt.executeUpdate(createTable);
            System.out.println("Table recreated with 'branch' column.");

            // STEP 2: Insert a dummy CSE student to test the update
            stmt.executeUpdate("INSERT INTO Students VALUES (101, 'Harsh', 'CSE', 80, 2025, 7)");

            // STEP 3: Perform the 5% increase (The code causing your error)
            // Logic: Increase percentage (per) by 5% for CSE students
            String updateQuery = "UPDATE Students SET per = per + (per * 0.05) WHERE branch = 'CSE'";
            
            int rowsAffected = stmt.executeUpdate(updateQuery);
            
            System.out.println("Update Successful! Rows affected: " + rowsAffected);

            con.close();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}