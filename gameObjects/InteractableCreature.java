package gameObjects;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import components.Position;
import components.Range;
import components.Rectangle;
import global.Input;
import interfaces.Interactable;
import interfaces.Interaction;

/**
* A Creature that can be interacted with by the user
* @version	1.0
*/
public abstract class InteractableCreature extends Creature implements Interactable
{
    //Instance Variables

    private Range range;
    private float rangeXOffset;
    private float rangeYOffset;
    private Interaction<InteractableCreature> interactionObject;

    //Constructors

    public InteractableCreature(Range range, Interaction<InteractableCreature> interactionObject, Rectangle hitBox, Position position, char symbol)
    {
        super(hitBox, position, symbol);
        this.range = range;
        this.interactionObject = interactionObject;
    }

    public void setInteractionObject(Interaction<InteractableCreature> interactionObject)
    {
        this.interactionObject = interactionObject;
    }

    public void setRange(Range range)
    {
        this.range = range;
    }

    public void setRangeXOffset(float offset)
    {
        this.rangeXOffset = offset;
    }

    public void setRangeYOffset(float offset)
    {
        this.rangeYOffset = offset;
    }

    public Range getRange()
    {
        return new Range(this.range);
    }
}
