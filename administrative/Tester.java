package administrative;

/**
* @version  1.1
*/
public class Tester
{
    /**
     * This is a 'static' class
     */
    private Tester() {}

    /**
    * Starts the game
    * @param args The lines of code.
    */
    public static void main(String[] args)
    {
        Game theBestestGameInTheWorldLetsPlay = Game.getGame();
        theBestestGameInTheWorldLetsPlay.start();
    }
}
