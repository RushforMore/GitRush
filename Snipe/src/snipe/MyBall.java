package snipe;

/**
 * Creates a user controlled ball.
 * @author Zhu Cai
 *
 */
public class MyBall extends Ball{
	public final int BALL_SIZE = 10;
    private int xPosition;
    private int yPosition;
    private int xLimit, yLimit;
    private int xDelta;
    private int yDelta;
    
    /**
     * Creates the ball, sets position in center and speed of 0.
     */
    public MyBall(){
    	xPosition = 400;
    	yPosition = 400;
    	xDelta = 0;
    	yDelta = 0;
    }
    
    /**
     * Makes the ball move left.
     */
    public void left(){
    	xDelta = (xDelta <= -30)? -30: xDelta - 1;
    }
	
    /**
     * Makes the ball move right.
     */
    public void right(){
    	xDelta = (xDelta >= 30)? 30: xDelta + 1;
    }
    
    /**
     * Makes the ball move up.
     */
    public void up(){
    	yDelta = (yDelta <= -30)? -30: yDelta - 1;
    }
    
    /**
     * Makes the ball move down.
     */
    public void down(){
    	yDelta = (yDelta >= 30)? 30: yDelta + 1;
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
            xDelta = -xDelta;
            xPosition += xDelta;
        }
        yPosition += yDelta;
        if (yPosition < 0 || yPosition >= yLimit) {
            yDelta = -yDelta;
            yPosition += yDelta;
        }
    }
}
