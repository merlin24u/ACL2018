package test;

import static org.junit.Assert.*;
import java.awt.*;
import model.Map;
import model.PacmanGame;
import model.item.Key;
import model.movable.character.Pacman;
import model.movable.character.monster.Monster;
import model.movable.character.monster.MonsterFactory;
import org.junit.Before;
import org.junit.Test;
import engine.Cmd;

public class TestPacman {

	PacmanGame test_game;
	Pacman pacman;
	Monster monster;
	Map map;

	private Point startPoint;

	@Before
	public void initialize() {

		test_game = new PacmanGame("test_map.xml");
		map = test_game.getMap();
		pacman = test_game.getPlayer();
		startPoint = new Point(pacman.getPosition());
		test_game.getGameState().setState(model.GameState.State.RUNNING);
		monster = MonsterFactory.getInstance().createMonster("warrior", map);
	}

	@Test
	public void testPlayerCollisionNoneWallRight() {

		startPoint = new Point(5, 5);
		test_game.getPlayer().setPosition(startPoint.x, startPoint.y);
		test_game.evolve(Cmd.RIGHT);
		assertEquals("mur", startPoint.x + 1, test_game.getPlayer().getPosition().x);

	}

	@Test
	public void testPlayerCollisionNoneWallLeft() {

		startPoint = new Point(5, 5);
		test_game.getPlayer().setPosition(startPoint.x, startPoint.y);
		test_game.evolve(Cmd.LEFT);
		assertEquals("mur", startPoint.x - 1, test_game.getPlayer().getPosition().x);
	}

	@Test
	public void testPlayerCollisionNoneWallUp() {

		startPoint = new Point(5, 5);
		test_game.getPlayer().setPosition(startPoint.x, startPoint.y);
		test_game.evolve(Cmd.UP);
		assertEquals("mur", startPoint.y - 1, test_game.getPlayer().getPosition().y);
	}

	@Test
	public void testPlayerCollisionNoneWallDown() {
		startPoint = new Point(6, 6);
		test_game.getPlayer().setPosition(startPoint.x, startPoint.y);
		test_game.getMap().getCharacters().get(1).setPosition(startPoint.x, startPoint.y);
		test_game.evolve(Cmd.DOWN);
		assertEquals("mur", startPoint.y + 1, test_game.getPlayer().getPosition().y);
	}

	@Test
	public void testPlayerCollisionWallRight() {

		test_game.evolve(Cmd.RIGHT);
		assertEquals("pas de mur", startPoint.x, test_game.getPlayer().getPosition().x);
	}

	@Test
	public void testPlayerCollisionWallLeft() {

		test_game.evolve(Cmd.LEFT);
		assertEquals("pas de mur", startPoint.x, test_game.getPlayer().getPosition().x);

	}

	@Test
	public void testPlayerCollisionWallUp() {

		test_game.evolve(Cmd.UP);
		assertEquals("pas de mur", startPoint.y, test_game.getPlayer().getPosition().y);
	}

	@Test
	public void testPlayerCollisionWallDown() {

		test_game.evolve(Cmd.DOWN);
		assertEquals("pas de mur", startPoint.y, test_game.getPlayer().getPosition().y);
	}

	@Test
	public void testPlayerGetMoneyAmount() {

		assertEquals(pacman.getMoneyAmount(), 0);
	}

	@Test
	public void testPlayerIncreaseMoneyAmount() {

		int beginMoney = 0;
		int endMoney = 10;
		assertEquals("Player have already money!", beginMoney, pacman.getMoneyAmount());

		pacman.increaseMoneyAmount(endMoney);

		assertEquals("The sum of money isn't equal!", endMoney, pacman.getMoneyAmount());

		pacman.increaseMoneyAmount(-endMoney);

		assertEquals("Couldn't reset amount of money correctly!", beginMoney, pacman.getMoneyAmount());
	}

	@Test
	public void testPlayerAddItemAddOneKey() {

		String keyId = "First key";
		String keyName = "Powerful Key";

		Key key = new Key(keyId, keyName);
		pacman.addItem(key);
		assertEquals("le joueur n'a pas de clé", pacman.getItem(keyId).getName(), keyName);
	}

	@Test
	public void testPlayerAddItemAddSameKey() {

		String keyId = "First key";
		String keyName = "Powerful Key 1";

		Key key = new Key(keyId, keyName);
		pacman.addItem(key);
		pacman.addItem(key);

		assertEquals("le joueur n'a pas de clé 1", pacman.getItem(keyId).getName(), keyName);
	}

	@Test
	public void testPlayerAddItemAddTwoKey() {

		String keyId_1 = "First key";
		String keyName_1 = "Powerful Key 1";
		String keyId_2 = "Second key";
		String keyName_2 = "Powerful Key 2";

		Key key_1 = new Key(keyId_1, keyName_1);
		Key key_2 = new Key(keyId_2, keyName_2);
		pacman.addItem(key_1);
		pacman.addItem(key_2);

		assertEquals("le joueur n'a pas de clé 1", pacman.getItem(keyId_1).getName(), keyName_1);
		assertEquals("le joueur n'a pas de clé 2", pacman.getItem(keyId_2).getName(), keyName_2);
	}

	@Test
	public void testPlayerAddItemAddTwoKeysWithSameName() {

		String keyId_1 = "First key";
		String keyName_1 = "Powerful Key 1";

		String keyId_2 = "Second key";
		String keyName_2 = "Powerful Key 1";

		Key key_1 = new Key(keyId_1, keyName_1);
		Key key_2 = new Key(keyId_2, keyName_2);
		pacman.addItem(key_1);
		pacman.addItem(key_2);

		assertEquals("le joueur n'a pas de clé 1", pacman.getItem(keyId_1).getName(), keyName_1);
		assertEquals("le joueur n'a pas de clé 2", pacman.getItem(keyId_2).getName(), keyName_2);
	}

	@Test
	public void testPlayerAddItemAddTwoKeysWithSameId() {

		String keyId_1 = "First key";
		String keyName_1 = "Powerful Key 1";
		String keyId_2 = "First key";
		String keyName_2 = "Powerful Key 2";

		Key key_1 = new Key(keyId_1, keyName_1);
		Key key_2 = new Key(keyId_2, keyName_2);
		pacman.addItem(key_1);
		pacman.addItem(key_2);

		assertEquals("le joueur n'a pas de clé 1", pacman.getItem(keyId_1).getName(), keyName_1);
		assertNotEquals("le joueur n'a pas de clé 2", pacman.getItem(keyId_2).getName(), keyName_2);
	}

	@Test
	public void testPlayerRemoveItem() {

		String keyId = "First key";
		String keyName = "Powerful Key 1";

		Key key = new Key(keyId, keyName);
		pacman.addItem(key);
		assertEquals("le joueur n'a pas de clé", pacman.getItem(keyId).getName(), keyName);

		pacman.removeItem(key);
		assertNull("Le clé de joueur n'a pas été supprimer!", pacman.getItem(keyId));
	}

	@Test
	public void testPlayerRemoveTwoItemsWithTheSameKey() {

		String keyId = "First key";
		String keyName = "Powerful Key 1";

		Key key = new Key(keyId, keyName);
		pacman.addItem(key);
		pacman.addItem(key);
		assertEquals("le joueur n'a pas de clé", pacman.getItem(keyId).getName(), keyName);

		pacman.removeItem(key);
		assertNull("Seulement un clé a été supprimer!", pacman.getItem(keyId));
	}

	@Test
	public void testPlayerRemoveTwoItemsWithTheSameId() {

		String keyId_1 = "First key";
		String keyName_1 = "Powerful Key 1";

		String keyId_2 = "First key";
		String keyName_2 = "Powerful Key 1";

		Key key_1 = new Key(keyId_1, keyName_1);
		Key key_2 = new Key(keyId_2, keyName_2);

		pacman.addItem(key_1);
		pacman.addItem(key_2);

		assertEquals("le joueur n'a pas de clé", pacman.getItem(keyId_1).getName(), keyName_1);
		assertEquals("le joueur n'a pas de clé", pacman.getItem(keyId_2).getName(), keyName_2);

		pacman.removeItem(key_1);
		assertNull("Seulement un clé a été supprimer!", pacman.getItem(keyId_1));
	}

	@Test
	public void testTakeDamage() {

		int beginHP = pacman.getCurrentHp();

		assertEquals(beginHP, pacman.getCurrentHp());
		monster.attack(pacman);
		pacman.update();
		assertEquals(beginHP - 1, pacman.getCurrentHp());

	}

	@Test
	public void testTakeDamageOnCollision() {

		int beginHP = pacman.getCurrentHp();

		assertEquals(beginHP, pacman.getCurrentHp());
		monster.onCollision(pacman);
		pacman.update();
		assertEquals("Le pacman n'a pas obtenu des dégâts", beginHP - 1, pacman.getCurrentHp());

	}

	@Test
	public void testDestroyPacman() {

		pacman.applyDamages(pacman.getCurrentHp());
		pacman.update();

		assertEquals("Le pacman n'est pas ete detruit!", true, pacman.isToDestroy());
	}

	@Test
	public void testDestroyPacmanOnCollision() {

		int beginHP = pacman.getCurrentHp();
		for (int i = 0; i < beginHP; i++) {
			monster.onCollision(pacman);
			pacman.update();
		}

		assertEquals("Le pacman n'est pas ete detruit! Current life:" + pacman.getCurrentHp(), true,
				pacman.isToDestroy());
	}

}
