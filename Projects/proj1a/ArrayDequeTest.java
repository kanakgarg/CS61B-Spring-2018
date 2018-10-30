public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> arahn = new ArrayDeque();
        for (int i = 0; i < 25; i++) {
            arahn.addFirst(i);
        }
        for (int i = 0; i < 20; i++) {
            arahn.removeLast();
        }

        arahn.printDeque();
    }
}
