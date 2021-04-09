package Utils;

public class TimeController{
    private static long startTime;

    public static void setTimeLimit(long timeLimit) {
        TimeController.timeLimit = timeLimit;
    }

    private static long timeLimit;

    public void reset(){
        startTime = System.currentTimeMillis();
    }

    public static boolean timeIsUp(){
        return System.currentTimeMillis() - startTime >= timeLimit;
    }

}
