package gameObjects;

import components.Position;

public abstract class Stationary extends Entity
{
    
    public Stationary(Position position, char symbol) 
    {
        super(position, symbol);        
    }

    public abstract void tick();

}
