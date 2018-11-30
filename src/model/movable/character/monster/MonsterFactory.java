package model.movable.character.monster;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import model.Map;
import model.effect.factory.DamageEffectFactory;
import model.movable.character.Character;
import model.movable.ai.FollowMovableArtificialIntelligence;
import model.movable.ai.MovableArtificialIntelligence;
import model.movable.collision.ECollisionType;
import model.movable.collision.GroundCollisionHandler;
import model.movable.collision.GroundCollisionHandlerFactory;

public class MonsterFactory {
	private static MonsterFactory instance = null;

	public Monster createMonster(String type, Map map) {
		DamageEffectFactory damageEffectFactory = null;
		GroundCollisionHandler groundCollisionHandler = null;
		MovableArtificialIntelligence movableArtificialIntelligence = null;
		int currentHP = 0, maximumHP = 0, defensePoints = 0;
		int movingSpeedXMax = 0, movingSpeedYMax = 0, movingTick = 0;
		ECollisionType[] collisions = null;
		String texture = null;
		switch (type) {
		case "warrior":
			damageEffectFactory = new DamageEffectFactory(1, 10);
			collisions = new ECollisionType[] { ECollisionType.WALL };
			groundCollisionHandler = GroundCollisionHandlerFactory.getInstance().getGroundCollisionHandler(map,
					collisions);
			movableArtificialIntelligence = new FollowMovableArtificialIntelligence(map, groundCollisionHandler);
			currentHP = 5;
			maximumHP = 5;
			defensePoints = 0;
			movingSpeedXMax = 1;
			movingSpeedYMax = 1;
			movingTick = 8;
			texture = "monster";
			break;
		case "ghost":
			damageEffectFactory = new DamageEffectFactory(1, 10);
			collisions = new ECollisionType[] {};
			groundCollisionHandler = GroundCollisionHandlerFactory.getInstance().getGroundCollisionHandler(map,
					collisions);
			movableArtificialIntelligence = new FollowMovableArtificialIntelligence(map, groundCollisionHandler);
			currentHP = 5;
			maximumHP = 5;
			defensePoints = 0;
			movingSpeedXMax = 1;
			movingSpeedYMax = 1;
			movingTick = 5;
			texture = "ghost";
			break;
		}
		return new Monster(damageEffectFactory, movableArtificialIntelligence, currentHP, maximumHP, defensePoints,
				groundCollisionHandler, movingSpeedXMax, movingSpeedYMax, movingTick, new Point(), texture);
	}

	public static MonsterFactory getInstance() {
		if (instance == null)
			instance = new MonsterFactory();
		return instance;
	}

}
