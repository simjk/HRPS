package entity;

import java.io.Serializable;

/**
 * <h1>Room</h1>
 * <p>
 * This entity class holds information about a room.
 * </p>
 * <p>
 * The related control class is: RoomControl.
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */
public class Room implements Serializable {
	/**
	 * The room type of this room
	 */
	private RoomType roomType;

	/**
	 * The weekday room rate of this room
	 */
	private double weekdayRoomRate;

	/**
	 * The weekend room rate of this room
	 */
	private double weekendRoomRate;

	/**
	 * The room no. of this room
	 */
	private String roomNum;

	/**
	 * The bed type of this room
	 */
	private BedType bedType;

	/**
	 * The smoke allow option of this room
	 */
	private boolean smoke;

	/**
	 * The room status of this room
	 */
	private RoomStatus roomStatus;

	/**
	 * The wifi enabled option of this room
	 */
	private boolean wifiEnabled;

	/**
	 * The facing view of this room
	 */
	private FacingView facingView;

	/**
	 * Room type (Single, Double, Deluxe, Vip suite) of this room
	 */
	public enum RoomType {
		SINGLE, DOUBLE, DELUXE, VIP_SUITE
	}

	/**
	 * Bed type (Single, Double, Master) of this room
	 */
	public enum BedType {
		SINGLE_BED, DOUBLE_BED, MASTER_BED
	}

	/**
	 * Room status (Vacant, Occupied, Reserved, Under maintenance) of this room
	 */
	public enum RoomStatus {
		VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE
	}

	/**
	 * Facing view (City, Sea, Mountain) of this room
	 */
	public enum FacingView {
		CITY_VIEW, SEA_VIEW, MOUNTAIN_VIEW
	}

	/**
	 * Default Room constructor
	 */
	public Room() {

	}

	/**
	 * Create a new room with the given details.
	 * 
	 * @param roomNum
	 *            The Room's no.
	 * @param roomType
	 *            The Room's room type
	 * @param weekdayRoomRate
	 *            The Room's weekday room rate
	 * @param weekendRoomRate
	 *            The Room's weekend room rate
	 * @param wifiEnabled
	 *            The Room's wifi option
	 * @param smoke
	 *            The Room's smoke option
	 * @param bedType
	 *            The Room's bed type
	 * @param facingView
	 *            The Room's facing view
	 * @param roomStatus
	 *            The Room's room status
	 */
	public Room(String roomNum, RoomType roomType, double weekdayRoomRate, double weekendRoomRate, boolean wifiEnabled,
			boolean smoke, BedType bedType, FacingView facingView, RoomStatus roomStatus) {
		this.roomNum = roomNum;
		this.roomType = roomType;
		this.weekdayRoomRate = weekdayRoomRate;
		this.weekendRoomRate = weekendRoomRate;
		this.wifiEnabled = wifiEnabled;
		this.smoke = smoke;
		this.bedType = bedType;
		this.facingView = facingView;
		this.roomStatus = roomStatus;
	}

	/**
	 * <p>
	 * Gets the first and last name of the Room
	 * </p>
	 * 
	 * @return The Room's type
	 */
	public RoomType getRoomType() {
		return roomType;
	}

	/**
	 * <p>
	 * Set/Change the room type of the Room
	 * </p>
	 * 
	 * @param roomType
	 *            The Room's room type
	 */
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	/**
	 * <p>
	 * Gets the weekday room rate of the Room
	 * </p>
	 * 
	 * @return The Room's weekday room rate
	 */
	public double getweekdayRoomRate() {
		return weekdayRoomRate;
	}

	/**
	 * <p>
	 * Set/Change the weekday room rate of the Room
	 * </p>
	 * 
	 * @param weekdayRoomRate
	 *            The Room's weekday room rate
	 */
	public void setweekdayRoomRate(double weekdayRoomRate) {
		this.weekdayRoomRate = weekdayRoomRate;
	}
	
	/**
	 * <p>
	 * Gets the weekend room rate of the Room
	 * </p>
	 * 
	 * @return The Room's weekend room rate 
	 */
	public double getweekendRoomRate() {
		return weekendRoomRate;
	}

	/**
	 * <p>
	 * Set/Change the weekend room rate of the Room
	 * </p>
	 * 
	 * @param weekendRoomRate
	 *            The Room's weekend room rate
	 */
	public void setweekendRoomRate(double weekendRoomRate) {
		this.weekendRoomRate = weekendRoomRate;
	}

	/**
	 * <p>
	 * Gets the room no. of the Room
	 * </p>
	 * 
	 * @return The Room's no
	 */
	public String getRoomNum() {
		return roomNum;
	}

	/**
	 * <p>
	 * Set/Change the room no. of the Room
	 * </p>
	 * 
	 * @param roomNum
	 *            The Room's room no.
	 */
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	/**
	 * <p>
	 * Gets the bed type of the Room
	 * </p>
	 * 
	 * @return The Room's bed type
	 */
	public BedType getBedType() {
		return bedType;
	}

	/**
	 * <p>
	 * Set/Change the bed type of the Room
	 * </p>
	 * 
	 * @param bedType
	 *            The Room's bed type
	 */
	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}

	/**
	 * <p>
	 * Gets the wifi option of the Room
	 * </p>
	 * 
	 * @return The Room's wifi option
	 */
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}

	/**
	 * <p>
	 * Set/Change the wifi option of the Room
	 * </p>
	 * 
	 * @param wifiEnabled
	 *            The Room's wifi option
	 */
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}

	/**
	 * <p>
	 * Gets the facing view of the Room
	 * </p>
	 * 
	 * @return The Room's facing view
	 */
	public FacingView getFacingView() {
		return facingView;
	}

	/**
	 * <p>
	 * Set/Change the facing view of the Room
	 * </p>
	 * 
	 * @param facingView
	 *            The Room's facing view
	 */
	public void setFacingView(FacingView facingView) {
		this.facingView = facingView;
	}

	/**
	 * <p>
	 * Gets the smoking option of the Room
	 * </p>
	 * 
	 * @return The Room's smoking option
	 */
	public boolean getSmoke() {
		return smoke;
	}

	/**
	 * <p>
	 * Set/Change the smoke option of the Room
	 * </p>
	 * 
	 * @param smoke
	 *            The Room's smoke option
	 */
	public void setSmoke(boolean smoke) {
		this.smoke = smoke;
	}

	/**
	 * <p>
	 * Gets the room status of the Room
	 * </p>
	 * 
	 * @return The Room's status
	 */
	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	/**
	 * <p>
	 * Set/Change the room status of the Room
	 * </p>
	 * 
	 * @param roomStatus
	 *            The Room's room status
	 */
	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

}
