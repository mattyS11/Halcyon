package administrative;
import gameObjects.Player;
import gameObjects.TileGrid;
import global.Assets;
import gameObjects.Room;
import components.Position;
import components.Rectangle;


/**
* @version  1.0
*/
public class TestCases
{
    /**
    * This is a 'static' class
    */
    private TestCases() {}
    
    /**
    * Runs all the test cases
    * @param args The lines of code.
    */
    public static void main(String[] args)
    {
        // Test if file with roomsize = 0 is correctly loaded
        TileGrid test1 = new TileGrid("resources/ROOM_SIZE_0.txt");
        if (test1.getSize() == 0)
            System.out.println("PASS1: correct size loaded");
        else
            System.out.println("FAIL1: wrong size");

        // Test if player's bounding box has width & height 16 (it should)
        Rectangle boundingBox = new Rectangle(new Position(0,0,0), Assets.get_TILESIZE(),
                                                Assets.get_TILESIZE());

        Player testPlayer = Player.getPlayer();
        testPlayer.setHitBox(boundingBox);
        if (testPlayer.getHitBox().width == 16 &&
            testPlayer.getHitBox().height == 16)
            System.out.println("PASS2: bounding box" +
                                " dimensions were set correctly");
        else
            System.out.println("FAIL2: something in " +
                                "setting dimensions went wrong");
        
        //Test if getPlayer returns the correct room.
        Game game = Game.getGame();
        Player player = Player.getPlayer();
        TileGrid testTileGrid = new TileGrid(10);
        Room testRoom = new Room(testTileGrid);
        int testRoomIndex = game.addRoom(testRoom);
        Position p = new Position(testRoomIndex, 0, 0);
        player.setPosition(p);
        if (game.getPlayerRoomIndex() == testRoomIndex)
        {
            System.out.println("PASS3: Player room is detected");
        }
        else
        {
            System.out.println("FAIL3: Player room is not detected correctly");
        }
    }

}
