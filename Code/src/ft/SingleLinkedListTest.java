package ft;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SingleLinkedListTest {
    SingleLinkedList<Integer> list =  new SingleLinkedList<>();

    @Before
    public void init() {
        list =  new SingleLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
    }
    @Test
    public void BaseTest() {
        Assert.assertEquals(4, (int)list.getFirst());
        Assert.assertEquals(3, (int)list.get(1));
        Assert.assertEquals("( 4 3 2 1 )", list.toString());
    }

    @Test
    public void SizeTest() {
        list.addFirst(1);
        Assert.assertEquals(5, list.size());
    }

    @Test
    public void SetTest() {
        list.addFirst(30);

        int ogSize = list.size();
        list.set(list.size() - 1, 10);
        Assert.assertEquals(10, (int)list.get(list.size() - 1));
        int ret = list.set(0, 20);
        Assert.assertEquals(30, ret);
        Assert.assertEquals(20, (int)list.get(0));
        list.setLast(2);
        Assert.assertEquals(2, (int)list.getLast());
    }

    @Test
    public void ContainTest() {
        Assert.assertTrue(list.contains(1));
        Assert.assertFalse(list.contains(100));
    }

    @Test
    public void swapTest() {
        list.swap2by2();
        Assert.assertEquals("( 3 4 1 2 )", list.toString());
        System.out.println(list);
    }

    @Test
    public void appendTest() {
        SingleLinkedList<Integer> newList = new SingleLinkedList<Integer>();
        for (int i = 10; i > 7; i--) {
            newList.addFirst(i);
        }
        list.append(newList);
        Assert.assertEquals("( 8 9 10 )", newList.toString());
        Assert.assertEquals("( 4 3 2 1 8 9 10 )", list.toString());
    }

}
