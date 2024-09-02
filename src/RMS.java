import java.util.*;

public class RMS {
	
	private List<Renter> renters;
	
	RMS()
	{
		renters= new ArrayList<>();	
	}
	RMS(Renter r)
	{
		renters= new ArrayList<>();	
		renters.add(r);
	}
	public void addRenters(Renter r)
	{
		renters.add(r);
	}
	public void displayRenter()
	{
		for(Renter a: renters)	//displays renters 
		{
			a.displayRenterDetails();
		}
	}
	public void removeRenter(int ID) //removing renters using specific id 
	{
		Iterator<Renter> start= renters.iterator();
		
		while(start.hasNext()==true)
		{
			Renter a = start.next();
			if(a.getRentID()==ID)
			{
				List<Car> cars= a.getRentedCars();
				if(cars.isEmpty())
				{
					start.remove();
					System.out.println("Renter removed");
				}
				else if(cars.isEmpty()!= true)
				{
					System.out.println("Sorry the renter has rented cars");
				}
				
			
			}
		}		
		
	}
	public void removeRenter()
	{
		Iterator<Renter> start= renters.iterator();
		
		while(start.hasNext()==true)
		{
			Renter a = start.next();
		
			List<Car> cars= a.getRentedCars();
			if(cars.isEmpty())
			{
				start.remove();
				System.out.println("Renter removed");
			}
			else if(cars.isEmpty()!= true)
			{
				System.out.println("Sorry the renter has rented cars");
			}		
		}
	}
	
	
	

}
