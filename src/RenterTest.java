import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

class RenterTest {
	
	private CorporateRenter renter_1;
	private RegularRenter renter_2;
	private FrequentRenter renter_3;
	
	@BeforeEach
	void setUp()
	{
		 renter_1 = new CorporateRenter("Noor","noor.gmail.com","0453911313","G10");
		 renter_2 = new RegularRenter("Sara","sara.gmail.com","0911323213","F11");
		 renter_3 = new FrequentRenter("Arwa","arwa.gmail.com","0113145393","G11");
		
	}
	@Test
	void testCalculateRate() {
		assertEquals(100-(0*100),renter_2.calculateRate(100));
		assertEquals(100-(0.10*100),renter_3.calculateRate(100));
		assertEquals(100-(0.30*100),renter_1.calculateRate(100));
		
	}

	@Test
	void testDisplayRenterType() {
		assertEquals("Regular Renter",renter_2.displayRenterType());
		assertEquals("Frequent Renter",renter_3.displayRenterType());
		assertEquals("Corporate Renter",renter_1.displayRenterType());
		
	}

}
