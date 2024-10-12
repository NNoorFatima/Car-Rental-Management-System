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
	            int crmsStInt=crmsSt?1:0;

	            if (dbSt != crmsSt)
	            {
	                String updateSQL = "UPDATE car SET status = " + crmsStInt + " WHERE carid = " + car.getID();
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
	{
		String sql = "INSERT INTO renter (name, email, address, phone_no,type,total_rent_fee) VALUES ('"
                + rent.getName() + "', '" 
                + rent.getEmail()+ "', '" 
                + rent.getAddress() + "', '" 
                + rent.getPh_no()+ "','"
                +rent.displayRenterType()+"',"
                +rent.getTotal_rent_fee()+")";
		try (Connection conn = DriverManager.getConnection(URL);
        Statement stmt = conn.createStatement()) 
		{
			int rowsInserted = stmt.executeUpdate(sql);
			if (rowsInserted > 0) 
				System.out.println("A new renter was added successfully!");
			else 
				System.out.println("Unable to add renter");
		}
		catch (SQLException e) 
		{
			System.out.println("Error occurred while adding the renter.");
			e.printStackTrace();
		}
	}
	public static void displayRenters()
	{
		String sql = "SELECT * FROM renter";

	    try (Connection conn = DriverManager.getConnection(URL);
	         Statement renterStmt = conn.createStatement();
	         ResultSet renterRs = renterStmt.executeQuery(sql)) {

	        while (renterRs.next()) {
	            int id = renterRs.getInt("renterid");
	            String name = renterRs.getString("name");
	            String email = renterRs.getString("email");
	            String address = renterRs.getString("address");
	            String phone_no = renterRs.getString("phone_no");
	            String type = renterRs.getString("type");
	            Float total_rent_fee= renterRs.getFloat("total_rent_fee");
	            
	            System.out.println("ID: " + id +
	                               "\nName: " + name +
	                               "\nEmail: " + email +
	                               "\nAddress: " + address +
	                               "\nPhone_no: " + phone_no+
	            					"\nTotal_rent_fee: "+total_rent_fee);

	            // Check cars_rented
	            String carsSql = "SELECT carid, brand FROM cars_rented WHERE renterid = " + id;
	            try (Statement carStmt = conn.createStatement();
	                 ResultSet carRs = carStmt.executeQuery(carsSql)) {

	                if (!carRs.isBeforeFirst()) 
	                { // If the ResultSet is empty
	                    System.out.println("No cars rented.");
	                } 
	                else 
	                {
	                    System.out.println("Cars rented:");
	                    while (carRs.next()) 
	                    {
	                        int carId = carRs.getInt("carid");
	                        String brand = carRs.getString("brand");
	                        System.out.println("- Car ID: " + carId + ", Brand: " + brand);
	                    }
	                }
	            }
	            System.out.println("\n");
	        }

	    } catch (SQLException e) {
	        System.out.println("Error occurred while retrieving Renters.");
	        e.printStackTrace();
	    }
	}
	public static void removeRenter(int id)
	{
		String sql = "DELETE FROM renter WHERE renterid = " + id + 
                " AND NOT EXISTS (SELECT 1 FROM cars_rented WHERE renterid = " + id + ")";
		 try (Connection conn = DriverManager.getConnection(URL);
	             Statement stmt = conn.createStatement())
		 {
			 int Affected = stmt.executeUpdate(sql);
			 if(Affected>0)
				 System.out.println("Renter with id "+ id +" has been removed\n");
			 else
				 System.out.println("No renter with id "+ id +" was found or they have cars rented2\n");
	     } 
		 catch (SQLException e) {
	            System.out.println("Error occurred while retrieving Renter.");
	            e.printStackTrace();
		 }
	}
	public static void updateRenter(Renter rent)
	{
		String checkRenterSQL = "SELECT COUNT(*) FROM renter WHERE renterid = " + rent.getRentID();

		try (Connection conn = DriverManager.getConnection(URL);
		     Statement checkStmt = conn.createStatement()) 
		{
		    ResultSet rs = checkStmt.executeQuery(checkRenterSQL);
//		    if (rs.next() && rs.getInt(1) == 0) 
//		    {
//		        System.out.println("Error");
//		        return;
//		    }

		    if (!rent.getRentedCars().isEmpty() && rent.getRentedCars() != null )
		    {
		        try (Statement insertStmt = conn.createStatement())
		        {
		            for (Car car : rent.getRentedCars())
		            {
		                String alreadyExisting = "SELECT COUNT(*) FROM cars_rented WHERE renterid = " + rent.getRentID() + " AND carid = " + car.getID();
		                ResultSet carCheckRs = insertStmt.executeQuery(alreadyExisting);
		                if (carCheckRs.next() && carCheckRs.getInt(1) > 0) 
		                {
		                    System.out.println("Entry for renter ID " + rent.getRentID() + 
		                                       " and car ID " + car.getID() +
		                                       " already exists. Skipped.");
		                    continue;
		                }
		                String insertCarSQL = "INSERT INTO cars_rented (renterid, carid, brand) VALUES ("
		                                      + rent.getRentID() + ", " + car.getID() + ", '" + car.getBrand() + "')";
		                insertStmt.executeUpdate(insertCarSQL);
		            }
		            System.out.println("Updated renter ID: " + rent.getRentID());
		        } 
		    } 
		} 
		catch (SQLException e) 
		{
		    System.out.println("Error occurred while updating the renter.");
		    e.printStackTrace();
		}

	}





}


