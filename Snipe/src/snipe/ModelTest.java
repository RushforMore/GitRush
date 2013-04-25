package snipe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the model.
 * @author Zhu Cai
 *
 */
public class ModelTest {
	Model model;
	@Before
	public void setUp() throws Exception {
		model = new Model();
	}

	@Test
	public void testModel() {
		assertEquals(7, model.getBalls().length);
	}

	@Test
	public void testSetLimits() {
		model.setLimits(100, 100);
		Ball testBall = model.getBalls()[0];
		int ballSize = testBall.BALL_SIZE;
		assertEquals(100 - ballSize, testBall.getXLimit());
	}

	@Test
	public void testGetBalls() {
		assertEquals(7, model.getBalls().length);
	}

	@Test
	public void testGetMyBall() {
		assertTrue(model.getMyBall() instanceof MyBall);
	}

	@Test
	public void testLeft() {
		int originalSpeed = model.getMyBall().getXDelta();
		model.left();
		int currentSpeed = model.getMyBall().getXDelta();
		assertTrue(currentSpeed == originalSpeed - 1);
	}

	@Test
	public void testRight() {
		int originalSpeed = model.getMyBall().getXDelta();
		model.right();
		int currentSpeed = model.getMyBall().getXDelta();
		assertTrue(currentSpeed == originalSpeed + 1);
	}

	@Test
	public void testUp() {
		int originalSpeed = model.getMyBall().getYDelta();
		model.up();
		int currentSpeed = model.getMyBall().getYDelta();
		assertTrue(currentSpeed == originalSpeed - 1);
	}

	@Test
	public void testDown() {
		int originalSpeed = model.getMyBall().getYDelta();
		model.down();
		int currentSpeed = model.getMyBall().getYDelta();
		assertTrue(currentSpeed == originalSpeed + 1);
	}

	@Test
	public void testMakeOneStep() {
		Ball testBall = model.getBalls()[0];
		int originalPosition = testBall.getX();
		int speed = testBall.getXDelta();
		int limit = testBall.getXLimit();
		model.makeOneStep();
		int currentPosition = testBall.getX();
		if(originalPosition + speed >= limit){
			assertTrue(currentPosition == originalPosition + speed - limit);
		}
		else if(originalPosition + speed < 0){
			assertTrue(currentPosition == originalPosition + speed + limit);
		}
		else{
			assertTrue(currentPosition == originalPosition + speed);
		}
	}

	@Test
	public void testCheckCollide(){
		Ball testBall = model.getBalls()[0];
		testBall.setXPosition(400);
		testBall.setYPosition(400);
		model.checkCollide();
		assertTrue(testBall.isDead);
	}
	
	@Test
	public void testCheckWin() {
		Ball[] balls = model.getBalls();
		for(int i = 0; i < balls.length; i++){
			balls[i].isDead = true;
		}
		assertTrue(model.checkWin());
	}

	@Test
	public void testIsCollide() {
		Ball testBall = new Ball();
		testBall.setXPosition(400);
		testBall.setYPosition(400);
		MyBall testMyBall = new MyBall();
		assertTrue(model.isCollide(testBall, testMyBall));
		testBall.setXPosition(0);
		testBall.setYPosition(0);
		assertTrue(!model.isCollide(testBall, testMyBall));
	}
}
