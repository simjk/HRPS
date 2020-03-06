package control;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import boundary.GuestBoundary;
import control.MainControl;
import entity.Guest;
import entity.Guest.IdType;

/**
 * <h1>GuestControl</h1>
 * <p>
 * The program implements methods that create, update, search guest detail.
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */
public class GuestControl implements Serializable, Control {

	Guest g = new Guest();
	readWriteFile rwf = new readWriteFile();
	Scanner in = new Scanner(System.in);

	/**
	 * <h1>GuestControl</h1>
	 * <p>
	 * This method is used to call other GuestControl methods in other classes
	 * </p>
	 */
	public GuestControl() {
	}

	/**
	 * <h1>process</h1>
	 * <p>
	 * This method call selection() method
	 * </p>
	 */
	public void process() {
		selection();
	}

	/**
	 * <h1>selection</h1>
	 * <p>
	 * This method uses a simple textScanner to parse primitive types and strings
	 * into guestInterface method.
	 * </p>
	 */
	public void selection() {
		int choice = 0;
		String input;

		System.out.println("------------------------------------------------------------");
		System.out.println("==================== HRPS: Guest System ====================");
		System.out.println("------------------------------------------------------------");

		System.out.println("1. Create Guest" + "\n2. Update Guest" + "\n3. Search Guest" + "\n0. Return to Main Menu");
		System.out.println("------------------------------------------------------------");

		// Validation for numeric inputs
		if (in.hasNextInt()) {
			choice = in.nextInt();
			if (choice >= 0 && choice <= 3)
				guestInterface(choice);
			else {
				System.out.println("Wrong input, please input the correct value!");
				selection();
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			selection();
		}
		in.close();
	}

	/**
	 * <h1>guestInterface</h1>
	 * <p>
	 * guestInterface take in {@value control#choice} and call other methods.
	 * </p>
	 * 
	 * @param choice
	 *            - choose between create/update/guest
	 */
	private void guestInterface(int choice) {
		switch (choice) {
		case 1:
			createGuest("guest");
			System.out.println("Enter 0 to return to Guest System Menu");
			int choice1 = in.nextInt();
			if (choice1 == 0)
				selection();
			break;
		case 2:
			updateGuest2();
			System.out.println("Enter 0 to return to Guest System Menu");
			int choice2 = in.nextInt();
			if (choice2 == 0)
				selection();
			break;
		case 3:
			searchGuest("guest");
			System.out.println("Enter 0 to return to Guest System Menu");
			int choice3 = in.nextInt();
			if (choice3 == 0)
				selection();
			break;
		case 0:
			MainControl.displayUI();
			break;
		default:
			System.out.println("Wrong input. Please input values within no. 0 - 3 only!\n");
			selection();
			break;
		}
	}

	/**
	 * <h1>createGuest</h1>
	 * <p>
	 * This method call other method to create all related information of guest
	 * </p>
	 * 
	 * @param from
	 *            - Contains where the method called from
	 * 
	 * @return list - guest's information
	 */
	public ArrayList<Guest> createGuest(String from) {
		int choice = 999999999;
		System.out.println("------------------------------------------------------------");
		System.out.println("=================== Creating Guest System ==================");
		System.out.println("------------------------------------------------------------");

		createName(from);
		createIdType(); // Prompt user to choose between Passport and Driving License
		createIdentity();
		System.out.println();
		in.nextLine(); // dummy scan
		createAddress();
		createCountry();
		createGender();
		createNationality();
		createContactNum();
		createCreditCardNum();

		ArrayList<Guest> list = new ArrayList<Guest>();
		list = displayGuestDetail("create", choice);
		return list;
	}

	// ===========================================================================================================================================================//
	// GUEST CREATE
	// ===========================================================================================================================================================//
	/**
	 * <h1>createName</h1>
	 * <p>
	 * {@code private void createName()}
	 * </p>
	 * <p>
	 * This method validate and set guest's name.
	 * </p>
	 */
	private void createName(String from) {
		System.out.println("Name: (Eg. Jackie Chan)");

		if (!from.contentEquals("walkin"))
			in.nextLine();
		String name = in.nextLine();
		if (Pattern.matches("^[A-Za-z\\s]+$", name)) {
			g.setName(name);
			System.out.println();
		} else {
			System.out.printf("\"%s\" is not a valid name.\n", name);
			System.out.println("Please try again!");
			System.out.println();
			createName(from);
		}
	}

	/**
	 * <h1>createIdType</h1>
	 * <p>
	 * {@code private void createIdType()}
	 * </p>
	 * <p>
	 * This method validate and set guest's identity type.
	 * </p>
	 * 
	 * @exception InputMismatchException
	 *                - wrong input
	 */
	private void createIdType() {
		int choice = 0;
		String input;

		System.out.println("Types of ID: " + "\n1. Passport" + "\n2. Driving License");

		try {
			if (!in.hasNextInt()) {
				input = in.nextLine();
				System.out.printf("\"%s\" is not a valid digit.\n\n", input);
				createIdType();
			}
			choice = in.nextInt();

			switch (choice) {
			case 1:
				g.setIdtype(IdType.PASSPORT);
				System.out.println();
				break;
			case 2:
				g.setIdtype(IdType.DRIVING_LICENSE);
				System.out.println();
				break;
			default:
				System.out.println("Wrong Input, Please Key in No. 1 - 2 Only!\n");
				createIdType();
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Wrong input. Please input values within no. 1 - 2 only!\n");
			createIdType();
		}
	}

	/**
	 * <h1>createIdentity</h1>
	 * <p>
	 * {@code private void createIdentity()}
	 * </p>
	 * <p>
	 * This method validate and set guest's identity.
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find guest.dat
	 */
	private void createIdentity() { // Type first time wrong = Restart
		boolean check = false;

		System.out.println("Identity: (Eg. S1234567A)");
		String identity = in.next();

		try {
			ArrayList<Guest> guestlist = rwf.readGuestFile();
			do {
				if ((identity.length() == 9) && (Character.toUpperCase(identity.charAt(0)) == 'S')
						&& (Character.isLetter(identity.charAt(8)))) {
					for (int i = 1; i < 8; i++) {
						if (!Character.isDigit(identity.charAt(i))) {
							System.out.printf("\"%s\" is not a valid input.\n", identity);
							System.out.println("Please try again!");
							System.out.println();
							createIdentity();
						}
					}
					for (int j = 0; j < guestlist.size(); j++) {
						Guest gst = guestlist.get(j);
						if (identity.matches(gst.getIdentity())) {
							System.out.println("You have entered a duplicate identity number. Please enter again.");
							System.out.println();
							check = true;
							createIdentity();
						} else
							check = false;
					}
					g.setIdentity(identity);
					break;
				} else {
					System.out.printf("\"%s\" is not a valid input.\n", identity);
					System.out.println("Please try again!");
					System.out.println();
					// createIdentity();
				}
			} while (!check);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>createAddress</h1>
	 * <p>
	 * {@code private void createAddress()}
	 * </p>
	 * <p>
	 * This method validate and set guest's address.
	 * </p>
	 */
	private void createAddress() {
		System.out.println("Address: (Eg. 50 Nanyang Ave, 639798)");
		String address = in.nextLine();
		g.setAddress(address);
		System.out.println();
	}

	/**
	 * <h1>createCountry</h1>
	 * <p>
	 * {@code private void createCountry()}
	 * </p>
	 * <p>
	 * This method validate and set guest's country.
	 * </p>
	 */
	private void createCountry() {
		System.out.println("Country: (Eg. Singapore)");
		String country = in.next();
		if (Pattern.matches("^[A-Za-z\\s]+$", country)) {
			g.setCountry(country);
			System.out.println();
		} else {
			System.out.printf("\"%s\" is not a valid input.\n", country);
			System.out.println("Please try again!");
			System.out.println();
			createCountry();
		}
	}

	/**
	 * <h1>createGender</h1>
	 * <p>
	 * {@code private void createGender()}
	 * </p>
	 * <p>
	 * This method validate and set guest's gender.
	 * </p>
	 */
	private void createGender() {
		System.out.println("Gender: (Eg. Female)");
		String gender = in.next();

		if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("male")) {
			g.setGender(gender.toUpperCase());
			System.out.println();

		} else {
			System.out.printf("\"%s\" is not a valid input.\n", gender);
			System.out.println("Please try again!");
			System.out.println();
			createGender();
		}
	}

	/**
	 * <h1>createNationality</h1>
	 * <p>
	 * {@code private void createNationality()}
	 * </p>
	 * <p>
	 * This method validate and set guest's nationality.
	 * </p>
	 */
	private void createNationality() {
		System.out.println("Nationality: ");
		String nationality = in.next();

		if (Pattern.matches("^[A-Za-z\\s]+$", nationality)) {
			g.setNationality(nationality);
			System.out.println();

		} else {
			System.out.printf("\"%s\" is not a valid input.\n", nationality);
			System.out.println("Please try again!");
			System.out.println();
			createNationality();
		}
	}

	/**
	 * <h1>createContactNum</h1>
	 * <p>
	 * {@code private void createContactNum()}
	 * </p>
	 * <p>
	 * This method validate and set guest's contact no.
	 * </p>
	 */
	private void createContactNum() {
		System.out.println("Contact: (Eg. 98765432)");
		String contact = in.next();

		if ((Pattern.matches("-?\\d+(\\.\\d+)?", contact)) && (contact.length() == 8)
				&& ((contact.charAt(0) == '8') || (contact.charAt(0) == '9'))) {
			g.setContact(contact);
			System.out.println();

		} else {
			System.out.printf("\"%s\" is not a valid input.\n", contact);
			System.out.println("Please try again!");
			System.out.println();
			createContactNum();
		}
	}

	/**
	 * <h1>createCreditCardNum</h1>
	 * <p>
	 * {@code private void createCreditCardNum()}
	 * </p>
	 * <p>
	 * This method validate and set guest's credit card no.
	 * </p>
	 */
	private void createCreditCardNum() {
		System.out.println("Credit Card Number: (Eg. 9999-8888-7777-6666) Type without '-'");
		String card = in.next();

		if (Pattern.matches("-?\\d+(\\.\\d+)?", card) && (card.length() == 16)) {
			g.setCreditCardNo(card);
			System.out.println();
		} else {
			System.out.printf("\"%s\" is not a valid input.\n", card);
			System.out.println("Please try again!");
			System.out.println();
			createCreditCardNum();
		}
	}

	// ===========================================================================================================================================================//
	// Display Guest Detail
	// ===========================================================================================================================================================//

	/**
	 * <h1>displayGuestDetail</h1>
	 * <p>
	 * {@code public ArrayList<Guest> displayGuestDetail(String from, int choice1)}
	 * </p>
	 * <p>
	 * This method display all information about guest and write it to class
	 * </p>
	 * 
	 * @param from
	 *            - contains where the method is being called from
	 * @param choice1
	 *            - select which parameters to update
	 * @return - a list of guest information
	 */

	public ArrayList<Guest> displayGuestDetail(String from, int choice1) {
		ArrayList<Guest> list = new ArrayList<Guest>();

		System.out.println("-------------------------------------------------------------------");
		System.out.println("==================== Guest Information ====================");

		GuestBoundary.displayGuestInfo(g);

		System.out.println("\nHello, is the details correct?");
		System.out.println("\n1. Yes" + "\n2. No");

		int choice = in.nextInt();

		Guest g1 = new Guest(g.getName(), g.getIdtype(), g.getIdentity(), g.getAddress(), g.getCountry(), g.getGender(),
				g.getNationality(), g.getContact(), g.getCreditCardNo());

		list.add(g1);

		if (choice == 1) {
			if (from.equalsIgnoreCase("create"))
				rwf.writeGuestToFile(list);
			else if (from.equalsIgnoreCase("update")) {
				ArrayList<Guest> list1 = new ArrayList<Guest>();
				try {
					list1 = rwf.readGuestFile();
					for (int i = 0; i < list1.size(); i++) {
						list1.set(choice1, g1);
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}

				File file = new File("guest.dat");
				if (file.exists())
					file.delete();
				rwf.writeGuestToFile(list1);

			}

			System.out.println("The guest has been created successfully!");

		} else if (choice == 2)
			updateGuest(from, choice1);
		else {
			System.out.println("Wrong Input, please re-enter your choice");
			displayGuestDetail(from, choice1);
		}
		return list;
	}

	// ===========================================================================================================================================================//
	// Update Guest Detail
	// ===========================================================================================================================================================//

	/**
	 * <h1>updateGuest</h1>
	 * <p>
	 * {@code private void updateGuest()}
	 * </p>
	 * <p>
	 * This method allows user to choose which component to update
	 * </p>
	 * 
	 * @param from
	 *            - contains where the method is being called from
	 * @param choice
	 *            - select which parameters to update
	 * @exception InputMismatchException
	 *                - choice mismatch
	 */
	private void updateGuest(String from, int choice) {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("==================== Updating Guest Detail ====================");

		GuestBoundary.displayGuestInfo(g);

		try {
			int choice1 = in.nextInt();
			// in.nextLine();
			switch (choice1) {
			case 1:
				createName(from);
				break;
			case 2:
				createIdType();
				break;
			case 3:
				createIdentity();
				break;
			case 4:
				createAddress();
				break;
			case 5:
				createCountry();
				break;
			case 6:
				createGender();
				break;
			case 7:
				createNationality();
				break;
			case 8:
				createContactNum();
				break;
			case 9:
				createCreditCardNum();
				break;
			default:
				System.out.println("Wrong input. Please input values within no. 0 - 9 only!\n");
				updateGuest(from, choice);
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Wrong Input, please re-enter your choice\n");
			updateGuest(from, choice);
		}
		displayGuestDetail(from, choice);
	}

	// ===========================================================================================================================================================//
	// Update Guest 2 Detail
	// ===========================================================================================================================================================//

	/**
	 * <h1>updateGuest2</h1>
	 * <p>
	 * {@code private void updateGuest2()}
	 * </p>
	 * <p>
	 * This method read from guest.dat and update guests' information
	 * </p>
	 * 
	 * @exception IOExceptio
	 *                - input error
	 * @exception ClassNotFoundExceptio
	 *                - unable to find guest.dat
	 */
	private void updateGuest2() {
		try {
			ArrayList<Guest> list = rwf.readGuestFile();

			System.out.println("-------------------------------------------------------------------");
			System.out.println("==================== Updating Guest Detail ====================");
			System.out.println("-------------------------------------------------------------------");

			System.out.println("Please select which of the following Guest to update");

			for (int i = 0; i < list.size(); i++) {
				g = (Guest) list.get(i);
				System.out.println((i + 1) + ". " + g.getName());
			}

			System.out.println("0. Return to Guest System");
			int choice = in.nextInt();
			if (choice != 0) {
				g.setName(list.get(choice - 1).getName());
				g.setIdentity(list.get(choice - 1).getIdentity());
				g.setIdtype(list.get(choice - 1).getIdtype());
				g.setAddress(list.get(choice - 1).getAddress());
				g.setCountry(list.get(choice - 1).getCountry());
				g.setGender(list.get(choice - 1).getGender());
				g.setNationality(list.get(choice - 1).getNationality());
				g.setContact(list.get(choice - 1).getContact());
				g.setCreditCardNo(list.get(choice - 1).getCreditCardNo());
				updateGuest("update", choice - 1);
			} else
				selection();

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	// ===========================================================================================================================================================//
	// Search Guest Detail
	// ===========================================================================================================================================================//
	/**
	 * <h1>searchGuest</h1>
	 * <p>
	 * {@code public ArrayList<Guest> searchGuest(String from)}
	 * </p>
	 * <p>
	 * This method search guests' name from guest.dat by using any
	 * keywords/alphabets in regard of case sensitive
	 * </p>
	 * 
	 * @param from
	 *            - contains where the method is being called from
	 * @return - return the list of selected guest
	 */
	public ArrayList<Guest> searchGuest(String from) {
		ArrayList<Guest> glist = new ArrayList<Guest>();
		ArrayList<Guest> selected = new ArrayList<Guest>();
		int count = 1;
		boolean flag = false;
		String input;

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Searching Guests' name by using keywords");
		System.out.println("-------------------------------------------------------------------");
		if (from.equalsIgnoreCase("guest"))
			in.nextLine();
		String str = in.nextLine();
		try {
			ArrayList<Guest> list = rwf.readGuestFile();

			// Matching keywords of names
			for (int i = 0; i < list.size(); i++) {
				g = (Guest) list.get(i);
				if (Pattern.compile(Pattern.quote(str), Pattern.CASE_INSENSITIVE).matcher(g.getName()).find()) {
					System.out.println(count + ". " + g.getName());
					glist.add(g);
					count++;
					flag = true;
				}
			}

			// No Name is found
			if (flag == false) {
				System.out.printf("No names are found with '%s'. Please try again!\n", str);
				System.out.println("1. Search again." + "\n0. Exit");

				int choice = in.nextInt();
				if (choice != 0) {
					searchGuest(from);
				} else
					selection();
			}

			// Display chosen user information
			if (!from.equalsIgnoreCase("walkIn")) {
				if (in.hasNextInt()) {
					int choice = in.nextInt();
					if (choice != 0 && choice <= glist.size() && choice >= 0) {
						selected = GuestBoundary.displayInfo(glist, choice);
					} else {
						System.out.println("Wrong input, please re-enter your choice.\n");
						searchGuest(from);
					}
				} else {
					input = in.nextLine();
					System.out.printf("\"%s\" is not a valid digit.\n\n", input);
					searchGuest(from);
				}
			}
			if (from.equalsIgnoreCase("walkIn")) {
				return glist;
			}

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return selected;
	}
}