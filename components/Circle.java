package components;
import java.awt.geom.Ellipse2D.Float;

public class Circle extends java.awt.geom.Ellipse2D.Float
{
	//Instance Variables 
	
	private Position position;
	private float radius;
	
	
	//Constructers 
	
	/**
	 * Copy Constructer
	 * @param circleToCopy A circle object for the constructer to copy
	 */
    public Circle(Circle circleToCopy)
	{
		this.position = circleToCopy.getPosition();
		this.radius = circleToCopy.radius;
	}
	
	
    /**
     * Position and radius constructer
     * @param position A position object
     * @param radius A float to represent the circle's radius
     */
	public Circle(Position position, float radius)
	{
		this.position = new Position(position);
		this.radius = radius;
	}
	
	//Getters
	
	/**
	 * @return Returns a deep copy of a position object.
	 */
	public Position getPosition()
	{
		return new Position(position);
	}
	
	/**
	 * @return Returns a float representing the radius of a Circle object.
	 */
	public float getRadius()
	{
		return this.radius;
	}
	
	//Methods
	
	/**
	 * @param position A position for an entity
	 * @return Boolean to determine if this circle contains an Entity object's position
	 */
	public boolean inCircle(Position position)
	{
		Position newPosition = new Position(position);
		Float f = new Float(this.position.getPosX(), this.position.getPosY(), this.radius*2, this.radius*2);
		return f.contains(newPosition.getPosX(), newPosition.getPosY());
	}
}

	
