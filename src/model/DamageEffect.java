package model;

public class DamageEffect extends Effect implements IDamager{
	private int damages;
	
	public DamageEffect(Character character, int damages, int tickDuration) {
		super(character, tickDuration);
		this.damages = damages;
	}


	@Override
	public void _apply() {
		attack(character);
	}

	@Override
	public int getDamages() {
		return damages;
	}
}
