package ft;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 21/11/2023, mardi
 **/
public class HashMapTest {

    @Test
    public void put() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Aanana", 10); //maps to 6
        map.put("Mango", 10); //maps to 2
        map.put("mango", 9); //maps also to 2 -> Collision on index 2, check if we can retrieve mango
        map.put("Fruit", 10); //maps to 4
        map.put("Avocado", 10); //maps to 3
        Assert.assertEquals(5, (int)map.size()); //Check if all 5 elements have been added
        Assert.assertEquals(10, (int)map.get("Avocado"));
        Assert.assertNull(map.get("Banana")); //banana maps to 5
        Assert.assertNull(map.get("aanana")); //maps to 6 but key is not the same so it should return null
        Assert.assertEquals(9, (int)map.get("mango")); //-> collision check
        Assert.assertEquals(10, (int)map.get("Mango")); //-> collision check

        Assert.assertEquals(10, (int)map.put("Avocado", 9)); //Changing value of key avocado should replace current value 10 with 9, should return prev value
        Assert.assertEquals(9, (int)map.get("Avocado"));
        map.putIfAbsent("Avocado", 11); //only adding if no element exists, should not be added
        Assert.assertEquals(9, (int)map.get("Avocado"));
        map.putIfAbsent("meat", 8); //maps to 11, should be added
        Assert.assertEquals(8, (int)map.get("meat"));
        Assert.assertEquals(6, map.size()); //Check if all 7 elements are present (6 put call but 1 replaced values (=>5 values) 2 putIf calls but one one an existing value =>(+1 value)  )
    }

    @Test
    public void resize() {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i < 25; i++) {
            map.put(String.format("%d", i), i); //triggers resize at i == 12 && i == 24 as (12/16 && 24/32) >= 32
        }
        for (int i = 1; i < 25; i++) { //All inserted elements should still exist
            System.out.println(i);
            Assert.assertEquals(i, (int)map.get(String.format("%d", i)));
        }
    }
}