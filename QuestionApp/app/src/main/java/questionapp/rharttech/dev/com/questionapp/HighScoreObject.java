package questionapp.rharttech.dev.com.questionapp;

public class HighScoreObject {

    private int score;
    private String name;
    private long time;


    public HighScoreObject() {
//nothing here!
    }

    public HighScoreObject(int score, String name, long time) {
        this.score = score;
        this.name = name;
        this.time = time;
    }

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
