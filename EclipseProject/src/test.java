import DatabaseAccess.Driver;
import java.sql.*;

public class test
{
	public static void main(String[] args){
		
		Driver d = new Driver();
		ResultSet rs = d.printUsers();
		try {
			while(rs.next()){
			    System.out.println(rs.getString("uid") + rs.getString("name"));	

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}