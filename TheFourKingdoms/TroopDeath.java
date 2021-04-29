import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>TroopDeath Class</h1>
 * 
 * Created when a troop dies. Displays a skull that grows more and more transparent until it eventually gets removed.
 * 
 * @author Group 1, Mr. Cohen
 * @version April 2021
 */
public class TroopDeath extends Actor
{
    /**
     * Constructor for TroopDeath class, creates the image of the skull.
     */
    public TroopDeath()
    {
        //Set the image to full transparency
        this.getImage().setTransparency(250);
    }
    
    /**
     * Called every act, subtracts from the transparency, removes the object after the transparency reaches below 5.
     */
    public void act() 
    {
        //Decrease transparency and/or remove it
        this.getImage().setTransparency(getImage().getTransparency() - 1);
        if (this.getImage().getTransparency() < 5) {
            getWorld().removeObject(this);
        }
    }     
}
