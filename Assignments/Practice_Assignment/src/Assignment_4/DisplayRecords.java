package Assignment_4;

import java.sql.*;

public class DisplayRecords {
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "Harsh@SQL12");

			String query = "SELECT * FROM Students WHERE sem = 7 AND branch = 'EC'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			System.out.println("--- Students of 7th Sem (EC Branch) ---");
			while (rs.next()) {
				System.out.println("Eno: " + rs.getInt("eno") + ", Name: " + rs.getString("name") + ", Percentage: "
						+ rs.getInt("per") + "%");
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
