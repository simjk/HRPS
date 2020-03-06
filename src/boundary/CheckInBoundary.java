package boundary;

import java.util.ArrayList;

import entity.Reservation;

public class CheckInBoundary {

	public static void displayDetails(ArrayList<Reservation> results, int choice) {
		System.out.println("------------------------------------------------------------");
		System.out.println("====================== Selected Guest ======================");
		System.out.println("------------------------------------------------------------");
		System.out.println("Reservation No: 	" + results.get(choice - 1).getReservationNo());
		System.out.println("Name: 			" + results.get(choice - 1).getG().get(0).getName());
		System.out.println("Identity: 		" + results.get(choice - 1).getG().get(0).getIdentity());
		System.out.println("Contact Number: 	" + results.get(choice - 1).getG().get(0).getContact());
		System.out.println("Check In: 		" + results.get(choice - 1).getCheckIn());
		System.out.println("Check Out: 		" + results.get(choice - 1).getCheckOut());
		System.out.println("Reservation Status: 	" + results.get(choice - 1).getStatus());
		System.out.print("Reserved Rooms: 	");
		for (int k = 0; k < results.get(choice - 1).getR().size(); k++) {
			System.out.print(results.get(choice - 1).getR().get(k).getRoomNum() + " ");
		}
		System.out.println();
		System.out.println("------------------------------------------------------------");

	}
}
