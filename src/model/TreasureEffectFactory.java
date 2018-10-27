package model;

public class TreasureEffectFactory extends EffectFactory {
	private int moneyAmount;

	public TreasureEffectFactory(int moneyAmount, int tickDuration) {
		super(tickDuration);
		this.moneyAmount = moneyAmount;
	}

	@Override
	public void applyTo(Character character) {
		if (character instanceof Pacman)
			character.addEffect(new TreasureEffect(character, moneyAmount,
					tickDuration));
	}
	
	public int getMoneyAmount() {
		return moneyAmount;
	}
}
