package model;

public class Map {

	public static int nbMap;
	private int id;
	private int[][] grid;

	public Map() {
		id = nbMap;
		nbMap++;
		//tab = new int[100][100];
		// Récupération des valeurs des collisions
		int wallCollision = ECollisionType.WALL.getValue();
		int noneCollision = ECollisionType.NONE.getValue();
		// Création de la map en dur.
		grid =new int[][]{
			  { wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision },
			  { 0, 0, 0, 0, 0, 0, 0, 0, 0, wallCollision },
			  { wallCollision, 0, 0, wallCollision, 0, 0, 0, 0, 0, wallCollision },
			  { wallCollision, 0, 0, 0, 0, 0, 0, 0, 0, wallCollision },
			  { wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision }
			};
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
	

}
