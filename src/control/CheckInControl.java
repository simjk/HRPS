package control;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import boundary.CheckInBoundary;
import control.WalkInControl;
import entity.Guest;
import entity.Reservation;
import entity.Room;
import entity.Reservation.Status;
import entity.Room.RoomStatus;
import control.MainControl;

/**
 * <h1>CheckInControl</h1>
 * <p>
 * The program implements methods that allow Walk-in or Reservation guest to
 * check-in, display all check-in details, release of expired rooms.
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */
public class CheckInControl implements Serializable, Control {
	Scanner in = new Scanner(System.in);
	readWriteFile rwf = new readWriteFile();

	/**
	 * <h1>CheckInControl</h1>
	 * <p>
	 * This method is used to call other CheckInControl methods in other classes
	 * </p>
	 */
	public CheckInControl() {
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
	 * into checkInInterface method.
	 * </p>
	 */
	private void selection() {
		String input;

		System.out.println("------------------------------------------------------------");
		System.out.println("================== HRPS: Check In System ===================");
		System.out.println("------------------------------------------------------------");

		System.out.println("1. Reservation Customers" + "\n2. Walk In Customers" + "\n3. Display Check-In Guest"
				+ "\n4. Release Expired Rooms" + "\n0. Exit");
		System.out.println("------------------------------------------------------------");

		if (in.hasNextInt()) {
			int sel = in.nextInt();
			checkInInterface(sel);
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			selection();
		}
		in.close();
	}

	/**
	 * <h1>checkInInterface</h1>
	 * <p>
	 * checkInInterface take in {@value control#sel} and call other methods.
	 * </p>
	 * 
	 * @param sel
	 *            - choose between reservation Check-in/Walk-in Check-in/Display All
	 *            check-in details/Release of expired rooms
	 */
	private void checkInInterface(int sel) {
		switch (sel) {
		case 1:
			resCheckIn();
			System.out.println("Enter 0 to return to Check In Menu");
			int choice1 = in.nextInt();
			if (choice1 == 0)
				selection();
			break;
		case 2:
			searchWalkInGuest();
			System.out.println("Enter 0 to return to Check In Menu");
			int choice2 = in.nextInt();
			if (choice2 == 0)
				selection();
			break;

		case 3:
			displayCheckIn();
			System.out.println("Enter 0 to return to Check In Menu");
			int choice3 = in.nextInt();
			if (choice3 == 0)
				selection();
			break;
		case 4:
			expiredRooms();
			System.out.println("Enter 0 to return to Check In Menu");
			int choice4 = in.nextInt();
			if (choice4 == 0)
				selection();
			break;

		case 0:
			MainControl.displayUI();
			break;
		default:
			System.out.println("Wrong input. Please input values within no. 0 - 4 only!\n");
			selection();
			break;
		}
	}

	// TODO
	// ===========================================================================================================================================================//
	// RESERVATION CHECK IN
	// ===========================================================================================================================================================//

	/**
	 * <h1>resCheckIn</h1>
	 * <p>
	 * This method call other method to allow reservation guest to check-in by
	 * searching their reservation details
	 * </p>
	 * 
	 */
	public void resCheckIn() {
		ArrayList<Reservation> reserve = new ArrayList<Reservation>();
		reserve = searchCheckIn();
		int num = 0;
		boolean isNum = false;

		do {
			System.out.println();
			System.out.println("Is this the correct information?");
			System.out.println("1.Yes" + "\n2.No");
			if (in.hasNextInt()) {
				isNum = true;
				num = in.nextInt();
				if (num == 1 | num == 2) {
					isNum = true;
				} else {
					System.out.println("Invalid input! Please try again!");
					isNum = false;
				}
			} else {
				System.out.println("Please input numbers only!");
				isNum = false;
				in.next();
			}
		} while (isNum == false);

		switch (num) {
		case 1:
			resCheckInCase1(reserve);
			break;
		case 2:
			searchCheckIn();
			break;

		default:
			System.out.println("Invalid input! Please input number 1 or 2 only!");
			break;
		}

	}

	/**
	 * <h1>resCheckInCase1</h1>
	 * <p>
	 * {@code private void resCheckInCase1(ArrayList<Reservation> reserve)}
	 * </p>
	 * <p>
	 * This method validate, set reservation status to CHECKED_IN and set Room
	 * Status to OCCUPIED
	 * </p>
	 * 
	 * @param reserve
	 *            - contains the guest's reservation details
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat/reservation.dat
	 */
	private void resCheckInCase1(ArrayList<Reservation> reserve) {

		String date;

		try {
			ArrayList<Reservation> results = rwf.readReservationFile();
			ArrayList<Room> results2 = rwf.ReadRoomFile();
			ArrayList<String> datecin = new ArrayList<String>();
			ArrayList<ArrayList> mainList = new ArrayList<ArrayList>();

			ArrayList<Reservation> checkInR = new ArrayList<Reservation>();
			checkInR.add(reserve.get(0));
			for (int i = 0; i < results.size(); i++) {
				Reservation r = results.get(i);
				for (int n = 0; n < reserve.size(); n++) {
					if (reserve.get(n).getReservationNo() == r.getReservationNo()) {
						r.setStatus(Status.CHECKED_IN);

						for (int k = 0; k < r.getR().size(); k++) {
							r.getR().get(k).setRoomStatus(RoomStatus.OCCUPIED);
						}
					}
				}
			}

			File file = new File("reservation.dat");
			if (file.exists()) {
				file.delete();
				rwf.writeReservationToFile(results);
			}

			for (int i = 0; i < reserve.size(); i++) {
				Reservation rs = reserve.get(i);
				for (int j = 0; j < rs.getR().size(); j++) {
					for (int k = 0; k < results2.size(); k++) {
						if (rs.getR().get(j).getRoomNum().matches(results2.get(k).getRoomNum())) {
							results2.get(k).setRoomStatus(RoomStatus.OCCUPIED);
						}
					}
				}
			}

			File file1 = new File("room.dat");
			if (file1.exists()) {
				file1.delete();
				rwf.writeRoomToFile(results2);

				Date mydate = new Date();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
				date = df.format(mydate);
				datecin.add(date);

				mainList.add(checkInR);
				mainList.add(datecin);
				rwf.writecheckInDetailsToFile(mainList);

				System.out.println("Guest Checked In Successfully!");
				System.out.println();
				selection();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>searchCheckIn</h1>
	 * <p>
	 * {@code private ArrayList<Reservation> searchCheckIn()}
	 * </p>
	 * <p>
	 * This method search for guest that has make reservation
	 * </p>
	 * @return list2 - returns the guest reservation details
	 */
	public ArrayList<Reservation> searchCheckIn() {

		int counter = 0;
		ArrayList<Reservation> list1 = new ArrayList<Reservation>();
		ArrayList<Reservation> list2 = new ArrayList<Reservation>();

		try {
			int count = 1;
			ArrayList<Reservation> results = rwf.readReservationFile();
			System.out.println();
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Searching Guests' name");
			System.out.println("-------------------------------------------------------------------");
			String str = in.nextLine();

			for (int i = 0; i < results.size(); i++) {
				Reservation res = results.get(i);
				for (int g = 0; g < res.getG().size(); g++) {
					if (Pattern.compile(Pattern.quote(str), Pattern.CASE_INSENSITIVE).matcher(res.getG().get(g).getName()).find()) {
						if (res.getStatus() != Status.CHECKED_IN) {
							System.out.println(count + ". " + res.getG().get(g).getName());
							counter++;
							list1.add(res);
							count++;
						}
					}
				}
			}
			if (counter == 0) {
				System.out.println("No guest found!");
			}
			System.out.println("0. Exit");

			int choice = in.nextInt();
			if (choice == 0) {
				selection();
			} else if (choice >= count) {
				System.out.println("Invalid input! Please input number from 0 to " + (count - 1) + " only!");
				System.out.println();
				searchCheckIn();
			} else {
				list2 = displayDetails(list1, choice);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Invaild input! Please input number only!");
			System.out.println();
			searchCheckIn();
		}
		return list2;
	}

	/**
	 * <h1>displayDetails</h1>
	 * <p>
	 * {@code public ArrayList<Reservation> displayDetails(ArrayList<Reservation> results, int choice)}
	 * </p>
	 * <p>
	 * This method display all reservation details of a guest
	 * </p>
	 * 
	 * @param results
	 *            - contains the reservation details
	 * @param choice
	 *            - select which guest to display
	 * @return list 1 - return the guest's reservation details
	 */
	private ArrayList<Reservation> displayDetails(ArrayList<Reservation> results, int choice) {
		ArrayList<Reservation> list1 = new ArrayList<Reservation>();
		CheckInBoundary.displayDetails(results, choice);
		list1.add(results.get(choice - 1));

		return list1;
	}

	// TODO
	// ===========================================================================================================================================================//
	// DISPLAY CHECK IN
	// ===========================================================================================================================================================//
	/**
	 * <h1>displayCheckIn</h1>
	 * <p>
	 * {@code public void displayCheckIn()}
	 * </p>
	 * <p>
	 * This method display all checked-in details
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find CheckIn.dat
	 */
	private void displayCheckIn() {
		WalkInControl wic = new WalkInControl();

		try {

			ArrayList<ArrayList<Reservation>> reser = rwf.readCheckInDetailsToFile();
			ArrayList<ArrayList> checkInList = rwf.readCheckInDetailsToFile();
			ArrayList<ArrayList> mainArray = rwf.readCheckInDetailsToFile();

			System.out.println("-----------------------------------------------");
			System.out.printf("%33s", "List of Check-In Guests");
			System.out.println();
			System.out.println("-----------------------------------------------");
			for (int i = 0; i < mainArray.size(); i += 2) {
				for (int re = 0; re < reser.get(i).size(); re++) {
					for (int k = 0; k < reser.get(i).get(re).getG().size(); k++) {
						System.out.println("Guest Name: " + reser.get(i).get(re).getG().get(k).getName());
					}
					System.out.println();
					System.out.println("------Room(s) Occupied------");
					for (int t = 0; t < reser.get(i).get(re).getR().size(); t++) {
						System.out.println("     " + (t + 1) + ". " + reser.get(i).get(re).getR().get(t).getRoomNum());
					}
				}
				System.out.println();

				for (int j = 0; j < checkInList.get(i + 1).size(); j++) {
					System.out.println("Check In Date/Time: " + checkInList.get(i + 1).get(j));
					System.out.println("----------------------------------------------");
				}
				System.out.println();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		wic.displayAllWalkInDetails("checkin");
	}

	// TODO
	// ===========================================================================================================================================================//
	// SEARCH WALK IN GUEST
	// ===========================================================================================================================================================//
	/**
	 * <h1>searchWalkInGuest</h1>
	 * <p>
	 * {@code public ArrayList<ArrayList> searchWalkInGuest()}
	 * </p>
	 * <p>
	 * This method search for Walk-in Guests & checked-in for them. Set Room Status
	 * to OCCUPIED
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat/WalkIn.dat
	 * 
	 * @return list3 - contains selected guest walk-in details
	 */
	private ArrayList<ArrayList> searchWalkInGuest() {
		ArrayList<ArrayList> list4 = new ArrayList<ArrayList>();
		WalkInControl walkIn = new WalkInControl();
		ArrayList<ArrayList> list3 = new ArrayList<ArrayList>();

		ArrayList<Room> walkinData1 = null;
		try {
			ArrayList<ArrayList<Guest>> WalkinGuests = rwf.readWalkInFile();
			ArrayList<ArrayList<Room>> WalkinRooms = rwf.readWalkInFile();
			ArrayList<ArrayList> WalkinDate = rwf.readWalkInFile();
			ArrayList<ArrayList> WalkoutDate = rwf.readWalkInFile();
			ArrayList<Room> roomdata = rwf.ReadRoomFile();
			ArrayList<Guest> searchGuests = new ArrayList<Guest>();
			ArrayList<ArrayList<Room>> searchRooms = new ArrayList<ArrayList<Room>>();
			ArrayList<ArrayList> searchchkin = new ArrayList<ArrayList>();
			ArrayList<ArrayList> searchchkout = new ArrayList<ArrayList>();
			int counter = 0;
			int count = 1;
			ArrayList<ArrayList> results = rwf.readWalkInFile();
			System.out.println("------------------------------------------------------------");
			System.out.println("Search by Name using keywords...");
			System.out.println("------------------------------------------------------------");

			String str = in.nextLine();

			for (int i = 0; i < results.size(); i += 4) {

				for (int k = 0; k < WalkinGuests.get(i).size(); k++) {
					if (WalkinGuests.get(i).get(k).getName().toLowerCase().contains(str)) {
						Guest walkInData = WalkinGuests.get(i).get(k);
						System.out.println(count + ". " + WalkinGuests.get(i).get(k).getName());
						searchGuests.add(walkInData);
						counter++;
						count++;

						for (int j = 0; j < WalkinRooms.get(i + 1).size(); j++) {
							walkinData1 = WalkinRooms.get(i + 1);
						}
						searchRooms.add(walkinData1);
						for (int g = 0; g < WalkinDate.get(i + 2).size(); g++) {
							ArrayList walkinData2 = WalkinDate.get(i + 2);
							searchchkin.add(walkinData2);
						}
						for (int g = 0; g < WalkoutDate.get(i + 3).size(); g++) {
							ArrayList walkinData3 = WalkoutDate.get(i + 3);
							searchchkout.add(walkinData3);
						}
					}
				}
			}

			list3.add(searchGuests);
			list3.add(searchRooms);
			list3.add(searchchkin);
			list3.add(searchchkout);

			if (counter == 0) {
				System.out.println("No guest found!");
			}
			System.out.println("0. Exit");
			int choiceW = in.nextInt();

			if (choiceW == 0) {
				selection();
			} else if (choiceW >= count) {
				System.out.println("Invalid input! Please input number from 0 to " + (count - 1) + " only!");
				System.out.println();
				searchWalkInGuest();
			} else {
				System.out.println("------------------------------------------------------------");
				System.out.println("====================== Selected Guest ======================");
				System.out.println("------------------------------------------------------------");
				Guest ggg = (Guest) list3.get(0).get(choiceW - 1);
				System.out.println("Guest Name: 		" + ggg.getName());
				System.out.println("Identity: 		" + ggg.getIdentity());
				System.out.println("Contact Number: 	" + ggg.getContact());

				ArrayList ccc = (ArrayList) list3.get(2).get(choiceW - 1);
				System.out.println("Check-in Date: 		" + ccc.get(0));

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
				ArrayList ddd = (ArrayList) list3.get(3).get(choiceW - 1);
				System.out.println("Check-out Date: 	" + df.format(ddd.get(0)));

				System.out.print("Reserved Room(s): 	");
				ArrayList<Room> rrr = (ArrayList<Room>) list3.get(1).get(choiceW - 1);
				for (int i = 0; i < rrr.size(); i++) {
					System.out.print(rrr.get(i).getRoomNum() + " ");
				}
				System.out.println();
				System.out.println("------------------------------------------------------------");

				System.out.println();
				boolean isNum = true;
				int nnn = 0;
				do {
					System.out.println();
					System.out.println("Is this the correct information?\n1. Yes\n2. No");
					if (in.hasNextInt()) {
						isNum = true;
						nnn = in.nextInt();
						if (nnn == 1 | nnn == 2) {
							isNum = true;
						} else {
							System.out.println("Invalid input! Please try again!");
							isNum = false;
						}

					} else {
						System.out.println("Please input numbers only!");
						isNum = false;

						in.next();

					}
				} while (isNum == false);

				switch (nnn) {
				case 1:
					for (int i = 0; i < rrr.size(); i++) {
						for (int j = 0; j < roomdata.size(); j++) {
							if (rrr.get(i).getRoomNum().matches(roomdata.get(j).getRoomNum())) {
								roomdata.get(j).setRoomStatus(RoomStatus.OCCUPIED);

							}
						}
					}
					File file = new File("room.dat");
					if (file.exists())
						file.delete();
					rwf.writeRoomToFile(roomdata);
					System.out.println();
					System.out.println("Room(s) checked-in successfully");
					selection();
					break;

				case 2:
					searchWalkInGuest();
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list3;
	}

	// TODO
	// ===========================================================================================================================================================//
	// EXPIRED GUEST
	// ===========================================================================================================================================================//
	/**
	 * <h1>expiredRooms</h1>
	 * <p>
	 * {@code public void expiredRooms()}
	 * </p>
	 * <p>
	 * This method validates and release of expired rooms. Set Reservation Status to
	 * EXPIRED & Set Room Status to VACANT
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat/WalkIn.dat
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat/reservation.dat
	 */
	// Loop through reservation file
	// Compare reservation check in date/time with current date/time
	// if current date/time > reservation check in date/time by 1hour
	// Change reservation status to expired & Set the rooms to vacant
	private void expiredRooms() {

		String currentDate;
		String currentTime;
		String resDate;
		String resTime;

		try {
			ArrayList<Reservation> results = rwf.readReservationFile();
			ArrayList<Room> rmResults = rwf.ReadRoomFile();
			Date mydate = new Date();

			DateFormat time = new SimpleDateFormat("HH:mm");
			DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
			currentDate = date.format(mydate);
			currentTime = time.format(mydate);

			for (int i = 0; i < results.size(); i++) {
				resDate = date.format(results.get(i).getCheckIn());
				if (currentDate.matches(resDate)) {
					resTime = time.format(results.get(i).getCheckIn());
					String currentTimeParse[] = currentTime.split(":");
					String resTimeParse[] = resTime.split(":");
					int firstHour = Integer.parseInt(currentTimeParse[0]);
					int firstMinute = Integer.parseInt(currentTimeParse[1]);
					int secondHour = Integer.parseInt(resTimeParse[0]);
					int secondMinute = Integer.parseInt(resTimeParse[1]);
					int durationHour = firstHour - secondHour;
					if (durationHour >= 1) {
						results.get(i).setStatus(Status.EXPIRED);
						for (int r = 0; r < results.get(i).getR().size(); r++) {
							for (int g = 0; g < rmResults.size(); g++) {
								if (results.get(i).getR().get(r).getRoomNum().matches(rmResults.get(g).getRoomNum())) {
									rmResults.get(g).setRoomStatus(RoomStatus.VACANT);
								}
							}
						}

					}
				} else if (mydate.after(results.get(i).getCheckIn())) {
					results.get(i).setStatus(Status.EXPIRED);
					for (int r = 0; r < results.get(i).getR().size(); r++) {
						for (int g = 0; g < rmResults.size(); g++) {
							if (results.get(i).getR().get(r).getRoomNum().matches(rmResults.get(g).getRoomNum())) {
								rmResults.get(g).setRoomStatus(RoomStatus.VACANT);
							}
						}
					}
				}
				File file = new File("reservation.dat");
				if (file.exists()) {
					file.delete();
					rwf.writeReservationToFile(results);

					File file1 = new File("room.dat");
					if (file1.exists()) {
						file1.delete();
						rwf.writeRoomToFile(rmResults);

						System.out.println("System updated successfully!\n");
						selection();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
