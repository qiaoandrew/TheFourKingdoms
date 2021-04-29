import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>Ammunition Class</h1>
 * 
 * Abstract superclass of all types of ammunition, initializes basic variables and includes functions necessary for the ammunition's functions.
 * 
 * @author Brendan Chan, Andrew Qiao
 * @version April 2021
 */
public abstract class Ammunition extends Actor
{
    //Declare instance variables
    protected int damage;
    protected int speed;
    protected int alliance;
    protected int xTarget;
    protected int yTarget;
    
    //Declare objects that will potentially be hit by the ammunition
    private Troop t;
    private Establishment e;
    
    /**
     * Initializes the ammunition's speed, alliance, and coordinates of its target.
     * 
     * @param speed         The speed of the ammunition
     * @param alliance      The alliance the ammunition belongs to
     * @param target        The target of the ammunition
     */
    public Ammunition(int speed, int alliance, Actor target)
    {
        this.speed = speed;
        this.alliance = alliance;
        this.xTarget = target.getX();
        this.yTarget = target.getY();
    }
    
    /**
     * Called every method, checks if the ammunition has hit anything, and if not moves and checks if it is out of bounds.
     */
    public void act() 
    {
        if (!checkHit()) {
            move(speed);
            checkEdges();
        }
    }    
    
    /**
     * Called when ammunition is added to the world, turns towards its target.
     * 
     * @param w     The World the ammunition is in
     */
    public void addedToWorld(World w)
    {
        turnTowards(xTarget, yTarget);
    }
    
    /**
     * Checks if the ammunition has hit anything, and if so removes itself from the world.
     * 
     * @return boolean      If the ammunition has hit anything
     */
    protected boolean checkHit()
    {
        //Potential targets
        e = (Establishment)getOneObjectAtOffset(0, 0, Establishment.class);
        t = (Troop)getOneObjectAtOffset(0, 0, Troop.class);
        
        //Checks if the targets are on the other alliance and acts accordingly
        if (e != null && e.getAlliance() != alliance) {
            e.hit(damage);
            getWorld().removeObject(this);
            return true;
        }
        
        if (t != null && t.getAlliance() != alliance) {
            t.hit(damage);
            getWorld().removeObject(this);
            return true;
        }
        
        return false;
    }
    
    /**
     * Checks if the ammunition has reaches the edges, and if so, removes itself from the world.
     */
    protected void checkEdges()
    {
        if (getX() - getImage().getWidth() / 2 <= 0 || getX() + getImage().getWidth() / 2 >= 1280 || 
        getY() - getImage().getHeight() / 2 <= 0 || getY() + getImage().getHeight() / 2 >= 800) {
            getWorld().removeObject(this);
        }
    }
}
