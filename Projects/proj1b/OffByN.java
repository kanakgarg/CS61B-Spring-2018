public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int x) {
        n = x;
    }


    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (Math.abs(diff) == n) {
            return true;
        }
        return false;
    }
}
