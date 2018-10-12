package model;

public class Pacman {

	private int nbVies;
	private int posX;
	private int posY;

	public Pacman() {
		nbVies = 5;
		posX = 0;
		posY = 0;
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

}
