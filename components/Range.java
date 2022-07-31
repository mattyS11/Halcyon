package components;
import java.util.ArrayList;

import administrative.Game;
import gameObjects.Entity;
import gameObjects.Room;

public class Range {

	//Instance Variables
	
	private Position position;
	private Circle circle;

	//Constructors
	
	/**
	 * Constructor for a circle around the player.
	 * @param position A Position object
	 * @param circle A Circle object
	 */
	public Range(Position position, Circle circle){
		this.position = position;
		this.circle = circle;
	}
	
	/**
	 * Copy Constructor
	 * @param rangeToCopy a Range object
	 */
	public Range(Range rangeToCopy)
	{
		this.position = rangeToCopy.getPosition();
		this.circle = rangeToCopy.getCircle();
	}

	//Methods
	
	/**
	 * Method which returns a list of Entities in range of an entity
	 * according to the entity posX, posY and radius.
	 * @param int i The index of the current room the entity is in, in an array of Room objects.
	 * @return entitiesInRangeList A list of Entities which are in range of the entity
	 * 
	 */
    public ArrayList<Entity> entitiesInRange(int i) {
		ArrayList<Entity> entitiesInRangeList = new ArrayList<Entity>();
		Room currentRoom = Game.activeRoom;
		
		
		for(Entity entity : currentRoom.getEntity())
		{
			if(Math.pow(entity.getPosition().getPosX() - this.position.getPosX(), 2) + Math.pow(entity.getPosition().getPosY() - this.position.getPosY(), 2) < Math.pow(this.circle.getRadius(), 2))
			{
				entitiesInRangeList.add(entity);
			}
		}
        
        
        return entitiesInRangeList;
		}	
     
    // Getters/Setters
     
    /**
     * @return the radius of a Range object
     */
	public float getRadius(){
       return this.circle.getRadius();
	}

    /**
     * @return X position of a Range object
     */
	public float getPosX(){
       return this.position.getPosX();
    }
    
    /**
     * @return Y position of A range object
     */
    public float getPosY(){  
       return this.position.getPosY();
    }
    
    /**
     * @return A deep copy of a Position object
     */
    public Position getPosition()
    {
		return new Position(this.position);
	}
	
	/**
	 * @param newPosition a Position object
	 */
	public void setPosition(Position position)
	{
		this.position = new Position(position);
	}
	
	/**
	 * @return a Circle object.
	 */
	public Circle getCircle()
	{
		return new Circle(this.circle);
	}
	

}

