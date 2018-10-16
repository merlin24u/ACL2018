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
	protected static int TILE_WIDTH = 10;
	protected static int TILE_HEIGHT = 10;

	private Pacman player;
	private Map map;
	private CollisionPainterResponsability collisionPainter;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Pacman p, Map m) {
		player = p;
		map = m;
		WIDTH = map.getWidth() * TILE_WIDTH;
		HEIGHT = map.getHeigh() * TILE_HEIGHT;
		collisionPainter = new WallCollisionPainterResponsability(ECollisionType.WALL);
	}

	/**
	 * methode redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		for(int y = 0; y < map.getHeigh(); y++) {
			for(int x = 0; x < map.getWidth(); x++) {
				collisionPainter.drawCollision(crayon, x, y, TILE_WIDTH, TILE_HEIGHT, map.get(x,y));
			}
		}
		crayon.setColor(Color.blue);
		crayon.fillOval(player.getPosX()*TILE_WIDTH, player.getPosY()*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
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
