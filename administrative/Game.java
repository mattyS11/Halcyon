package administrative;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

import components.Circle;
import components.Position;
import components.Range;
import components.Exit;
import gameObjects.Entity;
import gameObjects.Frog;
import gameObjects.InteractableStationary;
import gameObjects.Player;
import gameObjects.Room;
import gameObjects.TileGrid;
import global.Assets;
import global.Input;
import graphics.Display;
import global.Graphics;

/**
* The game class handles and initialization and management
* @version  2.0
*/
public class Game
{
    //Instance Variables
    private static ArrayList<Room> world = new ArrayList<Room>();
    public static Room activeRoom;
    private static Game game = null;
    
    //Constructors
    
    /**
     * Singleton Pattern
     */
    private Game(){}
    /**
     * Singleton Pattern, used to create a game object.
     * @return  game    The game object, use this to create a game.
     */
    public static Game getGame()
    {
        if (game == null)
        {
            game = new Game();
        }
        return game;
    }
    
    //Methods
    
    /**
    * Method which initializes and structures the elements
    * that make up our game before starting the game.
    */
    public void start()
    {
        //Magic Numbers
        
        
        //Tile Size
        final int t = Assets.get_TILESIZE();
        
        //Entities
        //-frog
        final int FROG_ROOM = 1;
        final int FROG_POS_X = 5 *t;
        final int FROG_POS_Y = 6*t;
        //-lever
        final int LEVER_POS_X = 6 * t;
        final int LEVER_POS_Y = 6 * t;
        final float LEVER_RANGE = 1 * t;
        final char LEVER_SYMBOL = 'o';
        //-sword
        final int SWORD_POS_X = 8 * t;
        final int SWORD_POS_Y = 8 * t;
        final float SWORD_RANGE = 1 * t;
        final char SWORD_SYMBOL = 's';
        //-scroll
        final int SCROLL_POS_X = 4*t;
        final int SCROLL_POS_Y = 10*t;
        final float SCROLL_RANGE = 2*t;
        final char SCROLL_SYMBOL = 'r';
        
        //Rooms
        final String TILE_GRID_FILE_NAME = "resources/tileGrid1.txt";
        final String TILE_GRID_FILE_NAME2 = "resources/tileGrid2.txt";
        
        //Displays
        final String DISPLAY_TITLE = "CPSC 233 Final Project";
        final int DISPLAY_WIDTH = 300;
        final int DISPLAY_HEIGHT = 300;
        
///////////////////////////////////////////////////////////////////////////////
        
        //Entities
        //frog
        Position frogPos = new Position(FROG_ROOM, FROG_POS_X, FROG_POS_Y);
        Entity frog = new Frog(frogPos);
        //hero
        Entity hero = Player.getPlayer();
        //lever
        Position leverPos = new Position(getPlayerRoomIndex(), LEVER_POS_X, 
                                    LEVER_POS_Y);
        InteractableStationary lever = new InteractableStationary(
            new Range(leverPos, new Circle(leverPos, LEVER_RANGE)),
            (InteractableStationary i) ->
            {
                getRoom(getPlayerRoomIndex()).getFloor().changeTile(
                                            4, 6, Assets.get_TILE('.'));
                getRoom(getPlayerRoomIndex()).getFloor().changeTile(
                                            5, 6, Assets.get_TILE('.'));
                getRoom(getPlayerRoomIndex()).getFloor().changeTile(
                                            4, 7, Assets.get_TILE('.'));
                getRoom(getPlayerRoomIndex()).getFloor().changeTile(
                                            5, 7, Assets.get_TILE('.'));
                
                i.setSprite(Assets.get_SPRITE('f'));
            },
            LEVER_SYMBOL,
            leverPos);
        //sword
        Position swordPos = new Position(getPlayerRoomIndex(), SWORD_POS_X,
                                    SWORD_POS_Y);
        InteractableStationary sword = new InteractableStationary(
            new Range(swordPos, new Circle(swordPos, SWORD_RANGE)),
            (InteractableStationary i) -> { System.out.println("That tickles! OwO"); },
            SWORD_SYMBOL,
            swordPos);
        //scroll
        Position scrollPos = new Position(getPlayerRoomIndex(), SCROLL_POS_X,
                                    SCROLL_POS_Y);
        InteractableStationary scroll = new InteractableStationary(
            new Range(scrollPos, new Circle(scrollPos, SCROLL_RANGE)),
            (InteractableStationary i) -> 
            {
                System.out.println("This range is twice as large!"); 
            },
            SCROLL_SYMBOL,
            scrollPos);
        
        Position scrollPos2 = new Position(1, SCROLL_POS_X,
                SCROLL_POS_Y);
        
        InteractableStationary scroll2 = new InteractableStationary(
                new Range(scrollPos, new Circle(scrollPos2, SCROLL_RANGE)),
                (InteractableStationary i) -> 
                {
                    System.out.println("This range is twice as large!"); 
                },
                SCROLL_SYMBOL,
                scrollPos);
        
        //Rooms
        ArrayList<Entity> firstRoomEntityList = new ArrayList<Entity>();
        firstRoomEntityList.addAll(Arrays.asList(lever, sword, scroll));
        
        ArrayList<Entity> secondRoomEntityList = new ArrayList<Entity>();
        secondRoomEntityList.addAll(Arrays.asList(scroll2, frog));
        
        switch(getCheckPoint("resources/checkPoint.txt"))
        {
        
        case 0:
        	firstRoomEntityList.add(hero);
        	break;
        	
        case 1:
        	secondRoomEntityList.add(hero);
        	break;
        
        }
        
        int firstRoomNum = addRoom(new Room(new TileGrid(TILE_GRID_FILE_NAME)));
        int secondRoomNum = addRoom(new Room(new TileGrid(TILE_GRID_FILE_NAME2)));
        
        world.get(firstRoomNum).addEntities(firstRoomEntityList);
        world.get(secondRoomNum).addEntities(secondRoomEntityList);
        
        world.get(firstRoomNum).addExit(new Exit(new Position(1, 6*16, 11*16), new Position(0, 6*16, 4*16), 1));
        world.get(firstRoomNum).addExit(new Exit(new Position(1, 7*16, 11*16), new Position(0, 7*16, 4*16), 1));
        world.get(secondRoomNum).addExit(new Exit(new Position(0, 6*16, 6*16), new Position(1, 6*16, 12*16), 0));
        world.get(secondRoomNum).addExit(new Exit(new Position(0, 7*16, 6*16), new Position(1, 7*16, 12*16), 0));
        
        //Display
        Display mainDisplay = new Display(DISPLAY_TITLE,
                                            DISPLAY_WIDTH, 
                                            DISPLAY_HEIGHT);
        //Start ticking the player room or the first room if it exists.
        activeRoom = world.get(getCheckPoint("resources/checkPoint.txt"));
        Room roomToTick = world.get(getCheckPoint("resources/checkPoint.txt"));
        if (roomToTick == null && !world.isEmpty())
        {
        	activeRoom = world.get(0);
            tickOnSchedule(mainDisplay);
        }
        else if (roomToTick != null)
        {
        	activeRoom = roomToTick;
            tickOnSchedule(mainDisplay);
        }
    }
    
    /**
     * Method that runs the game
     * by calling the tickRoom() method on a schedule
     * @param roomToTick        Room to update.
     * @param displayToRenderTo The display to draw the game onto.
     */
    private static void tickOnSchedule(Display displayToRenderTo)
    {
        final long rate = (long)(1000/60);
        Timer tickRate = new Timer();
        tickRate.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                tickRoom(displayToRenderTo);
            }
        }, 0, rate);
    }
    
    /**
     * Method that updates the Entities on the activeRoom object.
     * @param displayToRenderTo     The display to render the room to.
     */
    private static void tickRoom(Display displayToRenderTo)
    {
    	
    	Boolean roomSwitch = false;
    	Entity player = null;
    	Exit setExit = null;
    	
        //Update all entities in a room.
        for (Entity entity : activeRoom.getEntity())
        {
        	if(entity instanceof Player) 
        	{
        		player = entity;
        		
        		for(Exit exit : activeRoom.getExits()) 
        		{
        			if(exit.getExitPosition().equalPos(player.getPosition())) 
        			{
        				setExit = exit;
        				player.setPosition(setExit.getTelePosition());
        				roomSwitch = true;
        			}
        		}
        	}
        	
        	entity.tick();
        }
        
        if(roomSwitch)
        {
        	
			activeRoom.removeEntity(player);
			activeRoom = world.get(setExit.getLinkRoomIndex());
			activeRoom.addEntity(player);
			setCheckPoint("resources/checkPoint.txt");
			
        }
        
        //render the room.
        render(activeRoom, displayToRenderTo);
    }
    
    /**
     * Draws to the screen and optionally the console.
     * @param   roomToDisplay       The room to draw on screen.
     * @param   displayToRenderTo   The display to render the room to.
     */
    private static void render(Room roomToDisplay, Display displayToRenderTo)
    {
        if (Input.getKeyDown(KeyEvent.VK_T))
        {
            Graphics.print(roomToDisplay);
        }
        Graphics.draw(roomToDisplay, displayToRenderTo);
    }
    
    //Getters Setters
    
    /**
     * Getter method for an Room from world.
     * @param   index   the index for the room you want.
     * @return  a Room from world if it exists, else null
     */
    public static Room getRoom(int index)
    {
        if (index <= world.size() - 1 && index <= 0)
        {
            return world.get(index);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * returns the index of the room the player resides in.
     * @return  the room the player is currently in, -1 if no players in world
     */
    public static int getPlayerRoomIndex()
    {
        if (world.isEmpty())
        {
            return -1;
        }
        
        for (Room room : world)
        {
            for (Entity entity : room.getEntity())
            {
                if (entity instanceof Player)
                {
                    return world.indexOf(room);
                }
            }
        }
        return -1;
    }
    
    /**
     * Getter method for the last room the player was in before exit
     * @param file A text file containing the roomIndex of the last room the player was in.
     * @return An int representing the roomIndex of the last room the player was in.
     */
    public static int getCheckPoint(String file)
    {
		try{
			
				BufferedReader input = new BufferedReader(new FileReader(file));
				String data = input.readLine();
				int checkPoint = Integer.parseInt(data);
				input.close();
				return checkPoint;
				
			}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
		
	}
	
	/**
	 * Setter method for the room the player is in
	 * @param file A text file which will contain the index of a Room the player is in.
	 */
	public static void setCheckPoint(String file)
	{
		try{
			Scanner fileScanner = new Scanner(file);
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			while(fileScanner.hasNextLine())
			{
				String next = fileScanner.nextLine();
				if(next.equals("\n"))
				{
					out.newLine();
				}
				else
				{
					out.write(Integer.toString(getPlayerRoomIndex()));
				}
				out.newLine();
				
			}
			out.close();
			fileScanner.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
		
    /**
     * adds a room to the world arrayList.
     * @param roomToAdd The room being added to the arrayList.
     */
    public int addRoom(Room roomToAdd)
    {
        world.add(roomToAdd);
        return world.indexOf(roomToAdd);
    }
    
}
