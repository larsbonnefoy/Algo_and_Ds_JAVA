package ft;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 05/12/2023, mardi
 **/
public class PriorityQueueListTest {

    private final int ASCII_LOWER_CASE_OFFSET = 97;
    PriorityQueueList<Character, Integer> list;

    //String represents ascii character. Sorted depending on their value
    //Randomly generate an array that goes from 0 to 25, this will be the order ascii chars form a to z will be inserted
    @Before
    public void init() {
        list = new PriorityQueueList<>();
        ArrayList<Integer> randList = new ArrayList<>();
        for (int i = 0; i < 26 ; i++) {
            randList.add(i);
        }
        Collections.shuffle(randList);

        for (int i = 0; i < randList.size(); i++) {
            int v = randList.get(i);
            char asciiChar = (char) (v + ASCII_LOWER_CASE_OFFSET);
            list.push(asciiChar, v + ASCII_LOWER_CASE_OFFSET);
        }
    }

    //Test for push pop and top, as pop and top are needed to verify if push worked
    @Test
    public void all() {
        //Inserting element with a lower prio
        char toAdd = 'A';
        list.push(toAdd, (int) toAdd);
        Assert.assertEquals(toAdd, (char)list.top());
        Assert.assertEquals(27, list.size());

        //Inserting element with highest prio
        toAdd = '}';
        list.push(toAdd, (int) toAdd);
        //Elements have been inserted in a random order in init function. They should be sorted depending on priority
        //when removing them
        char current = list.top();
        for (int i = list.size() - 1; i > 0; i--) {
            Assert.assertEquals(current, (char)list.pop());
            char prev = current;
            current = list.top();
            Assert.assertTrue(prev < current);
        }
    }

}