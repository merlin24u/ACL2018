package model;

import engine.Cmd;

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

		int B_posX = posX;
		int B_posY = posY;
		String move = null;

		switch (commande) {
		case DOWN:
			if (posY < game.getMaxH()) {
				B_posY++;
				move = "D";
			}
			break;
		case LEFT:
			if (posX != 0) {
				B_posX--;
				move = "L";
			}
			break;
		case RIGHT:
			if (posX < game.getMaxW()) {
				B_posX++;
				move = "R";
			}
			break;
		case UP:
			if (posY != 0) {
				B_posY--;
				move = "U";
			}
			break;
		default:
			break;
		}

		if (!game.isWall(B_posX, B_posY)) {
			posX = B_posX;
			posY = B_posY;
			System.out.println(move);
		}

	}

}
