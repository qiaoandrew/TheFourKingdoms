import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>StoryScreen Class</h1>
 * 
 * The World that explains to the user the backstory of the conflict through images of text, finishing by showing a CONTINUE! button.
 * Also has an intense soundtrack playing, allowing players to mentally submerge themselves into the world of the simulation.
 * 
 * @author Andrew Qiao
 * @version April 2021
 */
public class StoryScreen extends World
{
    //Declare GreenfootImages for the story parts
    private GreenfootImage part1 = new GreenfootImage("part1.png");
    private GreenfootImage part2 = new GreenfootImage("part2.png");
    private GreenfootImage part3 = new GreenfootImage("part3.png");
    private GreenfootImage part4 = new GreenfootImage("part4.png");
    private GreenfootImage part5 = new GreenfootImage("part5.png");
    private GreenfootImage part6 = new GreenfootImage("part6.png");
    
    //Declare the text button for continuing
    private TextButton continueButton;
    
    //Declare actCount, to count acts
    private int actCount;
    
    //Declare soundtrack
    private GreenfootSound attackOnTitan = new GreenfootSound("attackontitan.mp3");
    
    /**
     * Constructor for StoryScreen, initalizes dimensions and actCount, also starts playing the soundtrack.
     */
    public StoryScreen()
    {    
        // Create a new world with 1280x800 cells with a cell size of 1x1 pixels.
        super(1280, 800, 1);
        
        //Set actCount to 0
        actCount = 0;
        
        //Start the soundtrack
        attackOnTitan.setVolume(40);
        attackOnTitan.playLoop();
    }
    
    /**
     * Called every act, determines if and when to draw the image parts, as well as when to display the button.
     */
    public void act()
    {
        //Determine the current act, and if an image should be drawn
        if (actCount == 0) {
            getBackground().drawImage(part1, 0, 50);
        } else if (actCount == 50) {
            getBackground().drawImage(part2, 0, 130);
        } else if (actCount == 125) {
            getBackground().drawImage(part3, 0, 210);
        } else if (actCount == 225) {
            getBackground().drawImage(part4, 0, 290);
        } else if (actCount == 325) {
            getBackground().drawImage(part5, 0, 370);
        } else if (actCount == 425) {
            getBackground().drawImage(part6, 0, 450);
        } else if (actCount == 525) {
            //Create the button and add it to the World        
            continueButton = new TextButton("CONTINUE!", 50, new Color(7, 20, 33), new Color(253, 249, 127), 10);
            addObject(continueButton, getWidth() / 2, 650);
        }
        
        //Increment the actCount
        actCount++;
        
        //Check if the button is pressed, if so, go to the next World, and stop the soundtrack
        if (Greenfoot.mousePressed(continueButton)){
            ControlScreen controlScreen = new ControlScreen();
            Greenfoot.setWorld(controlScreen);
            attackOnTitan.stop();
        } 
    }
}
