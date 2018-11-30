package test;

import model.*;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class TestMonster {

    PacmanGame test_game;
    Pacman pacman;
    Monster monster;
    Map map;

    private Point startPoint;
    public void initialize(String pathMap) {

        test_game = new PacmanGame(pathMap);
        map = test_game.getMap();
        pacman = new Pacman(map);
        startPoint = new Point(pacman.getPosition());

        monster = MonsterFactory.getInstance().createMonster("warrior", map);
//        map.addCharacter(monster);
    }

    @Test
    public void testTakeDamage() {

        initialize("src/test/res/test_map_1.xml");

        int beginHP = monster.getCurrentHp();

        assertEquals(beginHP, monster.getCurrentHp());
        pacman.attack(monster);
        monster.update();
        assertEquals(beginHP - 1, monster.getCurrentHp());

    }

    @Test
    public void testDestroyMonster() {

        initialize("src/test/res/test_map_1.xml");

        int beginHP = monster.getCurrentHp();
        for (int i = 0; i < beginHP; i++) {
            pacman.attack(monster);
            monster.update();
        }

        assertEquals("Le monstre n'est pas ete detruit! Current life:" + monster.getCurrentHp(), true, monster.isToDestroy());
    }
}
