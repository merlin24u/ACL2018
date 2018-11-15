package texture;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextureFactory {

	private Image wall, door, key, treasure, character, monster, ground;
	private static TextureFactory instance;

	private TextureFactory() throws IOException {
		wall = ImageIO.read(new File("res/wall.png"));
		door = ImageIO.read(new File("res/door.png"));
		key = ImageIO.read(new File("res/key.png"));
		treasure = ImageIO.read(new File("res/treasure.png"));
		character = ImageIO.read(new File("res/character.png"));
		monster = ImageIO.read(new File("res/monster.png"));
		ground = ImageIO.read(new File("res/ground.png"));
	}

	public static TextureFactory getInstance() throws IOException {
		if (instance == null)
			instance = new TextureFactory();

		return instance;
	}

	public Image get(String texture) throws Exception {
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
		default:
			throw new Exception("texture non implementee");
		}
	}

}
