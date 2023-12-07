package ft;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author : larsbonnefoy
 * @mailto : lars.bonnefoy@vub.be
 * @created : 06/12/2023, mercredi
 **/
public class TreeTest {

    Tree<Integer> bst = null;
    @Before
    public void init() {
        bst = new Tree<>();

        ArrayList<Integer> randlist = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            randlist.add(i);
        }
        Collections.shuffle(randlist);

        //Randomly insert elements from 0 to 50.
        for (int i = 0; i < randlist.size(); i++) {
            bst.insert(randlist.get(i));
        }
    }

    @Test
    public void find() {
       //searches all 50 randomly inserted values in the tree
        for (int i = 0; i < bst.size(); i++) {
            Assert.assertTrue(bst.find(i));
        }
        for (int i = bst.size(); i < bst.size() * 2; i++) {
            Assert.assertFalse(bst.find(i));
        }
    }

    @Test
    public void spaceSearch() {
        //Creating smaller bst, same as on page 430 in ref book
        Tree<Integer> bst_2 = new Tree<>();
        bst_2.add(12,12);
        bst_2.add(5,5);
        bst_2.add(2,2);
        bst_2.add(9,9);
        bst_2.add(18,18);
        bst_2.add(19,19);
        bst_2.add(15,15);
        bst_2.add(13,13);
        bst_2.add(17,17);
        //Depth first Should give 12 5 2 9 18 15 13 17 19
        Assert.assertEquals("12\n5\n2\n9\n18\n15\n13\n17\n19\n", bst_2.depthFirst());
        //Breadth first Should give 12 18 5 19 15 9 2 17 13
        Assert.assertEquals("12\n18\n5\n19\n15\n9\n2\n17\n13\n", bst_2.breadthFirst());
        Assert.assertEquals(19, (int)bst_2.findMax());
        Assert.assertEquals(4, bst_2.height());
        //Add two values to the right, having a max height of 5
        bst_2.add(20,20);
        bst_2.add(21,21);
        Assert.assertEquals(5, bst_2.height());
    }

}