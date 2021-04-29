import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue; //Import Queue from java.util
import java.util.LinkedList; //Import LinkedList from java.util

/**
 * <h1>Garrison Class</h1>
 * 
 * Subclass of Establishment superclass, establishments that train troops for the kingdom.
 * Can be upgraded by the Chateau, a higher level, means a higher level troop, leading to higher HP and more damage.
 * 
 * @author Andrew Qiao
 * @version April 2021
 */
public class Garrison extends Establishment
{
    //Declare instance variables
    private int level;
    private boolean busy;
    private int currentTroop;
    
    //Declare array of images for different levels
    private GreenfootImage[] garrisonImages = {new GreenfootImage("garrison1.png"), new GreenfootImage("garrison2.png"), new GreenfootImage("garrison3.png")};
    
    //Queue of troops to be trained
    private Queue<Integer> troopsQueue;
    
    //Chateau this garrison belongs to
    private Chateau chateau;
    
    //Declare the timer that will keep track of timing for when troops will be finished training
    private SimpleTimer simpleTimer = new SimpleTimer();
    
    //Declare sound effect for destruction
    private GreenfootSound destroySoundEffect;
    
    //Declare timer
    private Timer timer;
    
    /**
     * Constructor for Garrison class, calls the constructor of its superclass, initializes queue for its queue of troops, sets its image, and sets its starting values.
     * 
     * @param hp            Starting HP of the garrison
     * @param alliance      Alliance the garrison belongs to (0 - gold, 1 - blue)
     * @param chateau       Chateau the garrison belongs to
     */
    public Garrison(int hp, int alliance, Chateau chateau)
    {
        //Call constructor of its superclass
        super(hp, alliance);
        
        //Initialize queue containing troops to be trained
        troopsQueue = new LinkedList<Integer>();
        
        //Set the garrison's image
        setImage(garrisonImages[0]);
        
        //Set starting values of each of the instance variables
        level = 1;
        this.chateau = chateau;
        busy = false;
        
        //Initialize sound effect
        destroySoundEffect = new GreenfootSound("destruction.wav");
        destroySoundEffect.setVolume(80);
    }
    
    /**
     * Called every act, determines if a troop should be queued or added to the world.
     */
    public void act() 
    {
        //If the garrison is not currently busy, it will start training the troop at the beginning of its queue
        if (!busy && troopsQueue.size() > 0) {
            if (troopsQueue.peek() == 0) {
                simpleTimer.mark();
                currentTroop = 0;
                busy = true;
                timer = new Timer(WarWorld.SERPENTINE_TIME);
                getWorld().addObject(timer, getX(), getY());
            } else if (troopsQueue.peek() == 1) {
                simpleTimer.mark();
                currentTroop = 1;
                busy = true;
                timer = new Timer(WarWorld.DRAGON_TIME);
                getWorld().addObject(timer, getX(), getY());
            }
        }
        
        //If the garrison is busy, it will look at what troop is currently being trained and if the training time is over, it will add it to the world
        if (currentTroop == 0 && busy) {
            if (simpleTimer.millisElapsed() >= WarWorld.SERPENTINE_TIME) {
                Serpentine s = new Serpentine(alliance, level, chateau);
                getWorld().addObject(s, getX(), getY());
                chateau.addTroop(s);
                troopsQueue.remove();
                busy = false;
                getWorld().removeObject(timer);
            }
        } else if (currentTroop == 1 && busy) {
            if (simpleTimer.millisElapsed() >= WarWorld.DRAGON_TIME) {
                Dragon d = new Dragon(alliance, level, chateau);
                getWorld().addObject(d, getX(), getY());
                chateau.addTroop(d);
                troopsQueue.remove();
                busy = false;
                getWorld().removeObject(timer);
            }
        }
    }  
    
    /**
     * Called to level up the garrison, incremenets the level, and changes its image.
     */
    public void levelUp()
    {
        //Garrison max level is 3
        if (level < 3) {
            level++; //Increment the level
            setImage(garrisonImages[level - 1]); //Set image based on the level
        }
    }
    
    /**
     * Called when the HP of the garrison reaches less than or equal to 0, adds the image of the rubble, and removes this object from the garrisons ArrayList and from the world.
     */
    protected void explode()
    {
        //Remove the timer if is still there
        if (timer != null) {
            getWorld().removeObject(timer);
        }
        
        //If the garrison is still in the world, otherwise it will crash
        if (getWorld() != null) {
            destroySoundEffect.play(); //Play sound effect
            getWorld().addObject(new Rubble(), getX(), getY()); //Add the image of rubble
            chateau.removeGarrison(this); //Remove this garrison from the ArrayLists of garrisons
            getWorld().removeObject(this); //Remove this garrison from the world
        }
    }
    
    /**
     * Called when the castle has been destroyed, essentially does the same as the explode() method but does not remove the garrison from the ArrayList.
     */
    protected void castleDestroyed()
    {
        //Remove the timer if is still there
        if (timer != null) {
            getWorld().removeObject(timer);
        }
        
        //If the garrison is still in the world, otherwise it will crash
        if (getWorld() != null) {
            getWorld().addObject(new Rubble(), getX(), getY()); //Add the image of rubble
            getWorld().removeObject(this); //Remove this garrison from the world
        }
    }
    
    /**
     * Returns the level of the garrison.
     * 
     * @return int          The level of the garrison
     */
    public int getLevel()
    {
        return level;
    }
    
    /**
     * Calculates and returns the queue time of all the troops in its queue.
     * 
     * @return int          Queue time in milliseconds
     */
    public int getQueueTime()
    {
        int queueTime = 0;
        for (int troop : troopsQueue) {
            if (troop == 1) {
                queueTime += WarWorld.SERPENTINE_TIME;
            }
        }
        return queueTime;
    }
    
    /**
     * Adds a troop to the queue.
     * 
     * @param troopType         The type of troop to be added (0 - Serpentine, 1 - Dragon)
     */
    public void queueTroop(int troopType)
    {
        troopsQueue.add(troopType);
    }
}
