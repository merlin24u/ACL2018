package model.onmoveover;

import java.awt.Point;
import java.util.ArrayList;

import model.ICoordinate;
import model.IDestructible;
import model.IUpdate;
import model.Map;
import model.effect.factory.EffectFactory;
import model.movable.character.Character;


public abstract class OnMoveOver implements IUpdate, ICoordinate,
		IDestructible {
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
	private ArrayList<EffectFactory> effectsFactories;

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
		this.effectsFactories = new ArrayList<>();
	}

	protected void applyTo(Character character) {
		for (EffectFactory ef : effectsFactories) {
			boolean res = ef.applyTo(character);
			if (!isPersistingAfterActivation && res)
				toDestroy = true;
		}
	}

	public void addEffectFactory(EffectFactory ef) {
		effectsFactories.add(ef);
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

	public ArrayList<EffectFactory> getEffectFactory() {
		return effectsFactories;
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
