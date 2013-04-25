package snipe;
import java.util.Random;

/**
 * Creates a computer controlled ball.
 * @author Zhu Cai
 *
 */
public class Ball{
	public final int BALL_SIZE = 10;
    private int xPosition;
    private int yPosition;
    private int xLimit, yLimit;
    private int xDelta;
    private int yDelta;
    public boolean isDead = false;
    
    /**
     * Creates the ball with random position and speed.
     */
    public Ball(){
    	Random r = new Random();
    	xDelta = r.nextInt(16) - 8;
    	if(xDelta == 0){
    		while(yDelta == 0){
    			yDelta = r.nextInt(16) - 8;
    		}
    	}
    	else{
    		yDelta = r.nextInt(16) - 8;
    	}
    	xPosition = r.nextInt(800);
    	yPosition = r.nextInt(800);
    }
    
    /**
	 * Sets limits for the balls.
	 * @param xLimit Limits of x axis.
	 * @param yLimit Limits of y axis.
	 */
    public void setLimits(int xLimit, int yLimit) {
        this.xLimit = xLimit - BALL_SIZE;
        this.yLimit = yLimit - BALL_SIZE;
    }
    
    /**
     * Sets the position of x axis.
     * @param x X axis position.
     */
    public void setXPosition(int x){
    	xPosition = x;
    }
    
    /**
     * Sets the position of y axis.
     * @param y Y axis position.
     */
    public void setYPosition(int y){
    	yPosition = y;
    }
    
    /**
     * Gets the position of x axis.
     * @return X axis position.
     */
    public int getX() {
        return xPosition;
    }

    /**
     * Gets the position of y axis.
     * @return Y axis position.
     */
    public int getY() {
        return yPosition;
    }
    
    /**
     * Gets the limit of x axis.
     * @return X axis limit.
     */
    public int getXLimit(){
    	return xLimit;
    }
    
    /**
     * Gets the limit of y axis.
     * @return Y axis limit.
     */
    public int getYLimit(){
    	return yLimit;
    }
    
    /**
     * Gets the speed of x axis.
     * @return X axis speed.
     */
    public int getXDelta(){
    	return xDelta;
    }
    
    /**
     * Gets the speed of y axis.
     * @return Y axis speed.
     */
    public int getYDelta(){
    	return yDelta;
    }
    
    /**
     * Makes one step move.
     */
    public void makeOneStep() {
        // Do the work
        xPosition += xDelta;
        if (xPosition < 0 || xPosition >= xLimit) {
            xPosition = (xPosition < 0)? xPosition + xLimit: xPosition -xLimit;
        }
        yPosition += yDelta;
        if (yPosition < 0 || yPosition >= yLimit) {
            yPosition = (yPosition < 0)? yPosition + xLimit: yPosition -xLimit;
        }
    }
    
    /**
     * Changes speed and status when dead.
     */
    public void dead(){
    	xDelta = 0;
    	yDelta = 0;
    	isDead = true;
    }
}
