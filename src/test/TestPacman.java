package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.*;
import model.Character;
import org.junit.Before;
import org.junit.Test;
import engine.Cmd;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TestPacman {

	PacmanGame game;
	Pacman player;

	int startPosX;
	int startPosY;
	int finishPosX;
	int finishPosY;

	public void initializeGame(String pathMap) throws ParserConfigurationException, IOException, SAXException {

		game = new PacmanGame(pathMap);
		player = game.getPlayer();

		File stocks = new File(pathMap);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(stocks);
		doc.getDocumentElement().normalize();

		NodeList startNodes = doc.getElementsByTagName("start");
		Node startNode = startNodes.item(0);

		if (startNode.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) startNode;
			startPosX = Integer.parseInt(getValue("posX", element));
			startPosY = Integer.parseInt(getValue("posY", element));
		}

		NodeList finishNodes = doc.getElementsByTagName("finish");
		Node finishNode = finishNodes.item(0);

		if (finishNode.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) finishNode;
			finishPosX = Integer.parseInt(getValue("posX", element));
			finishPosY = Integer.parseInt(getValue("posY", element));
		}

	}

	static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}

	@Test
	public void testPlayerCollisionNoneWallRight() throws IOException, SAXException, ParserConfigurationException {

		initializeGame("src/test/res/test_map_1.xml");

		game.evolve(Cmd.RIGHT);
		assertEquals("mur", startPosX + 1, player.getPosition().x);
	}

	@Test
	public void testPlayerCollisionNoneWallLeft() throws IOException, SAXException, ParserConfigurationException {

		initializeGame("src/test/res/test_map_1.xml");

		game.evolve(Cmd.LEFT);
		assertEquals("mur", startPosX - 1, player.getPosition().x);
	}

	@Test
	public void testPlayerCollisionNoneWallUp() throws IOException, SAXException, ParserConfigurationException {

		initializeGame("src/test/res/test_map_1.xml");

		game.evolve(Cmd.UP);
		assertEquals("mur", startPosY - 1, player.getPosition().y);
	}

	@Test
	public void testPlayerCollisionNoneWallDown() throws IOException, SAXException, ParserConfigurationException {

		initializeGame("src/test/res/test_map_1.xml");

		game.evolve(Cmd.DOWN);
		assertEquals("mur", startPosY + 1, player.getPosition().y);
	}

	@Test
	public void testPlayerCollisionWallRight() throws IOException, SAXException, ParserConfigurationException {

		initializeGame("src/test/res/test_map_2.xml");

		game.evolve(Cmd.RIGHT);
		assertEquals("pas de mur", startPosX, player.getPosition().x);
	}

	@Test
	public void testPlayerCollisionWallLeft() throws IOException, SAXException, ParserConfigurationException {

		initializeGame("src/test/res/test_map_2.xml");

		game.evolve(Cmd.LEFT);
		assertEquals("pas de mur", startPosX, player.getPosition().x);
	}

	@Test
	public void testPlayerCollisionWallUp() throws IOException, SAXException, ParserConfigurationException {

		initializeGame("src/test/res/test_map_2.xml");

		game.evolve(Cmd.UP);
		assertEquals("pas de mur", startPosY, player.getPosition().y);
	}

	@Test
	public void testPlayerCollisionWallDown() throws IOException, SAXException, ParserConfigurationException {

		initializeGame("src/test/res/test_map_2.xml");

		game.evolve(Cmd.DOWN);
		assertEquals("pas de mur", startPosY, player.getPosition().y);
	}

	@Test
	public void testPlayerGetMoneyAmount() throws IOException, SAXException, ParserConfigurationException {
		initializeGame("src/test/res/test_map_1.xml");

		assertEquals(player.getMoneyAmount(), 0);
	}

	@Test
	public void testPlayerIncreaseMoneyAmount() throws IOException, SAXException, ParserConfigurationException {

		int beginMoney = 0;
		int endMoney = 10;

		initializeGame("src/test/res/test_map_1.xml");

		assertEquals(player.getMoneyAmount(), beginMoney);

		player.increaseMoneyAmount(endMoney);

		assertEquals(player.getMoneyAmount(), endMoney);
	}

	@Test
	public void testPlayerAddItemAddOneKey() throws IOException, SAXException, ParserConfigurationException {
		initializeGame("src/test/res/test_map_1.xml");

		String keyId = "First key";
		String keyName = "Powerful Key";

		Key key = new Key(keyId, keyName);
		player.addItem(key);
		assertEquals("le joueur n'a pas de clé", player.getItem(keyId).getName(), keyName);
	}

	@Test
	public void testPlayerAddItemAddSameKey() throws IOException, SAXException, ParserConfigurationException {
		initializeGame("src/test/res/test_map_1.xml");

		String keyId = "First key";
		String keyName = "Powerful Key 1";

		Key key = new Key(keyId, keyName);
		player.addItem(key);
		player.addItem(key);

		assertEquals("le joueur n'a pas de clé 1", player.getItem(keyId).getName(), keyName);
	}

	@Test
	public void testPlayerAddItemAddTwoKey() throws IOException, SAXException, ParserConfigurationException {
		initializeGame("src/test/res/test_map_1.xml");

		String keyId_1 = "First key";
		String keyName_1 = "Powerful Key 1";

		String keyId_2 = "Second key";
		String keyName_2 = "Powerful Key 2";

		Key key_1 = new Key(keyId_1, keyName_1);
		Key key_2 = new Key(keyId_2, keyName_2);
		player.addItem(key_1);
		player.addItem(key_2);

		assertEquals("le joueur n'a pas de clé 1", player.getItem(keyId_1).getName(), keyName_1);
		assertEquals("le joueur n'a pas de clé 2", player.getItem(keyId_2).getName(), keyName_2);
	}

	@Test
	public void testPlayerAddItemAddTwoKeysWithSameName()
			throws IOException, SAXException, ParserConfigurationException {
		initializeGame("src/test/res/test_map_1.xml");

		String keyId_1 = "First key";
		String keyName_1 = "Powerful Key 1";

		String keyId_2 = "Second key";
		String keyName_2 = "Powerful Key 1";

		Key key_1 = new Key(keyId_1, keyName_1);
		Key key_2 = new Key(keyId_2, keyName_2);
		player.addItem(key_1);
		player.addItem(key_2);

		assertEquals("le joueur n'a pas de clé 1", player.getItem(keyId_1).getName(), keyName_1);
		assertEquals("le joueur n'a pas de clé 2", player.getItem(keyId_2).getName(), keyName_2);
	}

	@Test
	public void testPlayerAddItemAddTwoKeysWithSameId() throws IOException, SAXException, ParserConfigurationException {
		initializeGame("src/test/res/test_map_1.xml");

		String keyId_1 = "First key";
		String keyName_1 = "Powerful Key 1";

		String keyId_2 = "First key";
		String keyName_2 = "Powerful Key 2";

		Key key_1 = new Key(keyId_1, keyName_1);
		Key key_2 = new Key(keyId_2, keyName_2);
		player.addItem(key_1);
		player.addItem(key_2);

		assertEquals("le joueur n'a pas de clé 1", player.getItem(keyId_1).getName(), keyName_1);
		assertNotEquals("le joueur n'a pas de clé 2", player.getItem(keyId_2).getName(), keyName_2);
	}

	@Test
	public void testPlayerRemoveItem() throws IOException, SAXException, ParserConfigurationException {
		initializeGame("src/test/res/test_map_1.xml");

		String keyId = "First key";
		String keyName = "Powerful Key 1";

		Key key = new Key(keyId, keyName);
		player.addItem(key);
		assertEquals("le joueur n'a pas de clé", player.getItem(keyId).getName(), keyName);

		player.removeItem(key);
		assertNull("Le clé de joueur n'a pas été supprimer!", player.getItem(keyId));
	}

	@Test
	public void testPlayerRemoveTwoItemsWithTheSameKey()
			throws IOException, SAXException, ParserConfigurationException {
		initializeGame("src/test/res/test_map_1.xml");

		String keyId = "First key";
		String keyName = "Powerful Key 1";

		Key key = new Key(keyId, keyName);
		player.addItem(key);
		player.addItem(key);
		assertEquals("le joueur n'a pas de clé", player.getItem(keyId).getName(), keyName);

		player.removeItem(key);
		assertNull("Seulement un clé a été supprimer!", player.getItem(keyId));
	}

	@Test
	public void testPlayerRemoveTwoItemsWithTheSameId() throws IOException, SAXException, ParserConfigurationException {
		initializeGame("src/test/res/test_map_1.xml");

		String keyId_1 = "First key";
		String keyName_1 = "Powerful Key 1";

		String keyId_2 = "First key";
		String keyName_2 = "Powerful Key 1";

		Key key_1 = new Key(keyId_1, keyName_1);
		Key key_2 = new Key(keyId_2, keyName_2);

		player.addItem(key_1);
		player.addItem(key_2);

		assertEquals("le joueur n'a pas de clé", player.getItem(keyId_1).getName(), keyName_1);
		assertEquals("le joueur n'a pas de clé", player.getItem(keyId_2).getName(), keyName_2);

		player.removeItem(key_1);
		assertNull("Seulement un clé a été supprimer!", player.getItem(keyId_1));
	}
}
