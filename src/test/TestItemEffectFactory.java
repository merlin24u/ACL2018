package test;

import model.*;
import model.Character;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestItemEffectFactory {

    private Pacman pacman;
    private Monster monster;

    private Item item;
    private ItemEffectFactory itemEffectFactory;

    private String keyId;
    private String keyName;

    @Before
    public void initialize(){

        pacman = new Pacman(new Map());
        monster = MonsterFactory.getInstance().createMonster("worrior", new Map());
        keyId = "KeyId";
        keyName = "KeyName";

        item = new Key(keyId,keyName);
        itemEffectFactory = new ItemEffectFactory(item);
    }

    @Test
    public void testApplyToPacman(){
        boolean result = itemEffectFactory.applyTo(pacman);
        assertEquals("ItemEffectFactory n'a pas Pacman instance", true, result);
    }

    @Test
    public void testApplyToMonster(){
        boolean result = itemEffectFactory.applyTo(monster);
        assertEquals("ItemEffectFactory a Monster instance", false, result);
    }

    @Test
    public void testGetItem(){
        boolean result = itemEffectFactory.applyTo(pacman);
        assertEquals("ItemEffectFactory n'a pas Pacman instance", true, result);

        Key key = new Key(keyId, keyName);
        assertEquals("Pacman n'a pas item!", key, pacman.getItem(keyId));


    }
}