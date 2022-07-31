package gameObjects;
import components.Rectangle;

import java.util.Random;

import components.Position;
import gameObjects.Creature;
import global.Assets;


public class Frog extends Creature
{
    //Instance Variables
    private int square = 0;
    private Random speedMultiplyer = new Random();
    
    //Constructors
    

    public Frog(Position position)
    {
        super(new Rectangle(new Position(position.getRoomIndex(), 0, 0), Assets.get_TILESIZE(), Assets.get_TILESIZE()), position, 'F');
    }
    
    //Methods
    
    /**
     * Actions the frog should execute before every frame renders.
     */
    public void tick()
    {
        getFrogInputs();
        super.tick();
        xMove = (float) (xMove * speedMultiplyer.nextFloat() * 1.5);
        yMove = (float) (yMove * speedMultiplyer.nextFloat() * 1.5);
        super.move();
    }
    
    /**
     * Defines creatures movement patterns. Frog goes in a square.
     */
    private void getFrogInputs()
    {
        int squareSize=48;
        if (square <= squareSize*1)
        {
            this.xMove = 1;
            this.yMove = 0;
            square++;
        }
        else if (square <= squareSize*2)
        {
            this.xMove = 0;
            this.yMove = -1;
            square++;
        }
        else if (square <= squareSize*3)
        {
            this.xMove = -1;
            this.yMove = 0;
            square++;
        }
        else if (square <= squareSize*4)
        {
            this.xMove = 0;
            this.yMove = 1;
            square++;
        }
        else if (square >= squareSize*4)
        {
            square = 0;
        }
    }
}
