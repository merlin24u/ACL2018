package test;

import model.*;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class TestMonster {

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

        GroundCollisionHandler groundCollisionHandler = new GroundCollisionHandler(map);

        int movingSpeedXMax = 1;
        int movingSpeedYMax = 1;

        Point position = new Point(5, 5);

        MovableArtificialIntelligence movableArtificialIntelligence = new RandomMovableArtificialIntelligence(map);

        monster = new Monster(null, movableArtificialIntelligence, currentHP, maximumHP, defensePoints, groundCollisionHandler, movingSpeedXMax, movingSpeedYMax, movingSpeedYMax, position) {
        };
    }

    @Test
    public void testTakeDamage() {

        initialize("src/test/res/test_map_1.xml");

        int beginHP = monster.getCurrentHp();

        assertEquals(beginHP, monster.getCurrentHp());
        pacman.attack(monster);
        assertEquals(beginHP - 1, monster.getCurrentHp());

    }

    @Test
    public void testTakeDamageOnCollision() {

        initialize("src/test/res/test_map_1.xml");

        int beginHP = monster.getCurrentHp();

        assertEquals(beginHP, monster.getCurrentHp());
        pacman.onCollision(monster);
        monster.update();
        assertEquals("Le monstre n'a pas obtenu des dégâts", beginHP - 1, monster.getCurrentHp());

    }


    @Test
    public void testDestroyMonster() {

        initialize("src/test/res/test_map_1.xml");

        int beginHP = monster.getCurrentHp();
        for (int i = 0; i < beginHP; i++) {
            pacman.onCollision(monster);
            monster.update();
        }

        assertEquals("Le monstre n'est pas ete detruit! Current life:" + monster.getCurrentHp(), true, monster.isToDestroy());
    }
}
