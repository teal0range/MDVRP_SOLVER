package Utils;

import java.util.*;

public class RandomController {
    private static Random random = new Random(System.currentTimeMillis());

    public static void setSeed(long rnd) {
        random = new Random(rnd);
    }

    public static void shuffle(List<?> list) {
        Collections.shuffle(list, random);
    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public static ArrayList<Integer> randIndex(int bound){
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < bound; i++) {
            res.set(i, i);
        }
        Collections.shuffle(res);
        return res;
    }
}
