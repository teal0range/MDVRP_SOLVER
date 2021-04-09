package Utils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

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
}
