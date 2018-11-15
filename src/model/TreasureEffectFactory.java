package model;

public class TreasureEffectFactory extends EffectFactory {
	private int moneyAmount;

	public TreasureEffectFactory(int moneyAmount, int tickDuration) {
		super(tickDuration);
		this.moneyAmount = moneyAmount;
	}

	@Override
	public boolean applyTo(Character character) {
		if (character.isType("Player")) {
			character.addEffect(new TreasureEffect(character, moneyAmount, tickDuration));
			return true;
		} else
			return false;
	}

	public int getMoneyAmount() {
		return moneyAmount;
	}

	@Override
	public String getTexture() {
		return "treasure";
	}
}
