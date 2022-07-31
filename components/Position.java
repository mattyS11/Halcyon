package components; 


import static java.lang.Math.sqrt;

import gameObjects.Entity;
import gameObjects.Player;

public class Position 
{
	// Instance Variables
	private int roomIndex;
	private float posX;
	private float posY;
	
	
	// Constructors
	 
	/**
	 * Copy constructor
	 * @param positionToCopy a Position object
	 */
	public Position(Position positionToCopy)
	{
		setRoomIndex(positionToCopy.roomIndex);
		setPosX(positionToCopy.posX);
		setPosY(positionToCopy.posY);
	}
	
	public Position(int roomIndex, float posX, float posY)
	{
		setRoomIndex(roomIndex);
		setPosX(posX);
		setPosY(posY);
	}
		
	
	// Getters/Setters
	
	/**
	 * @return index of a Room within an ArrayList
	 */
	public int getRoomIndex()
	{
		return roomIndex;
	}
	 
	/**
	 * @return the X position of an Entity object
	 */
	public float getPosX()
	{
		return this.posX;
	}
	
	/**
	 * @return the Y position of an Entity object
	 */ 
	public float getPosY()
	{
		return this.posY;
	}
	
	/**
	 * @param newPosX the new X position of an Entity object
	 * @return 
	 */ 
	public void setPosX(float newPosX)
	{
		if (newPosX >= 0)
		this.posX = newPosX;
	}
	
	/**
	 * @param newPosY the new Y position of an Entity object
	 * @return 
	 */ 
	public void setPosY(float newPosY)
	{
		if (newPosY >= 0 )
		this.posY = newPosY;
	}
	 
	/**
	 * @param newRoomIndex the new index of a Room object within an ArrayList
	 */ 
	public void setRoomIndex(int newRoomIndex)
	{
		if (newRoomIndex >= 0)
		this.roomIndex = newRoomIndex;
	}
	
	// Methods
	
	/**
	 * A method which returns the distance between two Entity objects
	 * @param entity An Entity object
	 * @return a float representing the distance between two Entity objects
	 */
	public float distanceTo(Entity entity)
	{
		double distance = Math.sqrt(Math.pow((double)entity.getPosition().getPosX() - (double)this.posX, 2) + Math.pow((double)entity.getPosition().getPosY() - (double)this.posY, 2));
		return (float)distance; 
	}
	
	public boolean equalPos(Position position)
	{
		if((int)(this.posX/16) == (int)(position.getPosX()/16) && (int)(this.posY/16) == (int)(position.getPosY()/16))
			return(true);
		else
			return(false);
	}
}

