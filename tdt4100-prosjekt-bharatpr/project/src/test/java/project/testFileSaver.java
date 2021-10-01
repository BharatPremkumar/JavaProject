package project;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class testFileSaver {

	Person Person;

	LocalDate inDate = LocalDate.parse("2021-04-15");
	LocalDate outDate = LocalDate.parse("2021-04-16");

	Person person = new Person("Bharat", "41375835", inDate, outDate, "Single", "Delux", 2000,
			"bharatpremkumar@gmail.com");
	Person person2 = new Person("Prem", "42375835", inDate, outDate, "Couple", "Comfort", 2000,
			"bharatpremkumar@gmail.com");
	Person person3 = new Person("Kumar", "43375835", inDate, outDate, "Family", "Economy", 2000,
			"bharatpremkumar@gmail.com");

	Person person4 = new Person("Ola", "42283511", inDate, outDate, "Single", "Comfort", 1000, "OlaNordman@gmail.com");
	Person person5 = new Person("Pia", "43092311", inDate, outDate, "Family", "Delux", 8000, "Pia@gmail.com");

	ArrayList<String> personList = new ArrayList<String>();


	@Test
	void testGetPeople() {
		ObservableList<Person> people = FXCollections.observableArrayList();
		people.add(person);
		people.add(person2);
		people.add(person3);

		assertEquals(person, people.get(0));
		assertEquals(person2, people.get(1));
		assertEquals(person3, people.get(2));
	}

	@Test
	void testMakePerson() throws IOException {

		File file = new File(
				"C:\\Users\\bhara\\OneDrive\\Documents\\NTNU\\2_Semester\\tdt4100-prosjekt-bharatpr\\project\\src\\main\\java\\project\\Testorders.txt");
		FileWriter test = new FileWriter(file, true);
		test.write(person.toString() + "\n");
		test.write(person2.toString() + "\n");
		test.write(person3.toString() + "\n");
		test.write(person4.toString() + "\n");
		test.write(person5.toString() + "\n");
		test.close();

		assertEquals(true, file.canWrite());

	}

	@Test
	void testReadFile() throws FileNotFoundException {

		Scanner testfile = new Scanner(new File(
				"C:\\\\Users\\\\bhara\\\\OneDrive\\\\Documents\\\\NTNU\\\\2_Semester\\\\tdt4100-prosjekt-bharatpr\\\\project\\\\src\\\\main\\\\java\\\\project\\\\Testorders.txt"));

		while (testfile.hasNextLine()) {
			List<String> personTest = new ArrayList<String>();
			personTest.add(testfile.nextLine());
			personList.addAll(personTest);
		}

		assertEquals(person.toString(), personList.get(0));
		assertEquals(person2.toString(), personList.get(1));
		assertEquals(person3.toString(), personList.get(2));

	}

	@Test
	void testReadLastOrder() throws FileNotFoundException {


		Scanner testLastLine = new Scanner(new File(
				"C:\\\\Users\\\\bhara\\\\OneDrive\\\\Documents\\\\NTNU\\\\2_Semester\\\\tdt4100-prosjekt-bharatpr\\\\project\\\\src\\\\main\\\\java\\\\project\\\\Testorders.txt"));
		List<String> lastLine = new ArrayList<String>();
		while (testLastLine.hasNextLine()) {
			lastLine.add(testLastLine.nextLine());
		}
		personList.add(lastLine.get(lastLine.size() - 1));
		
		assertEquals(person5.toString(), personList.get(personList.size()-1));


	}

}
