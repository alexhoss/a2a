package ca.bcit.comp2526.a2a;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for a generic double linked list.
 *
 * @param <E> the type of the list
 * @author alexhosseini
 * @version 1.0
 */
public class DoublyLinkedList<E> implements Iterable<E>, Serializable {
    private DoubleNode<E> head;
    private DoubleNode<E> tail;
    private int size;

    /**
     * double linked list constructor.
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;

    }

    /**
     * Method to add an element to the front of a linked list.
     *
     * @param e the generic type to add
     * @throws CouldNotAddException thrown if method failed
     */
    public void addToFront(E e) throws CouldNotAddException {
        if (e == null) {
            throw new CouldNotAddException();
        }
        DoubleNode<E> newBoy = new DoubleNode<E>(e);

        if (isEmpty()) {
            head = newBoy;
            tail = newBoy;

        } else {
            newBoy.setNext(head);
            head.setPrev(newBoy);
            head = newBoy;

        }
        size++;

    }

    /**
     * Checks if the list is empty.
     *
     * @return true if empty; else false
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add a node to the end of the double linked list.
     *
     * @param e the node to add
     * @throws CouldNotAddException if trying to add null
     */
    public void addToEnd(E e) throws CouldNotAddException {
        if (e == null) {
            throw new CouldNotAddException();
        }
        DoubleNode<E> newBoy = new DoubleNode<E>(e);

        if (isEmpty()) {
            head = newBoy;
            tail = newBoy;
        } else {
            newBoy.setPrev(tail);
            tail.setNext(newBoy);
            tail = newBoy;
        }
        size++;

    }

    /**
     * Removes the last element in the list.
     *
     * @return the element remmoved.
     * @throws CouldNotRemoveException thrown if list is empty
     */
    public E removeFromEnd() throws CouldNotRemoveException {
        if (isEmpty()) {
            throw new CouldNotRemoveException();
        }
        E ret = tail.getData();
        tail = tail.getPrev();
        size--;

        return ret;

    }

    /**
     * Removes the first element in the list.
     *
     * @return the element removed
     * @throws CouldNotRemoveException thrown if list is empty
     */
    public E removeFromFront() throws CouldNotRemoveException {

        if (isEmpty()) {
            throw new CouldNotRemoveException();
        }
        E ret = head.getData();


        if (head.getNext() != null) {
            head = head.getNext();
            head.setPrev(null);
        }
        size--;
        return ret;
    }

    /**
     * Gets the first element in the list.
     *
     * @return the first element in the list
     */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.getData();
        }
    }

    /**
     * Gets the last element in the list.
     * @return the last element in the list.
     *
     */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.getData();
        }
    }

    /**
     * Gets the size of the list.
     * @return the size
     */
    public int size() {
        return size;
    }


    @Override
    public Iterator<E> iterator() {
        return new LLIterator();
    }

    /**
     * Linked list iterator.
     */
    class LLIterator implements Iterator<E> {
        private DoubleNode<E> nextNode;

        /**
         * Default constructor for a linked list iterator.
         */
         LLIterator() {
            nextNode = head;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E temp = nextNode.getData();
            nextNode = nextNode.getNext();
            return temp;
        }
    }

    /**
     * Double node class for a double linked list.
     * @param <E> the elemnent type
     */
    static class DoubleNode<E> implements Serializable {
        private DoubleNode<E> prev;
        private DoubleNode<E> next;
        private E data;

        /**
         * Double node constructor.
         * @param e the data to store in the node
         */
         DoubleNode(E e) {
            this.data = e;

        }

        /**
         * Sets the previous node.
         * @param n previous node to set
         */
        public void setPrev(DoubleNode<E> n) {
            this.prev = n;

        }
        /**
         * Gets the data in the node.
         * @return data
         */
        public E getData() {
            return data;
        }

        /**
         * Sets the next node.
         * @param n next node to set
         */
        public void setNext(DoubleNode<E> n) {
            this.next = n;
        }

        /**
         * Returns the previous node.
         * @return the previous node
         */
        public DoubleNode<E> getPrev() {
            return prev;
        }

        /**
         * Returns the next node.
         * @return the next node
         */
        public DoubleNode<E> getNext() {
            return next;
        }

    }
}
