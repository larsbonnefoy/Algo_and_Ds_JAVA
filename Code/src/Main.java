import ft.Vector;

import java.util.Iterator;
//import java.util.LinkedList;
import ft.LinkedList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> testlist = new LinkedList<>();
        for (int i = 0; i < 10 ; i++) {
            testlist.add(i);
        }
        Iterator<Integer> it = testlist.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        Iterator<Integer> revIt = testlist.descendingIterator();
        while (revIt.hasNext()) {
            System.out.println(revIt.next());
        }
    }
}