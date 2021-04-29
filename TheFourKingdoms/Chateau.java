import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util

/**
 * <h1>Chateau Class</h1>
 * 
 * Subclass of Establishment superclass, controls the main functions of each kingdom, such as choosing which troop to queue into training, 
 * and determining which establishment to upgrade.
 * The color of the Chateau will be either gold or blue, based on its alliance.
 * 
 * @author Andrew Qiao
 * @version April 2021
 */
public class Chateau extends Establishment
{
    //Declare ArrayLists for chateau to keep track of its establishments and troops
    private ArrayList<Treasury> treasuries;
    private ArrayList<Garrison> garrisons;
    private ArrayList<Troop> troops;
    
    //Declare instance variables
    private int money;
    private int index;
    private String name;
    private int actCount;
    private boolean isDestroyed;
    
    //Declare images for blue and gold chateau
    private GreenfootImage goldChateau = new GreenfootImage("goldcastle.png");
    private GreenfootImage blueChateau = new GreenfootImage("bluecastle.png");
    
    //Declare sound effect for leveling up buildings
    private GreenfootSound[] levelUpSounds;
    private int levelUpSoundsIndex;
    
    //Declare sound effect for building getting destroyed
    private GreenfootSound destroySoundEffect;
    
    /**
     * Constructor for Chateau class, sets instance variables, initializes its ArrayLists and determines which colored picture should be showing.
     * 
     * @param hp            The HP the chateau will start off with
     * @param alliance      The alliance of the chateau, 0 is gold, 1 is blue
     * @param name          Name of the kingdom the chateau belongs to
     * @param money         Amount of money the kingdom starts off with
     */
    public Chateau(int hp, int alliance, String name, int money)
    {
        //Call the constructor of its superclass
        super(hp, alliance);
        
        //Initialize the ArrayLists
        treasuries = new ArrayList<Treasury>();
        garrisons = new ArrayList<Garrison>();
        troops = new ArrayList<Troop>();
        
        //Determine which picture to display
        if (alliance == 0) {
            setImage(goldChateau);
        } else {
            setImage(blueChateau);
        }
        
        //Set instance variables
        this.money = money;
        this.name = name;
        actCount = 0;
        isDestroyed = false;
        
        //Determine what index the chateau is (0 - Belzerg, 1 - Leiden, 2 - Eldia, 3 - Marley)
        if (name.equals("Belzerg")) {
            index = 0;
        } else if (name.equals("Leiden")) {
            index = 1;
        } else if (name.equals("Eldia")) {
            index = 2;
        } else {
            index = 3;
        }
        
        //Initalize the index for sounds and fill its array
        levelUpSoundsIndex = 0;
        levelUpSounds = new GreenfootSound[20];
        for (int i = 0; i < levelUpSounds.length; i++) {
            levelUpSounds[i] = new GreenfootSound("levelup.wav");
            levelUpSounds[i].setVolume(70);
        }
        
        //Declare sound effect for destruction
        destroySoundEffect = new GreenfootSound("destruction.wav");
        destroySoundEffect.setVolume(80);
    }
    
    /**
     * Called every act, determines which troops to queue and which upgrades to make based on the act count, also increments the act count.
     */
    public void act() 
    {
        //Determine if it should queue a troop
        if (actCount % 50 == 0 && garrisons.size() != 0) {
            determineTroop();
        }
        
        //Determine if an upgrade should be made to garrisons or treasuries
        if (actCount % 20 == 0 && garrisons.size() != 0) {
            determineUpgrade(2);
        }
        
        if (actCount % 15 == 0 && treasuries.size() != 0) {
            determineUpgrade(1);
        }
        
        //Increment the act counter
        actCount++;
    }    
    
    /**
     * Method that is called to determine if an upgrade to an establishment should be made.
     * 
     * @param establishmentType         The type of establishment that should be upgraded (1 - treasury, 2 - garrison)
     */
    private void determineUpgrade(int establishmentType)
    {
        if (establishmentType == 1) { //Determine if a treasury should be upgraded
            //If there is only 1 treasury
            if (treasuries.size() == 1) {
                //Determine its current level, and determine if the chateau has enough resources to upgrade, if so upgrade and decrement money
                if (treasuries.get(0).getLevel() == 1) {
                    if (money >= WarWorld.TREASURY_LEVEL_2_COST) {
                        treasuries.get(0).levelUp();
                        money -= WarWorld.TREASURY_LEVEL_2_COST;
                        
                        //Play the upgrade sound
                        playSound();
                    }
                } else if (treasuries.get(0).getLevel() == 2) {
                    if (money >= WarWorld.TREASURY_LEVEL_3_COST) {
                        treasuries.get(0).levelUp();
                        money -= WarWorld.TREASURY_LEVEL_3_COST;
                        
                        //Play the upgrade sound
                        playSound();
                    }
                }
            //If there are 2 treasuries
            } else if (treasuries.size() == 2) {
                //Upgrade the treasury of the lowest level
                //Determine if there is enough financial resources to upgrade, if so, ugprade and decrement money
                if (treasuries.get(0).getLevel() <= treasuries.get(1).getLevel()) {
                    if (treasuries.get(0).getLevel() == 1) {
                        if (money >= WarWorld.TREASURY_LEVEL_2_COST) {
                            treasuries.get(0).levelUp();
                            money -= WarWorld.TREASURY_LEVEL_2_COST;
                            
                            //Play the upgrade sound
                            playSound();
                        }
                    } else if (treasuries.get(0).getLevel() == 2) {
                        if (money >= WarWorld.TREASURY_LEVEL_3_COST) {
                            treasuries.get(0).levelUp();
                            money -= WarWorld.TREASURY_LEVEL_3_COST;
                            
                            //Play the upgrade sound
                            playSound();
                        }
                    }
                } else {
                    if (treasuries.get(1).getLevel() == 1) {
                        if (money >= WarWorld.TREASURY_LEVEL_2_COST) {
                            treasuries.get(1).levelUp();
                            money -= WarWorld.TREASURY_LEVEL_2_COST;
                            
                            //Play the upgrade sound
                            playSound();
                        }
                    } else if (treasuries.get(1).getLevel() == 2) {
                        if (money >= WarWorld.TREASURY_LEVEL_3_COST) {
                            treasuries.get(1).levelUp();
                            money -= WarWorld.TREASURY_LEVEL_3_COST;
                            
                            //Play the upgrade sound
                            playSound();
                        }
                    }
                }
            }
        } else { //Determine if a garrison should be upgraded
            if (garrisons.size() == 1) {
                if (garrisons.get(0).getLevel() == 1) {
                    if (money >= WarWorld.GARRISON_LEVEL_2_COST) {
                        garrisons.get(0).levelUp();
                        money -= WarWorld.GARRISON_LEVEL_2_COST;
                        
                        //Play the upgrade sound
                        playSound();
                    }
                } else if (garrisons.get(0).getLevel() == 2) {
                    if (money >= WarWorld.GARRISON_LEVEL_3_COST) {
                        garrisons.get(0).levelUp();
                        money -= WarWorld.GARRISON_LEVEL_3_COST;
                        
                        //Play the upgrade sound
                        playSound();
                    }
                }
            } else if (garrisons.size() == 2) {
                if (garrisons.get(0).getLevel() <= garrisons.get(1).getLevel()) {
                    if (garrisons.get(0).getLevel() == 1) {
                        if (money >= WarWorld.GARRISON_LEVEL_2_COST) {
                            garrisons.get(0).levelUp();
                            money -= WarWorld.GARRISON_LEVEL_2_COST;
                            
                            //Play the upgrade sound
                            playSound();
                        }
                    } else if (garrisons.get(0).getLevel() == 2) {
                        if (money >= WarWorld.GARRISON_LEVEL_3_COST) {
                            garrisons.get(0).levelUp();
                            money -= WarWorld.GARRISON_LEVEL_3_COST;
                            
                            //Play the upgrade sound
                            playSound();
                        }
                    }
                } else {
                    if (garrisons.get(1).getLevel() == 1) {
                        if (money >= WarWorld.GARRISON_LEVEL_2_COST) {
                            garrisons.get(1).levelUp();
                            money -= WarWorld.GARRISON_LEVEL_2_COST;
                            
                            //Play the upgrade sound
                            playSound();
                        }
                    } else if (garrisons.get(1).getLevel() == 2) {
                        if (money >= WarWorld.GARRISON_LEVEL_3_COST) {
                            garrisons.get(1).levelUp();
                            money -= WarWorld.GARRISON_LEVEL_3_COST;
                            
                            //Play the upgrade sound
                            playSound();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Determines which troop to queue, and at which garrison.
     */
    private void determineTroop()
    {
        //Determine which garrison has the lowest queue time and choose that one
        int garrisonIndex;
        if (garrisons.size() == 1) {
            garrisonIndex = 0;
        } else if (garrisons.size() == 2) {
            if (garrisons.get(0).getQueueTime() < garrisons.get(1).getQueueTime()) {
                garrisonIndex = 0;
            } else if (garrisons.get(0).getQueueTime() == garrisons.get(1).getQueueTime()) {
                int random = Greenfoot.getRandomNumber(2);
                garrisonIndex = random;
            } else {
                garrisonIndex = 1;
            }
        } else {
            return;
        }
        
        //Determine which troop to spawn and if the chateau has enought money to create it
        int random = Greenfoot.getRandomNumber(10);
        
        if (money >= WarWorld.SERPENTINE_COST && (random == 0 || random == 1)) {
            garrisons.get(garrisonIndex).queueTroop(0);
            money -= WarWorld.SERPENTINE_COST;
        } else if (money >= WarWorld.DRAGON_COST && random == 2) {
            garrisons.get(garrisonIndex).queueTroop(1);
            money -= WarWorld.DRAGON_COST;
        }
    }
    
    /**
     * Called when a new establishment is added to the ArrayList of the chateau.
     * 
     * @param building                  The building to be added to the ArrayList
     * @param establishmentType         Type of establishmnet to be added (1 - treasury, 2 - garrison)
     */
    public void addEstablishment(Establishment building, int establishmentType)
    {
        if (establishmentType == 1) {
            treasuries.add((Treasury)building);
        } else {
            garrisons.add((Garrison)building);
        }
    }
    
    /**
     * Plays sound of a building upgrading.
     */
    private void playSound()
    {
        //Play the sound, increment the index and reset the index if necessary
        levelUpSounds[levelUpSoundsIndex].play();
        levelUpSoundsIndex++;
        if (levelUpSoundsIndex > levelUpSounds.length) {
            levelUpSoundsIndex = 0;
        }
    }
    
    /**
     * Called when the chateau's HP reaches below 0, adds the rubble, removes all the troops and establishments, and removes this chateau.
     */
    protected void explode()
    {
        //Play the sound effect for destruction
        destroySoundEffect.play();
        
        //Add the rubble at the current x and y coordinates
        getWorld().addObject(new Rubble(), getX(), getY());
        
        //Loop through all the troops, and remove them
        for (Troop t : troops) {
            t.death(true);
        }
        
        //Loop through all the treasuries, destroy them
        for (Treasury t : treasuries) {
            t.castleDestroyed();
        }
        
        //Loop through all the garrisons, destroy them
        for (Garrison g : garrisons) {
            g.castleDestroyed();
        }
        
        //Remove this object from the ArrayList of chateaus
        WarWorld.getChateaus().remove(this);
        
        //Castle is now destroyed
        isDestroyed = true;
        
        //Remove this object from the world
        getWorld().removeObject(this);
    }
    
    /**
     * Removes a troop from the ArrayList of troops.
     * 
     * @param t         The troop to be removed
     */
    public void removeTroop(Troop t)
    {
        troops.remove(t);
    }
    
    /**
     * Returns a boolean for whether or not the chateau is destroyed.
     * 
     * @return boolean          True if the chateau is destroyed, false if not
     */
    public boolean getIsDestroyed()
    {
        return isDestroyed;
    }
    
    /**
     * Sets the alliance of the chateau, as well as all other troops and establishments under its command.
     * 
     * @param alliance          The alliance the chateau should be set to (0 - gold, 1 - blue)
     */
    public void setAlliance(int alliance)
    {
        //Set this chateau's alliance
        this.alliance = alliance;
        
        //Set the chateau's image based on alliance
        if (alliance == 0) {
            setImage(goldChateau);
        } else {
            setImage(blueChateau);
        }
        
        //Change the alliance for each of the troops
        for (Troop t : troops) {
            t.setAlliance(alliance);
        }
        
        //Change the alliance for each of the treasuries
        for (Treasury t : treasuries) {
            t.setAlliance(alliance);
        }
        
        //Change the alliance for each of the garrisons
        for (Garrison g : garrisons) {
            g.setAlliance(alliance);
        }
    }
    
    /**
     * Removes a garrison from the ArrayList of garrisons.
     * 
     * @param g         The garrison to be removed from the ArrayList
     */
    public void removeGarrison(Garrison g)
    {
        garrisons.remove(g);
    }
    
    /**
     * Removes a treasury from the ArrayList of treasuries.
     * 
     * @param t         The treasury to be removed from the ArrayList
     */
    public void removeTreasury(Treasury t)
    {
        treasuries.remove(t);
    }
    
    /**
     * Called by the treasuries to add money to the chateau.
     * 
     * @param amount            The amount of money to be added
     */
    public void addMoney(int amount)
    {
        money += amount;
    }
    
    /**
     * Called by the garrisons to add a troop to the ArrayList of troops.
     * 
     * @param troop             The troop to be added
     */
    public void addTroop(Troop troop)
    {
        troops.add(troop);
    }
    
    /**
     * Getter method for the money variable, returns the amount of money.
     * 
     * @return int              The amount of money the chateau has
     */
    public int getMoney()
    {
        return money;
    }
    
    /**
     * Returns the name of the kingdom.
     * 
     * @return String           The name of the kingdom
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the ArrayList of troops.
     * 
     * @return ArrayList<Troop>         The ArrayList of troops under the kingdom
     */
    public ArrayList<Troop> getTroops()
    {
        return troops;
    }
    
    /**
     * Returns the ArrayList of treasuries.
     * 
     * @return ArrayList<Treasury>      The ArrayList of treasuries under the kingdom
     */
    public ArrayList<Treasury> getTreasuries()
    {
        return treasuries;
    }
    
    /**
     * Returns the ArrayList of garrisons.
     * 
     * @return ArrayList<Garrison>      The ArrayList of garrisons under the kingdom
     */
    public ArrayList<Garrison> getGarrisons()
    {
        return garrisons;
    }
}
