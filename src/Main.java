import java.util.Scanner;

import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 CMS car_management= new CMS();
		 RMS renter_management= new RMS();
		 Transactions rent_transactions= new Transactions(car_management,renter_management);
		 
		 while(true)
		 {
			 Scanner sc= new Scanner(System.in);
			 System.out.println("Car Rental Management System\n");
			 System.out.println("1.Car Management");
			 System.out.println("2.Renter Management");
			 System.out.println("3.Transactions");
			 System.out.println("4.Exit\n");
			 System.out.println("Enter your choice:\n");
			 int choice= sc.nextInt();
			 if(choice==1)
			 {
				 System.out.println("1.Add new cars");
				 System.out.println("2.Display available cars");
				 System.out.println("3.Remove Car");
				// System.out.println("4.Exit");
				 System.out.println("Enter your choice:\n");
				 int choice_1= sc.nextInt();
				 if(choice_1==1)
				 {
					 System.out.println("Enter if its\n a.CompactCar\n b.SUV\n c.Luxury Car ");
					 char ch= sc.next().charAt(0);
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
				// System.out.println("4.Exit");
				 System.out.println("Enter your choice:\n");
				 int choice_2= sc.nextInt();
				 if(choice_2==1)
				 {
					 System.out.println("Enter if its\n a.Regular Renter\n b.Frequent Renter \n c.Corporate Renter ");
					 char ch= sc.next().charAt(0);
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
				 System.out.println("Enter your choice:\n");
				 int choice_3= sc.nextInt();
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
