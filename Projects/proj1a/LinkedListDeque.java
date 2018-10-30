public class LinkedListDeque<T> implements Deque<T>{
    private class TypeNode {
        TypeNode prev;
        T current;
        TypeNode next;

        TypeNode(TypeNode p, T c, TypeNode n) {
            prev = p;
            current = c;
            next = n;
        }
    }

    private TypeNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new TypeNode(sentinel, null, sentinel);
        size = 0;
    }

    @Override
    public void addFirst(T item) {

        if (size == 0) {
            sentinel.next = new TypeNode(sentinel, item, sentinel);
            sentinel.prev = sentinel.next;
            size += 1;
            return;
        }
        size += 1;
        sentinel.next = new TypeNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;


    }

    @Override
    public void addLast(T item) {

        if (size == 0) {
            this.addFirst(item);
            return;
        }
        sentinel.prev.next = new TypeNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T val = sentinel.next.current;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return val;

    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T val =  sentinel.prev.current;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return val;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        TypeNode current = sentinel.next;
        while (current.current != null) {
            System.out.print(current.current + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        TypeNode current  = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.current;
    }

    private T getRHelper(TypeNode c, int i) {
        if (i == 0) {
            return c.current;
        }
        return getRHelper(c.next, i - 1);
    }

    public T getRecursive(int index) {
        return getRHelper(this.sentinel.next, index);
    }
}
