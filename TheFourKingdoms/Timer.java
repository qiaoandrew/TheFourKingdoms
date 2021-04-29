import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>Timer Class</h1>
 * 
 * A simple timer that keeps track of the time before a Troop is finished training.
 * Is also semi-transparent so that users can see more of the garrison's image.
 * 
 * @author Andrew Qiao, Brendan Chan
 * @version April 2021
 */
public class Timer extends Actor
{
    //Declare GreenfootImages for the text and the complete timer
    private GreenfootImage tempTimer;
    private GreenfootImage timer;
    
    //Declare the time in milliseconds
    private int timeInMillis;
    
    //Declare the SimpleTimer, used to keep track of time
    private SimpleTimer simpleTimer;
    
    /**
     * Constructor for Timer class, sets starting values of instance variable, initializes the SimpleTimer and updates itself to display a visual.
     * 
     * @param timeInMillis              Time in milliseconds left on the timer
     */
    public Timer(int timeInMillis) 
    {
        //Set starting value of the time in milliseconds
        this.timeInMillis = timeInMillis;
        
        //Initialize the SimpleTimer
        simpleTimer = new SimpleTimer();
        
        //Mark the current time to keep track of it
        simpleTimer.mark();
        
        //Update the timer
        update();
    }
    
    /**
     * Called every act, calls the method to update the visuals of the timer.
     */
    public void act()
    {
        update();
    }
    
    /**
     * Called to update the visuals of the Timer, redraws the different elements and displays them together.
     */
    public void update()
    {
        //Calculates the time in seconds that should be displayed
        int timeInSeconds = (timeInMillis - simpleTimer.millisElapsed()) / 1000 + 1;
        
        //Creates the image of the text itself
        tempTimer = new GreenfootImage(String.valueOf(timeInSeconds), 20, new Color(7, 20, 33), new Color(253, 249, 127));
        
        //Creates the image of the entire timer
        timer = new GreenfootImage(tempTimer.getHeight() + 10, tempTimer.getHeight() + 10);
        timer.setColor(new Color(253, 249, 127));
        timer.fillOval(0, 0, timer.getWidth(), timer.getHeight()); //Fill an oval with the background color
        timer.drawImage(tempTimer, 11, 5); //Draw the image of the text on top of the timer
        timer.setColor(new Color(7, 20, 33)); 
        timer.drawOval(0, 0, timer.getWidth(), timer.getHeight());//Draw the outline of the circle
        
        //Sets the transparency to 200 to make the garrison's center look semi-visible
        timer.setTransparency(200);
        
        //Set the current image to that of the timer
        setImage(timer);
    }
}
