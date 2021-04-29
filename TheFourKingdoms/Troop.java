import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util

/**
 * <h1>Troop Class</h1>
 * 
 * Superclass of all different troops, contains all the necessary instance variables pertaining to the properties of each troop, also includes 
 * vital methods for the activities of the troops, such as targeting the nearest troops, establishments, and getting hit by enemies.
 * 
 * @author Andrew Qiao, Ryo Minakami
 * @verions April 2021
 */
public abstract class Troop extends Actor
{    
    //Declare instance variables
    protected int alliance;
    protected int maxHP;
    protected int hp;
    protected int reloadTime;
    protected int speed;
    protected int actCount;
    protected int level;
    protected double closestTroop;
    protected double closestEstablishment;
    protected int reloadCounter;
    
    //Declare ArrayLists for troops and establishments
    protected ArrayList<Troop> troops;
    protected ArrayList<Establishment> establishments;
    
    //Declare potential targets
    protected Troop targetTroop;
    protected Establishment targetEstablishment;
    
    //Declare the chateau that this troop belongs to
    protected Chateau chateau;
    
    //Declare the health bar 
    protected StatBar healthBar;
    
    /**
     * Constructor for Troop, sets starting values of variables.
     * 
     * @param alliance          Alliance the troop belongs to (0 - Gold, 1 - Blue)
     * @param level             Level of the troop
     * @param chateau           Chateau that the troop belongs to
     */
    public Troop(int alliance, int level, Chateau chateau)
    {
        //Set starting values of the instance variables
        this.alliance = alliance;
        actCount = 0;
        reloadCounter = 0;
        this.level = level;
        this.chateau = chateau;
    }
    
    /**
     * Called when the troop gets hit, decrements HP accordingly.
     * 
     * @param damage            Damage dealt to the troop
     */
    protected void hit(int damage)
    {
        //If there is not health bar, create one
        if (healthBar == null) {
            healthBar = new StatBar(hp, hp, this, 70, 8, 40, Color.GREEN, Color.RED, true);
            getWorld(). addObject(healthBar, getX(), getY() - 40);
            healthBar.update(hp);
        }
        
        //Decrement the HP and update the health bar accordingly
        hp -= damage;
        healthBar.update(hp);
        
        //If the HP of the troop reached 0 or below, call method for the troop's death
        if (hp <= 0) {
            death(false);
        }
    }
    
    /**
     * Returns the closest establishment of the enemy alliance, returns null if there are none.
     * 
     * @return Establishment                Closest establishment of enemy alliance to the troop
     */
    protected Establishment findEstablishment()
    {
        //Target establishment starts off as null
        Establishment establishmentTarget = null;
        
        //Create ArrayList of all the establishments in the world
        establishments = (ArrayList)getWorld().getObjects(Establishment.class);
        
        //Starting number for establishment distance
        closestEstablishment = 1000000;
        
        //Loop through all the establishments, if an establishment is closer than the currently closest and is from the opposing alliance, make that the target
        for (Establishment e : establishments) {
            if (e.getAlliance() != alliance) {
                double establishmentDistance = getDistance(e);
                if (establishmentDistance < closestEstablishment) {
                    establishmentTarget = e;
                    closestEstablishment = establishmentDistance;
                }
            }
        }
        
        //Return the target establishment (null if none)
        return establishmentTarget;
    }
    
    /**
     * Returns the closest troop of the opposing alliance.
     * 
     * @return Troop            Closest troop of the opposing alliance
     */
    protected Troop findTroop()
    {
        //Target troop starts off as null
        Troop troopTarget = null;
        
        //ArrayList of all the troops
        troops = (ArrayList)getWorld().getObjects(Troop.class);
        
        //Starting number for troop distance
        closestTroop = 1000000;
        
        //Loop through all the troops, if a troop is closer than the currently closest and is from the opposing alliance, make that the target
        for (Troop t : troops) {
            if (t.getAlliance() != alliance) {
                double troopDistance = getDistance(t);
                if (troopDistance < closestTroop) {
                    troopTarget = t;
                    closestTroop = troopDistance;
                }
            }
        }
        
        //Return the target troop (null if none)
        return troopTarget;
    }
    
    /**
     * Returns the distance between a troop and another actor.
     * 
     * @return double           Distance between a troop and another actor
     */
    protected double getDistance(Actor a)
    {
        //Uses Pythagorean Theorem to find the distance between 2 coordinates
        return Math.sqrt(Math.pow(getX() - a.getX(), 2) + Math.pow(getY() - a.getY(), 2));
    }
    
    /**
     * Called when a troop's HP is 0 or below.
     * 
     * @param isCastleDestroyed             If the castle has been destroyed
     */
    public void death(boolean isCastleDestroyed)
    {
        //Remove this troop from the ArrayList of troops of the castle only if the castle has not been destroyed
        if (!isCastleDestroyed) {
            chateau.removeTroop(this);
        }
        
        //If the troop is still in this world, create a new TroopDeath and remove the troop from the world
        if (getWorld() != null) {
            getWorld().addObject(new TroopDeath(), getX(), getY());
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Getter method, returns the alliance of the Troop (0 - Gold, 1 - Blue).
     * 
     * @return int          Allinace of the Troop (0 - Gold, 1 - Blue)
     */
    public int getAlliance()
    {
        return alliance;
    }
    
    /**
     * Sets the alliance of the Troop.
     * 
     * @param alliance      Alliance of the Troop (0 - Gold, 1 - Blue)
     */
    public void setAlliance(int alliance)
    {
        this.alliance = alliance;
    }
}
