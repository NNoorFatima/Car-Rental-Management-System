import java.util.*;

abstract class Car {
	private static int id=1;
	private final int carID;
	private String Brand;
	private String model;
	private int year;
	private boolean rental_status; //if rented then the status is 1 else its 
	private int rental_fee;
	private String plate_no;
	
	
	public static void setStartingId(int startingId) 
	{
        if (startingId > id)
            id = startingId;  
    }
	
	public Car( String b, String m, int y, boolean status, int fee, String plate)
	{
		this.carID=id++;;
		this.Brand=b;
		this.model=m;
		this.year=y;
		this.rental_status=status;
		this.rental_fee=fee;
		this.plate_no=plate;
		
		
		
		
	}
	//getters
	public int getID() 
	{
		return this.carID;
	}
	public String getBrand() 
	{
		return this.Brand;
	}
	public String getModel() 
	{
		return this.model;
	}
	public String getPlate() 
	{
		return this.plate_no;
	}
	public int getFee() 
	{
		return this.rental_fee;
	}
	public boolean getStatus() 
	{
		return this.rental_status;
	}
	public int getYear() 
	{
		return this.year;
	}
	
	//setters
	/*public void setID(int id) 
	{
		this.carID=id;
	}*/
	public void setBrand(String b) 
	{
		this.Brand=b;
	}
	public void setModel(String m) 
	{
		this.model=m;
	}
	public void setPlate(String p) 
	{
		this.plate_no=p;
	}
	public void setFee(int f) 
	{
		this.rental_fee=f;
	}
	public void setStatus(boolean b) 
	{
		this.rental_status=b;
	}
	public void setYear(int u) 
	{
		this.year=u;
	}
	
	//abstract methods
	public abstract double calculateRent(double distance);
	
	public abstract double DamageCost(double a);
    public void displayDetails() {
        System.out.println("Car ID: " + carID);
        System.out.println("Brand: " + Brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Plate Number: " + plate_no);
        System.out.println("Rental Status: " + (rental_status ? "Rented" : "Not Rented"));
        System.out.println("Rental Fee: $" + rental_fee);
    }
	public abstract String displayCarType();
	public abstract boolean isInsurable();
	public abstract int insuranceCost();	
}



//subclasses
class CompactCar extends Car
{
	private static final double per_distance_cost =0.3;
	private static final int damage_percentage=5;
	public static final int insurance_cost=0;

	public CompactCar(String b, String m, int y, String p,boolean Status,int fee)
	{
		 super(b, m, y,Status,fee, p);
	}

	public void displayDetails()
	{
		
        System.out.println("Car Type: Compact Car");
        System.out.println("Features: Basic features, suitable for short-distance travel.");
        super.displayDetails();
        System.out.println("\n");
	}

	//implement logic
	public double calculateRent(double distance)
	{
		//base rent+ distance travelled cost 
		double rent_cost= getFee() + (distance* per_distance_cost);
		
		return rent_cost;
	}
	/////////////
	public double DamageCost(double amt)
	{
		//double a= (damage_percentage/100);
		double damage_cost= amt *  (damage_percentage/100.0);
		return damage_cost;
	}

	public boolean isInsurable()
	{
		return false;
	}
	
	public int insuranceCost()
	{
		return insurance_cost;
	}
	public String displayCarType()
	{
		String type="Compact Car";
		return type;
	}
	
	
}


class SUV extends Car{
	
	private static final double per_distance_cost=0.5;
	public static final int damage_percentage=10;
	public static final int insurance_cost=30;
	public SUV(String b,String m, int y, String p, boolean Status,int fee)
	{
		super(b,m,y,Status,fee,p);
	}
	
	public void displayDetails()
	{
		 System.out.println("Car Type: SUV Car");
		 System.out.println("Features: Spacious, suitable for family trips.");
		 super.displayDetails();
		 System.out.println("\n");
		
	}
	
	public double calculateRent(double distance)
	{
		double total_rent= getFee()+(per_distance_cost*distance);
		return total_rent;
	}

	public double DamageCost(double amt)
	{
		double damage_cost= amt *  (damage_percentage/100.0);
		return damage_cost;
	}
	
	public boolean isInsurable()
	{
		return true;
	}
	
	public int insuranceCost()
	{
		return insurance_cost;
	}
	public String displayCarType()
	{
		String type="SUV Car";
		return type;
	}
	
	
}


class LuxuryCar extends Car{
	
	private static final double per_distance_cost=1.2;
	public static final int damage_percentage=20;
	public static final int insurance_cost=40; 
	public LuxuryCar(String b,String m, int y, String p, boolean Status,int fee)
	{
		super(b,m,y,Status,fee,p);
	}

	public void displayDetails()
	{
		 System.out.println("Car Type: Luxury Car");
		 System.out.println("Features: High-end, suitable for special occasions.");
		 super.displayDetails();
		 System.out.println("\n");
		
		
	}
	
	public double calculateRent(double distance)
	{
		double total_rent= getFee()+(per_distance_cost*distance);
		return total_rent;
	}

	public double DamageCost(double amt )
	{
		double damage_cost= amt *  (damage_percentage/100.0);
		return damage_cost;
	}
	
	public boolean isInsurable()
	{
		return true;
	}
	
	public int insuranceCost()
	{
		return insurance_cost;
	}
	public String displayCarType()
	{
		String type="Luxury Car";
		return type;
	}
	
}


