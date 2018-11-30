package test;

import model.PacmanGame;
import model.movable.character.monster.Monster;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

import model.movable.character.Character;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPacmanGame {

	PacmanGame game;
	ArrayList<Character> listM;

	@Before
	public void initialise() {
		game = new PacmanGame("test_map.xml");

		listM = new ArrayList<>();
		for (Character c : game.getMap().getCharacters()) {
			if (c instanceof Monster)
				listM.add(c);
		}
	}

	@Test
	public void checkSumOfWall() {

		int numberOfWallsInXML = 40;
		int numberOfWallsInMap = 0;

		for (int width = 0; width < game.getMap().getWidth(); width++) {
			for (int height = 0; height < game.getMap().getHeigh(); height++) {
				if (game.getMap().getValue(width, height) == 1) {
					numberOfWallsInMap = numberOfWallsInMap + 1;
				}
			}
		}
		assertEquals("Le nombre de murs sur la map ne correspond pas au numéro de contrôle", numberOfWallsInXML,
				numberOfWallsInMap);
	}

	@Test
	public void checkSumOfEmptyFields() {

		int numberOfEmptyFieldsInXML = 60;
		int numberOfEmptyFieldsInMap = 0;

		for (int width = 0; width < game.getMap().getWidth(); width++) {
			for (int height = 0; height < game.getMap().getHeigh(); height++) {
				if (game.getMap().getValue(width, height) == 0) {
					numberOfEmptyFieldsInMap = numberOfEmptyFieldsInMap + 1;
				}
			}
		}
		assertEquals("Le nombre de champs vides sur la map ne correspond pas au numéro de contrôle",
				numberOfEmptyFieldsInXML, numberOfEmptyFieldsInMap);
	}

	@Test
	public void testMonsterPresent() throws IOException, SAXException, ParserConfigurationException {

		assertTrue("monstre pas présent", listM.size() == 1);
	}

}
