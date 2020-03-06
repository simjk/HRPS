package entity;

import java.io.Serializable;

/**
 * <h1>Guest</h1>
 * <p>
 * This entity class holds information about a guest.
 * </p>
 * <p>
 * The related control class is: GuestControl.
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */

public class Guest implements Serializable {
	/**
	 * The first and last name of this guest
	 */
	private String name;

	/**
	 * The address of this guest
	 */
	private String address;

	/**
	 * The gender of this guest
	 */
	private String gender;

	/**
	 * The country of this guest
	 */
	private String country;

	/**
	 * The identity of this guest
	 */
	private String identity;

	/**
	 * The nationality of this guest
	 */
	private String nationality;

	/**
	 * The contact of this guest
	 */
	private String contact;

	/**
	 * The credit card number of this guest
	 */
	private String creditCardNo;

	/**
	 * The identity type of this guest
	 */
	private IdType idtype;

	/**
	 * Identity type (Passport/ Driving License) of this guest
	 */
	public enum IdType {
		PASSPORT, DRIVING_LICENSE;
	}

	/**
	 * Default Guest constructor
	 */
	public Guest() {
	}

	/**
	 * Create a new Guest with the given details.
	 * 
	 * @param name
	 *            The Guest's name
	 * @param idtype
	 *            Identity's type: Passport/ Driving License
	 * @param identity
	 *            The Guest's Identity (NRIC)
	 * @param address
	 *            The Guest's address
	 * @param country
	 *            The Guest's country
	 * @param gender
	 *            The Guest's gender
	 * @param nationality
	 *            The Guest's nationality
	 * @param contact
	 *            The Guest's contact
	 * @param creditCardNo
	 *            The Guest's credit card number
	 */
	public Guest(String name, IdType idtype, String identity, String address, String country, String gender,
			String nationality, String contact, String creditCardNo) {
		this.name = name;
		this.idtype = idtype;
		this.identity = identity;
		this.address = address;
		this.country = country;
		this.gender = gender;
		this.nationality = nationality;
		this.contact = contact;
		this.creditCardNo = creditCardNo;
	}

	/**
	 * <p>
	 * Gets the first and last name of the Guest
	 * </p>
	 * 
	 * @return The Guest's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>
	 * Set/Change the name of the Guest
	 * </p>
	 * 
	 * @param name
	 *            The Guest's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p>
	 * Gets the address of the Guest
	 * </p>
	 * 
	 * @return The Guest's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <p>
	 * Set/Change the address of the Guest
	 * </p>
	 * 
	 * @param address
	 *            The Guest's address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * <p>
	 * Gets the gender of the Guest
	 * </p>
	 * 
	 * @return The Guest's gender.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * <p>
	 * Set/Change the gender of the Guest
	 * </p>
	 * 
	 * @param gender
	 *            The Guest's gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * <p>
	 * Gets the country of the Guest
	 * </p>
	 * 
	 * @return The Guest's country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * <p>
	 * Set/Change the country of the Guest
	 * </p>
	 * 
	 * @param country
	 *            The Guest's country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * <p>
	 * Gets the identity of the Guest
	 * </p>
	 * 
	 * @return The Guest's identity.
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * <p>
	 * Set/Change the identity of the Guest
	 * </p>
	 * 
	 * @param identity
	 *            The Guest's identity
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * <p>
	 * Gets the nationality of the Guest
	 * </p>
	 * 
	 * @return The Guest's nationality.
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * <p>
	 * Set/Change the nationality of the Guest
	 * </p>
	 * 
	 * @param nationality
	 *            The Guest's nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * <p>
	 * Gets the contact of the Guest
	 * </p>
	 * 
	 * @return The Guest's contact.
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * <p>
	 * Set/Change the contact of the Guest
	 * </p>
	 * 
	 * @param contact
	 *            The Guest's contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * <p>
	 * Gets the credit card no. of the Guest
	 * </p>
	 * 
	 * @return The Guest's credit card no.
	 */
	public String getCreditCardNo() {
		return creditCardNo;
	}

	/**
	 * <p>
	 * Set/Change the credit card number of the Guest
	 * </p>
	 * 
	 * @param creditCardNo
	 *            The Guest's credit card no.
	 */
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	/**
	 * <p>
	 * Gets the id type of the Guest
	 * </p>
	 * 
	 * @return The Guest's id tyoe.
	 */
	public IdType getIdtype() {
		return idtype;
	}

	/**
	 * <p>
	 * Set/Change the id type of the Guest
	 * </p>
	 * 
	 * @param idtype
	 *            The Guest's id type
	 */
	public void setIdtype(IdType idtype) {
		this.idtype = idtype;
	}
}
