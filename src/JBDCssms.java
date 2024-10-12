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

	//TRANSACTIONS
	public static void saveTransactions(CRMS tran) {
	    // Assuming this gets the latest transaction
	    rental_transaction a = tran.getTransactions().get(tran.getTransactions().size() - 1);
	    
	    // Prepare status string
	    String st = a.getStatus() ? "Returned" : "Rented";

	    // Create SQL query
	    String sql = "INSERT INTO transactions (tranID, carid, renterID, car_type, renter_type, status) VALUES ("
	            + a.getTransId() + ", "  
	            + a.getCarId() + ", "   
	            + a.getRenterId() + ", '"  
	            + a.getCar_type() + "', '"
	            + a.getRenter_type() + "', '"
	            + st + "')";
	    try (Connection conn = DriverManager.getConnection(URL);
	         Statement stmt = conn.createStatement()) {

	        // Execute SQL insertion
	        int rowsInserted = stmt.executeUpdate(sql);
	        if (rowsInserted > 0) {
	            System.out.println("Transaction was added successfully!");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error occurred while adding the transaction.");
	        e.printStackTrace();
	    }
	}

	public static void displayTransactions()
	{
		String sql = "SELECT *FROM transactions";
		try (Connection conn = DriverManager.getConnection(URL);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) 
		 {
	            while (rs.next()) 
	            {
	                int id = rs.getInt("tranID");
	                int carid = rs.getInt("carid");
	                int renterid = rs.getInt("renterid");
	                String car_type = rs.getString("car_type");
	                String renter_type = rs.getString("renter_type");
	                Double totalRentalCost = rs.getObject("total_rental_cost", Double.class); // Using getObject to handle nulls
	                Double damageCost = rs.getObject("damage_cost", Double.class);
	                Boolean isInsured = rs.getObject("insurance", Boolean.class);
	                String status=rs.getString("status");
	                System.out.println("TranID: " + id +
	                                   "\nCarID: " + carid +
	                                   "\nRenterID: " + renterid +
	                                   "\nCar_type: " + car_type +
	                                   "\nRenter_type: " + renter_type+
						                "\nTotal Rental Cost: " + (totalRentalCost != null ? totalRentalCost : "N/A") +
					                    "\nDamage Cost: " + (damageCost != null ? damageCost : "N/A")+
					                    "\nIs Insured: " + (isInsured != null ? (isInsured ? "Insured\n" : "Not Insured\n") : "N/A\n")
					                    +"Status: "+ status + "\n");;
	            }

		 } 
		 catch (SQLException e) 
		 {
	            System.out.println("Error occurred while retrieving Transaction .");
	            e.printStackTrace();
		 }
	}
	public static void updateInsuranceTransactions(CRMS tran)
	{
		if (tran != null && !tran.getTransactions().isEmpty()) 
        {
            try (Connection conn = DriverManager.getConnection(URL);
                 Statement stmt = conn.createStatement()) {

                for (rental_transaction a : tran.getTransactions())
                {
                    Car cartype = null;
                    for (Car car : tran.getCar_management().getCars())
                    {
                        if (car.getID() == a.getCarId()) 
                        {
                            cartype = car;
                            break;
                        }
                    }
                    if (cartype != null)
                    {
                    	int insurance = cartype.isInsurable() ? 1 :0;
                        String updateInsuranceSQL = "UPDATE transactions SET insurance = " + insurance +
                                                    " WHERE tranID = " + a.getTransId();
                        int rowsAffected = stmt.executeUpdate(updateInsuranceSQL);
                        if (rowsAffected > 0) 
                            System.out.println("Updated insurance status for transaction ID: " + a.getTransId());
                        else 
                            System.out.println("No update occurred for transaction ID: " + a.getTransId());
                    }
                }

            } 
            catch (SQLException e) {
                System.out.println("Error occurred while updating insurance status.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No transactions available to update.");
        }
	}
	public static void updateCostTransactions(CRMS tran) 
	{
		if (tran != null && !tran.getTransactions().isEmpty()) 
	    {
	        try (Connection conn = DriverManager.getConnection(URL);
	             Statement stmt = conn.createStatement()) 
	        {
	            for (rental_transaction a : tran.getTransactions())
	            {
	                Car cartype = null;
	                Renter rentertype = null;
	                for (Car car : tran.getCar_management().getCars())
	                {
	                    if (car.getID() == a.getCarId()) 
	                    {
	                        cartype = car;
	                        break;
	                    }
	                }
	                for (Renter rent : tran.getRenter_management().getRenters())
	                {
	                    if (rent.getRentID() == a.getRenterId())  // Fixed this check to compare renterID
	                    {
	                        rentertype = rent;
	                        break;
	                    }
	                }

	                if (cartype != null && rentertype != null)
	                {
	                    float damagecost = (float)tran.calculateDamageCost(rentertype, cartype);
	                    float total_rental_cost =(float) rentertype.getTotal_rent_fee();	                  
	                    String updateSQL = "UPDATE transactions SET damage_cost = " + damagecost + 
	                                       ", total_rental_cost = " + total_rental_cost + 
	                                       " WHERE tranID = " + a.getTransId();
	                    int rowsAffected = stmt.executeUpdate(updateSQL);
	                    if (rowsAffected > 0) 
	                        System.out.println("Updated cost for transaction ID: " + a.getTransId());
	                    else 
	                        System.out.println("No update occurred for transaction ID: " + a.getTransId());
	                } else {
	                    System.out.println("Car or Renter not found for transaction ID: " + a.getTransId());
	                }
	            }
	        } 
	        catch (SQLException e) 
	        {
	            System.out.println("Error occurred while updating cost transactions.");
	            e.printStackTrace();
	        }
	    } 
	    else 
	    {
	        System.out.println("No transactions available to update.");
	    }
	}
	public static void returnTransactions(CRMS tran) 
	{
		rental_transaction lastTransaction = tran.getTransactions().get(tran.getTransactions().size()-1);
		String sql= "DELETE FROM cars_rented WHERE carid= " + lastTransaction.getCarId()
					+" AND renterid= " + lastTransaction.getRenterId() ;
		
		 try (Connection conn = DriverManager.getConnection(URL);
		         Statement stmt = conn.createStatement()) 
		 {
			 int rowsAffected = stmt.executeUpdate(sql);
             if (rowsAffected > 0) 
                 System.out.println("Deleted from cars_rented table");
             else 
                 System.out.println("Couldn't delete from cars_rented table");
             Car cartype=null;
             for(Car car: tran.getCar_management().getCars())
             {
            	 if(car.getID()==lastTransaction.getCarId())
            	 {
            		 cartype=car;
            		 break;
            	 }
             }
             //update the status of car 
             updateCar(cartype);
             //remove from the renters list of rented cars in crms 
             for(Renter rent: tran.getRenter_management().getRenters())
             {
            	 if(rent.getRentID()==lastTransaction.getRenterId())
            	 {
            		 rent.getRentedCars().remove(cartype);
            	 }
             }
             //( lastTransaction);
             tran.getTransactions().get(tran.getTransactions().size()-1).setStatus(true);
            saveTransactions(tran);
             
		 }
		 catch (SQLException e) {
		        System.out.println("Error occurred while adding the transaction.");
		        e.printStackTrace();
		    }
	}



}


