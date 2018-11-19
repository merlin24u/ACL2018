package model;

public class HealthEffect extends Effect {
	private int healthAmount;

	public HealthEffect(Character character, int healthAmount, int tickDuration) {
		super(character, tickDuration);
		this.healthAmount = healthAmount;
	}

	public int getHealthAmount() {
		return healthAmount;
	}

	@Override
	public void _apply() {
		((Pacman) character).increaseHP(healthAmount);
	}
}
