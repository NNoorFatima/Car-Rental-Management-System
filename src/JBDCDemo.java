import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class JBDCDemo {

	private static final String URL="jdbc:mysql://localhost:3306/crms_task";
	private static final String username="root";
	private static final String password="22639646410Aa";
	
	public static void main(String [] args) 
	{
	
		String url = "jdbc:mysql://localhost:3306/crms_task";
		String username="root";
		String password="22639646410Aa";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection(url, username, password);
			System.out.println("Connection created");
			Statement statement=con.createStatement();
			ResultSet resultset=statement.executeQuery("select *from car");
			
			while(resultset.next())
			{
				System.out.println(resultset.getInt(1)+ " " +resultset.getString(2)+" "+resultset.getString(3)
				+" "+resultset.getInt(4)+" "+resultset.getInt(5)+ " "+resultset.getInt(6)+"\n");
			}
			con.close();
		}
		
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	public static void saveCars()
	{
		
	}
}
