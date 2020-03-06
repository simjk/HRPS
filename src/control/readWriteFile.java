package control;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entity.FoodItem;
import entity.Guest;
import entity.Reservation;
import entity.Room;
import entity.RoomService;

/**
 * <h1>readWriteFile</h1>
 * <p>
 * The program read and write for every control
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */
public class readWriteFile {

	// ===========================================================================================================================================================//
	// GUEST SERIALIZATION AND DESERIALIZATION METHODS
	// ===========================================================================================================================================================//

	/**
	 * <h1>writeGuestToFile</h1>
	 * <p>
	 * This method write guest list to guest.dat
	 * </p>
	 * 
	 * @param list
	 *            - list of guest to write
	 */
	public void writeGuestToFile(ArrayList<Guest> list) {
		FileOutputStream fos = null;
		ObjectOutputStream ois = null;
		try {
			fos = new FileOutputStream(new File("guest.dat"), true);
			ois = new ObjectOutputStream(fos);
			ois.writeObject(list);
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>readGuestFile</h1>
	 * <p>
	 * This method read guest list from guest.dat
	 * </p>
	 * 
	 * @return - list of guest
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find guest.dat
	 * @exception EOFException
	 *                - unable check if file stream end            
	 */
	public ArrayList<Guest> readGuestFile() throws IOException, ClassNotFoundException {
		ArrayList<Guest> results = new ArrayList<Guest>();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream("guest.dat");
			while (fis.available() > 0) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				results.addAll((ArrayList<Guest>) ois.readObject());
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}
		return results;
	}

	// ===========================================================================================================================================================//
	// ROOM SERIALIZATION AND DESERIALIZATION METHODS
	// ===========================================================================================================================================================//

	/**
	 * <h1>writeRoomToFile</h1>
	 * <p>
	 * This method write room list to room.dat
	 * </p>
	 * 
	 * @param list
	 *            - list of room to write
	 */
	public void writeRoomToFile(ArrayList<Room> list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(new File("room.dat"), true);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * <h1>readRoomFile</h1>
	 * <p>
	 * This method read room list from room.dat
	 * </p>
	 * 
	 * @return - list of room
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find room.dat
	 * @exception EOFException
	 *                - unable check if file stream end            
	 */
	public ArrayList<Room> ReadRoomFile() throws ClassNotFoundException, IOException {
		ArrayList<Room> results = new ArrayList<Room>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("room.dat");
			while (fis.available() > 0) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				results.addAll((ArrayList<Room>) ois.readObject());
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}
		return results;
	}

	// ===========================================================================================================================================================//
	// RESERVATION SERIALIZATION AND DESERIALIZATION METHODS
	// ===========================================================================================================================================================//

	/**
	 * <h1>writeReservationToFile</h1>
	 * <p>
	 * This method write reservation list to reservation.dat
	 * </p>
	 * 
	 * @param list
	 *            - list of reservation to write
	 */
	public void writeReservationToFile(ArrayList<Reservation> list) {
		FileOutputStream fos = null;
		ObjectOutputStream ois = null;
		try {
			fos = new FileOutputStream(new File("reservation.dat"), true);
			ois = new ObjectOutputStream(fos);
			ois.writeObject(list);
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>readReservationFile</h1>
	 * <p>
	 * This method read reservation list from reservation.dat
	 * </p>
	 * 
	 * @return - list of reservation
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find guest.dat
	 * @exception EOFException
	 *                - unable check if file stream end            
	 */
	public ArrayList<Reservation> readReservationFile() throws IOException, ClassNotFoundException {
		ArrayList<Reservation> results = new ArrayList<Reservation>();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream("reservation.dat");
			while (fis.available() > 0) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				results.addAll((ArrayList<Reservation>) ois.readObject());
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}
		return results;
	}

	// ===========================================================================================================================================================//
	// FOOD MENU SERIALIZATION AND DESERIALIZATION METHODS
	// ===========================================================================================================================================================//

	/**
	 * <h1>writeFoodItemToFile</h1>
	 * <p>
	 * This method write food list to foodItem.dat
	 * </p>
	 * 
	 * @param list
	 *            - list of food to write
	 */
	public void writeFoodItemToFile(ArrayList<FoodItem> list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(new File("foodItem.dat"), true);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * <h1>readFoodItem</h1>
	 * <p>
	 * This method read food item list from foodItem.dat
	 * </p>
	 * 
	 * @return - list of food item
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find guest.dat
	 * @exception EOFException
	 *                - unable check if file stream end            
	 */
	public ArrayList<FoodItem> readFoodItem() throws IOException, ClassNotFoundException {
		ArrayList<FoodItem> results = new ArrayList<FoodItem>();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream("foodItem.dat");
			while (fis.available() > 0) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				results.addAll((ArrayList<FoodItem>) ois.readObject());
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}
		return results;
	}

	// ===========================================================================================================================================================//
	// ROOM SERVICE SERIALIZATION AND DESERIALIZATION METHODS
	// ===========================================================================================================================================================//

	/**
	 * <h1>writeRoomServiceOrderToFile</h1>
	 * <p>
	 * This method write room service list to roomServiceOrder.dat
	 * </p>
	 * 
	 * @param list
	 *            - list of room service
	 */
	public void writeRoomServiceOrderToFile(ArrayList<RoomService> list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(new File("roomServiceOrder.dat"), true);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * <h1>readRoomServiceOrderFile</h1>
	 * <p>
	 * This method read room service order from roomServiceOrder.dat
	 * </p>
	 * 
	 * @return - list of room service
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find guest.dat
	 * @exception EOFException
	 *                - unable check if file stream end            
	 */
	public ArrayList<RoomService> readRoomServiceOrder() throws IOException, ClassNotFoundException {
		ArrayList<RoomService> results = new ArrayList<RoomService>();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream("roomServiceOrder.dat");
			while (fis.available() > 0) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				results.addAll((ArrayList<RoomService>) ois.readObject());
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}
		return results;
	}

	// ===========================================================================================================================================================//
	// CHECK IN SERIALIZATION AND DESERIALIZATION METHODS
	// ===========================================================================================================================================================//

	/**
	 * <h1>writecheckInDetailsToFile</h1>
	 * <p>
	 * This method write check in list to checkIn.dat
	 * </p>
	 * 
	 * @param details
	 *            - list of check in guest to write
	 */
	public void writecheckInDetailsToFile(ArrayList<ArrayList> details) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(new File("checkIn.dat"), true);
			out = new ObjectOutputStream(fos);
			out.writeObject(details);
			out.close();
			// System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * <h1>readCheckInDetailsToFile</h1>
	 * <p>
	 * This method read check in list from checkIn.dat
	 * </p>
	 * 
	 * @return - list of check in guest
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find guest.dat
	 * @exception EOFException
	 *                - unable check if file stream end            
	 */
	public ArrayList readCheckInDetailsToFile() throws IOException, ClassNotFoundException {
		ArrayList<RoomService> results = new ArrayList<RoomService>();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream("checkIn.dat");
			while (fis.available() > 0) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				results.addAll((ArrayList) ois.readObject());
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}
		return results;
	}

	// ===========================================================================================================================================================//
	// WALK IN SERIALIZATION AND DESERIALIZATION METHODS
	// ===========================================================================================================================================================//

	/**
	 * <h1>writeWalkInToFile</h1>
	 * <p>
	 * This method write walk in list to walkIn.dat
	 * </p>
	 * 
	 * @param list
	 *            - list of walk in guest to write
	 */
	public void writeWalkInToFile(ArrayList<ArrayList> list) {
		FileOutputStream fos = null;
		ObjectOutputStream ois = null;
		try {
			fos = new FileOutputStream(new File("walkIn.dat"), true);
			ois = new ObjectOutputStream(fos);
			ois.writeObject(list);
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>readWalkInFile</h1>
	 * <p>
	 * This method read guest list from guest.dat
	 * </p>
	 * 
	 * @return - list of walkIn guest
	 * @exception IOException
	 *                - input error
	 * @exception ClassNotFoundException
	 *                - unable to find walkIn.dat
	 * @exception EOFException
	 *                - unable check if file stream end            
	 */
	public ArrayList readWalkInFile() throws IOException, ClassNotFoundException {
		ArrayList results = new ArrayList();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream("walkIn.dat");
			while (fis.available() > 0) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				results.addAll((ArrayList) ois.readObject());
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}
		return results;
	}
}
