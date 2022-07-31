package gameObjects;

import components.Exit;
import java.util.ArrayList;

/**
* A class that holds objects and a floor to display the ground
* @version	1.0
*/
public class Room
{
    //Instance Variables
    private TileGrid floor;
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private ArrayList<Exit> exits = new ArrayList<Exit>();

    //Constructors
    /**
    * Default constructor
    */
    public Room()
    {
        floor = new TileGrid(3);
    }
    /**
    * Constructor for Room with a floor, but default entities.
    * @param aFloor  A TileGrid object
    */
    public Room(TileGrid aFloor)
    {
        floor = aFloor;
    }
    /**
    * Constructor for default floor, and entities
    * @param entities  An array of Entity objects
    */
    public Room(ArrayList<Entity> entities)
    {
        floor = new TileGrid(3);
        addEntities(entities);
    }
    /**
    * Constructor for a floor and entities.
    * @param aFloor  A TileGrid object
    * @param theEntities  An array of Entity objects
    */
    public Room(TileGrid aFloor, ArrayList<Entity> theEntities)
    {
        floor = aFloor;
        addEntities(theEntities);
    }

    //Getters Setters
    /**
    *Used to retrieve the entity list
    *@return Returns the Entity list
    */
    public ArrayList<Entity> getEntity()
    {    	
    	if(entities.isEmpty())
    	{
    		return(null);
    	}
    	
        return entities;
    }
    
    /**
     * @return Returns the length of the entities array
     */
    public int getEntityArrayLength()
    {
        return entities.size();
    }
    
    /**
    * Method to add Entity objects to an existing array of Entities
    * @param entitiesToAdd  The current list of Entity objects
    */
    public void addEntities(ArrayList<Entity> entitiesToAdd)
    {
        entities.addAll(entitiesToAdd);
    }
    
    /**
     * Adds an entity to the list of entities
     * @param newEntity
     */
    public void addEntity(Entity newEntity)
    {
    	entities.add(newEntity);
    }
    
    /**
     * Removes the specified entity
     * @param entityToRemove
     */
    public void removeEntity(Entity entityToRemove)
    {
    	entities.remove(entityToRemove);
    }
    /**
    * @return Returns a TileGrid object as a board for the gamespace
    */
    public TileGrid getFloor()
    {
        return floor;
    }
    
    /**
     * Used to retrieve the list of exits in the room
     * @return ArrayList<Exit>
     */
    public ArrayList<Exit> getExits()
    {
    	return exits;
    }
    
    /**
     * Adds the specified exit to the room
     * @param exit
     */
    public void addExit(Exit exit)
    {
    	exits.add(exit);
    }
    
    /**
     * Removes the specified exit from the room
     * @param exit
     */
    public void removeExit(Exit exit)
    {
    	exits.remove(exit);
    }
}
