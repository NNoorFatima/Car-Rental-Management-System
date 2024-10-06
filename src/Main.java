import java.io.IOException;


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
		 if(storage==1)
		 {
			 myStorage=new FileManager();
		 }
		 else if(storage==2)
		 {
			 mySqlstorage= new JBDCDemo();
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
					 System.out.println("\nDisplaying cars from CRMS\n");
					 car_management.displayCars();
					 System.out.println("\nDisplaying cars from storage method\n");
					 if(storage==1)
						 handleCarFile(car_type,carfilename,myStorage,3);
					 else if(storage==2)
						 handleCarMySQL(car_type,mySqlstorage,3);
				 }
				 else if(choice_1==3)
				 {
					 System.out.println("Enter the id of the car you want to remove");
					 int id= sc.nextInt();
					 car_management.removeCar(id);
					 car_type= car_management.getCars().get(id);
					 if(storage==1)
						 handleCarFile(car_type,carfilename,myStorage,4);
					 else if(storage==2)
						 handleCarMySQL(car_type,mySqlstorage,4);
				 }	
				 else if(choice_1==4)
				 {
					 System.out.println("Enter the id of the car you want to remove");
					 int id= sc.nextInt();
					 car_type= car_management.getCars().get(id);
					 if(storage==1)
						 handleCarFile(car_type,carfilename,myStorage,2);
					 else if(storage==2)
						 handleCarMySQL(car_type,mySqlstorage,2);
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
					 System.out.println("Displaying cars from CRMS\n");
					 renter_management.displayRenter();
					 System.out.println("\nDisplaying cars from storage method\n");
					 if(storage==1)
						 handleRenterFile(renter_type,renterfilename,myStorage,3);
					 else if(storage==2)
						 handleRenterMySQL(renter_type,mySqlstorage,3);
				 }
				 else if(choice_2==3)
				 {
					 System.out.println("Enter the id of the car you want to remove");
					 int id= sc.nextInt();
					 renter_management.removeRenter(id);
					 renter_type=renter_management.getRenters().get(id);
					 if(storage==1)
						 handleRenterFile(renter_type,renterfilename,myStorage,4);
					 else if(storage==2)
						 handleRenterMySQL(renter_type,mySqlstorage,4);
				 } 
				 else if(choice_2==4)
				 {
					 System.out.println("Enter the id of the car you want to update");
					 int id= sc.nextInt(); 
					 renter_type=renter_management.getRenters().get(id);
					 if(storage==1)
						 handleRenterFile(renter_type,renterfilename,myStorage,2);
					 else if(storage==2)
						 handleRenterMySQL(renter_type,mySqlstorage,2);
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
					 
				 }
				 else if(choice_3==2)
				 {
					 System.out.println("\nDisplaying information from CRMS\n");
					 rent_transactions.displayRentalDetails();
					 System.out.println("\nDisplaying information from storage method\n");
					 if(storage==1)
						 handleTransactionFile(rent_transactions,tranfilename,myStorage,2);
				 }
				 else if (choice_3==3)
				 {
					 rent_transactions.rentCalculation();
					 if(storage==1) //adds in file the damage cost + total rental cost 
						 handleTransactionFile(rent_transactions,tranfilename,myStorage,4);
				 }
				 else if(choice_3==4)
				 {
					 rent_transactions.costWithInsurance();
					 if(storage==1)
						 handleTransactionFile(rent_transactions,tranfilename,myStorage,3);
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
				Car car_type= null;
				//update car with car id passed in parameter
				System.out.println("Enter the new car details");
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
				 }
	
				int id= car.getID();
				try {
				    storage.updateCar(id, car_type, filename);
				} 
				catch (IOException e) {
				    System.out.println("Error updating the car: " + e.getMessage());
				    e.printStackTrace();
				}
				
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
			else if(option==5)
			{
				return;
			}
	}

	//CRUD 

}

