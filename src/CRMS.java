
import java.util.*;
public class CRMS {
	private static int id_count=1;
	private int trans_ID;
	private CMS car_management;
	private RMS renter_management;
	private List<rental_transaction> transactions;

	public static void setStartingId(int startingId) 
	{
        if (startingId > id_count)
            id_count = startingId;  
    }

	CRMS()
	{}
	CRMS(CMS a,RMS b)
	{
		this.car_management=a;
		this.renter_management=b;
		transactions= new ArrayList<>();
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
				obj.setStatus(false);
				break;
			}
		}
		
	}
	//rental details
	public void displayRentalDetails()
	{
		Iterator<rental_transaction> start= transactions.iterator();
		//System.out.println("displaying all the transactions made");
		while(start.hasNext()==true)
		{
			rental_transaction a = start.next();
			String st=a.getStatus()?"Returned\n":"Rented\n";
			System.out.println("Transaction ID:"+a.getTransId()
			+"\nCar ID: "+a.getCarId()+"\nRenter ID: "+a.getRenterId()
			+ "\nCar Type: "+a.getCar_type()+"\nRenter type: "+ a.getRenter_type()+"\nStatus: "+st);
		}	
	}
	
	//total rent cost
	
	public void rentCalculation()
	{
		Scanner input=new Scanner(System.in);
		double total_cost_renter=0;
		for(Renter a :renter_management.getRenters() )
		{
			total_cost_renter=0;
			for(Car b: a.getRentedCars())
			{
				System.out.println("Enter the distance you have travelled");
				int distance=input.nextInt();
				double car_fee=b.calculateRent(distance);
				double total_rental_cost=a.calculateRate(car_fee);
				total_cost_renter+=total_rental_cost;
				System.out.println("the total rental cost of "+b.displayCarType()+" rented by " + a.getName() + " is "+ total_rental_cost);
			}
			a.setTotal_rent_fee(total_cost_renter);
			System.out.println("The total rental cost {for all cars in list} for renter ID: "+ a.getRentID() +" is "+ total_cost_renter);
			System.out.println("---------------------------------------------------------------------");
		}
	
		
	}
	//add insurance option, if insurable 
	//cost with insurance 
	public void costWithInsurance()
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
				//check if the car is insurable 
				//if yes add in total rent 
				if(b.isInsurable())
				{
					total_rental_cost+=b.insuranceCost();
					System.out.println("the total rental cost of "+b.displayCarType()+"{insurable} rented by " + a.getName() + " is "+ total_rental_cost);
				}
				else 
					System.out.println("the total rental cost of "+b.displayCarType()+" {not insurable} rented by " + a.getName() + " is "+ total_rental_cost);

			}
		}
	}
	
	//damage cost
	public double calculateDamageCost(Renter a, Car b)
	{
		System.out.println("Updation : DAMAGE COST");
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the distance you have travelled");
		int distance=input.nextInt();
		double car_fee=b.calculateRent(distance);
		double total_rental_cost=a.calculateRate(car_fee);
		if(b.isInsurable())
		{
			total_rental_cost+=b.insuranceCost();
		}
		double damage_cost =b.DamageCost(total_rental_cost);
		 
		return damage_cost;
		
	}
	//return car 
	public void returnCar(int renter_id,int car_id)
	{
		Scanner input=new Scanner(System.in);
		for(Renter a :renter_management.getRenters() )
		{
			if(a.getRentID()==renter_id)
			{
				for(Car b: a.getRentedCars())
				{
					if(b.getID()==car_id)
					{
						System.out.println("Returning Car");
						//make car available for other renters 
						b.setStatus(false);
						System.out.println("Is car damaged:");
						String ans=input.nextLine();
						if(ans=="Yes" || ans=="yes")
						{
							double damage_cost=calculateDamageCost(a,b);
							//calculate damage cost 
							if(b.isInsurable())
							{
								//cost if insurable 
								damage_cost-=b.insuranceCost();
								System.out.println("This "+ b.displayCarType()+ " was insurable,damage cost is " + damage_cost);
								System.out.println("-------------------------------------------------------");
								
							}
							else
							{
								//cost if not insurable 
								System.out.println("This "+ b.displayCarType()+ " was not insurable,damage cost is " + damage_cost);
								System.out.println("-------------------------------------------------------");
							}
						}
						trans_ID=id_count++;;
						
						rental_transaction obj= new rental_transaction(trans_ID,car_id,renter_id,b.displayCarType(),a.displayRenterType());
						transactions.add(obj);
						obj.setStatus(true);
						break;
						
					}
				}
			}
		}
	}
	public static int getId_count() {
		return id_count;
	}
	public int getTrans_ID() {
		return trans_ID;
	}
	public CMS getCar_management() {
		return car_management;
	}
	public RMS getRenter_management() {
		return renter_management;
	}
	public List<rental_transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<rental_transaction> transactions) {
		this.transactions = transactions;
	}

}
