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
	                                   ", Brand: " + brand +
	                                   ", Model: " + model +
	                                   ", Year: " + year +
	                                   ", Fee: " + fee +
	                                   ", Status: " + (status == 1 ? "Available" : "Unavailable")+
	                					",Type: "+ type+
	                					",Plate: "+plate);	            }

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
	            System.out.println("Error in removing car");
	            e.printStackTrace();
		 }
		
	}
	public static void updateCar(int id)
	{
//		System.out.println("Choose the car which you want to update\n");
//		displayCars();
		Scanner sc= new Scanner(System.in);
		int choice = id;
		
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
	public static void updateRenter(Renter rent) {
	    String checkRenterSQL = "SELECT COUNT(*) FROM renter WHERE renterid = " + rent.getRentID();

	    try (Connection conn = DriverManager.getConnection(URL, username, password);
	         Statement checkStmt = conn.createStatement();
	         Statement insertStmt = conn.createStatement()) {

	        // Check if renter exists
	        ResultSet rs = checkStmt.executeQuery(checkRenterSQL);
	        if (rs.next() && rs.getInt(1) == 0)
	        {
	            System.out.println("Error: Renter with ID " + rent.getRentID() + " does not exist.");
	            return;
	        }

	        // Begin transaction
	        conn.setAutoCommit(false);

	        if (rent.getRentedCars() != null && !rent.getRentedCars().isEmpty()) 
	        {
	            for (Car car : rent.getRentedCars())
	            {
	                String insertCarSQL = "INSERT INTO cars_rented (renterid, carid, brand) VALUES ("
	                                      + rent.getRentID() + ", " + car.getID() + ", '" + car.getBrand() + "')";
	                insertStmt.executeUpdate(insertCarSQL);
	            }
	            conn.commit(); // Commit the transaction
	            System.out.println("Cars rented by the renter added to cars_rented table.");
	        } else {
	            System.out.println("Renter has no rented cars.");
	        }

	    }
	    catch (SQLException e) {
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


