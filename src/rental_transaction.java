
public class rental_transaction{
		private int transId;
	    private int carId;
	    private int renterId;
	    private String renter_type;
	    private String car_type;

	    // Constructor
	    public rental_transaction(int transId, int carId, int renterId,String typec, String typer) 
	    {
	        this.transId = transId;
	        this.carId = carId;
	        this.renterId = renterId;
	        this.renter_type=typer;
	        this.car_type=typec;
	    }

	    // Getters and Setters
	    public int getTransId() {
	        return transId;
	    }

	    public void setTransId(int transId) {
	        this.transId = transId;
	    }

	    public int getCarId() {
	        return carId;
	    }

	    public void setCarId(int carId) {
	        this.carId = carId;
	    }

	    public int getRenterId() {
	        return renterId;
	    }

	    public void setRenterId(int renterId) {
	        this.renterId = renterId;
	    }

		public String getRenter_type() {
			return renter_type;
		}

		public void setRenter_type(String renter_type) {
			this.renter_type = renter_type;
		}

		public String getCar_type() {
			return car_type;
		}

		public void setCar_type(String car_type) {
			this.car_type = car_type;
		}
}