import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 CMS car_management= new CMS();
		 RMS renter_management= new RMS();
		 CRMS rent_transactions= new CRMS(car_management,renter_management);
		 FileManager myStorage=null;
		 JBDCDemo mySqlstorage= null;
		 int storage = 0;
	     String carfilename="dataCar.txt";
	     String renterfilename="dataRenter.txt";
	     String tranfilename="dataTransaction.txt";

	     
	     System.out.println("Choose your storage method,enter (1-3):");
		 System.out.println("1. File-based Storage");
		 System.out.println("2. SQL-based Storage");
		 System.out.println("3. Oracle Storage");
		 //validation 
		 Scanner sc1 = new Scanner(System.in);
	    
		 do {
	            System.out.print("Enter your choice (1-3): ");
	            while (!sc1.hasNextInt()) {  // Check if input is an integer
	                System.out.println("Invalid input. Please enter a number between 1 and 3.");
	                sc1.next();  // Discard invalid input
	            }
	            storage = sc1.nextInt(); // Read user input

	            if (storage < 1 || storage > 3) {
	                System.out.println("Invalid choice. Please select a valid option (1-3).");
	            }
	        }while (storage < 1 || storage > 3); // Loop until valid input
		 //storage choice
		 int strtid;
		 if(storage==1)
		 {
			 myStorage=new FileManager();
			 try {
				 loadDataIntoCRMS(rent_transactions,carfilename,renterfilename,tranfilename);
				 strtid= initializeIdsFromFile(carfilename);
				 Car.setStartingId(strtid); //static method sari oop yaad aagai

				 strtid=initializeIdsFromFile(renterfilename);
				 Renter.setStartingId(strtid);
			//	 initializeIdsFromFile(tranfilename);
			 }
			 catch (IOException  e){	
				System.out.println("Error"); 
			 }
			 
		 }
		 else if(storage==2)
		 {
			 mySqlstorage= new JBDCDemo();
			 addFromSQLToCRMS(rent_transactions);
		 }
		 
		 //MAIN MENUUU
		 while(true)
		 {
			 
			 Scanner sc= new Scanner(System.in);
			 System.out.println("\n\nCar Rental Management System\n");
			 System.out.println("1.Car Management");
			 System.out.println("2.Renter Management");
			 System.out.println("3.Transactions");
			 System.out.println("4.Exit\n");
			
			 int choice= -1;
			 //input validation 
	            do {
	                System.out.println("Enter your choice (1-4):");
	                while (!sc.hasNextInt()) {  // Check if input is an integer
	                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
	                    sc.next();  // Discard invalid input
	                }
	                choice = sc.nextInt();
	                
	                if (choice < 1 || choice > 4) {
	                    System.out.println("Invalid choice. Please select a valid option (1-4).");
	                }
	            } while (choice < 1 || choice > 4);
	          //done input validation 
			 if(choice==1)
			 {
				 System.out.println("1.Add new cars");
				 System.out.println("2.Display available cars");
				 System.out.println("3.Remove Car");
				 System.out.println("4.Update Car");
				 
				 int choice_1=-1;
				 //validate input 
				 do {
			            System.out.println("Enter your choice (1-3):");
			            while (!sc.hasNextInt()) {  // Check if input is an integer
			                System.out.println("Invalid input. Please enter a number between 1 and 3.");
			                sc.next();  // Discard invalid input
			            }
			            choice_1 = sc.nextInt();

			            if (choice_1 < 1 || choice_1 > 3) {
			                System.out.println("Invalid choice. Please select a valid option (1-3).");
			            }
			        } while (choice_1 < 1 || choice_1 > 3);  // Repeat until a valid choice is entered

				 //done validation 
				 Car car_type= null;
				 
				 if(choice_1==1)
				 {
					 
					 System.out.println("Enter if its\n a.CompactCar\n b.SUV\n c.Luxury Car ");
					 char ch=' ';
					 //validation 
				        do {
				            System.out.println("Enter your choice (a, b, or c):");
				            String input = sc.next();  // Get the user's input as a String

				            if (input.length() == 1) {  // Ensure input is a single character
				                ch = input.charAt(0);  // Get the first character
				                ch = Character.toLowerCase(ch);  // Convert to lowercase to handle both cases
				            } else {
				                ch = 'x';  // Invalid input flag
				            }
				            if (ch != 'a' && ch != 'b' && ch != 'c') {
				                System.out.println("Invalid choice. Please enter 'a' for Compact Car, 'b' for SUV, or 'c' for Luxury Car.");
				            }
				        } while (ch != 'a' && ch != 'b' && ch != 'c'); 
					 if(ch=='a')
					 {
						 sc.nextLine();
						 System.out.println("Enter brand");
						 String brand = sc.nextLine();
						 System.out.println("Enter model");
						 String model = sc.nextLine();
						 System.out.println("Enter year");
						 int year = sc.nextInt();
						 sc.nextLine(); // Consume the newline character
						 System.out.println("Enter rental fee");
						 int fee = sc.nextInt();
						 sc.nextLine(); // Consume the newline character
						 System.out.println("Enter plate number");
						 String plate_no = sc.nextLine();
						 car_type = new CompactCar(brand, model, year, plate_no, false, fee);
						 car_management.addCars(car_type);
						 
						 
					 }
					 else if(ch=='b')
					 {
						 sc.nextLine();
						 System.out.println("Enter brand");
						 String brand = sc.nextLine();
						 System.out.println("Enter model");
						 String model = sc.nextLine();
						 System.out.println("Enter year");
						 int year= sc.nextInt();
						 sc.nextLine(); 
						 System.out.println("Enter rental fee");
						 int fee= sc.nextInt();
						 sc.nextLine(); 
						 System.out.println("Enter plate number");
						 String plate_no = sc.nextLine();
						 car_type=new SUV(brand,model,year,plate_no,false,fee);
						 car_management.addCars(car_type);
						 
					 }
					 else if(ch=='c') 
					 {
						 sc.nextLine();
						 System.out.println("Enter brand");
						 String brand = sc.nextLine();
						 System.out.println("Enter model");
						 String model = sc.nextLine();
						 System.out.println("Enter year");
						 int year= sc.nextInt();
						 sc.nextLine(); 
						 System.out.println("Enter rental fee");
						 int fee= sc.nextInt();
						 sc.nextLine(); 
						 System.out.println("Enter plate number");
						 String plate_no = sc.nextLine();
						 car_type=new LuxuryCar(brand,model,year,plate_no,false,fee);
						 car_management.addCars(car_type);
						
					 }
					 
					 if(storage==1)
						 handleCarFile(car_type,carfilename,myStorage,1);
					 else if(storage==2)
						 handleCarMySQL(car_type,mySqlstorage,1);
					 
	 			 }
				 else if(choice_1==2)
				 {
					 
					 System.out.println("\nDisplaying cars from storage method\n");
					 if(storage==1)
						 handleCarFile(car_type,carfilename,myStorage,3);
					 else if(storage==2)
						 handleCarMySQL(car_type,mySqlstorage,3);
					 System.out.println("------------------------------------");
					 System.out.println("\nDisplaying cars from CRMS\n");
					 car_management.displayCars();
				 }
				 else if(choice_1==3)
				 {
					 System.out.println("Enter the id of the car you want to remove");
					 int id= sc.nextInt();
					 for (Car car : car_management.getCars())
					 {
					     if (car.getID() == id)
					     {
					         car_type = car;
					         break;
					     }
					 }

					 if(car_type != null)
					 {
						 car_management.removeCar(id);
						 if(storage==1)
							 handleCarFile(car_type,carfilename,myStorage,4);
					 	 else if(storage==2)
					 		 handleCarMySQL(car_type,mySqlstorage,4);
					 }
					 else 
						 System.out.println("Car with id "+ id+ "doesn't exist");
				 }	
				 else if(choice_1==4)
				 {
					 System.out.println("Enter the id of the car you want to update");
					 int id= sc.nextInt();
					 for (Car car : car_management.getCars())
					 {
					     if (car.getID() == id)
					     {
					         car_type = car;
					         break;
					     }
					 }
					 if(car_type!=null)
					 {
						 if(storage==1)
							 handleCarFile(car_type,carfilename,myStorage,2);
						 else if(storage==2)
							 handleCarMySQL(car_type,mySqlstorage,2);
					 }
				 }
			 }
			 
//========================================================================
			 
			 else if (choice==2)
			 {
				 Renter renter_type =null;
				 System.out.println("1.Add new renters");
				 System.out.println("2.Display renter details");
				 System.out.println("3.Remove renter");
				 System.out.println("4. Update cars rented in storage");
				 //validate 
				 int choice_2=-1;
				 // Validate input for renter management menu
			        do {
			            System.out.println("Enter your choice (1-4):");
			            while (!sc.hasNextInt()) {  // Check if the input is an integer
			                System.out.println("Invalid input. Please enter a number between 1 and 4.");
			                sc.next();  // Discard the invalid input
			            }
			            choice_2 = sc.nextInt();

			            if (choice_2 < 1 || choice_2 > 4) {
			                System.out.println("Invalid choice. Please select a valid option (1-4).");
			            }
			        } while (choice_2 < 1 || choice_2 > 4);
				 //done validation
				 if(choice_2==1)
				 {
					 System.out.println("Enter if its\n a.Regular Renter\n b.Frequent Renter \n c.Corporate Renter ");
					 char ch;
					 do {
				            System.out.println("Enter your choice (a, b, or c):");
				            String input = sc.next();  // Get user's input as a String

				            if (input.length() == 1) {  // Ensure input is a single character
				                ch = input.charAt(0);  // Get the first character
				                ch = Character.toLowerCase(ch);  // Convert to lowercase to handle both cases
				            } else {
				                ch = 'x';  // Invalid input flag
				            }

				            if (ch != 'a' && ch != 'b' && ch != 'c') {
				                System.out.println("Invalid choice. Please enter 'a' for Regular Renter, 'b' for Frequent Renter, or 'c' for Corporate Renter.");
				            }
				        } while (ch != 'a' && ch != 'b' && ch != 'c');
					 if(ch=='a')
					 {
						 sc.nextLine();
						 System.out.println("Enter name");
						 String name = sc.nextLine();
	
						 System.out.println("Enter email");
						 String email = sc.nextLine();
						 
						 System.out.println("Enter phone number");
						 String phone_no = sc.nextLine();
						 
						 System.out.println("Enter address");
						 String add = sc.nextLine();
	
						 renter_type = new RegularRenter(name,email,phone_no,add);
						 renter_management.addRenters(renter_type);
					 }
					 else if(ch=='b')
					 {
						 sc.nextLine();
						 System.out.println("Enter name");
						 String name = sc.nextLine();
	
						 System.out.println("Enter email");
						 String email = sc.nextLine();
						 
						 System.out.println("Enter phone number");
						 String phone_no = sc.nextLine();
						 
						 System.out.println("Enter address");
						 String add = sc.nextLine();
	
						 renter_type = new FrequentRenter(name,email,phone_no,add);
						 renter_management.addRenters(renter_type);
					 }
					 else if(ch=='c')
					 {
						 sc.nextLine();
						 System.out.println("Enter name");
						 String name = sc.nextLine();
	
						 System.out.println("Enter email");
						 String email = sc.nextLine();
						 
						 System.out.println("Enter phone number");
						 String phone_no = sc.nextLine();
						 
						 System.out.println("Enter address");
						 String add = sc.nextLine();
	
						 renter_type = new CorporateRenter(name,email,phone_no,add);
						 renter_management.addRenters(renter_type);
					 }
					 if(storage==1)
						 handleRenterFile(renter_type,renterfilename,myStorage,1);
					 else if(storage==2)
						 handleRenterMySQL(renter_type,mySqlstorage,1);
				 }
				 else if(choice_2==2)
				 {
					 
					 System.out.println("\nDisplaying cars from storage method\n");
					 if(storage==1)
						 handleRenterFile(renter_type,renterfilename,myStorage,3);
					 else if(storage==2)
						 handleRenterMySQL(renter_type,mySqlstorage,3);
					 System.out.println("------------------------------------");
					 System.out.println("Displaying cars from CRMS\n");
					 renter_management.displayRenter();
				 }
				 else if(choice_2==3)
				 {
					 System.out.println("Enter the id of the car you want to remove");
					 int id= sc.nextInt();
					 renter_management.removeRenter(id);

					 for (Renter rent : renter_management.getRenters())
					 {
					     if (rent.getRentID() == id)
					     {
					         renter_type = rent;
					         break;
					     }
					 }
					 if(renter_type!=null)
					 {
						 if(storage==1)
						 	handleRenterFile(renter_type,renterfilename,myStorage,4);
					 	else if(storage==2)
					 		handleRenterMySQL(renter_type,mySqlstorage,4);
					 }
				 } 
				 else if(choice_2==4)
				 {
					 System.out.println("Enter the id of the renter you want to update");
					 int id= sc.nextInt(); 
					 for (Renter rent : renter_management.getRenters())
					 {
					     if (rent.getRentID() == id)
					     {
					         renter_type = rent;
					         break;
					     }
					 }
					 if(renter_type!=null)
					 {
					 if(storage==1)
						 handleRenterFile(renter_type,renterfilename,myStorage,2);
					 else if(storage==2)
						 handleRenterMySQL(renter_type,mySqlstorage,2);
					 }
				 
				}
			 }
//========================================================================

			 else if(choice==3)//transactions 
			 {
				 System.out.println("1.Rent a car");
				 System.out.println("2.Display rental details");
				 System.out.println("3.Total rental Cost");
				 System.out.println("4.Cost with insurance");
				 System.out.println("5.Return Car {with damage cost}");
				// System.out.println("Enter your choice:\n");
				 int choice_3=-1;
				 // Validate input for transaction menu
			     do {
			    	 	System.out.println("Enter your choice (1-5):");
			            while (!sc.hasNextInt()) {  // Check if input is an integer
			                System.out.println("Invalid input. Please enter a number between 1 and 5.");
			                sc.next();  // Discard the invalid input
			            }
			            choice_3 = sc.nextInt();

			            if (choice_3 < 1 || choice_3 > 5) {
			                System.out.println("Invalid choice. Please select a valid option (1-5).");
			            }
			        } while (choice_3 < 1 || choice_3 > 5);

				 
				 if(choice_3==1)
				 {
					 rent_transactions.rentCar();
					 if(storage==1)
						 handleTransactionFile(rent_transactions,tranfilename,myStorage,1);
					 else if(storage==2)
						 handleTransactionMySQL(rent_transactions,mySqlstorage,1);
					 
				 }
				 else if(choice_3==2)
				 {
					 System.out.println("\nDisplaying information from CRMS\n");
					 rent_transactions.displayRentalDetails();
					 System.out.println("\nDisplaying information from storage method\n");
					 if(storage==1)
						 handleTransactionFile(rent_transactions,tranfilename,myStorage,2);
					 else if(storage==2)
						 handleTransactionMySQL(rent_transactions,mySqlstorage,2);
				 }
				 else if (choice_3==3)
				 {
					 rent_transactions.rentCalculation();
					 if(storage==1) //adds in file the damage cost + total rental cost 
						 handleTransactionFile(rent_transactions,tranfilename,myStorage,4);
					 else if(storage==2)
						 handleTransactionMySQL(rent_transactions,mySqlstorage,4);
				 }
				 else if(choice_3==4)
				 {
					 rent_transactions.costWithInsurance();
					 if(storage==1)
						 handleTransactionFile(rent_transactions,tranfilename,myStorage,3);
					 else if(storage==2)
						 handleTransactionMySQL(rent_transactions,mySqlstorage,3);
				 }
				 else if(choice_3==5)
				 {
					 System.out.println("Enter your renter id");
					 int renter_id= sc.nextInt();
					 System.out.println("Enter the id of the car you want to return");
					 int car_id= sc.nextInt();
					 rent_transactions.returnCar(renter_id, car_id);
					 if(storage==1)
						 handleTransactionFile(rent_transactions,tranfilename,myStorage,5);
					 else if(storage==2)
						 handleTransactionMySQL(rent_transactions,mySqlstorage,5);
				 }
			 }
			 else if(choice==4)
			 {
				 break;
			 }
		 }
	}
	//CRUD FILE 
	public static void handleRenterFile(Renter rent, String filename, FileManager storage,int option)
	{
		if(rent==null && option==1)
			return;
		Scanner sc= new Scanner(System.in);
			if(option==1)
			{
				try {
				        storage.saveRenter(rent, filename); // This may throw IOException
				 } 
				 catch (IOException e) {
				        System.out.println("An error occurred while saving the renter data: " + e.getMessage());
				 }
			}
			else if(option==2)
			{				
				//this is where when cars are rented so the update is made
				try{
					storage.updateRenter( rent, filename);	
				}
				catch (IOException e){
					System.out.println("An error occurred while displaying the renter data: " + e.getMessage());
				}
			}
			else if(option ==3)
			{
				try {
					 storage.displayRenters(filename); // This may throw IOException
				 } 
				 catch (IOException e) {
				        System.out.println("An error occurred while displaying the renter data: " + e.getMessage());
				 }
			}
			else if(option==4)
			{
				try {
				    storage.removeRenter(rent.getRentID(), filename); // Remove the car from the file
				} 
				catch (IOException e) {
				    System.out.println("Error occurred while removing the renter: " + e.getMessage());
				}	
			}
	}
	
	public static void handleCarFile(Car car,String filename,FileManager storage,int option)
	{
		if(car==null && option==1)
			return;
		Scanner sc= new Scanner(System.in);		
			if(option==1)
			{
				 try {
				        storage.saveCars(car, filename); 
				 } 
				 catch (IOException e) {
				        System.out.println("An error occurred while saving the car data: " + e.getMessage());
				 }
			}
			else if(option==2)
			{
				//updates method
				
			}
			else if(option==3)
			{
				 try {
					 storage.displayCars(filename); // This may throw IOException
				 } 
				 catch (IOException e) {
				        System.out.println("An error occurred while displaying the car data: " + e.getMessage());
				 }
			}
			else if(option==4)
			{
				try {
				    storage.removeCar(car.getID(), filename); // Remove the car from the file
				} 
				catch (IOException e) {
				    System.out.println("Error occurred while removing the car: " + e.getMessage());
				}
			}
	}
	
	public static void handleTransactionFile(CRMS tran, String filename, FileManager storage,int option )
	{
		if(tran==null)
			return;
		
		Scanner sc= new Scanner(System.in);
			if(option==1) //save basic transaction 
			{
				 try {
				        storage.saveTransactions(tran, filename); // This may throw IOException
				 } 
				 catch (IOException e) {
				        System.out.println("An error occurred while saving the car data: " + e.getMessage());
				 }
			}
			else if(option==2)//display what is stored in file 
			{
				try {
			        storage.displayTransactions(filename); // This may throw IOException
				 } 
				 catch (IOException e) {
				        System.out.println("An error occurred while saving the car data: " + e.getMessage());
				 }
			}
			else if(option==3)//add insurance
			{
				try {
			        storage.updateInsuranceTransactions(tran, filename); // This may throw IOException
				 } 
				 catch (IOException e) {
				        System.out.println("An error occurred while saving the car data: " + e.getMessage());
				 }
			}
			else if(option==4)//add damage cost + rental cost
			{
				try {
			        storage.updateDamageCostTransactions(tran, filename); // This may throw IOException
				 } 
				 catch (IOException e) {
				        System.out.println("An error occurred while saving the car data: " + e.getMessage());
				 }
			}	
			else if(option==5)
			{
				try {
			        storage.removeTransaction(tran, filename); // This may throw IOException
				 } 
				 catch (IOException e) {
				        System.out.println("An error occurred while saving the car data: " + e.getMessage());
				 }
			}
		
	}

	public static void loadDataIntoCRMS(CRMS crms, String carFilename, String renterFilename, String transactionFilename) throws IOException {
	    // CARS
	    try (BufferedReader carReader = new BufferedReader(new FileReader(carFilename)))
	    {
	        String line;
	        while ((line = carReader.readLine()) != null)
	        {
	            String[] carData = line.split(";");
	            if (carData.length >= 8)
	            {
	                int id = Integer.parseInt(carData[0]);
	                String brand = carData[1];
	                String model = carData[2];
	                int year = Integer.parseInt(carData[3]);
	                int fee = Integer.parseInt(carData[4]);
	                String plate = carData[5];
	                boolean status = Boolean.parseBoolean(carData[6]);
	                String type= carData[7];
	                Car car=null;
	                if(type != null && type.equals("Compact Car"))
	                	car = new CompactCar(brand, model, year, plate, status,fee ); 
	                else if(type != null && type.equals("SUV"))
	                	car = new SUV(brand, model, year, plate, status,fee ); 
	                else if(type != null && type.equals("Luxury Car"))
	                	car = new LuxuryCar(brand, model, year, plate, status,fee ); 
	                crms.getCar_management().addCars(car);; 
	            }
	        }
	    } 
	    catch (IOException e) {
	        System.out.println("Error reading cars file: " + e.getMessage());
	    }

	    // RENTERS
	    try (BufferedReader renterReader = new BufferedReader(new FileReader(renterFilename))) 
	    {
	        String line;
	        while ((line = renterReader.readLine()) != null) 
	        {
	            String[] renterData = line.split(";");
	            if (renterData.length >= 6)
	            {
	                int rentId = Integer.parseInt(renterData[0]);
	                String name = renterData[1];
	                String email = renterData[2];
	                String address = renterData[3];
	                String phone = renterData[4];
	                String type = renterData[5];
	                Renter renter=null;
	                if(type != null && type.equals("Regular Renter"))
	                	renter= new RegularRenter( name, email, address, phone); 
	                else if(type != null && type.equals("Frequent Renter"))
	                	renter= new FrequentRenter( name, email, address, phone);
	                else if(type != null && type.equals("Corporate Renter"))
	                	renter= new CorporateRenter(name, email, address, phone);
	                crms.getRenter_management().addRenters(renter); 
	            }
	        }
	    } 
	    catch (IOException e) {
	        System.out.println("Error reading renters file: " + e.getMessage());
	    }
	}
	public static int initializeIdsFromFile(String filename) throws IOException 
	{
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            int maxId = 0;
            while ((line = reader.readLine()) != null) {
                String[] Data = line.split(";");
                if (Data.length > 0)
                {
                    int id = Integer.parseInt(Data[0]);
                    maxId = Math.max(maxId, id); 
                }
            }
            maxId+=1;
            return maxId; // Set nextId to maxId + 1
        }
    }
	
	
	
	
	//CRUD SQL
	public static void handleCarMySQL(Car car,JBDCDemo s,int option)
	{
		if(car==null && option==1)
			return;
		Scanner sc= new Scanner(System.in);
			if(option==1){
				s.saveCars(car);
			}
			else if(option==2){
				s.updateCar(car.getID());
			}
			else if(option==3){
				s.displayCars();
			}
			else if(option==4){
				s.removeCar();
			}
			else if(option==5){
				return;
			}
		
	}
	public static void handleRenterMySQL(Renter rent, JBDCDemo s,int option)
	{
		if(rent==null && option ==1)
			return;
		
		Scanner sc= new Scanner(System.in);
			if(option==1){
				s.saveRenters(rent);
			}
			else if(option==2){
				s.updateRenter(rent);
			}
			else if(option==3)
			{
				s.displayRenters();
			}
			else if(option==4)
			{
				s.removeRenter();
			}
	}
	public static void handleTransactionMySQL(CRMS tran, JBDCDemo s,int option)
	{
		if(tran==null && option ==1)
			return;
		
		Scanner sc= new Scanner(System.in);
			if(option==1){
				
				s.saveTransactions(tran);
			}
			else if(option==2){
				s.displayTransactions();
			}
			else if(option==3)
			{
				
			}
			else if(option==4)
			{
				
			}
			else if(option==5)
			{
			
			}
			
	}
	public static void addFromSQLToCRMS( CRMS crms) 
	{
        Connection conn = null;
        Statement stmtcar = null;
        ResultSet rscar = null;
        Statement stmtrent = null;
        Statement stmtrentedCars = null;
        ResultSet rsrent = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crms_task", "root", "22639646410Aa");
           
            stmtcar = conn.createStatement();
            String sql = "SELECT carid, brand, model, year, status, fee, plate,type FROM car"; 
            rscar = stmtcar.executeQuery(sql);
            //CARS
            while (rscar.next()) 
            {
                String brand = rscar.getString("brand");
                String model = rscar.getString("model");
                int year = rscar.getInt("year");
                boolean status = rscar.getBoolean("status");
                int fee = rscar.getInt("fee");
                String plate = rscar.getString("plate");
                String type= rscar.getString("type");
                Car car=null;
                if(type != null && type.equals("Compact Car"))
                {
                	car = new CompactCar(brand, model,year, plate,status,fee);
                }	
                else if(type != null && type.equals("SUV"))
                {
                	car = new SUV(brand, model, year, plate,status,fee);
                }
                else if(type != null && type.equals("Luxury Car"))
                {
                	car = new LuxuryCar(brand, model, year, plate, status,fee ); 
                }
                crms.getCar_management().addCars(car);; 
            }
         
        //RENTER
            stmtrent = conn.createStatement();
            sql = "SELECT renterid, name, email, address,phone_no,type FROM renter"; 
            rsrent = stmtrent.executeQuery(sql);
            //Renters
            while (rsrent.next()) 
            {
            	int renterid = rsrent.getInt("renterid");
                String name = rsrent.getString("name");
                String email = rsrent.getString("email");
                String address= rsrent.getString("address");
                String phone_no = rsrent.getString("phone_no");
                String type= rsrent.getString("type");
                Renter rent=null;
                if(type != null && type.equals("Regular Renter"))
                {
                	rent = new RegularRenter(name,email,address,phone_no);
                }	
                else if(type != null && type.equals("Frequent Renter"))
                {
                	rent = new FrequentRenter(name,email,address,phone_no);
                }
                else if(type != null && type.equals("Corporate Renter"))
                {
                	rent = new CorporateRenter(name,email,address,phone_no ); 
                }
                crms.getRenter_management().addRenters(rent);; 
                
                stmtrentedCars = conn.createStatement();
                String carsRentedSQL = "SELECT carid, brand FROM cars_rented WHERE renterid = " + renterid;
                ResultSet rsCars = stmtrentedCars.executeQuery(carsRentedSQL);
                List<Car> rentedCars= new ArrayList<>();
                while (rsCars.next()) 
                {
                    int carid = rsCars.getInt("carid");
                    String brand = rsCars.getString("brand");
                    Car rentedCar=null;
                    for(Car car: crms.getCar_management().getCars())
                    {
                    	if(car.getID()==carid)
                    	{
                    		rentedCar=car;
                    		break;
                    	}
                    }
                    rentedCars.add(rentedCar);
                }
                rent.setRentedCars(rentedCars);
                rsCars.close();
            }           
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
            try {
                if (rscar != null) rscar.close();
                if (stmtcar != null) stmtcar.close();
                if (rsrent != null) rsrent.close();
                if (stmtrentedCars != null) stmtrentedCars.close();
                if (stmtrent != null) stmtrent.close();
                if (conn != null) conn.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
	
	//CRUD 

}

