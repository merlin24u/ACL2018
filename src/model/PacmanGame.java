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
	private GameState gameState;

	/**
	 * constructeur avec fichier source pour la map
	 * 
	 */
	public PacmanGame(String source) {
		gameState = new GameState();

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
		gameState = new GameState();
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
			if(gameState.equalsTo(GameState.State.RUNNING)) {
				if (commande != Cmd.IDLE) {
					player.evolve(commande);
					System.out.println("Pacman(" + player.getPosition().x + "," + player.getPosition().y + ")");
					System.out.println("Ecrire commande (Z,Q,S,D)");
				}
				map.update();
		
				if (changeMap) {
					currentMap++;
					if (currentMap == listMap.size()) {
						gameState.setState(GameState.State.WON);
					} else {
						map = listMap.get(currentMap);
						player.changeMap(map);
						map.setGame(this);
						map.addCharacter(player);
					}
				}
			}else if(gameState.equalsTo(GameState.State.STARTING)) {
				if(Time.getInstance().getTick() > 35) {
					gameState.setState(GameState.State.RUNNING);
				}
			}
			Time.getInstance().update();
	}

	public GameState getGameState() {
		return gameState;
	}
	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		return gameState.equalsTo(GameState.State.GAMEOVER) || gameState.equalsTo(GameState.State.WON);
	}

	@Override
	public boolean changeMap() {
		if (changeMap) {
			changeMap = false;
			return true;
		} else
			return false;
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
