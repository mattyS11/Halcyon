package gameObjects;
import java.awt.image.BufferedImage;

/**
* Defines the look and feel of everyTile covering the floors and walls
* @version 1.0
*/
public class Tile
{
    //Instance Variables
    private BufferedImage texture;
    private boolean collidable = false;

    //Constructors
    /**
    * Constructor with an image leaving collidable false
    * @param texture [description]
    */
    public Tile(BufferedImage texture)
    {
        this.texture = texture;
    }
    /**
    * Constructor with an image and explicitly
    * @param texture    Image that displays the Tile.
    * @param collidable Whether the player can walk on this Tile.
    */
    public Tile(BufferedImage texture, boolean collidable)
    {
        this.texture = texture;
        this.collidable = collidable;
    }
    
    /**
     * Constructor to create a new tile based on an existing one
     * @param tileToCopy  The tile from which the values will be copied
     */
    public Tile(Tile tileToCopy)
    {
        this.texture = tileToCopy.getTexture();
        this.collidable = tileToCopy.getCollidable();
    }

    //Getters Setters
    /**
    * @return texture Image that displays the Tile.
    */
    public BufferedImage getTexture()
    {
        return texture;
    }
    /**
    * @return collidable Whether the player can walk on this Tile.
    */
    public boolean getCollidable()
    {
        return collidable;
    }
}
