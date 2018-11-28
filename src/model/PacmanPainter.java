package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import engine.GamePainter;
import texture.TextureFactory;

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
	private PacmanGame game;
	private Map map;
	private CollisionPainterResponsability collisionPainter;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter(PacmanGame g) {
		game = g;
		map = g.getMap();
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
		String texture;
		Image img;

		try {
			img = TextureFactory.getInstance().get("ground");
			crayon.drawImage(img, 0, 0, im.getWidth(), im.getHeight(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int y = 0; y < map.getHeigh(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				collisionPainter.drawCollision(crayon, x, y, TILE_WIDTH, TILE_HEIGHT, map.getValue(x, y));
			}
		}

		for (OnMoveOver m : map.getEvents()) {
			for (EffectFactory ef : m.getEffectFactory()) {
				try {
					texture = ef.getTexture();
					img = TextureFactory.getInstance().get(texture);
					crayon.drawImage(img, m.getPosition().x * TILE_WIDTH, m.getPosition().y * TILE_HEIGHT, TILE_WIDTH,
							TILE_HEIGHT, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		for (Character c : map.getCharacters()) {
			try {
				texture = c.getTexture();
				img = TextureFactory.getInstance().get(texture);
				crayon.drawImage(img, c.getPosition().x * TILE_WIDTH, c.getPosition().y * TILE_HEIGHT, TILE_WIDTH,
						TILE_HEIGHT, null);
				int effectX = c.getPosition().x * TILE_WIDTH;
				int effectY = c.getPosition().y * TILE_HEIGHT;
				int effectWidth = TILE_WIDTH/4;
				int effectHeight = TILE_HEIGHT/4;
				for(int i =0; i< c.getEffectsSize();i++) {
					texture = c.getEffect(i).getTexture();
					if(texture != null) {
						img = TextureFactory.getInstance().get(texture);
						crayon.drawImage(img, effectX, effectY, effectWidth, effectHeight, null);
						effectX+=effectWidth;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	@Override
	public void setChangeMap() {
		map = game.getMap();
		WIDTH = map.getWidth() * TILE_WIDTH;
		HEIGHT = map.getHeigh() * TILE_HEIGHT;
	}

}
