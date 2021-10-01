package project;

/*Imports to read and write file*/
import java.io.IOException;
import java.time.LocalDate;
import java.io.FileNotFoundException;


/*Imports*/
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class HotelController {

	// lenker til de andre klassene
	Person Person;
	// person-objekt til per fileSaver og HotelController
	fileSaver fileSaver = new fileSaver(this);
	Person person = new Person(this);

	// Får dagens dato
	LocalDate today = LocalDate.now();

	/* Lager variabler for å lage personlig data */
	@FXML
	private TextField name;
	@FXML
	private TextField surename;
	@FXML
	private Label validation1;
	@FXML
	private Label validation2;
	@FXML
	private Label validation3;
	@FXML
	private Label validation4;
	@FXML
	private Label validation5;
	@FXML
	private ImageView img;
	@FXML
	private TextField phone;
	@FXML
	private TextField mail;
	@FXML
	private TextField country;
	@FXML
	private TextField cardNr;
	@FXML
	private TextField cvc;
	@FXML
	private TextArea moreInfo;

	/* Lager variabler for å lage datoer */
	@FXML
	private DatePicker checkIn;
	@FXML
	private DatePicker checkOut;

	/* Lager variabler for å lage romtype */
	@FXML
	private RadioButton eco;
	@FXML
	private RadioButton comfort;
	@FXML
	private RadioButton delux;

	private ToggleGroup RoomType;
	public int RoomPrice;
	private String RoomClass = "Economy";

	/* Lager variabler for å lage romforhold */
	@FXML
	private RadioButton single;
	@FXML
	private RadioButton couple;
	@FXML
	private RadioButton fam;

	private ToggleGroup RoomCap;
	public int RoomSize;
	private String Room = "Single" ;

	/* Lager kolonner til tableView*/
	@FXML
	public TableView<Person> tableView;
	@FXML
	private TableColumn<Person, String> nameColumn;
	@FXML
	private TableColumn<Person, String> phoneNrColumn;
	@FXML
	private TableColumn<Person, String> checkInColumn;
	@FXML
	private TableColumn<Person, String> checkOutColumn;
	@FXML
	private TableColumn<Person, String> roomClassColumn;
	@FXML
	private TableColumn<Person, String> roomTypeColumn;
	@FXML
	private TableColumn<Person, String> priceColumn;
	@FXML
	private TableColumn<Person, String> mailColumn;

	/* KJØR KODEN */
	@FXML
	private void initialize() throws FileNotFoundException {
		validation1.setVisible(false);
		validation2.setVisible(false);
		validation3.setVisible(false);
		validation4.setVisible(false);
		validation5.setVisible(false);
		rooms();
		roomCap();
		checkIn.setValue(person.current);
		checkOut.setValue(person.current);
		table();
		fileSaver.readFile();
	}

	/* henter data fra personklassen som skal sette i tabellen */
	@FXML
	private void table() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("fullName"));
		phoneNrColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNr"));
		checkInColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("checkIn"));
		checkOutColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("checkOut"));
		roomClassColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("roomType"));
		roomTypeColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("roomCap"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("price"));
		mailColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("mail"));

		// setter personene inn i tabellen
		tableView.setItems(fileSaver.getPeople());

		tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	}

	/* legger til reservasjoner i tabellen */
	@FXML
	private void AddRes() throws IOException {

		// konverterer textfield til string
		String Name = name.getText();
		String Phone = phone.getText();
		String Surename = surename.getText();
		LocalDate CheckIn = checkIn.getValue();
		LocalDate CheckOut = checkOut.getValue();
		String Mail = mail.getText();
		String Country = country.getText();
		String Card = cardNr.getText();
		String CVC = cvc.getText();
		

		// Dersom ALLE av valideringene blir godkjent
		if ((project.Person.validateMail(Mail) == true) && (project.Person.validatePhone(Phone) == true)
				&& (project.Person.validateName(Name, Surename) == true)
				&& (project.Person.validateDate(CheckIn, CheckOut, today) == true)
				&& (project.Person.validateBankDetails(Country, Card, CVC) == true)){
			validation1.setVisible(false);
			validation2.setVisible(false);
			validation3.setVisible(false);
			validation4.setVisible(false);
			validation5.setVisible(false);
			img.setVisible(true);

			// lager ny person objekt
			Person newPerson = new Person(Name, Phone, CheckIn, CheckOut, RoomClass, Room, Mail, Country, Card, CVC,
					Surename);

			// regner ut prisen
			newPerson.calculate();

			// kaller på skrive metoden i fileSaver
			fileSaver.makePerson(newPerson);
			clearAll();

		}

		// Dersom INGEN av valideringene blir godkjent
		if ((project.Person.validateMail(Mail) == false) && (project.Person.validatePhone(Phone) == false)
				&& (project.Person.validateName(Name, Surename) == false)
				&& (project.Person.validateDate(CheckIn, CheckOut, today) == false)
				&& (project.Person.validateBankDetails(Country, Card, CVC) == false)) {
			validation1.setVisible(true);
			validation2.setVisible(true);
			validation1.setText("None of the input feild has any valid value");
			validation2.setText("To register an order, input valid values");
			validation3.setVisible(false);
			validation4.setVisible(false);
			validation5.setVisible(false);

			img.setVisible(false);
		} else {
			// dersom enkelte validering ikke er gyldig
			if ((project.Person.validateMail(Mail) == false)) {
				validation1.setVisible(true);
				img.setVisible(false);
				validation1.setText("Invalid Mail, try format: test@gmail.com");
			} else {
				validation1.setVisible(false);
			}

			if (project.Person.validatePhone(Phone) == false) {
				validation2.setVisible(true);
				img.setVisible(false);
				validation2.setText("Invalid Phonenumber, only 8 digit allowd");
			} else {
				validation2.setVisible(false);
			}

			if (project.Person.validateName(Name, Surename) == false) {
				validation3.setVisible(true);
				img.setVisible(false);
				validation3.setText("Invalid name, no digits or symbols allowd");
			} else {
				validation3.setVisible(false);
			}

			if (project.Person.validateDate(CheckIn, CheckOut, today) == false) {
				validation4.setVisible(true);
				img.setVisible(false);
				validation4.setText("Invalid dates. Checkin must be before chekout");
			} else {
				validation4.setVisible(false);
			}

			if (project.Person.validateBankDetails(Country, Card, CVC) == false) {
				validation5.setVisible(true);
				img.setVisible(false);
				validation5.setText(
						"Invalid Transaction info. We accept: Visa, Mastercard, American Express. CVC with 3 digits and Country with no digits or symbols");
			} else {
				validation5.setVisible(false);
			}
		}
	}

	// nullstiller alt når en reservasjonen er ferdig
	public void clearAll() {
		name.setText("");
		surename.setText("");
		phone.setText("");
		mail.setText("");
		country.setText("");
		cvc.setText("");
		cardNr.setText("");
		this.single.setSelected(true);
		this.eco.setSelected(true);
		checkIn.setValue(person.current);
		checkOut.setValue(person.current);
	}

	/* defierer rom */
	private void rooms() {
		try {
			RoomType = new ToggleGroup();
			this.eco.setToggleGroup(RoomType);
			this.comfort.setToggleGroup(RoomType);
			this.delux.setToggleGroup(RoomType);
			this.eco.setSelected(true);
		} catch (NullPointerException e) {
			System.out.println("Controller");
		}
	}

	/* definerer romstørrelse */
	private void roomCap() {
		try {
			RoomCap = new ToggleGroup();
			this.single.setToggleGroup(RoomCap);
			this.couple.setToggleGroup(RoomCap);
			this.fam.setToggleGroup(RoomCap);
			this.single.setSelected(true);
		} catch (NullPointerException e) {
			System.out.println("Controller2");
		}
	}

	/* Gir verdi til romtype */
	public void ChooseRooms() {
		if (this.RoomType.getSelectedToggle().equals(this.eco)) {
			RoomClass = "Economy";
		}

		if (this.RoomType.getSelectedToggle().equals(this.comfort)) {
			RoomClass = "Comfort";
		}

		if (this.RoomType.getSelectedToggle().equals(this.delux)) {
			RoomClass = "Delux";
		}

	}

	/* Gir verdi til romstørrelse */
	public void ChooseRoomCap() {
		
		if (this.RoomCap.getSelectedToggle().equals(this.single)) {
			Room = "Single";
		}

		if (this.RoomCap.getSelectedToggle().equals(this.couple)) {
			Room = "Couple";
		}

		if (this.RoomCap.getSelectedToggle().equals(this.fam)) {
			Room = "Family";
		}
	}

}
