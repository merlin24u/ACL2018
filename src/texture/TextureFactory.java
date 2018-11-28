package texture;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextureFactory {

	private Image wall, door, door_closed, key, treasure, character0, character1, character2, character3, monster0, monster1, monster2, monster3, ground;
	private static TextureFactory instance;

	private TextureFactory() throws IOException {
		wall = ImageIO.read(new File("res/wall.png"));
		door = ImageIO.read(new File("res/door.png"));
		door_closed = ImageIO.read(new File("res/door_closed.png"));
		key = ImageIO.read(new File("res/key.png"));
		treasure = ImageIO.read(new File("res/treasure.png"));
		character0 = ImageIO.read(new File("res/Char0.png"));
		character1 = ImageIO.read(new File("res/Char1.png"));
		character2 = ImageIO.read(new File("res/Char2.png"));
		character3 = ImageIO.read(new File("res/Char3.png"));
		monster0 = ImageIO.read(new File("res/monster0.png"));
		monster1 = ImageIO.read(new File("res/monster1.png"));
		monster2 = ImageIO.read(new File("res/monster2.png"));
		monster3 = ImageIO.read(new File("res/monster3.png"));
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
		case "character0":
			return character0;
		case "character1":
			return character1;
		case "character2":
			return character2;
		case "character3":
			return character3;
		case "ground":
			return ground;
		case "wall":
			return wall;
		case "monster0":
			return monster0;
		case "monster1":
			return monster1;
		case "monster2":
			return monster2;
		case "monster3":
			return monster3;
		case "door_closed":
			return door_closed;
		default:
			throw new Exception("texture non implementee");
		}
	}

}
