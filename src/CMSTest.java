import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class CMSTest {
	private CMS cms;	
	@BeforeEach
	void setUp()
	{
		cms= new CMS();
		Car compactCar = new CompactCar("Kia", "Sportage", 2020, "ABC-123", false, 45);
        Car suvCar = new SUV("Honda", "CRV", 2021, "XYZ-789", false, 60);
        Car luxuryCar = new LuxuryCar("BMW", "X5", 2022, "LMN-456", false, 100);
        cms.addCars(luxuryCar);
        cms.addCars(suvCar);
        cms.addCars(compactCar);
	}
	@Test

	
	void testAddCars() {
		Car car = new CompactCar("Hyundai", "Elantra", 2019, "xyz-789", false, 40);
		cms.addCars(car);
		
		List<Car> listCars= cms.getCars();
		assertEquals(4,listCars.size());
		assertEquals("Hyundai",listCars.get(3).getBrand());
		assertEquals("Elantra",listCars.get(3).getModel());
		assertEquals(2019,listCars.get(3).getYear());
		assertEquals("xyz-789",listCars.get(3).getPlate());
		
		
	}


	@Test
	void testRemoveCar() {
		
		cms.removeCar(6);
		//cms.displayCars();
		List<Car> listCar= cms.getCars();
		assertEquals(2,listCar.size());
		System.out.println("Car with id 6 should be removed and car id 4 and 5 should be present\n\n");
		cms.displayCars();
		cms.removeCar(4);
		assertEquals(1,listCar.size());
		
	}
	

}
