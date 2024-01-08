package oy.interact.tira.student;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import oy.interact.tira.util.Pair;

class TreeNode<K extends Comparable<K>, V> {

    private Comparator<K> comparator;
    private Pair<K,V> pair;
    private TreeNode<K,V> leftChild;
    private TreeNode<K,V> rightChild;
    private boolean result = false;

    public TreeNode() {
        //empty
    }

    public TreeNode(Comparator<K> comparator, Pair<K,V> pair) {
        this.pair = pair;
        this.comparator = comparator;
    }

    public TreeNode<K,V> getRight() {
        return rightChild;
    }

    public TreeNode<K,V> getLeft() {
        return leftChild;
    }

    public Pair<K,V> getPair() {
        return pair;
    }

    public boolean insert(K key, V value) {
        if (pair == null || value.equals(pair.getValue())) {
            this.pair = new Pair<K,V>(key, value);
            return false;
        }

        if (comparator.compare(key, pair.getKey()) < 0 || comparator.compare(key, pair.getKey()) == 0) {
            if (leftChild == null) {
                leftChild = new TreeNode<K,V>(comparator, new Pair<K,V>(key, value));
                result = true;
            } else {
                result = leftChild.insert(key, value);
            }
        } else {
            if (rightChild == null) {
                rightChild = new TreeNode<K,V>(comparator, new Pair<K,V>(key, value));
                result = true;
            } else {
                result = rightChild.insert(key, value);
            }
        }
        return result;
    }

    public Pair<K,V> find(K key) {

        Pair<K,V> result = null;

        if(comparator.compare(key, pair.getKey()) == 0) {
            result = pair;
        } else if (comparator.compare(key, pair.getKey()) < 0 && leftChild != null) {
            result = leftChild.find(key);
        } else if (comparator.compare(key, pair.getKey()) > 0 && rightChild != null) {
            result = rightChild.find(key);
        }
        return result;
    }

    public void toArray(Pair<K, V>[] array, AtomicInteger currentIndex) {
        if (leftChild != null) {
            leftChild.toArray(array, currentIndex);
        }

        array[currentIndex.getAndIncrement()] = pair;
        if (rightChild != null) {
            rightChild.toArray(array, currentIndex);
        }
    }
}
