package texture;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextureFactory {

	private BufferedImage wall, door, door_closed, key, treasure, character, monster, ground, damageEffect, healthEffect, treasureEffect, teleportationEffect, won, gameover;

	private static TextureFactory instance;

	private TextureFactory() throws IOException {
		wall = ImageIO.read(new File("res/wall.png"));
		door = ImageIO.read(new File("res/door.png"));
		door_closed = ImageIO.read(new File("res/door_closed.png"));
		key = ImageIO.read(new File("res/key.png"));
		treasure = ImageIO.read(new File("res/treasure.png"));
		character = ImageIO.read(new File("res/character.png"));
		monster = ImageIO.read(new File("res/monster.png"));
		ground = ImageIO.read(new File("res/ground.png"));
		won = ImageIO.read(new File("res/bravo.png"));
		gameover = ImageIO.read(new File("res/perdu.png"));
		damageEffect = ImageIO.read(new File("res/damageEffect.png"));
		healthEffect = ImageIO.read(new File("res/healthEffect.png"));
		treasureEffect = ImageIO.read(new File("res/treasureEffect.png"));
		teleportationEffect = ImageIO.read(new File("res/teleportationEffect.png"));
	}

	public static TextureFactory getInstance() throws IOException {
		if (instance == null)
			instance = new TextureFactory();

		return instance;
	}

	public BufferedImage get(String texture) throws Exception {
		switch (texture) {
		case "door":
			return door;
		case "key":
			return key;
		case "treasure":
			return treasure;
		case "character":
			return character;
		case "ground":
			return ground;
		case "wall":
			return wall;
		case "monster":
			return monster;
		case "door_closed":
			return door_closed;
		case "won":
			return won;
		case "gameover":
			return gameover;
		case "damageEffect":
			return damageEffect;
		case "healthEffect":
			return healthEffect;
		case "treasureEffect":
			return treasureEffect;
		case "teleportationEffect":
			return teleportationEffect;
		default:
			throw new Exception("texture non implementee");
		}
	}

}
