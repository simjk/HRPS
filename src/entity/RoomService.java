package entity;

import java.io.Serializable;
import java.util.ArrayList;

import entity.FoodItem;
import entity.Guest;

/**
 * <h1>Room Service</h1>
 * <p>
 * This entity class holds information about room service.
 * </p>
 * <p>
 * The related control class is: RoomServiceControl.
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */

public class RoomService implements Serializable {
	/**
	 * The order id of this room service
	 */
	private int orderId;

	/**
	 * The remark of this room service
	 */
	private String remark;

	/**
	 * The order date of this room service
	 */
	private String orderDate;

	/**
	 * The order status of this room service
	 */
	private orderStatus OrderStatus;

	/**
	 * The guest list of this room service
	 */
	private ArrayList<Guest> g;

	/**
	 * The name of this room service
	 */
	private String name;

	/**
	 * The food item of this room service
	 */
	private ArrayList<FoodItem> userFoodItem;

	/**
	 * The room no. of this room service
	 */
	private String roomnum;

	/**
	 * Order status (Confirmed/ Preparing/ Delivered) of this room service
	 */
	public enum orderStatus {
		CONFIRMED, PREPARING, DELIVERED;
	}

	/**
	 * Create a room service with the given details.
	 * 
	 * @param orderId
	 *            The Room Service's order id
	 * @param orderDate
	 *            The Room Service's order date
	 * @param name
	 *            The Room Service's name
	 * @param roomnum
	 *            The Room Service's room no.
	 * @param userFoodItem
	 *            The Room Service's user food item
	 * @param remarks
	 *            The Room Service's remark
	 * @param OrderStatus
	 *            The Room Service's order status
	 */
	public RoomService(int orderId, String orderDate, String name, String roomnum, ArrayList<FoodItem> userFoodItem,
			String remarks, orderStatus OrderStatus) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.name = name;
		this.roomnum = roomnum;
		this.userFoodItem = userFoodItem;
		this.remark = remarks;
		this.OrderStatus = OrderStatus;
	}

	/**
	 * Default Room Service constructor
	 */
	public RoomService() {
	}

	/**
	 * <p>
	 * Gets the order id of the Room Service
	 * </p>
	 * 
	 * @return The Room Service's order id
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * <p>
	 * Set/Change the order id of Room Service
	 * </p>
	 * 
	 * @param orderId
	 *            The Room Service's order id
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * <p>
	 * Gets the remark of the Room Service
	 * </p>
	 * 
	 * @return The Room Service's remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * <p>
	 * Set/Change the remark of Room Service
	 * </p>
	 * 
	 * @param remark
	 *            The Room Service's remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * <p>
	 * Gets the order date of the Room Service
	 * </p>
	 * 
	 * @return The Room Service's order date
	 */
	public String getOrderDate() {
		return orderDate;
	}


	/**
	 * <p>
	 * Set/Change the order date of Room Service
	 * </p>
	 * 
	 * @param orderDate
	 *            The Room Service's order date
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * <p>
	 * Gets the order status of the Room Service
	 * </p>
	 * 
	 * @return The Room Service's order status
	 */
	public orderStatus getOrderStatus() {
		return OrderStatus;
	}

	/**
	 * <p>
	 * Set/Change the order status of Room Service
	 * </p>
	 * 
	 * @param orderStatus
	 *            The Room Service's order status
	 */
	public void setOrderStatus(orderStatus orderStatus) {
		OrderStatus = orderStatus;
	}

	/**
	 * <p>
	 * Gets the name of the Room Service
	 * </p>
	 * 
	 * @return The Room Service's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>
	 * Set/Change the order name of Room Service
	 * </p>
	 * 
	 * @param name
	 *            The Room Service's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p>
	 * Gets the room no. of the Room Service
	 * </p>
	 * 
	 * @return The Room Service's room no.
	 */
	public String getRoomnum() {
		return roomnum;
	}

	/**
	 * <p>
	 * Set/Change the room no. of Room Service
	 * </p>
	 * 
	 * @param roomnum
	 *            The Room Service's room no.
	 */
	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}

	/**
	 * <p>
	 * Gets the user food item of the Room Service
	 * </p>
	 * 
	 * @return The Room Service's user food item
	 */
	public ArrayList<FoodItem> getuserFoodItem() {
		return userFoodItem;
	}

	/**
	 * <p>
	 * Set/Change the user food item of Room Service
	 * </p>
	 * 
	 * @param userFoodItem
	 *            The Room Service's user food item
	 */
	public void setuserFoodItem(ArrayList<FoodItem> userFoodItem) {
		this.userFoodItem = userFoodItem;
	}

	/**
	 * <p>
	 * Gets the guest list of the Room Service
	 * </p>
	 * 
	 * @return The Room Service's guest list
	 */
	public ArrayList<Guest> getG() {
		return g;
	}

	/**
	 * <p>
	 * Set/Change the guest list of Room Service
	 * </p>
	 * 
	 * @param g
	 *            The Room Service's guest list
	 */
	public void setG(ArrayList<Guest> g) {
		this.g = g;
	}
}
