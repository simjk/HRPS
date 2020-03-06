package control;

import java.util.Scanner;

/**
 * <h1>MainControl</h1>
 * <p>
 * The program implements an application that simply computerize the processes
 * of making hotel reservation, reordering of orders and displaying of records.
 * It is solely used by the hotel staff.
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 13-04-2018
 */
public class MainControl {
	/**
	 * This is the main method which makes use of displayUI() method.
	 * 
	 * @param args
	 *            - main args
	 */
	public static void main(String[] args) {
		displayUI();
	}

	/**
	 * <h1>displayUI()</h1>
	 * <p>
	 * This method makes use a simple textScanner to parse primitive types and
	 * strings into mainInterface method.
	 * </p>
	 */
	public static void displayUI() {

		int choice = 0;
		String input;
		Scanner in = new Scanner(System.in);

		System.out.println("------------------------------------------------------------");
		System.out.println("======= Hotel Reservation and Payment System (HRPS) ========");
		System.out.println("------------------------------------------------------------");
		System.out.println("1. Guest" + "\n2. Room" + "\n3. Reservation" + "\n4. Walk In" + "\n5. Check In"
				+ "\n6. Room Service" + "\n7. Check Out" + "\n0. Exit");
		System.out.println("------------------------------------------------------------");

		if (in.hasNextInt()) {
			choice = in.nextInt();
			mainInterface(choice);
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			displayUI();
		}
		in.close();
	}

	/**
	 * <h1>mainInterface</h1>
	 * <p>
	 * {@code private static void mainInterface(int choice)}
	 * </p>
	 * <p>
	 * This method take in {@value control#choice} and call other control classes
	 * </p>
	 * 
	 * @param choice
	 *            The first parameter to pass into mainInterface method.
	 */
	private static void mainInterface(int choice) {
		switch (choice) {
		case 1:
			GuestControl gc = new GuestControl();
			gc.process();
			break;
		case 2:
			RoomControl rc = new RoomControl();
			rc.process();
			break;
		case 3:
			ReservationControl rc1 = new ReservationControl();
			rc1.process();
			break;
		case 4:
			WalkInControl wc = new WalkInControl();
			wc.process();
			break;
		case 5:
			CheckInControl cic = new CheckInControl();
			cic.process();
			break;
		case 6:
			RoomServiceControl rsc = new RoomServiceControl();
			rsc.process();
			break;
		case 7:
			CheckOutControl coc = new CheckOutControl();
			coc.process();
			break;
		case 0:
			System.out.println(">> You have left Hotel Reservation and Payment System (HRPS).");
			System.exit(0);
			break;
		default:
			System.out.println("Wrong input. Please input values within no. 0 - 8 only!\n");
			displayUI();
			break;
		}

	}

}