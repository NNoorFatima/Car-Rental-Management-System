import java.util.Scanner;

import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 CMS car_management= new CMS();
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
				 System.out.println("4.Exit");
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
						 System.out.println("Enter rental fee");
						 int fee= sc.nextInt();
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
						 System.out.println("Enter rental fee");
						 int fee= sc.nextInt();
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
				 System.out.println("4.Exit");
				 System.out.println("Enter your choice:\n");
				 int choice_2= sc.nextInt();
				 
			 }
			 else if(choice==3)
			 {
				 System.out.println("1.Add new renters");
				 System.out.println("2.Display renter details");
				 System.out.println("3.Remove renter");
				 System.out.println("4.Exit");
				 System.out.println("Enter your choice:\n");
				 int choice_3= sc.nextInt();
			 }
			 else if(choice==4)
			 {
				 break;
			 }
		 }
		 
	}

}