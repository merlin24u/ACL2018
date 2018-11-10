package model;

import java.awt.Point;
import java.util.ArrayList;

public class Map implements IUpdate {

	public static int nbMap;
	private int id;
	private int[][] grid;
	private Point start, finish;
	private ArrayList<Character> characters;
	private ArrayList<OnMoveOver> events;

	public Map() {
		int wallCollision = ECollisionType.WALL.getValue();
		int noneCollision = ECollisionType.NONE.getValue();
		this.characters = new ArrayList<Character>();
		this.events = new ArrayList<OnMoveOver>();
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

		start = new Point(0, 1);
		finish = start;

		TEMPORAIRE();

	}

	public Map(int[][] g, Point s, Point f) {
		id = nbMap;
		nbMap++;
		grid = g;
		start = s;
		finish = f;

		this.characters = new ArrayList<Character>();
		this.events = new ArrayList<OnMoveOver>();

		TEMPORAIRE();

	}

	// TODO: A supprimer
	private void TEMPORAIRE() {
		Monster m = new Monster(new RandomMovableArtificialIntelligence(), 5, 5, 0, new GroundCollisionHandler(this), 1,
				1, new Point(3, 2));
		this.characters.add(m);

		Item item1 = new Key("K01", "Clef verte");
		// Case qui donnera la clef
		ItemEffectFactory ief = new ItemEffectFactory(item1);
		OnMoveOver onMove1 = new SimpleOnMoveOver(this, new Point(1, 2), true, false, true);
		onMove1.addEffectFactory(ief);
		this.events.add(onMove1);

		// Sortie qui necessite la clef
		ArrayList<Item> itemsRequired = new ArrayList<Item>();
		itemsRequired.add(item1);
		ExitEffectFactory ef = new ExitEffectFactory(1);
		OnMoveOver onMove2 = new ItemRequiredOnMoveOver(this, finish, true, false, true, itemsRequired, true);
		onMove2.addEffectFactory(ef);
		this.events.add(onMove2);
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

	public Point getStart() {
		return start;
	}

	public void addCharacter(Character character) {
		this.characters.add(character);
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}

	public ArrayList<OnMoveOver> getEvents() {
		return events;
	}

	@Override
	public void update() {
		for (OnMoveOver omo : events) {
			omo.update();
		}
		for (Character character : characters) {
			character.update();
		}
	}

}
