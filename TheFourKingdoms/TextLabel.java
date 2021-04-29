import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>TextLabel Class</h1>
 * 
 * A simple customizable text label that displays text.
 * 
 * @author Andrew Qiao, Ryo Minakami
 * @version April 2021
 */
public class TextLabel extends Actor
{
    //Declare instance variables
    private String text;
    private int fontSize;
    private int padding;
    
    //Declare colors
    private Color foreground;
    private Color background;
    
    //Declare the GreenfootImage for the text label
    private GreenfootImage textLabel;
    
    /**
     * Constructor for the TextLabel class, sets the values for the instance variables and calls update.
     * 
     * @param text              Text that will be displayed on the label
     * @param fontSize          Size of the font of the text
     * @param foreground        Color of the text
     * @param background        Color of the background
     */
    public TextLabel(String text, int fontSize, int padding, Color foreground, Color background)
    {
        //Set values of instance variables
        this.text = text;
        this.fontSize = fontSize;
        this.padding = padding;
        this.foreground = foreground;
        this.background = background;
        
        //Call update to draw all the text label image
        update();
    }
    
    /**
     * Called to draw out the parts of the text label image.
     */
    private void update()
    {
        //Create the image that will contain the text
        GreenfootImage tempText = new GreenfootImage(text, fontSize, foreground, background);
        
        //Create the image for the text label
        textLabel = new GreenfootImage(tempText.getWidth() + padding * 2, tempText.getHeight() + padding * 2);
        
        //Draw the text label
        textLabel.setColor(background);
        textLabel.fill();
        textLabel.drawImage(tempText, padding, padding);
        
        //Draw a rectangle around the text label
        textLabel.setColor(foreground);
        textLabel.drawRect(0, 0, textLabel.getWidth() - 1, textLabel.getHeight() - 1);
        
        //Set the image to the text label
        setImage(textLabel);
    }
}
