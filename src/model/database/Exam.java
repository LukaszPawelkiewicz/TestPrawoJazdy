package model.database;

import java.util.Date;

public class Exam {

    private String date;
    private long points;

    public Exam() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

}
