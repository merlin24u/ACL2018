package model.effect;
import model.movable.character.Character;
import model.movable.character.Pacman;

public class HealthEffect extends Effect {
	private int healthAmount;

	public HealthEffect(Character character, String texture, int healthAmount, int tickDuration) {
		super(character, texture, tickDuration);
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
