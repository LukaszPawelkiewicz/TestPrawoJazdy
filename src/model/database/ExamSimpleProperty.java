package model.database;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class ExamSimpleProperty {

    private final SimpleLongProperty points;
    private final SimpleStringProperty date;

    public ExamSimpleProperty(Exam exam) {
        points = new SimpleLongProperty(exam.getPoints());
        date = new SimpleStringProperty(exam.getDate());
    }

    public Long getPoints() {
        return points.getValue();
    }

    public String getDate() {
        return date.getValue();
    }
}
