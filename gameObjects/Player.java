package gameObjects;
import java.awt.event.KeyEvent;

import administrative.Game;
import components.Position;
import components.Rectangle;
import global.Assets;
import global.Input;

/**
* An Entity and a Creature that can be controlled by the user
* @version	1.1
*/
public class Player extends Creature
{
    
    public static Player player;

    /**
    * Player Constructor
    */
    private Player(Rectangle hitBox, Position position, char symbol)
    {
        super(hitBox, position, symbol);
        this.position.setPosY(6*Assets.get_TILESIZE());
        this.position.setPosX(4*Assets.get_TILESIZE());
    }

    //Methods
    /**
    * Actions the player should execute before every frame renders.
    */
    @Override
    public void tick()
    {
    	if (Input.getKeyDown(KeyEvent.VK_SHIFT))
    	{
    		speed = 2;
    	}
    	else
    	{
    		speed = 1;
    	}
        if (Input.getKeyDown(KeyEvent.VK_P))
        {
            System.out.println("Row: "+ (int)(this.position.getPosX()/Assets.get_TILESIZE()) + "\tCol: "+ (int)(this.position.getPosY()/Assets.get_TILESIZE()));
        }
    	super.tick();
        getInput();
        xMove *= speed;
        yMove *= speed;
        move();
    }

    /**
    * Sets a value based on user input that determines which direction
    * the Player symbol will move on the TileGrid.
    */
    public void getInput()
    {
        xMove = 0;
        yMove = 0;

        if (Input.getKeyDown(KeyEvent.VK_A) || Input.getKeyDown(KeyEvent.VK_LEFT))
        {
            xMove = -1;
        }
        if (Input.getKeyDown(KeyEvent.VK_D)|| Input.getKeyDown(KeyEvent.VK_RIGHT))
        {
            xMove = 1;
        }
        if (Input.getKeyDown(KeyEvent.VK_W)|| Input.getKeyDown(KeyEvent.VK_UP))
        {
            yMove = -1;
        }
        if (Input.getKeyDown(KeyEvent.VK_S)|| Input.getKeyDown(KeyEvent.VK_DOWN))
        {
            yMove = 1;
        }
    }

    public static Player getPlayer()
    {
        
        final int t = Assets.get_TILESIZE();
        final int HERO_POS_X = 3 * t;
        final int HERO_POS_Y = 3 * t;
        final int HERO_HITBOX_X_OFFSET = 0;
        final int HERO_HITBOX_Y_OFFSET = (t/2)-2;
        final int HERO_HITBOX_WIDTH = t-1;
        final int HERO_HITBOX_HEIGHT = t/2;
        final char HERO_SYMBOL = '*';
        
        if(player == null)
        {
			final String CHECKPOINT_FILE_NAME = "resources/checkPoint.txt";
        
			final int HERO_ROOM_INDEX = Game.getCheckPoint(CHECKPOINT_FILE_NAME);
            
            Position heroPos = new Position(HERO_ROOM_INDEX,
                    HERO_POS_X, HERO_POS_Y);
            Rectangle heroHitbox = new Rectangle(
                    new Position(HERO_ROOM_INDEX, 
                            HERO_HITBOX_X_OFFSET, HERO_HITBOX_Y_OFFSET),
                    (float)HERO_HITBOX_WIDTH,
                    (float)HERO_HITBOX_HEIGHT);
            player = new Player(heroHitbox, heroPos, HERO_SYMBOL);
            
        }
        
        return player;
        
    }

}
