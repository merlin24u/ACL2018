package test;

import model.*;
import org.junit.Test;
import java.awt.*;
import model.movable.ai.MovableArtificialIntelligence;
import model.movable.ai.RandomMovableArtificialIntelligence;
import model.movable.character.Pacman;
import model.movable.character.monster.Monster;
import model.movable.character.monster.MonsterFactory;
import model.movable.collision.ECollisionType;
import model.movable.collision.GroundCollisionHandler;

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
		// map.addCharacter(monster);
	}

	@Test
	public void testTakeDamage() {

		initialize("test_map.xml");

		int beginHP = monster.getCurrentHp();
		assertEquals(beginHP, monster.getCurrentHp());
		pacman.attack(monster);
		monster.update();
		assertEquals(beginHP - 1, monster.getCurrentHp());
		assertEquals(beginHP, monster.getCurrentHp());
		pacman.onCollision(monster);
		monster.update();
		assertEquals("Le monstre n'a pas obtenu des dégâts", beginHP - 1, monster.getCurrentHp());
	}

	@Test
	public void testDestroyMonster() {
		initialize("test_map.xml");

		int beginHP = monster.getCurrentHp();
		for (int i = 0; i < beginHP; i++) {
			pacman.attack(monster);
			monster.update();
		}

		assertEquals("Le monstre n'est pas ete detruit! Current life:" + monster.getCurrentHp(), true,
				monster.isToDestroy());
	}
}
