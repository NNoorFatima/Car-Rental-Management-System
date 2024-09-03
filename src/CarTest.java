import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

class CarTest {

	private CompactCar compactCar;
    private SUV suvCar;
    private LuxuryCar luxuryCar;
    @BeforeEach
    void setUp()
    {
    	 compactCar = new CompactCar("Kia", "Sportage", 2020, "ABC-123", false, 45);
         suvCar = new SUV("Honda", "CRV", 2021, "XYZ-789", false, 60);
         luxuryCar = new LuxuryCar("BMW", "X5", 2022, "LMN-456", false, 100);
    }

	@Test
	void testCalculateRent() {
		 assertEquals(45 + 100 * 0.3, compactCar.calculateRent(100), 0.001);
		 assertEquals(60 + 100 * 0.5, suvCar.calculateRent(100), 0.001);
		 assertEquals(100 + 100 * 1.2, luxuryCar.calculateRent(100), 0.001);
	}

	@Test
	void testDamageCost() {
		 // Testing damage cost calculation for different car types
		//amt*(damage%)
		//the answer is supposed to be 100*0.05, the second var return how the actual function runs and the third variable is tolerance
        assertEquals(100 * 0.05, compactCar.DamageCost(100));
        assertEquals(100 * 0.10, suvCar.DamageCost(100));
        assertEquals(100 * 0.20, luxuryCar.DamageCost(100));
	}

}
