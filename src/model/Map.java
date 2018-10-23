package model;

import java.awt.Point;
import java.util.ArrayList;

public class Map implements IUpdate{

	public static int nbMap;
	private int id;
	private int[][] grid;
	private ArrayList<Character> characters;

	public Map() {
		id = nbMap;
		nbMap++;
		//tab = new int[100][100];
		// Récupération des valeurs des collisions
		int wallCollision = ECollisionType.WALL.getValue();
		int noneCollision = ECollisionType.NONE.getValue();
		// Création de la map en dur.
		this.grid =new int[][]{
			  { wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision },
			  { 0, 0, 0, 0, 0, 0, 0, 0, 0, wallCollision },
			  { wallCollision, 0, 0, wallCollision, 0, 0, 0, 0, 0, wallCollision },
			  { wallCollision, 0, 0, 0, 0, 0, 0, 0, 0, wallCollision },
			  { wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision }
			};
		this.characters = new ArrayList<Character>();
		// TODO: TEMPORAIRE !
		Monster m = new Monster(new RandomMovableArtificialIntelligence(),5,5,0,new GroundCollisionHandler(this),1,1,new Point(3,2));
		this.characters.add(m);
	}

	public int getHeigh() {
		return grid.length;
	}

	public int getWidth() {
		return grid[0].length;
	}

	public int get(int x, int y) {
		return grid[y][x];
	}

	public void addCharacter(Character character) {
		this.characters.add(character);
	}
	
	public ArrayList<Character> getCharacters(){
		return characters;
	}
	
	@Override
	public void update() {
		for(Character character: characters){
			character.update();
		}
	}
	

}
