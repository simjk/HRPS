package control;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import boundary.RoomServiceBoundary;
import control.ReservationControl;
import entity.FoodItem;
import entity.Guest;
import entity.Reservation;
import entity.RoomService;
import entity.Reservation.Status;
import entity.Room;
import entity.Room.RoomStatus;
import entity.RoomService.orderStatus;
import control.MainControl;
/**
 * <h1>RoomServiceControl</h1>
 * <p>
 * The program implements methods that create,update,remove,display food item,
 * 									   create,update,display Room Service Order
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */
public class RoomServiceControl implements Serializable, Control {

	boolean isNum;
	FoodItem fi = new FoodItem();
	Scanner in = new Scanner(System.in);
	readWriteFile rwf = new readWriteFile();
	RoomService ser = new RoomService();
	/**
	 * <h1>RoomServiceControl</h1>
	 * <p>
	 * This method is used to call other RoomServiceControl methods in other classes
	 * </p>
	 */
	public RoomServiceControl() {
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
	 * into roomServiceInterface method.
	 * </p>
	 */
	private void selection() {

		String input;

		System.out.println("------------------------------------------------------------");
		System.out.println("================ HRPS: Room Service System =================");
		System.out.println("------------------------------------------------------------");

		System.out.println("1. Create Food Item" + "\n2. Update Food Item" + "\n3. Display Food Item"
				+ "\n4. Remove Food Item" + "\n5. Create Room Service Order" + "\n6. Display All Room Service Order"
				+ "\n7. Update Room Service Status" + "\n0. Return to Main Menu");
		System.out.println("------------------------------------------------------------");

		if (in.hasNextInt()) {
			int choice = in.nextInt();

			if (choice >= 0 && choice <= 7)
				roomServiceInterface(choice);
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
	 * <h1>roomServiceInterface</h1>
	 * <p>
	 * roomServiceInterface take in {@value control#choice} and call other methods.
	 * </p>
	 * 
	 * @param choice
	 *            - choose between create food item/update food item/display food item/remove food item
	 *            - choose between create room service order/update room service status/display room service order
	 */
	private void roomServiceInterface(int choice) {
		switch (choice) {
		case 1:
			createFoodItem();
			System.out.println("Enter 0 to return to Room Service System Menu");
			int choice1 = in.nextInt();
			if (choice1 == 0)
				selection();
			break;
		case 2:
			updateFood();
			System.out.println("Enter 0 to return to Room Service System Menu");
			int choice2 = in.nextInt();
			if (choice2 == 0)
				selection();
			break;
		case 3:
			displayAllFoodItems();
			System.out.println("Enter 0 to return to Room Service System Menu");
			int choice3 = in.nextInt();
			if (choice3 == 0)
				selection();
			break;
		case 4:
			removeFoodItem();
			System.out.println("Enter 0 to return to Room Service System Menu");
			int choice4 = in.nextInt();
			if (choice4 == 0)
				selection();
			break;
		case 5:
			createRoomServiceOrder();
			System.out.println("Enter 0 to return to Room Service System Menu");
			int choice5 = in.nextInt();
			if (choice5 == 0)
				selection();
			break;
		case 6:
			displayAllRoomServiceOrder();
			System.out.println("Enter 0 to return to Room Service System Menu");
			int choice6 = in.nextInt();
			if (choice6 == 0)
				selection();
			break;
		case 7:
			updateRoomServiceStatus();
			System.out.println("Enter 0 to return to Room Service System Menu");
			int choice7 = in.nextInt();
			if (choice7 == 0)
				selection();
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
	// FOOD MENU CREATION
	// ===========================================================================================================================================================//
	/**
	 * <h1>createFoodItem</h1>
	 * <p>
	 * This method call other method to create all related information of food item
	 * </p>
	 * 
	 */
	private void createFoodItem() {
		Random rdm;
		rdm = new Random();

		System.out.println("------------------------------------------------------------");
		System.out.println("================= Creating Food Item Detail ================");
		System.out.println("------------------------------------------------------------");

		fi.setFoodId(rdm.nextInt(1000) + 1);

		in.nextLine();
		FoodName();
		FoodDescription();
		FoodPrepSteps();
		FoodQuantity();
		FoodPrice();
		displayFoodItem();
	}
	/**
	 * <h1>displayFoodItem</h1>
	 * <p>
	 * {@code private void displayFoodItem()}
	 * </p>
	 * <p>
	 * This method display all information about food item and write it to class
	 * </p>
	 * 
	 */
	private void displayFoodItem() {
		String input;
		ArrayList<FoodItem> list = new ArrayList<FoodItem>();
		
		RoomServiceBoundary.printCreateFoodItemDetail(fi);

		System.out.println("\n1. Yes" + "\n2. No");
		if (in.hasNextInt()) {
			int choice = in.nextInt();
			if (choice == 1) {
				FoodItem f1 = new FoodItem(fi.getFoodId(), fi.getName(), fi.getDescription(), fi.getPrepSteps(),
						fi.getQuantity(), fi.getPrice());
				list.add(f1);
				rwf.writeFoodItemToFile(list);
				System.out.println("Food Item successfully created!");
				selection();
			} else if (choice == 2) {
				updateFoodItem2();
				displayFoodItem();
			}
		} else {
			input = in.nextLine();
			System.out.printf("\"%s\" is not a valid digit.\n\n", input);
			displayFoodItem();
		}
	}
	/**
	 * <h1>FoodName</h1>
	 * <p>
	 * {@code private void FoodName()}
	 * </p>
	 * <p>
	 * This method validate and set food's name.
	 * </p>
	 */
	private void FoodName() {
		System.out.println("Food name: (Eg. Carbonara)");
		String name = in.nextLine();
		if (Pattern.matches("^[A-Za-z\\s]+$", name))
			fi.setName(name);
		else {
			System.out.printf("\"%s\" is not a valid name.\n", name);
			System.out.println("Please try again!");
			System.out.println();
			FoodName();
		}
	}
	/**
	 * <h1>FoodDescription</h1>
	 * <p>
	 * {@code private void FoodDescription()}
	 * </p>
	 * <p>
	 * This method validate and set food's description.
	 * </p>
	 */
	private void FoodDescription() {
		System.out.println("Description: ");
		String description = in.nextLine();
		fi.setDescription(description);
	}
	/**
	 * <h1>FoodPrepSteps</h1>
	 * <p>
	 * {@code private void FoodPrepSteps()}
	 * </p>
	 * <p>
	 * This method validate and set food's prep steps.
	 * </p>
	 */
	private void FoodPrepSteps() {
		System.out.println("Prep Steps: ");
		String prepSteps = in.nextLine();
		fi.setPrepSteps(prepSteps);
	}
	/**
	 * <h1>updateFoodItem2</h1>
	 * <p>
	 * {@code private void updateFoodItem2()}
	 * </p>
	 * <p>
	 * This method allows user to choose which component to update
	 * </p>
	 * 
	 */
	private void updateFoodItem2() {
		
		RoomServiceBoundary.printUpdateFoodItemHeader();
		

		int detailchoice = in.nextInt();
		in.nextLine();

		switch (detailchoice) {
		case 1:
			FoodName();
			break;
		case 2:
			FoodDescription();
			break;
		case 3:
			FoodPrepSteps();
			break;
		case 4:
			FoodQuantity();
			break;
		case 5:
			FoodPrice();
			break;
		default:
			System.out.println("Wrong input, please input no. 1 - 5 only!\n");
			updateFoodItem2();
			break;
		}
	}
	/**
	 * <h1>FoodQuantity</h1>
	 * <p>
	 * {@code private void FoodQuantity()}
	 * </p>
	 * <p>
	 * This method validate and set food's quantity.
	 * </p>
	 */
	private void FoodQuantity() {
		do {
			System.out.println("Quantity: ");
			if (in.hasNextInt()) {
				int quantity = in.nextInt();
				if (quantity > 0) {
					fi.setQuantity(quantity);
					isNum = true;
				} else
					System.out.println("Wrong input, please input again!");
			} else {
				System.out.println("Please input numbers only!");
				isNum = false;
				in.next();
			}
		} while (!isNum);
	}
	/**
	 * <h1>FoodPrice</h1>
	 * <p>
	 * {@code private void FoodPrice()}
	 * </p>
	 * <p>
	 * This method validate and set food's price.
	 * </p>
	 */
	private void FoodPrice() {
		do {
			System.out.println("Price: ");
			if (in.hasNextDouble()) {
				double price = in.nextDouble();
				if (price > 0) {
					fi.setPrice(price);
					isNum = true;
				} else
					System.out.println("Wrong input, please input again!");
			} else {
				System.out.println("Please input numbers only!");
				isNum = false;
				in.next();
			}
		} while (!isNum);
	}

	// ===========================================================================================================================================================//
	// FOOD MENU (UPDATE)
	// ===========================================================================================================================================================//
	/**
	 * <h1>updateFood</h1>
	 * <p>
	 * {@code private void updateFood()}
	 * </p>
	 * <p>
	 * This method allows user to enter which food item to update
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find FoodItem.dat
	 * @exception InputMismatchException
	 * 				  - Wrong Input
	 */
	private void updateFood() {

		ArrayList<FoodItem> results;
		boolean checkInput = true;

		try {
			results = rwf.readFoodItem();
			FoodItem f = null;

			do {
				int n = 0;
				
				RoomServiceBoundary.printFoodItemHeader();
				
				for (int i = 0; i < results.size(); i++) {
					f = (FoodItem) results.get(i);
					System.out.format("%5d %5d %30s", (n + 1), f.getFoodId(), f.getName());
					System.out.println();
					n++;
				}

				int foodchoice = in.nextInt();
				if (foodchoice == 0) {
					selection();
					checkInput = true;
				} else if (foodchoice > 0 && foodchoice <= results.size()) {
					fi.setFoodId(results.get(foodchoice - 1).getFoodId());
					fi.setName(results.get(foodchoice - 1).getName());
					fi.setDescription(results.get(foodchoice - 1).getDescription());
					fi.setPrepSteps(results.get(foodchoice - 1).getPrepSteps());
					fi.setPrice(results.get(foodchoice - 1).getPrice());
					fi.setQuantity(results.get(foodchoice - 1).getQuantity());

					updateFoodItem(results, foodchoice);

					checkInput = true;
				} else {
					System.out.println("Please enter a valid no!");
					checkInput = false;
				}
			} while (!checkInput);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Please input numbers only!");
			updateFood();
		}
	}
	/**
	 * <h1>updateFoodItem</h1>
	 * <p>
	 * {@code private void updateFoodItem(ArrayList<FoodItem> results, int foodchoice)}
	 * </p>
	 * <p>
	 * This method allows user to choose which component to update
	 * </p>
	 * 
	 * @param results
	 *            - contains the food item details
	 * @param foodchoice
	 *            - select which parameters to update
	 * @exception InputMismatchException
	 *                - choice mismatch
	 */
	private void updateFoodItem(ArrayList<FoodItem> results, int foodchoice) {
		try {
			RoomServiceBoundary.printUpdateFoodItem(fi);

			int detailchoice = in.nextInt();
			in.nextLine();
			switch (detailchoice) {
			case 1:
				FoodName();
				break;
			case 2:
				FoodDescription();
				break;
			case 3:
				FoodPrepSteps();
				break;
			case 4:
				FoodQuantity();
				break;
			case 5:
				FoodPrice();
				break;
			case 0:
				updateFood();
				break;
			default:
				System.out.println("Wrong input, please input no. 0 - 5 only!\n");
				updateFoodItem(results, foodchoice);
				break;
			}

			results.set(foodchoice - 1, fi);

			File file = new File("foodItem.dat");
			if (file.exists())
				file.delete();
			rwf.writeFoodItemToFile(results);

			updateFoodItem(results, foodchoice);

		} catch (InputMismatchException e) {
			System.out.println("Please input numbers only!");
			updateFoodItem(results, foodchoice);
		}
	}

	// ===========================================================================================================================================================//
	// FOOD MENU DISPLAY ALL
	// ===========================================================================================================================================================//
	/**
	 * <h1>displayAllFoodItems</h1>
	 * <p>
	 * {@code public void displayAllFoodItems()}
	 * </p>
	 * <p>
	 * This method display all information about food
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find guest.dat
	 */
	private void displayAllFoodItems() {

		ArrayList<FoodItem> results;
		FoodItem f = null;

		try {
			results = rwf.readFoodItem();
			System.out.println("------------------------------------------------------------");
			System.out.println("======================== Food Menu =========================");
			System.out.println("------------------------------------------------------------");

			for (int i = 0; i < results.size(); i++) {
				f = (FoodItem) results.get(i);
				RoomServiceBoundary.printAllFoodItemDetails(f);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ===========================================================================================================================================================//
	// FOOD MENU REMOVAL
	// ===========================================================================================================================================================//
	/**
	 * <h1>removeFoodItem</h1>
	 * <p>
	 * {@code public void removeFoodItem()}
	 * </p>
	 * <p>
	 * This method removes food item
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find foodItem.dat
	 */
	private void removeFoodItem() {
		ArrayList<FoodItem> results;
		boolean checkInput = false;
		try {
			results = rwf.readFoodItem();
			FoodItem f = null;
			do {
				int n = 0;
				RoomServiceBoundary.printRemoveFoodItemHeader();
				
				for (int i = 0; i < results.size(); i++) {
					f = (FoodItem) results.get(i);
					System.out.format("%5d %5d %30s", (n + 1), f.getFoodId(), f.getName());
					System.out.println();
					n++;
				}

				int removechoice = in.nextInt();

				if (removechoice == 0) {
					selection();
					checkInput = true;
				} else if (removechoice > 0 && removechoice <= results.size()) {
					results.remove(results.get(removechoice - 1));
					File file = new File("foodItem.dat");

					if (file.exists())
						file.delete();

					rwf.writeFoodItemToFile(results);
					System.out.println("Removed Successfully!");

					removeFoodItem();
					checkInput = true;
				} else {
					System.out.println("Please enter a valid no!");
					checkInput = false;
				}

			} while (!checkInput);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ===========================================================================================================================================================//
	// ROOM SERVICE MENU CREATION
	// ===========================================================================================================================================================//
	/**
	 * <h1>createRoomServiceOrder</h1>
	 * <p>
	 * {@code public void createRoomServiceOrder()}
	 * </p>
	 * <p>
	 * This method create room service order
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find foodItem.dat
	 * @exception InputMismatchException
	 *   			  - Wrong Input
	 */

	private void createRoomServiceOrder() {

		ArrayList<RoomService> order = new ArrayList<RoomService>();
		ArrayList<FoodItem> userFoodItem = new ArrayList<FoodItem>();
		ArrayList<ArrayList> walkIn = new ArrayList<ArrayList>();
		ArrayList<FoodItem> foodList;
		ArrayList<Reservation> list;
		ReservationControl rc = new ReservationControl();
		Reservation p = null;
		Random rdm;
		int n = 0;
		FoodItem food = null;
		ArrayList<Room> roomList = null;
		ArrayList<Guest> guestList = null;

		System.out.println("------------------------------------------------------------");
		System.out.println("================= Create Room Service Order ================");
		System.out.println("------------------------------------------------------------");
		System.out.println("1. Reservation \n2. Walk In");
		int choice = in.nextInt();

		try {
			walkIn = rwf.readWalkInFile();
			foodList = rwf.readFoodItem();
			list = rwf.readReservationFile();
			System.out.print("================Rooms Occupied by Guests================");
			if (choice == 1) {
				for (int i = 0; i < list.size(); i++) {
					Reservation res = list.get(i);

					for (int g = 0; g < res.getG().size(); g++) {
						if (res.getStatus() == Status.CHECKED_IN) {
							System.out.println();
							System.out.print("Room(s) occupied by: ");
							System.out.println(res.getG().get(g).getName());
							System.out.println("---------------------");
							System.out.printf("%5s", "Room Number");
							System.out.println();
							System.out.println("---------------------");
						}
					}

					for (int r = 0; r < res.getR().size(); r++) {
						if (res.getStatus() == Status.CHECKED_IN) {
							System.out.format("%7s", res.getR().get(r).getRoomNum());
							System.out.println();
							n++;
						}
					}
				}

				System.out.println();
				rdm = new Random();
				ser.setOrderId(rdm.nextInt(1000) + 1);

				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
				Date date = new Date();
				ser.setOrderDate(dateFormat.format(date)); // 2016/11/16, 12:08:43
				boolean check = false;

				do {
					System.out.println("Enter the Room Number:");
					String roomNum = in.next();
					if (roomNum.matches("^0[2-7]-0[0-8]$")) {
						for (int j = 0; j < list.size(); j++) {
							Reservation res = list.get(j);
							for (int rm = 0; rm < res.getR().size(); rm++) {
								if ((res.getR().get(rm).getRoomStatus() == RoomStatus.OCCUPIED)
										&& (roomNum.matches(res.getR().get(rm).getRoomNum()))) {
									ser.setRoomnum(roomNum);
									ser.setName(res.getG().get(rm).getName());
									check = true;
									break;
								}
							}
						}

						if (check == false) {
							System.out.println("Room is not occupied by any guest. Please enter again.");
							System.out.println();
						}

					} else {
						System.out.println("Wrong input, please input in this format: E.g. 02-01");
						System.out.println(
								"Note: For each level, (level 2 to 7) room number are range from 01 to 08. \n");
						check = false;
					}
				} while (check == false);
			} else if (choice == 2) {
				System.out.println();
				for (int i = 0; i < walkIn.size(); i += 4) {
					guestList = walkIn.get(i);
					roomList = walkIn.get(i + 1);
					System.out.print("Room(s) occupied by: ");
					for (int j = 0; j < guestList.size(); j++) {
						System.out.println(guestList.get(j).getName());
					}
					System.out.println("---------------------");
					System.out.printf("%5s", "Room Number");
					System.out.println();
					System.out.println("---------------------");
					for (int k = 0; k < roomList.size(); k++) {
						System.out.format("%7s", roomList.get(k).getRoomNum());
						System.out.println();
					}
				}

				rdm = new Random();
				ser.setOrderId(rdm.nextInt(1000) + 1);

				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
				Date date = new Date();
				ser.setOrderDate(dateFormat.format(date)); // 2016/11/16, 12:08:43
				boolean check = false;

				do {
					System.out.println("Enter the Room Number:");
					String roomNum = in.next();
					if (roomNum.matches("^0[2-7]-0[0-8]$")) {
						for (int o = 0; o < walkIn.size(); o += 4) {
							guestList = walkIn.get(o);
							roomList = walkIn.get(o + 1);
							for (int i = 0; i < roomList.size(); i++) {
								if (roomList.get(i).getRoomNum().matches(roomNum)) {
									ser.setName(guestList.get(i).getName());
									ser.setRoomnum(roomList.get(i).getRoomNum());
									check = true;
								}
							}
						}
						if (check == false) {
							System.out.println("Room is not occupied by any guest. Please enter again.");
							System.out.println();
						}

					} else {
						System.out.println("Wrong input, please input in this format: E.g. 02-01");
						System.out.println(
								"Note: For each level, (level 2 to 7) room number are range from 01 to 08. \n");
						check = false;
					}
				} while (check == false);

			} else {
				System.out.print("Wrong input, please enter the input again!");
				createRoomServiceOrder();
			}
			displayAllFoodItems();

			System.out.print("No. of Food Item to order: ");
			int noFood = in.nextInt();

			if (noFood != 0) {
				for (int i = 0; i < noFood; i++) {
					System.out.println("Enter Food ID:");
					int foodID = in.nextInt();

					for (int j = 0; j < foodList.size(); j++) {
						food = foodList.get(j);
						if (foodID == food.getFoodId()) {
							System.out.println("Enter Quantity: ");
							int qty = in.nextInt();
							food.setQuantity(qty);
							// System.out.println("Food name: " + food.getName());
							// System.out.println("Food Quantity: " + food.getQuantity());
							// System.out.println();

							fi = new FoodItem(food.getFoodId(), food.getName(), food.getDescription(),
									food.getPrepSteps(), food.getQuantity(), food.getPrice());

						}
					}
					userFoodItem.add(fi);
				}
			}

			System.out.println("Enter Remarks: ");
			in.nextLine();
			ser.setRemark(in.nextLine());

			System.out.println("Select Room Service Status\n 1. Confirmed \n 2. Preparing \n 3. Delivered");
			int statuschoice = in.nextInt();
			switch (statuschoice) {
			case 1:
				ser.setOrderStatus(orderStatus.CONFIRMED);
				break;
			case 2:
				ser.setOrderStatus(orderStatus.PREPARING);
				break;
			case 3:
				ser.setOrderStatus(orderStatus.DELIVERED);
				break;
			}

			RoomService r = new RoomService(ser.getOrderId(), ser.getOrderDate(), ser.getName(), ser.getRoomnum(),
					userFoodItem, ser.getRemark(), ser.getOrderStatus());
			order.add(r);
			rwf.writeRoomServiceOrderToFile(order);
			System.out.println("Room Service Order created successfully!!\n");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input!");
			createRoomServiceOrder();
		}
	}
	// ===========================================================================================================================================================//
	// ROOM SERVICE MENU DISPLAY
	// ===========================================================================================================================================================//
	/**
	 * <h1>displayAllRoomServiceOrder</h1>
	 * <p>
	 * {@code public void displayAllRoomServiceOrder()}
	 * </p>
	 * <p>
	 * This method display all information about Room service order 
	 * </p>
	 * 
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find RoomServiceOrder.dat
	 */
	private void displayAllRoomServiceOrder() {
		ArrayList<RoomService> results;
		int n = 0;
		try {
			results = rwf.readRoomServiceOrder();
			RoomService f = null;
			RoomServiceBoundary.printAllRoomServiceOrderHeader();
			for (int i = 0; i < results.size(); i++) {
				f = (RoomService) results.get(i);
				System.out.printf("%5d %5d %27s %17s %17s", (n + 1), f.getOrderId(), f.getOrderDate(), f.getName(),
						f.getRoomnum());

				if (f.getuserFoodItem().size() >= 1)
					System.out.format("%22s %18s", f.getuserFoodItem().get(0).getName(),
							f.getuserFoodItem().get(0).getQuantity());

				System.out.format("%26s %20s\n", f.getRemark(), f.getOrderStatus());

				if (f.getuserFoodItem().size() > 1)
					for (int k = 1; k < f.getuserFoodItem().size(); k++) {
						System.out.format("%97s %18s\n", f.getuserFoodItem().get(k).getName(),
								f.getuserFoodItem().get(k).getQuantity());
					}
				System.out.println();
				n++;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ===========================================================================================================================================================//
	// ROOM SERVICE MENU UPDATE
	// ===========================================================================================================================================================//
	/**
	 * <h1>updateRoomServiceStatus</h1>
	 * <p>
	 * {@code private void updateRoomServiceStatus()}
	 * </p>
	 * <p>
	 * This method allows user to update Room Service Status
	 * </p>
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find RoomServiceOrder.dat
	 * @exception InputMismatchException
	 *                - choice mismatch
	 */
	private void updateRoomServiceStatus() {

		ArrayList<RoomService> results;
		int n = 0;
		try {
			results = rwf.readRoomServiceOrder();
			System.out.println("------------------------------------------------------------");
			System.out.println("=============== Updating Room Service Status ===============");
			System.out.println("------------------------------------------------------------");

			RoomServiceBoundary.printAllRoomServiceOrderHeader();
			for (int i = 0; i < results.size(); i++) {
				RoomService s = results.get(i);
				System.out.printf("%5d %5d %27s %17s %17s", (n + 1), s.getOrderId(), s.getOrderDate(), s.getName(),
						s.getRoomnum());

				if (s.getuserFoodItem().size() >= 1)
					System.out.format("%22s %18s", s.getuserFoodItem().get(0).getName(),
							s.getuserFoodItem().get(0).getQuantity());

				System.out.format("%21s %20s\n", s.getRemark(), s.getOrderStatus());

				if (s.getuserFoodItem().size() > 1)
					for (int k = 1; k < s.getuserFoodItem().size(); k++) {
						System.out.format("%97s %18s\n", s.getuserFoodItem().get(k).getName(),
								s.getuserFoodItem().get(k).getQuantity());
					}
				System.out.println();
				n++;
			}

			System.out.println("Enter the number you want to update: ");
			int orderchoice = in.nextInt();

			if (orderchoice > n) {
				System.out.println("Invalid input! Please enter again!");
				updateRoomServiceStatus();
			} else {

				System.out.println("Current Order Status: " + results.get(orderchoice - 1).getOrderStatus());

				System.out.println("Select Room Service Status\n 1. Confirmed \n 2. Preparing \n 3. Delivered");
				int statuschoice = in.nextInt();
				switch (statuschoice) {
				case 1:
					results.get(orderchoice - 1).setOrderStatus(orderStatus.CONFIRMED);
					break;
				case 2:
					results.get(orderchoice - 1).setOrderStatus(orderStatus.PREPARING);
					break;
				case 3:
					results.get(orderchoice - 1).setOrderStatus(orderStatus.DELIVERED);
					break;
				default:
					System.out.print("Invalid input! Please input number (1-3) only!");
					updateRoomServiceStatus();

				}

				File file = new File("RoomServiceOrder.dat");
				if (file.exists())
					file.delete();

				rwf.writeRoomServiceOrderToFile(results);
				System.out.println("Room service status successfully updated!!!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input! Please input numbers only!");
			updateRoomServiceStatus();
		}
	}
}
