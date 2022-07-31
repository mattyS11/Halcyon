package components; 

public class Rectangle extends java.awt.Rectangle

{
	//Instance Variables
	private Position position;
	
	//Constructors
	
	/**
	 * @param toCopy A Rectangle object
	 */
	public Rectangle(Rectangle toCopy)
	{
		this.position = toCopy.position;
		this.width = toCopy.width;
		this.height = toCopy.height;
	}
	
	/**
	 * @param position A Position object
	 * @param width A float representing the width of a Rectangle object
	 * @param height A float representing the height of a Rectangle object
	 */
	public Rectangle(Position position, float width, float height) 
	{
		super((int) width, (int) height); 
		this.position = position;

   
	}
	
	//Getters
	
	/**
	 * @return Deep copy of a Position object
	 */
	public Position getPosition()
	{
		return new Position(position);
	}
	
	//Setters
	/**
	 * @param sets position to a deep copy of the passed in position
	 */
	public void setPosition(Position position)
	{
	    this.position = new Position(position);
	}
}
