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
	    String sql = "INSERT INTO car (brand, model, year, fee, status,type,plate) VALUES ('"
	                 + car.getBrand() + "', '" 
	                 + car.getModel() + "', " 
	                 + car.getYear() + ", " 
	                 + car.getFee() + ", " 
	                 + statusValue + ",'"
	    			 + car.displayCarType()+"','"
	    			 +car.getPlate()+"')";
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
	                				   "\nPlate: "+plate);	            }

	        } 
		 catch (SQLException e) {
	            System.out.println("Error occurred while retrieving cars.");
	            e.printStackTrace();
		 }
	}
	public static void removeCar(int id)
	{
		
		String sql="DELETE FROM car WHERE carid = " + id + " AND status = 0";
;
		
		 try (Connection conn = DriverManager.getConnection(URL, username, password);
	             Statement stmt = conn.createStatement())
		 {
			 int Affected = stmt.executeUpdate(sql);
			 if(Affected>0)
				 System.out.println("Car with id "+ id +" has been removed\n");
			 else
				 System.out.println("Car with id "+ id +" was either not found or is rented\n");

	     } 
		 catch (SQLException e) {
	            System.out.println("Error in removing car");
	            e.printStackTrace();
		 }
		
	}

	public static void updateCar(Car car)
	{
	    try (Connection conn = DriverManager.getConnection(URL, username, password);
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
	                System.out.println("Car ID status updated in MySQL.");
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



	//RENTER
	public static void saveRenters(Renter rent)
	{
	    String sql = "INSERT INTO renter (name, email, address, phone_no,type,total_rent_fee) VALUES ('"
	                 + rent.getName() + "', '" 
	                 + rent.getEmail()+ "', '" 
	                 + rent.getAddress() + "', '" 
	                 + rent.getPh_no()+ "','"
	                 +rent.displayRenterType()+"',"
	                 +rent.getTotal_rent_fee()+")";
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
	public static void displayRenters() {
	    String sql = "SELECT * FROM renter";

	    try (Connection conn = DriverManager.getConnection(URL, username, password);
	         Statement renterStmt = conn.createStatement();
	         ResultSet renterRs = renterStmt.executeQuery(sql)) {

	        while (renterRs.next()) {
	            int id = renterRs.getInt("renterid");
	            String name = renterRs.getString("name");
	            String email = renterRs.getString("email");
	            String address = renterRs.getString("address");
	            String phone_no = renterRs.getString("phone_no");
	            String type = renterRs.getString("type");
	            Double total_rent_fee= renterRs.getDouble("total_rent_fee");

	            // Print the renter data
	            System.out.println("ID: " + id +
	                               "\nName: " + name +
	                               "\nEmail: " + email +
	                               "\nAddress: " + address +
	                               "\nPhone_no: " + phone_no+
	            					"\nTotal_rent_fee: "+total_rent_fee);

	            // Check if the renter has any rented cars
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
		 try (Connection conn = DriverManager.getConnection(URL, username, password);
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
	    
	    try (Connection conn = DriverManager.getConnection(URL, username, password);
	         Statement checkStmt = conn.createStatement()) 
	    {
	        ResultSet rs = checkStmt.executeQuery(checkRenterSQL);
	        if (rs.next() && rs.getInt(1) == 0) 
	        {
	            System.out.println("Error: Renter with ID " + rent.getRentID() + " does not exist.");
	            return;
	        }
	        conn.setAutoCommit(false);

	        if (rent.getRentedCars() != null && !rent.getRentedCars().isEmpty())
	        {
	            try (Statement insertStmt = conn.createStatement())
	            {
	                for (Car car : rent.getRentedCars())
	                {
	                    String alreadyExisting = "SELECT COUNT(*) FROM cars_rented WHERE renterid = " +rent.getRentID() + " AND carid = " + car.getID();
	                    ResultSet carCheckRs = insertStmt.executeQuery(alreadyExisting);
	                    if (carCheckRs.next() && carCheckRs.getInt(1) > 0) 
	                    {
	                        System.out.println("Entry for renter ID " + rent.getRentID() + 
	                                           " and car ID " + car.getID() + " already exists. Skipped.");
	                        continue;
	                    }
	                    String insertCarSQL = "INSERT INTO cars_rented (renterid, carid, brand) VALUES ("
	                                          + rent.getRentID() + ", " + car.getID() + ", '" + car.getBrand() + "')";
	                    insertStmt.executeUpdate(insertCarSQL);
	                }
	                conn.commit(); // Commit the transaction
	                System.out.println("Updated renter ID: " + rent.getRentID());
	            } 
	        } else {
	            System.out.println("No cars rented by renter ID " + rent.getRentID() + ". No updates made.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error occurred while updating the renter.");
	        e.printStackTrace();
	    }
	}


	
	//Transactions
	public static void saveTransactions(CRMS tran)
	{
		rental_transaction a = tran.getTransactions().get(tran.getTransactions().size()-1);
	    String sql = "INSERT INTO transaction (tranid, carid, renterid, car_type,renter_type) VALUES ("
	                 +a.getTransId() + ", '" 
	                 + a.getCarId()+ ", " 
	                 + a.getRenterId() + ", '" 
	                 + a.getCar_type()+ "','"
	                 +a.getRenter_type()+"')"; 
	    try (Connection conn = DriverManager.getConnection(URL, username, password);
	         Statement stmt = conn.createStatement()) {

	        int rowsInserted = stmt.executeUpdate(sql);
	        if (rowsInserted > 0) 
	            System.out.println("A new transaction was added successfully!");
	    } catch (SQLException e) {
	        System.out.println("Error occurred while adding the transaction.");
	        e.printStackTrace();
	    }
	}
	public static void displayTransactions()
	{
		 String sql = "SELECT *FROM transactions";
		 
		 try (Connection conn = DriverManager.getConnection(URL, username, password);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) 
		 {
	            while (rs.next()) 
	            {
	                int id = rs.getInt("tranid");
	                int carid = rs.getInt("carid");
	                int renterid = rs.getInt("renterid");
	                String car_type = rs.getString("car_type");
	                String renter_type = rs.getString("renter_type");
	                Double totalRentalCost = rs.getObject("totalrentalcost", Double.class); // Using getObject to handle nulls
	                Double damageCost = rs.getObject("damagecost", Double.class);
	                Boolean isInsured = rs.getObject("isInsured", Boolean.class);
	                // Print the data (you can format this as needed)
	                System.out.println("TranID: " + id +
	                                   "\nCarID: " + carid +
	                                   "\nRenterID: " + renterid +
	                                   "\nCar_type: " + car_type +
	                                   "\nRenter_type: " + renter_type+
						                "\nTotal Rental Cost: " + (totalRentalCost != null ? totalRentalCost : "N/A") +
					                    "\nDamage Cost: " + (damageCost != null ? damageCost : "N/A")+
					                    "\nIs Insured: " + (isInsured != null ? (isInsured ? "Available\n" : "Unavailable\n") : "N/A\n"));;
	            }

		 } 
		 catch (SQLException e) {
	            System.out.println("Error occurred while retrieving Transaction .");
	            e.printStackTrace();
		 }
	}
	public static void updateTransactionInsurance()
	{
		
	}
}


