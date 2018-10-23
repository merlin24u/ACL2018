package dao;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import model.ECollisionType;
import model.Map;
import org.w3c.dom.Element;

public class MapXmlDAO implements MapDAO {

	private static MapXmlDAO instance = null;

	private MapXmlDAO() {
	}

	@Override
	public Map load(String f) throws Exception {
		int wallCollision = ECollisionType.WALL.getValue();
		int noneCollision = ECollisionType.NONE.getValue();
		int[][] grid = null;
		File file = new File(f);
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		NodeList nList = doc.getElementsByTagName("size");
		Node nNode = nList.item(0);

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			int x = Integer.parseInt(eElement.getElementsByTagName("sizeX").item(0).getTextContent());
			int y = Integer.parseInt(eElement.getElementsByTagName("sizeY").item(0).getTextContent());
			grid = new int[x][y];
		}

		nList = doc.getElementsByTagName("data");
		nNode = nList.item(0);

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			Element eElement2 = null;
			for (int i = 0; i < eElement.getElementsByTagName("tab").getLength(); i++) {
				eElement2 = (Element) eElement.getElementsByTagName("tab").item(i);
				for (int j = 0; j < eElement2.getElementsByTagName("tab2D").getLength(); j++) {
					switch (eElement2.getElementsByTagName("tab2D").item(j).getTextContent()) {
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

		return new Map(grid);
	}

	@Override
	public void save(Map map) {
	}

	public static MapXmlDAO getInstance() {
		if (instance == null) {
			instance = new MapXmlDAO();
		}
		return instance;
	}

}
