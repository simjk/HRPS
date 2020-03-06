package control;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import control.MainControl;
import control.CheckInControl;
import control.GuestControl;
import control.ReservationControl;
import control.RoomControl;
import control.RoomServiceControl;
import entity.FoodItem;
import entity.Guest;
import entity.Reservation;
import entity.Room;
import entity.RoomService;
import entity.Reservation.Status;
import entity.Room.RoomStatus;

/**
 * <h1>CheckOutControl</h1>
 * <p>
 * The program implements methods that prompt for payment, print bill receipt
 * before checking out.
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */
public class CheckOutControl implements Serializable, Control {

	Scanner in = new Scanner(System.in);
	CheckInControl cic = new CheckInControl();
	GuestControl gc = new GuestControl();
	ReservationControl rc = new ReservationControl();
	RoomControl rm = new RoomControl();
	RoomServiceControl rser = new RoomServiceControl();
	readWriteFile rwf = new readWriteFile();

	/**
	 * <h1>CheckOutControl</h1>
	 * <p>
	 * This method is used to call other CheckOutControl methods in other classes
	 * </p>
	 */
	public CheckOutControl() {
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
	 * into checkOutInterface method.
	 * </p>
	 */
	private void selection() {
		int choice = 0;
		String input;
		Scanner in = new Scanner(System.in);

		System.out.println("------------------------------------------------------------");
		System.out.println("================== HRPS: Check Out System ==================");
		System.out.println("------------------------------------------------------------");
		System.out.println("1. Make Payment" + "\n2. Print Room Status Statistics Report" + "\n0. Quit");
		System.out.println("------------------------------------------------------------");

		if (in.hasNextInt()) {
			choice = in.nextInt();

			if (choice != 0)
				checkOutInterface(choice);
			else
				MainControl.displayUI();
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			selection();
		}
		in.close();
	}

	/**
	 * <h1>checkOutInterface</h1>
	 * <p>
	 * checkOutInterface take in {@value control#choice} and call other methods.
	 * </p>
	 * 
	 * @param choice
	 *            - choose between make payment/print room statistic report
	 */
	private void checkOutInterface(int choice) {
		switch (choice) {
		case 1:
			makePayment();
			System.out.println("Enter 0 to return to Check Out System Menu");
			int choice1 = in.nextInt();
			if (choice1 == 0)
				selection();
			break;
		case 2:
			rm.printRoomStatReport();
			System.out.println("Enter 0 to return to Check Out System Menu");
			int choice2 = in.nextInt();
			if (choice2 == 0)
				selection();
			break;
		case 0:
			MainControl.displayUI();
			break;
		default:
			System.out.println("Wrong input. Please input values within no. 0 - 2 only!\n");
			selection();
			break;
		}
	}

	// ===========================================================================================================================================================//
	// PAYMENT
	// ===========================================================================================================================================================//
	/**
	 * <h1>makePayment</h1>
	 * <p>
	 * {@code private void makePayment()}
	 * </p>
	 * <p>
	 * This method calls other methods for two different version - For Reservation
	 * Guest or Walk-in Guest
	 * </p>
	 */
	private void makePayment() {
		int choice = 0;
		String input;
		System.out.println("Choose a guest type: ");
		System.out.println("1. Reservation Guest" + "\n2. WalkIn Guest");
		if (in.hasNextInt()) {
			choice = in.nextInt();
			if (choice == 1) {
				printTotalBill("reservation"); // reservation version
			} else if (choice == 2) {
				printTotalBill("walkin"); // walkIn version
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			selection();
		}
		System.out.println();
	}

	/**
	 * <h1>printTotalBill</h1>
	 * <p>
	 * {@code private void printTotalBill(String from)}
	 * </p>
	 * <p>
	 * This method calls for other methods to get reservation/walk-in details,
	 * compile total bill, print receipt set Room Status to VACANT, set reservation
	 * status to CHECKED_OUT
	 * </p>
	 * 
	 * @param from
	 *            - contains where the method is being called from
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat/reservation.dat/CheckIn.dat
	 */
	private void printTotalBill(String from) { // get reservation data via checkIn file

		String input;
		ArrayList<ArrayList> checkInList;
		ArrayList<Room> wholeRoomList;

		if (from.equalsIgnoreCase("reservation")) {
			try {
				checkInList = rwf.readCheckInDetailsToFile();
				// ArrayList<ArrayList<Reservation>> reserList = rwf.readCheckInDetailsToFile();
				ArrayList<Reservation> res = new ArrayList<Reservation>();
				wholeRoomList = rwf.ReadRoomFile();
				ArrayList<Reservation> wholeReserList = rwf.readReservationFile();
				// ArrayList roomList = new ArrayList();

				System.out.println("Enter Guest Identity: ");
				String identity = in.next();

				for (int q = 0; q < checkInList.size(); q += 2) {
					res = checkInList.get(q);
					ArrayList<String> check_In = checkInList.get(q + 1);
					for (int w = 0; w < res.size(); w++) {
						Reservation resObj = res.get(w);
						for (int e = 0; e < resObj.getG().size(); e++) {
							ArrayList<Guest> guestObj = resObj.getG();

							if (guestObj.get(e).getIdentity().contains(identity)) {
								System.out.println();
								System.out.println(
										"Is " + guestObj.get(e).getName() + " checking out?" + "\n1. Yes" + "\n2. No");
								if (in.hasNextInt()) {
									int choice = in.nextInt();
									if (choice == 1) { // Yes
										printReceipt(resObj, check_In, null);
										for (int i = 0; i < wholeRoomList.size(); i++) {
											for (int k = 0; k < resObj.getR().size(); k++) {
												if (wholeRoomList.get(i).getRoomNum()
														.contains(resObj.getR().get(k).getRoomNum())) {
													wholeRoomList.get(i).setRoomStatus(RoomStatus.VACANT);
												}
											}
										}

										for (int i = 0; i < wholeReserList.size(); i++) {
											for (int k = 0; k < resObj.getR().size(); k++) {
												if (wholeReserList.get(i)
														.getReservationNo() == (resObj.getReservationNo())) {
													wholeReserList.get(i).setStatus(Status.CHECKED_OUT);
												}
											}
										}
										File file = new File("room.dat");
										if (file.exists())
											file.delete();
										rwf.writeRoomToFile(wholeRoomList);
										file = new File("reservation.dat");
										if (file.exists())
											file.delete();
										rwf.writeReservationToFile(wholeReserList);

									} else if (choice == 2) {// No
										selection();
									} else {
										System.out.println("Wrong input, please input the correct value");
									}
								} else {
									input = in.nextLine();
									System.out.printf("\"%s\" is not a valid digit.\n\n", input);
									printTotalBill(from);
								}
							}
						}
					}
				}
				System.out.printf("Payment has been successfully made via %s\n", promptPaymentMode());
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		} else if (from.equalsIgnoreCase("walkin")) {
			try {

				System.out.println("Enter Guest Identity");
				String identity = in.next();

				ArrayList<ArrayList> walkIn = rwf.readWalkInFile();
				ArrayList<Guest> walkInGList = new ArrayList<Guest>();
				ArrayList<Room> walkInRList = new ArrayList<Room>();
				ArrayList<String> walkInCheckInList;
				ArrayList<Date> walkInCheckOutList;
				ArrayList<ArrayList> selectedWalkIn = new ArrayList<ArrayList>();
				Guest g = new Guest();
				wholeRoomList = rwf.ReadRoomFile();

				for (int i = 0; i < walkIn.size(); i += 4) {
					walkInGList = walkIn.get(i);
					walkInRList = walkIn.get(i + 1);
					walkInCheckInList = walkIn.get(i + 2);
					walkInCheckOutList = walkIn.get(i + 3);

					for (int k = 0; k < walkInGList.size(); k++) {
						g = walkInGList.get(k);
						if (g.getIdentity().matches(identity)) {
							selectedWalkIn.add(walkInGList);
							selectedWalkIn.add(walkInRList);
							selectedWalkIn.add(walkInCheckInList);
							selectedWalkIn.add(walkInCheckOutList);
							printReceipt(null, null, selectedWalkIn);

							for (int i1 = 0; i1 < wholeRoomList.size(); i1++) {
								for (int k1 = 0; k1 < walkInRList.size(); k1++) {
									if (wholeRoomList.get(i1).getRoomNum().contains(walkInRList.get(k1).getRoomNum())) {
										wholeRoomList.get(i1).setRoomStatus(RoomStatus.VACANT);
									}
								}
							}
							File file = new File("room.dat");
							if (file.exists())
								file.delete();
							rwf.writeRoomToFile(wholeRoomList);

						}
					}
				}
				System.out.printf("Payment has been successfully made via %s\n", promptPaymentMode());
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <h1>printReceipt</h1>
	 * <p>
	 * {@code private void printReceipt((Reservation reser, ArrayList<String> checkInString, ArrayList<ArrayList> walkIn)}
	 * </p>
	 * <p>
	 * This method calculates the total bill including discount, tax, number of
	 * stays and room service
	 * </p>
	 * 
	 * @param reser
	 *            - contains the guest's reservation details
	 * @param checkInString
	 *            - contains the check-in and check-out date
	 * @param walkIn
	 *            - contains the guest's walk-in details
	 */
	private void printReceipt(Reservation reser, ArrayList<String> checkInString, ArrayList<ArrayList> walkIn) {

		double total;
		double discount = promptDiscountRate();
		int tax = 7;

		System.out.println("------------------------------------------------------------");
		System.out.println("========================= Receipt ==========================");
		System.out.println("------------------------------------------------------------");

		if (walkIn == null) {
			System.out.println("Guest name: 		" + reser.getG().get(0).getName());
			System.out.print("Room(s):		");
			for (int i = 0; i < reser.getR().size(); i++) {
				System.out.print(reser.getR().get(i).getRoomNum() + " ");
			}

			System.out.println();
			System.out.println("Check In Date:		" + checkInString.get(0));

			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
			System.out.println("Check Out Date: 	" + format.format(reser.getCheckOut()));

			cal = formatCheckIn(checkInString.get(0));
			Calendar cal2 = formatCheckIn(checkInString.get(0));

			Date checkIn = new Date();
			checkIn = cal.getTime();

			int numOfDays = (int) ((reser.getCheckOut().getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24)) + 1;

			System.out.println("Number of Days stayed:  " + numOfDays);

			Calendar checkO = Calendar.getInstance();
			checkO.setTime(reser.getCheckOut());
			double rmCharge = calculateRoomCharge(reser.getR(), cal, checkO);
			double rmService = calculateRoomService(reser.getR(), cal2, checkO);
			System.out.printf("Room Charge: 		$%.2f\n", rmCharge);
			System.out.printf("Room Service: 		$%.2f\n", rmService);

			total = (rmCharge + rmService) * ((100 - discount) / 100) * 1.07;
			if (discount != 0) {
				System.out.printf("Total:			$%.2f (after %.2f%% discount and %d" + "%% tax)\n", total, discount,
						tax);
			} else {
				System.out.printf("Total:			$%.2f (after %d%% tax)\n", total, tax);
			}

		} else if (reser == null && checkInString == null) {

			ArrayList<Guest> guestList = new ArrayList<Guest>();
			ArrayList<Room> roomList = new ArrayList<Room>();
			ArrayList<String> checkIn = new ArrayList<String>();
			ArrayList<Date> checkOut = new ArrayList<Date>();

			for (int i = 0; i < walkIn.size(); i += 4) {

				guestList = walkIn.get(i);
				roomList = walkIn.get(i + 1);
				checkIn = walkIn.get(i + 2);
				checkOut = walkIn.get(i + 3);

				for (int k = 0; k < guestList.size(); k++) {
					System.out.println("Guest name: 		" + guestList.get(k).getName());

				}
				System.out.print("Room(s):		");
				for (int j = 0; i < roomList.size(); i++) {
					System.out.print(roomList.get(j).getRoomNum() + " ");
				}

				System.out.println();
				System.out.println("Check In Date:		" + checkIn.get(0));

				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
				System.out.println("Check Out Date: 	" + format.format(checkOut.get(0)));

				cal = formatCheckIn(checkIn.get(0));
				Calendar cal2 = formatCheckIn(checkIn.get(0));

				Date checkIn_1 = new Date();
				checkIn_1 = cal.getTime();

				int numOfDays = (int) ((checkOut.get(0).getTime() - checkIn_1.getTime()) / (1000 * 60 * 60 * 24)) + 1;

				System.out.println("Number of Days stayed:  " + numOfDays);

				Calendar checkO = Calendar.getInstance();
				checkO.setTime(checkOut.get(0));

				double rmCharge = calculateRoomCharge(roomList, cal, checkO);
				double rmService = calculateRoomService(roomList, cal2, checkO);
				System.out.printf("Room Charge: 		$%.2f\n", rmCharge);
				System.out.printf("Room Service: 		$%.2f\n", rmService);

				total = (rmCharge + rmService) * ((100 - discount) / 100) * 1.07;
				if (discount != 0) {
					System.out.printf("Total:			$%.2f (after %.2f%% discount and %d" + "%% tax)\n", total,
							discount, tax);
				} else {
					System.out.printf("Total:			$%.2f (after %d%% tax)\n", total, tax);
				}
			}
		}
		System.out.println("------------------------------------------------------------");

	}

	/**
	 * <h1>promptDiscountRate</h1>
	 * <p>
	 * {@code private double promptDiscountRate()}
	 * </p>
	 * <p>
	 * This method prompts for discount rate
	 * </p>
	 * 
	 * @return discount - returns the discount rate
	 */
	private double promptDiscountRate() {
		int choice = 0;
		double discount = 0.0;
		String input;
		System.out.println("Apply discount? ");
		System.out.println("1. Yes" + "\n2. No");
		if (in.hasNextInt()) {
			choice = in.nextInt();
			if (choice == 1) {
				System.out.println("Enter discount rate: (Eg. 20%)");
				if (in.hasNextDouble()) {
					discount = in.nextDouble();
				} else {
					input = in.nextLine();
					System.out.printf("\"%s\" is not a valid digit.\n\n", input);
				}
				if (choice == 2) {
					discount = 0;
				}
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
		}
		return discount;
	}

	/**
	 * <h1>calculateRoomService</h1>
	 * <p>
	 * {@code private double calculateRoomService(ArrayList<Room> roomList, Calendar checkIn, Calendar checkOut)}
	 * </p>
	 * <p>
	 * This method collects Room Service Order details and compile the bill
	 * </p>
	 * 
	 * @param roomlist
	 *            - contains room details
	 * @param checkIn
	 *            - contains check-in date
	 * @param checkOut
	 *            - contains check-out date
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find RoomServiceOrder.dat
	 * @return total - returns the total room service bill
	 */
	private double calculateRoomService(ArrayList<Room> roomList, Calendar checkIn, Calendar checkOut) {

		ArrayList<RoomService> wholeRoomServiceList = new ArrayList<RoomService>();
		double total = 0.0;
		String orderDateString;
		Date orderDate;

		int i = 0, j = 0, k = 0, m = 0;
		try {
			wholeRoomServiceList = rwf.readRoomServiceOrder();
			RoomService ca = null;
			FoodItem fi = new FoodItem();

			for (i = 0; i < roomList.size(); i++) { // roomList.size = 2

				for (j = 0; j < wholeRoomServiceList.size(); j++) { // wholeRoomServiceList.size = 2
					ca = (RoomService) wholeRoomServiceList.get(j);
					if ((ca.getRoomnum()).contains(roomList.get(i).getRoomNum())) { // same room num
						orderDateString = ca.getOrderDate();
						orderDate = formatCheckIn(orderDateString).getTime();
						if ((orderDate.compareTo(checkIn.getTime()) == 1)
								&& (checkOut.getTime().compareTo(orderDate)) == 1) {
							// calculate room service
							for (k = 0; k < ca.getuserFoodItem().size(); k++) {
								fi = ca.getuserFoodItem().get(k);
								for (m = 0; m < fi.getQuantity(); m++) {
									total += fi.getPrice();
								}
							}
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * <h1>calculateRoomCharge</h1>
	 * <p>
	 * {@code private double calculateRoomCharge(ArrayList<Room> roomList, Calendar checkIn, Calendar checkOut)}
	 * </p>
	 * <p>
	 * This method calculates the total bill for room
	 * </p>
	 * 
	 * @param roomlist
	 *            - contains room details
	 * @param checkIn
	 *            - contains check-in date
	 * @param checkOut
	 *            - contains check-out date
	 * @return total - returns the total room bill
	 */
	private double calculateRoomCharge(ArrayList<Room> roomList, Calendar checkIn, Calendar checkOut) {
		double total = 0.0;

		double weekday = 0, weekend = 0;
		int numOfDays = 0;

		numOfDays = (int) ((checkOut.getTime().getTime() - checkIn.getTime().getTime()) / (1000 * 60 * 60 * 24)) + 1;

		int i = 0, k = 0;
		int dayOfWeek = 0;
		for (i = 0; i < roomList.size(); i++) {
			Room ca = roomList.get(i);

			weekday = ca.getweekdayRoomRate();
			weekend = ca.getweekendRoomRate();
			for (k = 0; k < numOfDays; k++) {
				dayOfWeek = checkIn.get(Calendar.DAY_OF_WEEK);
				if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) { // Sunday || Saturday
					total += weekend;
					// System.out.println("Weekend Total: " + total);
				} else {
					total += weekday;
					// System.out.println("Weekday Total: " + total);
				}
				checkIn.add(Calendar.DATE, 1);
			}
		}
		return total;
	}

	/**
	 * <h1>formatCheckIn</h1>
	 * <p>
	 * {@code private Calendar formatCheckIn(String checkInString)}
	 * </p>
	 * <p>
	 * This method convert string to date format
	 * </p>
	 * 
	 * @param checkInString
	 *            - contains check-in date
	 * @return cal - returns the dateformat date
	 */
	private Calendar formatCheckIn(String checkInString) {
		// Convert string to date format for checkInString
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		// Date checkIn = new Date();
		String[] a = checkInString.split(", ");
		String date = a[0];
		String time = a[1];

		String[] c = date.split("/");
		String[] d = time.split(":");

		cal.set(Calendar.YEAR, Integer.parseInt(c[2]));
		cal.set(Calendar.MONTH, Integer.parseInt(c[1]) - 1);// 0 - 11
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(c[0])); // 1 - 31
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(d[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(d[1]));

		return cal;
	}

	/**
	 * <h1>promptPaymentMode</h1>
	 * <p>
	 * {@code private String promptPaymentMode()}
	 * </p>
	 * <p>
	 * This method convert string to date format
	 * </p>
	 * 
	 * @return payment - returns the payment type
	 */
	private String promptPaymentMode() {
		String payment = null;
		int choice = 0;
		String input;
		System.out.println("------------------------------------------------------------");
		System.out.println("====================== Payment Mode ========================");
		System.out.println("------------------------------------------------------------");


		System.out.println("1. Credit card" + "\n2. Cash");
		System.out.println("------------------------------------------------------------");

		if (in.hasNextInt()) {
			choice = in.nextInt();
			if (choice == 1) {
				payment = "Credit card";
			} else if (choice == 2) {
				payment = "Cash";
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			promptPaymentMode();
		}
		return payment;
	}

}
