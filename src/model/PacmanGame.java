package model;

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
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

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame(String source) {

		try {
			map = DAOFactory.getAbstractDAOFactory(DAOFactory.XML).getMapDAO().load(source);
		} catch (Exception e) {
			e.printStackTrace();
			map = new Map();
		}
		player = new Pacman(map);
		this.map.addCharacter(player);

		/*
		 * BufferedReader helpReader; try { helpReader = new BufferedReader(new
		 * FileReader(source)); String ligne; while ((ligne = helpReader.readLine()) !=
		 * null) { System.out.println(ligne); } helpReader.close(); } catch (IOException
		 * e) { System.out.println("Help not available"); }
		 */
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
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
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

}
