package gameObjects;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import components.Position;
import components.Range;
import global.Input;
import interfaces.Interaction;
import interfaces.Interactable;

/**
* An Entity that can be interacted with by the user
* @version  1.0
*/
public class InteractableStationary extends Stationary implements Interactable
{
    //Instance Variables
    
    private Range range;
    private Interaction<?> interactionObject;
    private float rangeXOffset;
    private float rangeYOffset;
    
    //Constructors
    
    public InteractableStationary(Range range, Interaction<InteractableStationary> interactionObject, char symbol, Position position) 
    {
        super(position, symbol);
        this.range = range;
        this.interactionObject = interactionObject;
    }
    
    //Methods
    
    @Override
    public void tick()
    {
        /**
         * Known bug: This runs every tick the player holds down Enter for.
         * We only want it to run when the player pushes enter down.
         * Not when they hold it down.
         */
        if(Input.getKeyDown(KeyEvent.VK_ENTER)) 
        {
            boolean playerInRange = false;
            ArrayList<Entity> entitiesInRange = range.entitiesInRange(this.getPosition().getRoomIndex()); 
            for(Entity entity:entitiesInRange) 
            {
                 if (entity instanceof Player) 
                  {
                      playerInRange = true;
                      break;
                  }
            }
               
            if(playerInRange)
            {
                interactionObject.apply(this);
            }  
        }
    }
    
    public void setRange(Range range)
    {
        this.range = range;
    }
    
    public void setRangeYOffset(float offset)
    {
        this.rangeYOffset = offset;
    }
    
    public void setRangeXOffset(float offset)
    {
        this.rangeXOffset = offset;
    }

    public Range getRange() 
    {
        return new Range(range);
    }

    public void setIneractionObject(Interaction<?> interaction)
    {
        this.interactionObject = interaction;
    }
    
 }

