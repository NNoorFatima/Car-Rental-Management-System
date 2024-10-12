import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class JBDCssms {
	
	private static final String URL="jdbc:sqlserver://FATIMA\\SQLEXPRESS;databaseName=CRMS;integratedSecurity=true;trustServerCertificate=true";
	
	JBDCssms()
	{}
	public static void main(String [] args)
	{
		String url = "jdbc:sqlserver://FATIMA\\SQLEXPRESS;databaseName=CRMS;integratedSecurity=true;trustServerCertificate=true";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("SDF");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

	///CARS
	public static void saveCars(Car car)
	{

	    try (Connection conn = DriverManager.getConnection(URL);
	         Statement stmt = conn.createStatement())
	    {
	    	String sql = "INSERT INTO car (brand, model, year, fee, status, type, plate) VALUES ('"
	                    + car.getBrand() + "', '"
	                    + car.getModel() + "', "
	                    + car.getYear() + ", "
	                    + car.getFee() + ", "
	                    + (car.getStatus() ? 1 : 0) + ", '"
	                    + car.getClass().getSimpleName() + "', '"
	                    + car.getPlate() + "')";
	            
	            stmt.executeUpdate(sql);
	            System.out.println("Car data inserted into the database successfully.");
	        
	    } 
	    catch (SQLException e)
	    {
	        System.out.println("Error occurred while inserting car data: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	public static void displayCars()
	{
		String sql = "SELECT *FROM car";
		 
		 try (Connection conn = DriverManager.getConnection(URL);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) 
		 {
	            while (rs.next()) 
	            {
	                int id = rs.getInt("carid");
	                String brand = rs.getString("brand");
	                String model = rs.getString("model");
	                int year = rs.getInt("year");
	                double fee = rs.getDouble("fee");
	                int status = rs.getInt("status");
	                String type = rs.getString("type");
	                String plate= rs.getString("plate");	
	                // Print the data (you can format this as needed)
	                System.out.println("ID: " + id +
	                                   "\nBrand: " + brand +
	                                   "\nModel: " + model +
	                                   "\nYear: " + year +
	                                   "\nFee: " + fee +
	                                   "\nStatus: " + (status == 1 ? "Rented" : "Not Rented")+
	                                   "\nType: "+ type+
	                				   "\nPlate: "+plate+"\n");	
	                }

	        } 
		 catch (SQLException e) {
	            System.out.println("Error occurred while retrieving cars.");
	            e.printStackTrace();
		 }
	}
	public static void removeCar(int id)
	{
		
		String sql="DELETE FROM car WHERE carid = " + id + " AND status = 0;";
		
		 try (Connection conn = DriverManager.getConnection(URL);
	             Statement stmt = conn.createStatement())
		 {
			 int Affected = stmt.executeUpdate(sql);
			 if(Affected>0)
				 System.out.println("Car with id "+ id +" has been removed\n");
			 else
				 System.out.println("Car with id "+ id +" was either not found or is rented\n");

	     } 
		 catch (SQLException e) 
		 {
	            System.out.println("Error in removing car");
	            e.printStackTrace();
		 }
		
	}
	public static void updateCar(Car car)
	{
	    try (Connection conn = DriverManager.getConnection(URL);
	         Statement stmt = conn.createStatement()) 
	    {
	        String query = "SELECT carid, status FROM car WHERE carid = " + car.getID();
	        ResultSet rs = stmt.executeQuery(query);
	        if (rs.next()) 
	        {
	            boolean dbSt = rs.getBoolean("status");
	            boolean crmsSt = car.getStatus();

	            if (dbSt != crmsSt)
	            {
	                String updateSQL = "UPDATE car SET status = " + crmsSt + " WHERE carid = " + car.getID();
	                stmt.executeUpdate(updateSQL);
	                System.out.println("Updated");
	            } 
	            else 
	                System.out.println("No status change for Car ID " + car.getID());	            
	        }
	        rs.close();
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	//RENTERS
	public static void saveRenters(Renter rent)
	{}
	public static void displayRenters()
	{}
	public static void removeRenter()
	{}
	public static void updateRenter()
	{}





}


