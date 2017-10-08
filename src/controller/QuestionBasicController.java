package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Img;
import model.TestNaPrawoJazdy;
import model.database.User;
import view.Loader;

public class QuestionBasicController implements Initializable {

    @FXML
    private TextArea questionText;
    @FXML
    private Label questionNumber;
    @FXML
    private ImageView img;
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnA;
    @FXML
    private Button btnB;
    @FXML
    private Button btnC;
    @FXML
    private Pane trueFalsePane;
    @FXML
    private Pane singleChoicePane;

    private TestNaPrawoJazdy testNaPrawoJazdy;
    private User user;
    private boolean paneNotChanged = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void init() {
        testNaPrawoJazdy = new TestNaPrawoJazdy();
        prepareQuestion();
    }

    public void actionYes() {
        if ("TAK".equals(testNaPrawoJazdy.getCurrentQuestion().getAnswer())) {
            this.testNaPrawoJazdy.correctAnswer();
        } else {
            this.testNaPrawoJazdy.incorrectAnswer();
        }
        prepareQuestion();

        if ("4".equals(this.testNaPrawoJazdy.getCurrentQuestion().getNumber1()) && this.paneNotChanged) {
            changePane();
            this.paneNotChanged = false;
        }

        if (testNaPrawoJazdy.isEndOfTest()) {
            try {
                showResults(btnYes);
            } catch (IOException ex) {
                Logger.getLogger(QuestionBasicController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void actionNo() {
        if ("NIE".equals(testNaPrawoJazdy.getCurrentQuestion().getAnswer())) {
            this.testNaPrawoJazdy.correctAnswer();
        } else {
            this.testNaPrawoJazdy.incorrectAnswer();
        }
        prepareQuestion();

        if ("4".equals(this.testNaPrawoJazdy.getCurrentQuestion().getNumber1()) && this.paneNotChanged) {
            changePane();
            this.paneNotChanged = false;
        }
    }

    public void actionA() {
        if ("A".equals(testNaPrawoJazdy.getCurrentQuestion().getAnswer())) {
            this.testNaPrawoJazdy.correctAnswer();
        } else {
            this.testNaPrawoJazdy.incorrectAnswer();
        }
        prepareQuestion();

        if (testNaPrawoJazdy.isEndOfTest()) {
            try {
                showResults(btnNo);
            } catch (IOException ex) {
                Logger.getLogger(QuestionBasicController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void actionB() {
        if ("B".equals(testNaPrawoJazdy.getCurrentQuestion().getAnswer())) {
            this.testNaPrawoJazdy.correctAnswer();
        } else {
            this.testNaPrawoJazdy.incorrectAnswer();
        }
        prepareQuestion();

        if (testNaPrawoJazdy.isEndOfTest()) {
            try {
                showResults(btnNo);
            } catch (IOException ex) {
                Logger.getLogger(QuestionBasicController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void actionC() {
        if ("C".equals(testNaPrawoJazdy.getCurrentQuestion().getAnswer())) {
            this.testNaPrawoJazdy.correctAnswer();
        } else {
            this.testNaPrawoJazdy.incorrectAnswer();
        }
        prepareQuestion();

        if (testNaPrawoJazdy.isEndOfTest()) {
            try {
                showResults(btnNo);
            } catch (IOException ex) {
                Logger.getLogger(QuestionBasicController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void actionNext() {
        this.testNaPrawoJazdy.incorrectAnswer();
        prepareQuestion();

        if ("4".equals(this.testNaPrawoJazdy.getCurrentQuestion().getNumber1()) && this.paneNotChanged) {
            changePane();
            this.paneNotChanged = false;
        }

        if (testNaPrawoJazdy.isEndOfTest()) {
            try {
                showResults(btnNo);
            } catch (IOException ex) {
                Logger.getLogger(QuestionBasicController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void changePane() {
        this.trueFalsePane.setVisible(false);
        this.singleChoicePane.setVisible(true);
    }

    private void showResults(Node node) throws IOException {
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = Loader.getInstance().getResultFXMLLoader();
        Parent root = loader.load();
        ResultController controller = loader.<ResultController>getController();
        controller.initData(this.testNaPrawoJazdy.getPoints(), this.user);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void prepareQuestion() {
        Img.loadImage(testNaPrawoJazdy.getCurrentQuestion().getQuestionImg());
        this.img.setImage(Img.getInstance().getImg());
        this.questionText.setText(testNaPrawoJazdy.getCurrentQuestion().getText());
        this.questionNumber.setText(testNaPrawoJazdy.getCurrentQuestion().getNumber2());
    }

    public void actionHelp() throws IOException {

        FXMLLoader loader = (FXMLLoader) Loader.getInstance().getQuestionHelpFXMLLoader();
        Parent root = (Parent) loader.load();
        QuestionHelpWindowController controller = loader.<QuestionHelpWindowController>getController();
        controller.initData(this.testNaPrawoJazdy.getCurrentQuestion().getHelp());

        Scene scene;
        if (root.getScene() != null) {
            scene = root.getScene();
        } else {
            scene = new Scene(root);

        }
        Stage stage = new Stage();
        stage.setTitle("Wskazówka");
        stage.setScene(scene);
        stage.show();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        init();
    }

}
