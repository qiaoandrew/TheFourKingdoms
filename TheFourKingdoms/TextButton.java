import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>TextButton Class</h1>
 * 
 * A simple customizable text button that displays text and can be clicked.
 * 
 * @author Jordan Cohen, Andrew Qiao
 * @version April 2021
 */
public class TextButton extends Actor
{
    // Declare objects and variables
    private GreenfootImage myImage; //Original image of button
    private GreenfootImage myAltImage; //Button of image when clicked
    
    private String buttonText; //Text of the button
    private int textSize; //Size of text of the button
    private Color textColor; //Color of text
    private Color backgroundColor; //Color of background
    private int padding; //Internal padding of the button
    private boolean isClicked; //If the text button is currently clicked or not
    private boolean isDisabled; //If the text button is currently disabled (cannot be clicked)
    
    /**
     * Constructor for TextButton class, sets initial values of variables, and its appearance at the start.
     * 
     * @param buttonText                    Text to be displayed
     * @param textSize                      Size of the text
     * @param textColor                     Color of the text
     * @param backgroundColor               Color of the background
     * @param padding                       Padding around the text
     */
    public TextButton (String buttonText, int textSize, Color textColor, Color backgroundColor, int padding)
    {
        //Assign values to variables
        this.buttonText = buttonText;
        this.textSize = textSize;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.padding = padding;
        
        //Button is not currently clicked
        isClicked = false;
        
        //Button is not currently disabled
        isDisabled = false;
        
        //Create the image of the text
        GreenfootImage tempTextImage = new GreenfootImage (buttonText, textSize, textColor, backgroundColor);
        
        //Create the image of the button and set it as the current image
        myImage = new GreenfootImage(tempTextImage.getWidth() + padding * 2, tempTextImage.getHeight() + padding * 2);
        myImage.setColor(backgroundColor);
        myImage.fill();
        myImage.drawImage(tempTextImage, padding, padding);
        
        myImage.setColor(textColor);
        myImage.drawRect(0, 0, tempTextImage.getWidth() + 2 * padding - 1, tempTextImage.getHeight() + 2 * padding - 1);
        setImage(myImage);
    }
    
    /**
     * Allows other classes to change the text and the color of the text and background.
     */
    public void updateMe(String text, Color textColor, Color backgroundColor)
    {
        this.buttonText = text;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        
        updateMe();
    }
    
    /**
     * Updates the visuals of the text button.
     */
    public void updateMe()
    {
        //Create temporary image of the text for the button's unclicked state
        GreenfootImage tempTextImage = new GreenfootImage (buttonText, textSize, textColor, backgroundColor);
        myImage = new GreenfootImage(tempTextImage.getWidth() + padding * 2, tempTextImage.getHeight() + padding * 2);
        myImage.setColor(backgroundColor);
        myImage.fill();
        myImage.drawImage(tempTextImage, padding, padding);

        myImage.setColor(textColor);
        myImage.drawRect(0, 0, tempTextImage.getWidth() + 2 * padding - 1, tempTextImage.getHeight() + 2 * padding - 1);
        setImage(myImage);
        
        //Create temporary image of the text for the button's clicked state
        tempTextImage = new GreenfootImage (buttonText, textSize, backgroundColor, textColor);
        
        myAltImage = new GreenfootImage(tempTextImage.getWidth() + padding * 2, tempTextImage.getHeight() + padding * 2);
        myAltImage.setColor(textColor);
        myAltImage.fill();
        myAltImage.drawImage (tempTextImage, padding, padding);

        myAltImage.setColor(backgroundColor);
        myAltImage.drawRect(0, 0, tempTextImage.getWidth() + 2 * padding - 1, tempTextImage.getHeight() + 2 * padding - 1);
        
        //Change if the button is clicked or not
        isClicked = !isClicked;
        
        //Set image based on if it is clicked
        if (isClicked) {
            setAlt();
        } else {
            reset();
        }
    }
    
    /**
     * Sets the button to be disabled or not, when disabled, the button will be greyed out.
     * When not disabled, it will return to its regular form.
     * 
     * @param disabled              Boolean for whether or not the button is disabled
     */
    public void setDisabled(boolean disabled)
    {
        isDisabled = disabled;
        if (isDisabled) {
            //Create temporary image of the text for the button's disabled state
            GreenfootImage disabledTextImage = new GreenfootImage (buttonText, textSize, Color.BLACK, new Color(170, 175, 180));
            myImage = new GreenfootImage(disabledTextImage.getWidth() + padding * 2, disabledTextImage.getHeight() + padding * 2);
            myImage.setColor(new Color(170, 175, 180));
            myImage.fill();
            myImage.drawImage(disabledTextImage, padding, padding);

            myImage.setColor(Color.BLACK);
            myImage.drawRect(0, 0, disabledTextImage.getWidth() + 2 * padding - 1, disabledTextImage.getHeight() + 2 * padding - 1);
            setImage(myImage);
        } else {
            //Create temporary image of the text for the button's undisabled state
            GreenfootImage tempTextImage = new GreenfootImage (buttonText, textSize, textColor, backgroundColor);
            myImage = new GreenfootImage(tempTextImage.getWidth() + padding * 2, tempTextImage.getHeight() + padding * 2);
            myImage.setColor(backgroundColor);
            myImage.fill();
            myImage.drawImage(tempTextImage, padding, padding);

            myImage.setColor(textColor);
            myImage.drawRect(0, 0, tempTextImage.getWidth() + 2 * padding - 1, tempTextImage.getHeight() + 2 * padding - 1);
            setImage(myImage);
        }
    }
    
    /**
     * Returns a boolean for whether the 
     */
    public boolean getIsDisabled()
    {
        return isDisabled;
    }
    
    /**
     * Returns a variable for whether or not the button is currently clicked.
     * 
     * @return boolean          If the button is currently clicked
     */
    public boolean getIsClicked()
    {
        return isClicked;
    }
    
    /**
     * Sets the image of the button to the alternative image (clicked).
     */
    public void setAlt()
    {
        setImage(myAltImage);
    }
    
    /**
     * Sets the image of the button to its original image (unclicked).
     */
    public void reset()
    {
        setImage(myImage);
    }
}
