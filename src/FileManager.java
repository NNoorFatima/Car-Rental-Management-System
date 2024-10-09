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
                     + a.getYear() + ";" + a.getFee() + ";" + a.getPlate() + ";" + a.getStatus()+ ";"+ a.displayCarType());
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
	    			System.out.println("Plate: " + carData[6]);
	    			System.out.println("Type: " + carData[7]+"\n");
	    			
	    		
	    		line =reader.readLine();
	    	}
	    	
	    	
	    }
	    catch (IOException e) {
	        System.out.println("Error reading file: " + e.getMessage());
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
                     + a.getAddress() + ";" + a.getPh_no()+ ";"+a.displayRenterType());
             writer.newLine();
        }
	}
	public static void displayRenters( String filename) throws IOException 
	{
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
	    {
	    	
	    	String line =reader.readLine();
	    	while(line != null)
	    	{
	    		String[] renterData= line.split(";");
	    		int cur_ID= Integer.parseInt(renterData[0]);
	    			System.out.println("\nRenter ID: " + renterData[0]);
	    			System.out.println("Name: " + renterData[1]);
	    			System.out.println("Email: " + renterData[2]);
	    			System.out.println("Address: " + renterData[3]);
	    			System.out.println("Phone_no: " + renterData[4]);
	    			System.out.println("Renter_Type: " + renterData[5]+ "\n");
	    			if (renterData.length > 6) {
	    		        System.out.println("Cars rented by " + renterData[1] + ":");
	    		        for (int i = 5; i < renterData.length; i += 2)
	    		        {
	    		            if (i + 1 < renterData.length) 
	    		            {
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
	                found = true; 
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
	        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
	        {
	            String line;
	            while ((line = br.readLine()) != null)
	            {
	                String[] renterDetails = line.split(";"); 
	                int renterId = Integer.parseInt(renterDetails[0]); // Assuming ID is the first column

	                // Check if the current line corresponds to the renter a
	                if (renterId == a.getRentID()) 
	                {
	                    found = true;
	              
	                    String updatedRenter="";
	                    for(Car car : a.getRentedCars())
	                    {
	                    	updatedRenter=a.getRentID() + ";" + a.getName() + ";" + a.getEmail() + ";" 
	                    +a.getAddress()+";"+a.getPh_no()+ ";"+a.displayRenterType()
	                    			+";"+ car.getBrand()+";"+car.getID();
	                    	//System.out.println(updatedRenter);
	                    }
	                    renters.add(updatedRenter);
	                    System.out.println("Updated\n");
	                } 
	                else 
	                    renters.add(line);
	                
	            }
	        } 
	        catch (IOException e) {
	            System.out.println("Error reading the file: " + e.getMessage());
	        }
	        if (!found) //first entry in the file 
	        {
	            String newRenter = a.getRentID() + ";" + a.getName() + ";" + a.getEmail() + ";" +a.getAddress()+";"+a.getPh_no()+";"+a.displayRenterType();
	            for (Car car : a.getRentedCars()) 
	                newRenter += ";" + car.getBrand() + ";" + car.getID();
	            
	            renters.add(newRenter);
	            System.out.println("New renter added: " + newRenter);
	        }

	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) 
	        {
	            for (String renter : renters) 
	            {
	                bw.write(renter);
	                bw.newLine();
	            }
	        } 
	        catch (IOException e) {
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
	            for(rental_transaction a :tran.getTransactions()) //llop through all transactions 
	            {
	            	Car ab= b.getCars().get(a.getCarId()-1);
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
	            	Car ab= b.getCars().get(a.getCarId()-1);
		            Renter ba=c.getRenters().get(a.getRenterId()-1);
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
		//basically finds the transaction that it cannot find in the CRMS and removes that transaction
		if (tran == null || tran.getTransactions().isEmpty()) 
	        return; 
	   
	    List<String> transactions = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
	    {
	        String line = reader.readLine();
	        while (line != null)
	        {
	            transactions.add(line); 
	            line = reader.readLine();
	        }
	    }
	    List<String> newTransactions = new ArrayList<>();
	    List<rental_transaction> currentTransactions = tran.getTransactions();
	    
	    for (String fileTransaction : transactions) 
	    {
	        String[] transData = fileTransaction.split(";");
	        int transId = Integer.parseInt(transData[0]); 
	        boolean found = false;
	        for (rental_transaction trans : currentTransactions) // find this tran_id in CRMS
	        {
	            if (trans.getTransId() == transId)
	            {
	                found = true;
	                break;
	            }
	        }
	        if (found)
	            newTransactions.add(fileTransaction);   
	    }
	    // write new transaction to the file
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) 
	    {
	        for (String transaction : newTransactions)
	        {
	            writer.write(transaction);
	            writer.newLine(); 	        }
	    }
		
	}
}
