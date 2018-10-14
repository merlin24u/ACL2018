package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.GamePainter;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {

	/**
	 * la taille des cases
	 */
	protected static int WIDTH;
	protected static int HEIGHT;

	private Pacman player;
	private Map map;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Pacman p, Map m) {
		player = p;
		map = m;
		WIDTH = map.getWidth() + 10;
		HEIGHT = map.getHeigh() + 10;
	}

	/**
	 * methode redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillOval(player.getPosX(), player.getPosY(), 10, 10);
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
