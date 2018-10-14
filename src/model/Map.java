package model;

public class Map {

	public static int nbMap;
	private int id;
	private int[][] tab;

	public Map() {
		id = nbMap;
		nbMap++;
		tab = new int[100][100];
	}

	public int getHeigh() {
		return tab.length;
	}

	public int getWidth() {
		return tab[0].length;
	}

}
