import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * New and Improved Stat Bar (Formerly Health Bar). This stat bar can be set to follow
 * an Actor or stay in one place (see constructors). This stat bar may have customized colors,
 * can hide when at full, and can have a customized border. This class aims to be as flexible
 * as possible, allowing it to be simple to use for beginners (easy 0 or 2 parameter constructor)
 * while also highly flexible for those who want to provide more specific parameters (multiple bars
 * in custom colours and sizes with custom offsets and borders).
 * <p>
 * Implementation - If using multiple bars, all arrays must be the same size. To 
 * optimize the appearance choose a height such that:
 * <p><code> (height - (borderThickness * 2)) % numBars == 0  </code></p>
 * <p>In other words, after factoring out the border, the size should be evenly divisible by 
 * the number of bars, so that all bars end up the same size.</p>
 * <p><b>Version Notes:</b></p>
 * <ul>
 * <li>Now has a boolean to determine whether it will hide itself when Val is full.</li>
 * <li>Now has a set of constructors to allow simple and complex implementation.</li>
 * <li>2.1.0 --> Added a border feature, allows customization of thickness and colour</li>
 * </ul>
 * 
 * @author Jordan Cohen
 * @author Mr Cohen
 * @version 2.1.0 - 2020 rewrite
 */
public class StatBar extends Actor
{

    // Declare Instance Variables
    private int[] maxVal;
    private int[] currVal;
    private double currPercentVal;
    private int[] missingBarSize;
    private int[] filledBarSize;
    private boolean hideAtMax;
    private boolean hasBorder;

    // for multiple bars
    private int barCount;
    private int barHeight;

    // Declare Instance Images
    private GreenfootImage bar;
    private GreenfootImage blank;

    // Some constants - can be changed to suit size of related objects
    private int width;
    private int height;
    private int offset;
    private int borderThickness;

    // Declare Instance Objects
    private Actor target;

    // Declare some Color objects
    private Color[] filledColor;
    private Color[] missingColor;
    private Color borderColor;

    /**
     * Main constructor - A basic constructor that sets default values. Easy to use, not very flexible.
     */
    public StatBar()
    {
        this(100, 100, null, 48, 6, 36);
    }

    /**
     * A simple constructor - specify a single value (which will be treated as both current and max for the stat)
     * as well as an owner to follow. If you do not want this to follow an Actor, use null for the second parameter.
     * 
     * @param maxVal    The maximum value for this stat, which will also be the starting value for this stat
     * @param owner     The Actor to follow around. If you do not want to associate this with an Actor, provide
     *                  null instead.
     */
    public StatBar (int maxVal, Actor owner){
        this(maxVal, maxVal, owner, 48, 4, 36);
    }

    /**
     *  A simple constructor for a somewhat customized stat bar. If owner is null, just position this object where you want it and it wont move.
     *  If owner is not null, this object will follow the owner.
     *  
     *  @param  maxVal  the maximum value for this stat
     *  @param currVal  the starting value for this stat
     *  @param  owner   the Actor that this stat bar will follow (null for DONT FOLLOW). Can be changed to just an Actor if needed
     *  @param  width   the width of the stat bar
     *  @param height   the height of the stat bar
     *  @param offset   the y-offset for positioning this bar in relation to it's owner
     */
    public StatBar (int maxVal, int currVal, Actor owner, int width, int height, int offset){
        this (maxVal, currVal, owner, width, height, offset, Color.GREEN, Color.RED);
    }

    /**
     *  Similar to above, but with the ability to customize colors
     *  
     *  @param  maxVal  the maximum value for this stat
     *  @param currVal  the starting value for this stat
     *  @param  owner   the Actor that this stat bar will follow (null for DONT FOLLOW). Can be changed to just an Actor if needed
     *  @param  width   the width of the stat bar
     *  @param height   the height of the stat bar
     *  @param offset   the y-offset for positioning this bar in relation to it's owner
     *  @param filledColor  the color to be used to represent the current value
     *  @param missingColor the color to be used to represent the missing value
     */
    public StatBar (int maxVal,  int currVal, Actor owner, int width, int height, int offset, Color filledColor, Color missingColor){
        this (maxVal, currVal, owner, width, height, offset, filledColor, missingColor, true);
    }

    /**
     *  Similar to above, but with the ability to have the bar hide when full - for example if you don't want full health bars shown.
     *  
     *  @param  maxVal  the maximum value for this stat
     *  @param currVal  the starting value for this stat
     *  @param  owner   the Actor that this stat bar will follow (null for DONT FOLLOW). Can be changed to just an Actor if needed
     *  @param  width   the width of the stat bar
     *  @param height   the height of the stat bar
     *  @param offset   the y-offset for positioning this bar in relation to it's owner
     *  @param filledColor  the color to be used to represent the current value
     *  @param missingColor the color to be used to represent the missing value
     *  @param  hideAtMax   set to true to have this statBar hide itself when currVal == maxVal
     */
    public StatBar (int maxVal,  int currVal, Actor owner, int width, int height, int offset, Color filledColor, Color missingColor, boolean hideAtMax){
        this (maxVal, currVal, owner, width, height, offset, filledColor, missingColor, true, null, 0);
    }

    /**
     *  The most detailed constructor! Can specify a border including thickness and color.
     *  
     *  @param  maxVal  the maximum value for this stat
     *  @param currVal  the starting value for this stat
     *  @param  owner   the Actor that this stat bar will follow (null for DONT FOLLOW). Can be changed to just an Actor if needed
     *  @param  width   the width of the stat bar
     *  @param height   the height of the stat bar
     *  @param offset   the y-offset for positioning this bar in relation to it's owner
     *  @param filledColor  the color to be used to represent the current value
     *  @param missingColor the color to be used to represent the missing value
     *  @param  hideAtMax   set to true to have this statBar hide itself when currVal == maxVal
     *  @param borderColor  the Color of the border
     *  @param borderThickness  the thickness of the border. This value should be at least 1.
     */
    public StatBar (int maxVal,  int currVal, Actor owner, int width, int height, int offset, Color filledColor, Color missingColor, boolean hideAtMax, Color borderColor, int borderThickness){
        this (new int[]{maxVal}, new int[]{currVal}, owner, width, height, offset, new Color[] {filledColor}, new Color[] {missingColor}, hideAtMax, borderColor, borderThickness);

    }

    /**
     * The king of all StatBar constuctors!
     * 
     * Takes details for an array of bars, otherwise the same as above. Note that all arrays must be the same length.
     * 
     *  @param  maxVal[]  the maximum values for each stat
     *  @param currVal[]  the starting values for each stat
     *  @param  owner   the Actor that this stat bar will follow (null for DONT FOLLOW). Can be changed to just an Actor if needed
     *  @param  width   the width of the stat bar
     *  @param height   the height of the stat bar
     *  @param offset   the y-offset for positioning this bar in relation to it's owner
     *  @param filledColor[]  the colors to be used to represent the current values
     *  @param missingColor[] the colors to be used to represent the missing values
     *  @param  hideAtMax   set to true to have this statBar hide itself when currVal == maxVal
     *  @param borderColor  the Color of the border
     *  @param borderThickness  the thickness of the border. This value should be at least 1.
     */
    public StatBar (int maxVal[],  int currVal[], Actor owner, int width, int height, int offset, Color filledColor[], Color missingColor[], boolean hideAtMax, Color borderColor, int borderThickness){
        this.barCount = maxVal.length;
        this.barHeight = (height - (2* borderThickness))/barCount;

        this.target = (Actor)owner;

        this.width = width;
        this.height = height;
        this.offset = offset;
        this.hideAtMax = hideAtMax;

        this.maxVal = maxVal;
        this.currVal = currVal;
        this.filledColor = filledColor;
        this.missingColor = missingColor;

        bar = new GreenfootImage (width, height);
        blank = new GreenfootImage (1, 1);

        if (borderColor == null){
            borderThickness = 0;
        } else {
            hasBorder = true;
            this.borderColor = borderColor;
            this.borderThickness = borderThickness;
        }

        redraw();

    }

    /**
     * The only purpose of the act method is to follow the target Actor. If you'd rather control this yourself,
     * delete this act() method and call moveMe() directly whenever your Actor moves. 
     */
    public void act () {
        moveMe();
    }
    
    /**
     * For projects where efficiency is more important, DELETE THE ACT METHOD and call this directly instead.
     * 
     * This allows the statBar object to be reactive, only moving when told, rather than acting
     * each act(). For most projects, and especially for beginners, the act method is easier to manage.
     * 
     */
    public void moveMe (){
        if (target != null && getWorld() != null){
            if (target.getWorld() != null)
            {
                setLocation (target.getX(), target.getY() - offset);
            }
            else
            {
                getWorld().removeObject(this);
                return;
            }
        }    
    }

    public void update (int newCurrVal){
        update (new int[]{newCurrVal});
    }

    /**
     * update Method:
     * 
     * Expects new current Val
     * 
     * Returns true if Val has changed (needs an update)
     * Returns false if Val has not changed (to avoid excessive processing)
     */
    public void update (int newCurrVal[])
    {
        currVal = newCurrVal;
        boolean full = true;
        for (int i = 0; i < barCount; i++){
            if (currVal[i] != maxVal[i]){
                full = false; // if I find one that's not full
                break;        // no point looking at the rest
            }
        }
        if (full)
        {
            this.setImage(blank);
        }
        else
        {
            redraw();
            this.setImage(bar);
        }

    }

    public void setMaxVal (int maxVal[]){
        for (int i = 0; i < barCount; i++){
            if (maxVal[i] <= 0){
                return; // invalid
            }
        }
        this.maxVal = maxVal;
    }

    private void redraw(){

        if (hasBorder){
            bar.setColor (borderColor);
            for (int i = 0; i < borderThickness; i++){
                bar.drawRect (i, i, width - 1 - (i * 2), height - 1 - (i * 2));
            }
        }

        int extraHeight = 0;
        for (int i = 0; i < barCount; i++){
            if (i % 2 == 0 && height % 2 == 1){
                extraHeight = 1;
            }
            currPercentVal = (double) currVal[i] / maxVal[i];
            int filledBarWidth = (int) (currPercentVal * (width-(borderThickness * 2)));
            int missingBarWidth = width - (borderThickness*2) - filledBarWidth;
            bar.setColor(filledColor[i]);
            bar.fillRect(borderThickness, borderThickness + (i * barHeight), filledBarWidth, barHeight + extraHeight);
            bar.setColor(missingColor[i]);
            bar.fillRect(filledBarWidth + borderThickness, borderThickness + (i * barHeight), missingBarWidth, barHeight +extraHeight);
        }


    }
}
