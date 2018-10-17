package model;

import engine.Cmd;
import engine.Game;

public class Pacman {

	private PacmanGame game;
	private int nbVies;
	private int posX;
	private int posY;

	public Pacman(PacmanGame p) {
		nbVies = 5;
		posX = 0;
		posY = 1;
		game = p;
	}

	public void setNbVies(int nbVies) {
		this.nbVies = nbVies;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getNbVies() {
		return nbVies;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void evolve(Cmd commande) {
		switch (commande) {
		case DOWN:
			if (posY < game.getMaxH()) {
				if (!game.isWall(posX, posY + 1)) {
					posY++;
					System.out.println("D");
				}
			}
			break;
		case LEFT:
			if (posX != 0) {
				if (!game.isWall(posX - 1, posY)) {
					posX--;
					System.out.println("L");
				}
			}
			break;
		case RIGHT:
			if (posX < game.getMaxW()) {
				if (!game.isWall(posX + 1, posY)) {
					posX++;
					System.out.println("R");
				}
			}
			break;
		case UP:
			if (posY != 0) {
				if (!game.isWall(posX, posY - 1)) {
					posY--;
					System.out.println("U");
				}
			}
			break;
		}

	}

}
