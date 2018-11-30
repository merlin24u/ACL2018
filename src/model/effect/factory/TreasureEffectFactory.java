package model.effect.factory;

import model.effect.TreasureEffect;
import model.movable.character.Character;

public class TreasureEffectFactory extends EffectFactory {
	private int moneyAmount;

	public TreasureEffectFactory(int moneyAmount, int tickDuration) {
		super(tickDuration);
		this.moneyAmount = moneyAmount;
	}

	@Override
	public boolean applyTo(Character character) {
		if (character.isType("Player")) {
			character.addEffect(new TreasureEffect(character, getEffectTexture(), moneyAmount, tickDuration));
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

	@Override
	public void changeTexture() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getEffectTexture() {
		return "treasureEffect";
	}
}
