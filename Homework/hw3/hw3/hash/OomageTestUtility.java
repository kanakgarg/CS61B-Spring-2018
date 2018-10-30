package hw3.hash;

import java.util.List;
import java.util.HashMap;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {

        HashMap<Integer, Integer> test = new HashMap<>(M);
        for (Oomage o: oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            if (!test.containsKey(bucketNum)) {
                test.put(bucketNum, 0);
            }
            test.put(bucketNum, test.get(bucketNum) + 1);
        }


        for (int i = 0; i < M; i++) {
            if (test.get(i) < oomages.size() / 50 || test.get(i) > oomages.size() / 2.5) {
                return false;
            }
        }

        return true;
    }
}
