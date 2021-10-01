package project;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

	// lenker til de andre klassene
	kalkulator kalk = new kalkulator();
	fileSaver fileSave;
	HotelController controll;

	// globale variabler
	private int price;
	private String roomType, roomCap, fullName, phoneNr, mail, country, cardNr, cvc, lastName;
	private LocalDate checkIn, checkOut;
	LocalDate current = LocalDate.now();

	// konstruktør for Person-objekt
	public Person(String fullName, String phoneNr, LocalDate checkIn, LocalDate checkOut, String roomCap,
			String roomType, String mail, String country, String cardNr, String cvc, String lastName) {

		this.fullName = fullName;
		this.phoneNr = phoneNr;
		this.roomType = roomType;
		this.roomCap = roomCap;
		this.mail = mail;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.cardNr = cardNr;
		this.country = country;
		this.cvc = cvc;
		this.lastName = lastName;

	}

	// konstruktør for Person-objekt
	public Person(String fullName, String phoneNr, LocalDate checkIn, LocalDate checkOut, String roomCap,
			String roomType, int price, String mail) {

		this.fullName = fullName;
		this.phoneNr = phoneNr;
		this.roomType = roomType;
		this.roomCap = roomCap;
		this.checkIn = checkIn;
		this.mail = mail;
		this.checkOut = checkOut;
		this.price = price;

	}

	public Person(fileSaver fileSaver) {
		this.fileSave = fileSaver;
	}

	public Person(HotelController controll) {
		this.controll = controll;
	}

	// Validering av Mail
	public static boolean validateMail(String mail) {
		String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

		Pattern mailPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = mailPattern.matcher(mail);
		return matcher.find();
	}

	// Validering av Telefonnummer
	public static boolean validatePhone(String phone) {
		String regex = "^\\d{8}$";

		Pattern phonePattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = phonePattern.matcher(phone);
		return matcher.find();
	}

	// Validering av Navn
	public static boolean validateName(String name, String surename) {
		String regex = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
		String regexSurename = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){1,24}$";
		

		Pattern namePattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Pattern lastnamePattern = Pattern.compile(regexSurename, Pattern.CASE_INSENSITIVE);
		Matcher matcher = namePattern.matcher(name);
		Matcher lastnamematcher = lastnamePattern.matcher(surename);

		if ((matcher.find() == true) && (lastnamematcher.find() == true)) {
			return true;
		} else {
			return false;
		}

	}

	// Validering av Dato
	public static boolean validateDate(LocalDate checkIn, LocalDate checkOut, LocalDate current) {

		if ((((checkIn).isBefore(checkOut) && (((checkIn).isAfter(current))
				|| (((checkIn).isEqual(current) && ((checkOut).isAfter(current)))))))) {
			return true;
		} else {
			return false;
		}

	}

	// Validering av Bankdetaljer
	public static boolean validateBankDetails(String country, String cardNr, String cvc) {

		String regexCountry = "^([a-zA-Z]+\\s)*[a-zA-Z]+$";
		String regexCard = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|\" +\r\n"
				+ "\"(?<mastercard>5[1-5][0-9]{14})|\" +\r\n"
				+ "\"(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|\" +\r\n"
				+ "\"(?<amex>3[47][0-9]{13})|\" +\r\n"
				+ "\"(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|\" +\r\n"
				+ "\"(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";
		String regexCVC = "^\\d{3}$";

		Pattern CountryPattern = Pattern.compile(regexCountry, Pattern.CASE_INSENSITIVE);
		Pattern cardPattern = Pattern.compile(regexCard, Pattern.CASE_INSENSITIVE);
		Pattern cvcPattern = Pattern.compile(regexCVC, Pattern.CASE_INSENSITIVE);
		Matcher Countrymatcher = CountryPattern.matcher(country);
		Matcher Cardmatcher = cardPattern.matcher(cardNr);
		Matcher CVCmatcher = cvcPattern.matcher(cvc);

		if ((Countrymatcher.find() == true) && (Cardmatcher.find() == true) && (CVCmatcher.find() == true)) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * public static boolean validateRooms(String roomSize, String roomCap) {
	 * 
	 * String regex = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
	 * 
	 * Pattern roomSizePattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	 * Pattern roomCapPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	 * Matcher roomSizematcher = roomSizePattern.matcher(roomSize); Matcher
	 * roomMatchmatcher = roomCapPattern.matcher(roomCap);
	 * 
	 * if ((roomSizematcher.find() == true) && (roomMatchmatcher.find() == true)) {
	 * return true; } else { return false; }
	 * 
	 * }
	 */

	// Henter mail
	public String getMail() {
		return mail;
	}

	// Henter fornavn
	public String getFullName() {
		return fullName;
	}

	// Henter etternavn
	public String getLastName() {
		return lastName;
	}

	void setFullName(String fullName) {
		this.fullName = fullName;
	}

	// Henter telefonnummer
	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

	// Henter romtype
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	// Henter romstørrelse
	public String getRoomCap() {
		return roomCap;
	}

	public void setRoomCap(String roomCap) {
		this.roomCap = roomCap;
	}

	// Henter innsjekkningdato
	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	// Henter utsjekkningsdato
	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// Henter pris
	public int getPrice() {
		return price;
	}

	public static void main(String[] args) {

	}

	// Henter personnr
	public String getCountry() {
		return country;
	}

	// Henter kortnr
	public String getCardNr() {
		return cardNr;
	}

	// Henter cvc
	public String getCvc() {
		return cvc;
	}

	// Kalkulasjonsfunksjon
	public void calculate() {
		kalk.calc(roomType, roomCap);
		this.price = kalk.calc(roomCap, roomType);
		/*
		 * System.out.println(roomType); System.out.println(roomCap);
		 */
	}

	@Override
	public String toString() {
		return fullName + "," + phoneNr + "," + roomType + "," + roomCap + "," + country + "," + cardNr + "," + cvc
				+ "," + checkIn + "," + checkOut + "," + Integer.toString(price) + "," + mail;
	}

}
