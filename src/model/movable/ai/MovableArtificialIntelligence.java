package model.movable.ai;

import model.Map;
import model.movable.Movable;

public abstract class MovableArtificialIntelligence {
	protected Map map;
	public MovableArtificialIntelligence(Map map) {
		this.map = map;
	}
	public abstract void execute(Movable movable);
}
