package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface fileContainer {

	/* Funksjoner */
	public void readFile() throws FileNotFoundException;
	public void readLastOrder() throws FileNotFoundException;
	void makePerson(Person newPerson) throws IOException;
	
}
