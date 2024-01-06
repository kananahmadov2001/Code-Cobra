import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	// private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private static double MOVEMENT_SIZE = SEGMENT_SIZE * 0.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;

	private double speed = 0.6;
	
	public Snake() {
		segments = new LinkedList<>();
		BodySegment bodySegment = new BodySegment(0.5, 0.5, SEGMENT_SIZE);
		segments.add(bodySegment);
		deltaX = 0;
		deltaY = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	
	public void move() {
		if(segments.size() != 1){
			double x = segments.getFirst().getX() + deltaX;
			double y = segments.getFirst().getY() + deltaY;
			BodySegment bodySegment = new BodySegment(x, y, SEGMENT_SIZE);
			BodySegment tail = segments.removeLast();
			segments.addFirst(bodySegment);
		} else {
			segments.getFirst().setX(segments.getFirst().getX() + deltaX);
			segments.getFirst().setY(segments.getFirst().getY() + deltaY);
		}
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	
	public void draw() {
		for (BodySegment bodySegment : segments) {
			bodySegment.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	
	public boolean eatFood(Food f) {
		double distance = Math.sqrt(Math.pow(segments.getFirst().getX() - f.getX(),2) + Math.pow(segments.getFirst().getY() - f.getY(),2));
		if(distance <= 0.03){			
			BodySegment bodySegment = new BodySegment(segments.getLast().getX() + SEGMENT_SIZE, segments.getLast().getY() + SEGMENT_SIZE, SEGMENT_SIZE);
			segments.add(bodySegment);
			MOVEMENT_SIZE = SEGMENT_SIZE * speed;
			speed = speed + 0.08;
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	
	public boolean isInbounds() {
		for (BodySegment bodySegment : segments) {
			if(bodySegment.getX() <= 0){
				return false;
			} else if(bodySegment.getY() <= 0){
				return false;
			} else if(bodySegment.getX() >= 1){
				return false;
			} else if(bodySegment.getY() >= 1){
				return false;
			} 
		}
		return true;
	}
}
