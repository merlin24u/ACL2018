package model;

public class DamageEffectFactory extends EffectFactory {
	private int damages;
	
	public DamageEffectFactory(int damages, int tickDuration) {
		super(tickDuration);
		this.damages = damages;
	}
	
	@Override
	public boolean applyTo(Character character) {
		character.addEffect(new DamageEffect(character, damages, tickDuration));
		return true;
	}

	public int getDamages() {
		return damages;
	}

	@Override
	public String getTexture() {
		return "trap";
	}

	@Override
	public void changeTexture() {
		// TODO Auto-generated method stub
	}
}
