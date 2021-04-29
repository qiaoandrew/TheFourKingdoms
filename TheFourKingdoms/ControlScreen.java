import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>ControlScreen Class</h1>
 * 
 * The World where the user is able to change certain parameters that will affect the simulation.
 * This is done through the use of many buttons, accompanied by text labels to tell the user what
 * each button is for.
 * Also includes a cool background graphic and an intriguing soundtrack, further drawing in players.
 * 
 * @author Andrew Qiao
 * @version April 2021
 */
public class ControlScreen extends World
{
    //Declare text labels
    private TextLabel title;
    private TextLabel belzerg;
    private TextLabel leiden;
    private TextLabel marley;
    private TextLabel eldia;
    private TextLabel alliance;
    private TextLabel startMoney;
    
    //Declare text buttons
    private TextButton belzergAlliance;
    private TextButton leidenAlliance;
    private TextButton marleyAlliance;
    private TextButton eldiaAlliance;
    
    private TextButton belzergMoney1;
    private TextButton belzergMoney2;
    private TextButton belzergMoney3;
    
    private TextButton leidenMoney1;
    private TextButton leidenMoney2;
    private TextButton leidenMoney3;
    
    private TextButton marleyMoney1;
    private TextButton marleyMoney2;
    private TextButton marleyMoney3;
    
    private TextButton eldiaMoney1;
    private TextButton eldiaMoney2;
    private TextButton eldiaMoney3;
    
    private TextButton startButton;
    
    //Declare integers to keep track what alliance is chosen (0 - Gold, 1 - Blue)
    private int currentBelzergAlliance;
    private int currentLeidenAlliance;
    private int currentMarleyAlliance;
    private int currentEldiaAlliance;
    
    //Declare integers to keep track of the starting money for each kingdom
    private int belzergMoney;
    private int leidenMoney;
    private int marleyMoney;
    private int eldiaMoney;
    
    //Declare soundtrack
    private GreenfootSound appleSeed = new GreenfootSound("appleseed.mp3");
    
    /**
     * Constructor for ControlScreen, initializes all the labels and text buttons to add to the world. 
     */
    public ControlScreen()
    {    
        // Create a new world with 1280x800 cells with a cell size of 1x1 pixels
        super(1280, 800, 1); 
        
        //Initialize all the text labels and add them to the world
        title = new TextLabel(" SIMULATION CONTROLS ", 50, 10, new Color(7, 20, 33), new Color(253, 249, 127));
        addObject(title, 640, 80);
        
        belzerg = new TextLabel(" Belzerg ", 40, 5, new Color(7, 20, 33), new Color(216, 126, 126));
        addObject(belzerg, 425, 200);
        
        leiden = new TextLabel(" Leiden ", 40, 5, new Color(7, 20, 33), new Color(185, 209, 184));
        addObject(leiden, 675, 200);
        
        marley = new TextLabel(" Marley ", 40, 5, new Color(7, 20, 33), new Color(208, 216, 227));
        addObject(marley, 925, 200);
        
        eldia = new TextLabel(" Eldia ", 40, 5, new Color(7, 20, 33), new Color(236, 227, 172));
        addObject(eldia, 1175, 200);
        
        alliance = new TextLabel(" Alliance ", 45, 7, new Color(7, 20, 33), new Color(253, 249, 127));
        addObject(alliance, 150, 325);
        
        startMoney = new TextLabel("Starting Money", 45, 7, new Color(7, 20, 33), new Color(253, 249, 127));
        addObject(startMoney, 150, 425);
        
        //Initialize all the buttons and add them to the world
        startButton = new TextButton("START!", 50, new Color(7, 20, 33), new Color(253, 249, 127), 10);
        addObject(startButton, 640, 700);
        
        belzergAlliance = new TextButton("GOLD", 30, new Color(253, 249, 127), new Color(7, 20, 33), 5);
        addObject(belzergAlliance, 425, 325);
        
        leidenAlliance = new TextButton("BLUE", 30, new Color(115, 194, 251), new Color(7, 20, 33), 5);
        addObject(leidenAlliance, 675, 325);
        
        marleyAlliance = new TextButton("GOLD", 30, new Color(253, 249, 127), new Color(7, 20, 33), 5);
        addObject(marleyAlliance, 925, 325);
        
        eldiaAlliance = new TextButton("BLUE", 30, new Color(115, 194, 251), new Color(7, 20, 33), 5);
        addObject(eldiaAlliance, 1175, 325);
        
        belzergMoney1 = new TextButton("$00000", 30, new Color(7, 20, 33), new Color(216, 126, 126), 5);
        addObject(belzergMoney1, 425, 425); 
        
        belzergMoney2 = new TextButton("$05000", 30, new Color(7, 20, 33), new Color(216, 126, 126), 5);
        belzergMoney2.updateMe();
        addObject(belzergMoney2, 425, 500); 
        
        belzergMoney3 = new TextButton("$10000", 30, new Color(7, 20, 33), new Color(216, 126, 126), 5);
        addObject(belzergMoney3, 425, 575); 
        
        leidenMoney1 = new TextButton("$00000", 30, new Color(7, 20, 33), new Color(185, 209, 184), 5);
        addObject(leidenMoney1, 675, 425); 
        
        leidenMoney2 = new TextButton("$05000", 30, new Color(7, 20, 33), new Color(185, 209, 184), 5);
        leidenMoney2.updateMe();
        addObject(leidenMoney2, 675, 500); 
        
        leidenMoney3 = new TextButton("$10000", 30, new Color(7, 20, 33), new Color(185, 209, 184), 5);
        addObject(leidenMoney3, 675, 575); 
        
        marleyMoney1 = new TextButton("$00000", 30, new Color(7, 20, 33), new Color(208, 216, 227), 5);
        addObject(marleyMoney1, 925, 425); 
        
        marleyMoney2 = new TextButton("$05000", 30, new Color(7, 20, 33), new Color(208, 216, 227), 5);
        marleyMoney2.updateMe();
        addObject(marleyMoney2, 925, 500); 
        
        marleyMoney3 = new TextButton("$10000", 30, new Color(7, 20, 33), new Color(208, 216, 227), 5);
        addObject(marleyMoney3, 925, 575);
        
        eldiaMoney1 = new TextButton("$00000", 30, new Color(7, 20, 33), new Color(236, 227, 172), 5);
        addObject(eldiaMoney1, 1175, 425); 
        
        eldiaMoney2 = new TextButton("$05000", 30, new Color(7, 20, 33), new Color(236, 227, 172), 5);
        eldiaMoney2.updateMe();
        addObject(eldiaMoney2, 1175, 500); 
        
        eldiaMoney3 = new TextButton("$10000", 30, new Color(7, 20, 33), new Color(236, 227, 172), 5);
        addObject(eldiaMoney3, 1175, 575); 
        
        //Initialize the starting values of the variables
        currentBelzergAlliance = 0;
        currentLeidenAlliance = 1;
        currentMarleyAlliance = 0;
        currentEldiaAlliance = 1;
        
        belzergMoney = 5000;
        leidenMoney = 5000;
        marleyMoney = 5000;
        eldiaMoney = 5000;
        
        //Set the volume of the soundtrack and start playing it
        appleSeed.setVolume(30);
        appleSeed.playLoop();
    }
    
    /**
     * Called every act, checks if the user has clicked any button, and acts accordingly.
     */
    public void act()
    {
        //Checks if the user has changed the alliance of Belzerg and acts accordingly
        if (Greenfoot.mousePressed(belzergAlliance)) {
            if (currentBelzergAlliance == 0) {
                belzergAlliance.updateMe("BLUE", new Color(7, 20, 33), new Color(115, 194, 251));
                currentBelzergAlliance = 1;
            } else {
                belzergAlliance.updateMe("GOLD", new Color(253, 249, 127), new Color(7, 20, 33));
                currentBelzergAlliance = 0;
            }
        }
        
        //Checks if the user has changed the alliance of Leiden and acts accordingly
        if (Greenfoot.mousePressed(leidenAlliance)) {
            if (currentLeidenAlliance == 0) {
                leidenAlliance.updateMe("BLUE", new Color(115, 194, 251), new Color(7, 20, 33));
                currentLeidenAlliance = 1;
            } else {
                leidenAlliance.updateMe("GOLD", new Color(7, 20, 33), new Color(253, 249, 127));
                currentLeidenAlliance = 0;
            }
        }
        
        //Checks if the user has changed the alliance of Marley and acts accordingly
        if (Greenfoot.mousePressed(marleyAlliance)) {
            if (currentMarleyAlliance == 0) {
                marleyAlliance.updateMe("BLUE", new Color(7, 20, 33), new Color(115, 194, 251));
                currentMarleyAlliance = 1;
            } else {
                marleyAlliance.updateMe("GOLD", new Color(253, 249, 127), new Color(7, 20, 33));
                currentMarleyAlliance = 0;
            }
        }
        
        //Checks if the user has changed the alliance of Eldia and acts accordingly
        if (Greenfoot.mousePressed(eldiaAlliance)) {
            if (currentEldiaAlliance == 0) {
                eldiaAlliance.updateMe("BLUE", new Color(115, 194, 251), new Color(7, 20, 33));
                currentEldiaAlliance = 1;
            } else {
                eldiaAlliance.updateMe("GOLD", new Color(7, 20, 33), new Color(253, 249, 127));
                currentEldiaAlliance = 0;
            }
        }
        
        //Disable the text button if all the kingdoms are on the same alliance
        if (currentBelzergAlliance == currentLeidenAlliance && currentBelzergAlliance == currentMarleyAlliance && currentBelzergAlliance == currentEldiaAlliance) {
            startButton.setDisabled(true);
        } else {
            startButton.setDisabled(false);
        }
        
        //Check if the user has changed the starting money of Belzerg kingdom, and act accordingly
        if (Greenfoot.mousePressed(belzergMoney1)) {
            if (belzergMoney == 5000) {
                belzergMoney = 0;
                belzergMoney1.updateMe();
                belzergMoney2.updateMe();
            } else if (belzergMoney == 10000) {
                belzergMoney = 0;
                belzergMoney3.updateMe();
                belzergMoney1.updateMe();
            }
        }
        
        if (Greenfoot.mousePressed(belzergMoney2)) {
            if (belzergMoney == 0) {
                belzergMoney = 5000;
                belzergMoney1.updateMe();
                belzergMoney2.updateMe();
            } else if (belzergMoney == 10000) {
                belzergMoney = 5000;
                belzergMoney3.updateMe();
                belzergMoney2.updateMe();
            }
        }
        
        if (Greenfoot.mousePressed(belzergMoney3)) {
            if (belzergMoney == 0) {
                belzergMoney = 10000;
                belzergMoney3.updateMe();
                belzergMoney1.updateMe();
            } else if (belzergMoney == 5000) {
                belzergMoney = 10000;
                belzergMoney3.updateMe();
                belzergMoney2.updateMe();
            }
        }
        
        //Check if the user has changed the starting money of Leiden kingdom, and act accordingly
        if (Greenfoot.mousePressed(leidenMoney1)) {
            if (leidenMoney == 5000) {
                leidenMoney = 0;
                leidenMoney1.updateMe();
                leidenMoney2.updateMe();
            } else if (leidenMoney == 10000) {
                leidenMoney = 0;
                leidenMoney3.updateMe();
                leidenMoney1.updateMe();
            }
        }
        
        if (Greenfoot.mousePressed(leidenMoney2)) {
            if (leidenMoney == 0) {
                leidenMoney = 5000;
                leidenMoney1.updateMe();
                leidenMoney2.updateMe();
            } else if (leidenMoney == 10000) {
                leidenMoney = 5000;
                leidenMoney3.updateMe();
                leidenMoney2.updateMe();
            }
        }
        
        if (Greenfoot.mousePressed(leidenMoney3)) {
            if (leidenMoney == 0) {
                leidenMoney = 10000;
                leidenMoney3.updateMe();
                leidenMoney1.updateMe();
            } else if (leidenMoney == 5000) {
                leidenMoney = 10000;
                leidenMoney3.updateMe();
                leidenMoney2.updateMe();
            }
        }
        
        //Check if the user has changed the starting money of Marley kingdom, and act accordingly
        if (Greenfoot.mousePressed(marleyMoney1)) {
            if (marleyMoney == 5000) {
                marleyMoney = 0;
                marleyMoney1.updateMe();
                marleyMoney2.updateMe();
            } else if (marleyMoney == 10000) {
                marleyMoney = 0;
                marleyMoney3.updateMe();
                marleyMoney1.updateMe();
            }
        }
        
        if (Greenfoot.mousePressed(marleyMoney2)) {
            if (marleyMoney == 0) {
                marleyMoney = 5000;
                marleyMoney1.updateMe();
                marleyMoney2.updateMe();
            } else if (marleyMoney == 10000) {
                marleyMoney = 5000;
                marleyMoney3.updateMe();
                marleyMoney2.updateMe();
            }
        }
        
        if (Greenfoot.mousePressed(marleyMoney3)) {
            if (marleyMoney == 0) {
                marleyMoney = 10000;
                marleyMoney3.updateMe();
                marleyMoney1.updateMe();
            } else if (marleyMoney == 5000) {
                marleyMoney = 10000;
                marleyMoney3.updateMe();
                marleyMoney2.updateMe();
            }
        }
        
        //Check if the user has changed the starting money of Eldia kingdom, and act accordingly
        if (Greenfoot.mousePressed(eldiaMoney1)) {
            if (eldiaMoney == 5000) {
                eldiaMoney = 0;
                eldiaMoney1.updateMe();
                eldiaMoney2.updateMe();
            } else if (eldiaMoney == 10000) {
                eldiaMoney = 0;
                eldiaMoney3.updateMe();
                eldiaMoney1.updateMe();
            }
        }
        
        if (Greenfoot.mousePressed(eldiaMoney2)) {
            if (eldiaMoney == 0) {
                eldiaMoney = 5000;
                eldiaMoney1.updateMe();
                eldiaMoney2.updateMe();
            } else if (eldiaMoney == 10000) {
                eldiaMoney = 5000;
                eldiaMoney3.updateMe();
                eldiaMoney2.updateMe();
            }
        }
        
        if (Greenfoot.mousePressed(eldiaMoney3)) {
            if (eldiaMoney == 0) {
                eldiaMoney = 10000;
                eldiaMoney3.updateMe();
                eldiaMoney1.updateMe();
            } else if (eldiaMoney == 5000) {
                eldiaMoney = 10000;
                eldiaMoney3.updateMe();
                eldiaMoney2.updateMe();
            }
        }
        
        //Check if the user has clicked the start button, and if it is not disabled, start the simulation
        if (Greenfoot.mousePressed(startButton)) {
            if (!startButton.getIsDisabled()) {
                appleSeed.stop();
                WarWorld warWorld = new WarWorld(currentBelzergAlliance, currentLeidenAlliance, currentMarleyAlliance, currentEldiaAlliance, belzergMoney, leidenMoney, marleyMoney, eldiaMoney);
                Greenfoot.setWorld(warWorld);
            }
        }
    }
}
