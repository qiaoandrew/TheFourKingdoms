import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util

/**
 * <h1>WarWorld Class</h1>
 * 
 * <p>The year is 500 BCE. The Great Dragon War ended 3000 years ago, bringing peace to the Four Kingdoms. But now, corruption has brought that balance to shambles.
 * The Four Kingdoms are now in full out war. Come together and witness this epic showdown!</p>
 * 
 * <h3>Description:</h3>
 * <p>WarWorld is where the simulation takes place. It includes four seperate corners, each home to a kingdom. Each kingdom starts off with a Chateau, two Treasuries
 * and two Garrisons. Throughout the simulation, treasuries produce money and Garrisons produce troops. The money produced by the treasuries goes towards creating troops,
 * and upgrading buildings. An upgraded treasury produces more money per cycle, and an upgraded garrison produces stronger troops. Their are two types of troops, the 
 * Dragon and the Serpentine. The Serpentine targets establishments, but may hit any of the troops in its way. It attacks through a hard-hitting cannonball, but takes 
 * significant time to reload. The Dragon, on the other hand, targets whatever is the closest, both estblishments and troops. It attacks through quickly fired, fireballs.
 * Although the fireballs do little damage, the firerate allows for the Dragon to have a higher damage per act (DPA). During the simulation, the kingdoms will be divided
 * into two alliances, blue or gold. Once an alliance is wiped out, the remaining kingdoms will be divided up into the same two alliance until there is a single kingdom
 * standing, the winner. While the simulation takes place, there will be a timer displaying the time in minutes and seconds that has passed, along with scoreboards for each
 * of the kingdoms, displaying their kingdom name, alliance, money, current troops, and current establishments.</p>
 * <p><b>NOTE:</b> For more information about the simulation, consult the important elements section below.</p>
 * 
 * <h3>Important elements:</h3>
 * <ul>
 *      <li>Five seperate soundtracks that play throughout the simulation for each of the stages. Consult the credits section below for their names and credits</li>
 *      <li>A starting screen that greets players with powerful music and attractive graphics</li>
 *      <li>A story screen explaining to the players the reasoning behind the conflict, with some more powerful music, allowing players to submerge themselves mentally to the simulation</li>
 *      <li>Control screen that allows players to choose the starting alliances and starting money for each kingdom</li>
 *      <li>Powerful music that plays during the simulation, keeping players engaged</li>
 *      <li>Exciting victory music that plays after the simulation, along with a victory screen that displays which kingdom won and the time it took</li>
 *      <li>A cool map designed by ourselves using a parchment background, the outlines of fantasy maps, colored parchment, and parchment assets</li>
 *      <li>Two types of troops, Dragon and Serpentine</li>
 *          <ul>
 *              <li>Dragon targets both establishments and troops, shoots out fireballs, moves faster, and does overall mord damage per act</li>
 *              <li>Serpentine targets only establishments, but may hit troops in its way, shoots cannonballs, moves slower, does less damage per act</li>
 *              <li>Dragon shoots fireballs that do much less damage, but due to the high rate of fire, does more DPA</li>
 *              <li>Serpentine shoots hard hitting fireballs that do much more damange, but due to the low rate of fire, does less DPA</li>
 *              <li>Dragon also does limited splash damage, whereas serpentine is single target</li>
 *          </ul>
 *      <li>Three types of establishments, Chateau, Treasury, and Garrison</li>
 *          <ul>
 *              <li>Chateau is the command centre of the kingdom and determines when to queue troops or upgrade</li>
 *              <li>Treasury creates money to upgrade establishments and create troops</li>
 *              <li>Garrison creates troops</li>
 *          </ul>
 *      <li>The Treasury and Garrison establishments are able to level up</li>
 *          <ul>
 *              <li>Treasuries level up to produce more money each cycle (every 2 seconds)</li>
 *              <li>Garrisons level up to produce troops with more HP and damage</li>
 *          </ul>
 *      <li>Two types of ammunition, Fireball and Cannonball</li>
 *          <ul>
 *              <li>The cannonball does a significant amount of damange and is single target</li>
 *              <li>A single fireball does little damange and does splash damage</li>
 *          </ul>
 *      <li>After buildings are destroyed, there is a <strong>very cool explosion animation</strong> that plays, made up of <strong>25 different frames</strong></li>
 *      <ul>
 *          <li>The animation was created by taking a transparent gif of an explosion and seperating it into seperate frames</li>
 *          <li>These frames are played through, changing every 4 acts, giving the effect of a super clean animation</li>
 *      </ul>
 *      <li>Sound effects for the destruction of a building, cannonball firing, fireball firing, establishment upgrades, serpentines being destroyed, and dragon's losing all their health (We were going to add more, but it sounded too hectic)</li>
 *      <li>Individual scoreboards for each of the kingdoms, can be toggled on or off using the "Toggle Scoreboards" button at the top of the simulation</li>
 *      <li>A time keeper to keep track of the minutes and seconds that have passed since the simulation started</li>
 *      <li>A health bar for each of the troops and establishments to display their health</li>
 *      <li>A timer for the garrisons to display how much time is remaining before the next troops is created</li>
 *      <li>A disappearing skull that is displayed once a troop dies</li>
 * </ul>
 * 
 * <h3>Credits:</h3>
 * <ul>
 *      <li>Ashes on the Fire Soundtrack (plays during the WarWorld): Kohta Yamamoto</li>
 *      <li>Attack on Titan Soundtrack (plays during the StoryScreen): Hiroyuki Sawano</li>
 *      <li>Bird in a Cage Soundtrack (plays during the StartScreen): Hiroyuki Sawano</li>
 *      <li>Wings of Freedom Soundtrack (plays during the EndScreen): Hiroyuki Sawano</li>
 *      <li>Apple Seed Soundtrack (plays during the ControlScreen): Hiroyuki Sawano</li>
 *      <li>Clash of Clans Sound Effects: Supercell Oy</li>
 *      <li>Explosion sound effect: Renato Ottlakan</li>
 *      <li>Cannon image: imarvintpa</li>
 *      <li>Castle image: Serge Billault</li>
 *      <li>Dragon image: DraconomiconArt</li>
 *      <li>Parchment image: Wikimedia Commons</li>
 *      <li>Parchment assets: GameDev Market</li>
 *      <li>Treasury and Garrison images: Supercell Oy</li>
 *      <li>Explosion GIF: Thollador</li>
 *      <li>Island images: Clipart Key, Wikimedia comons, Animupfel</li>
 *      <li>Fireball: Bethesda Game Studios</li>
 *      <li>Fantasy World backgrounds: WallpaperAccess, Wallpaper Cave</li>
 *      <li>StatBar class and zeroAdder() method: Mr. Cohen</li>
 *      <li>SimpleTimer class: Neil Brown</li>
 * </ul>
 * 
 * <h3>Next Steps (Possible Improvements):</h3>
 * <ul>
 *      <li>Add more troop subclasses</li>
 *      <li>Add more establishment subclasses</li>
 *      <li>Add more user controls on the ControlScreen for the user to experiment with</li>
 * </ul>
 * 
 * @author Andrew Qiao, Ryo Minakami, Brenden Chan, James Li
 * @version April 2021
 */
public class WarWorld extends World
{
    //Declare constants
    
    //Serpentine's properties
    public static final int SERPENTINE_COST = 6000;
    public static final int SERPENTINE_TIME = 7000;
    public static final int SERPENTINE_HP = 1000;
    public static final int SERPENTINE_SPEED = 1;
    public static final int SERPENTINE_RELOAD = 300;
    
    //Dragon's properties
    public static final int DRAGON_COST = 7000;
    public static final int DRAGON_TIME = 8000;
    public static final int DRAGON_HP = 1300;
    public static final int DRAGON_SPEED = 2;
    public static final int DRAGON_RELOAD = 5;
    
    //Cannonball's properties
    public static final int CANNONBALL_SPEED = 3;
    public static final int CANNONBALL_DAMAGE = 200;
    
    //Fireball's properties
    public static final int FIREBALL_SPEED = 4;
    public static final int FIREBALL_DAMAGE = 5;
    
    //Treasury's costs
    public static final int TREASURY_LEVEL_2_COST = 11000;
    public static final int TREASURY_LEVEL_3_COST = 15000;
    
    //Garrison's costs
    public static final int GARRISON_LEVEL_2_COST = 11000;
    public static final int GARRISON_LEVEL_3_COST = 15000;
    
    //Hit points of estbalishments
    public static final int TREASURY_HP = 3000;
    public static final int GARRISON_HP = 5000;
    public static final int CHATEAU_HP = 20000;
    
    //Coordinates for objects
    public static final int[][] CHATEAU_COORDINATES = {{50, 50}, {1230, 50}, {50, 750}, {1230, 750}};
    public static final int[][] GARRISON_COORDINATES = {{299, 43}, {184, 142}, {959, 51}, {1056, 266}, {320, 577}, {260, 745}, {979, 750}, {1000, 572}};
    public static final int[][] TREASURY_COORDINATES = {{314, 232}, {375, 118}, {950, 221}, {1175, 138}, {200, 638}, {340, 650}, {1151, 614}, {920, 653}};
    public static final int[][] SCOREBOARD_COORDINATES = {{110, 325}, {1170, 325}, {110, 520}, {1170, 520}};
    
    //Ranges
    public static final double ATTACK_RANGE = 100;
    public static final double FIREBALL_RANGE = 300;
    
    //Array containing alliances
    private int[] alliances = new int[4];
    
    //Number of kingdoms current alive
    private int currentKingdomsAlive;
    
    //Declare chateaus
    private Chateau belzergChateau;
    private Chateau leidenChateau;
    private Chateau eldiaChateau;
    private Chateau marleyChateau;
    
    //ArrayList of chateaus
    private static ArrayList<Chateau> chateaus;
    
    //Declare SimpleTimer to keep track of time
    private SimpleTimer timer;
    
    //Declare TimeKeeper that displays the time
    private TimeKeeper timeKeeper;
    
    //Declare scoreboards
    private Scoreboard belzergScoreboard;
    private Scoreboard leidenScoreboard;
    private Scoreboard eldiaScoreboard;
    private Scoreboard marleyScoreboard;
    
    //Declare button that allows users to hid scoreboard
    private TextButton hideScoreboardButton;
    
    //Declare soundtrack
    private GreenfootSound ashesOnTheFire = new GreenfootSound("ashesonthefire.mp3");
    
    //Declare array for starting money
    private int[] startingMoney = new int[4];
    
    /**
     * Constructor for WarWorld, where the simulation takes place, initializes each of the chateaus, as well as the objects that they contain.
     * Also takes in parameters for the starting values of alliance and money for each kingdom, as decided by the user on the ControlScreen.
     * 
     * @param belzergAlliance               Starting alliance for Belzerg kingdom (0 - Gold, 1 - Blue)
     * @param leidenAlliance                Starting alliance for Leiden kingdom (0 - Gold, 1 - Blue)
     * @param marleyAlliance                Starting alliance for Marley kingdom (0 - Gold, 1 - Blue)
     * @param eldiaAlliance                 Starting alliance for Eldia kingdom (0 - Gold, 1 - Blue)
     * @param belzergMoney                  Starting money for Belzerg kingdom
     * @param leidenMoney                   Starting money for Leiden kingdom
     * @param marleyMoney                   Starting money for Marley kingdom
     * @param eldiaMoney                    Starting money for Eldia kingdom
     */
    public WarWorld(int belzergAlliance, int leidenAlliance, int marleyAlliance, int eldiaAlliance, int belzergMoney, int leidenMoney, int marleyMoney, int eldiaMoney)
    {    
        // Create a new world with 1280x800 cells with a cell size of 1x1 pixels.
        super(1280, 800, 1); 
        
        //Initialize ArrayList of chateaus
        chateaus = new ArrayList<Chateau>();
        
        //Determine the alliances of each chateau
        alliances[0] = belzergAlliance;
        alliances[1] = leidenAlliance;
        alliances[2] = marleyAlliance;
        alliances[3] = eldiaAlliance;
        
        //Initialize starting amounts of money
        startingMoney[0] = belzergMoney;
        startingMoney[1] = leidenMoney;
        startingMoney[2] = marleyMoney;
        startingMoney[3] = eldiaMoney;
        
        //If they are all equal, change one chateau's alliance
        if (alliances[0] == alliances[1] && alliances[0] == alliances[2] && alliances[0] == alliances[3]) {
            int random = Greenfoot.getRandomNumber(4);
            if (alliances[0] == 0) {
                alliances[random] = 1;
            } else {
                alliances[random] = 0;
            }
        }
        
        //Initialize Belzerg's chateau and add it to the world
        belzergChateau = new Chateau(CHATEAU_HP, alliances[0], "Belzerg", startingMoney[0]);
        addObject(belzergChateau, CHATEAU_COORDINATES[0][0], CHATEAU_COORDINATES[0][1]);
        
        //Call the method to initialize the chateau's establishments
        initiateBuildings(belzergChateau, 0);
        
        //Add the chateau to the ArrayList of chateaus
        chateaus.add(belzergChateau);
        
        //Initalize its scoreboard and add it to the world
        if (belzergChateau.getAlliance() == 0) {
            belzergScoreboard = new Scoreboard(belzergChateau.getName(), 20, 15, belzergChateau.getMoney(), belzergChateau.getTroops().size(), belzergChateau.getTreasuries().size() + belzergChateau.getGarrisons().size() + 1, 10, 5, 220, new Color(7, 20, 33), new Color(253, 249, 127));
        } else {
            belzergScoreboard = new Scoreboard(belzergChateau.getName(), 20, 15, belzergChateau.getMoney(), belzergChateau.getTroops().size(), belzergChateau.getTreasuries().size() + belzergChateau.getGarrisons().size() + 1, 10, 5, 220, new Color(7, 20, 33), new Color(115, 194, 251));
        }
        addObject(belzergScoreboard, WarWorld.SCOREBOARD_COORDINATES[0][0], WarWorld.SCOREBOARD_COORDINATES[0][1]);
        
        //Initialize Leiden's chateau and add it to the world
        leidenChateau = new Chateau(CHATEAU_HP, alliances[1], "Leiden", startingMoney[1]);
        addObject(leidenChateau, CHATEAU_COORDINATES[1][0], CHATEAU_COORDINATES[1][1]);
        
        //Call the method to initialize the chateau's establishments
        initiateBuildings(leidenChateau, 1);
        
        //Add the chateau to the ArrayList of chateaus
        chateaus.add(leidenChateau);
        
        //Initalize its scoreboard and add it to the world
        if (leidenChateau.getAlliance() == 0) {
            leidenScoreboard = new Scoreboard(leidenChateau.getName(), 20, 15, leidenChateau.getMoney(), leidenChateau.getTroops().size(), leidenChateau.getTreasuries().size() + leidenChateau.getGarrisons().size() + 1, 10, 5, 220, new Color(7, 20, 33), new Color(253, 249, 127));
        } else {
            leidenScoreboard = new Scoreboard(leidenChateau.getName(), 20, 15, leidenChateau.getMoney(), leidenChateau.getTroops().size(), leidenChateau.getTreasuries().size() + leidenChateau.getGarrisons().size() + 1, 10, 5, 220, new Color(7, 20, 33), new Color(115, 194, 251));
        }
        addObject(leidenScoreboard, WarWorld.SCOREBOARD_COORDINATES[1][0], WarWorld.SCOREBOARD_COORDINATES[1][1]);
        
        //Initialize Marley's chateau and add it to the world
        marleyChateau = new Chateau(CHATEAU_HP, alliances[2], "Marley", startingMoney[2]);
        addObject(marleyChateau, CHATEAU_COORDINATES[2][0], CHATEAU_COORDINATES[2][1]);
        
        //Call the method to initialize the chateau's establishments
        initiateBuildings(marleyChateau, 2);
        
        //Add the chateau to the ArrayList of chateaus
        chateaus.add(marleyChateau);
        
        //Initalize its scoreboard and add it to the world
        if (marleyChateau.getAlliance() == 0) {
            marleyScoreboard = new Scoreboard(marleyChateau.getName(), 20, 15, marleyChateau.getMoney(), marleyChateau.getTroops().size(), marleyChateau.getTreasuries().size() + marleyChateau.getGarrisons().size() + 1, 10, 5, 220, new Color(7, 20, 33), new Color(253, 249, 127));
        } else {
            marleyScoreboard = new Scoreboard(marleyChateau.getName(), 20, 15, marleyChateau.getMoney(), marleyChateau.getTroops().size(), marleyChateau.getTreasuries().size() + marleyChateau.getGarrisons().size() + 1, 10, 5, 220, new Color(7, 20, 33), new Color(115, 194, 251));
        }
        addObject(marleyScoreboard, WarWorld.SCOREBOARD_COORDINATES[2][0], WarWorld.SCOREBOARD_COORDINATES[2][1]);
        
        //Initialize Eldia's chateau and add it to the world
        eldiaChateau = new Chateau(CHATEAU_HP, alliances[3], "Eldia", startingMoney[3]);
        addObject(eldiaChateau, CHATEAU_COORDINATES[3][0], CHATEAU_COORDINATES[3][1]);
        
        //Call the method to initialize the chateau's establishments
        initiateBuildings(eldiaChateau, 3);
        
        //Add the chateau to the ArrayList of chateaus
        chateaus.add(eldiaChateau);
        
        //Initalize its scoreboard and add it to the world
        if (eldiaChateau.getAlliance() == 0) {
            eldiaScoreboard = new Scoreboard(eldiaChateau.getName(), 20, 15, eldiaChateau.getMoney(), eldiaChateau.getTroops().size(), eldiaChateau.getTreasuries().size() + eldiaChateau.getGarrisons().size() + 1, 10, 5, 220, new Color(7, 20, 33), new Color(253, 249, 127));
        } else {
            eldiaScoreboard = new Scoreboard(eldiaChateau.getName(), 20, 15, eldiaChateau.getMoney(), eldiaChateau.getTroops().size(), eldiaChateau.getTreasuries().size() + eldiaChateau.getGarrisons().size() + 1, 10, 5, 220, new Color(7, 20, 33), new Color(115, 194, 251));
        }
        addObject(eldiaScoreboard, WarWorld.SCOREBOARD_COORDINATES[3][0], WarWorld.SCOREBOARD_COORDINATES[3][1]);
        
        //Set the paint order (which objects will be on top of which)
        setPaintOrder(TimeKeeper.class, Scoreboard.class, TextButton.class, TroopDeath.class, StatBar.class, Timer.class, Dragon.class, Garrison.class, Serpentine.class, Ammunition.class, Treasury.class, Rubble.class, WarWorld.class);
        
        //Initialize the variable for the current number of kingdoms alive
        currentKingdomsAlive = 4;
        
        //Initialize the SimpleTimer and mark the current time
        timer = new SimpleTimer();
        timer.mark();
        
        //Initialize and add the TimeKeeper to the world
        timeKeeper = new TimeKeeper(0, 40, 5, new Color(7, 20, 33), new Color(253, 249, 127), 220);
        addObject(timeKeeper, 640, 100);
        
        //Initialize and add the hid scoreboard button to the world
        hideScoreboardButton = new TextButton("Toggle Scoreboards", 20, new Color(7, 20, 33), new Color(253, 249, 127), 10);
        addObject(hideScoreboardButton, 640, 40);
        
        //Decrease the volume of the soundtrack
        ashesOnTheFire.setVolume(25);
        //Start looping the soundtrack
        ashesOnTheFire.playLoop();
    }
    
    /**
     * Called every act, checks if any kingdoms have fallen and if so, changes the current alliances accordingly, 
     * also updates scoreboards and keeps track of if the user clicked the hide scoreboard button.
     */
    public void act()
    {
        //Check if the number of kingdoms have decreased
        if (chateaus.size() == 1) {
            //If there is one kingdom left, initalize the ending screen and stop playing the soundtrack
            EndScreen endWorld = new EndScreen(chateaus.get(0).getName(), timer.millisElapsed());
            Greenfoot.setWorld(endWorld);
            ashesOnTheFire.stop();
        } else if (chateaus.size() < currentKingdomsAlive) {
            //Otherwise, if the number of kingdoms have decreased, change alliances if needed
            currentKingdomsAlive = chateaus.size();
            if (chateaus.size() == 3) {
                if (chateaus.get(0).getAlliance() == chateaus.get(1).getAlliance() && chateaus.get(0).getAlliance() == chateaus.get(2).getAlliance()) {
                    int random = Greenfoot.getRandomNumber(3);
                    if (chateaus.get(0).getAlliance() == 0) {
                        chateaus.get(random).setAlliance(1);
                    } else {
                        chateaus.get(random).setAlliance(0);
                    }
                }
            } else if (chateaus.size() == 2) {
                if (chateaus.get(0).getAlliance() == chateaus.get(1).getAlliance()) {
                    if (chateaus.get(0).getAlliance() == 0) {
                        chateaus.get(0).setAlliance(1);
                    } else {
                        chateaus.get(0).setAlliance(0);
                    }
                }
            }
        }
        
        //Update the time keeper
        timeKeeper.update(timer.millisElapsed());
        
        //Update scoreboards and background colors for each of the kingdoms
        if (belzergChateau.getIsDestroyed()) {
            belzergScoreboard.update(0, 0, 0);
        } else {
            belzergScoreboard.update(belzergChateau.getMoney(), belzergChateau.getTroops().size(), belzergChateau.getTreasuries().size() + belzergChateau.getGarrisons().size() + 1);
        }
        
        if (belzergChateau.getAlliance() == 0) {
            belzergScoreboard.setBackgroundColor(new Color(253, 249, 127));
        } else {
            belzergScoreboard.setBackgroundColor(new Color(115, 194, 251));
        }
        
        if (leidenChateau.getIsDestroyed()) {
            leidenScoreboard.update(0, 0, 0);
        } else {
            leidenScoreboard.update(leidenChateau.getMoney(), leidenChateau.getTroops().size(), leidenChateau.getTreasuries().size() + leidenChateau.getGarrisons().size() + 1);
        }
        
        if (leidenChateau.getAlliance() == 0) {
            leidenScoreboard.setBackgroundColor(new Color(253, 249, 127));
        } else {
            leidenScoreboard.setBackgroundColor(new Color(115, 194, 251));
        }
        
        if (marleyChateau.getIsDestroyed()) {
            marleyScoreboard.update(0, 0, 0);
        } else {
            marleyScoreboard.update(marleyChateau.getMoney(), marleyChateau.getTroops().size(), marleyChateau.getTreasuries().size() + marleyChateau.getGarrisons().size() + 1);
        }
        
        if (marleyChateau.getAlliance() == 0) {
            marleyScoreboard.setBackgroundColor(new Color(253, 249, 127));
        } else {
            marleyScoreboard.setBackgroundColor(new Color(115, 194, 251));
        }
        
        if (eldiaChateau.getIsDestroyed()) {
            eldiaScoreboard.update(0, 0, 0);
        } else {
            eldiaScoreboard.update(eldiaChateau.getMoney(), eldiaChateau.getTroops().size(), eldiaChateau.getTreasuries().size() + eldiaChateau.getGarrisons().size() + 1);
        }
        
        if (eldiaChateau.getAlliance() == 0) {
            eldiaScoreboard.setBackgroundColor(new Color(253, 249, 127));
        } else {
            eldiaScoreboard.setBackgroundColor(new Color(115, 194, 251));
        }
        
        //Check if the user has pressed the toggle scoreboard button, if so, set transparency accordingly
        if (Greenfoot.mousePressed(hideScoreboardButton)) {
            if (hideScoreboardButton.getIsClicked()) {
                hideScoreboardButton.updateMe();
                belzergScoreboard.setTransparency(220);
                leidenScoreboard.setTransparency(220);
                marleyScoreboard.setTransparency(220);
                eldiaScoreboard.setTransparency(220);
            } else {
                hideScoreboardButton.updateMe();
                belzergScoreboard.setTransparency(0);
                leidenScoreboard.setTransparency(0);
                marleyScoreboard.setTransparency(0);
                eldiaScoreboard.setTransparency(0);
            }
        }
    }
    
    /**
     * Initiates the buildings of the chateau and adds them to the world.
     * 
     * @chateau         Which chateau the buildings will belong to
     * @kingdomIndex    The index of the kingdom
     */
    private void initiateBuildings(Chateau chateau, int kingdomIndex)
    {
        //Initalize the garrisons and treasuries
        Garrison garrison1 = new Garrison(GARRISON_HP, alliances[kingdomIndex], chateau);
        Garrison garrison2 = new Garrison(GARRISON_HP, alliances[kingdomIndex], chateau);
        Treasury treasury1 = new Treasury(TREASURY_HP, alliances[kingdomIndex], chateau);
        Treasury treasury2 = new Treasury(TREASURY_HP, alliances[kingdomIndex], chateau);
        
        //Call the method of the chateau to add them to their respective ArrayLists
        chateau.addEstablishment(garrison1, 2);
        chateau.addEstablishment(garrison2, 2);
        chateau.addEstablishment(treasury1, 1);
        chateau.addEstablishment(treasury2, 1);
        
        //Add the objects to the world
        addObject(garrison1, GARRISON_COORDINATES[kingdomIndex * 2][0], GARRISON_COORDINATES[kingdomIndex * 2][1]);
        addObject(garrison2, GARRISON_COORDINATES[kingdomIndex * 2 + 1][0], GARRISON_COORDINATES[kingdomIndex * 2 + 1][1]);
        addObject(treasury1, TREASURY_COORDINATES[kingdomIndex * 2][0], TREASURY_COORDINATES[kingdomIndex * 2][1]);
        addObject(treasury2, TREASURY_COORDINATES[kingdomIndex * 2 + 1][0], TREASURY_COORDINATES[kingdomIndex * 2 + 1][1]);
    }
    
    /**
     * Returns the ArrayList of chateaus.
     * 
     * @return ArrayList<Chateau>       ArrayList of chateaus
     */
    public static ArrayList<Chateau> getChateaus()
    {
        return chateaus;
    }
}
