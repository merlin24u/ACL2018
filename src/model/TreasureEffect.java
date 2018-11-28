package model;

public class TreasureEffect extends Effect {
	private int moneyAmount;

	public TreasureEffect(Character character, String texture, int moneyAmount, int tickDuration) {
		super(character, texture, tickDuration);
		this.moneyAmount = moneyAmount;
	}

	public int getMoneyAmount() {
		return moneyAmount;
	}

	@Override
	public void _apply() {
		((Pacman) character).increaseMoneyAmount(moneyAmount);
	}
}
