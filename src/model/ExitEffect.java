package model;

public class ExitEffect extends Effect {

	public ExitEffect(Character character, int tickDuration) {
		super(character, tickDuration);
	}

	@Override
	protected void _apply() {
		System.out.println("You've won !");
		System.exit(1);
	}

}
