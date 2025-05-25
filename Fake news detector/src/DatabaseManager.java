import java.sql.*;

public class DatabaseManager {

    // 1️⃣ JDBC credentials – update if needed
    static final String DB_URL = "jdbc:mysql://localhost:3306/fake_news_db";
    static final String USER = "root"; // use your username
    static final String PASS = "";     // use your MySQL password

    // 2️⃣ Function to insert data into the table
    public static void insertResult(String headline, String result) {
        String query = "INSERT INTO news_checks (headline, result) VALUES (?, ?)";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            pstmt.setString(1, headline);
            pstmt.setString(2, result);
            pstmt.executeUpdate();
            System.out.println("✅ Data inserted into MySQL successfully.");

        } catch (SQLException e) {
            System.out.println("❌ Error inserting into DB: " + e.getMessage());
        }
    }
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/fake_news_db",
                    "root", // 👉 your MySQL username
                    "" // 👉 your MySQL password
            );
            System.out.println("✅ Connected to MySQL!");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}



