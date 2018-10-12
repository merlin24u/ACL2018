package model;

public class Map {

	public static int nbMap;
	private int id;
	private int[][] tab;

	public Map() {
		id = nbMap;
		nbMap++;
		tab = new int[10][10];
	}

}
