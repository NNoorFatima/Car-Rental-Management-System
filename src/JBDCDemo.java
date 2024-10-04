import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class JBDCDemo {

	private static final String URL="jdbc:mysql://localhost:3306/crms_task";
	private static final String username="root";
	private static final String password="22639646410Aa";
	
	JBDCDemo()
	{}
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
	
	//CARS
	public static void saveCars(Car car)
	{
	    int statusValue = car.getStatus()? 1 : 0; // Assuming "Available" means true, otherwise false
	    String sql = "INSERT INTO car (brand, model, year, fee, status) VALUES ('"
	                 + car.getBrand() + "', '" 
	                 + car.getModel() + "', " 
	                 + car.getYear() + ", " 
	                 + car.getFee() + ", " 
	                 + statusValue + ")";
	    try (Connection conn = DriverManager.getConnection(URL, username, password);
	         Statement stmt = conn.createStatement()) {

	        int rowsInserted = stmt.executeUpdate(sql);
	        if (rowsInserted > 0) 
	            System.out.println("A new car was added successfully!");
	    } 
	    catch (SQLException e) {
	        System.out.println("Error occurred while adding the car.");
	        e.printStackTrace();
	    }
	}
	public static void displayCars()
	{
		 String sql = "SELECT *FROM car";
		 
		 try (Connection conn = DriverManager.getConnection(URL, username, password);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) 
		 {

	            // Loop through the result set and print each record
	            while (rs.next()) 
	            {
	                int id = rs.getInt("carid");
	                String brand = rs.getString("brand");
	                String model = rs.getString("model");
	                int year = rs.getInt("year");
	                double fee = rs.getDouble("fee");
	                int status = rs.getInt("status");

	                // Print the data (you can format this as needed)
	                System.out.println("ID: " + id +
	                                   ", Brand: " + brand +
	                                   ", Model: " + model +
	                                   ", Year: " + year +
	                                   ", Fee: " + fee +
	                                   ", Status: " + (status == 1 ? "Available" : "Unavailable"));
	            }

	        } 
		 catch (SQLException e) {
	            System.out.println("Error occurred while retrieving cars.");
	            e.printStackTrace();
		 }
		 
		 
	}
	public static void removeCar()
	{
		System.out.println("Among all the cars below, chose the id of the car you want to remove\n");
		displayCars();
		Scanner sc= new Scanner(System.in);
		int id= sc.nextInt();
		String sql= "DELETE FROM car WHERE carid= "+id;
		
		 try (Connection conn = DriverManager.getConnection(URL, username, password);
	             Statement stmt = conn.createStatement())
		 {
			 int Affected = stmt.executeUpdate(sql);
			 if(Affected>0)
				 System.out.println("Car with id "+ id +" has been removed\n");
			 else
				 System.out.println("No car with id "+ id +" was found\n");

	     } 
		 catch (SQLException e) {
	            System.out.println("Error occurred while retrieving cars.");
	            e.printStackTrace();
		 }
		
	}
	public static void updateCar()
	{
		System.out.println("Choose the car which you want to update\n");
		displayCars();
		Scanner sc= new Scanner(System.in);
		int choice = sc.nextInt();
		
		System.out.println("What do you want to update?");
		System.out.println("1. Brand");
		System.out.println("2. Model");
		System.out.println("3. Fee");
		System.out.println("4. Year");
		int option = sc.nextInt();
		sc.nextLine();
		String sql="";
		if(option==1)
		{
			System.out.println("Enter the new brand name");
			String a= sc.nextLine();  
			sql="UPDATE car SET brand = '"+a+"' WHERE carid ="+choice;
		}
		else if(option==2)
		{
			System.out.println("Enter the new model name");
			String a= sc.nextLine();  
			sql="UPDATE car SET model = '"+a+"' WHERE carid ="+choice;
		}
		else if(option==3)
		{
			System.out.println("Enter the new rent fee ");
			int a= sc.nextInt();  
			sc.nextLine();
			sql="UPDATE car SET fee = '"+a+"' WHERE carid ="+choice;
		}
		else if(option==4)
		{
			System.out.println("Enter the new year");
			int a= sc.nextInt();  
			sc.nextLine();
			sql="UPDATE car SET year = '"+a+"' WHERE carid ="+choice;
		}
		
		
		
		 try (Connection conn = DriverManager.getConnection(URL, username, password);
	             Statement stmt = conn.createStatement())
		 {
			 int Affected = stmt.executeUpdate(sql);
			 if(Affected>0)
				 System.out.println("Car with id " + choice +" has been updated\n");
			 else
				 System.out.println("No car with id "+ choice +" was found\n");

	     } 
		 catch (SQLException e) {
	            System.out.println("Error occurred while retrieving cars.");
	            e.printStackTrace();
		 }
		
		
	}
	

	//RENTER
	public static void saveRenters(Renter rent)
	{
	    String sql = "INSERT INTO renter (name, email, address, phone_no) VALUES ('"
	                 + rent.getName() + "', '" 
	                 + rent.getEmail()+ "', " 
	                 + rent.getAddress() + ", " 
	                 + rent.getPh_no()+ ")"; 
	    try (Connection conn = DriverManager.getConnection(URL, username, password);
	         Statement stmt = conn.createStatement()) {

	        int rowsInserted = stmt.executeUpdate(sql);
	        if (rowsInserted > 0) 
	            System.out.println("A new renter was added successfully!");
	        

	    } catch (SQLException e) {
	        System.out.println("Error occurred while adding the renter.");
	        e.printStackTrace();
	    }
	}
	public static void displayRenters()
	{
		 String sql = "SELECT *FROM renter";
		 
		 try (Connection conn = DriverManager.getConnection(URL, username, password);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) 
		 {
	            while (rs.next()) 
	            {
	                int id = rs.getInt("renterid");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String address = rs.getString("address");
	                String phone_no = rs.getString("phone_no");
	                // Print the data (you can format this as needed)
	                System.out.println("ID: " + id +
	                                   ", Name: " + name +
	                                   ", Email: " + email +
	                                   ", Addres: " + address +
	                                   ", Phone_no: " + phone_no);
	            }

		 } 
		 catch (SQLException e) {
	            System.out.println("Error occurred while retrieving Renter.");
	            e.printStackTrace();
		 }
	}
	public static void removeRenter()
	{
		System.out.println("Among all the renters below, chose the id of the renter you want to remove\n");
		displayRenters();
		Scanner sc= new Scanner(System.in);
		int id= sc.nextInt();
		String sql= "DELETE FROM renter WHERE carid= "+id;
		
		 try (Connection conn = DriverManager.getConnection(URL, username, password);
	             Statement stmt = conn.createStatement())
		 {
			 int Affected = stmt.executeUpdate(sql);
			 if(Affected>0)
				 System.out.println("Renter with id "+ id +" has been removed\n");
			 else
				 System.out.println("No renter with id "+ id +" was found\n");

	     } 
		 catch (SQLException e) {
	            System.out.println("Error occurred while retrieving Renter.");
	            e.printStackTrace();
		 }
		
	}
	public static void updateRenter()
	{
		System.out.println("Choose the renter which you want to update\n");
		displayCars();
		Scanner sc= new Scanner(System.in);
		int choice = sc.nextInt();
		
		System.out.println("What do you want to update?");
		System.out.println("1. Name");
		System.out.println("2. Email");
		System.out.println("3. Address");
		System.out.println("4. Phone_no");
		int option = sc.nextInt();
		sc.nextLine();
		String sql="";
		if(option==1)
		{
			System.out.println("Enter the new name");
			String a= sc.nextLine();  
			sql="UPDATE renter SET name = '"+a+"' WHERE renterid ="+choice;
		}
		else if(option==2)
		{
			System.out.println("Enter the new email ");
			String a= sc.nextLine();  
			sql="UPDATE renter SET email = '"+a+"' WHERE renterid ="+choice;
		}
		else if(option==3)
		{
			System.out.println("Enter the new address ");
			String a= sc.nextLine();  
			sql="UPDATE renter SET address = '"+a+"' WHERE renterid ="+choice;
		}
		else if(option==4)
		{
			System.out.println("Enter the new phone_no");
			int a= sc.nextInt();  
			sc.nextLine();
			sql="UPDATE renter SET phone_no = '"+a+"' WHERE renterid ="+choice;
		}
		
		
		
		 try (Connection conn = DriverManager.getConnection(URL, username, password);
	             Statement stmt = conn.createStatement())
		 {
			 int Affected = stmt.executeUpdate(sql);
			 if(Affected>0)
				 System.out.println("renter with id " + choice +" has been updated\n");
			 else
				 System.out.println("No car renter id "+ choice +" was found\n");

	     } 
		 catch (SQLException e) {
	            System.out.println("Error occurred while retrieving renters.");
	            e.printStackTrace();
		 }
	}
}
