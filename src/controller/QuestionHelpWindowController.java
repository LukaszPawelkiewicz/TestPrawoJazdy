package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class QuestionHelpWindowController implements Initializable {

    @FXML
    private TextArea helpText;
    @FXML
    private Button btnOk;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void actionOk() {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    public void initData(String text) {
        String[] lines = text.split("/n");
        for (String line : lines) {
            helpText.appendText(line);
            helpText.appendText("\n");
        }
    }
}
