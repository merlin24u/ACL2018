package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	protected static int TILE_WIDTH = 50;
	protected static int TILE_HEIGHT = 50;

	private Map map;
	private CollisionPainterResponsability collisionPainter;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Map m) {
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
		for (int y = 0; y < map.getHeigh(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				collisionPainter.drawCollision(crayon, x, y, TILE_WIDTH, TILE_HEIGHT, map.getValue(x, y));
			}
		}

		String texture;
		Image img;
		for (OnMoveOver m : map.getEvents()) {
			for (EffectFactory ef : m.getEffectFactory()) {
				try {
					texture = ef.getTexture();
					img = ImageIO.read(new File(texture));
					crayon.drawImage(img, m.getPosition().x * TILE_WIDTH, m.getPosition().y * TILE_HEIGHT, TILE_WIDTH,
							TILE_HEIGHT, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		for (Character c : map.getCharacters()) {
			crayon.setColor(c.getColor());
			crayon.fillOval(c.getPosition().x * TILE_WIDTH, c.getPosition().y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
		}

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
