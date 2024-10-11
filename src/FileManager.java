import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
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
	            if (Integer.parseInt(line.split(";")[0]) != carId) 
	            {
	                writer.write(line);
	                writer.newLine();
	            } 
	            else if(Integer.parseInt(line.split(";")[0]) == carId && Boolean.parseBoolean(line.split(";")[6])==false)
	            {
	                found = true; // Car found and removed
	            }
	            else if(Integer.parseInt(line.split(";")[0]) == carId && Boolean.parseBoolean(line.split(";")[6])==true)
	            {
	            	writer.write(line);
	                writer.newLine();
	            }
	        }
	        
	        if (found) 
	        	System.out.println("Car with ID " + carId + " removed.");
	        else 
	        	System.out.println("Car with ID " + carId + " not found or was rented.");
	    }

	    if (!inputFile.delete() || !tempFile.renameTo(inputFile)) 
	    {
	        System.out.println("File update failed.");
	    }
	}
	public static void updateCar(Car car, String filename) throws IOException
	{
		File originalFile = new File(filename);
	    File tempFile = new File("tempfile.txt");
	    
	    try (BufferedReader br = new BufferedReader(new FileReader(filename));
	         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
	        
	        String line;
	        while ((line = br.readLine()) != null)
	        {
	            String[] carData = line.split(";");  
	            int fileCarId = Integer.parseInt(carData[0]);  
	            boolean fileStatus = Boolean.parseBoolean(carData[6]);    
	            boolean updated = false;
	            
	            if (car.getID() == fileCarId)
	            {
	            	boolean crmsSt = car.getStatus();
	            	if (fileStatus != crmsSt) 
	            	{
	            		carData[6] = String.valueOf(crmsSt);  
	            		updated = true;
	            	}	 
	            }
	            
	            if (updated) 
	                System.out.println("Car status updated in the file.");
	            bw.write(String.join(";", carData) + "\n");
	        }
	    }
	    catch (IOException e) {
	    	System.out.println("error");
	        e.printStackTrace();
	    }
	    
	    if (originalFile.delete()) {
	        if (tempFile.renameTo(originalFile)) {
	            System.out.println("File successfully updated.");
	        } else {
	            System.out.println("Error renaming the temp file.");
	        }
	    } else {
	        System.out.println("Error deleting the original file.");
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
	    			if (renterData.length > 6) 
	    			{
	    		        System.out.println("Cars rented by " + renterData[1] + ":");
	    		        for (int i = 6; i < renterData.length; i += 2)
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
	        	String[] renterData=line.split(";");
	            if (Integer.parseInt(renterData[0]) != renterId)
	            {
	                writer.write(line);
	                writer.newLine();
	            } 
	            else if(Integer.parseInt(renterData[0]) == renterId && renterData.length<6)
	                found = true; 
	            else if (Integer.parseInt(renterData[0]) == renterId && renterData.length>=6)
	            {
	                writer.write(line);
	                writer.newLine();
	            } 
	            
	        }
	        
	        if (found) 
	        	System.out.println("Renter with ID " + renterId + " removed.");
	        else 
	        	System.out.println("Renter with ID " + renterId + " not found or has cars rented.");
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

	    try (BufferedReader br = new BufferedReader(new FileReader(filename))) 
	    {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] renterDetails = line.split(";"); 
	            int renterId = Integer.parseInt(renterDetails[0]); 

	            if (renterId == a.getRentID())
	            {
	                found = true;
	                String updatedRenter = a.getRentID() + ";" + a.getName() + ";" + a.getEmail() + ";" 
	                    + a.getAddress() + ";" + a.getPh_no() + ";" + a.displayRenterType();
	                
	                for (Car car : a.getRentedCars()) 
	                    updatedRenter += ";" + car.getBrand() + ";" + car.getID();
	                
	                renters.add(updatedRenter);
	                System.out.println("Updated renter with ID: " + a.getRentID());
	            }
	            else
	                renters.add(line);  
	        }
	    } 
	    catch (IOException e) {
	        System.out.println("Error reading the Renter file: " + e.getMessage());
	    }
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
	        for (String renter : renters) {
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
	        List<String> updatedLines = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
	        {
	            String line;
	            while ((line = reader.readLine()) != null) 
	            {
	                String[] TranData = line.split(";");
	                boolean isUpdated = false;
	                for (rental_transaction a : tran.getTransactions()) 
	                {
	                    if (Integer.parseInt(TranData[0]) == a.getTransId())
	                    { 
	                    	Car cartype=null;
	                    	for(Car car: tran.getCar_management().getCars())
	                    	{
	                    		if(car.getID()==a.getCarId())
	                    		{
	                    			cartype=car;
	                    			break;
	                    		}
	                    	}
	                        String insurance = (cartype != null && cartype.isInsurable()) ? "Insured" : "Not insured";
	                        String updatedLine = String.join(";", TranData) + ";" + insurance;
	                        updatedLines.add(updatedLine);//add this string to file 
	                        isUpdated = true;
	                        break; 
	                    }
	                }
	                if (!isUpdated) 
	                    updatedLines.add(line);
	                
	            }
	        } 
	        catch (IOException e) {
	            System.out.println("Error reading the transactions file: " + e.getMessage());
	        }
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) 
	        {
	            for (String updatedLine : updatedLines) 
	            {
	                writer.write(updatedLine);
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error writing to the transactions file: " + e.getMessage());
	        }
	    }
	}

	public static void updateDamageCostTransactions(CRMS tran, String filename) throws IOException 
	{
	    if (tran != null && !tran.getTransactions().isEmpty()) 
	    {
	        List<String> updatedLines = new ArrayList<>();

	        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
	        {
	            String line;
	            while ((line = reader.readLine()) != null)
	            {
	                String[] TranData = line.split(";");
	                boolean isUpdated = false;
	                for (rental_transaction a : tran.getTransactions()) 
	                {
	                    if (Integer.parseInt(TranData[0]) == a.getTransId()) 
	                    { 
	                    	Car cartype=null;
	                    	for(Car car: tran.getCar_management().getCars() )
	                    	{
	                    		if(car.getID()==a.getCarId())
	                    		{	
	                    			cartype=car;
	                    			break;
	                    		}
	                    	}
	                    	Renter rentertype=null;
	                    	for(Renter rent: tran.getRenter_management().getRenters() )
	                    	{
	                    		if(rent.getRentID()==a.getRenterId())
	                    		{	
	                    			rentertype=rent;
	                    			break;
	                    		}
	                    	}
	                        double damagecost = tran.calculateDamageCost(rentertype, cartype);
	                        double total_rental_cost = rentertype.getTotal_rent_fee();
	                        String insurance = (cartype != null && cartype.isInsurable()) ? "Insured" : "Not insured";
	                        String updatedLine = String.join(";", TranData) 
	                        		+ ";" + insurance + ";" + damagecost + ";" + total_rental_cost;
	                        
	                        updatedLines.add(updatedLine);//add to file
	                        isUpdated = true;
	                        break; 
	                    }
	                }
	                if (!isUpdated) 
	                    updatedLines.add(line);
	                
	            }
	        } 
	        catch (IOException e) {
	            System.out.println("Error reading the transactions file: " + e.getMessage());
	        }
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) 
	        {
	            for (String updatedLine : updatedLines) {
	                writer.write(updatedLine);
	                writer.newLine();
	            }
	        }
	        catch (IOException e) {
	            System.out.println("Error writing to the transactions file: " + e.getMessage());
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
