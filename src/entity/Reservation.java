package entity;

import java.util.ArrayList;
import java.util.Date;

import entity.Guest;
import entity.Room;

import java.io.Serializable;

/**
 * <h1>Reservation</h1>
 * <p>
 * This entity class holds information about reservation.
 * </p>
 * <p>
 * The related control class is: ReservationControl
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */
public class Reservation implements Serializable {
	/**
	 * The reservation no. of this reservation
	 */
	private int reservationNo;

	/**
	 * The check in date of this reservation
	 */
	private Date checkIn;

	/**
	 * The check out date of this reservation
	 */
	private Date checkOut;

	/**
	 * The number of adult of this reservation
	 */
	private int numOfAdult;

	/**
	 * The number of child of this reservation
	 */
	private int numOfChild;

	/**
	 * The reservation status of this reservation
	 */
	private Status status;

	/**
	 * The guest list of this reservation
	 */
	private ArrayList<Guest> g;

	/**
	 * The room list of this reservation
	 */
	private ArrayList<Room> r;

	/**
	 * Status (Confirmed/ Waitlist/ Checked in/ Checked out, Expired) of this
	 * reservation
	 */
	public enum Status {
		CONFIRMED, WAITLIST, CHECKED_IN, CHECKED_OUT, EXPIRED;
	}

	/**
	 * Default Reservation constructor
	 */
	public Reservation() {

	}

	/**
	 * Create a new Reservation with the given details.
	 * 
	 * @param reservationNo
	 *            The Reservation's no
	 * @param checkIn
	 *            The Reservation's check in date
	 * @param checkOut
	 *            The Reservation's check out date
	 * @param numOfAdult
	 *            The Reservation's no. of adults
	 * @param numofChild
	 *            The Reservation's no. of child
	 * @param status
	 *            The Reservation's status
	 * @param g
	 *            The Reservation's guest list
	 * @param r
	 *            The Reservation's room list
	 */
	public Reservation(int reservationNo, Date checkIn, Date checkOut, int numOfAdult, int numofChild, Status status,
			ArrayList<Guest> g, ArrayList<Room> r) {
		this.reservationNo = reservationNo;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.numOfAdult = numOfAdult;
		this.numOfChild = numofChild;
		this.status = status;
		this.g = g;
		this.r = r;
	}

	/**
	 * <p>
	 * Gets the reservation's no. of Reservation
	 * </p>
	 * 
	 * @return The Reservation's reservation no.
	 */
	public int getReservationNo() {
		return reservationNo;
	}

	/**
	 * <p>
	 * Set/Change the reservation's no of the Reservation
	 * </p>
	 * 
	 * @param reservationNo
	 *            The Reservation's reservation no.
	 */
	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}
	
	/**
	 * <p>
	 * Gets the check date in of Reservation
	 * </p>
	 * 
	 * @return The Reservation's check in date
	 */
	public Date getCheckIn() {
		return checkIn;
	}

	/**
	 * <p>
	 * Set/Change the reservation's check in date of the Reservation
	 * </p>
	 * 
	 * @param checkIn
	 *            The Reservation's check in date.
	 */
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	/**
	 * <p>
	 * Gets the check out date of Reservation
	 * </p>
	 * 
	 * @return The Reservation's check out date
	 */
	public Date getCheckOut() {
		return checkOut;
	}

	/**
	 * <p>
	 * Set/Change the reservation's check out date of the Reservation
	 * </p>
	 * 
	 * @param checkOut
	 *            The Reservation's check out date.
	 */
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	/**
	 * <p>
	 * Gets the reservation's no. of adult of Reservation
	 * </p>
	 * 
	 * @return The Reservation no. of adult
	 */
	public int getNumOfAdult() {
		return numOfAdult;
	}

	/**
	 * <p>
	 * Set/Change the reservation's no. of adults of the Reservation
	 * </p>
	 * 
	 * @param numOfAdult
	 *            The Reservation's no. of adults
	 */
	public void setNumOfAdult(int numOfAdult) {
		this.numOfAdult = numOfAdult;
	}

	/**
	 * <p>
	 * Gets the reservation's no. of child of Reservation
	 * </p>
	 * 
	 * @return The Reservation no. of child
	 */
	public int getNumOfChild() {
		return numOfChild;
	}

	/**
	 * <p>
	 * Set/Change the reservation's no. of child of the Reservation
	 * </p>
	 * 
	 * @param numOfChild
	 *            The Reservation's no. of child
	 */
	public void setNumOfChild(int numOfChild) {
		this.numOfChild = numOfChild;
	}

	/**
	 * <p>
	 * Gets the reservation's status of Reservation
	 * </p>
	 * 
	 * @return The Reservation's status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * <p>
	 * Set/Change the reservation's status of the Reservation
	 * </p>
	 * 
	 * @param status
	 *            The Reservation's status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * <p>
	 * Gets the reservation's guest list of Reservation
	 * </p>
	 * 
	 * @return The Reservation's guest list
	 */
	public ArrayList<Guest> getG() {
		return g;
	}

	/**
	 * <p>
	 * Set/Change the reservation's guest list of the Reservation
	 * </p>
	 * 
	 * @param g
	 *            The Reservation's guest list
	 */
	public void setG(ArrayList<Guest> g) {
		this.g = g;
	}

	/**
	 * <p>
	 * Gets the reservation's room list of Reservation
	 * </p>
	 * 
	 * @return The Reservation's room list
	 */
	public ArrayList<Room> getR() {
		return r;
	}

	/**
	 * <p>
	 * Set/Change the reservation's room list of the Reservation
	 * </p>
	 * 
	 * @param r
	 *            The Reservation's room list
	 */
	public void setR(ArrayList<Room> r) {
		this.r = r;
	}

}
