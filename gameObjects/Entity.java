package gameObjects;
import java.awt.image.BufferedImage;

import components.Position;
import global.Assets;

/**
* Objects, things, npc's extend Entity almost everything that is not a tile is an Entity.
* @version	1.1
*/
public abstract class Entity
{
    //Instance Variables
    protected Position position;
    protected char symbol;
    protected BufferedImage sprite = null;

    //Methods
    public abstract void tick();


    //Constructor
    /**
    * Position and Symbol Constructor
    * @param position object that contains an x (positive distance right from
    * left wall of room) and a y (positive distance down from top wall of room)
    * @param symbol How the object gets displayed in text mode
    */
    public Entity(Position position, char symbol)
    {
        this.position = position;
        this.symbol = symbol;
        this.sprite = Assets.get_SPRITE(this.getSymbol());
    }

    //Getters Setters
    /**
    * @return a deep copy of the current entity's position
    */
    public Position getPosition()
    {
        return new Position(this.position);
    }

    /**
    * @return Symbol used to display in text mode
    */
    public char getSymbol()
    {
        return this.symbol;
    }

    /**
    * @return sprite used to display in gui mode
    */
    public BufferedImage getSprite()
    {
        return this.sprite;
    }

    /**
    * @param position Position object w/ x and y information to be deep copied
    */
    public void setPosition(Position position)
    {
        this.position = new Position(position);
    }

    /**
    * @param sprite sprite used to display in gui mode
    */
    public void setSprite(BufferedImage sprite)
    {
        this.sprite = sprite;
    }
}
