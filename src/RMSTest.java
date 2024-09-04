import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class RMSTest {

	RMS rms;
	@BeforeEach
	void setUp()
	{
		rms=new RMS();
		Renter renter_1= new RegularRenter("Noor","noor.email.com","05505050","G10");
		Renter renter_2= new FrequentRenter("Sara","sara.email.com","02225050","F10");
		Renter renter_3= new CorporateRenter("Mary","mary.email.com","055022150","G11");
		rms.addRenters(renter_1);
		rms.addRenters(renter_2);
		rms.addRenters(renter_3);
		
		
	}
	@Test
	void testAddRenters() {
		Renter newrenter= new RegularRenter("Tabidah","tabidah.email.com","055325050","G10");
		rms.addRenters(newrenter);
		List<Renter> rentersList= rms.getRenters();
		
		assertEquals(4,rentersList.size());
		assertEquals("Tabidah",rentersList.get(3).getName());
		assertEquals("tabidah.email.com",rentersList.get(3).getEmail());
		
		
		
	}

	@Test
	void testRemoveRenterInt() {
		rms.removeRenter(6);
		//cms.displayCars();
		List<Renter> list= rms.getRenters();
		assertEquals(2,list.size());
		System.out.println("Renter with id 6 should be removed and renter id 4 and 5 should be present\n\n");
		rms.displayRenter();
		rms.removeRenter(4);
		assertEquals(1,list.size());
	}

//	@Test
//	void testRemoveRenter() {
//		fail("Not yet implemented");
//	}
//


}
