package model;

import java.awt.Point;

import engine.Cmd;
import engine.Game;

public class Pacman extends Character{

	private PacmanGame game;
	private int moneyAmount;

	public Pacman(PacmanGame p) {
		super(5,5,0,new GroundCollisionHandler(p.getMap()),1,1,new Point(0,0));
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
		}
	}

	public void increaseMoneyAmount(int moneyAmount) {
		this.moneyAmount += moneyAmount;
	}

}
