import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>Cannonball Class</h1>
 * 
 * Cannonball ammunition, the ammunition used by Serpentine troops, deals damage to a single object.
 * A relatively hard-hitting projectile, and its damange can be increased with an increase of troop level.
 * 
 * @author: Brendan Chan
 */
public class Cannonball extends Ammunition
{
    /**
     * Constructor for cannonball class, initalizes necessary variables
     * 
     * @param alliance      The alliance of the cannonball
     * @param target        The target that the cannonball is travelling towards
     * @param level         Level of troop that the cannonball came from, determine's its damage
     */
    public Cannonball(int alliance, Actor target, int level)
    {
        //Call the superclass constructors to initalize variables
        super(WarWorld.CANNONBALL_SPEED, alliance, target);
        
        //Determine its damage based on level
        this.damage = WarWorld.CANNONBALL_DAMAGE + 50 * (level - 1);
    }
}
