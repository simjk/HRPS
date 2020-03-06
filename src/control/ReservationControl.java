package control;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.regex.Pattern;

import boundary.ReservationBoundary;
import control.GuestControl;
import control.RoomControl;
import entity.Guest;
import entity.Reservation;
import entity.Room;
import entity.Reservation.Status;
import entity.Room.RoomStatus;
import control.MainControl;

/**
 * <h1>ReservationControl</h1>
 * <p>
 * The program implements methods that create, update, remove, display all
 * Reservation information
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */
public class ReservationControl implements Serializable, Control {
	Reservation reser = new Reservation();
	readWriteFile rwf = new readWriteFile();
	Scanner in = new Scanner(System.in);

	/**
	 * <h1>ReservationControl</h1>
	 * <p>
	 * This method is used to call other ReservationControl methods in other classes
	 * </p>
	 */
	public ReservationControl() {
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
	 * into reservationInterface method.
	 * </p>
	 */
	private void selection() {

		int choice = 0;
		String input;

		System.out.println("------------------------------------------------------------");
		System.out.println("================= HRPS: Reservation System =================");
		System.out.println("------------------------------------------------------------");

		System.out.println("1. Create Reservation" + "\n2. Update Reservation" + "\n3. Remove Reservation"
				+ "\n4. Print all reservation" + "\n0. Return to Main Menu");
		System.out.println("------------------------------------------------------------");

		if (in.hasNextInt()) {
			choice = in.nextInt();
			if (choice >= 0 && choice <= 4)
				reservationInterface(choice);
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
	 * <h1>reservationInterface</h1>
	 * <p>
	 * reservationInterface take in {@value control#choice} and call other methods.
	 * </p>
	 * 
	 * @param choice
	 *            - choose between create/update/guest
	 */
	private void reservationInterface(int choice) {
		switch (choice) {
		case 1:
			createReservation();
			System.out.println("Enter 0 to return to Room System Menu");
			int choice1 = in.nextInt();
			if (choice1 == 0)
				selection();
			break;
		case 2:
			updateReservation();
			System.out.println("Enter 0 to return to Reservation System Menu");
			int choice2 = in.nextInt();
			if (choice2 == 0)
				selection();
			break;
		case 3:
			removeReservation();
			System.out.println("Enter 0 to return to Reservation System Menu");
			int choice3 = in.nextInt();
			if (choice3 == 0)
				selection();
			break;
		case 4:
			printAllReservation();
			System.out.println("Enter 0 to return to Reservation System Menu");
			int choice4 = in.nextInt();
			if (choice4 == 0)
				selection();
			break;
		case 0:
			MainControl.displayUI();
			break;
		default:
			break;
		}
	}

	// ===========================================================================================================================================================//
	// RESERVATION CREATE
	// ===========================================================================================================================================================//
	/**
	 * <h1>createReservation</h1>
	 * <p>
	 * {@code public void createReservation()}
	 * </p>
	 * <p>
	 * This method call other method to create all related information of
	 * reservation
	 * </p>
	 */
	public void createReservation() {

		GuestControl gc = new GuestControl();
		ArrayList<Guest> g = new ArrayList<Guest>();
		Random rdm;
		String input;

		System.out.println("------------------------------------------------------------");
		System.out.println("================ Creating Reservation System ===============");
		System.out.println("------------------------------------------------------------");
		System.out.println("Is the Guest existing or new Guest?" + "\n1. Existing" + "\n2. New" + "\n0. Exit");

		if (in.hasNextInt()) {
			int choice = in.nextInt();
			if (choice >= 0 && choice <= 2) {
				switch (choice) {
				case 1:
					rdm = new Random();
					reser.setReservationNo(rdm.nextInt(1000) + 1);
					g = gc.searchGuest("reservation");
					createReservationDetails(g);
					break;
				case 2:
					rdm = new Random();
					reser.setReservationNo(rdm.nextInt(1000) + 1);
					g = gc.createGuest("reservation");
					createReservationDetails(g);
					break;
				case 0:
					selection();
					break;
				default:
					System.out.println("Wrong input, please input the correct no. 0 - 2 only!\n");
					createReservation();
					break;
				}
			} else {
				input = in.nextLine();
				System.out.printf("\"%s\" is not a valid digit.\n\n", input);
				createReservation();

			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			createReservation();
		}

	}

	/**
	 * <h1>createReservationDetails</h1>
	 * <p>
	 * {@code public void createReservationDetails(ArrayList<Guest> g)}
	 * </p>
	 * <p>
	 * This method retrieve all the information about guest and room to write it to
	 * class
	 * </p>
	 * 
	 * @param g
	 *            - contains selected guest
	 */

	private void createReservationDetails(ArrayList<Guest> g) {

		RoomControl rc = new RoomControl();
		ArrayList<Room> r = null;

		reser.setG(g);
		System.out.println();

		r = rc.DisplayVacantRooms();
		reser.setR(r);

		for (int j = 0; j < r.size(); j++)
			System.out.println("Room selected: " + r.get(j).getRoomNum());

		while (true) {
			System.out.println("Check In Details:");
			reser.setCheckIn(checkInOutDate());
			System.out.println();
			System.out.println("Check Out Details:");
			reser.setCheckOut(checkInOutDate());
			if (reser.getCheckOut().compareTo(reser.getCheckIn()) >= 1) {
				break;
			}
			System.out.println("Check Out date is less than Check In date. Try again! \n");
		}

		numOfAdults();
		numOfChildren();

		System.out.println("\n");
		displayReservationConfirmation(g, r);
	}

	/**
	 * <h1>numOfAdults</h1>
	 * <p>
	 * {@code private void numOfAdults()}
	 * </p>
	 * <p>
	 * This method validate and set reservation's no. of adults.
	 * </p>
	 */
	private void numOfAdults() {
		int number = 0;

		do {
			System.out.println("Number of Adults (min. 1 adult):");
			while (!in.hasNextInt()) {
				String input = in.next();
				System.out.printf("\"%s\" is not a valid number.\n", input);
				System.out.println("Please number of adults: ");
			}
			number = in.nextInt();
		} while (number <= 0);

		reser.setNumOfAdult(number);
	}

	/**
	 * <h1>numOfChildren</h1>
	 * <p>
	 * {@code private void numOfChildren()}
	 * </p>
	 * <p>
	 * This method validate and set reservation's no. of childrens.
	 * </p>
	 */
	private void numOfChildren() {
		int number = 0;

		do {
			System.out.println("Number of Children:");
			while (!in.hasNextInt()) {
				String input = in.next();
				System.out.printf("\"%s\" is not a valid number.\n", input);
				System.out.println("Please number of children: ");
			}
			number = in.nextInt();
		} while (number < 0);
		reser.setNumOfChild(number);

	}

	/**
	 * <h1>checkInOutDate</h1>
	 * <p>
	 * {@code private Date checkInOutDate()}
	 * </p>
	 * <p>
	 * This method validate and set reservation's date cannot be lesser than today
	 * date.
	 * </p>
	 */
	private Date checkInOutDate() {

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));

		String date = new String();
		String time = new String();
		boolean checkDate = true;
		boolean checkTime = false;
		Date a = null;

		do {
			System.out.println("Date (dd/MM/yyyy): ");
			date = in.next();

			if (date != null && Pattern.matches("([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/((19|20)\\d\\d)", date)) {
				checkDate = true;
				do {
					System.out.println("Time in 24H format (HH:mm): ");
					time = in.next();
					if (time != null && Pattern.matches("([01]?[0-9]|2[0-3]):([0-5][0-9])", time)) {
						checkTime = true;
						break;
					} else {
						System.out.println("Wrong time format, please try again!");
						checkTime = false;
					}
				} while (checkTime == false);
			} else {
				checkDate = false;
				System.out.println("Wrong date format, pelase try again!");
			}
		} while (checkDate == false);

		String[] c = date.split("/");
		String[] d = time.split(":");

		cal.set(Calendar.YEAR, Integer.parseInt(c[2]));
		cal.set(Calendar.MONTH, Integer.parseInt(c[1]) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(c[0]));
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(d[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(d[1]));
		cal.getTime();

		if (cal.compareTo(today) > 0)
			a = cal.getTime();
		else {
			System.out.println("Input date/time is less than today's date/time. Try again!\n");
			a = checkInOutDate();
		}
		return a;
	}

	/**
	 * <h1>displayReservationConfirmation</h1>
	 * <p>
	 * {@code private void displayReservationConfirmation(ArrayList<Guest> g, ArrayList<Room> r)}
	 * </p>
	 * <p>
	 * This method display all information about reservation and write it to class
	 * </p>
	 * 
	 * @param g
	 *            - contains guest list
	 * @param r
	 *            - contains room list
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find reservation.dat
	 */
	private void displayReservationConfirmation(ArrayList<Guest> g, ArrayList<Room> r) {

		ArrayList<Reservation> resList = new ArrayList<Reservation>();
		ArrayList<Room> rmList = new ArrayList<Room>();
		Room rm = null;
		int choice;
		String input;

		System.out.println("------------------------------------------------------------");
		System.out.println("================== Reservation Information =================");
		System.out.println("------------------------------------------------------------");

		ReservationBoundary.displayReservation(reser, g, r);
		System.out.println("------------------------------------------------------------");

		System.out.println("Confirm reservation?\n1. Yes\n2. No");

		if (in.hasNextInt()) {
			choice = in.nextInt();
			if (choice > 0 && choice < 3) {
				switch (choice) {
				case 1:
					reser.setStatus(Reservation.Status.CONFIRMED);

					try {
						rmList = rwf.ReadRoomFile();

						for (int i = 0; i < rmList.size(); i++) {
							rm = rmList.get(i);
							for (int j = 0; j < reser.getR().size(); j++) {
								if (reser.getR().get(j).getRoomNum().matches(rm.getRoomNum())) {
									reser.getR().get(j).setRoomStatus(RoomStatus.RESERVED);
									rm.setRoomStatus(RoomStatus.RESERVED);
								}
							}
						}
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}

					resList.add(reser);
					rwf.writeReservationToFile(resList);

					File file = new File("room.dat");
					if (file.exists())
						file.delete();
					rwf.writeRoomToFile(rmList);

					System.out.println("Reservation has been made successfully!");
					break;
				case 2:
					selection();
					break;
				default:
					System.out.println("Wrong input. Please input values within no. 1 - 2 only!\n");
					displayReservationConfirmation(g, r);
					break;
				}
			} else {
				System.out.println("Wrong input. Please input values within no. 1 - 2 only!\n");
				displayReservationConfirmation(g, r);
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			displayReservationConfirmation(g, r);
		}
	}

	// ===========================================================================================================================================================//
	// RESERVATION UPDATE
	// ===========================================================================================================================================================//

	/**
	 * <h1>updateReservation</h1>
	 * <p>
	 * {@code public void updateReservation()}
	 * </p>
	 * <p>
	 * This method display all information about reservation and write it to class
	 * </p>
	 */
	public void updateReservation() {
		try {
			ArrayList<Reservation> list = rwf.readReservationFile();

			System.out.println("------------------------------------------------------------");
			System.out.println("================= Update Reservation Detail ================");
			System.out.println("------------------------------------------------------------");

			ReservationBoundary.updateReservationDetail(list);

			if (in.hasNextInt()) {
				int choice = in.nextInt();
				if (choice <= list.size() && choice > 0) {
					updateReservationDetail(list, choice);
				} else if (choice == 0) {
					selection();
				} else {
					System.out.printf("Wrong input, please enter no. 0-%d only!\n", list.size());
					System.out.println();
					updateReservation();
				}
			} else {
				System.out.println("No such options! Please try again!\n");
				in.next();
				updateReservation();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>updateReservationDetail</h1>
	 * <p>
	 * {@code private void updateReservationDetail(ArrayList<Reservation> list, int choice)}
	 * </p>
	 * <p>
	 * This method display all information about reservation's room and reservation
	 * detail
	 * </p>
	 */
	private void updateReservationDetail(ArrayList<Reservation> list, int choice) {

		String input;
		ReservationBoundary.updateReservationDetail(list, choice);

		if (in.hasNextInt()) {
			int c = in.nextInt();
			if (c == 1) {
				// 1. Room Detail
				RoomDetail(list, choice);
			} else if (c == 2) {
				// 2. Reservation Detail
				ReservationDetail(list, choice);
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			updateReservationDetail(list, choice);
		}
	}

	/**
	 * <h1>ReservationDetail</h1>
	 * <p>
	 * {@code private void updateReservationDetail(ArrayList<Reservation> list, int choice)}
	 * </p>
	 * <p>
	 * This method display all information about reservation's room and reservation
	 * detail
	 * </p>
	 */
	private void ReservationDetail(ArrayList<Reservation> list, int choice) {

		Reservation res = null;

		ReservationBoundary.ReservationDetail();
		int choice1 = in.nextInt();

		// 1. C_In, 2. C_Out, 3. Adult, 4. Child, 5. Reservation
		if (choice1 == 1) {
			while (true) {
				System.out.println("Check In Details:");
				reser.setCheckIn(checkInOutDate());
				System.out.println();
				if (list.get(choice - 1).getCheckOut().compareTo(reser.getCheckIn()) >= 1) {
					break;
				}
				System.out.println("Check Out date is less than Check In date. Try again! \n");
			}

			res = new Reservation(list.get(choice - 1).getReservationNo(), reser.getCheckIn(),
					list.get(choice - 1).getCheckOut(), list.get(choice - 1).getNumOfAdult(),
					list.get(choice - 1).getNumOfChild(), list.get(choice - 1).getStatus(), list.get(choice - 1).getG(),
					list.get(choice - 1).getR());

		} else if (choice1 == 2) {
			System.out.println("Check Out Details:");
			while (true) {
				System.out.println("Check Out Details:");
				reser.setCheckOut(checkInOutDate());
				if (reser.getCheckOut().compareTo(list.get(choice - 1).getCheckIn()) >= 1) {
					break;
				}
				System.out.println("Check Out date is less than Check In date. Try again! \n");
			}

			res = new Reservation(list.get(choice - 1).getReservationNo(), list.get(choice - 1).getCheckIn(),
					reser.getCheckOut(), list.get(choice - 1).getNumOfAdult(), list.get(choice - 1).getNumOfChild(),
					list.get(choice - 1).getStatus(), list.get(choice - 1).getG(), list.get(choice - 1).getR());
		} else if (choice1 == 3) {
			numOfAdults();
			res = new Reservation(list.get(choice - 1).getReservationNo(), list.get(choice - 1).getCheckIn(),
					list.get(choice - 1).getCheckOut(), reser.getNumOfAdult(), list.get(choice - 1).getNumOfChild(),
					list.get(choice - 1).getStatus(), list.get(choice - 1).getG(), list.get(choice - 1).getR());
		} else if (choice1 == 4) {
			numOfChildren();
			res = new Reservation(list.get(choice - 1).getReservationNo(), list.get(choice - 1).getCheckIn(),
					list.get(choice - 1).getCheckOut(), list.get(choice - 1).getNumOfAdult(), reser.getNumOfChild(),
					list.get(choice - 1).getStatus(), list.get(choice - 1).getG(), list.get(choice - 1).getR());
		} else if (choice1 == 5) {

			ReservationBoundary.ReservationStatus();
			int choice2 = in.nextInt();

			if (choice2 == 1)
				reser.setStatus(Status.CONFIRMED);
			else if (choice2 == 2)
				reser.setStatus(Status.WAITLIST);
			else if (choice2 == 3)
				reser.setStatus(Status.CHECKED_IN);
			else if (choice2 == 4)
				reser.setStatus(Status.CHECKED_OUT);
			else if (choice2 == 5)
				reser.setStatus(Status.EXPIRED);

			res = new Reservation(list.get(choice - 1).getReservationNo(), list.get(choice - 1).getCheckIn(),
					list.get(choice - 1).getCheckOut(), list.get(choice - 1).getNumOfAdult(),
					list.get(choice - 1).getNumOfChild(), reser.getStatus(), list.get(choice - 1).getG(),
					list.get(choice - 1).getR());
		}

		for (int i1 = 0; i1 < list.size(); i1++) {
			list.set(choice - 1, res);
			File file = new File("reservation.dat");
			if (file.exists())
				file.delete();
			rwf.writeReservationToFile(list);
		}

	}

	/**
	 * <h1>RoomDetail</h1>
	 * <p>
	 * {@code private void RoomDetail(ArrayList<Reservation> list, int choice)}
	 * </p>
	 * <p>
	 * This method display all information about reservation's room detail
	 * </p>
	 * 
	 * @param list
	 *            - list all the reservation's room list
	 * @param choice
	 *            - select a specified user
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find reservation.dat
	 */
	private void RoomDetail(ArrayList<Reservation> list, int choice) {

		ArrayList<Room> resList = new ArrayList<Room>();
		RoomControl rc = new RoomControl();
		Reservation res = null;
		ArrayList<Room> rmList = new ArrayList<Room>();

		resList = rc.DisplayVacantRooms();

		try {
			// Retrieve all room
			rmList = rwf.ReadRoomFile();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int j = 0; j < rmList.size(); j++) {
			for (int i = 0; i < list.size(); i++) {
				if (rmList.get(j).getRoomNum().contains(list.get(choice - 1).getR().get(i).getRoomNum())) {
					rmList.get(j).setRoomStatus(RoomStatus.VACANT);
				}
			}
			for (int o = 0; o < resList.size(); o++) {
				if (rmList.get(j).getRoomNum().contains(resList.get(o).getRoomNum())) {
					rmList.get(j).setRoomStatus(RoomStatus.RESERVED);
				}
			}
		}

		for (int i = 0; i < list.size(); i++) {
			for (int k = 0; k < resList.size(); k++) {
				resList.get(k).setRoomStatus(RoomStatus.RESERVED);
			}

			res = new Reservation(list.get(choice - 1).getReservationNo(), list.get(choice - 1).getCheckIn(),
					list.get(choice - 1).getCheckOut(), list.get(choice - 1).getNumOfAdult(),
					list.get(choice - 1).getNumOfChild(), list.get(choice - 1).getStatus(), list.get(choice - 1).getG(),
					resList);
			list.set(choice - 1, res);

			File file = new File("reservation.dat");
			if (file.exists())
				file.delete();
			rwf.writeReservationToFile(list);

			file = new File("room.dat");
			if (file.exists())
				file.delete();
			rwf.writeRoomToFile(rmList);
		}
		ReservationBoundary.updateReservationDetail(list, choice);
	}

	// ===========================================================================================================================================================//
	// PRINT ALL RESERVATION UPDATE
	// ===========================================================================================================================================================//

	/**
	 * <h1>printAllReservation</h1>
	 * <p>
	 * {@code public void printAllReservation()}
	 * </p>
	 * <p>
	 * This method display all information about reservation
	 * </p>
	 */
	public void printAllReservation() {
		ArrayList<Reservation> res = new ArrayList<Reservation>();
		try {
			res = rwf.readReservationFile();
			ReservationBoundary.printAllReservation(res);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ===========================================================================================================================================================//
	// REMOVE RESERVATION UPDATE
	// ===========================================================================================================================================================//

	/**
	 * <h1>removeReservation</h1>
	 * <p>
	 * {@code public void removeReservation()}
	 * </p>
	 * <p>
	 * This method delete selected reservation from reservation.dat
	 * </p>
	 */
	public void removeReservation() {
		String input;
		System.out.println("------------------------------------------------------------");
		System.out.println("==================== Remove Reservation ====================");
		System.out.println("------------------------------------------------------------");
		ArrayList<Room> rmList = new ArrayList<Room>();

		try {
			ArrayList<Reservation> res = rwf.readReservationFile();
			Reservation reserv = null;

			for (int i = 0; i < res.size(); i++) {
				reserv = res.get(i);
				for (int k = 0; k < reserv.getG().size(); k++) {
					System.out.println((i + 1) + ". " + res.get(i).getG().get(k).getName());
				}
			}

			System.out.println("0. Exit");
			if (in.hasNextInt()) {
				int choice = in.nextInt();
				if (choice > 0 && choice <= res.size()) {
					try {
						rmList = rwf.ReadRoomFile();

						for (int i = 0; i < rmList.size(); i++) {
							Room rm = rmList.get(i);
							for (int j = 0; j < reserv.getR().size(); j++) {
								if (reserv.getR().get(j).getRoomNum().matches(rm.getRoomNum())) {
									rm.setRoomStatus(RoomStatus.VACANT);
								}
							}
						}
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					res.remove(choice - 1);
				} else if (choice == 0)
					selection();
				else {
					System.out.println("Wrong input, please try again!");
					removeReservation();
				}
			} else {
				input = in.nextLine();
				System.out.printf("\"%s\" is not a valid digit.\n\n", input);
				removeReservation();
			}

			File file = new File("reservation.dat");
			if (file.exists())
				file.delete();
			rwf.writeReservationToFile(res); // Havent update to room.dat

			file = new File("room.dat");
			if (file.exists())
				file.delete();
			rwf.writeRoomToFile(rmList);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
