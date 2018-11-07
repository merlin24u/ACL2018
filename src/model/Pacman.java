package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import engine.Cmd;

public class Pacman extends Character {

	private PacmanGame game;
	private int moneyAmount;
	private ArrayList<Item> items;

	public Pacman(PacmanGame p) {
		super(5, 5, 0, new GroundCollisionHandler(p.getMap()), 1, 1, new Point(
				0, 1), Color.blue);
		game = p;
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
	}

	public void increaseMoneyAmount(int moneyAmount) {
		this.moneyAmount += moneyAmount;
	}
	
	public int getMoneyAmount() {
		return this.moneyAmount;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	public Item getItem(String itemId) {
		for(Item i: items) {
			if(i.id.equals(itemId))
				return i;
		}
		return null;
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
}
