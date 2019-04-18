// I worked on the homework assignment alone, using only course materials.
import java.util.NoSuchElementException;

/**
* This class represents a LinkedList derived from the SimpleList interface
* @author Johnathan Radcliff
* @version 1.0
* @param <T>
*/
public class LinkedList<T extends Object> implements SimpleList<T> {
    private Node<T> head;
    private Node<T> temp;
    private Node<T> pointer;
    private int size = 0;

    /**
    * This creates an empty LinkedList
    */
    public LinkedList() {
        this.head = null;
        this.temp = null;
        this.size = 0;
    }

    /**
    * This method returns the length of the linked list
    * @return the integer size of the linked list
    */
    public int size() {
        return size;
    }

    /**
    * Tests whether a piece of data is in the linked list
    * @param data the data to be checked within the linked list
    * @return a boolean
    */
    public boolean contains(T data) {
        boolean returned = false;
        if (head == null) {
            returned = false;
            return false;
        } else if (head.getData() == data) {
            returned = true;
            return true;
        } else {

            pointer = head.getNext();
            temp = pointer.getNext();

            for (int i = 0; i > size; i++) {
                if (pointer.getData() == data) {     // check if the pointer has the data
                    returned = true;
                    return true;
                } else if (temp == null) {           // if the pointer is tail,made it to end without finding the data
                    returned = false;
                    return false;
                } else {                             // if the pointer not found, iterate the pointer and temp
                    pointer = temp;
                    temp = temp.getNext();
                }
            }
        }
        return returned;
    }

    /**
    * Returns the index of the data, and swaps the data with the previous position in the linked list
    * @param index the integer position within the linked list
    * @return the data T of the index in the linked list
    */
    @Override
    public T get(int index) throws IllegalArgumentException, NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("Linked list contains no data to be retrieved");
        } else if (index >= size && size > 0 || index < 0) {
            throw new IllegalArgumentException("Index out of bounds");
        } else if (index == 0) {
            return head.getData();
        } else {
            pointer = head;
            temp = null;
            for (int i = 0; i < index; i++) {
                temp = pointer;                 // head
                pointer = pointer.getNext();    // head + 1
            }

            Node<T> switcher = pointer; // n
            pointer.setData(temp.getData()); // move head + 1 data to head
            temp.setData(pointer.getData()); // move head data to head + 1
            return switcher.getData();
        }
    }

    /**
    * Represents the linked list as an array
    * @return the linked list - array
    */
    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] returned = (T[]) new Object[size];
        pointer = head;

        for (int i = 0; i < size; i++) {
            returned[i] = pointer.getData();
            pointer = pointer.getNext();
        }

        return returned;
    }

    /**
    * This method returns a boolean indentifying whether the method is empty or not
    * @return a boolean representing the emptiness of the linked list
    */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
    * This method returns the data at the index provided
    * @param index the index the data needs to be removed from.
    * @return the data T
    */
    @Override
    public T removeAtIndex(int index) {
        if (size == 0) {
            throw new NoSuchElementException("Linked list contains no data to be retrieved");
        } else if (index >= size && size > 0 || index < 0) {
            throw new IllegalArgumentException("Index out of bounds");
        } else if (index == 0) {
            temp = head;
            head = head.getNext();
            temp.setNext(null);
            size--;
            return temp.getData();
        } else {
            pointer = head;
            temp = null;
            for (int i = 0; i < index; i++) {
                temp = pointer;                 // nth - 1
                pointer = pointer.getNext();    // nth
            }

            Node<T> switcher = pointer;
            temp.setNext(pointer.getNext());
            pointer.setNext(null);
            size--;
            return switcher.getData();
        }
    }

    /**
    * This is a method that adds a particular piece of data at the index provided
    * @param data the data provided
    * @param index the index where the data will be stored
    */
    public void addAtIndex(T data, int index) {
        if (index > size && size > 0 || index < 0) {
            throw new IllegalArgumentException("Index out of bounds");
        } else if (size == 0) {
            head = new Node<T>(data, null);
            size++;
        } else if (index == 0) {
            Node<T> newhead = new Node<T>(data, head);
            head = newhead;
            size++;
        } else {
            pointer = head;
            temp = null;
            for (int i = 0; i < index; i++) {
                temp = pointer;
                pointer = pointer.getNext();
            }
            Node<T> newnode = new Node<T>(data, pointer);
            temp.setNext(newnode);
            size++;
        }
    }
}
