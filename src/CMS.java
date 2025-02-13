import java.util.*;
public class CMS {
	private List<Car> cars;
	
	
	public CMS()
	{
		cars= new ArrayList<>();
	}
	public CMS(Car c)
	{
		cars= new ArrayList<>();
		cars.add(c);
	}
	public void addCars(Car c)
	{
		if(c!= null)
			cars.add(c);
	}
	public void displayCars()
	{
		for(Car a: cars)	//displays only cars that are not rented 
		{
			//if(a.getStatus()!=true)
			{
				
				a.displayDetails();
			}
		}
		
	}
	public void removeCar(int ID)
	{
		
		  Iterator<Car> start = cars.iterator();
		  while (start.hasNext())
		  {
		        Car a = start.next();
		        
		       // if (a.getStatus() == false) 
		        if(a.getID()==ID && a.getStatus()==false)
		        { // Check if the renter's ID matches the given ID
		                
		          start.remove(); // Safely remove the renter using the iterator
		          System.out.println("Car has been removed.");
		        }
		        else if(a.getID()==ID && a.getStatus()==true)
		        {
		                System.out.println("Sorry, this car has been rented.");
		        }    
		  	}//
		  

	}
	public void carBasicDetails()
	{
		Iterator<Car> start= cars.iterator();
		System.out.println("displaying all the available renters");
		while(start.hasNext()==true)
		{
			Car a = start.next();
			System.out.println("Car ID: "+a.getID());
			System.out.println("Car Type: "+ a.displayCarType());
			
		
		}
	}
	public List<Car> getCars() {
		return cars;
	}

}
