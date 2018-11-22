package model;

import java.util.ArrayList;

import dao.DAOFactory;
import engine.Cmd;
import engine.Game;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 * 
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {

	private Pacman player;
	private static boolean changeMap;
	private Map map;
	private int currentMap;
	private ArrayList<Map> listMap;
	private static boolean finished;

	/**
	 * constructeur avec fichier source pour la map
	 * 
	 */
	public PacmanGame(String source) {
		finished = false;

		try {
			map = DAOFactory.getAbstractDAOFactory(DAOFactory.XML).getMapDAO().load(source);
		} catch (Exception e) {
			e.printStackTrace();
			map = new Map();
		}

		player = new Pacman(map);
		map.setGame(this);
		map.addCharacter(player);
	}

	/**
	 * constructeur avec plusieurs niveaux
	 */
	public PacmanGame(String[] maps) {
		finished = false;
		changeMap = false;
		listMap = new ArrayList<>();
		Map m;

		for (int i = 0; i < maps.length; i++) {
			try {
				m = DAOFactory.getAbstractDAOFactory(DAOFactory.XML).getMapDAO().load(maps[i]);
			} catch (Exception e) {
				e.printStackTrace();
				m = new Map();
			}
			listMap.add(m);
		}

		currentMap = 0;
		map = listMap.get(currentMap);
		player = new Pacman(map);
		map.setGame(this);
		map.addCharacter(player);
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		if (commande != Cmd.IDLE) {
			player.evolve(commande);
			System.out.println("Pacman(" + player.getPosition().x + "," + player.getPosition().y + ")");
			System.out.println("Ecrire commande (Z,Q,S,D)");
		}
		map.update();
		Time.getInstance().update();

		if (changeMap) {
			currentMap++;
			if (currentMap == listMap.size()) {
				System.out.println("You've won !");
				finished = true;
			} else {
				map = listMap.get(currentMap);
				player.changeMap(map);
				map.setGame(this);
				map.addCharacter(player);
			}
		}
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public boolean changeMap() {
		if (changeMap) {
			changeMap = false;
			return true;
		} else
			return false;
	}

	public void setFinished() {
		finished = true;
	}

	public int getMaxH() {
		return map.getHeigh() - 1;
	}

	public int getMaxW() {
		return map.getWidth() - 1;
	}

	public Pacman getPlayer() {
		return player;
	}

	public Map getMap() {
		return map;
	}

	public static void updateMap() {
		changeMap = true;
	}
}
