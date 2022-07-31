package global;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import gameObjects.Tile;

/**
* A static class for accessing all the raw assets for our game.
* @version 1.0
*/
public final class Assets
{
    //Variables
    final private static HashMap<Character, Tile> TILES = new HashMap<>();
    final private static HashMap<Character, BufferedImage> SPRITES = new HashMap<>();
    final private static int TILESIZE = 16;

    //Constructors
    /**
    * Default Constructor. Cannot make Assets objects
    */
    private Assets(){}

    //Methods
    static
    {
        //SpriteSheets
        BufferedImage spriteSheet = Utility.loadImage("resources/spriteSheet.png");
        BufferedImage charactorSpriteSheet =
        Utility.loadImage("resources/charactorSpriteSheet.png");

        //Cropping out images and creating objects for use in game.
        Tile black = new Tile(Utility.loadImage("resources/blackTile.png"));
        Tile greyChecker = new Tile(spriteSheet.getSubimage(272, 289,
                                                        TILESIZE, TILESIZE));
        Tile downWall = new Tile(spriteSheet.getSubimage(187, 17,
                                                        TILESIZE, TILESIZE), true);
        Tile downRightCorner = new Tile(spriteSheet.getSubimage(204, 17,
                                                        TILESIZE, TILESIZE));
        Tile lrWall = new Tile(spriteSheet.getSubimage(170, 0,
                                                        TILESIZE, TILESIZE), true);
        Tile roughGreyLeft = new Tile(spriteSheet.getSubimage(136, 34,
                                                        TILESIZE, TILESIZE));
        Tile roughGreyRight = new Tile(spriteSheet.getSubimage(170, 34,
                                                        TILESIZE, TILESIZE));
        Tile roughGrey = new Tile(spriteSheet.getSubimage(153, 34,
                                                        TILESIZE, TILESIZE));
        Tile downLeftCorner = new Tile(spriteSheet.getSubimage(221, 17,
                                                        TILESIZE, TILESIZE));
        Tile greyBottomWallDownLeft = new Tile(spriteSheet.getSubimage(170, 68,
                                                        TILESIZE, TILESIZE));
        Tile greyBottomWallDownRight = new Tile(spriteSheet.getSubimage(136, 68,
                                                        TILESIZE, TILESIZE));
        Tile greyBottomWall = new Tile(spriteSheet.getSubimage(153, 68,
                                                        TILESIZE, TILESIZE), true);
        Tile upRightCorner = new Tile(spriteSheet.getSubimage(204, 0,
                                                        TILESIZE, TILESIZE));
        Tile upLeftCorner = new Tile(spriteSheet.getSubimage(221, 0,
                                                        TILESIZE, TILESIZE));
        BufferedImage hero = charactorSpriteSheet.getSubimage(0, 120,
                                                        TILESIZE, TILESIZE);
        BufferedImage leverOn = Utility.loadImage("resources/leverOn.png");
        BufferedImage leverOff = Utility.loadImage("resources/leverOff.png");
        BufferedImage sword = Utility.loadImage("resources/Item__00.png");
        BufferedImage scrollRed = Utility.loadImage("resources/Item__37.png");
        BufferedImage frog = Utility.loadImage("resources/FrogSprite.png");
        
        //Adding images and objects to HashMaps for easy access.
        TILES.put('-', greyChecker);
        TILES.put('.', black);
        TILES.put('|', downWall);
        TILES.put('L', downRightCorner);
        TILES.put('_', lrWall);
        TILES.put('a', roughGreyLeft);
        TILES.put('b', roughGreyRight);
        TILES.put('c', roughGrey);
        TILES.put('z', downLeftCorner);
        TILES.put('p', greyBottomWallDownLeft);
        TILES.put('q', greyBottomWallDownRight);
        TILES.put('u', greyBottomWall);
        TILES.put('w', upRightCorner);
        TILES.put('t', upLeftCorner);

        SPRITES.put('*', hero);
        SPRITES.put('o', leverOn);
        SPRITES.put('f', leverOff);
        SPRITES.put('s', sword);
        SPRITES.put('r', scrollRed);
        SPRITES.put('F',frog);
    }

    //Getters

    /**
     *@return Returns a deep copy of the current tile
     */
    public static Tile get_TILE(char key)
    {
       Tile aTile = new Tile(TILES.get(key));
       return aTile;
    }

    /**
     *@return Returns the buffered image that corresponds
     *to the provided key
     */
    public static BufferedImage get_SPRITE(char key)
    {
        return SPRITES.get(key);
    }


    /**
    *@return Returns TileSize
    */
    public static int get_TILESIZE()
    {
        return TILESIZE;
    }
}
