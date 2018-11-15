package test;

import engine.Cmd;
import model.PacmanController;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

public class TestPacmanController {

    PacmanController pacmanController;
    Robot robot;

    @Before
    public void initialise() throws AWTException {
        pacmanController = new PacmanController();
        robot = new Robot();
    }

    @Test
    public void keyPressedUpTest() {

        robot.keyPress(KeyEvent.VK_Z);
        assertEquals("CO", Cmd.UP, pacmanController.getCommand());
    }
}
