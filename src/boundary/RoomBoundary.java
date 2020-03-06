package boundary;

import java.util.ArrayList;

import entity.Reservation;
import entity.Room;

/**
 * <h1>Room Boundary</h1>
 * <p>
 * This class handles the display and printing of all Room information
 * <p>
 * The related entity classes are: Room, Reservation.
 * </p>
 * <p>
 * The related control class is: RoomControl.
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 13-04-2018
 */

public class RoomBoundary {

	/**
	 * <h1>displayAllRoomDetails</h1>
	 * <p>
	 * {@code public static void displayAllRoomDetails(Room p, String input, String inputS, String rt, String bt, String fv, String rs, int n)}
	 * </p>
	 * <p>
	 * This method display all room details(Room Number, Room type, WeekdayRate,
	 * WeekendRate, Wifi Enabled, Smoked Allowed, Bed Type, Facing View, Room
	 * Status)
	 * </p>
	 * 
	 * @param p
	 *            - contains all the Room information
	 * @param input
	 *            - Specified the Wifi Enabled Input
	 * @param inputS
	 *            - Specified the Smoked Allowed Input
	 * @param rt
	 *            - Specified the Room Type
	 * @param bt
	 *            - Specified the Bed Type
	 * @param fv
	 *            - Specified the Facing View
	 * @param rs
	 *            - Specified the Room Status
	 * @param n
	 *            - Specified the Counter
	 */
	public static void displayAllRoomDetails(Room p, String input, String inputS, String rt, String bt, String fv,
			String rs, int n) {
		System.out.format("%5d %15s %15s %15.2f %15.2f %15s %15s %15s %20s %20s", (n + 1), p.getRoomNum(), rt,
				p.getweekdayRoomRate(), p.getweekendRoomRate(), input, inputS, bt, fv, rs);
		System.out.println();

	}

	/**
	 * <h1>displayAllRoomDetailsWithoutStatus</h1>
	 * <p>
	 * {@code public static void displayAllRoomDetailsWithoutStatus(Room r1, String input, String inputS, String rt, String bt, String fv)}
	 * </p>
	 * <p>
	 * This method display all room details except room status(Room Number, Room
	 * type, WeekdayRate, WeekendRate, Wifi Enabled, Smoked Allowed, Bed Type,
	 * Facing View)
	 * </p>
	 * 
	 * @param r1
	 *            - contains all the Room information
	 * @param input
	 *            - Specified the Wifi Enabled Input
	 * @param inputS
	 *            - Specified the Smoked Allowed Input
	 * @param rt
	 *            - Specified the Room Type
	 * @param bt
	 *            - Specified the Bed Type
	 * @param fv
	 *            - Specified the Facing View
	 * 
	 */
	public static void displayAllRoomDetailsWithoutStatus(Room r1, String rt, String input, String inputS, String bt,
			String fv) {
		System.out.format("%5d %15s %20s %20.2f %20.2f %20s %20s %20s %20s", (1), r1.getRoomNum(), rt,
				r1.getweekdayRoomRate(), r1.getweekendRoomRate(), input, inputS, bt, fv);
		System.out.println();
	}

	/**
	 * <h1>displayAllVacantRoomDetails</h1>
	 * <p>
	 * {@code public static void displayAllVacantRoomDetails(Room ca, String input, String inputS, String rt, String bt, String fv, int n)}
	 * </p>
	 * <p>
	 * This method display all vacant room details except room status(Room Number,
	 * Room type, WeekdayRate, WeekendRate, Wifi Enabled, Smoked Allowed, Bed Type,
	 * Facing View)
	 * </p>
	 * 
	 * @param ca
	 *            - contains all the Room information
	 * @param input
	 *            - Specified the Wifi Enabled Input
	 * @param inputS
	 *            - Specified the Smoked Allowed Input
	 * @param rt
	 *            - Specified the Room Type
	 * @param bt
	 *            - Specified the Bed Type
	 * @param fv
	 *            - Specified the Facing View
	 * @param n
	 *            - Specified the Index Number
	 * 
	 */
	public static void displayAllVacantRoomDetails(Room ca, String rt, String input, String inputS, String bt,
			String fv, int n) {
		System.out.format("%5d %15s %20s %20.2f %20.2f %20s %20s %20s %20s", (n + 1), ca.getRoomNum(), rt,
				ca.getweekdayRoomRate(), ca.getweekendRoomRate(), input, inputS, bt, fv);
		System.out.println();
	}

	/**
	 * <h1>roomDisplayHeader</h1>
	 * <p>
	 * {@code public static void roomDisplayHeader()}
	 * </p>
	 * <p>
	 * This method prints out all the Room Headers.
	 * </p>
	 */
	public static void roomDisplayHeader() {
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%5s %15s %15s %15s %15s %15s %15s %15s %20s %20s", "No.", "Room No.", "Room Type",
				"Weekday Rate", "WeekendRate", "Wifi Enabled", "Smoked Allowed", "Bed Type", "Facing View",
				"Room Status");
		System.out.println();
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	/**
	 * <h1>RoomDisplayHeaderWithoutStatus</h1>
	 * <p>
	 * {@code public static void RoomDisplayHeaderWithoutStatus()}
	 * </p>
	 * <p>
	 * This method prints out all the Room Headers except room status.
	 * </p>
	 */
	public static void RoomDisplayHeaderWithoutStatus() {
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%5s %15s %20s %20s %20s %20s %20s %20s %20s", "No.", "Room No.", "Room Type", "Weekday Rate",
				"WeekendRate", "Wifi Enabled", "Smoked Allowed", "Bed Type", "Facing View");
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	/**
	 * <h1>AvailableVacantRoomHeader</h1>
	 * <p>
	 * {@code public static void AvailableVacantRoomHeader()}
	 * </p>
	 * <p>
	 * This method prints out the Available Room Title
	 * </p>
	 */
	public static void AvailableVacantRoomHeader() {
		System.out.println("------------------------------------------------------------");
		System.out.println("================ Checking All Available Room ===============");
		System.out.println("------------------------------------------------------------");
		System.out.println("List Of All Vacant Rooms:");
	}

	/**
	 * <h1>printWrongInputMessage</h1>
	 * <p>
	 * {@code public static void printWrongInputMessage()}
	 * </p>
	 * <p>
	 * This method prints out the error message when user enters wrong input
	 * </p>
	 */
	public static void printWrongInputMessage() {
		System.out.println("Wrong input, please enter no. 0-2 only!");
	}

	/**
	 * <h1>printInvalidDigitMessage</h1>
	 * <p>
	 * {@code public static void printInvalidDigitMessage()}
	 * </p>
	 * <p>
	 * This method prints out the error message.
	 * </p>
	 * 
	 * @param input
	 *            - Specified the users' input
	 */
	public static void printInvalidDigitMessage(String input) {
		System.out.printf("\"%s\" is not a valid digit.\n\n", input);
	}

	/**
	 * <h1>printRoomStatReportChoice</h1>
	 * <p>
	 * {@code public static void printRoomStatReportChoice()}
	 * </p>
	 * <p>
	 * This method prints out the choice of printing room status statistic report.
	 * </p>
	 */
	public static void printRoomStatReportChoice() {
		System.out.println("------------------------------------------------------------");
		System.out.println("============ Print Room Status Statistic Report ============");
		System.out.println("------------------------------------------------------------");
		System.out.println("\n1. Room type occupancy rate \n2. Room Status \n0. Exit");
	}

	/**
	 * <h1>printCheckAvailabilityChoice</h1>
	 * <p>
	 * {@code public static void printCheckAvailabilityChoice()}
	 * </p>
	 * <p>
	 * This method prints out the choice of checking Room availability.
	 * </p>
	 */
	public static void printCheckAvailabilityChoice() {
		System.out.println("------------------------------------------------------------");
		System.out.println("==================== Check Availability ====================");
		System.out.println("------------------------------------------------------------");

		System.out.println("1. Room Number\n2. Guest Name\n0. Exit");
	}

	/**
	 * <h1>printCheckVacancyChoice</h1>
	 * <p>
	 * {@code public static void printCheckVacancyChoice()}
	 * </p>
	 * <p>
	 * This method prints out the choice of checking vacancy.
	 * </p>
	 */
	public static void printCheckVacancyChoice() {
		System.out.println("------------------------------------------------------------");
		System.out.println("====================== Check Vacancy =======================");
		System.out.println("------------------------------------------------------------");

		System.out.println("1. Display All Vacant Rooms \n2. Room Type");
	}

	/**
	 * <h1>printSingleVacantRoomHeader</h1>
	 * <p>
	 * {@code public static void printSingleVacantRoomHeader()}
	 * </p>
	 * <p>
	 * This method prints out the Single Room Header
	 * </p>
	 * 
	 */
	public static void printSingleVacantRoomHeader() {
		System.out.println("==============SINGLE==============");
		System.out.println("Vacant Room Number:");
		System.out.println("---------------------------");
		System.out.printf("%5s %15s", "No.", "Room No.");
		System.out.println();
		System.out.println("---------------------------");
	}

	/**
	 * <h1>printDoubleVacantRoomHeader</h1>
	 * <p>
	 * {@code public static void printDoubleVacantRoomHeader()}
	 * </p>
	 * <p>
	 * This method prints out the Double Room Header
	 * </p>
	 * 
	 */
	public static void printDoubleVacantRoomHeader() {
		System.out.println("==============DOUBLE==============");
		System.out.println("Vacant Room Number:");
		System.out.println("---------------------------");
		System.out.printf("%5s %15s", "No.", "Room No.");
		System.out.println();
		System.out.println("---------------------------");
	}

	/**
	 * <h1>printDeluxeVacantRoomHeader</h1>
	 * <p>
	 * {@code public static void printDeluxeVacantRoomHeader()}
	 * </p>
	 * <p>
	 * This method prints out the Deluxe Room Header
	 * </p>
	 * 
	 */
	public static void printDeluxeVacantRoomHeader() {
		System.out.println("==============DELUXE==============");
		System.out.println("Vacant Room Number:");
		System.out.println("---------------------------");
		System.out.printf("%5s %15s", "No.", "Room No.");
		System.out.println();
		System.out.println("---------------------------");
	}

	/**
	 * <h1>printVIPVacantRoomHeader</h1>
	 * <p>
	 * {@code public static void printVIPVacantRoomHeader()}
	 * </p>
	 * <p>
	 * This method prints out the VIP Room Header
	 * </p>
	 * 
	 */
	public static void printVIPVacantRoomHeader() {
		System.out.println("=============VIP SUITE============");
		System.out.println("Vacant Room Number:");
		System.out.println("---------------------------");
		System.out.printf("%5s %15s", "No.", "Room No.");
		System.out.println();
		System.out.println("---------------------------");
	}

	/**
	 * <h1>displayVacantRoom</h1>
	 * <p>
	 * {@code public static void displayVacantRoom()}
	 * </p>
	 * <p>
	 * This method prints out only Vacant Rooms' number
	 * </p>
	 * 
	 * @param rm
	 *            - Specified the whole room details
	 * @param rt
	 *            - Specified the Room type
	 * @param n
	 *            - Specified the Index number
	 */
	public static void displayVacantRoom(Room rm, String rt, int n) {
		System.out.format("%4d %14s", (n + 1), rm.getRoomNum(), rt);
		System.out.println();

	}

	/**
	 * <h1>printSummaryVacantRoomCount</h1>
	 * <p>
	 * {@code public static void printSummaryVacantRoomCount()}
	 * </p>
	 * <p>
	 * This method prints out the total count of the vacant rooms.
	 * </p>
	 * 
	 * @param rmstatus
	 *            - Specified the count of room status
	 * @param rmcount
	 *            - Specified the total count of each room type
	 */
	public static void printSummaryVacantRoomCount(int rmstatus, int rmcount) {
		System.out.println("Summary: " + rmstatus + " out of " + rmcount + " are vacant.\n");

	}

	/**
	 * <h1>printSummaryRoomStatusCount</h1>
	 * <p>
	 * {@code public static void printSummaryRoomStatusCount()}
	 * </p>
	 * <p>
	 * This method prints out the total count of each room status.
	 * </p>
	 * 
	 * @param rmstatus
	 *            - Specified the count of room status
	 */
	public static void printSummaryRoomStatusCount(int rmstatus) {
		System.out.println("Summary: " + rmstatus + " out of " + "48 \n");
	}

	/**
	 * <h1>printAllVacantRoomHeader</h1>
	 * <p>
	 * {@code public static void printAllVacantRoomHeader()}
	 * </p>
	 * <p>
	 * This method prints out all vacant rooms number
	 * </p>
	 * 
	 */
	public static void printAllVacantRoomHeader() {
		System.out.println("==========VACANT==========");
		System.out.println("---------------------------");
		System.out.printf("%5s %15s", "No.", "Room No.");
		System.out.println();
		System.out.println("---------------------------");
	}

	/**
	 * <h1>printAllOccupiedRoomHeader</h1>
	 * <p>
	 * {@code public static void printAllOccupiedRoomHeader()}
	 * </p>
	 * <p>
	 * This method prints out all occupied rooms number
	 * </p>
	 * 
	 */
	public static void printAllOccupiedRoomHeader() {
		System.out.println("==========OCCUPIED==========");
		System.out.println("---------------------------");
		System.out.printf("%5s %15s", "No.", "Room No.");
		System.out.println();
		System.out.println("---------------------------");
	}

	/**
	 * <h1>printAllReservedRoomHeader</h1>
	 * <p>
	 * {@code public static void printAllReservedRoomHeader()}
	 * </p>
	 * <p>
	 * This method prints out all reserved rooms number
	 * </p>
	 * 
	 */
	public static void printAllReservedRoomHeader() {
		System.out.println("==========RESERVED==========");
		System.out.println("---------------------------");
		System.out.printf("%5s %15s", "No.", "Room No.");
		System.out.println();
		System.out.println("---------------------------");
	}

	/**
	 * <h1>printAllUnderMaintenanceRoomHeader</h1>
	 * <p>
	 * {@code public static void printAllUnderMaintenanceRoomHeader()}
	 * </p>
	 * <p>
	 * This method prints out all Under Maintenance rooms number
	 * </p>
	 * 
	 */
	public static void printAllUnderMaintenanceRoomHeader() {
		System.out.println("====UNDER MAINTENANCE=====");
		System.out.println("---------------------------");
		System.out.printf("%5s %15s", "No.", "Room No.");
		System.out.println();
		System.out.println("---------------------------");
	}

	/**
	 * <h1>printCurrentRoomStatus</h1>
	 * <p>
	 * {@code public static void printCurrentRoomStatus()}
	 * </p>
	 * <p>
	 * This method prints out the room current status
	 * </p>
	 * 
	 * @param r1
	 *            - Specified the Room information
	 * 
	 */
	public static void printCurrentRoomStatus(Room r1) {
		System.out.println("The room is " + r1.getRoomStatus());
	}

	/**
	 * <h1>printReservationRoomDetails</h1>
	 * <p>
	 * {@code public static void printReservationRoomDetails()}
	 * </p>
	 * <p>
	 * This method prints out the reservation room status
	 * </p>
	 * 
	 * @param res
	 *            - Specified the Reservation information
	 * @param r
	 *            - Specified the index position
	 * 
	 */
	public static void printReservationRoomDetails(Reservation res, int r) {
		System.out.format("%7s %23s", res.getR().get(r).getRoomNum(), res.getR().get(r).getRoomStatus());
		System.out.println();
	}

	/**
	 * <h1>printReservationlistHeader</h1>
	 * <p>
	 * {@code public static void printReservationlistHeader(Reservation res, int g)}
	 * </p>
	 * <p>
	 * This method prints out the reservation list details
	 * </p>
	 * 
	 * @param res
	 *            - Specified the Reservation information
	 * @param g
	 *            - Specified the index position
	 * 
	 */
	public static void printReservationlistHeader(Reservation res, int g) {
		System.out.print("Room(s) under by: ");
		System.out.println(res.getG().get(g).getName());
		System.out.println("----------------------------------------------");
		System.out.printf("%5s %20s", "Room Number", "Room Status");
		System.out.println();
		System.out.println("----------------------------------------------");
	}

	/**
	 * <h1>printUpdateRoomDetailHeader</h1>
	 * <p>
	 * {@code public static void printUpdateRoomDetailHeader()}
	 * </p>
	 * <p>
	 * This method prints out the header of update room details
	 * </p>
	 * 
	 */
	public static void printUpdateRoomDetailHeader() {
		System.out.println("------------------------------------------------------------");
		System.out.println("=================== Updating Room Detail ===================");
		System.out.println("Enter the Number that you want to update the details: (0: Exit)");
		System.out.println("------------------------------------------------------------");
		System.out.printf("%5s %15s", "No.", "Room No.");
		System.out.println();
		System.out.println("------------------------------------------------------------");
	}

	/**
	 * <h1>printAllRoomNumber</h1>
	 * <p>
	 * {@code public static void printAllRoomNumber(Room r, int n)}
	 * </p>
	 * <p>
	 * This method prints out all the room number
	 * </p>
	 * 
	 * @param r
	 *            - Specified the Room Information
	 * @param n
	 *            - Specified the Index Number
	 * 
	 */
	public static void printAllRoomNumber(Room r, int n) {
		System.out.format("%4d %14s", (n + 1), r.getRoomNum());
		System.out.println();
	}

	/**
	 * <h1>printRoomDetailsForUpdate</h1>
	 * <p>
	 * {@code public static void printRoomDetailsForUpdate(ArrayList<Room> results, int roomchoice)}
	 * </p>
	 * <p>
	 * This method prints out the room details for update
	 * </p>
	 * 
	 * @param results
	 *            - Stored the room details in an arraylist
	 * @param roomchoice
	 *            - Specified the users' input for update
	 * 
	 */
	public static void printRoomDetailsForUpdate(ArrayList<Room> results, int roomchoice) {
		System.out.println("------------------------------------------------------------");
		System.out.println("Room Number: " + results.get(roomchoice - 1).getRoomNum());
		System.out.println("(1) Room Type: " + results.get(roomchoice - 1).getRoomType());
		System.out.println("(2) Weekday Room Rate: " + results.get(roomchoice - 1).getweekdayRoomRate());
		System.out.println("(3) Weekend Room Rate: " + results.get(roomchoice - 1).getweekendRoomRate());
		System.out.println("(4) WiFi Enabled: " + results.get(roomchoice - 1).isWifiEnabled());
		System.out.println("(5) Smoke Allowed: " + results.get(roomchoice - 1).getSmoke());
		System.out.println("(6) Bed Type: " + results.get(roomchoice - 1).getBedType());
		System.out.println("(7) Facing View: " + results.get(roomchoice - 1).getFacingView());
		System.out.println("(8) Room Status: " + results.get(roomchoice - 1).getRoomStatus());
		System.out.println("(0) Exit");
		System.out.println("------------------------------------------------------------");
	}

	/**
	 * <h1>printCreateRoomDetail</h1>
	 * <p>
	 * {@code public static void printCreateRoomDetail(Room r)}
	 * </p>
	 * <p>
	 * This method prints out the room details for creation
	 * </p>
	 * 
	 * @param r
	 *            - Specified the Room information
	 */
	public static void printCreateRoomDetail(Room r) {
		System.out.println("------------------------------------------------------------");
		System.out.println("Room Number: 			" + r.getRoomNum());
		System.out.println("Room Type: 			" + r.getRoomType());
		System.out.println("Weekday Room Rate: 		" + r.getweekdayRoomRate());
		System.out.println("Weekend Room Rate: 		" + r.getweekendRoomRate());
		System.out.println("Wifi Enabled: 			" + r.isWifiEnabled());
		System.out.println("Smoking Allowed: 		" + r.getSmoke());
		System.out.println("Bed Type:			" + r.getBedType());
		System.out.println("Facing View: 			" + r.getFacingView());
		System.out.println("Room Status: 			" + r.getRoomStatus());
		System.out.println("------------------------------------------------------------");
	}
}
