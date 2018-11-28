package model;

public class DamageEffect extends Effect implements IDamager{
	private int damages;
	
	public DamageEffect(Character character, String texture, int damages, int tickDuration) {
		super(character, texture, tickDuration);
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


	@Override
	public void attack(Character character) {
		character.applyDamages(damages);
	}
}
