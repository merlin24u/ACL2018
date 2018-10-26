package model;

public class TreasureEffect extends Effect {
	private int moneyAmount;

	public TreasureEffect(Character character, int moneyAmount, int tickDuration) {
		super(character, tickDuration);
		this.moneyAmount = moneyAmount;
	}

	@Override
	public void _apply() {
		((Pacman) character).increaseMoneyAmount(moneyAmount);
	}
}
