package entity;

import java.io.Serializable;

/**
 * <h1>Food Item</h1>
 * <p>
 * This entity class holds information about food item
 * </p>
 * <p>
 * The related control class is: RoomServiceControl
 * </p>
 * 
 * @author Sim Jun Kai
 * @version 1.0
 * @since 14-04-2018
 */
public class FoodItem implements Serializable {
	/**
	 * The name of this food
	 */
	private String name;

	/**
	 * The description of this food
	 */
	private String description;

	/**
	 * The preparation steps of this food
	 */
	private String prepSteps;

	/**
	 * The quantity of this food
	 */
	private int quantity;

	/**
	 * The price of this food
	 */
	private double price;

	/**
	 * The id of this food
	 */
	private int foodId;

	/**
	 * Default FoodItem constructor
	 */
	public FoodItem() {

	}

	/**
	 * Create a new Food Item with the given details/
	 * 
	 * @param foodId
	 *            The Food's id
	 * @param name
	 *            The Food's name
	 * @param description
	 *            The Food's description
	 * @param prepSteps
	 *            The Food's preparation steps
	 * @param quantity
	 *            The Food's quantity
	 * @param price
	 *            The Food's price
	 */
	public FoodItem(int foodId, String name, String description, String prepSteps, int quantity, double price) {
		this.foodId = foodId;
		this.name = name;
		this.description = description;
		this.prepSteps = prepSteps;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * <p>
	 * Gets the name of the Food
	 * </p>
	 * 
	 * @return The Food's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>
	 * Set/Change the name of the Food
	 * </p>
	 * 
	 * @param name
	 *            The Food's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p>
	 * Gets the description of the Food
	 * </p>
	 * 
	 * @return The Food's description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <p>
	 * Set/Change the description of the Food
	 * </p>
	 * 
	 * @param description
	 *            The Food's description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * <p>
	 * Gets the preparation steps of the Food
	 * </p>
	 * 
	 * @return The Food's preparation steps
	 */
	public String getPrepSteps() {
		return prepSteps;
	}
	
	/**
	 * <p>
	 * Set/Change the preparation steps of the Food
	 * </p>
	 * 
	 * @param prepSteps
	 *            The Food's preparation steps
	 */
	public void setPrepSteps(String prepSteps) {
		this.prepSteps = prepSteps;
	}

	/**
	 * <p>
	 * Gets the quantity of the Food
	 * </p>
	 * 
	 * @return The Food's quantity.
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * <p>
	 * Set/Change the quantity of the Food
	 * </p>
	 * 
	 * @param quantity
	 *            The Food's quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * <p>
	 * Gets the price of the Food
	 * </p>
	 * 
	 * @return The Food's price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * <p>
	 * Set/Change the description of the Food
	 * </p>
	 * 
	 * @param price
	 *            The Food's price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * <p>
	 * Gets the id of the Food
	 * </p>
	 * 
	 * @return The Food's id.
	 */
	public int getFoodId() {
		return foodId;
	}

	/**
	 * <p>
	 * Set/Change the id of the Food
	 * </p>
	 * 
	 * @param foodId
	 *            The Food's id
	 */
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
}
