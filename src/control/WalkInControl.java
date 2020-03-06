package control;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.regex.Pattern;

import boundary.RoomBoundary;
import control.MainControl;
import control.GuestControl;
import control.RoomControl;
import entity.Guest;
import entity.Room;
import entity.Room.RoomStatus;

/**
 * <h1>WalkInControl</h1>
 * <p>
 * The program implements methods that allow Guests to Walk-in and display all
 * walk-in details
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 13-04-2018
 */
public class WalkInControl extends GuestControl implements Control, Serializable {

	RoomControl rc = new RoomControl();
	GuestControl gc = new GuestControl();
	ArrayList<ArrayList> mainArrayList = new ArrayList<ArrayList>();
	Scanner in = new Scanner(System.in);

	/**
	 * <h1>WalkInControl</h1>
	 * <p>
	 * This method is used to call other WalkIn methods in other classes
	 * </p>
	 */
	public WalkInControl() {
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

		System.out.println("------------------------------------------------------------");
		System.out.println("=================== HRPS: Walk In System ===================");
		System.out.println("------------------------------------------------------------");
		System.out.println("1. Walk in\n" + "2. Display All Walk In Details\n" + "0. Exit");
		System.out.println("------------------------------------------------------------");

		try {

			int choice = in.nextInt();
			switch (choice) {
			case 1:
				WalkIn();
				System.out.println("Enter 0 to return to Walk In System Menu");
				int input = in.nextInt();
				if (input == 0)
					selection();
				break;
			case 2:
				String from = "walkin";
				displayAllWalkInDetails(from);
				System.out.println("Enter 0 to return to Walk In System Menu");
				int input1 = in.nextInt();
				if (input1 == 0)
					selection();
				break;
			case 0:
				MainControl.displayUI();
				break;
			default:
				System.out.println("Select Option 1 - 2");
				selection();
			}
		} catch (InputMismatchException e) {
			System.out.println("You did not enter a valid value. Please Enter a Integer Value!!");
			selection();
		}
	}

	// ===========================================================================================================================================================//
	// WALK IN
	// ===========================================================================================================================================================//

	/**
	 * <h1>WalkIn</h1>
	 * <p>
	 * This method call other method to allow Guests to walk-in
	 * </p>
	 * 
	 */
	private void WalkIn() {

		String input;
		System.out.println("------------------------------------------------------------");
		System.out.println("========================== Walk In =========================");
		System.out.println("------------------------------------------------------------");
		System.out.println("Existing Guest?" + "\n1. Yes" + "\n2. No");

		if (in.hasNextInt()) {
			int choice1 = in.nextInt();
			if (choice1 == 1) {
				ExistingGuest();
			} else if (choice1 == 2) {
				createGuest("walkin");
				NewGuest();
			} else {
				System.out.println("Wrong input, please input the correct values!");
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			WalkIn();
		}
	}

	/**
	 * <h1>NewGuest</h1>
	 * <p>
	 * {@code private void NewGuest()}
	 * </p>
	 * <p>
	 * This method create new Guest, display Vacant rooms, Set chosen rooms' status
	 * to RESERVED
	 * </p>
	 * 
	 * @exception InputMismatchException
	 *                - wrong input
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find WalkIn.dat
	 */
	private void NewGuest() {

		String date = "";
		ArrayList<String> datecin = new ArrayList<String>();
		ArrayList<Date> datecout = new ArrayList<Date>();
		ArrayList<Room> results = new ArrayList<Room>();
		ArrayList<Room> results2 = new ArrayList<Room>();
		ArrayList<Guest> list2 = new ArrayList<Guest>();

		try {
			ArrayList<Room> wholeroom = rwf.ReadRoomFile();
			Date mydate = new Date();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
			date = df.format(mydate);
			datecin.add(date);

			ArrayList<Guest> guestlist = rwf.readGuestFile();
			//System.out.println("Selected Guest: " + guestlist.get(guestlist.size() - 1).getName());
			list2.add(guestlist.get(guestlist.size() - 1));

			int n = 0;
			results = rc.DisplayVacantRoomsForWalkIn(); // Display vacancy
			System.out.println("List of Vacant Rooms: ");
			RoomBoundary.RoomDisplayHeaderWithoutStatus();
			for (int i = 0; i < results.size(); i++) {
				Room p = results.get(i);
				System.out.format("%5d %15s %20s %20.2f %20.2f %20b %20b %20s %20s", (n + 1), p.getRoomNum(),
						p.getRoomType(), p.getweekdayRoomRate(), p.getweekendRoomRate(), p.isWifiEnabled(),
						p.getSmoke(), p.getBedType(), p.getFacingView());
				System.out.println();
				n++;
			}
			boolean check = true;
			do {
				System.out.println("How many room(s) to check in: ");
				int roo = in.nextInt();
				if (roo != 0) {

					for (int i = 0; i < roo; i++) {
						System.out.println("Select S/No. to check in Room");
						int c = in.nextInt();
						for (int j = 0; j < wholeroom.size(); j++) {
							if (results.get(c - 1).getRoomNum().matches(wholeroom.get(j).getRoomNum())) {
								wholeroom.get(j).setRoomStatus(RoomStatus.RESERVED);
								results2.add(wholeroom.get(j));
								check = true;
							}
						}
					}
					datecout.add(checkOutDate());
				} else {
					System.out.println("Must have at least 1 room minimum.");
					check = false;
				}
			} while (check == false);

			File file = new File("room.dat");
			if (file.exists())
				file.delete();
			rwf.writeRoomToFile(wholeroom);

			mainArrayList.add(list2);
			mainArrayList.add(results2);
			mainArrayList.add(datecin);
			mainArrayList.add(datecout);

			rwf.writeWalkInToFile(mainArrayList);

			System.out.println("Walk In Sucessfully!!");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("You did not enter a valid value. Please Enter a Integer Value!!!");
			WalkIn();
		}
	}

	/**
	 * <h1>ExistingGuest</h1>
	 * <p>
	 * {@code private void ExistingGuest()}
	 * </p>
	 * <p>
	 * This method search for existing guest, display Vacant rooms, Set chosen
	 * rooms' status to RESERVED
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find WalkIn.dat
	 */
	private void ExistingGuest() {

		String date = "";
		ArrayList<String> datecin = new ArrayList<String>();
		ArrayList<Date> datecout = new ArrayList<Date>();
		ArrayList<Room> results = new ArrayList<Room>();
		ArrayList<Room> results2 = new ArrayList<Room>();
		ArrayList<Guest> list2 = new ArrayList<Guest>();

		ArrayList<Guest> list = searchGuest("walkIn");
		Date mydate = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
		date = df.format(mydate);
		datecin.add(date);

		try {
			ArrayList<Room> wholeroom = rwf.ReadRoomFile(); // Read the data from Room
			int choice2 = in.nextInt();
			System.out.println("Selected Guest: " + list.size() + ", " + list.get(choice2 - 1).getName());
			list2.add(list.get(choice2 - 1));

			int n = 0;
			results = rc.DisplayVacantRoomsForWalkIn(); // Display vacancy
			System.out.println("List of Vacant Rooms: ");
			RoomBoundary.RoomDisplayHeaderWithoutStatus();
			for (int i = 0; i < results.size(); i++) {
				Room p = results.get(i);
				System.out.format("%5d %15s %20s %20.2f %20.2f %20b %20b %20s %20s", (n + 1), p.getRoomNum(),
						p.getRoomType(), p.getweekdayRoomRate(), p.getweekendRoomRate(), p.isWifiEnabled(),
						p.getSmoke(), p.getBedType(), p.getFacingView());
				System.out.println();
				n++;
			}
			boolean check = true;
			do {
				System.out.println("How many room(s) to check in: ");
				int roo = in.nextInt();
				if (roo != 0) {
					for (int i = 0; i < roo; i++) {
						System.out.println("Select S/No. to check in Room");
						int c = in.nextInt();
						for (int j = 0; j < wholeroom.size(); j++) {
							if (results.get(c - 1).getRoomNum().matches(wholeroom.get(j).getRoomNum())) {
								wholeroom.get(j).setRoomStatus(RoomStatus.RESERVED);
								results2.add(wholeroom.get(j));
								check = true;
							}
						}
					}
					datecout.add(checkOutDate());
				} else {
					System.out.println("Must have at least 1 room minimum.");
					check = false;
				}
			} while (check == false);

			File file = new File("room.dat");
			if (file.exists())
				file.delete();
			rwf.writeRoomToFile(wholeroom);

			mainArrayList.add(list2);
			mainArrayList.add(results2);
			mainArrayList.add(datecin);
			mainArrayList.add(datecout);

			rwf.writeWalkInToFile(mainArrayList);

			System.out.println("Walk In Sucessfully!!");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>checkOutDate</h1>
	 * <p>
	 * {@code private Date checkOutDate()}
	 * </p>
	 * <p>
	 * This method validate check-out date
	 * </p>
	 * 
	 * @return a - returns the check out date
	 */
	private Date checkOutDate() {
		Date a = null;
		while (true) {
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			Calendar today = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			boolean checkDate = true;
			boolean checkTime = false;
			String time = new String();
			String date22 = new String();
			do {
				System.out.println("Enter the Date to check out: E.g. dd/mm/yyyy");
				date22 = in.next();
				if (date22 != null
						&& Pattern.matches("([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/((19|20)\\d\\d)", date22)) {
					checkDate = true;
					do {
						System.out.println("Time in 24H format (HH:mm)");
						time = in.next();
						if (time != null && Pattern.matches("([01]?[0-9]|2[0-3]):([0-5][0-9])", time)) {
							checkTime = true;
							break;
						} else {
							System.out.println("Wrong time format. Try again!");
							checkTime = false;
						}
					} while (checkTime == false);
				} else {
					checkDate = false;
					System.out.println("Wrong date format. Try again!");
				}
			} while (checkDate == false);

			String[] c = date22.split("/");
			String[] d = time.split(":");
			cal.set(Calendar.YEAR, Integer.parseInt(c[2]));
			cal.set(Calendar.MONTH, Integer.parseInt(c[1]) - 1);// 0 - 11
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(c[0])); // 1 - 31
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(d[0]));
			cal.set(Calendar.MINUTE, Integer.parseInt(d[1]));
			cal.getTime();

			if (cal.compareTo(today) > 0) {
				a = cal.getTime();
				// datecout.add(a);

				break;
				// System.out.println(a);
			} else {
				System.out.println("Input date/time is less than today's date/time. Try again!\n");
			}
		}
		return a;
	}

	// ===========================================================================================================================================================//
	// DISPLAY ALL WALKIN
	// ===========================================================================================================================================================//

	/**
	 * <h1>displayAllWalkInDetails</h1>
	 * <p>
	 * {@code public void displayAllWalkInDetails(String from)}
	 * </p>
	 * <p>
	 * This method display all Walk-in information about guest
	 * </p>
	 * 
	 * @param from
	 *            - contains where the method is being called from
	 */
	public void displayAllWalkInDetails(String from) {
		ArrayList<ArrayList<Guest>> WalkinGuests;
		ArrayList<ArrayList<Room>> WalkinRooms;
		ArrayList<ArrayList> WalkinDate;
		ArrayList<ArrayList> WalkoutDate;
		try {
			ArrayList<ArrayList> main = rwf.readWalkInFile();
			WalkinGuests = rwf.readWalkInFile();
			WalkinRooms = rwf.readWalkInFile();
			WalkinDate = rwf.readWalkInFile();
			WalkoutDate = rwf.readWalkInFile();

			System.out.println("-----------------------------------");
			System.out.printf("%27s", "List of Walk-in Guests");
			System.out.println();
			System.out.println("-----------------------------------");
			for (int i = 0; i < main.size(); i += 4) {

				for (int k = 0; k < WalkinGuests.get(i).size(); k++) {
					System.out.println("Guest Name : " + WalkinGuests.get(i).get(k).getName());
				}
				System.out.println();
				System.out.println("----Rooms occupied----");
				for (int k = 0; k < WalkinRooms.get(i + 1).size(); k++) {
					System.out.println("     " + (k + 1) + ". " + WalkinRooms.get(i + 1).get(k).getRoomNum());
				}
				System.out.println();

				if (from.equalsIgnoreCase("walkin")) {
					for (int k = 0; k < WalkinDate.get(i + 2).size(); k++) {
						System.out.println("Check in date : " + WalkinDate.get(i + 2).get(k));
					}
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");

					for (int k = 0; k < WalkoutDate.get(i + 3).size(); k++) {
						System.out.println("Check out date: " + df.format(WalkinDate.get(i + 3).get(k)));
					}
				}
				if (from.equalsIgnoreCase("checkin")) {
					for (int k = 0; k < WalkinDate.get(i + 2).size(); k++) {
						System.out.print("Check In Date/Times: " + WalkinDate.get(i + 2).get(k));
					}
				}
				System.out.println();
				System.out.println("-----------------------------------");
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
