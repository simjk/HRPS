package boundary;

import java.util.ArrayList;

import entity.Reservation;
import entity.Room;
import entity.FoodItem;
import entity.Guest;
/**
 * <h1>Room Service Boundary</h1>
 * <p>
 * This class handles the display and printing of all Room Service information
 * <p>
 * The related entity classes are: Room, Reservation, Guest, FoodItem.
 * </p>
 * <p>
 * The related control class is: RoomServiceControl.
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 13-04-2018
 */
public class RoomServiceBoundary {

	/**
	 * <h1>printCreateFoodItemDetail</h1>
	 * <p>
	 * {@code public static void printCreateFoodItemDetail(Fooditem fi)}
	 * </p>
	 * <p>
	 * This method display all food item details(Food Id, Food Name,
	 * Food Description, Prep Steps, Quantity, Price) 
	 * </p>
	 * 
	 * @param fi
	 *            - contains the Food Item information
	 * 
	 */
public static void printCreateFoodItemDetail(FoodItem fi) {
	System.out.println("------------------------------------------------------------");
	System.out.println("========================= Food Item ========================");
	System.out.println("------------------------------------------------------------");
	System.out.println("Food ID: 		" + fi.getFoodId());
	System.out.println("Name: 			" + fi.getName());
	System.out.println("Description: 		" + fi.getDescription());
	System.out.println("Prep Steps: 		" + fi.getPrepSteps());
	System.out.println("Quantity: 		" + fi.getQuantity());
	System.out.println("Price: 			" + fi.getPrice());
	System.out.println("------------------------------------------------------------");
	System.out.println("\nHello, is the details correct?");

}
/**
 * <h1>printUpdateFoodItemHeader</h1>
 * <p>
 * {@code public static void printUpdateFoodItemHeader()}
 * </p>
 * <p>
 * This method print food item options for update(Food Name,
 * Food Description, Prep Steps, Quantity, Price) 
 * </p>
 * 
 */
public static void printUpdateFoodItemHeader() {
	System.out.println("------------------------------------------------------------");
	System.out.println("================= Updating Food Item Detail ================");
	System.out.println("------------------------------------------------------------");

	System.out.println("1. Food Name ");
	System.out.println("2. Descriptions ");
	System.out.println("3. Prep Steps ");
	System.out.println("4. Quantity ");
	System.out.println("5. Price ");
	System.out.println("------------------------------------------------------------");
	System.out.println("Select a choice to update (1-5): ");
}
/**
 * <h1>printFoodItemHeader</h1>
 * <p>
 * {@code public static void printFoodItemHeader()}
 * </p>
 * <p>
 * This method print food item header for update(Food ID,
 * Food Name)
 * </p>
 * 
 */
public static void printFoodItemHeader() {
	System.out.println("Enter the No. that you want to update: (0: Exit)");
	System.out.println("----------------------------------------------");
	System.out.printf("%5s %5s %27s", "No.", "Food ID", "Food Name");
	System.out.println();
	System.out.println("----------------------------------------------");
}
/**
 * <h1>printUpdateFoodItem</h1>
 * <p>
 * {@code public static void printUpdateFoodItem(FoodItem fi)}
 * </p>
 * <p>
 * This method print food item header for update(Food ID,
 * Food Name)
 * </p>
 * 
 * @param fi 
 * 			 - Specified the Food Item details
 * 
 */
public static void printUpdateFoodItem(FoodItem fi) {
	System.out.println("------------------------------------------------------------");
	System.out.println("==================== Updating Food Item ====================");
	System.out.println("------------------------------------------------------------");
	System.out.println("1. Food Name: 		" + fi.getName());
	System.out.println("2. Descriptions: 	" + fi.getDescription());
	System.out.println("3. Prep Steps: 		" + fi.getPrepSteps());
	System.out.println("4. Quantity: 		" + fi.getQuantity());
	System.out.println("5. Price: 		" + fi.getPrice());
	System.out.println("0. Exit");
	System.out.println("------------------------------------------------------------");

	System.out.println("Select a choice to update (1-5): ");
}
/**
 * <h1>printAllFoodItemDetails</h1>
 * <p>
 * {@code public static void printAllFoodItemDetails(FoodItem f)}
 * </p>
 * <p>
 * This method prints all Food Item details
 * </p>
 * 
 * @param f 
 * 			 - Contains the whole list of food items
 * 
 */
public static void printAllFoodItemDetails(FoodItem f) {
	System.out.println("Food ID: 		" + f.getFoodId());
	System.out.println("Food Name: 		" + f.getName());
	System.out.println("Description: 		" + f.getDescription());
	System.out.println("Prep Steps: 		" + f.getPrepSteps());
	System.out.println("Quantity: 		" + f.getQuantity());
	System.out.println("Price: 			" + f.getPrice());
	System.out.println("------------------------------------------------------------");


	System.out.println();
}
/**
 * <h1>printRemoveFoodItemHeader</h1>
 * <p>
 * {@code public static void printRemoveFoodItemHeader(}
 * </p>
 * <p>
 * This method prints the remove food item header
 * </p>
 * 
 */
public static void printRemoveFoodItemHeader() {
	System.out.println("Enter the Food No. that you want to remove: (0: Exit)");
	System.out.println("----------------------------------------------");
	System.out.printf("%5s %5s %27s", "No.", "Food ID", "Food Name");
	System.out.println();
	System.out.println("----------------------------------------------");
}
/**
 * <h1>printRoomOccupiedByGuests</h1>
 * <p>
 * {@code public static void printRoomOccupiedByGuests(Reservation res, int g}
 * </p>
 * <p>
 * This method prints room occupied by a guest header
 * </p>
 * 
 * @param res 
 * 			 - Contains the reservation details
 * @param g
 * 			 - Index Position
 */
public static void printRoomOccupiedByGuestsHeader(Reservation res, int g) {
	System.out.println();
	System.out.print("Room(s) occupied by: ");
	System.out.println(res.getG().get(g).getName());
	System.out.println("---------------------");
	System.out.printf("%5s", "Room Number");
	System.out.println();
	System.out.println("---------------------");
}
/**
 * <h1>printAllRoomServiceOrderHeader</h1>
 * <p>
 * {@code public static void printAllRoomServiceOrderHeader()}
 * </p>
 * <p>
 * This method prints room service order header
 * </p>
 * 
 */
public static void printAllRoomServiceOrderHeader() {
	System.out.println(
			"----------------------------------------------------------------------------------------------------------------------------------------------------------------");
	System.out.printf("%5s %8s %23s %17s %18s %20s %18s %25s %20s", "No.", "Order ID.", "Order Date",
			"Guest Name", "Room Number", "Food Name", "Quantity", "Remarks", "Order Status");
	System.out.println();
	System.out.println(
			"-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
}


}
