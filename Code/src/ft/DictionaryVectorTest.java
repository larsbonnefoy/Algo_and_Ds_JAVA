package ft;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * DictionaryVectorTest.java
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 05/12/2023, mardi
 *
 * Tests cases for Dictionary Vector
 **/
public class DictionaryVectorTest {

    DictionaryVector<String, Integer> vec;

    @Before
    public void init() {
        vec = new DictionaryVector<>();
        for (int i = 0; i < 100; i++) {
            vec.add(String.format("%d", i), i);
        }
    }
    @Test
    public void add() {
        for (int i = 100; i > 0; i--) {
            Assert.assertEquals(i - 1, (int)vec.get(i - 1));
        }
        Assert.assertEquals(100, vec.size());
    }

    @Test
    public void find() {
        for (int i = 0; i < 100; i++) {
            Assert.assertEquals(i, (int)vec.find( String.format("%d", i)));
        }
        Assert.assertNull(vec.find("a"));
    }

    @Test
    public void removeKey() {
        for (int i = 0; i < 50; i++) {
            vec.removeKey(String.format("%d", i));
        }
        for (int i = 0; i < 50; i++) {
            Assert.assertEquals(i + 50, (int)vec.get(i));
        }
    }

}