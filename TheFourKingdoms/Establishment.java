import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>Establishment Class</h1>
 * 
 * Abstract superclass for all establishments, including the Chateau, Garrison, and Treasury. 
 * Includes basic functions that are used by each type of establishment, such as returning and setting
 * the alliance and getting hit by troops.
 * 
 * @author Andrew Qiao, James Li
 * @version April 2021
 */
public abstract class Establishment extends Actor
{
    //Declare instance variables
    protected int hp;
    protected int alliance;
    protected int maxHP;
    
    //Declare health bar object
    protected StatBar healthBar;
    
    /**
     * Constructor for Establishment class, sets its starting variables.
     * 
     * @param maxHP         The maximum HP the establishment can have
     * @param alliance      The alliance the establishment belongs to, 0 is gold, 1 is blue
     */
    public Establishment(int maxHP, int alliance)
    {
        this.hp = maxHP;
        this.maxHP = maxHP;
        this.alliance = alliance;
    }
    
    /**
     * Getter method, returns the alliance variable.
     * 
     * @return alliance     The alliance of the establishment
     */
    public int getAlliance()
    {
        return alliance;
    }
    
    /**
     * Setter method, sets the alliance of the establishment.
     * 
     * @param alliance      The alliance of the establishment
     */
    public void setAlliance(int alliance)
    {
        this.alliance = alliance;
    }
    
    /**
     * Called by troops when the establishment gets hit, decreases HP, changes the properties of its health bar, and checks if HP has reached below 0 accordingly.
     * 
     * @param damage        The amount of damage that has been done to the establishment
     */
    public void hit(int damage)
    {
        //Create a health bar if there is not one already
        if (healthBar == null) {
            healthBar = new StatBar(hp, hp, this, 70, 8, 40, Color.GREEN, Color.RED, true);
            getWorld().addObject(healthBar, getX(), getY() - 40);
            healthBar.update(hp);
        }
        
        //Decrement the HP and update the 
        hp -= damage;
        healthBar.update(hp);
        
        //Check if HP is below 0, and if so remove the health bar and call the method to explode
        if (hp <= 0) {
            getWorld().removeObject(healthBar);
            explode();
        }
    }
    
    /**
     * Called when the establishment's HP reached below 0, abstract as each establishment acts differently.
     */
    protected abstract void explode();
}
