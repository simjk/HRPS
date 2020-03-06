package control;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import boundary.RoomBoundary;
import control.MainControl;
import entity.Guest;
import entity.Reservation;
import entity.Room;
import entity.Room.BedType;
import entity.Room.FacingView;
import entity.Room.RoomStatus;
import entity.Room.RoomType;

/**
 * <h1>RoomControl</h1>
 * <p>
 * The program implements methods that create, update, display all Reservation
 * information, check availability of room and guest, print room statistic
 * report of all room information
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */
public class RoomControl implements Control, Serializable {
	Room r = new Room();
	readWriteFile rwf = new readWriteFile();
	Scanner in = new Scanner(System.in);

	/**
	 * <h1>RoomControl</h1>
	 * <p>
	 * This method is used to call other RoomControl methods in other classes
	 * </p>
	 */
	public RoomControl() {
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
	 * into roomInterface method.
	 * </p>
	 */
	private void selection() {
		int choice = 0;
		String input;

		System.out.println("------------------------------------------------------------");
		System.out.println("==================== HRPS: Room System =====================");
		System.out.println("------------------------------------------------------------");

		System.out.println("1. Create Room\n" + "2. Update Room\n" + "3. Display All Room Details\n"
				+ "4. Print Room Status Statistic Report\n" + "5. Check Availability\n" + "0. Return to Main Menu");
		System.out.println("------------------------------------------------------------");

		// Validation for numeric inputs
		if (in.hasNextInt()) {
			choice = in.nextInt();
			if (choice >= 0 && choice <= 5)
				roomInterface(choice);
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
	 * roomInterface take in {@value control#choice} and call other methods.
	 * </p>
	 * 
	 * @param choice
	 *            - choose between create/update/display/print/check availability
	 */
	private void roomInterface(int choice) {
		switch (choice) {
		case 1:
			createRoom();
			System.out.println("Enter 0 to return to Room System Menu");
			int choice1 = in.nextInt();
			if (choice1 == 0)
				selection();
			break;
		case 2:
			updateRoom();
			System.out.println("Enter 0 to return to Room System Menu");
			int choice2 = in.nextInt();
			if (choice2 == 0)
				selection();
			break;
		case 3:
			displayAllRoomDetails();
			System.out.println("Enter 0 to return to Room System Menu");
			int choice3 = in.nextInt();
			if (choice3 == 0)
				selection();
			break;
		case 4:
			printRoomStatReport();
			System.out.println("Enter 0 to return to Room System Menu");
			int choice4 = in.nextInt();
			if (choice4 == 0)
				selection();
			break;
		case 5:
			CheckAvailability();
			System.out.println("\nEnter 0 to return to Room System Menu");
			int choice5 = in.nextInt();
			if (choice5 == 0)
				selection();
			break;
		case 0:
			MainControl.displayUI();
		default:
			break;
		}
	}

	// ===========================================================================================================================================================//
	// ROOM CREATE
	// ===========================================================================================================================================================//
	/**
	 * <h1>createRoom</h1>
	 * <p>
	 * {@code public void createRoom()}
	 * </p>
	 * <p>
	 * This method call other method to create all related information of room
	 * </p>
	 */
	public void createRoom() {

		System.out.println("------------------------------------------------------------");
		System.out.println("=================== Creating Room System ===================");
		System.out.println("------------------------------------------------------------");

		PromptRoomNumber(); // room number
		PromptRoomType(r); // room type
		PromptWeekdayRoomRate(r); // weekday room rate
		PromptWeekendRoomRate(r); // weekend room rate
		PromptWifiEnabled(r); // WIFI enabled
		PromptSmokeAllowed(r);// Smoke Allowed
		PromptBedType(r); // Bed Type
		PromptFacingView(r);// Facing View
		PromptRoomStatus(r);// Room Status

		displayCreateRoomDetail();
	}

	/**
	 * <h1>PromptRoomNumber</h1>
	 * <p>
	 * {@code private void PromptRoomNumber()}
	 * </p>
	 * <p>
	 * This method validate and set room number.
	 * </p>
	 */
	private void PromptRoomNumber() {
		System.out.println("Enter room number: (E.g. 02-01) ");
		String inputroom = in.next();

		do {
			if (inputroom.matches("^0[2-7]-0[0-8]$")) {
				r.setRoomNum(inputroom);
				break;
			} else {
				System.out.println("Wrong input, please write your Room in this format: 02-01");
				System.out.println("Note: For each level, (level 2 to 7) room number are range from 01 to 08. \n");
				System.out.println("Enter room number: ");
				inputroom = in.next();
			}
			r.setRoomNum(inputroom);
		} while (!inputroom.matches("^0[2-7]-0[0-8]$"));
	}

	/**
	 * <h1>PromptRoomType</h1>
	 * <p>
	 * {@code private void PromptRoomType(Room r)}
	 * </p>
	 * <p>
	 * This method validate and set room type
	 * </p>
	 * 
	 * @param r
	 *            - contains room detail
	 */
	private void PromptRoomType(Room r) {

		System.out.println("Enter Room Type: \n 1. SINGLE \n 2. DOUBLE \n 3. DELUXE \n 4. VIP_SUITE");
		String input;

		if (in.hasNextInt()) {
			int roomchoice = in.nextInt();
			switch (roomchoice) {
			case 1:
				r.setRoomType(RoomType.SINGLE);
				break;
			case 2:
				r.setRoomType(RoomType.DOUBLE);
				break;
			case 3:
				r.setRoomType(RoomType.DELUXE);
				break;
			case 4:
				r.setRoomType(RoomType.VIP_SUITE);
				break;
			default:
				System.out.println("Wrong input, please input no. 1 - 4 only!");
				PromptRoomType(r);
				break;
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			PromptRoomType(r);
		}
	}

	/**
	 * <h1>PromptWeekdayRoomRate</h1>
	 * <p>
	 * {@code private void PromptWeekdayRoomRate(Room r)}
	 * </p>
	 * <p>
	 * This method validate and set weekday room rate
	 * </p>
	 * 
	 * @param r
	 *            - contains room detail
	 */
	private void PromptWeekdayRoomRate(Room r) {
		double number;
		String input;

		System.out.println("Enter weekday room rate: ");

		do {
			while (!in.hasNextDouble()) {
				input = in.next();
				System.out.printf("\"%s\" is not a valid number.\n", input);
				System.out.println("Please re-enter weekday room rate: ");
			}
			number = in.nextDouble();
		} while (number < 0);

		r.setweekdayRoomRate(number);
	}

	/**
	 * <h1>PromptWeekendRoomRate</h1>
	 * <p>
	 * {@code private void PromptWeekendRoomRate(Room r)}
	 * </p>
	 * <p>
	 * This method validate and set weekend room rate
	 * </p>
	 * 
	 * @param r
	 *            - contains room detail
	 */
	private void PromptWeekendRoomRate(Room r) {
		double number;

		do {
			System.out.println("Enter weekend room rate: ");
			while (!in.hasNextDouble()) {
				String input = in.next();
				System.out.printf("\"%s\" is not a valid number.\n", input);
				System.out.println("Please re-enter weekend room rate: ");
			}
			number = in.nextDouble();
		} while (number < 0);

		r.setweekendRoomRate(number);
	}

	/**
	 * <h1>PromptWifiEnabled</h1>
	 * <p>
	 * {@code private void PromptWifiEnabled(Room r)}
	 * </p>
	 * <p>
	 * This method validate and set wifi option
	 * </p>
	 * 
	 * @param r
	 *            - contains room detail
	 */
	private void PromptWifiEnabled(Room r) {
		System.out.println("Wifi Enabled? \n 1. Yes \n 2. No");
		String input;

		if (in.hasNextInt()) {
			int wifichoice = in.nextInt();

			switch (wifichoice) {
			case 1:
				r.setWifiEnabled(true);
				break;
			case 2:
				r.setWifiEnabled(false);
				break;
			default:
				System.out.println("Wrong input, please input no. 1 - 2 only!");
				PromptWifiEnabled(r);
				break;
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			PromptWifiEnabled(r);
		}
	}

	/**
	 * <h1>PromptSmokeAllowed</h1>
	 * <p>
	 * {@code private void PromptSmokeAllowed(Room r)}
	 * </p>
	 * <p>
	 * This method validate and set smoke option
	 * </p>
	 * 
	 * @param r
	 *            - contains room detail
	 */
	private void PromptSmokeAllowed(Room r) {
		System.out.println("Smoke Allowed?\n 1. Yes \n 2. No ");
		String input;

		if (in.hasNextInt()) {
			int smokechoice = in.nextInt();
			switch (smokechoice) {
			case 1:
				r.setSmoke(true);
				break;
			case 2:
				r.setSmoke(false);
				break;
			default:
				System.out.println("Wrong Input, please input no. 1 - 2 Only!");
				PromptSmokeAllowed(r);
				break;
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			PromptSmokeAllowed(r);
		}
	}

	/**
	 * <h1>PromptBedType</h1>
	 * <p>
	 * {@code private void PromptBedType(Room r)}
	 * </p>
	 * <p>
	 * This method validate and set bed type
	 * </p>
	 * 
	 * @param r
	 *            - contains room detail
	 */
	private void PromptBedType(Room r) {
		System.out.println("Enter Bed Type: \n 1. SINGLE_BED \n 2. DOUBLE_BED \n 3. MASTER_BED");
		String input;

		if (in.hasNextInt()) {
			int bedchoice = in.nextInt();
			switch (bedchoice) {
			case 1:
				r.setBedType(BedType.SINGLE_BED);
				break;
			case 2:
				r.setBedType(BedType.DOUBLE_BED);
				break;
			case 3:
				r.setBedType(BedType.MASTER_BED);
				break;
			default:
				System.out.println("Wrong Input, please input no. 1 - 3 only!");
				PromptBedType(r);
				break;
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			PromptBedType(r);
		}
	}

	/**
	 * <h1>PromptFacingView</h1>
	 * <p>
	 * {@code private void PromptFacingView(Room r)}
	 * </p>
	 * <p>
	 * This method validate and set facing view
	 * </p>
	 * 
	 * @param r
	 *            - contains room detail
	 */
	private void PromptFacingView(Room r) {
		System.out.println("Enter Facing View: \n 1. CITY_VIEW \n 2. SEA_VIEW \n 3. MOUNTAIN_VIEW");
		String input;

		if (in.hasNextInt()) {
			int viewchoice = in.nextInt();
			switch (viewchoice) {
			case 1:
				r.setFacingView(FacingView.CITY_VIEW);
				break;
			case 2:
				r.setFacingView(FacingView.SEA_VIEW);
				break;
			case 3:
				r.setFacingView(FacingView.MOUNTAIN_VIEW);
				break;
			default:
				System.out.println("Wrong Input, Please Key in No. 1 - 3 Only!");
				PromptFacingView(r);
				break;
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			PromptFacingView(r);
		}
	}

	/**
	 * <h1>PromptRoomStatus</h1>
	 * <p>
	 * {@code private void PromptRoomStatus(Room r)}
	 * </p>
	 * <p>
	 * This method validate and set room status
	 * </p>
	 * 
	 * @param r
	 *            - contains room detail
	 */

	private void PromptRoomStatus(Room r) {
		System.out.println("Enter Room Status: \n 1. VACANT \n 2. OCCUPIED \n 3. RESERVED \n 4. UNDER_MAINTENANCE");
		String input;

		if (in.hasNextInt()) {
			int statuschoice = in.nextInt();

			switch (statuschoice) {
			case 1:
				r.setRoomStatus(RoomStatus.VACANT);
				break;
			case 2:
				r.setRoomStatus(RoomStatus.OCCUPIED);
				break;
			case 3:
				r.setRoomStatus(RoomStatus.RESERVED);
				break;
			case 4:
				r.setRoomStatus(RoomStatus.UNDER_MAINTENANCE);
				break;
			default:
				System.out.println("Wrong Input, Please Key in No. 1 - 4 Only!");
				PromptRoomStatus(r);
				break;
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			PromptRoomStatus(r);
		}
	}

	// ===========================================================================================================================================================//
	// Display Room Detail
	// ===========================================================================================================================================================//

	/**
	 * <h1>displayCreateRoomDetail</h1>
	 * <p>
	 * {@code private void displayCreateRoomDetail()}
	 * </p>
	 * <p>
	 * This method display all information about room and write it to class
	 * </p>
	 */
	private void displayCreateRoomDetail() {
		ArrayList<Room> list = new ArrayList<Room>(48);
		RoomBoundary.printCreateRoomDetail(r);

		Room p = new Room(r.getRoomNum(), r.getRoomType(), r.getweekdayRoomRate(), r.getweekendRoomRate(),
				r.isWifiEnabled(), r.getSmoke(), r.getBedType(), r.getFacingView(), r.getRoomStatus());

		list.add(p);
		rwf.writeRoomToFile(list);
		System.out.println("Room created successfully!!\n");

	}

	// ===========================================================================================================================================================//
	// Update Room Detail
	// ===========================================================================================================================================================//

	/**
	 * <h1>updateRoom</h1>
	 * <p>
	 * {@code private void updateRoom()}
	 * </p>
	 * <p>
	 * This method display all information about room and write it to class
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 * @exception InputMismatchExcept
	 *                - input doesn't match with choice
	 */
	private void updateRoom() {
		int n = 0;
		ArrayList<Room> results;
		int updatechoice;
		String input;

		try {
			results = rwf.ReadRoomFile();
			RoomBoundary.printUpdateRoomDetailHeader();
			for (int i = 0; i < results.size(); i++) {
				r = (Room) results.get(i);
				RoomBoundary.printAllRoomNumber(r, n);
				n++;
			}

			if (in.hasNextInt()) {
				int roomchoice = in.nextInt();
				if (roomchoice == 0)
					selection();
				else if (roomchoice <= results.size() && roomchoice > 0) {
					RoomBoundary.printRoomDetailsForUpdate(results, roomchoice);

					while (true) {
						try {
							in.nextLine();
							System.out.println("Select no. 0 - 8 to update the details");
							updatechoice = in.nextInt();

							if (updatechoice == 1) {
								PromptRoomType(results.get(roomchoice - 1));
								System.out.println("Room has been successfully updated!!");
								break;
							} else if (updatechoice == 2) {
								PromptWeekdayRoomRate(results.get(roomchoice - 1));
								System.out.println("Room has been successfully updated!!");
								break;
							} else if (updatechoice == 3) {
								PromptWeekendRoomRate(results.get(roomchoice - 1));
								System.out.println("Room has been successfully updated!!");
								break;
							} else if (updatechoice == 4) {
								PromptWifiEnabled(results.get(roomchoice - 1));
								System.out.println("Room has been successfully updated!!");
								break;
							} else if (updatechoice == 5) {
								PromptSmokeAllowed(results.get(roomchoice - 1));
								System.out.println("Room has been successfully updated!!");
								break;
							} else if (updatechoice == 6) {
								PromptBedType(results.get(roomchoice - 1));
								System.out.println("Room has been successfully updated!!");
								break;
							} else if (updatechoice == 7) {
								PromptFacingView(results.get(roomchoice - 1));
								System.out.println("Room has been successfully updated!!");
								break;
							} else if (updatechoice == 8) {
								PromptRoomStatus(results.get(roomchoice - 1));
								System.out.println("Room has been successfully updated!!");
								break;
							} else if (updatechoice == 0) {
								updateRoom();
							} else {
								System.out.println("Wrong input, please enter no. 0-8 only!");
							}
						} catch (InputMismatchException e) {
							System.out.println("You did not enter a valid value. Please Enter a Integer Value!!");
						}
					}
				} else {
					System.out.println("No such options! Please try again!");
					updateRoom();
				}

				File file = new File("room.dat");
				if (file.exists())
					file.delete();

				rwf.writeRoomToFile(results);
				RoomBoundary.printRoomDetailsForUpdate(results, roomchoice);

			} else {
				input = in.nextLine();
				RoomBoundary.printInvalidDigitMessage(input);
				updateRoom();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ===========================================================================================================================================================//
	// Display All Room Detail
	// ===========================================================================================================================================================//

	/**
	 * <h1>displayAllRoomDetails</h1>
	 * <p>
	 * {@code private void displayAllRoomDetails()}
	 * </p>
	 * <p>
	 * This method display all information about room
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 */
	private void displayAllRoomDetails() {
		ArrayList<Room> results;
		int n = 0;
		Room p = null;
		String input, inputS;

		try {
			results = rwf.ReadRoomFile();
			RoomBoundary.roomDisplayHeader();
			for (int i = 0; i < results.size(); i++) {
				p = (Room) results.get(i);
				String rt = String.valueOf(p.getRoomType());
				String bt = String.valueOf(p.getBedType());
				String fv = String.valueOf(p.getFacingView());
				String rs = String.valueOf(p.getRoomStatus());
				if (p.isWifiEnabled())
					input = "yes";
				else
					input = "no";

				if (p.getSmoke())
					inputS = "yes";
				else
					inputS = "no";
				RoomBoundary.displayAllRoomDetails(p, input, inputS, rt, bt, fv, rs, n);
				// System.out.format("%5d %15s %15s %15.2f %15.2f %15s %15s %15s %20s %20s", (n
				// + 1), p.getRoomNum(), rt,
				// p.getweekdayRoomRate(), p.getweekendRoomRate(), input, inputS, bt, fv, rs);
				// System.out.println();
				n++;

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>DisplayVacantRoomsForWalkIn</h1>
	 * <p>
	 * {@code public ArrayList<Room> DisplayVacantRoomsForWalkIn()}
	 * </p>
	 * <p>
	 * This method display vacant room
	 * </p>
	 * 
	 * @return - list of vacant room
	 */
	public ArrayList<Room> DisplayVacantRoomsForWalkIn() {

		ArrayList<Room> results1 = new ArrayList<Room>();
		ArrayList<Room> list = new ArrayList<Room>();
		try {
			results1 = rwf.ReadRoomFile();
			Room ca = null;
			for (int i = 0; i < results1.size(); i++) {
				ca = (Room) results1.get(i);
				if (ca.getRoomStatus() == RoomStatus.VACANT) {
					list.add(ca);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	// ===========================================================================================================================================================//
	// Print Room Stat Detail
	// ===========================================================================================================================================================//

	/**
	 * <h1>printRoomStatReport</h1>
	 * <p>
	 * {@code public void printRoomStatReport()}
	 * </p>
	 * <p>
	 * This method display all vacant room
	 * </p>
	 */
	public void printRoomStatReport() {
		String input;
		RoomBoundary.printRoomStatReportChoice();
		if (in.hasNextInt()) {
			int reportchoice = in.nextInt();

			if (reportchoice >= 0 && reportchoice <= 2) {
				switch (reportchoice) {
				case 1:
					DisplaySingleVacantRoom();
					DisplayDoubleVacantRoom();
					DisplayDeluxeVacantRoom();
					DisplayVIPVacantRoom();
					break;
				case 2:
					DisplayAllVacantRoom();
					DisplayAllOccupiedRoom();
					DisplayAllReservedRoom();
					DisplayAllUnderMaintenanceRoom();
					break;
				case 0:
					selection();
					break;
				default:
					RoomBoundary.printWrongInputMessage();
					printRoomStatReport();
					break;
				}
			} else {
				RoomBoundary.printWrongInputMessage();
				printRoomStatReport();
			}

		} else {
			input = in.nextLine();
			RoomBoundary.printInvalidDigitMessage(input);
			printRoomStatReport();
		}
	}

	/**
	 * <h1>DisplaySingleVacantRoom</h1>
	 * <p>
	 * {@code private void DisplaySingleVacantRoom()}
	 * </p>
	 * <p>
	 * This method display all vacant single room
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 */
	private void DisplaySingleVacantRoom() {
		ArrayList<Room> roomDetails;
		Room rm = null;
		int rmcount = 0;
		int rmstatus = 0;
		int n = 0;
		try {
			roomDetails = rwf.ReadRoomFile();

			RoomBoundary.printSingleVacantRoomHeader();

			for (int i = 0; i < roomDetails.size(); i++) {
				rm = (Room) roomDetails.get(i);
				if (rm.getRoomType() == RoomType.SINGLE) {
					rmcount++;
					if (rm.getRoomStatus() == RoomStatus.VACANT) {
						rmstatus++;
						// System.out.println(rm.getRoomNum());
						String rt = String.valueOf(rm.getRoomType());
						RoomBoundary.displayVacantRoom(rm, rt, n);
						n++;
					}
				}
			}
			RoomBoundary.printSummaryVacantRoomCount(rmstatus, rmcount);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * <h1>DisplayDoubleVacantRoom</h1>
	 * <p>
	 * {@code private void DisplayDoubleVacantRoom()}
	 * </p>
	 * <p>
	 * This method display all vacant double room
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 */
	private void DisplayDoubleVacantRoom() {
		ArrayList<Room> roomDetails;
		Room rm = null;
		int rmcount = 0;
		int rmstatus = 0;
		int n = 0;

		try {
			roomDetails = rwf.ReadRoomFile();

			RoomBoundary.printDoubleVacantRoomHeader();
			for (int i = 0; i < roomDetails.size(); i++) {
				rm = (Room) roomDetails.get(i);
				if (rm.getRoomType() == RoomType.DOUBLE) {
					rmcount++;
					if (rm.getRoomStatus() == RoomStatus.VACANT) {
						rmstatus++;
						String rt = String.valueOf(rm.getRoomType());
						RoomBoundary.displayVacantRoom(rm, rt, n);
						n++;
					}
				}
			}
			RoomBoundary.printSummaryVacantRoomCount(rmstatus, rmcount);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>DisplayDeluxeVacantRoom</h1>
	 * <p>
	 * {@code private void DisplayDeluxeVacantRoom()}
	 * </p>
	 * <p>
	 * This method display all vacant deluxe room
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 */
	private void DisplayDeluxeVacantRoom() {
		ArrayList<Room> roomDetails;
		Room rm = null;
		int rmcount = 0;
		int rmstatus = 0;
		int n = 0;

		try {

			roomDetails = rwf.ReadRoomFile();

			RoomBoundary.printDeluxeVacantRoomHeader();
			for (int i = 0; i < roomDetails.size(); i++) {
				rm = (Room) roomDetails.get(i);
				if (rm.getRoomType() == RoomType.DELUXE) {
					rmcount++;
					if (rm.getRoomStatus() == RoomStatus.VACANT) {
						rmstatus++;
						String rt = String.valueOf(rm.getRoomType());
						RoomBoundary.displayVacantRoom(rm, rt, n);
						n++;
					}
				}
			}
			RoomBoundary.printSummaryVacantRoomCount(rmstatus, rmcount);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>DisplayVIPVacantRoom</h1>
	 * <p>
	 * {@code private void DisplayVIPVacantRoom()}
	 * </p>
	 * <p>
	 * This method display all vacant VIP room
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 */
	private void DisplayVIPVacantRoom() {
		ArrayList<Room> roomDetails;
		Room rm = null;
		int rmcount = 0;
		int rmstatus = 0;
		int n = 0;

		try {

			roomDetails = rwf.ReadRoomFile();

			RoomBoundary.printVIPVacantRoomHeader();
			for (int i = 0; i < roomDetails.size(); i++) {
				rm = (Room) roomDetails.get(i);
				if (rm.getRoomType() == RoomType.VIP_SUITE) {
					rmcount++;
					if (rm.getRoomStatus() == RoomStatus.VACANT) {
						rmstatus++;
						String rt = String.valueOf(rm.getRoomType());
						RoomBoundary.displayVacantRoom(rm, rt, n);
						n++;
					}
				}
			}
			RoomBoundary.printSummaryVacantRoomCount(rmstatus, rmcount);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>DisplayAllVacantRoom</h1>
	 * <p>
	 * {@code private void DisplayAllVacantRoom()}
	 * </p>
	 * <p>
	 * This method display all vacant room
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 */
	private void DisplayAllVacantRoom() {
		ArrayList<Room> roomDetails;
		Room rm = null;
		int rmstatus = 0;
		int n = 0;

		try {
			roomDetails = rwf.ReadRoomFile();

			RoomBoundary.printAllVacantRoomHeader();
			for (int i = 0; i < roomDetails.size(); i++) {
				rm = (Room) roomDetails.get(i);
				if (rm.getRoomStatus() == RoomStatus.VACANT) {
					rmstatus++;
					String rt = String.valueOf(rm.getRoomType());
					RoomBoundary.displayVacantRoom(rm, rt, n);
					n++;
				}
			}
			RoomBoundary.printSummaryRoomStatusCount(rmstatus);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>DisplayAllOccupiedRoom</h1>
	 * <p>
	 * {@code private void DisplayAllOccupiedRoom()}
	 * </p>
	 * <p>
	 * This method display all occupied room
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 */
	private void DisplayAllOccupiedRoom() {
		ArrayList<Room> roomDetails;
		Room rm = null;
		int rmstatus = 0;
		int n = 0;

		try {
			roomDetails = rwf.ReadRoomFile();

			RoomBoundary.printAllOccupiedRoomHeader();
			for (int i = 0; i < roomDetails.size(); i++) {
				rm = (Room) roomDetails.get(i);
				if (rm.getRoomStatus() == RoomStatus.OCCUPIED) {
					rmstatus++;
					String rt = String.valueOf(rm.getRoomType());
					RoomBoundary.displayVacantRoom(rm, rt, n);
					n++;
				}
			}
			RoomBoundary.printSummaryRoomStatusCount(rmstatus);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>DisplayAllReservedRoom</h1>
	 * <p>
	 * {@code private void DisplayAllReservedRoom()}
	 * </p>
	 * <p>
	 * This method display all reserved room
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 */
	private void DisplayAllReservedRoom() {
		ArrayList<Room> roomDetails;
		Room rm = null;
		int rmstatus = 0;
		int n = 0;

		try {
			roomDetails = rwf.ReadRoomFile();

			RoomBoundary.printAllReservedRoomHeader();
			for (int i = 0; i < roomDetails.size(); i++) {
				rm = (Room) roomDetails.get(i);
				if (rm.getRoomStatus() == RoomStatus.RESERVED) {
					rmstatus++;
					// System.out.println(rm.getRoomNum() + " ");
					String rt = String.valueOf(rm.getRoomType());
					RoomBoundary.displayVacantRoom(rm, rt, n);
					n++;
				}
			}
			RoomBoundary.printSummaryRoomStatusCount(rmstatus);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>DisplayAllUnderMaintenanceRoom</h1>
	 * <p>
	 * {@code private void DisplayAllUnderMaintenanceRoom()}
	 * </p>
	 * <p>
	 * This method display all under maintenance room
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 */
	private void DisplayAllUnderMaintenanceRoom() {
		ArrayList<Room> roomDetails;
		Room rm = null;
		int rmstatus = 0;
		int n = 0;

		try {
			roomDetails = rwf.ReadRoomFile();
			RoomBoundary.printAllUnderMaintenanceRoomHeader();

			for (int i = 0; i < roomDetails.size(); i++) {
				rm = (Room) roomDetails.get(i);
				if (rm.getRoomStatus() == RoomStatus.UNDER_MAINTENANCE) {
					rmstatus++;
					// System.out.println(rm.getRoomNum() + " ");
					String rt = String.valueOf(rm.getRoomType());
					RoomBoundary.displayVacantRoom(rm, rt, n);
					n++;
				}
			}
			RoomBoundary.printSummaryRoomStatusCount(rmstatus);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ===========================================================================================================================================================//
	// Check Availability Detail
	// ===========================================================================================================================================================//
	/**
	 * <h1>CheckAvailability</h1>
	 * <p>
	 * {@code private void CheckAvailability()}
	 * </p>
	 * <p>
	 * This method display available room number and guest
	 * </p>
	 */
	private void CheckAvailability() {

		String input;
		RoomBoundary.printCheckAvailabilityChoice();

		if (in.hasNextInt()) {
			int choice = in.nextInt();
			if (choice >= 0 && choice <= 2) {
				switch (choice) {
				case 1:
					CheckAvailRoomNo();
					break;
				case 2:
					CheckAvailGuest();
					break;
				case 0:
					selection();
					break;
				default:
					System.out.println("You did not enter a valid value. Please enter the correct value!");
					CheckAvailability();
					break;
				}
			} else {
				System.out.println("You did not enter a valid value. Please enter the correct value!");
				CheckAvailability();
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			System.out.println("You did not enter a valid value. Please enter the correct value!");
			CheckAvailability();
		}
	}

	/**
	 * <h1>CheckAvailGuest</h1>
	 * <p>
	 * {@code private void CheckAvailGuest()}
	 * </p>
	 * <p>
	 * This method check all available guest
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find reservation.dat and guest.dat
	 */
	private void CheckAvailGuest() {
		boolean check = false;
		boolean check2 = true;
		String test = null;
		int count = 1;
		ArrayList<Guest> searchlist = new ArrayList<Guest>();
		ArrayList<Guest> guestlist;
		ArrayList<Reservation> reservationlist;

		try {
			guestlist = rwf.readGuestFile();
			reservationlist = rwf.readReservationFile();
			System.out.println("Search Guest Name using keywords:");
			String gg = in.nextLine();
			do {

				System.out.println("---------------------");
				System.out.printf("%5s %15s", "No.", "Guest Name");
				System.out.println();
				System.out.println("---------------------");
				for (int i = 0; i < guestlist.size(); i++) {
					Guest g1 = guestlist.get(i);
					if (g1.getName().toLowerCase().contains(gg.toLowerCase())) {
						System.out.format("%4d %15s", count, g1.getName());
						searchlist.add(g1);
						System.out.println();
						count++;
					}
				}
				System.out.println();

				System.out.println("Select the Guest you want to check: ");
				if (in.hasNextInt()) {
					int guestinput = in.nextInt();
					test = searchlist.get(guestinput - 1).getName();
					check2 = true;
				} else {
					System.out.println("You have entered invalid input. Please enter number only!");
					check2 = false;
					count = 1;
					in.next();
				}
			} while (!check2);

			for (int i = 0; i < reservationlist.size(); i++) {
				Reservation res = reservationlist.get(i);
				for (int g = 0; g < res.getG().size(); g++) {
					if (test.matches(res.getG().get(g).getName())) {
						check = true;
						RoomBoundary.printReservationlistHeader(res, g);

						for (int r = 0; r < res.getR().size(); r++) {
							if (res.getG().get(g).getName().matches(test)) {
								RoomBoundary.printReservationRoomDetails(res, r);
								// System.out.format("%7s %23s", res.getR().get(r).getRoomNum(),
								// res.getR().get(r).getRoomStatus());
								// System.out.println();
							}
						}

					}

				}

			}
			if (check == false)
				System.out.println("Guest has no Rooms");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>CheckAvailRoomNo</h1>
	 * <p>
	 * {@code private void CheckAvailRoomNo()}
	 * </p>
	 * <p>
	 * This method check room availability
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * 
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 */
	private void CheckAvailRoomNo() {

		ArrayList<Room> list = new ArrayList<Room>();
		Room r1 = null;
		String input, inputS;

		try {
			list = rwf.ReadRoomFile();
			while (true) {
				System.out.println("Enter the Room Number to check for availability");
				String rn = in.next();
				if (rn.matches("^0[2-7]-0[1-8]$")) {
					for (int i = 0; i < list.size(); i++) {
						r1 = (Room) list.get(i);
						if (r1.getRoomNum().equals(rn)) {
							RoomBoundary.printCurrentRoomStatus(r1);
							RoomBoundary.RoomDisplayHeaderWithoutStatus();
							String rt = String.valueOf(r1.getRoomType());
							String bt = String.valueOf(r1.getBedType());
							String fv = String.valueOf(r1.getFacingView());

							if (r1.isWifiEnabled())
								input = "yes";
							else
								input = "no";

							if (r1.getSmoke())
								inputS = "yes";
							else
								inputS = "no";
							RoomBoundary.displayAllRoomDetailsWithoutStatus(r1, rt, input, inputS, bt, fv);
						}
					}
					break;
				} else {
					System.out.println("No Such Room/Wrong Format. Please enter the room number again! E.g. 02-01");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>DisplayVacantRooms</h1>
	 * <p>
	 * {@code private void DisplayVacantRooms()}
	 * </p>
	 * <p>
	 * This method display all vacant rooms
	 * </p>
	 * 
	 * @return - Return vacant room list
	 */
	public ArrayList<Room> DisplayVacantRooms() {

		String input, inputS;
		ArrayList<Room> results = new ArrayList<Room>();
		ArrayList<Room> results1 = new ArrayList<Room>();
		ArrayList<Room> list = new ArrayList<Room>();
		int rmstatus = 0;

		RoomBoundary.printCheckVacancyChoice();
		int availablechoice = in.nextInt();

		switch (availablechoice) {
		case 1:
			int n = 0;

			try {
				results1 = rwf.ReadRoomFile();
				Room ca = null;
				RoomBoundary.AvailableVacantRoomHeader();
				RoomBoundary.RoomDisplayHeaderWithoutStatus();
				for (int i = 0; i < results1.size(); i++) {
					ca = (Room) results1.get(i);
					if (ca.getRoomStatus() == RoomStatus.VACANT) {
						list.add(ca);
						rmstatus++;
						String rt = String.valueOf(ca.getRoomType());
						String bt = String.valueOf(ca.getBedType());
						String fv = String.valueOf(ca.getFacingView());
						if (ca.isWifiEnabled())
							input = "yes";
						else
							input = "no";

						if (ca.getSmoke())
							inputS = "yes";
						else
							inputS = "no";
						RoomBoundary.displayAllVacantRoomDetails(ca, rt, input, inputS, bt, fv, n);
						n++;
					}
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			break;

		case 2:
			while (true) {
				System.out.println(
						"Select room type you want to check: \n 1. SINGLE\n 2. DOUBLE\n 3. DELUXE\n 4. VIP SUITE\n");
				int choice = in.nextInt();

				if (choice == 1) {
					DisplaySingleVacantRoom();
					break;
				} else if (choice == 2) {
					DisplayDoubleVacantRoom();
					break;
				} else if (choice == 3) {
					DisplayDeluxeVacantRoom();
					break;
				} else if (choice == 4) {
					DisplayVIPVacantRoom();
					break;
				} else {
					System.out.println("Please Select Option 1 - 4 only!");
				}
			}
			break;
		}

		System.out.println("How many rooms to reserve?");
		int choice = in.nextInt();
		if (choice != 0) {
			for (int i = 0; i < choice; i++) {
				System.out.println("Select S/No. to reserve Room");
				int c = in.nextInt();
				results.add(list.get(c - 1));
			}
		}
		return results;
	}

}
