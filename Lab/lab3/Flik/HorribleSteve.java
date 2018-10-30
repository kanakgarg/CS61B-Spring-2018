public class HorribleSteve {
    public static void main(String [] args) {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
            else{
                System.out.println(i + " " + j);
            }
        }
        System.out.println("i is " + i);
    }
}
