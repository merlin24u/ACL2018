package model;

import java.awt.Point;
import java.util.ArrayList;
import engine.Cmd;

public class Pacman extends Character implements IDamager {
	private DamageEffectFactory damageEffectFactory;
	private int moneyAmount;
	private ArrayList<Item> items;
	private boolean isAttacking;
	private boolean willAttack;
	private Map map;

	public Pacman(Map m) {
		super(50, 50, 0, GroundCollisionHandlerFactory.getInstance().getGroundCollisionHandler(m, new ECollisionType[] {ECollisionType.WALL}), 1, 1, 3, m.getStart());
		this.items = new ArrayList<Item>();
		this.damageEffectFactory = new DamageEffectFactory(1, 1);
		this.isAttacking = false;
		this.map = m;
	}

	public void evolve(Cmd commande) {
		switch (commande) {
		case ATTACK:
			this.willAttack = true;
			break;
		case DOWN:
			this.moveDown();
			System.out.println("D");
			break;
		case LEFT:
			this.moveLeft();
			System.out.println("L");
			break;
		case RIGHT:
			this.moveRight();
			System.out.println("R");
			break;
		case UP:
			this.moveUp();
			System.out.println("U");
			break;
		default:
			break;
		}
		System.out.println("money: " + this.moneyAmount);
	}

	public void increaseMoneyAmount(int moneyAmount) {
		this.moneyAmount += moneyAmount;
	}

	public int getMoneyAmount() {
		return this.moneyAmount;
	}

	public void addItem(Item item) {
		if (!items.contains(item)) {
			System.out.println("you've collected " + item.getName());
			this.items.add(item);
		}
	}

	public Item getItem(String itemId) {
		for (Item i : items) {
			if (i.getId().equals(itemId))
				return i;
		}
		return null;
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public boolean isType(String type) {
		if (type.equals("Player"))
			return true;
		else
			return super.isType(type);
	}

	@Override
	public void onCollision(Character character) {
		// Aucune action
	}

	@Override
	public int getDamages() {
		return damageEffectFactory.getDamages();
	}

	@Override
	public void attack(Character character) {
		damageEffectFactory.applyTo(character);
	}

	@Override
	public String getTexture() {
		return "character";
	}
	
	public void changeMap(Map m) {
		super.groundCollisionHandler.changeMap(m);
	}
	
	@Override
	public void update() {
		super.update();
		isAttacking = false;
		if(willAttack)
			AttackDirection();
		willAttack = false;
	}
	
	public boolean isAttacking() {
		return isAttacking;
	}
	
	private void AttackDirection() {
		Point attackPosition = new Point(position.x+direction.getDirection().x, position.y+direction.getDirection().y);
		for(Character c: map.getCharacters()) {
			if(attackPosition.equals(c.getPosition())) {
				attack(c);
			}
		}
		this.isAttacking = true;
	}
}
