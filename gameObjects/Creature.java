package gameObjects;

import administrative.Game;
import components.Position;
import components.Rectangle;
import global.Assets;

/**
* An Entity that can move and collide.
* @version	1.2
*/
public abstract class Creature extends Entity
{

    //Instance variables
    protected float xMove;
    protected float yMove;
    protected float speed = 1;
    protected Rectangle hitBox ;
    protected float hitBoxXOffset;
    protected float hitBoxYOffset;
    protected boolean wantsToInteract;

    //Constructors
    /**
    * Default constructor
    * @param hitBox The creature's hitbox. Position is relative to the creature.
    */
    public Creature(Rectangle hitBox, Position position, char symbol)
    {
        super(position, symbol);
    	this.hitBox = hitBox;
    	hitBoxXOffset = (float) hitBox.getPosition().getPosX();
    	hitBoxYOffset = (float) hitBox.getPosition().getPosY();
    }


    //Methods

    /**
    * Updates the hitbox position to stay connected with the creature.
    */
    public void tick()
    {
    	hitBox.setPosition(new Position(hitBox.getPosition().getRoomIndex(), (position.getPosX()+hitBoxXOffset), (position.getPosY()+hitBoxYOffset)));

    }

    /**
    * Moves the creature in the X and Y axis based on its xMove and yMove variables.
    */
    protected void move()
    {
        moveX();
    	moveY();
    }

    /**
    * Moves the creature in the X unless a collideable tile blocks this path.
    */
    private void moveX()
    {
        int collisonRightPadding = 1;
    	if (xMove > 0)
    	{
            //Moving Right
    	    int movingToColumn = (int) (hitBox.getPosition().getPosX()
                                                        + hitBox.getWidth()
                                                        + xMove)
                                                        / Assets.get_TILESIZE();
    		int movingToRowTop = (int) hitBox.getPosition().getPosY() / Assets.get_TILESIZE();
    		int movingToRowBottom = (int) (hitBox.getPosition().getPosY()
                                                        + hitBox.getHeight())
                                                        / Assets.get_TILESIZE();

            /**
            * Check that the top and bottom of the creature hitbox are landing on
            * collidable false tiles
            */
    		if ( !Game.getRoom(0).getFloor().getTile(movingToRowTop,movingToColumn).getCollidable()
    			&& !Game.getRoom(0).getFloor().getTile(movingToRowBottom,movingToColumn).getCollidable())
    		{
    			position.setPosX(position.getPosX()+xMove);
    		}
    		else
    		{
    			//If we are colliding move to the edge of the tile we're colliding with
    			position.setPosX((float) (movingToColumn
                                            * Assets.get_TILESIZE()
                                            - hitBox.getWidth()
                                            - hitBoxXOffset
                                            - collisonRightPadding));
    		}
    	}
    	else if (xMove < 0)
    	{
    		int movingToColumn = (int) (hitBox.getPosition().getPosX() + xMove)/ Assets.get_TILESIZE();
    		int movingToRowTop = (int) hitBox.getPosition().getPosY() / Assets.get_TILESIZE();
    		int movingToRowBottom = (int) (hitBox.getPosition().getPosY()
                                                                + hitBox.getHeight()) / Assets.get_TILESIZE();

    		//Check that the top and bottom of the creature are about to land on a non-solid tiles
    		if (!Game.getRoom(0).getFloor().getTile(movingToRowTop,movingToColumn).getCollidable()
    			&& !Game.getRoom(0).getFloor().getTile(movingToRowBottom,movingToColumn).getCollidable())
    		{
    			position.setPosX(position.getPosX()+xMove);
    		}
    	}
    }

    /**
    * Moves the creature in the Y unless a collideable tile blocks this path.
    */
    private void moveY()
    {
        int collisonDownPadding = 1;
    	if (yMove < 0)
    	{
    		//Going up
    		int movingToRow = (int)(hitBox.getPosition().getPosY() + yMove) / Assets.get_TILESIZE();
    		int movingToColumnLeft = (int)  hitBox.getPosition().getPosX() / Assets.get_TILESIZE();
    		int movingToColumnRight = (int) (hitBox.getPosition().getPosX() + hitBox.width) / Assets.get_TILESIZE();

    		//Check that the left and right of the creature are about to land on a non-solid tiles
    		if (!Game.activeRoom.getFloor().getTile(movingToRow,movingToColumnLeft).getCollidable()
					&& !Game.activeRoom.getFloor().getTile(movingToRow,movingToColumnRight).getCollidable())
			{
				position.setPosY(position.getPosY()+yMove);
			}
    	}
    	else if (yMove > 0)
    	{
    		//Going down
    		int movingToRow = (int)(hitBox.getPosition().getPosY()
                                                    + yMove
                                                    + hitBox.getHeight()) / Assets.get_TILESIZE();
    		int movingToColumnLeft = (int)  hitBox.getPosition().getPosX() / Assets.get_TILESIZE();
    		int movingToColumnRight = (int) (hitBox.getPosition().getPosX()
                                                                    + hitBox.width) / Assets.get_TILESIZE();

    		//Check that the left and right of the creature are about to land on a non-solid tiles
    		if (!Game.activeRoom.getFloor().getTile(movingToRow,movingToColumnLeft).getCollidable()
				&& !Game.activeRoom.getFloor().getTile(movingToRow,movingToColumnRight).getCollidable())
			{
				position.setPosY(position.getPosY()+yMove);
			}
    		else
    		{
    			//If we are colliding move to the edge of the tile we're colliding with
    			position.setPosY((float) (movingToRow
                                            * Assets.get_TILESIZE()
                                            - hitBox.getHeight()
                                            -hitBoxYOffset
                                            -collisonDownPadding));
    		}
    	}
    }

    /**
    *@return a boolean for whether the creature wants to interact with Player
    */
    public boolean getWantsToInteract()
    {
        return this.wantsToInteract;
    }

    /**
    * @return returns a deep copy of the creatures hitbox Rectangle
    */
    public Rectangle getHitBox()
    {
        return new Rectangle(hitBox);
    }
    
    public void setHitBox(Rectangle hitBox) 
    {
        this.hitBox = hitBox;
    }
}
