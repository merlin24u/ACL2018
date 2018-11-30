package test;

import static org.junit.Assert.*;
import java.util.ArrayList;

import model.PacmanGame;
import model.effect.factory.TreasureEffectFactory;
import model.movable.character.Character;
import model.movable.character.Pacman;

import org.junit.Before;
import org.junit.Test;
import engine.Cmd;

public class TestTreasureEffectFactory {

	PacmanGame game;
	Pacman player;
	int startMoneyAmount;
	TreasureEffectFactory tef;

	@Before
	public void initialise() {
		game = new PacmanGame("");
		player = game.getPlayer();
		startMoneyAmount = player.getMoneyAmount();
		tef = new TreasureEffectFactory(10, 5);
	}

	@Test
	public void testTreasureEffectFactoryMoneyAmount() {
		assertEquals("la valeur attendue de l'effet n'est pas bonne", 10, tef.getMoneyAmount());
	}

	@Test
	public void testTreasureEffectFactoryTickDuration() {
		assertEquals("la durée attendue de l'effet n'est pas bonne", 5, tef.getTickDuration());
	}

	@Test
	public void testTreasureEffectFactoryApplyTo() {
		tef.applyTo(player);
		assertEquals("le joueur n'a pas reçu le bon nombre d'effets", 1, player.getEffectsSize());
		assertEquals("le joueur n'a pas reçu le bon effet", TreasureEffect.class, player.getEffect(0).getClass());
		assertEquals("l'effet créé n'a pas la bonne valeur d'argent", 10,
				((TreasureEffect) player.getEffect(0)).getMoneyAmount());
		assertEquals("l'effet créé n'a pas la bonne valeur de durée", 5, player.getEffect(0).getTickDuration());
	}
}
