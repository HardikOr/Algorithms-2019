package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    public int height() {
        return height(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {
        private Deque<Node<T>> stack = new LinkedList<>();
        private Node<T> cur;

        private BinaryTreeIterator() {
            Node<T> node = root;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            cur = stack.peek();
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        @Override
        public T next() {
            if (!hasNext())
                throw new IllegalArgumentException();

            Node<T> tmp = stack.pop();
            Node<T> res = tmp;

            if (tmp.right != null) {
                tmp = tmp.right;

                while (tmp != null) {
                    stack.push(tmp);
                    tmp = tmp.left;
                }
            }

            cur = res;
            return cur.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            // TODO
            throw new NotImplementedError();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    class SubTree extends BinaryTree<T>{
        private T fromElement;
        private T toElement;
        private BinaryTree<T> tree;

        SubTree(BinaryTree<T> tree, T fromElement, T toElement) {
            this.tree = tree;
            this.fromElement = fromElement;
            this.toElement = toElement;
        }

        @Override
        public int size() {
//            size = tree.size();
            int s = 0;
            BinaryTreeIterator i = new BinaryTreeIterator();
            while (i.hasNext()) {
                if (isValid(i.next()))
                    s++;
            }
            return s;
        }

        @Override
        public boolean add(T t) {
            if (isValid(t))
//                return super.add(t);
                return tree.add(t);
            else
                throw new IllegalArgumentException();
        }

        @Override
        public boolean contains(Object o) {
            @SuppressWarnings("unchecked")
            boolean isV = isValid((T) o);
            return tree.contains(o) && isV;
        }

        private boolean isValid(T el) {
            return (fromElement == null || el.compareTo(fromElement) >= 0) &&
                    (toElement == null || el.compareTo(toElement) < 0);
        }
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * [Найти множество всех элементов в диапазоне {[fromElement, toElement)}
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        return new SubTree(this, fromElement, toElement);
//        throw new NotImplementedError();
//        return toSortedSet(root, fromElement, toElement, 1);
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
//        SubTree tree = new SubTree<>(null, toElement);
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
