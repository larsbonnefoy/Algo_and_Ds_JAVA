import ft.Vector;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("ASSIGNMENTS");
        System.out.println("1: Create a vector, add the elements 1 to 100 to it, get the size of the vector, verify whether the vector contains the elements 6 and 101");
        Vector<Integer> vec = new Vector<>(100);
        for (int i = 1; i < 11 ; i++) {
            vec.add(i);
        }
        vec.size();
        System.out.println(String.format("Vector contains 6: %b, Vector contains 101: %b", vec.contains(6), vec.contains(101)));
        System.out.println(String.format("Last element is %d : %b", vec.lastElement(), Objects.equals(vec.lastElement(), vec.get(99))));
        System.out.println(vec);

        Vector<Integer> vec2 = new Vector<>(1, 2);
        vec2.ensureCapacity(10);
        for (int i = 11; i < 15 ; i++) {
            vec2.add(i);
        }
        Vector<Integer> vec3 = vec.interleave(vec2);
        System.out.println(vec3);
        System.out.println(vec);
        vec3.removeFirst();
        System.out.println(vec);


    }
}