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
		cars.add(c);
	}
	public void displayCars()
	{
		for(Car a: cars)	//displays only cars that are not rented 
		{
			if(a.getStatus()!=true)
			{
				
				a.displayDetails();
			}
		}
		
	}
	public void removeCar(int ID)
	{
		int index=0;
		
		for (Car a: cars)
		{
			//remove car if its not rented and if it exists 
			if(a.getID()==ID && a.getStatus()==false)
			{
				cars.remove(index);
			}
			else if( a.getStatus()!=false)
			{
				System.out.println("Sorry the car has been rented so can't remove");
			}
			else
			{
				System.out.println("Sorry unable to perform operation");
			}
			index++;
		}
		
		
		
	}
	

}
