package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;

public class StackImplementation<E> implements StackInterface<E>{

    private Object [] itemArray;
    private int currentIndex;
    private static final int DEFAULT_STACK_SIZE = 10;
    
    public StackImplementation() {
        itemArray = new Object[DEFAULT_STACK_SIZE];
        currentIndex = -1;
    }

    public StackImplementation(int size) {
        itemArray = new Object[size];
        currentIndex = -1;
    }

    @Override
    public int capacity() {
        return itemArray.length;
    }

    @Override
    public void push(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element must not be null");
        }
        if (currentIndex >= capacity()-1) {
            reallocate();
        }

        currentIndex++;
        itemArray[currentIndex] = element;

    }

    private void reallocate() {
        Object [] newArray = new Object[itemArray.length * 2];
        for (int index = 0; index < itemArray.length; index ++) {
            newArray[index] = itemArray[index];
        }

        itemArray = newArray;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E pop() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("The array is empty");
        }
        E element = (E) itemArray[currentIndex];
        itemArray[currentIndex] = null;
        currentIndex--;
        return element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peek() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot peek to an empty array");
        }
        
        return (E) itemArray[currentIndex];
        
    }

    @Override
    public int size() {
        return currentIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        if (currentIndex == -1) {
            return true;
        }

        return false;
    }

    @Override
    public void clear() {
        itemArray = new Object[DEFAULT_STACK_SIZE];
        currentIndex = -1;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");

        for (int index = 0; index <= currentIndex; index++) {
            str.append(itemArray[index]);
            if (index != currentIndex) {
                str.append(", ");
            }
        }

        str.append("]");

        String finishedString = str.toString();
        return finishedString;
    }
    
}
