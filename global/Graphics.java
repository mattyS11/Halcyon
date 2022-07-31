package global;
import java.util.Arrays;
import java.util.Comparator;

import components.Rectangle;
import gameObjects.Creature;
import gameObjects.Entity;
import interfaces.Interactable;
import gameObjects.Room;
import graphics.Display;

import java.awt.Color;
import java.awt.event.KeyEvent;

/**
* A class to visualize the state of a room
* @version  2.0
*/
public class Graphics
{
    
    //Instance Variables
    private static Comparator<Entity> renderSorter = new Comparator<Entity>() 
    {
        @Override
        public int compare(Entity a, Entity b)
        {
            if  (a.getPosition().getPosY() < b.getPosition().getPosY()) 
            {
                return -1;
            }
            else if (b.getPosition().getPosY() < a.getPosition().getPosY())
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    };

    //Constructors
    /**
     * This is a 'static' class
     */
    private Graphics() { }

    //Methods
    /**
     * Method that prints the attributes of a Room object
     * on the screen, so that the player can see what is
     * around them.
     * @param room  The room to print to console.
     */
    public static void print(Room room)
    {
        int t = Assets.get_TILESIZE();
        for(int row = 0; row < room.getFloor().getSize(); row++)
        {
            for (int column = 0; column < room.getFloor().getSize(); column++)
            {
                for(Entity entity : room.getEntity())
                {
                    // Check if an Entity is on a certain tile
                    if (Math.floor(entity.getPosition().getPosX()
                                    /t) == column &&
                        Math.floor(entity.getPosition().getPosY()
                                    /t) == row)
                    {
                        // Print said Entity's symbol on the tile.
                        System.out.print(entity.getSymbol());
                    }
                    else
                    {    // Print an the floor
                        System.out.print(room.getFloor().getTileId(row, column));
                    }
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }
    
    /**
     * Method that draws the attributes of a Room object
     * on the screen, so that the player can see what is
     * around them.
     *
     * @param room      The room to draw to the screen.
     * @param display   The display to draw to.
     */
    public static void draw(Room room, Display display)
    {
        int t = Assets.get_TILESIZE();
        if (display == null)
        {
            return;
        }
        //Dip the paint brush in paint, must do everytime
        java.awt.Graphics paintBrush = 
                                display.getBufferStrategy().getDrawGraphics();
        //Erase you last drawing, this next one will be better
        paintBrush.clearRect(0, 0, display.getWidth(), display.getHeight());
        //DRAW HERE!!!

        paintBrush.setColor(Color.black);
        paintBrush.fillRect(0, 0, display.getWidth(), display.getHeight());

        paintBrush.setColor(Color.gray);
        paintBrush.fillRect(0, 0,
        room.getFloor().getSize()*t,
        room.getFloor().getSize()*t);
        
        Entity[] entitiesInRoom = new Entity[room.getEntityArrayLength()];
        for (int i=0; i < room.getEntityArrayLength(); i++)
        {
            entitiesInRoom[i] = room.getEntity().get(i);
        }
        Arrays.sort(entitiesInRoom, renderSorter);

        for (int row = 0;
                row < room.getFloor().getSize(); row++)
        {
            for (int column = 0;
                    column < room.getFloor().getSize(); column++)
            {
                paintBrush.drawImage(
                room.getFloor().getTile(row, column).getTexture(),
                column * t, row * t,null);

                for(Entity entity : entitiesInRoom)
                {
                    //Draw said Entity's sprite.
                    paintBrush.drawImage(entity.getSprite(),
                                        (int) (entity.getPosition().getPosX()),
                                        (int) (entity.getPosition().getPosY()),
                                        null);
                    if (entity instanceof Creature 
                            && Input.getKeyDown(KeyEvent.VK_H)) 
                    {
                        paintBrush.setColor(Color.red);
                        Rectangle hitbox = ((Creature) entity).getHitBox();
                        paintBrush.drawRect(
                                            (int)hitbox.getPosition().getPosX(),
                                            (int)hitbox.getPosition().getPosY(),
                                            (int)hitbox.getWidth(),
                                            (int)hitbox.getHeight());
                    }
                    if (entity instanceof Interactable 
                            && Input.getKeyDown(KeyEvent.VK_R)) 
                    {
                        paintBrush.setColor(Color.red);
                        int size = (int)((Interactable) entity)
                            .getRange().getCircle().getRadius()*2;
                        int x = (int)((Interactable) entity)
                            .getRange().getCircle().getPosition().getPosX()
                            -size/2;
                        int y = (int)((Interactable) entity)
                            .getRange().getCircle().getPosition().getPosY()
                            -size/2;
                        paintBrush.drawOval(x, y, size, size);
                    }
                }
            }
        }
        //END DRAWING!!!
        //Unveil your new masterpiece
        display.getBufferStrategy().show();
        //Clean up after yourself
        paintBrush.dispose();
    }

}
