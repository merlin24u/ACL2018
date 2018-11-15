package model;

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
	private Map map;
	private boolean finished;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame(String source) {
		finished = false;

		try {
			map = DAOFactory.getAbstractDAOFactory(DAOFactory.XML).getMapDAO()
					.load(source);
		} catch (Exception e) {
			e.printStackTrace();
			map = new Map();
		}
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
			System.out.println("Pacman(" + player.getPosition().x + ","
					+ player.getPosition().y + ")");
			System.out.println("Ecrire commande (Z,Q,S,D)");
		}
		map.update();
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		return finished;
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

}
