package test;

import model.*;
import model.Character;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class TestMonster {

    @Before
    public void initialize() {

        int currentHP = 100;
        int maximumHP = 100;
        int defensePoints = 10;

        Map map = new Map();

        GroundCollisionHandler groundCollisionHandler = new GroundCollisionHandler(map);

        int movingSpeedXMax = 5;
        int movingSpeedYMax = 5;

        Point position = new Point(5, 5);

        MovableArtificialIntelligence movableArtificialIntelligence = new RandomMovableArtificialIntelligence();

        Monster monster = new Monster(movableArtificialIntelligence, currentHP, maximumHP, defensePoints, groundCollisionHandler, movingSpeedXMax, movingSpeedYMax, position) {};
    }

}
