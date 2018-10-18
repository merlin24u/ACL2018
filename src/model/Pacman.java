package model;

import engine.Cmd;
import engine.Game;

public class Pacman extends Movable{

	private PacmanGame game;
	private int nbVies;

	public Pacman(PacmanGame p) {
		super(new GroundCollisionHandler(p), 0, 1, 1, 1);
		nbVies = 5;
		game = p;
	}

	public void setNbVies(int nbVies) {
		this.nbVies = nbVies;
	}


	public int getNbVies() {
		return nbVies;
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
		update();
	}

}
