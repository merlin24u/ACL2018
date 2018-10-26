package model;

import java.awt.Point;
import java.util.ArrayList;

public class OnMoveOver implements IUpdate, ICoordinate {
	private Map map;
	private Point position;
	// Si l'objet est visible
	private boolean isVisible;
	// Si l'objet a �t� activ�
	private boolean isActivated;
	// Si l'objet doit �tre d�truit une fois activ�
	private boolean isPersistingAfterActivation;

	private ArrayList<Character> charactersAlreadyOn;
	private ArrayList<EffectFactory> effectsFactories;

	public OnMoveOver(Map map, Point position, boolean isVisible,
			boolean isActivated, boolean isPersistingAfterActivation) {
		super();
		this.map = map;
		this.position = position;
		this.isVisible = isVisible;
		this.isActivated = isActivated;
		this.isPersistingAfterActivation = isPersistingAfterActivation;
		this.charactersAlreadyOn = new ArrayList<Character>();
		this.effectsFactories = new ArrayList<EffectFactory>();
	}

	protected void applyTo(Character character) {
		for (EffectFactory ef : effectsFactories) {
			ef.applyTo(character);
		}
	}

	public void addEffectFactory(EffectFactory effectFactory) {
		this.effectsFactories.add(effectFactory);
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
				if (!wasAlreadyOn(character)) {
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

	@Override
	public void setPosition(int x, int y) {
		// Inutilis�
	}

	@Override
	public void translate(int distanceX, int distanceY) {
		// Inutilis�
	}
}
