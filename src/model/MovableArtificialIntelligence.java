package model;

public abstract class MovableArtificialIntelligence {
	protected Map map;
	public MovableArtificialIntelligence(Map map) {
		this.map = map;
	}
	public abstract void execute(Movable movable);
}
