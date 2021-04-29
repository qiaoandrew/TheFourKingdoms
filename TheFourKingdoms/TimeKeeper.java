import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>TimeKeeper Class</h1>
 * 
 * A simple time keeper that keeps track of the time based on input in milliseconds.
 * 
 * @author Andrew Qiao, James Li
 * @verison April 2021
 */
public class TimeKeeper extends Actor
{
    //Declare instance variabels
    private String time;
    private int fontSize;
    private int padding;
    private int transparency;
    
    //Declare colors
    private Color foreground;
    private Color background;
    
    //Declare image for the time keeper
    private GreenfootImage timeKeeper;
    
    /**
     * Constructor for TimeKeeper class, calculates time information, sets instance variables and calls the method to update the TimeKeeper's visuals.
     * 
     * @param millis            Time in millis to be displayed
     * @param fontSize          Size of the font for the text
     * @param padding           Padding around the text
     * @param foreground        Color of the text
     * @param background        Color of the background
     * @param transparency      Transparency of the TimeKeeper
     */
    public TimeKeeper(int millis, int fontSize, int padding, Color foreground, Color background, int transparency)
    {
        //Determine the minutes and left over seconds that have passed
        int minutes = millis / 60000;
        int secondsLeftOver = (millis - (minutes * 60000)) / 1000;
        
        //Calculate the string that will be displayed on the TimeKeeper
        time = zeroAdder(minutes, 2) + ":" + zeroAdder(secondsLeftOver, 2);
        
        //Set values of instance variables
        this.fontSize = fontSize;
        this.padding = padding;
        this.foreground = foreground;
        this.background = background;
        this.transparency = transparency;
        
        //Call method to update
        update();
    }
    
    /**
     * Called to update the amount of milliseconds that will be displayed.
     * 
     * @param millis            The number of milliseconds to be displayed
     */
    public void update(int millis)
    {
        //Calculate the amount of minutes and seconds left over
        int minutes = millis / 60000;
        int secondsLeftOver = (millis - (minutes * 60000)) / 1000;
        
        //Calculate the String that will display the time
        time = zeroAdder(minutes, 2) + ":" + zeroAdder(secondsLeftOver, 2);
        
        //Call method to update
        update();
    }
    
    /**
     * Called to update the visuals of the TimeKeeper.
     */
    public void update()
    {
        //The temporary text for the text displayed on the TimeKeeper
        GreenfootImage tempText = new GreenfootImage(time, fontSize, foreground, background);
        
        //Create and fill image of the TimeKeeper
        timeKeeper = new GreenfootImage(tempText.getWidth() + padding * 2, tempText.getHeight() + padding * 2);
        timeKeeper.setColor(background);
        timeKeeper.fill();
        timeKeeper.drawImage(tempText, padding, padding);
        timeKeeper.setColor(foreground);
        timeKeeper.drawRect(0, 0, timeKeeper.getWidth() - 1, timeKeeper.getHeight() - 1);
        
        //Set the TimeKeeper's transparency
        timeKeeper.setTransparency(transparency);
        
        //Set the image
        setImage(timeKeeper);
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
