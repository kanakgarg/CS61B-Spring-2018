public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> letters = new LinkedListDeque<Character>();
        for (char c: word.toCharArray()) {
            letters.addLast(c);
        }
        return letters;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (word.charAt(0) == word.charAt(word.length() - 1)) {
            return isPalindrome(word.substring(1, word.length() - 1));
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
            return isPalindrome(word.substring(1, word.length() - 1), cc);
        }
        return false;
    }
}
