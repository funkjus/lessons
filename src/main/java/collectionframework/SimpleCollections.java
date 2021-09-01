package collectionframework;

import java.util.*;

/**
 * @author im
 */
public class SimpleCollections {
    public static void main(String[] args) {
//        List<Integer> link = new LinkedList<>();
        List<Integer> array = new ArrayList<>();
//        collectionframework.MyLinkedList myLinkedList = new collectionframework.MyLinkedList();
//        Map<String, String> map = new HashMap<>();
//        Set<String> set = new HashSet<>();

        array.add(3);
        array.add(9);
        array.add(7);
        array.add(7);

        Iterator<Integer> iterator = array.listIterator();

        System.out.println(array);

        int count = 0;
        while (iterator.hasNext()){

            if(iterator.next() == 3) {
                iterator.remove();
            }
            System.out.println(iterator.next());
            count++;
        }

        System.out.println(array + "" + count);

    }

    public static void timer(List<Integer> list) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            list.add(i);

        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    public static void myLinkManip(MyLinkedList list) {
        for (int i = 0; i < 3; i++) {
            list.add(i);

        }
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));

        list.remove(2);
        System.out.println(list);
    }

    public static void hashManip(Map<? extends Object, ? extends Object> map) {
        System.out.println(map.getClass());
        for (Map.Entry<? extends Object, ? extends Object> m : map.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }

    public static Set<Integer> setTest(int g) {
        Set<Integer> set = new HashSet<>();
        int count = g;
        for (int i = 0; i <= 100; i++) {
            set.add(count++);
        }
        return set;
    }
}

class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "collectionframework.Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
