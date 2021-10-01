package project;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class PersonTest {

	// Diverse datoer
	LocalDate current = LocalDate.now();
	LocalDate inDate = LocalDate.parse("2021-06-15");
	LocalDate outDate = LocalDate.parse("2021-06-16");
	LocalDate pastDate = LocalDate.parse("2021-03-16");

	// Person objekter som skal brukes til testing
	Person person = new Person("Bharat", "41375835", inDate, outDate, "Single", "Delux", "bharatpremkumar@gmail.com",
			"norway", "4234567890111111", "321", "Premkumar");
	Person person2 = new Person("Premkumar", "41255835", inDate, outDate, "Sing!le", "Delux", 2000,
			"premkumar@gmail.com");
	Person person3 = new Person("Prem!umar", "4125835", pastDate, outDate, "Family", "Del1ux", 8000,
			"premkumar@gmailcom");
	Person person4 = new Person("Prem2um ar", "41s5!35", current, current, "Single", "Delux", 2200,
			"premkumargmail.com");
	Person person5 = new Person("Fri!a", "41375800", inDate, outDate, "Single", "Delux", "frida08@gmail.com",
			"United Kingd!m", "4234567890110000", "21", "Ol2en");


	// tester om man får mailen er gyldig
	@Test
	void testValidateMail() {
		Person.validateMail(person.getMail());
		Person.validateMail(person3.getMail());
		Person.validateMail(person4.getMail());

		assertEquals(true, Person.validateMail(person.getMail()));
		assertEquals(false, Person.validateMail(person3.getMail()));
		assertEquals(false, Person.validateMail(person4.getMail()));
	}

	// tester om man får mobilen tilsendt
	@Test
	void testValidatePhone() {
		Person.validatePhone(person.getPhoneNr());
		Person.validatePhone(person3.getPhoneNr());
		Person.validatePhone(person4.getPhoneNr());

		assertEquals(true, Person.validatePhone(person.getPhoneNr()));
		assertEquals(false, Person.validatePhone(person3.getPhoneNr()));
		assertEquals(false, Person.validatePhone(person4.getPhoneNr()));
	}

	// tester om navnet er gyldig
	@Test
	void testValidateName() {
		Person.validateName(person.getFullName(), person.getLastName());
		Person.validateName(person5.getFullName(), person5.getLastName());

		assertEquals(true, Person.validateName(person.getFullName(), person.getLastName())); /* */
		assertEquals(false, Person.validateName(person5.getFullName(), person5.getLastName())); /* */

	}

	// tester om datoer er gyldig
	@Test
	void testValidateDate() {
		Person.validateDate(person.getCheckIn(), person.getCheckOut(), current);
		Person.validateDate(person3.getCheckIn(), person3.getCheckOut(), current);
		Person.validateDate(person4.getCheckIn(), person4.getCheckOut(), current);

		assertEquals(true, Person.validateDate(person.getCheckIn(), person.getCheckOut(), current)); /* */
		assertEquals(false, Person.validateDate(person3.getCheckIn(), person3.getCheckOut(), current)); /* */
		assertEquals(false, Person.validateDate(person4.getCheckIn(), person4.getCheckOut(), current)); /* */

	}

	// tester om bankdetaljer er gyldig
	@Test
	void testValidateBankDetails() {
		Person.validateBankDetails(person.getCountry(), person.getCardNr(), person.getCvc());

		assertEquals(true, Person.validateBankDetails(person.getCountry(), person.getCardNr(), person.getCvc())); /* */
		assertEquals(false, Person.validateBankDetails(person5.getCountry(), person5.getCardNr(), person5.getCvc())); /* */

	}

	// tester om mailen blir tilsendt
	@Test
	void testGetMail() {
		assertEquals("bharatpremkumar@gmail.com", person.getMail());
		assertEquals("premkumar@gmailcom", person3.getMail());
		assertEquals("premkumargmail.com", person4.getMail());
	}

	// tester om navn blir tilsendt
	@Test
	void testGetFullName() {
		assertEquals("Bharat", person.getFullName()); /* */
		assertEquals("Premkumar", person2.getFullName()); /* */
		assertEquals("Prem!umar", person3.getFullName()); /* */
		assertEquals("Prem2um ar", person4.getFullName()); /* */
	}

	// tester om navn blir tilsendt
	@Test
	void testGetLastName() {
		assertEquals("Premkumar", person.getLastName()); /* */
	}

	// tester om navn blir tilsendt
	@Test
	void testSetFullName() {
		person.setFullName("Petter");
		assertEquals("Petter", person.getFullName());
	}

	// tester om phone blir tilsendt
	@Test
	void testGetPhoneNr() {
		assertEquals("41375835", person.getPhoneNr()); /* */
	}

	// tester om phone blir tilsendt
	@Test
	void testSetPhoneNr() {
		person.setPhoneNr("22222222");
		assertEquals("22222222", person.getPhoneNr()); /* */
	}

	// tester om roomtype blir tilsendt
	@Test
	void testGetRoomType() {
		assertEquals("Delux", person.getRoomType()); /* */
	}

	// tester om roomtype blir tilsendt
	@Test
	void testSetRoomType() {
		person.setRoomType("Comfort");
		assertEquals("Comfort", person.getRoomType()); /* */
	}

	// tester om roomcap blir tilsendt
	@Test
	void testGetRoomCap() {
		assertEquals("Single", person.getRoomCap()); /* */
		assertEquals("Sing!le", person2.getRoomCap()); /* */
		assertEquals("Family", person3.getRoomCap()); /* */
		assertEquals("Single", person4.getRoomCap()); /* */
	}

	// tester om roomcap blir tilsendt
	@Test
	void testSetRoomCap() {
		person.setRoomCap("Family");
		assertEquals("Family", person.getRoomCap()); /* */
	}

	// tester om dato blir tilsendt
	@Test
	void testGetCheckIn() {
		assertEquals(LocalDate.parse("2021-06-15"), person.getCheckIn()); /* */
		assertEquals(LocalDate.parse("2021-06-15"), person2.getCheckIn()); /* */
		assertEquals(LocalDate.parse("2021-03-16"), person3.getCheckIn()); /* */
		assertEquals(LocalDate.now(), person4.getCheckIn()); /* */
	}

	// tester om dato blir tilsendt
	@Test
	void testSetCheckIn() {
		person.setCheckIn(LocalDate.parse("2021-06-26"));
		assertEquals(LocalDate.parse("2021-06-26"), person.getCheckIn()); /* */
	}

	// tester om dato blir tilsendt
	@Test
	void testGetCheckOut() {
		assertEquals(LocalDate.parse("2021-06-16"), person.getCheckOut()); /* */
	}

	// tester om dato blir tilsendt
	@Test
	void testSetCheckOut() {
		person.setCheckOut(LocalDate.parse("2021-06-26"));
		assertEquals(LocalDate.parse("2021-06-26"), person.getCheckOut()); /* */
	}

	// tester om pris blir tilsendt
	@Test
	void testSetPrice() {
		person.setPrice(4000);
		assertEquals(4000, person.getPrice());
	}

	// tester om pris blir tilsendt
	@Test
	void testGetPrice() {
		assertEquals(2000, person2.getPrice()); /* */
		assertEquals(8000, person3.getPrice()); /* */
	}

	// tester om socialNr blir tilsendt
	@Test
	void testGetCountry() {
		assertEquals("norway", person.getCountry()); /* */
	}

	// tester om cardNr blir tilsendt
	@Test
	void testGetCardNr() {
		assertEquals("4234567890111111", person.getCardNr()); /* */
	}

	// tester om cvc blir tilsendt
	@Test
	void testGetCvc() {
		assertEquals("321", person.getCvc()); /* */
	}

	// tester om kalkulasjonfunksjon fungerer
	@Test
	void testCalculate() {
		person.calculate();
		assertEquals(8000, person3.getPrice());
	}

	@Test
	void testToString() {
		person.toString();
		person2.toString();
		person3.toString();
		
	}

}
