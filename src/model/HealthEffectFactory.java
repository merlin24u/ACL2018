package model;

public class HealthEffectFactory extends EffectFactory {
	private int healthAmount;

	public HealthEffectFactory(int healthAmount, int tickDuration) {
		super(tickDuration);
		this.healthAmount = healthAmount;
	}

	@Override
	public boolean applyTo(Character character) {
		if (character.isType("Player")) {
			character.addEffect(new HealthEffect(character, getEffectTexture(), healthAmount, tickDuration));
			return true;
		} else
			return false;
	}

	public int getHealthAmount() {
		return healthAmount;
	}

	@Override
	public String getTexture() {
		return "healthEffect";
	}

	@Override
	public void changeTexture() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getEffectTexture() {
		return "healthEffect";
	}
}
