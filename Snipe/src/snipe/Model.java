package snipe;
import java.util.Observable;

/**
 * Creates the model.
 * @author Zhu Cai
 *
 */
public class Model extends Observable{
	private Ball[] balls;
	private MyBall myBall;
	private final int BALL_NUMBER = 7;
	
	/**
	 * Creates a model.
	 */
	public Model(){
		balls = new Ball[BALL_NUMBER];
		for(int i = 0; i < balls.length; i++){
			balls[i] = new Ball();
		}		
		myBall = new MyBall();
	}
	
	/**
	 * Sets limits for the balls.
	 * @param xLimit Limits of x axis.
	 * @param yLimit Limits of y axis.
	 */
	public void setLimits(int xLimit, int yLimit) {
		for(int i = 0; i < balls.length; i++){
			balls[i].setLimits(xLimit, yLimit);
		}
		myBall.setLimits(xLimit, yLimit);
	}

	/**
	 * Gets the balls.
	 * @return The computer controlled balls.
	 */
	public Ball[] getBalls(){
		return balls;
	}
	
	/**
	 * Gets user controlled ball.
	 * @return The user controlled ball.
	 */
	public MyBall getMyBall(){
		return myBall;
	}
	
	/**
	 * Makes user ball to move left.
	 */
	public void left(){
    	myBall.left();
    }
	
	/**
	 * Makes user ball to move right.
	 */
    public void right(){
    	myBall.right();
    }
    
    /**
	 * Makes user ball to move up.
	 */
    public void up(){
    	myBall.up();
    }
    
    /**
	 * Makes user ball to move down.
	 */
    public void down(){
    	myBall.down();
    }
    
    /**
     * Makes one step for both computer controlled balls and user controlled ball.
     */
	public void makeOneStep(){
		for(int i = 0; i < balls.length; i++){
			balls[i].makeOneStep();
		}
		myBall.makeOneStep();
		checkCollide();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Checks if user controlled ball has collided with computer balls.
	 */
	public void checkCollide(){
		for(int i = 0; i < balls.length; i++){
			if(isCollide(balls[i], myBall)){
				balls[i].dead();
			}
		}
	}
	
	/**
	 * Checks whether user has won the game or not.
	 * @return The game result.
	 */
	public boolean checkWin(){
		for(int i = 0; i < balls.length; i++){
			if(!balls[i].isDead){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks whether one ball is collide with another.
	 * @param ball The computer controlled ball.
	 * @param myBall The user controlled ball.
	 * @return The collide result.
	 */
	public boolean isCollide(Ball ball, MyBall myBall){
		int distX = Math.abs(ball.getX() - myBall.getX());
		int distY = Math.abs(ball.getY() - myBall.getY());
		double dist = Math.sqrt(distX * distX + distY * distY);
		return dist <= ball.BALL_SIZE;
	}
	
	/**
	 * Renews the balls for new game.
	 */
	public void newGame(){
		for(int i = 0; i < balls.length; i++){
			balls[i] = new Ball();
		}		
		myBall = new MyBall();
	}
}
