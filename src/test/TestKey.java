package test;


import org.junit.Before;
import org.junit.Test;

import model.item.Key;

import static org.junit.Assert.assertEquals;

public class TestKey {

    String keyId;
    String keyName;
    Key key;

    @Before
    public void initialize(){
        keyId = "keyId";
        keyName = "keyName";
        key = new Key(keyId, keyName);
    }

    @Test
    public void testClone(){
        assertEquals("Clone key n'est pas meme!", key, key.clone());
    }

    @Test
    public void testEquals(){
        Key newKey = new Key (keyId, keyName);

        boolean result = key.equals(newKey);
        assertEquals("Key n'est pas meme!", true, result);
    }

    @Test
    public void testNotEqualsId(){
        keyId = keyId + "New";
        Key newKey = new Key (keyId, keyName);

        boolean result = key.equals(newKey);
        assertEquals("Key est meme!", false, result);
    }

    @Test
    public void testNotEqualsName(){
        keyName = keyName + "New";
        Key newKey = new Key (keyId, keyName);

        boolean result = key.equals(newKey);
        assertEquals("Key est meme!", false, result);
    }

    @Test
    public void testNotEqualsIdName(){
        keyId = keyId + "New";
        keyName = keyName + "New";
        Key newKey = new Key (keyId, keyName);

        boolean result = key.equals(newKey);
        assertEquals("Key est meme!", false, result);
    }
}
