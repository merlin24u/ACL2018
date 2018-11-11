package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import engine.Cmd;

public class Pacman extends Character {

	private int moneyAmount;
	private ArrayList<Item> items;

	public Pacman(Map m) {
		super(5, 5, 0, new GroundCollisionHandler(m), 1, 1, m.getStart(), Color.blue);
		this.items = new ArrayList<Item>();
	}

	public void evolve(Cmd commande) {

		switch (commande) {
		case DOWN:
			this.moveDown();
			System.out.println("D");
			break;
		case LEFT:
			this.moveLeft();
			System.out.println("L");
			break;
		case RIGHT:
			this.moveRight();
			System.out.println("R");
			break;
		case UP:
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
		System.out.println("you've collected " + item.getName());
		this.items.add(item);
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
}
