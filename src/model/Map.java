package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Map implements IUpdate {

	private int[][] grid;
	private Point start, finish;
	private ArrayList<Character> characters;
	private ArrayList<OnMoveOver> events;
	private ArrayList<Object> toDestroy;
	private PacmanGame game;

	public Map() {
		int wallCollision = ECollisionType.WALL.getValue();
		int noneCollision = ECollisionType.NONE.getValue();
		this.characters = new ArrayList<Character>();
		this.events = new ArrayList<OnMoveOver>();
		toDestroy = new ArrayList<Object>();

		// Creation de la map statique
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

		start = new Point(1, 1);
		finish = new Point(0, 1);

		generationStatique();

	}

	public Map(int[][] g, Point s, Point f, HashMap<String, Point> items, ArrayList<Point> monsters) {
		grid = g;
		start = s;
		finish = f;

		this.characters = new ArrayList<Character>();
		this.events = new ArrayList<OnMoveOver>();
		toDestroy = new ArrayList<Object>();

		for (Point p : monsters) {
			GroundCollisionHandler gch = new GroundCollisionHandler(this);
			MovableArtificialIntelligence mai = new FollowMovableArtificialIntelligence(this, gch);
			Monster m = new Monster(new DamageEffectFactory(1, 1), mai, 5, 5, 0, gch, 1, 1, 5, p);
			this.characters.add(m);
		}

		Point key = items.get("key");
		Item item1 = new Key("K01", "Exit key");
		// Case qui donnera la clef
		ItemEffectFactory ief = new ItemEffectFactory(item1);
		OnMoveOver onMove1 = new SimpleOnMoveOver(this, key, true, false, false);
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

	private void generationStatique() {
		GroundCollisionHandler gch = new GroundCollisionHandler(this);
		MovableArtificialIntelligence mai = new FollowMovableArtificialIntelligence(this, gch);
		Monster m = new Monster(new DamageEffectFactory(1, 1), mai, 5, 5, 0, gch, 1, 1, 5, new Point(3, 3));
		this.characters.add(m);

		Item item1 = new Key("K01", "Exit key");
		// Case qui donnera la clef
		ItemEffectFactory ief = new ItemEffectFactory(item1);
		OnMoveOver onMove1 = new SimpleOnMoveOver(this, new Point(1, 2), true, false, false);
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

	public ArrayList<Character> getCharactersOfType(String type) {
		ArrayList<Character> list = new ArrayList<Character>();
		for (Character c : characters) {
			if (c.isType(type))
				list.add(c);
		}
		return list;
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
			if (omo.isToDestroy())
				toDestroy.add(omo);
		}

		// Gestion des collisions
		for (int i = 0, l = characters.size(); i < l - 1; i++) {
			Character character1 = characters.get(i);
			for (int j = i + 1; j < l; j++) {
				Character character2 = characters.get(j);
				if (character1.getPosition().x == character2.getPosition().x
						&& character1.getPosition().y == character2.getPosition().y) {
					character1.onCollision(character2);
					character2.onCollision(character1);
				}
			}
		}

		for (Character character : characters) {
			character.update();
		}

		for (Character character : characters) {
			if (character.isToDestroy()) {
				if (character.isType("Player")) {
					System.out.println("You've lost !");
					game.setFinished();
				}
				toDestroy.add(character);
			}
		}

		for (Object omo : toDestroy) {
			if (events.contains(omo))
				events.remove(omo);
			else if (characters.remove(omo))
				characters.remove(omo);
		}

		toDestroy.clear();
	}

	public void setGame(PacmanGame g) {
		game = g;
	}

}
