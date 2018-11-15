package texture;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextureFactory {

	private Image wall, door, key, treasure;
	private static TextureFactory instance;

	private TextureFactory() throws IOException {
		wall = ImageIO.read(new File("res/wall.png"));
		door = ImageIO.read(new File("res/door.png"));
		key = ImageIO.read(new File("res/key.png"));
		treasure = ImageIO.read(new File("res/treasure.png"));
	}

	public static TextureFactory getInstance() throws IOException {
		if (instance == null)
			instance = new TextureFactory();

		return instance;
	}

	public Image getWall() {
		return wall;
	}

	public Image getDoor() {
		return door;
	}

	public Image getKey() {
		return key;
	}

	public Image getTreasure() {
		return treasure;
	}

	public Image get(String texture) throws Exception {
		switch (texture) {
		case "door":
			return door;
		case "key":
			return key;
		case "treasure":
			return treasure;
		default:
			throw new Exception("texture non implementee");
		}
	}

}
