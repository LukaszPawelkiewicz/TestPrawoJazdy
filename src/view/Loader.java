package view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class Loader {

    private static Loader instance;

    private AnchorPane loginScreen;
    private AnchorPane menu;
    private AnchorPane questionBasic;
    private AnchorPane questionHelp;

    private FXMLLoader questionBasicLoader;
    private FXMLLoader questionHelpFXMLLoader;
    private FXMLLoader resultFXMLLoader;
    private FXMLLoader menuFXMLLoader;

    private Loader() {
        try {
            loginScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            menu = (AnchorPane) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            questionBasic = (AnchorPane) FXMLLoader.load(getClass().getResource("QuestionBasic.fxml"));
            questionHelp = (AnchorPane) FXMLLoader.load(getClass().getResource("QuestionHelpWindow.fxml"));

            questionBasicLoader = new FXMLLoader(getClass().getResource("QuestionBasic.fxml"));
            resultFXMLLoader = new FXMLLoader(getClass().getResource("Result.fxml"));
            questionHelpFXMLLoader = new FXMLLoader(getClass().getResource("QuestionHelpWindow.fxml"));
            menuFXMLLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Loader getInstance() {
        if (instance == null) {
            instance = new Loader();
        }
        return instance;
    }

    public AnchorPane getLoginScreen() {
        return loginScreen;
    }

    public AnchorPane getMenu() {
        return menu;
    }

    public AnchorPane getQuestionBasic() {
        return questionBasic;
    }

    public AnchorPane getQuestionHelp() {
        return questionHelp;
    }

    public FXMLLoader getQuestionHelpFXMLLoader() {
        return questionHelpFXMLLoader;
    }

    public FXMLLoader getResultFXMLLoader() {
        return resultFXMLLoader;
    }

    public FXMLLoader getQuestionBasicLoader() {
        return questionBasicLoader;
    }

    public FXMLLoader getMenuFXMLLoader() {
        return menuFXMLLoader;
    }
}
