 import java.util.*;
 
 
 
abstract class Renter {
	private static int id_count=1;
	private int rentID;
	private String name;
	private String email;
	private List<Car> rentedCars;
	private double total_rent_fee;
	private String ph_no;
	private String address;
	
	public static void setStartingId(int startingId) 
	{
        if (startingId > id_count)
        	id_count = startingId;  
    }
	//constructor 
	public Renter(String nm,String em, String ph,String add)
	{
		this.rentID=id_count++;
		this.name=nm;
		this.email=em;
		this.rentedCars= new ArrayList<>();
		this.total_rent_fee=0;
		this.ph_no=ph;
		this.address=add;
		
	}

	public int getRentID() {
		return rentID;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<Car> getRentedCars() {
		return rentedCars;
	}

	public double getTotal_rent_fee() {
		return total_rent_fee;
	}

	public String getPh_no() {
		return ph_no;
	}

	public String getAddress() {
		return address;
	}
	//setters

	/*public void setRentID(int rentID) {
		this.rentID = rentID;
	}*/

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRentedCars(List<Car> rentedCars) {
		this.rentedCars = rentedCars;
	}

	public void setTotal_rent_fee(double total_rent_fee) {
		this.total_rent_fee = total_rent_fee;
	}

	public void setPh_no(String ph_no) {
		this.ph_no = ph_no;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public void displayRenterDetails() {
        System.out.println("Renter ID: " + rentID);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + ph_no);
        System.out.println("Address: " + address);
        System.out.println("Total Rental Fee: " + total_rent_fee);
        System.out.println("Rented Cars: ");
        for (Car car : rentedCars) {
            System.out.println(" - Car ID: " + car.getID()+", Brand: "+car.getBrand());
        }
	
}
	public abstract double calculateRate(double amount);
	public abstract String displayRenterType();

}
//subclasses 
class RegularRenter extends Renter 
{
	private final double discount=0;
	RegularRenter(String nm,String em, String ph,String add)
	{
		super(nm, em,  ph, add);
	}
	public double calculateRate(double amount)
	{
		
		double amt= amount-(discount*amount);
		return amt;
	}
	public void displayRenterDetails()
	{
		System.out.println("type: Regular Renter");
		super.displayRenterDetails();
		System.out.println("\n");
	}
	public String displayRenterType()
	{
        String type="Regular Renter";
        return type;

	}
}


class FrequentRenter extends Renter 
{
	private final double discount=0.10;
	FrequentRenter(String nm,String em, String ph,String add)
	{
		super(nm, em,  ph, add);
	}
	public double calculateRate(double amount)
	{
		
		double amt= amount-(discount*amount);
		return amt;
	}
	public void displayRenterDetails()
	{
		System.out.println("type: Frequent Renter");
		super.displayRenterDetails();
		System.out.println("\n");
	}
	
	public String displayRenterType()
	{
        String type="Frequent Renter";
        return type;

	}
}


class CorporateRenter extends Renter 
{
	private final double discount=0.30;
	CorporateRenter(String nm,String em, String ph,String add)
	{
		super(nm, em,  ph, add);
	}
	public double calculateRate(double amount)
	{
		
		double amt= amount-(discount*amount);
		return amt;
	}
	public void displayRenterDetails()
	{
		System.out.println("type: Corporate Renter");
		super.displayRenterDetails();
		System.out.println("\n");
	}
	public String displayRenterType()
	{
        String type="Corporate Renter";
        return type;

	}
}