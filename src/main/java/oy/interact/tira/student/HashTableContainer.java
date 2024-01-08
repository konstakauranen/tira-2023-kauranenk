package oy.interact.tira.student;

import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

public class HashTableContainer<K extends Comparable<K>,V> implements TIRAKeyedContainer<K,V> {
    
    private static final int DEFAULT_CAPACITY = 40;
    private static final double LOAD_FACTOR = 0.65;
    @SuppressWarnings("unchecked")
    private Pair<K,V>[] array = new Pair[DEFAULT_CAPACITY];
    private int count;
    private int collisions;
    private int maxProbes;
    private int timesReallocated;



    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (count >= array.length  * LOAD_FACTOR ) {
            reallocate((int) (array.length / LOAD_FACTOR));
            timesReallocated++;
        }

        int index = 0;
        int hashModifier = 0;
        int probingCount = 0;
        boolean added = false;
        int hash = key.hashCode();

        do {

            index = indexFor(hash, hashModifier);
            if (array[index] == null) {
                array[index] = new Pair<K,V>(key, value);
                count++;
                added = true;
            } else if (array[index].getKey().equals(key)) {
                
                array[index] = new Pair<K,V>(key, value);
                added = true;
            } else {
                hashModifier++;
                collisions++;
                probingCount++;
            }

        } while (!added);

        
        maxProbes = Math.max(maxProbes, probingCount);
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        int hashModifier = 0;
        int index = 0;
        int hash = key.hashCode();
        boolean found = false;

        do {

            index = indexFor(hash, hashModifier);
            if (array[index] != null && array[index].getKey().equals(key)) {
                found = true;
                return array[index].getValue();
            }
            else {
                hashModifier++;
            }

        } while (!found && array[index] != null);

        return null;
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    public V find(Predicate<V> searcher) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] != null) {
                if (searcher.test(array[index].getValue())) {
                    return array[index].getValue();
                }
            }
        }

        return null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        if (count == 0) {
            array = new Pair[capacity];
        } else {
            if (capacity > count) {
                reallocate(capacity);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        array = new Pair[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<K, V>[] toArray() throws Exception {
        Pair<K,V>[] newArray = new Pair[count];
        int newIndex = 0;
        for (int index = 0; index < array.length; index++) {
            if (array[index] != null) {
                newArray[newIndex] = array[index];
                newIndex++;
                
            }
        }

        return newArray;
    }

    @SuppressWarnings("unchecked")
    private void reallocate(int capacity) {
        Pair<K, V>[] oldArray = array;
        array = new Pair[capacity];
        count = 0;
        collisions = 0;
        maxProbes = 0;

        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null) {
                add(oldArray[i].getKey(), oldArray[i].getValue());
            }
        }
    }

    private int indexFor(int hash, int modifier) {
        int index = ((hash + modifier) & 0x7FFFFFFF) % array.length;
        return index;
    }
    
}
