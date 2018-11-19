package start;

import model.PacmanPainter;
import engine.GameEngineGraphical;
import model.EDirection;
import model.PacmanController;
import model.PacmanGame;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame("res/map.xml");
		PacmanPainter painter = new PacmanPainter(game.getMap());
		PacmanController controller = new PacmanController();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter,
				controller);
		engine.run();
	}

}
