public class ArrayDeque<T> implements Deque<T> {
    private T[] array;
    private int size;
    private int arraySize;
    private int nextFirst;
    private int nextLast;



    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        arraySize = 8;
    }


    private void resize(int length) {
        T[] a = (T[]) new Object[length];
        System.arraycopy(array, nextFirst + 1, a, 0, arraySize - (nextFirst + 1));
        System.arraycopy(array, 0, a, arraySize - (nextFirst + 1), nextLast);
        nextLast = size;
        nextFirst = length - 1;
        arraySize = length;
        array = a;

    }

    private void resize(double length) {
        T[] a = (T[]) new Object[(int) length];
        if (nextFirst > nextLast) {
            System.arraycopy(array, nextFirst + 1, a, 0, arraySize - (nextFirst + 1));
            System.arraycopy(array, 0, a, arraySize - (nextFirst + 1), nextLast);
        } else {
            System.arraycopy(array, nextFirst + 1, a, 0, nextLast - nextFirst);
        }
        nextLast = size;
        nextFirst = (int) length - 1;
        arraySize = (int) length;
        array = a;

    }

    private void check() {
        if (arraySize <= 16) {
            return;
        }
        if (size < 0.25 * arraySize) {
            resize((double) arraySize / 2);
        }
    }

    @Override
    public void addFirst(T item) {
        if (size == arraySize) {
            resize(arraySize * 2);
        } else if (nextFirst < 0) {
            nextFirst = arraySize - 1;
        }

        array[nextFirst] = item;
        nextFirst -= 1;
        size += 1;

    }
    @Override
    public void addLast(T item) {

        if (size == arraySize) {
            resize(arraySize * 2);
        } else if (nextLast > arraySize - 1) {
            nextLast = 0;
        }

        array[nextLast] = item;
        nextLast += 1;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        nextFirst += 1;
        if (nextFirst > arraySize - 1) {
            nextFirst = 0;
        }
        T val = array[nextFirst];
        array[nextFirst] = null;
        size -= 1;
        check();
        return val;

    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        nextLast -= 1;
        if (nextLast < 0) {
            nextLast = arraySize - 1;
        }
        T val = array[nextLast];
        array[nextLast] = null;
        size -= 1;
        check();
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
        for (int i = nextFirst + 1; i < arraySize; i++) {

            System.out.print(array[i] + " ");
        }
        for (int i = 0; i < nextLast; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        int start = nextFirst + 1 + index;
        if (start >= arraySize) {
            start -= arraySize;
        }
        return array[start];
    }

}
