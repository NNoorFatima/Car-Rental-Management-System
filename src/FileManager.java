import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class FileManager 
{
	FileManager()
	{}
	//CARS
	public static void saveCars(Car a,String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true)))
        {
        	 writer.write( a.getID() + ";" + a.getBrand() + ";" + a.getModel() + ";"
                     + a.getYear() + ";" + a.getFee() + ";" + a.getPlate() + ";" + a.getStatus());
             writer.newLine();
        }
	}
	public static void displayCars( String filename) throws IOException 
	{
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
	    {
	    	
	    	String line =reader.readLine();
	    	System.out.println("printing cars data from file");
	    	while(line != null)
	    	{
	    		String[] carData= line.split(";");
	    		int cur_ID= Integer.parseInt(carData[0]);
	    		
	    		
	    		
	    			System.out.println("\nCar ID: " + carData[0]);
	    			System.out.println("Brand: " + carData[1]);
	    			System.out.println("Model: " + carData[2]);
	    			System.out.println("Year: " + carData[3]);
	    			System.out.println("Fee: " + carData[4]);
	    			System.out.println("Status: " + carData[5]);
	    			System.out.println("Plate: " + carData[6]+"\n");
	    			
	    		
	    		line =reader.readLine();
	    	}
	    	
	    	
	    }
	    catch (IOException e) {
	        System.out.println("Error reading file: " + e.getMessage());
	    }
	}
	public static void updateCar(int id, Car car_type, String filename) throws IOException {
        File file = new File(filename);
        List<String> lines = new ArrayList<>();
        boolean found = false;

        // Reading file and storing lines in a list
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] carDetails = line.split(";");
                if (Integer.parseInt(carDetails[0]) == id)
                {
                    // Update the line with the new car details
                    lines.add(car_type.getID() + ";" + car_type.getBrand() + ";" + car_type.getModel() + ";"
                            + car_type.getYear() + ";" + car_type.getFee() + ";" + car_type.getPlate() + ";" + car_type.getStatus());
                    found = true;
                } 
                else 
                {
                    // If it's not the matching car, keep the line as is
                    lines.add(line);
                }
            }
        }

        // If the car was found and updated, write back to the file
        if (found)
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) 
            {
                for (String updatedLine : lines)
                {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
            System.out.println("Car with ID " + id + " has been updated successfully.");
        } 
        else 
        {
            System.out.println("Car with ID " + id + " not found.");
        }
    }
	public static void removeCar(int carId, String filename) throws IOException 
	{
	    File inputFile = new File(filename);
	    File tempFile = new File("tempFile.txt");

	    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) 
	    {

	        String line;
	        boolean found = false;

	        while ((line = reader.readLine()) != null) 
	        {
	            if (Integer.parseInt(line.split(";")[0]) != carId) {
	                writer.write(line);
	                writer.newLine();
	            } 
	            else 
	            {
	                found = true; // Car found and removed
	            }
	        }
	        
	        if (found) 
	        	System.out.println("Car with ID " + carId + " removed.");
	        else 
	        	System.out.println("Car with ID " + carId + " not found.");
	    }

	    if (!inputFile.delete() || !tempFile.renameTo(inputFile)) 
	    {
	        System.out.println("File update failed.");
	    }
	}

	//RENTER
	public static void saveRenter(Renter a,String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true)))
        {
        	 writer.write( a.getRentID() + ";" + a.getName() + ";" + a.getEmail()+ ";"
                     + a.getAddress() + ";" + a.getPh_no());
             writer.newLine();
        }
	}
	public static void displayRenters( String filename) throws IOException 
	{
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
	    {
	    	
	    	String line =reader.readLine();
	    	System.out.println("printing renters data from file");
	    	while(line != null)
	    	{
	    		String[] renterData= line.split(";");
	    		int cur_ID= Integer.parseInt(renterData[0]);
	    			System.out.println("\nRenter ID: " + renterData[0]);
	    			System.out.println("Name: " + renterData[1]);
	    			System.out.println("Email: " + renterData[2]);
	    			System.out.println("Address: " + renterData[3]);
	    			System.out.println("Phone_no: " + renterData[4]+ "\n");
	    			
	    			if (renterData.length > 5) {
	    		        System.out.println("Cars rented by " + renterData[1] + ":");
	    		        for (int i = 5; i < renterData.length; i += 2) {
	    		            if (i + 1 < renterData.length) {
	    		                String carBrand = renterData[i];
	    		                String carID = renterData[i + 1];
	    		                System.out.println("- Car Brand: " + carBrand + ", Car ID: " + carID);
	    		            }
	    		        }
	    			}
	    		line =reader.readLine();
	    	}
	    }
	    catch (IOException e) {
	        System.out.println("Error reading file: " + e.getMessage());
	    }
	}

	public static void removeRenter(int renterId, String filename) throws IOException 
	{
	    File inputFile = new File(filename);
	    File tempFile = new File("tempFile.txt");

	    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) 
	    {

	        String line;
	        boolean found = false;

	        while ((line = reader.readLine()) != null) 
	        {
	            if (Integer.parseInt(line.split(";")[0]) != renterId) {
	                writer.write(line);
	                writer.newLine();
	            } 
	            else 
	            {
	                found = true; 
	            }
	        }
	        
	        if (found) 
	        	System.out.println("Renter with ID " + renterId + " removed.");
	        else 
	        	System.out.println("Renter with ID " + renterId + " not found.");
	    }

	    if (!inputFile.delete() || !tempFile.renameTo(inputFile)) 
	    {
	        System.out.println("File update failed.");
	    }
	}

	public static void updateRenter(Renter a, String filename) throws IOException
	{
		 List<String> renters = new ArrayList<>();
	        boolean found = false;

	        // Read the file and load renters into a list
	        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] renterDetails = line.split(";"); // Assuming CSV format
	                int renterId = Integer.parseInt(renterDetails[0]); // Assuming ID is the first column

	                // Check if the current line corresponds to the renter a
	                if (renterId == a.getRentID()) {
	                    found = true;
	              
	                    String updatedRenter="";
	                   // int size= a.getRentedCars().size();
	                    for(Car car : a.getRentedCars())
	                    {
	                    	updatedRenter=a.getRentID() + ";" + a.getName() + ";" + a.getEmail() + ";" 
	                    +a.getAddress()+";"+a.getPh_no()+ ";"+ car.getBrand()+";"+car.getID();
	                    	//System.out.println(updatedRenter);
	                    }
	                    renters.add(updatedRenter);
	                    System.out.println("Updated\n");
	                } else {
	                    renters.add(line);
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading the file: " + e.getMessage());
	        }
	        if (!found) {
	            String newRenter = a.getRentID() + ";" + a.getName() + ";" + a.getEmail() + ";" +a.getAddress()+";"+a.getPh_no();
	            for (Car car : a.getRentedCars()) {
	                newRenter += ";" + car.getBrand() + ";" + car.getID();
	            }
	            renters.add(newRenter);
	            System.out.println("New renter added: " + newRenter);
	        }

	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
	            for (String renter : renters) {
	                bw.write(renter);
	                bw.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error writing to the file: " + e.getMessage());
	        }	
	}
	//TRANSACTIONS
	public static void saveTransactions(CRMS tran, String filename) throws IOException
	{
	    if (tran != null && !tran.getTransactions().isEmpty()) 
	    {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true)))
	        {
	            rental_transaction a = tran.getTransactions().get(tran.getTransactions().size()-1); // Get the last transaction
	            writer.write(a.getTransId() + ";" + a.getCarId() + ";" + a.getRenterId() + ";" + 
	                         a.getCar_type() + ";" + a.getRenter_type());
	            
	            writer.newLine(); 
	        }
	    }
	}
	
	public static void updateInsuranceTransactions(CRMS tran, String filename) throws IOException
	{
		if (tran != null && !tran.getTransactions().isEmpty()) 
	    {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true)))
	        {
	        	CMS b=tran.getCar_management();
	        	RMS c=tran.getRenter_management();
	            for(rental_transaction a :tran.getTransactions()) // Get the last transaction
	            {
	            	Car ab= b.getCars().get(a.getCarId());
		            String insurance=(ab.isInsurable())?"Insured":"Not insured";
		            writer.write(a.getTransId() + ";" + a.getCarId() + ";" + a.getRenterId() + ";" + 
		                         a.getCar_type() + ";" + a.getRenter_type()+";"+insurance);
		            
		            writer.newLine(); 
	            }
	        }
	    }
	}
	
	public static void updateDamageCostTransactions(CRMS tran, String filename) throws IOException
	{
		if (tran != null && !tran.getTransactions().isEmpty()) 
	    {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true)))
	        {
	        	CMS b=tran.getCar_management();
	        	RMS c=tran.getRenter_management();
	        	
	            for(rental_transaction a :tran.getTransactions()) // Get the last transaction
	            {
	            	Car ab= b.getCars().get(a.getCarId());
		            Renter ba=c.getRenters().get(a.getRenterId());
		            double damagecost= tran.calculateDamageCost(ba, ab);
		            double total_rental_cost=ba.getTotal_rent_fee();
		            String insurance=(ab.isInsurable())?"Insured":"Not insured";
		            writer.write(a.getTransId() + ";" + a.getCarId() + ";" + a.getRenterId() + ";" + 
		                         a.getCar_type() + ";" + a.getRenter_type()+";"+insurance+";"+Double.toString(damagecost)+";"
		                        		 +Double.toString(total_rental_cost));
		            
		            writer.newLine(); 
	            }
	        }
	    }
	}
	
	public static void displayTransactions(String filename)throws IOException 
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
	    {
	    	
	    	String line =reader.readLine();
	    	System.out.println("printing transactions data from file");
	    	while(line != null)
	    	{
	    		String[] transData= line.split(";");
	    		int cur_ID= Integer.parseInt(transData[0]);
	    			System.out.println("\nTransaction ID: " + transData[0]);
	    			System.out.println("Car ID: " + transData[1]);
	    			System.out.println("Renter ID: " + transData[2]);
	    			System.out.println("Car Type: " + transData[3]);
	    			System.out.println("Renter Type: " + transData[4]);
	    			if(transData.length>5)
	    				System.out.println("Insurance: " + transData[5]);
	    			else 
	    				System.out.println("Insurance: N/a");
	    			if(transData.length>6)
	    				System.out.println("Damage Cost: " + transData[6]);
	    			else 
	    				System.out.println("Damage Cost: N/a");
	    			if(transData.length>7)
	    				System.out.println("Total Rental Fee: "+ transData[7]+"\n");
	    			else 
	    				System.out.println("Total Rental Fee: N/a");
	    		line =reader.readLine();
	    	}
	    }
	    catch (IOException e) {
	        System.out.println("Error reading file: " + e.getMessage());
	    }
	}
	public static void removeTransaction(CRMS tran,String filename)throws IOException 
	{
		if (tran == null || tran.getTransactions().isEmpty()) {
	        return; // No transactions to process
	    }
	    
	    // Step 1: Read all the transactions from the file
	    List<String> allTransactions = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        String line = reader.readLine();
	        while (line != null) {
	            allTransactions.add(line); // Store all lines (transactions) from the file
	            line = reader.readLine();
	        }
	    }

	    // Step 2: Check each transaction in the file, compare with tran's transactions, and remove missing ones
	    List<String> updatedTransactions = new ArrayList<>();
	    
	    // Get list of transactions from the CRMS object
	    List<rental_transaction> currentTransactions = tran.getTransactions();
	    
	    for (String fileTransaction : allTransactions) {
	        String[] transData = fileTransaction.split(";");
	        int transId = Integer.parseInt(transData[0]); // Extract transaction ID from the file

	        // Check if the transaction ID exists in the current transaction list from CRMS
	        boolean transactionFound = false;
	        for (rental_transaction trans : currentTransactions) {
	            if (trans.getTransId() == transId) {
	                transactionFound = true;
	                break;
	            }
	        }

	        // If transaction is found in CRMS, keep it, otherwise it's removed
	        if (transactionFound) {
	            updatedTransactions.add(fileTransaction);
	        }
	    }

	    // Step 3: Write the updated list of transactions back to the file
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	        for (String transaction : updatedTransactions) {
	            writer.write(transaction);
	            writer.newLine(); // Write each transaction back to the file
	        }
	    }
		
	}
}
