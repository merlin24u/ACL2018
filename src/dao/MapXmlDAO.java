package dao;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import model.movable.collision.ECollisionType;
import model.Map;

import org.w3c.dom.Element;

public class MapXmlDAO implements MapDAO {

	private static MapXmlDAO instance = null;

	public class TmpType {
		private int x, y;
		private String type;

		public TmpType(int x, int y, String t) {
			this.x = x;
			this.y = y;
			type = t;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public String getType() {
			return type;
		}
	}

	private MapXmlDAO() {
	}

	@Override
	public Map load(String f) throws Exception {
		int wallCollision = ECollisionType.WALL.getValue();
		int noneCollision = ECollisionType.NONE.getValue();
		int[][] grid = null;
		int sizeX, sizeY;
		Point start = null, finish = null;
		ArrayList<TmpType> items = new ArrayList<>();
		ArrayList<TmpType> monsters = new ArrayList<>();
		ArrayList<TmpType> effects = new ArrayList<>();

		File file = new File(f);
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document doc = dBuilder.parse(file);

		NodeList nList = doc.getElementsByTagName("start");
		Node nNode = nList.item(0);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			int x = Integer.parseInt(eElement.getElementsByTagName("posX")
					.item(0).getTextContent());
			int y = Integer.parseInt(eElement.getElementsByTagName("posY")
					.item(0).getTextContent());
			start = new Point(x, y);
		}

		nList = doc.getElementsByTagName("finish");
		nNode = nList.item(0);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			int x = Integer.parseInt(eElement.getElementsByTagName("posX")
					.item(0).getTextContent());
			int y = Integer.parseInt(eElement.getElementsByTagName("posY")
					.item(0).getTextContent());
			finish = new Point(x, y);
		}

		nList = doc.getElementsByTagName("size");
		nNode = nList.item(0);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			sizeX = Integer.parseInt(eElement.getElementsByTagName("sizeX")
					.item(0).getTextContent());
			sizeY = Integer.parseInt(eElement.getElementsByTagName("sizeY")
					.item(0).getTextContent());
			grid = new int[sizeY][sizeX];
		}

		nList = doc.getElementsByTagName("data");
		nNode = nList.item(0);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			Element eElement2 = null;
			for (int i = 0; i < eElement.getElementsByTagName("tab")
					.getLength(); i++) {
				eElement2 = (Element) eElement.getElementsByTagName("tab")
						.item(i);
				for (int j = 0; j < eElement2.getElementsByTagName("tab2D")
						.getLength(); j++) {
					switch (eElement2.getElementsByTagName("tab2D").item(j)
							.getTextContent()) {
					case "wallCollision":
						grid[i][j] = wallCollision;
						break;
					case "noneCollision":
						grid[i][j] = noneCollision;
						break;
					}
				}
			}
		}

		nList = doc.getElementsByTagName("items");
		nNode = nList.item(0);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			Element eElement2 = null;

			for (int i = 0; i < eElement.getElementsByTagName("item")
					.getLength(); i++) {
				eElement2 = (Element) eElement.getElementsByTagName("item")
						.item(i);
				int x = Integer.parseInt(eElement2.getElementsByTagName("posX")
						.item(0).getTextContent());
				int y = Integer.parseInt(eElement2.getElementsByTagName("posY")
						.item(0).getTextContent());
				String type = eElement2.getElementsByTagName("type").item(0)
						.getTextContent();
				items.add(new TmpType(x, y, type));
			}
		}

		nList = doc.getElementsByTagName("effects");
		nNode = nList.item(0);
		if (nNode != null && nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			Element eElement2 = null;

			for (int i = 0; i < eElement.getElementsByTagName("effect")
					.getLength(); i++) {
				eElement2 = (Element) eElement.getElementsByTagName("effect")
						.item(i);
				int x = Integer.parseInt(eElement2.getElementsByTagName("posX")
						.item(0).getTextContent());
				int y = Integer.parseInt(eElement2.getElementsByTagName("posY")
						.item(0).getTextContent());
				String type = eElement2.getElementsByTagName("type").item(0)
						.getTextContent();
				effects.add(new TmpType(x, y, type));
			}
		}

		nList = doc.getElementsByTagName("monsters");
		nNode = nList.item(0);
		if (nNode != null && nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			Element eElement2 = null;

			for (int i = 0; i < eElement.getElementsByTagName("monster")
					.getLength(); i++) {
				eElement2 = (Element) eElement.getElementsByTagName("monster")
						.item(i);
				int x = Integer.parseInt(eElement2.getElementsByTagName("posX")
						.item(0).getTextContent());
				int y = Integer.parseInt(eElement2.getElementsByTagName("posY")
						.item(0).getTextContent());
				String type = eElement2.getElementsByTagName("type").item(0)
						.getTextContent();
				monsters.add(new TmpType(x, y, type));
			}
		}

		return new Map(grid, start, finish, items, effects, monsters);
	}

	@Override
	public void save(Map map) {
		// pas implemente
	}

	public static MapXmlDAO getInstance() {
		if (instance == null) {
			instance = new MapXmlDAO();
		}
		return instance;
	}

}
