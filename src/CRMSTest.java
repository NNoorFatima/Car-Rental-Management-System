import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayInputStream;
import java.util.*;
import org.junit.jupiter.api.Test;
import java.util.*
;class CRMSTest {

	private CMS carManagementMock;
    private RMS renterManagementMock;
    private CRMS crms;
    
    @BeforeEach
    void setUp() {
        // Initialize mocks or test instances for CMS and RMS
        carManagementMock = new CMS(); // Replace with mock if using mocking framework
        renterManagementMock = new RMS(); // Replace with mock if using mocking framework

        // Create an instance of CRMS
        crms = new CRMS(carManagementMock, renterManagementMock);
        
        Car car= new CompactCar("Kia","Sportage",2020,"abc-123",false,45);
        carManagementMock.addCars(car);
        Car car_1= new LuxuryCar("Toyota","Corolla",2022,"afg-123",false,60);
        carManagementMock.addCars(car_1);
        Car car_2= new SUV("Honda","Civic",2021,"gbc-123",false,50);
        carManagementMock.addCars(car_2);
        Renter renter = new RegularRenter("noor","noor.email.com","059018872","G10");
        renterManagementMock.addRenters(renter);
        Renter renter_1 = new FrequentRenter("sara","sara.email.com","03209248293","G11");
        renterManagementMock.addRenters(renter_1);
        Renter renter_2 = new CorporateRenter("tabidah","tabidah.email.com","059011112","F10");
        renterManagementMock.addRenters(renter_2);
        
       
        
        

    }
	@Test
	void testCRMS() {
		assertNotNull(crms);

        // Verify that the car_management and renter_management fields are initialized correctly
        assertEquals(carManagementMock, crms.getCar_management());
        assertEquals(renterManagementMock, crms.getRenter_management());

        // Verify that the transactions list is initialized and empty
        assertNotNull(crms.getTransactions());
        assertTrue(crms.getTransactions().isEmpty());
	}

	 
	
	@Test
	void testRentCar() {
		 String simulatedInput = "10\n10\n"; // Simulate renter ID 1 and car ID 2
	     System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
	     crms.rentCar();
	    // Car rentedCar = carManagementMock.getCars().get(0); // Toyota Corolla (car ID 2)
	     Car rentedCar= crms.getCar_management().getCars().get(0);
	     assertTrue(rentedCar.getStatus());
	     Renter renter = renterManagementMock.getRenters().get(0); // Noor (renter ID 1)
	     assertTrue(renter.getRentedCars().contains(rentedCar), "Rented car should be in the renter's list");
	     // Verify that the transaction is recorded correctly
	     assertEquals(1, crms.getTransactions().size(), "There should be one transaction");
	     rental_transaction transaction = crms.getTransactions().get(0);
	     assertEquals(10, transaction.getCarId());
	     assertEquals(10, transaction.getRenterId());
	}


	@Test
	void testRentCalculation() {
      List<Car> list= new ArrayList<>();
      list.add(crms.getCar_management().getCars().get(1));
      
      crms.getRenter_management().getRenters().get(2).setRentedCars(list);
     // renter_2.setRentedCars(list); // renter with id 6 rented car with id 4	
      crms.getCar_management().getCars().get(1).setStatus(true);
      
		
		System.out.println("\n\n----------This is the test for rent calculation--------------");
		 // Simulate input for distances travelled for each car
        String simulatedInput = "100\n"; // Distances for each car rented by the renters
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        double expectedValue_1=126.0;
        crms.rentCalculation();
        assertEquals(expectedValue_1,crms.getRenter_management().getRenters().get(2).getTotal_rent_fee());
        simulatedInput = "200\n"; // Distances for each car rented by the renters
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        crms.rentCalculation();
        double expectedValue_2=210.0;
        assertEquals(expectedValue_2,crms.getRenter_management().getRenters().get(2).getTotal_rent_fee());
       
	}

	
	
	@Test
	void testReturnCar() {
		System.out.println("\n\n-------------this is return car test case-------------\n");
		String simulatedInput; 
	    //rent car simulation 
		List<Car> list= new ArrayList<>();
		list.add(crms.getCar_management().getCars().get(1));
	      
		crms.getRenter_management().getRenters().get(1).setRentedCars(list);
	     // renter_2.setRentedCars(list); // renter with id 6 rented car with id 4	
		crms.getCar_management().getCars().get(1).setStatus(true);
	     
		simulatedInput = "yes\n"; 
		System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
		crms.returnCar(8, 8);
	     
		boolean status= crms.getCar_management().getCars().get(1).getStatus();
		assertFalse(status);

	     

	}
	
	
	@Test
	void testcalculateDamageCost()
	{
		CRMS newcrms;
		CMS newcar=new CMS();
		RMS newrenter= new RMS();
		newcrms=new CRMS(newcar,newrenter);
		
		
		Renter new_renter= new FrequentRenter("Umema","Umema.email.com","059778812","F11");
		Car new_car= new SUV("Honda","City",2018,"lsc-123",false,90);
		newcar.addCars(new_car);
		newrenter.addRenters(new_renter);
		
		double expectedCost=15.6;
		String simulatedInput;
		simulatedInput = "100\n"; 
		System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
		double actualCost= newcrms.calculateDamageCost(new_renter, new_car);
		assertEquals(expectedCost,actualCost,0.0001);
	}


}
