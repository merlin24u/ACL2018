package test;

import static org.junit.Assert.*;

import java.awt.*;
import java.io.IOException;

import model.*;
import org.junit.Test;
import engine.Cmd;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class TestPacman {

    PacmanGame test_game;
    Pacman pacman;
    Monster monster;

    private Point startPoint;

    public void initialize(String pathMap) {

        int currentHP = 5;
        int maximumHP = 5;
        int defensePoints = 1;

        test_game = new PacmanGame(pathMap);
        Map map = test_game.getMap();
        pacman = new Pacman(map);
        startPoint = new Point(pacman.getPosition());

        GroundCollisionHandler groundCollisionHandler = new GroundCollisionHandler(map, null);

        int movingSpeedXMax = 1;
        int movingSpeedYMax = 1;

        Point position = new Point(5, 5);

        MovableArtificialIntelligence movableArtificialIntelligence = new RandomMovableArtificialIntelligence(map);

		monster = new Monster(null, movableArtificialIntelligence, currentHP, maximumHP, defensePoints, groundCollisionHandler, movingSpeedXMax, movingSpeedYMax, movingSpeedYMax, position, null) {
        };
    }

    @Test
    public void testPlayerCollisionNoneWallRight() {

        initialize("src/test/res/test_map_1.xml");
        test_game.evolve(Cmd.RIGHT);
        assertEquals("mur", startPoint.x + 1, pacman.getPosition().x);
    }

    @Test
    public void testPlayerCollisionNoneWallLeft() {

        initialize("src/test/res/test_map_1.xml");
        test_game.evolve(Cmd.LEFT);
        assertEquals("mur", startPoint.x - 1, pacman.getPosition().x);
    }

    @Test
    public void testPlayerCollisionNoneWallUp() {

        initialize("src/test/res/test_map_1.xml");
        test_game.evolve(Cmd.UP);
        assertEquals("mur", startPoint.y - 1, pacman.getPosition().y);
    }

    @Test
    public void testPlayerCollisionNoneWallDown() {

        initialize("src/test/res/test_map_1.xml");
        test_game.evolve(Cmd.DOWN);
        assertEquals("mur", startPoint.y + 1, pacman.getPosition().y);
    }

    @Test
    public void testPlayerCollisionWallRight() {

        initialize("src/test/res/test_map_2.xml");
        test_game.evolve(Cmd.RIGHT);
        assertEquals("pas de mur", startPoint.x, pacman.getPosition().x);
    }

    @Test
    public void testPlayerCollisionWallLeft() {

        initialize("src/test/res/test_map_2.xml");
        test_game.evolve(Cmd.LEFT);
        assertEquals("pas de mur", startPoint.x, pacman.getPosition().x);

    }

    @Test
    public void testPlayerCollisionWallUp() {

        initialize("src/test/res/test_map_2.xml");
        test_game.evolve(Cmd.UP);
        assertEquals("pas de mur", startPoint.y, pacman.getPosition().y);
    }

    @Test
    public void testPlayerCollisionWallDown() {

        initialize("src/test/res/test_map_2.xml");
        test_game.evolve(Cmd.DOWN);
        assertEquals("pas de mur", startPoint.y, pacman.getPosition().y);
    }

    @Test
    public void testPlayerGetMoneyAmount() {

        initialize("src/test/res/test_map_1.xml");
        assertEquals(pacman.getMoneyAmount(), 0);
    }

    @Test
    public void testPlayerIncreaseMoneyAmount() {

        initialize("src/test/res/test_map_1.xml");

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

        initialize("src/test/res/test_map_1.xml");

        String keyId = "First key";
        String keyName = "Powerful Key";

        Key key = new Key(keyId, keyName);
        pacman.addItem(key);
        assertEquals("le joueur n'a pas de clé", pacman.getItem(keyId).getName(), keyName);
    }

    @Test
    public void testPlayerAddItemAddSameKey() {

        initialize("src/test/res/test_map_1.xml");

        String keyId = "First key";
        String keyName = "Powerful Key 1";

        Key key = new Key(keyId, keyName);
        pacman.addItem(key);
        pacman.addItem(key);

        assertEquals("le joueur n'a pas de clé 1", pacman.getItem(keyId).getName(), keyName);
    }

    @Test
    public void testPlayerAddItemAddTwoKey() {

        initialize("src/test/res/test_map_1.xml");

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

        initialize("src/test/res/test_map_1.xml");

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

        initialize("src/test/res/test_map_1.xml");

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

        initialize("src/test/res/test_map_1.xml");

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

        initialize("src/test/res/test_map_1.xml");

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

        initialize("src/test/res/test_map_1.xml");

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

        initialize("src/test/res/test_map_1.xml");

        int beginHP = pacman.getCurrentHp();

        assertEquals(beginHP, pacman.getCurrentHp());
        monster.attack(pacman);
        assertEquals(beginHP - 1, pacman.getCurrentHp());

    }

    @Test
    public void testTakeDamageOnCollision() {

        initialize("src/test/res/test_map_1.xml");

        int beginHP = pacman.getCurrentHp();

        assertEquals(beginHP, pacman.getCurrentHp());
        monster.onCollision(pacman);
        assertEquals("Le pacman n'a pas obtenu des dégâts", beginHP - 1, pacman.getCurrentHp());

    }

    @Test
    public void testDestroyPacman() {

        initialize("src/test/res/test_map_1.xml");

        pacman.applyDamages(pacman.getCurrentHp());
        pacman.update();

        assertEquals("Le pacman n'est pas ete detruit!", true, pacman.isToDestroy());
    }

    @Test
    public void testDestroyPacmanOnCollision() {

        initialize("src/test/res/test_map_1.xml");

        int beginHP = pacman.getCurrentHp();
        for (int i = 0; i < beginHP; i++) {
            monster.onCollision(pacman);
            pacman.update();
        }

        assertEquals("Le pacman n'est pas ete detruit! Current life:" + pacman.getCurrentHp(), true, pacman.isToDestroy());
    }

}
