package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.database.DatabaseConnector;
import model.database.Exam;
import model.database.ExamSimpleProperty;
import model.database.User;
import view.Loader;

public class MenuController implements Initializable {

    @FXML
    private Button start;
    @FXML
    private Button exit;
    @FXML
    private TableView<ExamSimpleProperty> examsTable;
    @FXML
    private TableColumn<ExamSimpleProperty, Long> pointsColumn;
    @FXML
    private TableColumn<ExamSimpleProperty, String> dateColumn;

    private User user;
    private List<Exam> exams;

    private final ObservableList<ExamSimpleProperty> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void actionExit() {
        System.out.println("Exit");

        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    public void actionStart() throws IOException {
        System.out.println("Start");

        Stage stage = (Stage) start.getScene().getWindow();
        FXMLLoader loader = Loader.getInstance().getQuestionBasicLoader();
        Parent root = loader.load();
        QuestionBasicController controller = loader.<QuestionBasicController>getController();
        controller.setUser(user);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void readFromDatabase() {
        try {
            exams = DatabaseConnector.readAllUserExams(user);
            updateTable();
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateTable() {
        exams.stream().map((exam) -> {
            data.add(new ExamSimpleProperty(exam));
            return exam;
        }).map((_item) -> {
            pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
            return _item;
        }).map((_item) -> {
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            return _item;
        }).forEachOrdered((_item) -> {
            examsTable.setItems(data);
        });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
