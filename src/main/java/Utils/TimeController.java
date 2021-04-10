package Utils;

public class TimeController {
    private static long startTime = System.currentTimeMillis();

    public static void setTimeLimit(long timeLimit) {
        TimeController.timeLimit = timeLimit;
    }

    public static void setTimeLimit(int minutes) {
        TimeController.timeLimit = minutes * 60000L;
    }

    private static long timeLimit;

    public static void reset() {
        startTime = System.currentTimeMillis();
    }

    public static boolean timeIsUp() {
        return System.currentTimeMillis() - startTime >= timeLimit;
    }

}
