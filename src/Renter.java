 import java.util.*;
 
 
 
abstract class Renter {
	private int rentID;
	private String name;
	private String email;
	private List<Car> rentedCars;
	private double total_rent_fee;
	private String ph_no;
	private String address;
	
	//constructor 
	public Renter(int id,String nm,String em,double rent_fee, String ph,String add)
	{
		this.rentID=id;
		this.name=nm;
		this.email=em;
		this.rentedCars= new ArrayList<>();
		this.total_rent_fee=rent_fee;
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

	public void setRentID(int rentID) {
		this.rentID = rentID;
	}

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
            System.out.println(" - " + car.getModel());
        }
	
}
	
}

class RegularRenter extends Renter 
{
	RegularRenter(int id,String nm,String em,double rent_fee, String ph,String add)
	{
		super(id,nm, em, rent_fee,  ph, add);
	}

}