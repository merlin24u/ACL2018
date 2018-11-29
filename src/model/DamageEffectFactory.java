package model;

public class DamageEffectFactory extends EffectFactory {
	private int damages;

	public DamageEffectFactory(int damages, int tickDuration) {
		super(tickDuration);
		this.damages = damages;
	}

	@Override
	public boolean applyTo(Character character) {
		character.addEffect(new DamageEffect(character, getEffectTexture(), damages, tickDuration));
		return true;
	}

	public int getDamages() {
		return damages;
	}

	@Override
	public String getTexture() {
		return "damageEffect";
	}

	@Override
	public void changeTexture() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getEffectTexture() {
		return "damageEffect";
	}
}
