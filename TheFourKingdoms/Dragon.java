import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>Dragon Class</h1>
 * 
 * Subclass of Troop superclass, shoots fireballs at both Troop and Establishments targets.
 * Moves at a speed of 2, and can also move while shooting within a certain range.
 * Shoots fireballs extremely quickly, and does splash damage to troops at the exact same 
 * position. Radius splash damage was removed as that made the troops and buildings unbalanced.
 * Once fireballs reach a certain distance away from its dragon, they dissapear, recreating
 * the real life effect. A sound is also played every 10 fireballs, to make sure it doesn't 
 * get hectic. Once the dragon's health reaches 0 or below, it will die, leaving behind a sound
 * effect and a disappearing skull.
 * 
 * @author Andrew Qiao
 * @version April 2021
 */
public class Dragon extends Troop
{
    //Declare array for sound effect for shooting
    private GreenfootSound[] fireballSounds;
    
    //Index to keep track of index of sound effect array
    private int fireballSoundsIndex;
    
    //Index to keep track of when to play the sound effect
    private int shotIndex;
    
    //Declare sound effect for dragon's death
    private GreenfootSound dragonDeath;
    
    /**
     * Constructor for Dragon class, calls superclass constructor, sets starting values of the dragon, and determines its image based on alliance.
     * 
     * @param alliance              Alliance dragon belongs to (0 - Gold, 1 - Blue)
     * @param level                 Level of the dragon
     * @param chateau               Chateau dragon belongs to
     */
    public Dragon(int alliance, int level, Chateau chateau)
    {
        //Calls the superclass constructor
        super(alliance, level, chateau);
        
        //Sets starting properties of the Dragon
        maxHP = WarWorld.DRAGON_HP + 100 * (level - 1);
        hp = maxHP;
        reloadTime = WarWorld.DRAGON_RELOAD;
        speed = WarWorld.DRAGON_SPEED;
        
        //Determines which color dragon image to show based on alliance
        if (alliance == 0) {
            setImage("reddragon.png");
        } else {
            setImage("bluedragon.png");
        }
        
        //Fill the array with the sound effect
        fireballSounds = new GreenfootSound[40];
        for (int i = 0; i < fireballSounds.length; i++) {
            fireballSounds[i] = new GreenfootSound("fireball.wav");
            fireballSounds[i].setVolume(60);
        }
        fireballSoundsIndex = 0;
        
        shotIndex = 0;
        
        //Initialize the sound effect for the dragon's death
        dragonDeath = new GreenfootSound("dragondeath.wav");
        dragonDeath.setVolume(80);
    }
    
    /**
     * Called every act, finds the closest troop and establishment of the opposite alliance and determines whether to move, shoot a fireball, or both.
     */
    public void act() 
    {
        //Incremenet act counter
        actCount++;
        
        //Determine closes troop and establishment
        targetTroop = findTroop();
        targetEstablishment = findEstablishment();
        
        //If there is only a troop to target
        if (targetTroop != null && targetEstablishment == null) {
            //Turn towards the troop
            turnTowards(targetTroop.getX(), targetTroop.getY());
            //If the troop is in range
            if (getDistance(targetTroop) <= WarWorld.FIREBALL_RANGE) {
                //Determine if the dragon can shoot, if not, increment the reload counter
                if (reloadCounter == reloadTime) {
                    shoot(targetTroop);
                    reloadCounter = 0;
                    
                    //This is to prevent the sound effect from playing every single shot, instead plays every 10
                    if (shotIndex == 10) {
                        //Play the fireball sound effect, increment the index, and determine if the index should be reset
                        fireballSounds[fireballSoundsIndex].play();
                        fireballSoundsIndex++;
                        if (fireballSoundsIndex > 20) {
                            fireballSoundsIndex = 0;
                        }
                        shotIndex = 0;
                    } else {
                        shotIndex++;
                    }
                } else {
                    reloadCounter++;
                }
                //Dragon can attack while moving towards a target
                if (getDistance(targetTroop) > WarWorld.ATTACK_RANGE) {
                    move(speed);
                }
            } else { //Otherwise, only move
                move(speed);
            }
        } else if (targetEstablishment != null && targetTroop == null) { //If there is an establishment to target, but no troop
            //Turn towards the establishment
            turnTowards(targetEstablishment.getX(), targetEstablishment.getY());
            //If in range of shooting
            if (getDistance(targetEstablishment) <= WarWorld.FIREBALL_RANGE) {
                //Determine if the dragon can shoot, if not, increment the reload counter
                if (reloadCounter == reloadTime) {
                    shoot(targetEstablishment);
                    reloadCounter = 0;
                    
                    //This is to prevent the sound effect from playing every single shot, instead plays every 10
                    if (shotIndex == 10) {
                        //Play the fireball sound effect, increment the index, and determine if the index should be reset
                        fireballSounds[fireballSoundsIndex].play();
                        fireballSoundsIndex++;
                        if (fireballSoundsIndex > 20) {
                            fireballSoundsIndex = 0;
                        }
                        shotIndex = 0;
                    } else {
                        shotIndex++;
                    }
                } else {
                    reloadCounter++;
                }
                ////Dragon can attack while moving towards a target
                if (getDistance(targetEstablishment) > WarWorld.ATTACK_RANGE) {
                    move(speed);
                }
            } else { //Otherwise, only move
                move(speed);
            }
        } else if (targetTroop != null && targetEstablishment != null) { //If there is both an establishment and troop to target
            //If the troop is closer or equally close to the establishment
            if (getDistance(targetTroop) <= getDistance(targetEstablishment)) {
                //Turn towards the troop
                turnTowards(targetTroop.getX(), targetTroop.getY());
                //If the troop is within range
                if (getDistance(targetTroop) <= WarWorld.FIREBALL_RANGE) {
                    //Determine if reload is complete, if not reload
                    if (reloadCounter == reloadTime) {
                        shoot(targetTroop);
                        reloadCounter = 0;
                        
                        //This is to prevent the sound effect from playing every single shot, instead plays every 10
                        if (shotIndex == 10) {
                            //Play the fireball sound effect, increment the index, and determine if the index should be reset
                            fireballSounds[fireballSoundsIndex].play();
                            fireballSoundsIndex++;
                            if (fireballSoundsIndex > 20) {
                                fireballSoundsIndex = 0;
                            }
                            shotIndex = 0;
                        } else {
                            shotIndex++;
                        }
                    } else {
                        reloadCounter++;
                    }
                    //Dragon can move while shooting
                    if (getDistance(targetTroop) > WarWorld.ATTACK_RANGE) {
                        move(speed);
                    }
                } else { //If not in range, simply move
                    move(speed);
                }
            } else { //If the establishment is closer
                //Turn towards the establishment
                turnTowards(targetEstablishment.getX(), targetEstablishment.getY());
                if (getDistance(targetEstablishment) <= WarWorld.FIREBALL_RANGE) { //If the establishment is within range
                    //Determine if reload is complete, if not, reload
                    if (reloadCounter == reloadTime) {
                        shoot(targetEstablishment);
                        reloadCounter = 0;
                        
                        //This is to prevent the sound effect from playing every single shot, instead plays every 10
                        if (shotIndex == 10) {
                            //Play the fireball sound effect, increment the index, and determine if the index should be reset
                            fireballSounds[fireballSoundsIndex].play();
                            fireballSoundsIndex++;
                            if (fireballSoundsIndex > 20) {
                                fireballSoundsIndex = 0;
                            }
                            shotIndex = 0;
                        } else {
                            shotIndex++;
                        }
                    } else {
                        reloadCounter++;
                    }
                    //Dragon can move while shooting
                    if (getDistance(targetEstablishment) > WarWorld.ATTACK_RANGE) {
                        move(speed);
                    }
                } else { //If not in range, simply move
                    move(speed);
                }
            }
        }
    }    
    
    /**
     * Called for the dragon to shoot a fireball.
     * 
     * @param a         Actor which to shoot at
     */
    private void shoot(Actor a)
    {
        //Create a new Fireball and add it to the world at the dragon's location
        getWorld().addObject(new Fireball(alliance, a, level, this), getX(), getY());
    }
    
    /**
     * Set the alliance of the dragon.
     * 
     * @param alliance          Alliance the dragon belongs to (0 - Gold, 1 - Blue)
     */
    public void setAlliance(int alliance)
    {
        this.alliance = alliance; //Set the value of the alliance
        
        //Determine which image to show, based on alliance
        if (alliance == 0) {
            setImage("reddragon.png");
        } else {
            setImage("bluedragon.png");
        }
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
            dragonDeath.play();
            
            getWorld().addObject(new TroopDeath(), getX(), getY());
            getWorld().removeObject(this);
        }
    }
}
