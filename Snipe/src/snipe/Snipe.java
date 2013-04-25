package snipe;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * Creates snipe game.
 * @author Zhu Cai
 *
 */
public class Snipe extends JFrame{
	JPanel buttonPanel = new JPanel();
	JButton playButton = new JButton("Play");
	JButton pauseButton = new JButton("Pause");
	JButton resumeButton = new JButton("Resume");
	JButton quitButton = new JButton("Quit");
	Timer timer;
	long beginTime, elapsedTime, gapTime = 0;
	long bestTime = 1000000;
	boolean isFirstGame = true;
	Model model;
	View view; // View must know about Model

	/**
	 * Constructs a snipe.
	 */
	public Snipe(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 1000);
		setVisible(true);
	}
	
	/**
	 * Creates a snipe game.
	 * @param args
	 */
	public static void main(String[] args) {
		Snipe snipe = new Snipe();
		snipe.init();
	}

	/**
	 * Initializes snipe.
	 */
	public void init() {
		model = new Model();
		view = new View(model);
		layOutComponents();
		attachListenersToComponents();

		// Connect model and view

		model.addObserver(view);
	}

	/**
	 * Layouts the components.
	 */
	private void layOutComponents() {
		setLayout(new BorderLayout());
		this.add(BorderLayout.SOUTH, buttonPanel);
		buttonPanel.add(playButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(resumeButton);
		buttonPanel.add(quitButton);
		pauseButton.setEnabled(false);
		resumeButton.setEnabled(false);
		this.add(BorderLayout.CENTER, view);
	}

	/**
	 * Attaches listeners to keyboard and buttons.
	 */
	private void attachListenersToComponents() {
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				playButton.setEnabled(false);
				pauseButton.setEnabled(true);
				resumeButton.setEnabled(false);
				if(!isFirstGame){
					view.newGame();
				}
				else{
					isFirstGame = false;
				}
				beginTime = System.currentTimeMillis();
				view.requestFocus();
				timer = new Timer(true);
				timer.schedule(new Strobe(), 0, 40); // 25 times a second
			}
		});
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				playButton.setEnabled(false);
				pauseButton.setEnabled(false);
				resumeButton.setEnabled(true);
				timer.cancel();
			}
		});
		resumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				playButton.setEnabled(false);
				pauseButton.setEnabled(true);
				resumeButton.setEnabled(false);
				gapTime = System.currentTimeMillis() - elapsedTime - beginTime;
				view.requestFocus();
				timer = new Timer(true);
				timer.schedule(new Strobe(), 0, 40);
			}
		});
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		view.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_I || key == KeyEvent.VK_UP){
					model.up();
				}
				else if(key == KeyEvent.VK_A || key == KeyEvent.VK_J || key == KeyEvent.VK_LEFT){
					model.left();
				}
				else if(key == KeyEvent.VK_S || key == KeyEvent.VK_K || key == KeyEvent.VK_DOWN){
					model.down();
				}
				else if(key == KeyEvent.VK_D || key == KeyEvent.VK_L || key == KeyEvent.VK_RIGHT){
					model.right();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
		view.requestFocus();
	}

	/**
	 * Creates a time tasks, updates the move and time.
	 * @author Zhu Cai
	 *
	 */
	private class Strobe extends TimerTask {
		public void run() {
			model.setLimits(view.getWidth(), view.getHeight());
			model.makeOneStep();
			if(model.checkWin()){
				cancel();
				playButton.setEnabled(true);
				pauseButton.setEnabled(false);
				resumeButton.setEnabled(false);
				if(elapsedTime < bestTime){
					bestTime = elapsedTime;
				}
				JOptionPane.showMessageDialog(view, "Congraturations! You Win!\nYour Time is:"
						+ String.format("%4.1fs", (double)(elapsedTime) / 1000) + "\nBest Record is:"
						+ String.format("%4.1fs", (double)(bestTime) / 1000));
			}
			elapsedTime = System.currentTimeMillis() - beginTime - gapTime;
			view.setTime(elapsedTime);
		}
	}

}
