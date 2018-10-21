package model;

public class Map {

	public static int nbMap;
	private int id;
	private int[][] grid;

	public Map() {
		id = nbMap;
		nbMap++;

		// Recuperation des valeurs des collisions
		int wallCollision = ECollisionType.WALL.getValue();
		int noneCollision = ECollisionType.NONE.getValue();

		// Creation de la map en dur.
		grid = new int[][] {
				{ wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision,
						wallCollision, wallCollision, wallCollision, wallCollision },
				{ noneCollision, noneCollision, noneCollision, noneCollision, noneCollision, noneCollision,
						noneCollision, noneCollision, noneCollision, wallCollision },
				{ wallCollision, noneCollision, noneCollision, wallCollision, noneCollision, noneCollision,
						noneCollision, noneCollision, noneCollision, wallCollision },
				{ wallCollision, noneCollision, noneCollision, noneCollision, noneCollision, noneCollision,
						noneCollision, noneCollision, noneCollision, wallCollision },
				{ wallCollision, wallCollision, wallCollision, wallCollision, wallCollision, wallCollision,
						wallCollision, wallCollision, wallCollision, wallCollision } };
	}

	public Map(int[][] g) {
		id = nbMap;
		nbMap++;
		grid = g;
	}

	public int getHeigh() {
		return grid.length;
	}

	public int getWidth() {
		return grid[0].length;
	}

	public int getValue(int x, int y) {
		return grid[y][x];
	}

}
