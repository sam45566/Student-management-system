/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {
    private Connection con;

    public DatabaseManager() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "Alex@1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get the next student ID
    public int getNextStudentId() {
        int id = 0;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM stud_data_db");
            if (rs.next()) {
                id = rs.getInt(1);  // Retrieve the maximum ID
            }
            id++;  // Increment to get the next ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    // Method to register a student
    public boolean registerStudent(int id, String firstName, String lastName, String department, String dob, String entryYear) {
        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO stud_data_db VALUES(" + id + ", '" + firstName + "', '" + lastName + "', '" +
                    department + "', '" + dob + "', '" + entryYear + "')";
            int result = stmt.executeUpdate(sql);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Close connection when done
    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
