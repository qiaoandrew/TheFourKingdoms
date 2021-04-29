import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>Serpentine Class</h1>
 * 
 * Subclass of Troop superclass, shoots cannonballs at Establishment targets.
 * Moves at a speed of 1, shoots hard-hitting cannonballs, but takes a while to reload.
 * Only targets establishments, but can hit troops that are in its way. A cannonball sound
 * effect is also played every shot. When the serpentine's health reaches 0 or below an 
 * explosion sound effect will be played, along with a disappearing skull.
 * 
 * @author Ryo Minakami, Andrew Qiao
 * @version April 2021
 */
public class Serpentine extends Troop
{
    //Declare array for sound effect for shooting
    private GreenfootSound[] cannonballSounds;
    
    //Index to keep track of index of sound effect array
    private int cannonballSoundsIndex;
    
    //Declare the sound effect
    private GreenfootSound serpentineExplosion;
    
    /**
     * Constructor for Serpentine class, calls superclass constructor and sets properties for the Serpentine.
     */
    public Serpentine(int alliance, int level, Chateau chateau)
    {
        //Call superclass constructor
        super(alliance, level, chateau);
        
        //Set starting values of Serpentine's properties
        maxHP = WarWorld.SERPENTINE_HP + 100 * (level - 1);
        hp = maxHP;
        reloadTime = WarWorld.SERPENTINE_RELOAD;
        speed = WarWorld.SERPENTINE_SPEED;
        
        //Fill the array with the sound effect
        cannonballSounds = new GreenfootSound[40];
        for (int i = 0; i < cannonballSounds.length; i++) {
            cannonballSounds[i] = new GreenfootSound("cannonfire.wav");
            cannonballSounds[i].setVolume(75);
        }
        cannonballSoundsIndex = 0;
        
        //Declare the explosion sound effect
        serpentineExplosion = new GreenfootSound("serpentineexplosion.wav");
        serpentineExplosion.setVolume(90);
    }
    
    /**
     * Called every act, determines the closest establishment and moves, reloads, or shoots accordingly.
     */
    public void act()
    {
        //Increment act counter
        actCount++;
        
        //Determine closest establishment of opposing alliance
        targetEstablishment = findEstablishment();
        
        //If there is an establishment to target
        if (targetEstablishment != null) {
            //Turn towards the establishment
            turnTowards(targetEstablishment.getX(), targetEstablishment.getY());
            //If the establishment is within range
            if (getDistance(targetEstablishment) <= WarWorld.ATTACK_RANGE) {
                if (reloadCounter == reloadTime) { //If reload is complete
                    shoot(targetEstablishment); //Shoot
                    reloadCounter = 0; //Restart reloading
                    
                    //Play the cannonball sound effect, increment the index, and determine if the index should be reset
                    cannonballSounds[cannonballSoundsIndex].play();
                    cannonballSoundsIndex++;
                    if (cannonballSoundsIndex > cannonballSounds.length) {
                        cannonballSoundsIndex = 0;
                    }
                } else { //If reload is not complete
                    reloadCounter++; //Incremenet reload counter
                }
            } else { //If not within range, move
                move(speed);
            }
        }
    }
    
    /**
     * Called to shoot a cannonball.
     * 
     * @param a         Target to shoot cannonball at.
     */
    protected void shoot(Actor a)
    {
        //Create a new cannonball and add it to the world
        getWorld().addObject(new Cannonball(alliance, a, level), getX(), getY());
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
            //Play the sound effect
            serpentineExplosion.play();
            
            getWorld().addObject(new TroopDeath(), getX(), getY());
            getWorld().removeObject(this);
        }
    }
}
