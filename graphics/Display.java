package graphics;
import java.awt.Canvas;
import javax.swing.*;

import global.Input;

import java.awt.Dimension;
import java.awt.image.BufferStrategy;

/**
* A class representing the window in the gui game.
* @version 2.0
*/
public class Display
{
    //Instance Variables
    private BufferStrategy bufferStrategy;
    
    private String title;
    private int width;
    private int height;

    //Constructors
    /**
    * Constructor to create a graphical window for the game
    * @param title  The text on the top of the window
    * @param width  The windows width in pixels
    * @param height The windows height in pixels
    */
    public Display(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        createWindow();
    }

    //Getters Setters
    /**
    * @return   height  The windows height in pixels.
    */
    public int getHeight()
    {
        return height;
    }
    /**
    * @return   width   The windows width in pixels
    */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * 
     * @return  bufferStrategy   Defines the number of frames we will pre-render
     */
    public BufferStrategy getBufferStrategy()
    {
        return bufferStrategy;
    }

    //Methods
    /**
    * Creates a window with a canvas object we can draw on.
    */
    private void createWindow()
    {
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Canvas canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        //Adds a canvas to draw to on the frame.
        frame.add(canvas);
        frame.pack();
        //Add key listener
        frame.addKeyListener(Input.getKeyListener());
        //Defines the number of frames we will pre-render.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
    }
}
