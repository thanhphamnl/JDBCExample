import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CallableStmt {

	// JDBC URL, username and password of MySQL server
	private static final String url = "jdbc:mysql://localhost:3306/db_world?serverTimezone=UTC";
	//jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC
	private static final String user = "root";
	private static final String password = "Tudongtest123";

	private static Connection con;
	private static CallableStatement stmt;

	public static void main(String[] args) {

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			con = DriverManager.getConnection(url, user, password);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			String sql = "{call update_price (?, ?)}";
			stmt = con.prepareCall(sql);

			// Bind IN parameter first, then bind OUT parameter
			String isbn = "001";
			stmt.setString(1, isbn);

			int price = 1000;
			stmt.setInt(2, price);

			// Use execute method to run stored procedure.
			System.out.println("Executing stored procedure...");
			stmt.executeUpdate();

			stmt.close();
			con.close();
		} catch (SQLException e) {
			// Handle errors for JDBC
			//se.printStackTrace();
			System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    System.out.println("VendorError: " + e.getErrorCode());
		} catch (Exception e) {
			// Handle errors for Class.forName
			//e.printStackTrace();
			System.out.println("SQLException: " + e.getMessage());
		   
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				//se.printStackTrace();
				System.out.println("SQLException: " + e.getMessage());
			    System.out.println("SQLState: " + e.getSQLState());
			    System.out.println("VendorError: " + e.getErrorCode());
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end main
		// end
}
