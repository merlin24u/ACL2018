package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

	private Pacman joueur;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame(String source) {

		joueur = new Pacman();
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		if (commande != Cmd.IDLE) {
			joueur.evolve(commande);
			System.out.println("Pacman(" + joueur.getPosX() + ","
					+ joueur.getPosY() + ")");
			System.out.println("Ecrire commande (Z,Q,S,D)");
		}

	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}

}
