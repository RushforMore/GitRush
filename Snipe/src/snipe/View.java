package snipe;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Creates the view.
 * @author Zhu Cai
 *
 */
class View extends JPanel implements Observer {
	Model model;
	Random rand;
	private final int COLOR_NUMBER = 7;
	Color[] colors = new Color[COLOR_NUMBER];
	private long elapsedTime = 0;

	/**
	 * Creates the view.
	 * @param model The model for the view.
	 */
	View(Model model) {
		setSize(800, 800);
		this.model = model;
		rand = new Random();
		for(int i = 0; i < colors.length; i++){
			colors[i] = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		}
	}

	/**
	 * Paints each ball and the time.
	 */
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		Ball[] balls = model.getBalls();
		MyBall myBall = model.getMyBall();
		for(int i = 0; i < balls.length; i++){
			if(!balls[i].isDead){
				g.setColor(colors[i]);
				g.fillOval(balls[i].getX(), balls[i].getY(),
						balls[i].BALL_SIZE, balls[i].BALL_SIZE);
			}
			else{
				int red = (colors[i].getRed() + 5 >= 255)? 255: colors[i].getRed() + 5;
				int blue = (colors[i].getBlue() + 5 >= 255)? 255: colors[i].getBlue() + 5;
				int green = (colors[i].getGreen() + 5 >= 255)? 255: colors[i].getGreen() + 5;
				colors[i] = new Color(red, green, blue);
				g.setColor(colors[i]);
				g.fillOval(balls[i].getX(), balls[i].getY(),
						balls[i].BALL_SIZE, balls[i].BALL_SIZE);

			}
		}
		g.setColor(Color.black);
		g.fillOval(myBall.getX(), myBall.getY(),
				myBall.BALL_SIZE, myBall.BALL_SIZE);
		g.drawString(String.format("Time: %4.1fs", (double)(elapsedTime) / 1000), 720, 20);
	}

	/**
	 * Sets the elapsed time.
	 * @param time The new time.
	 */
	public void setTime(long time){
		elapsedTime = time;
	}

	/**
	 * Updates the view.
	 */
	public void update(Observable obs, Object arg) {
		repaint();
	}

	/**
	 * Recalculates the colors and renew the model.
	 */
	public void newGame(){
		model.newGame();
		for(int i = 0; i < colors.length; i++){
			colors[i] = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		}
	}
}