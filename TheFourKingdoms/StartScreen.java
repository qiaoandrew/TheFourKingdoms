import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>StartScreen Class</h1>
 * 
 * The World that is shown at the very start of the simulation.
 * A graphic is displayed to greet the players, and also includes a button that can be clicked to enter the next StoryScreen.
 * Includes captivating and intense background music, allowing players to be drawn in.
 * 
 * @author Andrew Qiao
 * @version April 2021
 */
public class StartScreen extends World
{
    //Declare TextButton
    private TextButton startButton;
    
    //Declare soundtrack
    private GreenfootSound birdInACage = new GreenfootSound("birdinacage.mp3");
    
    /**
     * Constructor for Startscreen, creates the world, and the button.
     */
    public StartScreen()
    {    
        // Create a new world with 1280x800 cells with a cell size of 1x1 pixels
        super(1280, 800, 1); 
        
        //Initalize the button and add it to the world
        startButton = new TextButton("BATTLE!", 50, new Color(7, 20, 33), new Color(253, 249, 127), 10);
        addObject(startButton, getWidth() / 2, getHeight() - 250);
    }
    
    /**
     * Method that is called every act, continuously checks if the startButton has been pressed.
     */
    public void act() 
    {
        //If the startButton is pressed, set the World to storyScreen, and stop the soundtrack
        if (Greenfoot.mousePressed(startButton)){
            StoryScreen storyScreen = new StoryScreen();
            Greenfoot.setWorld(storyScreen);
            birdInACage.stop();
        } 
    }
    
    /**
     * This method is called when the program starts, looping the soundtrack.
     */
    public void started() {
        birdInACage.setVolume(40);
        birdInACage.playLoop();
    }
}
