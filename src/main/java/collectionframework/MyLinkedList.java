package collectionframework;

import java.util.Arrays;

public class MyLinkedList {

    private Node head;
    private int size;

    public void add(int value) {
        if (head == null) {
            this.head = new Node(value);
        } else {
            Node tick = head;
            while (tick.getNext() != null) {
                tick = tick.getNext();
            }
            tick.setNext(new Node(value));
        }
        size++;
    }

    public int get(int index) {
        int counter = 0;
        Node tick = head;
        while (tick != null){
            if(counter == index){
                return tick.getValue();
            } else {
                tick = tick.getNext();
                counter++;
            }
        }
        throw new IllegalArgumentException();
    }

    public void remove(int index) {
        int counter = 0;
        Node tick = head;
        while (tick != null) {
            if(counter == index - 1) {
                tick.setNext(tick.getNext().getNext());
                size--;
                return;
            } if (index == 0) {
                this.head = tick.getNext();
                size--;
                return;
            } else {
                counter++;
                tick = tick.getNext();
            }
        }
    }

    @Override
    public String toString() {
        int[] all = new int[size];
        Node tick = head;
        int counter = 0;
        while (tick != null) {
            all[counter] = tick.getValue();
            tick = tick.getNext();
            counter++;
        }
        return Arrays.toString(all);
    }

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public Node getNext() {
            return this.next;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
