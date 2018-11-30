package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import dao.MapXmlDAO;
import model.effect.factory.DamageEffectFactory;
import model.effect.factory.ExitEffectFactory;
import model.effect.factory.HealthEffectFactory;
import model.effect.factory.ItemEffectFactory;
import model.effect.factory.TreasureEffectFactory;
import model.item.Item;
import model.item.Key;
import model.movable.character.Character;
import model.movable.character.Pacman;
import model.movable.character.monster.Monster;
import model.movable.character.monster.MonsterFactory;
import model.movable.collision.ECollisionType;
import model.onmoveover.ItemRequiredOnMoveOver;
import model.onmoveover.OnMoveOver;
import model.onmoveover.SimpleOnMoveOver;

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

		start = new Point(1, 1);
		finish = new Point(0, 1);

		generationStatique();

	}

	public Map(int[][] g, Point s, Point f, ArrayList<MapXmlDAO.TmpType> items,
			ArrayList<MapXmlDAO.TmpType> effects,
			ArrayList<MapXmlDAO.TmpType> monsters) {
		grid = g;
		start = s;
		finish = f;

		this.characters = new ArrayList<Character>();
		this.events = new ArrayList<OnMoveOver>();
		toDestroy = new ArrayList<Object>();

		for (MapXmlDAO.TmpType tmpM : monsters) {
			Monster m = MonsterFactory.getInstance().createMonster(
					tmpM.getType(), this);
			m.setPosition(tmpM.getX(), tmpM.getY());
			this.characters.add(m);
		}

		Item key = new Key("K01", "Exit key");
		ItemEffectFactory ief;
		TreasureEffectFactory iefT;
		HealthEffectFactory iefH;
		DamageEffectFactory iefD;
		OnMoveOver onMove;

		try {
			for (MapXmlDAO.TmpType tmpM : items) {
				switch (tmpM.getType()) {
				case "key":
					Point posKey = new Point(tmpM.getX(), tmpM.getY());
					ief = new ItemEffectFactory(key);
					onMove = new SimpleOnMoveOver(this, posKey, true, false,
							false);
					onMove.addEffectFactory(ief);
					this.events.add(onMove);
					break;
				case "treasure":
					Point posTreasure = new Point(tmpM.getX(), tmpM.getY());
					iefT = new TreasureEffectFactory(1, 30);
					onMove = new SimpleOnMoveOver(this, posTreasure, true,
							false, false);
					onMove.addEffectFactory(iefT);
					this.events.add(onMove.getClone());
					break;
				}
			}

			for (MapXmlDAO.TmpType tmpM : effects) {
				switch (tmpM.getType()) {
				case "health":
					Point posHealth = new Point(tmpM.getX(), tmpM.getY());
					iefH = new HealthEffectFactory(2, 75);
					onMove = new SimpleOnMoveOver(this, posHealth, true, false,
							false);
					onMove.addEffectFactory(iefH);
					this.events.add(onMove.getClone());
					break;
				case "damage":
					Point posDamage = new Point(tmpM.getX(), tmpM.getY());
					iefD = new DamageEffectFactory(2, 10);
					onMove = new SimpleOnMoveOver(this, posDamage, true, false,
							false);
					onMove.addEffectFactory(iefD);
					this.events.add(onMove.getClone());
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Sortie
		ArrayList<Item> itemsRequired = new ArrayList<Item>();
		itemsRequired.add(key);
		ExitEffectFactory ef = new ExitEffectFactory(1);
		OnMoveOver onMove2 = new ItemRequiredOnMoveOver(this, finish, true,
				false, true, itemsRequired, true);
		onMove2.addEffectFactory(ef);
		this.events.add(onMove2);
	}

	private void generationStatique() {
		Monster m = MonsterFactory.getInstance().createMonster("warrior", this);
		m.setPosition(2, 2);
		this.characters.add(m);

		Item item1 = new Key("K01", "Exit key");
		// Case qui donnera la clef
		ItemEffectFactory ief = new ItemEffectFactory(item1);
		OnMoveOver onMove1 = new SimpleOnMoveOver(this, new Point(1, 2), true,
				false, false);
		onMove1.addEffectFactory(ief);
		this.events.add(onMove1);

		// Sortie qui necessite la clef
		ArrayList<Item> itemsRequired = new ArrayList<Item>();
		itemsRequired.add(item1);
		ExitEffectFactory ef = new ExitEffectFactory(1);
		OnMoveOver onMove2 = new ItemRequiredOnMoveOver(this, finish, true,
				false, true, itemsRequired, true);
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

	public ArrayList<Monster> getMonsters() {
		ArrayList<Monster> list = new ArrayList<>();
		for (Character c : characters) {
			if (c.isType("Monster"))
				list.add((Monster) c);
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
		Pacman player = game.getPlayer();
		for (Monster monster : getMonsters()) {
			if (player.getPosition().x == monster.getPosition().x
					&& player.getPosition().y == monster.getPosition().y) {
				monster.onCollision(player);
			} else
				monster.setAlreadyOn(false);
		}

		for (Character character : characters) {
			character.update();
		}

		for (Character character : characters) {
			character.clearEffect();

			if (character.isToDestroy()) {
				if (character.isType("Player")) {
					game.getGameState().setState(GameState.State.GAMEOVER);
				}
				toDestroy.add(character);
			}
		}

		for (Object omo : toDestroy) {
			if (events.contains(omo))
				events.remove(omo);
			else if (characters.contains(omo))
				characters.remove(omo);
		}

		toDestroy.clear();
	}

	public void setGame(PacmanGame g) {
		game = g;
	}

}
