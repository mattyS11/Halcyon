package global;
import java.util.HashSet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
* A static class for creating and getting listeners and handling keyboard input.
* @version 1.0
*/
public class Input
{
    //Static Variables

	//Creates a KeyListener to detect key presses and releases
	private static KeyListener keyListener = new KeyListener()
    {
        @Override
        public void keyTyped(KeyEvent e)
        {
            return;
        }
		@Override
		public void keyPressed(KeyEvent e)
        {
            if (getKeyDown(KeyEvent.VK_I)) 
            {
                displayInfo(e, "Key pressed: ");
            }
            keysPressed.add(e.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent e)
        {
            if (getKeyDown(KeyEvent.VK_I)) 
            {
                displayInfo(e, "Key released: ");
            }
            keysPressed.remove(e.getKeyCode());
		}
	 };
	 //setFocusable(true);
	//Creates a HashSet in which keyCodes will be added and removed for easy access
	public static HashSet<Integer> keysPressed = new HashSet<Integer>();

     //"Constructors"

     //Default constructor
     private Input()
     {
     }

     //Methods

	/**
	 * Getter for retrieving the keyListener
	 * @return keyListener Input's personal object that handles keyboard input.
	 */
	public static KeyListener getKeyListener()
	{
		return keyListener;
	}

	/**
	 * Method which checks set of characters contained in keysPressed
	 * returns true if the passed character is found.
	 * @param key KeyCode being checked for state
	 */
	public static boolean getKeyDown(int key)
    {
		return keysPressed.contains(key);
	}

    //THIS IS FOR DEBUG PURPOSES of input.
	private static void displayInfo(KeyEvent e, String keyStatus)
    {
        //You should only rely on the key char if the event
        //is a key typed event.
		if (e.getKeyChar() == KeyEvent.CHAR_UNDEFINED) 
		{
			System.out.println(keyStatus + e.getKeyCode());
		}
		else 
		{
	        System.out.println(keyStatus + e.getKeyChar());
		}
    }
}
