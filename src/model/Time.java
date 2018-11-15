package model;

public class Time  implements IUpdate{
	private int tick;
	private Time() {
		reset();
	}
	public void reset() {
		tick = 0;
	}
	@Override
	public void update() {
		tick ++;
	}
	
	public int getTick() {
		return tick;
	}
	
	public static Time instance = new Time();
	
	public static Time getInstance() {
		return instance;
	}
}
