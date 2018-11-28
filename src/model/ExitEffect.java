package model;

public class ExitEffect extends Effect {

	public ExitEffect(Character character, String texture, int tickDuration) {
		super(character, texture, tickDuration);
	}

	@Override
	protected void _apply() {
		PacmanGame.updateMap();
	}

}
