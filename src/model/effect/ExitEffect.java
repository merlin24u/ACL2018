package model.effect;

import model.PacmanGame;
import model.movable.character.Character;

public class ExitEffect extends Effect {

	public ExitEffect(Character character, String texture, int tickDuration) {
		super(character, texture, tickDuration);
	}

	@Override
	protected void _apply() {
		PacmanGame.updateMap();
	}

}
