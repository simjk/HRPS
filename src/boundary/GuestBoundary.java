package boundary;

import java.util.ArrayList;

import entity.Guest;

/**
 * <h1>Guest Boundary</h1>
 * <p>
 * This class handles the display and printing of all Guest information
 * <p>
 * The related entity classes are: Guest.
 * </p>
 * <p>
 * The related control class is: GuestControl.
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 13-04-2018
 */
public class GuestBoundary {

	/**
	 * <h1>displayInfo</h1>
	 * <p>
	 * {@code public static ArrayList<Guest> displayInfo(ArrayList<Guest> list, int choice)}
	 * </p>
	 * <p>
	 * This method display selected information related to Guest (Name, Id type,
	 * Identity Address, Country, Gender, Nationality, Contact, Credit Card Number)
	 * </p>
	 * 
	 * @return - Return specified guest list
	 * @param list
	 *            - contains all the Guest information
	 * @param choice
	 *            - Specified the guest index
	 * 
	 */
	public static ArrayList<Guest> displayInfo(ArrayList<Guest> list, int choice) {
		ArrayList<Guest> list1 = new ArrayList<Guest>();
		System.out.println("-------------------------------------------------------------------");
		System.out.println("====================Guest Information===================");
		System.out.printf("Name:				%s\n", list.get(choice - 1).getName());
		System.out.printf("Id Type: 			%s\n", list.get(choice - 1).getIdtype());
		System.out.printf("Identity: 			%s\n", list.get(choice - 1).getIdentity());
		System.out.printf("Address: 			%s\n", list.get(choice - 1).getAddress());
		System.out.printf("Country: 			%s\n", list.get(choice - 1).getCountry());
		System.out.printf("Gender: 			%s\n", list.get(choice - 1).getGender());
		System.out.printf("Nationality: 			%s\n", list.get(choice - 1).getNationality());
		System.out.printf("Contact: 			%s\n", list.get(choice - 1).getContact());
		System.out.printf("Credit Card No: 		%s\n", list.get(choice - 1).getCreditCardNo());
		System.out.println("-------------------------------------------------------------------");

		list1.add(list.get(choice - 1));

		return list1;
	}

	/**
	 * <h1>displayGuestInfo</h1>
	 * <p>
	 * {@code public static void displayGuestInfo(Guest g)}
	 * </p>
	 * <p>
	 * This method prints out all information related to the Guest
	 * </p>
	 * @param g Retrieve from guest
	 */
	public static void displayGuestInfo(Guest g) {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("1. Name: 				" + g.getName());
		System.out.println("2. Id Type: 				" + g.getIdtype());
		System.out.println("3. Identity: 				" + g.getIdentity());
		System.out.println("4. Address: 				" + g.getAddress());
		System.out.println("5. Country: 				" + g.getCountry());
		System.out.println("6. Gender: 				" + g.getGender());
		System.out.println("7. Nationality: 			" + g.getNationality());
		System.out.println("8. Contact: 				" + g.getContact());
		System.out.println("9. Credit Card No: 			" + g.getCreditCardNo());
		System.out.println("-------------------------------------------------------------------");
	}
}
