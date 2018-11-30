package test;

import static org.junit.Assert.*;
import java.util.ArrayList;

import model.PacmanGame;
import model.effect.TreasureEffect;
import model.movable.character.Character;
import model.movable.character.Pacman;

import org.junit.Before;
import org.junit.Test;
import engine.Cmd;

public class TestTreasureEffect {

	PacmanGame game;
	Pacman player;
	int startMoneyAmount;
	TreasureEffect te;

	@Before
	public void initialise() {
		game = new PacmanGame("");
		player = game.getPlayer();
		startMoneyAmount = player.getMoneyAmount();
		te = new TreasureEffect(player, 10, 2);

	}

	@Test
	public void testTreasureEffectCharacter() {
		assertEquals("n'a pas le bon character", player, te.getCharacter());
	}

	@Test
	public void testTreasureEffectMoneyAmount() {
		assertEquals("n'a pas 10 d'argent", 10, te.getMoneyAmount());
	}

	@Test
	public void testTreasureEffectTickDuration() {
		assertEquals("durée de l'effet n'est pas bon", 2, te.getTickDuration());
	}

	@Test
	public void testTreasureEffectFactoryApplyOnTime() {
		te.apply();
		assertEquals("durée de l'effet n'est pas bon", 1, te.getTickDuration());
		assertEquals("le character n'a pas reçu le bon montant d'argent", startMoneyAmount + 10,
				player.getMoneyAmount());
	}

	@Test
	public void testTreasureEffectFactoryApplyTwoTimes() {
		te.apply();
		te.apply();
		assertEquals("durée de l'effet n'est pas bon", 0, te.getTickDuration());
		assertEquals("le character n'a pas reçu le bon montant d'argent", startMoneyAmount + 20,
				player.getMoneyAmount());
	}

	@Test
	public void testTreasureEffectFactoryApplyThreeTimes() {
		te.apply();
		te.apply();
		te.apply();
		assertEquals("durée de l'effet n'est pas bon", -1, te.getTickDuration());
		assertEquals("le character n'a pas reçu le bon montant d'argent", startMoneyAmount + 20,
				player.getMoneyAmount());
	}
}
