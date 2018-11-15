package model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class OnMoveOver implements IUpdate, ICoordinate, IDestructible {
	private Map map;
	private Point position;
	// Si l'objet est visible
	private boolean isVisible;
	// Si l'objet a ete active
	private boolean isActivated;
	// Si l'objet doit etre detruit une fois active
	private boolean isPersistingAfterActivation;
	// a detruire
	private boolean toDestroy;

	private ArrayList<Character> charactersAlreadyOn;
	private EffectFactory effectsFactorie;

	public OnMoveOver(Map map, Point position, boolean isVisible, boolean isActivated,
			boolean isPersistingAfterActivation) {
		super();
		this.map = map;
		this.position = position;
		this.isVisible = isVisible;
		this.isActivated = isActivated;
		this.isPersistingAfterActivation = isPersistingAfterActivation;
		this.toDestroy = false;
		this.charactersAlreadyOn = new ArrayList<Character>();
	}

	protected void applyTo(Character character) {
		boolean res = effectsFactorie.applyTo(character);
		if (!isPersistingAfterActivation && res)
			toDestroy = true;
	}

	public void addEffectFactory(EffectFactory ef) {
		effectsFactorie = ef;
	}

	private boolean hasSamePosition(Point position) {
		return this.position.x == position.x && this.position.y == position.y;
	}

	private boolean wasAlreadyOn(Character character) {
		return charactersAlreadyOn.contains(character);
	}

	@Override
	public void update() {
		for (Character character : map.getCharacters()) {
			if (hasSamePosition(character.getPosition())) {
				if (!wasAlreadyOn(character) && hasRequirements(character)) {
					applyTo(character);
					charactersAlreadyOn.add(character);
				}
			} else {
				charactersAlreadyOn.remove(character);
			}
		}
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public EffectFactory getEffectFactory() {
		return effectsFactorie;
	}

	@Override
	public void setPosition(int x, int y) {
		// Inutilisé
	}

	@Override
	public void translate(int distanceX, int distanceY) {
		// Inutilisé
	}

	@Override
	public boolean isToDestroy() {
		return toDestroy;
	}

	protected abstract boolean hasRequirements(Character character);
}
