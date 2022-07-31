package gameObjects;
import java.io.*;

import global.Assets;
/**
 * A class that holds a 2d array of tiles representing the floor.
 * @version 1.1
 */
public class TileGrid
    {
    //Instance Variables
    private int size = 0;
    private char[][] tileIdArray = null;
    private Tile[][] tileArray = null;

    //Constructors
    /**
    * Constructor from file
    * @param fileName  A file name from which a TileGrid object
    *                         will be formatted and printed.
    */
    public TileGrid(String fileName)
    {
        loadFromFile(fileName);
    }
    /**
    * Default Constructor
    */
    public TileGrid()
    {
        this(3);
    }
    /**
    * Constructor from size
    * @param  size width or height of the square
    */
    public TileGrid(int size)
    {
        this.size = size;
        tileIdArray = new char[size][size];
        for(int row=0; row<size; row++)
        {
            for(int column = 0; column<size; column++)
            {
                tileIdArray[row][column] = '#';
            }
        }
        generateTileArrayFromIdArray( );
    }
    /**
    * Constructor from values
    * @param size  width or height of the square
    * @param tileIdArray a char representation of the floor
    */
    public TileGrid(int size, char[][] tileIdArray)
    {
        this.size = size;
        this.tileIdArray = tileIdArray;
        generateTileArrayFromIdArray();
    }
    
    /**
     * Constructor from existing TileGrid
     * @param tileGridToCopy TileGrid from which it's values will be copied
     */
    public TileGrid(TileGrid tileGridToCopy)
    {
        this.size = tileGridToCopy.getSize();
        tileIdArray = new char[size][size];
        for(int row=0; row<size; row++)
        {
            for(int column = 0; column<size; column++)
            {
                tileIdArray[row][column] = tileGridToCopy.getTileId(row, column);
            }
        }
        generateTileArrayFromIdArray();        
    }

    //Getters Setters
    /**
    * @return width (also height) of the square
    */
    public int getSize()
    {
        return size;
    }
  
    /**
    *@return tileIdArray a char representation of the floor
    */
    public char getTileId(int row, int column)
    {
        return tileIdArray[row][column];
    }
    
    /** 
    * Retrieves a specified tile from the tileArray
    *@param row  The first index of tileArray
    *@param column  The second index of tileArray 
    *@return Tile from specified indices
    */
    public Tile getTile(int row, int column)
    {
        return new Tile(tileArray[row][column]);
    }
    
    /**
     * Changes the tile at the specified indices to the passed tile
     * @param row  The first index of tileArray
     * @param column  The second index of tileArray
     * @param tile  The new tile to be used
     */
    public void changeTile(int row, int column, Tile tile)
    {
        this.tileArray[row][column] = new Tile(tile);
    }

    //Methods
    /**
    * Takes a file and initializes floor and size
    * @param fileName name of the file to load grid from
    */
    private void loadFromFile(String fileName)
    {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        String firstLine;
        String row;
        char[] tileRowArray;

        int rowNum = 0;
        int columnNum = 0;

        try
        {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            //FirstLine is the floor's size as a string.
            firstLine = bufferedReader.readLine();

            //Converts ASCII value of firstLine into its integer value.
            size = Integer.parseInt(firstLine);
            //Initializes tileIdArray to the size it's going to be.
            tileIdArray = new char[size][size];
            //While there are still more lines, save the line to row,
            while ((row = bufferedReader.readLine()) !=  null)
            {
                //turn it into an array of chars.
                tileRowArray = row.toCharArray();

                //For every tile in the row,
                for (columnNum=0; columnNum<size;columnNum++)
                {
                    //Set the corresponding grid position to the tile.
                    tileIdArray[rowNum][columnNum] = tileRowArray[columnNum];
                }
                //Move onto the next row.
                rowNum++;
            }
            generateTileArrayFromIdArray();
        }
        //The rest makes sure if any of this goes wrong we get an error
        //and we don't break the file we opened.
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (bufferedReader != null)
                {
                    bufferedReader.close();
                }

                if (fileReader != null)
                {
                    fileReader.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    /**
    * takes a tile ID array and generates a ID
    * @return       Whether the method ran succesfully.
    */
    private boolean generateTileArrayFromIdArray()
    {
        if (tileIdArray == null || size == 0)
        {
            return false;
        }
        else
        {
            tileArray = new Tile[size][size];
            for(int row = 0; row < tileIdArray.length; row++)
            {
                for (int column = 0;
                column < tileIdArray[row].length;
                column++)
                {
                    tileArray[row][column]
                    = Assets.get_TILE(tileIdArray[row][column]);
                }
            }
            return true;
        }
    }
    
    
    
}
