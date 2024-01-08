package oy.interact.tira.student;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    private TreeNode<K,V> root;
    private int count = 0;
    private int maxDepth = 0;
    private Comparator<K> comparator;

    public BinarySearchTreeContainer(Comparator<K> comparator) {
		this.comparator = comparator;
	}

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Parameters must not be null");
        }
        if (root == null) {
            root = new TreeNode<K,V>(comparator, new Pair<K,V>(key, value));
            count++;
            maxDepth++;
        } else {
            if (root.insert(key, value)) {
                count++;
            };
        }
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (root == null) {
            return null;
        }

        Pair <K,V> result = root.find(key);
        if (result != null) {
            return result.getValue();
        } else {
            return null;
        }
    }


    @Override
    public V find(Predicate<V> searcher) {
        if (root == null) {
            return null;
        }

        StackImplementation<TreeNode<K,V>> stack = new StackImplementation<>();
        TreeNode<K,V> current = root;
        TreeNode<K,V> parent = null;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                parent = current;
                current = current.getLeft();
            } else {
                parent = stack.pop();
                current = parent.getRight();
                if (searcher.test(parent.getPair().getValue())) {
                    return parent.getPair().getValue();
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
        return Integer.MAX_VALUE;
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        //empty
    }

    @Override
    public void clear() {
        root = null;
        count = 0;
    }

    @Override
    public Pair<K, V>[] toArray() throws Exception {
        @SuppressWarnings("unchecked")
        Pair<K, V>[] array = (Pair<K, V>[]) new Pair[count];
        if (root == null) {
            return array;
        } 
        AtomicInteger atomicIndex = new AtomicInteger(0);
        root.toArray(array, atomicIndex);
        return array;
    }

    @Override
    public int indexOf(K itemKey) {
        if (root == null) {
            return -1;
        }
        int index = 0;
        StackImplementation<TreeNode<K,V>> stack = new StackImplementation<>();
        TreeNode<K,V> current = root;
        TreeNode<K,V> parent = null;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                parent = current;
                current = parent.getLeft();
            } else {
                parent = stack.pop();
                current = parent.getRight();
                if (comparator.compare(itemKey, parent.getPair().getKey()) == 0) {
                    return index;
                }
                index++;
            }
        }

        return -1;
    }

    @Override
    public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
        if (root == null) {
            return null;
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index");
        }
        if (index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        int currentIndex = 0;
        StackImplementation<TreeNode<K,V>> stack = new StackImplementation<>();
        TreeNode<K,V> current = root;
        TreeNode<K,V> parent = null;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                parent = current;
                current = current.getLeft();
            } else {
                parent = stack.pop();
                if (currentIndex == index) {
                    current = parent.getRight();
                    return parent.getPair();
                }
                currentIndex++;
                current = parent.getRight();
            }
        }
        return null;
    }

    @Override
    public int findIndex(Predicate<V> searcher) {
        if (root == null) {
            return -1;
        }

        int currentIndex = 0;
        StackImplementation<TreeNode<K,V>> stack = new StackImplementation<>();
        TreeNode<K,V> current = root;
        TreeNode<K,V> parent = null;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                parent = current;
                current = current.getLeft();
            } else {
                parent = stack.pop();
                current = parent.getRight();
                if (searcher.test(parent.getPair().getValue())) {
                    return currentIndex;
                }

                currentIndex++;
            }
        }

        return -1;
    }

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
}
