package model;

public class ItemEffectFactory extends EffectFactory{
	private Item item;
	
	public ItemEffectFactory(Item item) {
		super(0);
	}
	
	@Override
	public void applyTo(Character character) {
		if (character instanceof Pacman)
			((Pacman)character).addItem(item.clone());
	}
	
	public Item getItem() {
		return item;
	}
}
