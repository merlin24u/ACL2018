package model;

import java.awt.Point;
import java.util.ArrayList;

public class Map implements IUpdate {

	public static int nbMap;
	private int id;
	private int[][] grid;
	private ArrayList<Character> characters;
	private ArrayList<OnMoveOver> events;

	public Map() {
		int wallCollision = ECollisionType.WALL.getValue();
		int noneCollision = ECollisionType.NONE.getValue();
		this.characters = new ArrayList<Character>();
		this.events = new ArrayList<OnMoveOver>();
		// Creation de la map en dur.
		grid = new int[][] {
				{ wallCollision, wallCollision, wallCollision, wallCollision,
						wallCollision, wallCollision, wallCollision,
						wallCollision, wallCollision, wallCollision },
				{ noneCollision, noneCollision, noneCollision, noneCollision,
						noneCollision, noneCollision, noneCollision,
						noneCollision, noneCollision, wallCollision },
				{ wallCollision, noneCollision, noneCollision, wallCollision,
						noneCollision, noneCollision, noneCollision,
						noneCollision, noneCollision, wallCollision },
				{ wallCollision, noneCollision, noneCollision, noneCollision,
						noneCollision, noneCollision, noneCollision,
						noneCollision, noneCollision, wallCollision },
				{ wallCollision, wallCollision, wallCollision, wallCollision,
						wallCollision, wallCollision, wallCollision,
						wallCollision, wallCollision, wallCollision } };

		TEMPORAIRE();

	}

	public Map(int[][] g) {
		id = nbMap;
		nbMap++;
		grid = g;

		this.characters = new ArrayList<Character>();
		this.events = new ArrayList<OnMoveOver>();

		TEMPORAIRE();
		
	}

	// TODO: A supprimer
	private void TEMPORAIRE() {
		Monster m = new Monster(new RandomMovableArtificialIntelligence(), 5,
				5, 0, new GroundCollisionHandler(this), 1, 1, new Point(3, 2));
		this.characters.add(m);


		Item item1 = new Key("K01","Clef verte");
		// Case qui donnera la clef
		ItemEffectFactory ief = new ItemEffectFactory(item1);
		OnMoveOver omo1 = new SimpleOnMoveOver(this, new Point(1, 2), true, false,
				true);
		omo1.addEffectFactory(ief);
		this.events.add(omo1);
		
		// Case qui necessite la clef
		ArrayList<Item> itemsRequired = new ArrayList<Item>();
		itemsRequired.add(item1);
		TreasureEffectFactory tf = new TreasureEffectFactory(10, 3);
		OnMoveOver omo2 = new ItemRequiredOnMoveOver(this, new Point(2, 1), true, false,
				true, itemsRequired, true);
		omo2.addEffectFactory(tf);
		this.events.add(omo2);
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

	public void addCharacter(Character character) {
		this.characters.add(character);
	}

	public ArrayList<Character> getCharacters() {
		return characters;
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
