import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util

/**
 * <h1>Fireball Class</h1>
 * 
 * Fireball ammunition, ammunition used by the Dragon troop, deals limited splash damage.
 * The damage per fireball is quite low, and can be increased by leveling up barracks.
 * Limited splash damage means if multiple troops are at the exact location, they will all be hit.
 * Splash damage within a radius was originally used, but that produced an unbalance in the simulation.
 * 
 * @author: Andrew Qiao
 * @version: April 2021
 */
public class Fireball extends Ammunition
{
    //Initalizes objects
    private Dragon dragon; //The dragon it belongs to
    private ArrayList<Troop> troopsHit; //The troops that have been hit
    private ArrayList<Establishment> establishmentsHit; //The establishments that have been hit
    
    /**
     * Constructor for Fireball ammunition, initializes its variables and objects.
     * 
     * @param alliance      The alliance it belongs to
     * @param target        The target of the fireball
     * @param level         The level of the fireball
     * @param dragon        The dragon the fireball belongs to
     */
    public Fireball(int alliance, Actor target, int level, Dragon dragon)
    {
        super(WarWorld.FIREBALL_SPEED, alliance, target);
        this.dragon = dragon;
        damage = WarWorld.FIREBALL_DAMAGE + 1 * (level - 1);
    }   
    
    /**
     * Called every act, checks if anything has been hit, and if not, moves and checks its position in the world.
     */
    public void act()
    {
        if (!checkHit()) { //Call method to check if anything has been hit, and if not
            move(speed); //Move at its speed
            checkEdges(); //Call method to check if it has reached the edges
            if (getWorld() != null) { //If the fireball hasn't been removed yet
                checkOutOfRange(); //Call method to check if the fireball is out of range of the dragon
            }
        }
    }
    
    /**
     * Checks if anything has been hit by the fireball, and returns a boolean accordingly. If it has hit anything on the opposite alliance, it will remove itself.
     */
    protected boolean checkHit()
    {
        //Declare variable for if it hit anything
        boolean hitSomething = false;
        
        //ArrayLists for troops and establishments hit
        troopsHit = (ArrayList)getObjectsAtOffset(0, 0, Troop.class);
        establishmentsHit = (ArrayList)getObjectsAtOffset(0, 0, Establishment.class);
        
        //Determines if anything has been hit
        if (troopsHit.size() == 0 && establishmentsHit.size() == 0) {
            return hitSomething;
        } else {
            //If the troops or establishments is on the opposing alliance, it will be damaged
            for (Troop t : troopsHit) {
                if (t.getAlliance() != alliance) {
                    t.hit(damage);
                    hitSomething = true;
                }
            }
            
            for (Establishment e : establishmentsHit) {
                if (e.getAlliance() != alliance) {
                    e.hit(damage);
                    hitSomething = true;
                }
            }
            
            if (hitSomething) {
                getWorld().removeObject(this);
            }
            
            return hitSomething;
        }
    }
    
    /**
     * Checks if the Fireball is more than or equal to 200 pixels away from its host dragon, if so, it gets removed from the world.
     */
    private void checkOutOfRange()
    {
        if (dragon.getWorld() != null) {
            if (Math.sqrt(Math.pow(getX() - dragon.getX(), 2) + Math.pow(getY() - dragon.getY(), 2)) >= 200) {
                getWorld().removeObject(this);
            }
        } else {
            getWorld().removeObject(this);
        }
    }
}
