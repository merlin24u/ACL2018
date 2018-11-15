package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import model.Monster;
import model.Pacman;
import model.PacmanGame;
import model.RandomMovableArtificialIntelligence;
import model.TreasureEffect;
import model.TreasureEffectFactory;
import model.Character;
import org.junit.Before;
import org.junit.Test;
import engine.Cmd;

public class TestRandomMovableArtificialIntelligence {

	PacmanGame game;
	Pacman player;
	RandomMovableArtificialIntelligence rmai;

	@Before
	public void initialise() {
		game = new PacmanGame("");
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
