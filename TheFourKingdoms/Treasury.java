import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>Treasury Class</h1>
 * 
 * Subclass of Establishment superclass, creates money for the kingdom.
 * Every two seconds is a cycle, when money will be added.
 * Has three levels, can be upgraded by the Chateau. Once upgraded, more money will be produced every cycle.
 * 
 * @author Andrew Qiao
 * @version April 2021
 */
public class Treasury extends Establishment
{
    //Decalre instance variables
    private int level;
    
    //Declare timer that will be used to determine when money will be added
    private SimpleTimer timer = new SimpleTimer();
    
    //Declare array of images for each treasury level
    private GreenfootImage[] treasuryImages = {new GreenfootImage("treasury1.png"), new GreenfootImage("treasury2.png"), new GreenfootImage("treasury3.png")};
    
    //The chateau that this treasury belongs to
    private Chateau chateau;
    
    //Declare the time it takes for each cycle
    private final int timePerCycle = 2000;
    
    //Declare the amount of money to add per cycle based on level
    private final int[] amountPerCycle = {1000, 2000, 3000};
    
    //Declare sound effect for destruction
    private GreenfootSound destroySoundEffect;
    
    /**
     * Constructor for Treasury class, calls its superclass constructor, sets the image, sets instance variables, and starts the timer.
     */
    public Treasury(int hp, int alliance, Chateau chateau)
    {
        //Call superclass constructor
        super(hp, alliance);
        
        //Set the image of level 1 treasury
        setImage(treasuryImages[0]);
        
        //Set values of instance variables
        this.chateau = chateau;
        level = 1;
        
        //Start the timer
        timer.mark();
        
        //Initialize the sound effect
        destroySoundEffect = new GreenfootSound("destruction.wav");
        destroySoundEffect.setVolume(80);
    }
    
    /**
     * Called ever act, determines if a cycle is complete, if so, adds money to the chateau.
     */
    public void act() 
    {
        //If the time of a cycle has passed
        if (timer.millisElapsed() >= timePerCycle) {
            //Reset the timer
            timer.mark();
            
            //Add the amount of money to the chateau based on its level
            chateau.addMoney(amountPerCycle[level - 1]);
        }
    }    
    
    /**
     * Called to level up the treasury and change its image accordingly.
     */
    public void levelUp()
    {
        //Max level of treasury is 3
        if (level < 3) {
            level++; //Increments the level
            setImage(treasuryImages[level - 1]); //Sets the image based on level
        }
    }
    
    /**
     * Called when the HP of thre treasury reaches below 0, adds rubble, removes the treasury from the ArrayList of treasuries, and removes the treasury from the world.
     */
    protected void explode()
    {
        //If the treasury is in the world, otherwise it will crash
        if (getWorld() != null) {
            destroySoundEffect.play(); //Play sound effect
            getWorld().addObject(new Rubble(), getX(), getY()); //Add rubble in place of the treasury
            chateau.removeTreasury(this); //Remove this treasury from the ArrayList of treasuries
            getWorld().removeObject(this); //Remove this object from the world
        }
    }
    
    /**
     * Called when the castle that the treasury belongs to has been destroyed, does mostly the same thing as the explode() method except it doesn't remove the treasury from the ArrayList of treasuries, as the chateau does that.
     */
    protected void castleDestroyed()
    {
        //If the treasury is in the world, otherwise it will crash
        if (getWorld() != null) {
            getWorld().addObject(new Rubble(), getX(), getY()); //Add rubble in place of the treasury
            getWorld().removeObject(this); //Remove this object from the world
        }
    }
    
    /**
     * Returns the current level of the Treasury.
     * 
     * @return int          The current level of the treasury
     */
    public int getLevel()
    {
        return level;
    }
}
