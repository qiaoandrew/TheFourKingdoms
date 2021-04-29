import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>Scoreboard Class</h1>
 * 
 * Each kingdom possesses a scoreboard, which displays the name of the kingdom, the current amount of money it possesses, 
 * the current number of troops, and current number of establishments.
 * The background also changes between blue and yellow based upon the current alliance.
 * 
 * @author Mr. Cohen, Andrew Qiao, James Li
 * @version April 2021
 */
public class Scoreboard extends Actor
{
    //Declare instance variables
    private String title;
    private int money;
    private int titleFontSize;
    private int fontSize;
    private int currentTroops;
    private int currentEstablishments;
    private int height;
    private int width;
    private int transparency;
    private int paddingAround;
    private int paddingBetween;
    
    //Declare colors
    private Color foreground;
    private Color background;
    
    //Declare GreenfootImage that will display the information
    private GreenfootImage scoreboard;
    private GreenfootImage tempTitle;
    private GreenfootImage tempMoney;
    private GreenfootImage tempCurrentTroops;
    private GreenfootImage tempCurrentEstablishments;
    
    /**
     * Constructor for scoreboard class, sets values of variables, draws the different images, determines its height and width, and calls method to update.
     * 
     * @param title                     Title of the scoreboard
     * @param titleFontSize             Font size of the title
     * @param fontSize                  Font size of the rest of the text
     * @param money                     Amount of money the kingdom currently has
     * @param currentTroops             Number of troops the kingdom currently has
     * @param currentEstablishments     Number of establishments the kingdom currently has
     * @param paddingAround             Amount of padding around the text
     * @param paddingBetween            Amount of padding between the text
     * @param transparency              Transparency of the scoreboard
     * @param foreground                Color of the text
     * @param background                Color of the background
     */
    public Scoreboard(String title, int titleFontSize, int fontSize, int money, int currentTroops, int currentEstablishments, int paddingAround, int paddingBetween, int transparency, Color foreground, Color background)
    {
        //Set values of instances variables
        this.title = title;
        this.titleFontSize = titleFontSize;
        this.fontSize = fontSize;
        this.money = money;
        this.currentTroops = currentTroops;
        this.currentEstablishments = currentEstablishments;
        this.paddingAround = paddingAround;
        this.paddingBetween = paddingBetween;
        this.transparency = transparency;
        this.foreground = foreground;
        this.background = background;
        
        //Create images that display the text
        tempTitle = new GreenfootImage("Kingdom of " + title, titleFontSize, foreground, background);
        tempMoney = new GreenfootImage("Money: $" + zeroAdder(money, 6), fontSize, foreground, background);
        tempCurrentTroops = new GreenfootImage("Troops: " + zeroAdder(currentTroops, 2), fontSize, foreground, background);
        tempCurrentEstablishments = new GreenfootImage("Establishments: " + currentEstablishments, fontSize, foreground, background);
        
        //Calculate height and width of scoreboard
        height = tempTitle.getHeight() + tempMoney.getHeight() + tempCurrentTroops.getHeight() + tempCurrentEstablishments.getHeight() + paddingAround * 2 + paddingBetween * 3;
        width = tempTitle.getWidth() + paddingAround * 2;
        
        //Cal method to update
        update();
    }
    
    /**
     * Called to update the current amount of money, troops and establishments a kingdom has.
     * 
     * @param money                     Current amount of money a kingdom has
     * @param currentTroops             Current number of troops a kingdom has
     * @param currentEstablishments     Current number of establishments a kingdom has
     */
    public void update(int money, int currentTroops, int currentEstablishments)
    {
        //Set values of variables
        this.money = money;
        this.currentTroops = currentTroops;
        this.currentEstablishments = currentEstablishments;
        
        //Create images that will display the text
        tempTitle = new GreenfootImage("Kingdom of " + title, titleFontSize, foreground, background);
        tempMoney = new GreenfootImage("Money: $" + zeroAdder(money, 6), fontSize, foreground, background);
        tempCurrentTroops = new GreenfootImage("Troops: " + zeroAdder(currentTroops, 2), fontSize, foreground, background);
        tempCurrentEstablishments = new GreenfootImage("Establishments: " + currentEstablishments, fontSize, foreground, background);
        
        //Calculate the height and width
        height = tempTitle.getHeight() + tempMoney.getHeight() + tempCurrentTroops.getHeight() + tempCurrentEstablishments.getHeight() + paddingAround * 2 + paddingBetween * 3;
        width = tempTitle.getWidth() + paddingAround * 2;
        
        //Call method to update
        update();
    }
    
    /**
     * Called to update the scoreboard's visuals.
     */
    public void update()
    {
        //Create the image that will be the scoreboard
        scoreboard = new GreenfootImage(width, height);
        
        //Fill the scoreboard with the background color
        scoreboard.setColor(background);
        scoreboard.fill();
        
        //Draws the different images containing text on the scoreboard
        scoreboard.drawImage(tempTitle, paddingAround, paddingAround);
        scoreboard.drawImage(tempMoney, paddingAround, paddingAround + tempTitle.getHeight() + paddingBetween);
        scoreboard.drawImage(tempCurrentTroops, paddingAround, paddingAround + tempTitle.getHeight() + tempMoney.getHeight() + paddingBetween * 2);
        scoreboard.drawImage(tempCurrentEstablishments, paddingAround, paddingAround + tempTitle.getHeight() + tempMoney.getHeight() + tempCurrentTroops.getHeight() + paddingBetween * 3);
        
        //Sets transparency
        scoreboard.setTransparency(transparency);
        
        //Sets the image to the scoreboard
        setImage(scoreboard);
    }
    
    /**
     * Sets the transparency of the scoreboard and updates.
     * 
     * @param transparency          Transparency of the scoreboard
     */
    public void setTransparency(int transparency)
    {
        //Set value of transparency
        this.transparency = transparency;
        
        //Call method to update
        update();
    }
    
    /**
     * Sets the background color of the scoreboard.
     * 
     * @param color             Background color of the scoreboard
     */
    public void setBackgroundColor(Color color)
    {
        //Sets background color
        background = color;
        
        //Update the scoreboard
        update();
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
