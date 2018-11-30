package model.effect.factory;

import model.item.Item;
import model.movable.character.Character;
import model.movable.character.Pacman;

public class ItemEffectFactory extends EffectFactory {
	private Item item;

	public ItemEffectFactory(Item item) {
		super(0);
		this.item = item;
	}

	@Override
	public boolean applyTo(Character character) {
		if (character.isType("Player")) {
			((Pacman) character).addItem(item.clone());
			item.setCollected();
			return true;
		} else
			return false;
	}

	public Item getItem() {
		return item;
	}

	@Override
	public String getTexture() {
		return item.getTexture();
	}

	@Override
	public void changeTexture() {
		// TODO Auto-generated method stub	
	}

	@Override
	public String getEffectTexture() {
		// TODO Auto-generated method stub
		return null;
	}
}
