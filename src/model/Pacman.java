package model;

import java.util.ArrayList;
import engine.Cmd;

public class Pacman extends Character implements IDamager {
	private DamageEffectFactory damageEffectFactory;
	private int moneyAmount;
	private ArrayList<Item> items;

	public Pacman(Map m) {
		super(5, 5, 0, new GroundCollisionHandler(m), 1, 1, 3, m.getStart());
		this.items = new ArrayList<Item>();
		this.damageEffectFactory = new DamageEffectFactory(1, 1);
	}

	public void evolve(Cmd commande) {

		switch (commande) {
		case DOWN:
			this.setOrientation(0);
			this.moveDown();
			System.out.println("D");
			break;
		case LEFT:
			this.setOrientation(2);
			this.moveLeft();
			System.out.println("L");
			break;
		case RIGHT:
			this.setOrientation(3);
			this.moveRight();
			System.out.println("R");
			break;
		case UP:
			this.setOrientation(1);
			this.moveUp();
			System.out.println("U");
			break;
		default:
			break;
		}
		System.out.println("money: " + this.moneyAmount);
	}

	public void increaseMoneyAmount(int moneyAmount) {
		this.moneyAmount += moneyAmount;
	}

	public int getMoneyAmount() {
		return this.moneyAmount;
	}

	public void addItem(Item item) {
		if (!items.contains(item)) {
			System.out.println("you've collected " + item.getName());
			this.items.add(item);
		}
	}

	public Item getItem(String itemId) {
		for (Item i : items) {
			if (i.getId().equals(itemId))
				return i;
		}
		return null;
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public boolean isType(String type) {
		if (type.equals("Player"))
			return true;
		else
			return super.isType(type);
	}

	@Override
	public void onCollision(Character character) {
		// Aucune action
	}

	@Override
	public int getDamages() {
		return damageEffectFactory.getDamages();
	}

	@Override
	public void attack(Character character) {
		damageEffectFactory.applyTo(character);
	}

	@Override
	public String getTexture() {
		return "character"+this.getOrientation();
	}

	public void changeMap(Map m) {
		super.groundCollisionHandler.changeMap(m);
	}
}
