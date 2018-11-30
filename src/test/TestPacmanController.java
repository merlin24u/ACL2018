package test;

import engine.Cmd;
import model.controller.PacmanController;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

public class TestPacmanController {

    PacmanController pacmanController;

    @Before
    public void initialise() {
        pacmanController = new PacmanController();
    }

    @Test
    public void keyPressedUpTest() {

        KeyEvent keyEvent = new KeyEvent(new Button(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'Z');
        pacmanController.keyPressed(keyEvent);

        assertEquals("La touche n'a pas été enfoncée - UP", Cmd.UP, pacmanController.getCommand());
    }

    @Test
    public void keyPressedDownTest() {

        KeyEvent keyEvent = new KeyEvent(new Button(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'S');
        pacmanController.keyPressed(keyEvent);

        assertEquals("La touche n'a pas été enfoncée - DOWN", Cmd.DOWN, pacmanController.getCommand());
    }

    @Test
    public void keyPressedRightTest() {

        KeyEvent keyEvent = new KeyEvent(new Button(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'D');
        pacmanController.keyPressed(keyEvent);

        assertEquals("La touche n'a pas été enfoncée - RIGHT", Cmd.RIGHT, pacmanController.getCommand());
    }

    @Test
    public void keyPressedLeftTest() {

        KeyEvent keyEvent = new KeyEvent(new Button(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'Q');
        pacmanController.keyPressed(keyEvent);

        assertEquals("La touche n'a pas été enfoncée - LEFT", Cmd.LEFT, pacmanController.getCommand());
    }

    @Test
    public void keyReleasedTest() {

        KeyEvent keyEvent = new KeyEvent(new Button(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, ' ');
        pacmanController.keyPressed(keyEvent);

        assertEquals("La touche a été enfoncée - IDLE", Cmd.IDLE, pacmanController.getCommand());
    }
}
