package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import model.Monster;
import model.Pacman;
import model.PacmanGame;
import model.Character;
import org.junit.Before;
import org.junit.Test;
import engine.Cmd;

public class TestCharacter {

	PacmanGame game;
	Pacman player;
	ArrayList<Character> listM;

	@Before
	public void initialise() {
		game = new PacmanGame("");
		player = game.getPlayer();
		listM = new ArrayList<>();
		for (Character c : game.getMap().getCharacters()) {
			if (c instanceof Monster)
				listM.add(c);
		}
	}

	@Test
	public void testPlayerCollisionNoneWall() {
		game.evolve(Cmd.RIGHT);
		assertEquals("mur", 1, player.getPosition().x);
	}

	@Test
	public void testPlayerCollisionWall() {
		game.evolve(Cmd.UP);
		assertEquals("pas de mur", 1, player.getPosition().y);
	}

	@Test
	public void testMonsterPresent() {
		assertTrue("monstre pas présent", listM.size() == 1);
	}

}
