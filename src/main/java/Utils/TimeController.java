package Utils;

public class TimeController{
    private long startTime;
    private final long timeLimit;

    public TimeController(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public TimeController(int minutes) {
        this(minutes * 60000L);
        startTime = System.currentTimeMillis();
    }

    public void reset(){
        startTime = System.currentTimeMillis();
    }

    public boolean timeIsUp(){
        return System.currentTimeMillis() - startTime >= timeLimit;
    }

}
