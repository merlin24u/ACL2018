package test;

import static org.junit.Assert.*;
import model.PacmanGame;
import model.movable.ai.RandomMovableArtificialIntelligence;
import model.movable.character.Pacman;
import org.junit.Before;
import org.junit.Test;

public class TestRandomMovableArtificialIntelligence {

	PacmanGame game;
	Pacman player;
	RandomMovableArtificialIntelligence rmai;

	@Before
	public void initialise() {
		game = new PacmanGame("test_map.xml");
		player = game.getPlayer();
		rmai = new RandomMovableArtificialIntelligence(game.getMap(), 14);
	}

	@Test
	public void testRandomMovableArtificialIntelligenceExecuteOneTime() {
		player.resetCurrentSpeed();
		rmai.execute(player);

		assertEquals("Le currentSpeedX du player n'a pas la bonne valeur", 0, player.getCurrentSpeedX());
		assertEquals("Le currentSpeedY du player n'a pas la bonne valeur", 0, player.getCurrentSpeedY());
	}

	@Test
	public void testRandomMovableArtificialIntelligenceExecuteTwoTimes() {
		rmai.execute(player);
		player.resetCurrentSpeed();
		rmai.execute(player);
		assertEquals("Le currentSpeedX du player n'a pas la bonne valeur", 0, player.getCurrentSpeedX());
		assertEquals("Le currentSpeedY du player n'a pas la bonne valeur", 1, player.getCurrentSpeedY());
	}

	@Test
	public void testRandomMovableArtificialIntelligenceExecuteThreeTimes() {
		rmai.execute(player);
		rmai.execute(player);
		player.resetCurrentSpeed();
		rmai.execute(player);
		assertEquals("Le currentSpeedX du player n'a pas la bonne valeur", 1, player.getCurrentSpeedX());
		assertEquals("Le currentSpeedY du player n'a pas la bonne valeur", 0, player.getCurrentSpeedY());
	}

	@Test
	public void testRandomMovableArtificialIntelligenceExecuteFourTimes() {
		rmai.execute(player);
		rmai.execute(player);
		rmai.execute(player);
		player.resetCurrentSpeed();
		rmai.execute(player);
		assertEquals("Le currentSpeedX du player n'a pas la bonne valeur", -1, player.getCurrentSpeedX());
		assertEquals("Le currentSpeedY du player n'a pas la bonne valeur", 0, player.getCurrentSpeedY());
	}

	@Test
	public void testRandomMovableArtificialIntelligenceExecuteFiveTimes() {
		rmai.execute(player);
		rmai.execute(player);
		rmai.execute(player);
		rmai.execute(player);
		player.resetCurrentSpeed();
		rmai.execute(player);
		assertEquals("Le currentSpeedX du player n'a pas la bonne valeur", 0, player.getCurrentSpeedX());
		assertEquals("Le currentSpeedY du player n'a pas la bonne valeur", -1, player.getCurrentSpeedY());
	}
}
