package boundary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entity.Guest;
import entity.Room;
import entity.Reservation;

/**
 * <h1>Reservation Boundary</h1>
 * <p>
 * This class handles the display and printing of all Reservation information
 * <p>
 * The related entity classes are: Reservation.
 * </p>
 * <p>
 * The related control class is: ReservationControl.
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 13-04-2018
 */
public class ReservationBoundary {

	/**
	 * <h1>displayReservation</h1>
	 * <p>
	 * {@code	public static void displayReservation(Reservation reser, ArrayList<Guest> g, ArrayList<Room> r)}
	 * </p>
	 * <p>
	 * This method display selected information related to Reservation (Reservation
	 * No., Name, Identity, Credit Card Number, Number of Adults, Number of
	 * Children, Check In Detail, Check Out Detail, Reservation Status)
	 * </p>
	 * 
	 * @param reser
	 *            - contains all the Reservation information
	 * @param g
	 *            - Specified the guest information
	 * @param r
	 *            - Specified the room information
	 */
	public static void displayReservation(Reservation reser, ArrayList<Guest> g, ArrayList<Room> r) {
		System.out.println("Reservation No.: 		" + reser.getReservationNo());
		System.out.println("Guest Name: 			" + g.get(0).getName());
		System.out.println("Guest Identity: 		" + g.get(0).getIdentity());
		System.out.println("Credit Card No.: 	 	" + g.get(0).getCreditCardNo());
		System.out.print("Room Number(s): 		");

		for (int j = 0; j < r.size(); j++) {
			System.out.print(r.get(j).getRoomNum() + " "); // 02-10, 01-20, 03-10
		}
		System.out.println("\nNumber of Adults: 		" + reser.getNumOfAdult());
		System.out.println("Number of Children:		" + reser.getNumOfChild());

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY, HH:mm");
		System.out.println("Check In Detail: 		" + format.format(reser.getCheckIn()));
		System.out.println("Check Out Detail:		" + format.format(reser.getCheckOut()));
	}

	/**
	 * <h1>updateReservationDetail</h1>
	 * <p>
	 * {@code public static void updateReservationDetail(ArrayList<Reservation> list)}
	 * </p>
	 * <p>
	 * This method update the selected information related to Reservation
	 * (Reservation No., Name, Identity, Credit Card Number, Number of Adults,
	 * Number of Children, Check In Detail, Check Out Detail, Reservation Status)
	 * </p>
	 * 
	 * @param list
	 *            - contains all the Reservation information
	 */
	public static void updateReservationDetail(ArrayList<Reservation> list) {
		System.out.println("Please select which Reservation to update (0: Exit)");
		System.out.printf("%s %15s %15s %15s %15s" + " %13s %15s %24s %25s %15s", "S/n", "Reservation No.", " Name",
				"Identity", "Contact No.", "Room No.", "Room Status", "Check In", "Check Out", "Reservation Status");
		System.out.println();

		for (int k = 0; k < list.size(); k++) {
			Reservation reser = list.get(k);
			System.out.printf("%s %15s", (k + 1) + ". ", reser.getReservationNo());
			for (int j = 0; j < reser.getG().size(); j++) {
				System.out.printf("%15s %15s %15s", reser.getG().get(j).getName(), reser.getG().get(j).getIdentity(),
						reser.getG().get(j).getContact());
			}

			if (reser.getR().size() >= 1)
				System.out.printf("%15s %15s", reser.getR().get(0).getRoomNum(), reser.getR().get(0).getRoomStatus());

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");

			System.out.printf("%25s %25s %18s\n", format.format(reser.getCheckIn()), format.format(reser.getCheckOut()),
					reser.getStatus());

			if (reser.getR().size() > 1) {
				for (int ki = 1; ki < reser.getR().size(); ki++) {
					System.out.format("%81s %15s\n", reser.getR().get(ki).getRoomNum(),
							reser.getR().get(ki).getRoomStatus());
				}
			}
		}
	}

	/**
	 * <h1>ReservationDetail</h1>
	 * <p>
	 * {@code public static void ReservationDetail()}
	 * </p>
	 * <p>
	 * This method display information related to Reservation (Number of Adults,
	 * Number of Children, Check In Detail, Check Out Detail, Reservation Status)
	 * </p>
	 * 
	 */
	public static void ReservationDetail() {

		System.out.println("------------------------------------------------------------");
		System.out.println("================== Reservation Information =================");
		System.out.println("------------------------------------------------------------");

		System.out.println("1. Check In" + "\n2. Check Out" + "\n3. Number of Adult" + "\n4. Number of Child"
				+ "\n5. Reservation Status");
		System.out.println("------------------------------------------------------------");
	}

	/**
	 * <h1>ReservationStatus</h1>
	 * <p>
	 * {@code public static void ReservationStatus()}
	 * </p>
	 * <p>
	 * This method display information related to Reservation status (Confirmed,
	 * Waitlist, Check In Check_out, Expired)
	 * </p>
	 * 
	 */
	public static void ReservationStatus() {
		System.out.println("------------------------------------------------------------");
		System.out.println("==================== Reservation Status ====================");
		System.out.println("------------------------------------------------------------");

		System.out.println("1. CONFIRMED" + "\n2. WAITLIST" + "\n3. CHECKED_IN" + "\n4. CHECKED_OUT" + "\n5. EXPIRED");
		System.out.println("------------------------------------------------------------");

	}

	/**
	 * <h1>updateReservationDetail</h1>
	 * <p>
	 * {@code public static void updateReservationDetail(ArrayList<Reservation> list, int choice)}
	 * </p>
	 * <p>
	 * This method display selected information related to Reservation (Reservation
	 * No., Name, Identity, Credit Card Number, Number of Adults, Number of
	 * Children, Check In Detail, Check Out Detail, Reservation Status)
	 * </p>
	 * 
	 * @param list
	 *            - Retrieve reservation list
	 * @param choice
	 *            - Retrieve user choice
	 */
	public static void updateReservationDetail(ArrayList<Reservation> list, int choice) {
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println("================ Update Reservation Detail =================");
		System.out.println("------------------------------------------------------------");

		System.out.println("Reservation No.: 	 " + list.get(choice - 1).getReservationNo());
		System.out.println("------------------------------------------------------------");
		System.out.println("Guest Detail");

		System.out.println("Name:			 " + list.get(choice - 1).getG().get(0).getName());
		System.out.println("Identity: 		 " + list.get(choice - 1).getG().get(0).getIdentity());
		System.out.println("Contact Number: 	 " + list.get(choice - 1).getG().get(0).getContact());
		System.out.println("------------------------------------------------------------");

		System.out.println("1. Room Detail");
		System.out.print("Room Number:");

		for (int i = 0; i < list.get(choice - 1).getR().size(); i++) {
			System.out.printf("%18s", list.get(choice - 1).getR().get(i).getRoomNum());
		}

		System.out.print("\nRoom Status: ");

		for (int i = 0; i < list.get(choice - 1).getR().size(); i++) {
			System.out.printf("%20s", list.get(choice - 1).getR().get(i).getRoomStatus());
		}

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");

		System.out.println();

		System.out.println("------------------------------------------------------------");
		System.out.println("2. Reservation Detail");

		System.out.println("Check In: 		 " + format.format(list.get(choice - 1).getCheckIn()));
		System.out.println("Check Out: 		 " + format.format(list.get(choice - 1).getCheckOut()));
		System.out.println("Number of Adult:	 " + list.get(choice - 1).getNumOfAdult());
		System.out.println("Number of Child:	 " + list.get(choice - 1).getNumOfChild());
		System.out.println("Reservation Status:	 " + list.get(choice - 1).getStatus());
		System.out.println();
		System.out.println("0. Exit");
	}

	/**
	 * <h1>printAllReservation</h1>
	 * <p>
	 * {@code public static void updateReservationDetail(ArrayList<Reservation> res)}
	 * </p>
	 * <p>
	 * This method display selected information related to Reservation (Reservation
	 * No., Name, Identity, Credit Card Number, Number of Adults, Number of
	 * Children, Check In Detail, Check Out Detail, Reservation Status)
	 * </p>
	 * 
	 * @param res
	 *            contains all reservation list
	 */
	public static void printAllReservation(ArrayList<Reservation> res) {
		System.out.println("------------------------------------------------------------");
		System.out.println("================ List of Guest Reservation =================");
		System.out.println("------------------------------------------------------------");
		for (int i = 0; i < res.size(); i++) {
			Reservation reser = res.get(i);
			System.out.println("Reservation No: 	" + reser.getReservationNo());
			for (int j = 0; j < reser.getG().size(); j++) {
				System.out.println("Name:			" + reser.getG().get(j).getName() + "\nIdentity:		"
						+ reser.getG().get(j).getIdentity() + "\nContact Number:		"
						+ reser.getG().get(j).getContact());
			}

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
			System.out.println("Check In: 		" + format.format(reser.getCheckIn()) + "\nCheck Out: 		"
					+ format.format(reser.getCheckOut()));

			System.out.println("Reservation Status: 	" + reser.getStatus());
			System.out.println();
			System.out.println("List of Reserved Rooms:");
			System.out.println("------------------------");
			System.out.printf("%s %15s", "Room No.", "Room Status");
			System.out.println();
			System.out.println("------------------------");
			for (int k = 0; k < reser.getR().size(); k++) {
				System.out.format("%s %15s", reser.getR().get(k).getRoomNum(), reser.getR().get(k).getRoomStatus());
				System.out.println();
			}
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
		}
	}
}
