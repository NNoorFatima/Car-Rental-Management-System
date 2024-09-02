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
		int index=0;
		for (Renter a: renters)
		{
			//remove renter if no cars are rented 
			if(a.getRentedCars()==null)
			{
				renters.remove(index);
			}
			else if( a.getRentedCars()!=null)
			{
				System.out.println("Sorry this renter has rented cars so can't remove them");
			}
			else
			{
				System.out.println("Sorry id is not available");
			}
			index++;
		}
		
	}
	public void removeRenter()
	{
		

		int index=0;
		for (Renter a: renters)
		{
			//remove renter if no cars are rented 
			if(a.getRentedCars()==null)
			{
				renters.remove(index);
			}
			else if( a.getRentedCars()!=null)
			{
				System.out.println("Sorry this renter has rented cars so can't remove them");
			}
			else
			{
				System.out.println("Sorry id is not available");
			}
			index++;
		}
	}
	
	
	

}
