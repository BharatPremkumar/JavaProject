package project;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class TestKalkulator {

String type = "Delux";
String cap = "Single";

String type2 = "Comfort";
String cap2 = "Family";

String type3 = "Economy";
String cap3 = "Couple";

String type4 = "Delux";
String cap4 = "Family";


	kalkulator kalk = new kalkulator();
	kalkulator kalk2 = new kalkulator();
	kalkulator kalk3 = new kalkulator();
	kalkulator kalk4 = new kalkulator();
	
	@Test
	void testKalkulator() {
		kalk.calc(type, cap);
		kalk2.calc(type2, cap2);
		kalk3.calc(type3, cap3);
		kalk4.calc(type4, cap4);
		assertEquals(2000, kalk.calc(type, cap));
		assertEquals(4000, kalk.calc(type2, cap2));
		assertEquals(1000, kalk.calc(type3, cap3));
		assertEquals(8000, kalk.calc(type4, cap4));
		
	}

}
