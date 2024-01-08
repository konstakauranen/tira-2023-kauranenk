package oy.interact.tira.student;

import oy.interact.tira.util.QueueInterface;

public class QueueImplementation<E> implements QueueInterface<E>{
    private Object [] itemArray;
    private int head = 0;
    private int tail = 0;
    private int count = 0;
    private static final int DEFAULT_QUEUE_SIZE = 10;

    public QueueImplementation() {
        itemArray = new Object[DEFAULT_QUEUE_SIZE];
    }

    public QueueImplementation(int size) {
        itemArray = new Object[size];
    }

    @Override
    public int capacity() {
        return itemArray.length;
    }

    @Override
    public void enqueue(Object element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
            throw new NullPointerException("The element cannot be null");
        }
    
        if (count == capacity()) {
            Object[] newItemArray = new Object[capacity() * 2];
            for (int index = 0; index < count; index++) {
                newItemArray[index] = itemArray[(head + index) % itemArray.length];
            }
    
            itemArray = newItemArray;
            head = 0;
            tail = count;
        }
    
        itemArray[tail] = element;
        tail = (tail + 1) % itemArray.length;
        count++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E dequeue() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("The queue is empty");
        } else {
            E element = (E) itemArray[head];
            itemArray[head] = null;
            head = (head +1) % itemArray.length;
            count--;
            return element;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E element() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("The queue is empty");
        }

        return (E) itemArray[head];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        itemArray = new Object[DEFAULT_QUEUE_SIZE];
        head = 0;
        tail = 0;
        count = 0;
        
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        int counter = count;
        int index = head;

        while (counter > 0) {
            str.append(itemArray[index]);
            if (counter > 1) {
                str.append(", ");
            }
            index++;
            counter--;
            if (index >= itemArray.length) {
                index = 0;
            }
        }
        str.append("]");
        String finishedString = str.toString();
        return finishedString;
    }
    
}
