package test;


import model.Map;
import model.PacmanGame;
import model.movable.Movable;
import model.movable.collision.GroundCollisionHandler;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class TestMovable {

    PacmanGame pacmanGame;
    Map map;
    int movingSpeedXMax;
    int movingSpeedYMax;
    Point startPosition;
    Movable movable;

    @Before
    public void initialize(){

        pacmanGame = new PacmanGame("src/test/res/test_map_1.xml");
        map = pacmanGame.getMap();
        movingSpeedXMax = 1;
        movingSpeedYMax = 1;
        startPosition = new Point(map.getStart().x, map.getStart().y);
        Point position = new Point(map.getStart().x, map.getStart().y);
        movable = new Movable(new GroundCollisionHandler(map, null), movingSpeedXMax, movingSpeedYMax, 0, position);

    }

    @Test
    public void testMoveUpOnce(){
        movable.moveUp();
        movable.update();

        assertEquals("Movable est alle plus que " + movingSpeedYMax + " point", startPosition.y - 1, movable.getPosition().y);
    }

    @Test
    public void testMoveUpTwice(){

        movable.moveUp();
        movable.moveUp();
        movable.update();

        assertEquals("Movable est alle plus que " + movingSpeedYMax + " point", startPosition.y - 2, movable.getPosition().y);
    }

    @Test
    public void testMoveDownOnce(){
        movable.moveDown();
        movable.update();

        assertEquals("Movable est alle plus que " + movingSpeedYMax + " point", startPosition.y + 1, movable.getPosition().y);
    }

    @Test
    public void testMoveDownTwice(){

        movable.moveDown();
        movable.moveDown();
        movable.update();

        assertEquals("Movable est alle plus que " + movingSpeedYMax + " point", startPosition.y + 2, movable.getPosition().y);
    }

    @Test
    public void testMoveRightOnce(){
        movable.moveRight();
        movable.update();

        assertEquals("Movable est alle plus que " + movingSpeedXMax + " point", startPosition.x + 1, movable.getPosition().x);
    }

    @Test
    public void testMoveRightTwice(){

        movable.moveRight();
        movable.moveRight();
        movable.update();

        assertEquals("Movable est alle plus que " + movingSpeedXMax + " point", startPosition.x + 2, movable.getPosition().x);
    }

    @Test
    public void testMoveLeftOnce(){
        movable.moveLeft();
        movable.update();

        assertEquals("Movable est alle plus que " + movingSpeedXMax + " point", startPosition.x - 1, movable.getPosition().x);
    }

    @Test
    public void testMoveLeftTwice(){

        movable.moveLeft();
        movable.moveLeft();
        movable.update();

        assertEquals("Movable est alle plus que " + movingSpeedXMax + " point", startPosition.x - 2, movable.getPosition().x);
    }

    @Test
    public void testResetCurrentSpeed(){

        movable.moveLeft();
        movable.moveLeft();

        assertEquals("CurrestSpeed n'est pas meme!", -2, movable.getCurrentSpeedX());

        movable.resetCurrentSpeed();
        assertEquals("CurrestSpeedX n'est pas ete resete!", 0, movable.getCurrentSpeedX());
        assertEquals("CurrestSpeedY n'est pas ete resete!", 0, movable.getCurrentSpeedY());

        movable.moveRight();
        movable.moveRight();

        assertEquals("CurrestSpeed n'est pas meme!", 2, movable.getCurrentSpeedX());

        movable.resetCurrentSpeed();
        assertEquals("CurrestSpeedX n'est pas ete resete!", 0, movable.getCurrentSpeedX());
        assertEquals("CurrestSpeedY n'est pas ete resete!", 0, movable.getCurrentSpeedY());

        movable.moveUp();
        movable.moveUp();

        assertEquals("CurrestSpeed n'est pas meme!", -2, movable.getCurrentSpeedY());

        movable.resetCurrentSpeed();
        assertEquals("CurrestSpeedX n'est pas ete resete!", 0, movable.getCurrentSpeedX());
        assertEquals("CurrestSpeedY n'est pas ete resete!", 0, movable.getCurrentSpeedY());

        movable.moveDown();
        movable.moveDown();

        assertEquals("CurrestSpeed n'est pas meme!", 2, movable.getCurrentSpeedY());

        movable.resetCurrentSpeed();
        assertEquals("CurrestSpeedX n'est pas ete resete!", 0, movable.getCurrentSpeedX());
        assertEquals("CurrestSpeedY n'est pas ete resete!", 0, movable.getCurrentSpeedY());

        movable.moveDown();
        movable.moveDown();
        movable.moveRight();
        movable.moveRight();

        assertEquals("CurrestSpeedX n'est pas meme!", 2, movable.getCurrentSpeedX());
        assertEquals("CurrestSpeedY n'est pas meme!", 2, movable.getCurrentSpeedY());

        movable.resetCurrentSpeed();
        assertEquals("CurrestSpeedX n'est pas ete resete!", 0, movable.getCurrentSpeedX());
        assertEquals("CurrestSpeedY n'est pas ete resete!", 0, movable.getCurrentSpeedY());

        movable.moveUp();
        movable.moveUp();
        movable.moveLeft();
        movable.moveLeft();

        assertEquals("CurrestSpeedX n'est pas meme!", -2, movable.getCurrentSpeedX());
        assertEquals("CurrestSpeedY n'est pas meme!", -2, movable.getCurrentSpeedY());

        movable.resetCurrentSpeed();
        assertEquals("CurrestSpeedX n'est pas ete resete!", 0, movable.getCurrentSpeedX());
        assertEquals("CurrestSpeedY n'est pas ete resete!", 0, movable.getCurrentSpeedY());
    }

    @Test
    public void testTranslatePlus(){

        Point startPoint = new Point(movable.getPosition());
        int translateX = 5;
        int translateY = 5;
        movable.translate(translateX,translateY);

        assertEquals("TranslateX n'a pas ete bien execute!", startPoint.x + translateX, movable.getPosition().x);
        assertEquals("TranslateY n'a pas ete bien execute!", startPoint.y + translateY, movable.getPosition().y);
    }

    @Test
    public void testTranslateMinus(){

        Point startPoint = new Point(movable.getPosition());
        int translateX = -5;
        int translateY = -5;
        movable.translate(translateX,translateY);

        assertEquals("TranslateX n'a pas ete bien execute!", startPoint.x + translateX, movable.getPosition().x);
        assertEquals("TranslateY n'a pas ete bien execute!", startPoint.y + translateY, movable.getPosition().y);
    }

    @Test
    public void testUpdate(){
        movable.moveUp();
        movable.moveRight();
        movable.update();

        assertEquals("Movable est alle plus que " + movingSpeedYMax + " point", startPosition.y - 1, movable.getPosition().y);
        assertEquals("Movable est alle plus que " + movingSpeedXMax + " point", startPosition.x + 1, movable.getPosition().x);

        assertEquals("Movable CurrentSpeet X n'a pas ete resete", 0, movable.getCurrentSpeedX());
        assertEquals("Movable CurrentSpeet X n'a pas ete resete", 0, movable.getCurrentSpeedY());
    }
}
