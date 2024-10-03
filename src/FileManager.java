import java.io.*;
import java.io.File;
import java.util.ArrayList;
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
	    		
	    		
	    		
	    			System.out.println("Car ID: " + carData[0]);
	    			System.out.println("Brand: " + carData[1]);
	    			System.out.println("Model: " + carData[2]);
	    			System.out.println("Year: " + carData[3]);
	    			System.out.println("Fee: " + carData[4]);
	    			System.out.println("Status: " + carData[5]);
	    			System.out.println("Plate: " + carData[6]+"\n");
	    			
	    		
	    		line =reader.readLine();
	    	}
	    	
	    	
	    }catch (IOException e) {
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
	
	//TRANSACTIONS
	
}
