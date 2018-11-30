package texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextureFactory {

	private BufferedImage wall, door, door_closed, key, treasure, ground, damageEffect, healthEffect, treasureEffect,
			teleportationEffect, won, gameover;
	private BufferedImage[] character, monster, ghost;
	private static TextureFactory instance;

	private TextureFactory() throws IOException {
		wall = ImageIO.read(ResourceLoader.load("wall.png"));
		door = ImageIO.read(ResourceLoader.load("door.png"));
		door_closed = ImageIO.read(ResourceLoader.load("door_closed.png"));
		key = ImageIO.read(ResourceLoader.load("key.png"));
		treasure = ImageIO.read(ResourceLoader.load("treasure.png"));
		character = new BufferedImage[] { ImageIO.read(ResourceLoader.load("CharNorth.png")),
				ImageIO.read(ResourceLoader.load("CharSouth.png")), ImageIO.read(ResourceLoader.load("CharEst.png")),
				ImageIO.read(ResourceLoader.load("CharWest.png")), ImageIO.read(ResourceLoader.load("AttackNorth.png")),
				ImageIO.read(ResourceLoader.load("AttackSouth.png")),
				ImageIO.read(ResourceLoader.load("AttackEast.png")),
				ImageIO.read(ResourceLoader.load("AttackWest.png")) };
		monster = new BufferedImage[] { ImageIO.read(ResourceLoader.load("MonsterNorth.png")),
				ImageIO.read(ResourceLoader.load("MonsterSouth.png")),
				ImageIO.read(ResourceLoader.load("MonsterEst.png")),
				ImageIO.read(ResourceLoader.load("MonsterWest.png")),
				ImageIO.read(ResourceLoader.load("MAttackNorth.png")),
				ImageIO.read(ResourceLoader.load("MAttackSouth.png")),
				ImageIO.read(ResourceLoader.load("MAttackEast.png")),
				ImageIO.read(ResourceLoader.load("MAttackWest.png")) };
		ghost = new BufferedImage[] { ImageIO.read(ResourceLoader.load("SqueletonNorth.png")),
				ImageIO.read(ResourceLoader.load("SqueletonSouth.png")),
				ImageIO.read(ResourceLoader.load("SqueletonEst.png")),
				ImageIO.read(ResourceLoader.load("SqueletonWest.png")),
				ImageIO.read(ResourceLoader.load("SAttackNorth.png")),
				ImageIO.read(ResourceLoader.load("SAttackSouth.png")),
				ImageIO.read(ResourceLoader.load("SAttackEast.png")),
				ImageIO.read(ResourceLoader.load("SAttackWest.png")) };
		ground = ImageIO.read(ResourceLoader.load("ground.png"));
		won = ImageIO.read(ResourceLoader.load("bravo.png"));
		gameover = ImageIO.read(ResourceLoader.load("perdu.png"));
		damageEffect = ImageIO.read(ResourceLoader.load("damageEffect.png"));
		healthEffect = ImageIO.read(ResourceLoader.load("healthEffect.png"));
		treasureEffect = ImageIO.read(ResourceLoader.load("treasureEffect.png"));
		teleportationEffect = ImageIO.read(ResourceLoader.load("teleportationEffect.png"));
	}

	public static TextureFactory getInstance() throws IOException {
		if (instance == null)
			instance = new TextureFactory();

		return instance;
	}

	public BufferedImage get(String texture, int state) {
		switch (texture) {
		case "character":
			if (state >= 4)
				System.out.println(state);
			return character[state];
		case "monster":
			return monster[state];
		case "ghost":
			return ghost[state];
		}
		return null;
	}

	public BufferedImage get(String texture) throws Exception {
		switch (texture) {
		case "door":
			return door;
		case "key":
			return key;
		case "treasure":
			return treasure;
		case "ground":
			return ground;
		case "wall":
			return wall;
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
