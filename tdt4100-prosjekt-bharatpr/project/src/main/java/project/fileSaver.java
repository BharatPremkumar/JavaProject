package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class fileSaver implements fileContainer {

	// lenker til de andre klassene
	Person Person;
	HotelController controll;
	// person-objekt til per fileSaver
	Person person = new Person(this);

	// konstuktør
	public fileSaver(HotelController controll) {
		this.controll = controll;
	}

	/* liste variabel */
	public ArrayList<String> list = new ArrayList<String>();

	// Liste som lagrer person objekter
	ObservableList<Person> people = FXCollections.observableArrayList(Person);

	// legger alle personer som er skrevet inn i en arraylist, og tilpasser den til
	// å kunne sette inn verdier i tableView
	public ObservableList<Person> getPeople() {
		people.clear();
		for (String l : list) {
			/* System.out.println(b.get(0)); */
			List<String> b = Arrays.asList((l.split(",")));
			Person newPerson = new Person((String) b.get(0), (String) b.get(1), (LocalDate.parse(b.get(7))),
					(LocalDate.parse(b.get(8))), (String) b.get(2), (String) b.get(3),
					(Integer.parseInt(b.get(9).replaceAll(" ", ""))), (String) b.get(10));
			people.add(newPerson);

		}
		return people;
	}

	// skriver fil og legger inn person dersom all validering har blitt godkjent. 
	@Override
	public void makePerson(Person newPerson) throws IOException {

		File file = new File(
				"C:\\Users\\bhara\\OneDrive\\Documents\\NTNU\\2_Semester\\tdt4100-prosjekt-bharatpr\\project\\src\\main\\java\\project\\reservations.txt");
		FileWriter w = new FileWriter(file, true);
		w.write(newPerson.toString() + "\n");
		w.close();

		readLastOrder();
	}

	/*
	 * Leser tekstfilen og legger alle personene i en arraylist som blir tilpasset
	 * av getPeople funksjonen. For at man skal få opp filen. Må man skrive inn full filPath
	 */
	@Override
	public void readFile() throws FileNotFoundException {

		Scanner s = new Scanner(new File(
				"C:\\Users\\bhara\\OneDrive\\Documents\\NTNU\\2_Semester\\tdt4100-prosjekt-bharatpr\\project\\src\\main\\java\\project\\reservations.txt"));

		while (s.hasNextLine()) {
			List<String> person = new ArrayList<String>();
			person.add(s.nextLine());
			list.addAll(person);

		}
		getPeople();

	}

	/* Leser tekstfilen og legger til KUN den siste personen i tekstfilen. For at man skal få opp filen. Må man skrive inn full filPath */
	@Override
	public void readLastOrder() throws FileNotFoundException {

		Scanner s2 = new Scanner(new File(
				"C:\\Users\\bhara\\OneDrive\\Documents\\NTNU\\2_Semester\\tdt4100-prosjekt-bharatpr\\project\\src\\main\\java\\project\\reservations.txt"));
		List<String> lastOrder = new ArrayList<String>();
		while (s2.hasNextLine()) {
			lastOrder.add(s2.nextLine());
		}
		list.add(lastOrder.get(lastOrder.size() - 1));
		getPeople();

	}

	public static void main(String[] args) {

	}

}
