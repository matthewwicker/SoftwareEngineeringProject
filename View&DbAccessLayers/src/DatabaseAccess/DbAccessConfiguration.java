package DatabaseAccess;

public abstract class DbAccessConfiguration {

	protected String DB_DRIVE_NAME = "com.mysql.jdbc.Driver";
	
	protected String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/Books";
	
	protected String DB_CONNECTION_USERNAME = "root";
	
	protected String DB_CONNECTION_PASSWORD = "";
}
