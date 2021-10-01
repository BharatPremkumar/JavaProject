package project;

public class kalkulator {

	/* Globale variabler */
	private int rSize; // Får verdi for romtype
	private int rCap; // Får verdi for romstørrelse

	// tom konstruktør
	public kalkulator() {
		// TODO Auto-generated constructor stub
	}

	// kalkulasjonsfunksjonen
	public int calc(String roomType, String roomCap) {
		// bruker try catch for å slippe NullPointerException
		try {
			if (roomCap == null) {
				rCap = 1;
			} else if (roomCap.equals("Single")) {
				rCap = 1;
			} else if (roomCap.equals("Couple")) {
				rCap = 2;
			} else if (roomCap.equals("Family")) {
				rCap = 4;
			}

			if (roomType == null) {
				rSize = 500;
			} else if (roomType.equals("Economy")) {
				rSize = 500;
			} else if (roomType.equals("Comfort")) {
				rSize = 1000;
			} else if (roomType.equals("Delux")) {
				rSize = 2000;
			}

		} catch (NullPointerException e) {
			System.out.println("Det går bra");
		}

		/*
		 * System.out.println("1  " + rSize); System.out.println("2  " + rCap);
		 */
		// retunerer prisen
		return rSize * rCap;
	}

	public static void main(String[] args) {

	}

}
