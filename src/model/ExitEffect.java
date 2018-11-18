package model;

public class ExitEffect extends Effect {

	public ExitEffect(Character character, int tickDuration) {
		super(character, tickDuration);
	}

	@Override
	protected void _apply() {
		PacmanGame.updateMap();
	}

}
