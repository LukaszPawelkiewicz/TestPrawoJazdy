package controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.database.DatabaseConnector;
import model.database.Exam;
import model.database.User;

public class ResultController implements Initializable {

    @FXML
    private Label pointsLabel;
    @FXML
    private Button btnOk;

    private User user;
    private Exam exam;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(int points, User user) {
        pointsLabel.setText(String.valueOf(points));
        setUser(user);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(new Date());
        System.out.println(date);
        
        exam = new Exam();
        exam.setDate(date);
        exam.setPoints(Long.parseLong(pointsLabel.getText()));
        
        DatabaseConnector.saveExam(user, exam);
    }

    public void actionOk() {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
