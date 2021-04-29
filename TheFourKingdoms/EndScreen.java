import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>EndScreen Class</h1>
 * 
 * The World that is displayed after the simulation finishes, showing which kingdom won and how long it took.
 * Also displays a button for users to restart the simulation.
 * Vibrant and energetic victory music is also played, adding to the audience's enjoyment.
 * 
 * @author Andrew Qiao, Mr. Cohen
 * @version April 2021
 */
public class EndScreen extends World
{
    //Declare instance variables
    private String winnerName;
    private int timeMinutes;
    private int leftOverSeconds;
    
    //Declare button and labels
    private TextButton startButton = new TextButton("NEW GAME!", 50, new Color(253, 249, 127), new Color(7, 20, 33), 5);
    private TextLabel winnerLabel;
    private TextLabel timeLabel;
    
    //Declare soundtrack
    private GreenfootSound wingsOfFreedom = new GreenfootSound("wingsoffreedom.mp3");
    
    /**
     * Creates an ending screen based on parameters of the kingdom who won's name and the time the simulation took in milliseconds.
     * 
     * @param winnerName        String, the kingdom that won's name
     * @param timeMillis        Time the simulation took in milliseconds
     */
    public EndScreen(String winnerName, int timeMillis)
    {    
        // Create a new world with 1280x800 cells with a cell size of 1x1 pixels
        super(1280, 800, 1); 
        
        //Calculate the time in minutes and left over seconds the simulation took
        timeMinutes = timeMillis / 60000;
        leftOverSeconds = (timeMillis - timeMinutes * 60000) / 10000;
        
        //Initalize the name of the winner
        this.winnerName = winnerName;
        
        //Display winner and time information using labels
        winnerLabel = new TextLabel(winnerName.toUpperCase() + " EMERGES VICTORIOUS!", 80, 10, new Color(253, 249, 127), new Color(7, 20, 33));
        addObject(winnerLabel, 640, 250);
        
        timeLabel = new TextLabel("Time: " + zeroAdder(timeMinutes, 2) + ":" + zeroAdder(leftOverSeconds, 2), 60, 5, new Color(253, 249, 127), new Color(7, 20, 33));
        addObject(timeLabel, 640, 400);
        
        //Add the button to the world
        addObject(startButton, 640, 600);
        
        //Start looping the soundtrack
        wingsOfFreedom.setVolume(40);
        wingsOfFreedom.playLoop();
    }
    
    /**
     * Called every act, checks if the user has clicked the button to restart the simulation.
     */
    public void act()
    {
        //Checks if user has clicked the button to restart the simulation, if so, creates a new instance of the WarWorld, also stops the soundtrack
        if (Greenfoot.mousePressed(startButton)){
            ControlScreen controlScreen = new ControlScreen();
            Greenfoot.setWorld(controlScreen);
            wingsOfFreedom.stop();
        } 
    }
    
    /**
     * Method that aids in the appearance of the scoreboard by generating
     * Strings that fill in zeros before the score. For example:
     * 
     * 27 ===> to 5 digits ===> 00027
     * 
     * @param   value   integer value to use for score output
     * @param   digits   number of zeros desired in the return String
     * @return  String  built score, ready for display
     */
    public static String zeroAdder (int value, int digits)
    {
        // Figure out how many digits the number is
        int numDigits = digitCounter(value);

        // If not extra digits are needed
        if (numDigits >= digits)
            return Integer.toString(value);

        else // Build the number with zeroes for extra place values:
        {
            String zeroes = "";
            for (int i = 0; i < (digits - numDigits); i++)
            {
                zeroes += "0";
            }
            return (zeroes + value);
        }
    }
    
    /**
     * Useful private method that counts the digit in any integer.
     * 
     * @param number    The number whose digits you want to count
     * @return  int     The number of digits in the given number
     */
    private static int digitCounter (int number)
    {
        if (number < 10) {
            return 1;
        }
        int count = 0;
        while (number > 0) {
            number /= 10;
            count++;
        }
        return count;
    }
}
