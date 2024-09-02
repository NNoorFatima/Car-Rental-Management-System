
import java.util.*;
public class Transactions {
	private static int id_count=1;
	private int trans_ID;
	private CMS car_management;
	private RMS renter_management;
	private List<rental_transaction> transactions;

	Transactions(CMS a,RMS b)
	{
		this.car_management=a;
		this.renter_management=b;
		transactions= new ArrayList<>();
//		trans_ID=id_count++;
	}
	//rent car to renter 
	
	public void rentCar()
	{
		Scanner input= new Scanner(System.in);
		System.out.println("Please enter renter ID of the renter renting");
		renter_management.renterBasicDetails();
		int renter_id = input.nextInt();
		input.nextLine(); 
		
		System.out.println("Please enter Car ID of the car you want to rent");
		car_management.carBasicDetails();
		int car_id = input.nextInt();
		input.nextLine(); 
		
		
		//find car 
		
		Iterator<Car> start= car_management.getCars().iterator();
		Car a=null;
		while(start.hasNext()==true)
		{
			Car c=start.next();
		//	a = start.next();
			if(c.getID()==car_id)
			{
				if(c.getStatus()==false)
				{
					c.setStatus(true);
					a=c;
					break;
				}
				else
				{
					System.out.println("car already rented");
					return;
				}
			}
			
		}
		if(a==null)
		{
			System.out.println("Car not found");
			return;
		}
		
		//find renter
		Iterator<Renter> s_renter= renter_management.getRenters().iterator();
		while(s_renter.hasNext()==true)
		{
			Renter b = s_renter.next();
			if(b.getRentID()==renter_id)
			{
				b.getRentedCars().add(a);
				trans_ID=id_count++;;
				
				rental_transaction obj= new rental_transaction(trans_ID,car_id,renter_id,a.displayCarType(),b.displayRenterType());
				transactions.add(obj);
				break;
			}
		}
		
	}
	//rental details
	
	public void displayRentalDetails()
	{
		Iterator<rental_transaction> start= transactions.iterator();
		System.out.println("displaying all the transactions made");
		while(start.hasNext()==true)
		{
			rental_transaction a = start.next();
			System.out.println("Transaction ID:"+a.getTransId()+", Car ID: "+a.getCarId()+", Renter ID: "+a.getRenterId()+ ", Car Type: "+a.getCar_type()+", Renter type: "+ a.getRenter_type());
		}	
	}
	
	//total rent cost
	public void rentCalculation()
	{
		Scanner input=new Scanner(System.in);
		for(Renter a :renter_management.getRenters() )
		{
			for(Car b: a.getRentedCars())
			{
				System.out.println("Enter the distance you have travelled");
				int distance=input.nextInt();
				double car_fee=b.calculateRent(distance);
				double total_rental_cost=a.calculateRate(car_fee);
				System.out.println("the total rental cost of "+b.displayCarType()+" rented by " + a.getName() + " is "+ total_rental_cost);

			}
			
		}
		
		
		
		
		
//		Iterator<Car> start= cars.iterator();
//		System.out.println("displaying all the available renters");
//		while(start.hasNext()==true)
//		{
//			Car a = start.next();
//			System.out.println("Car ID: "+a.getID());
//			System.out.println("Car Type: "+ a.displayCarType());
//			
//		
//		}
		
	}
	//add insurance option, if insurable 
	
	//cost with insurance 
	
	//damage cost

}
