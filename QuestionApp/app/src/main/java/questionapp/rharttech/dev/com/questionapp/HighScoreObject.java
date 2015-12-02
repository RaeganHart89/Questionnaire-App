package questionapp.rharttech.dev.com.questionapp;

public class HighScoreObject {
    //Variables
    private int score;
    private String name;
    private long time;
    //Set return values
    public HighScoreObject(int score, String name, long time) {
        this.score = score;
        this.name = name;
        this.time = time;
    }
    //Return values
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }
}
