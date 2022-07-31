package components;

/**
* A class that holds objects and contains the information for exiting and entering a room
* @version	1.0
*/
public class Exit{
	
	private Position telePos;
	private Position exitPos;
	private int linkRoomIndex;
	
	/**
	 * The only constructor, used to specify a location and connected room of an exit
	 * @param telePos  The position that the player will be moved to upon touching the exit
	 * @param exitPos  The position the exit is located on
	 * @param linkRoomIndex  The room index the exit connects to
	 */
	public Exit(Position telePos, Position exitPos, int linkRoomIndex)
	{
		this.telePos = telePos;
		this.exitPos = exitPos;
		this.linkRoomIndex = linkRoomIndex;
	}
	
	/**
	 * Used to get the exit position of the Exit
	 * @return Position
	 */
	public Position getExitPosition()
	{
		return exitPos;
	}
	
	/**
	 * Used to get the teleport position of the exit
	 * @return  Position
	 */
	public Position getTelePosition()
	{
		return telePos;
	}
	
	/**
	 * Used to get the index the exit sends the player to
	 * @return int linkRoomIndex
	 */
	public int getLinkRoomIndex()
	{
		return linkRoomIndex;
	}

}
