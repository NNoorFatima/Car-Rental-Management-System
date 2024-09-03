import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

class CRMSTest {

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

	 
	
//	@Test
//	void testRentCar() {
//		 String simulatedInput = "0\n2\n"; // Simulate renter ID 1 and car ID 2
//	     System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
//	     crms.rentCar();
//	     Car rentedCar = carManagementMock.getCars().get(0); // Toyota Corolla (car ID 2)
//	     assertTrue(rentedCar.getStatus());
//	     Renter renter = renterManagementMock.getRenters().get(0); // Noor (renter ID 1)
//	     assertTrue(renter.getRentedCars().contains(rentedCar), "Rented car should be in the renter's list");
//	     // Verify that the transaction is recorded correctly
//	     assertEquals(1, crms.getTransactions().size(), "There should be one transaction");
//	     rental_transaction transaction = crms.getTransactions().get(0);
//	     assertEquals(0, transaction.getCarId());
//	     assertEquals(0, transaction.getRenterId());
//	}

//	@Test
//	void testDisplayRentalDetails() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRentCalculation() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCostWithInsurance() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCalculateDamageCost() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testReturnCar() {
//		fail("Not yet implemented");
//	}

}
