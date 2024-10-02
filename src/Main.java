import java.util.Scanner;

import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 CMS car_management= new CMS();
		 RMS renter_management= new RMS();
		 CRMS rent_transactions= new CRMS(car_management,renter_management);
		 //main menu
		 while(true)
		 {
			 Scanner sc= new Scanner(System.in);
			 System.out.println("Car Rental Management System\n");
			 System.out.println("1.Car Management");
			 System.out.println("2.Renter Management");
			 System.out.println("3.Transactions");
			 System.out.println("4.Exit\n");
			 //System.out.println("Enter your choice:\n");
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
				 //System.out.println("Enter your choice:\n");
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
				 
				 if(choice_1==1)
				 {
					 System.out.println("Enter if its\n a.CompactCar\n b.SUV\n c.Luxury Car ");
					 char ch=' ';
					// Input validation for car type selection
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
						 Car car_type = new CompactCar(brand, model, year, plate_no, false, fee);
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
						 Car car_type=new SUV(brand,model,year,plate_no,false,fee);
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
						 Car car_type=new LuxuryCar(brand,model,year,plate_no,false,fee);
						 car_management.addCars(car_type);
					 }
	 			 }
				 else if(choice_1==2)
				 {
					 car_management.displayCars();
				 }
				 else if(choice_1==3)
				 {
					 System.out.println("Enter the id of the car you want to remove");
					 int id= sc.nextInt();
					 car_management.removeCar(id);
				 }
				 
				 
			 }
			 else if (choice==2)
			 {
				 System.out.println("1.Add new renters");
				 System.out.println("2.Display renter details");
				 System.out.println("3.Remove renter");
				// System.out.println("Enter your choice:\n");
				 //validate 
				 int choice_2=-1;
				// Validate input for renter management menu
			        do {
			            System.out.println("Enter your choice (1-3):");
			            while (!sc.hasNextInt()) {  // Check if the input is an integer
			                System.out.println("Invalid input. Please enter a number between 1 and 3.");
			                sc.next();  // Discard the invalid input
			            }
			            choice_2 = sc.nextInt();

			            if (choice_2 < 1 || choice_2 > 3) {
			                System.out.println("Invalid choice. Please select a valid option (1-3).");
			            }
			        } while (choice_2 < 1 || choice_2 > 3);
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
	
						/* System.out.println("Enter total rent fee");
						 double t_rental_fee = sc.nextInt();
						 sc.nextLine(); // Consume the newline character*/
						 
						 System.out.println("Enter phone number");
						 String phone_no = sc.nextLine();
						 
						 System.out.println("Enter address");
						 String add = sc.nextLine();
	
						 Renter renter_type = new RegularRenter(name,email,phone_no,add);
						 renter_management.addRenters(renter_type);
					 }
					 else if(ch=='b')
					 {
						 sc.nextLine();
						 System.out.println("Enter name");
						 String name = sc.nextLine();
	
						 System.out.println("Enter email");
						 String email = sc.nextLine();
	
						 /*System.out.println("Enter total rent fee");
						 double t_rental_fee = sc.nextInt();
						 sc.nextLine(); // Consume the newline character*/
						 
						 System.out.println("Enter phone number");
						 String phone_no = sc.nextLine();
						 
						 System.out.println("Enter address");
						 String add = sc.nextLine();
	
						 Renter renter_type = new FrequentRenter(name,email,phone_no,add);
						 renter_management.addRenters(renter_type);
					 }
					 else if(ch=='c')
					 {
						 sc.nextLine();
						 System.out.println("Enter name");
						 String name = sc.nextLine();
	
						 System.out.println("Enter email");
						 String email = sc.nextLine();
	
					/*	 System.out.println("Enter total rent fee");
						 double t_rental_fee = sc.nextInt();
						 sc.nextLine();*/ // Consume the newline character
						 
						 System.out.println("Enter phone number");
						 String phone_no = sc.nextLine();
						 
						 System.out.println("Enter address");
						 String add = sc.nextLine();
	
						 Renter renter_type = new CorporateRenter(name,email,phone_no,add);
						 renter_management.addRenters(renter_type);
					 }
					 
				 }
				 else if(choice_2==2)
				 {
					 renter_management.displayRenter();
				 }
				 else if(choice_2==3)
				 {
					 System.out.println("Enter the id of the car you want to remove");
					 int id= sc.nextInt();
					 renter_management.removeRenter(id);
				 }
				 
			 }
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
				 }
				 else if(choice_3==2)
				 {
					 rent_transactions.displayRentalDetails();
				 }
				 else if (choice_3==3)
				 {
					 rent_transactions.rentCalculation();
				 }
				 else if(choice_3==4)
				 {
					 rent_transactions.costWithInsurance();
				 }
				 else if(choice_3==5)
				 {
					 System.out.println("Enter your renter id");
					 int renter_id= sc.nextInt();
					 System.out.println("Enter the id of the car you want to return");
					 int car_id= sc.nextInt();
					 rent_transactions.returnCar(renter_id, car_id);
				 }
			 }
			 else if(choice==4)
			 {
				 break;
			 }
		 }
		 
	}

}
