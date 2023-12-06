package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

	private Snake snake;
	private Food food;

	private static int points;
	
	public Game() {
		StdDraw.enableDoubleBuffering();
		snake = new Snake();
		food = new Food();
		points = 0;
	}
	
	public void play() {
		while (snake.isInbounds()) {
			int dir = getKeypress();

			snake.changeDirection(dir);

			snake.move();
			
			if(snake.eatFood(food)){
				food = new Food();
				points += 10;
			}

			updateDrawing();
		}
		StdDraw.text(0.2, 0.2, "You have won " + points + " points!");
		StdDraw.show();
	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	
	private void updateDrawing() {
		StdDraw.clear();
		snake.draw();
		food.draw();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		StdDraw.show();
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
		System.out.println("You have won " + points + " points!");
	}
}
