package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.database.DatabaseConnector;
import model.database.User;
import view.Loader;

public class LoginScreenController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegister;
    @FXML
    private TextField textName;
    @FXML
    private TextField textSecondName;
    @FXML
    private TextField textPesel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    @FXML
    public void loginAction() throws IOException {
        System.out.println("Login");

        if (!textName.getText().isEmpty()
                && !textSecondName.getText().isEmpty()
                && textPesel.getText().length() == 11) {

            User user = new User();
            user.setName(textName.getText());
            user.setSecondName(textSecondName.getText());
            user.setPesel(Long.parseLong(textPesel.getText()));

            try {
                if (DatabaseConnector.login(user)) {
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    FXMLLoader loader = Loader.getInstance().getMenuFXMLLoader();
                    Parent root = loader.load();
                    MenuController controller = loader.<MenuController>getController();
                    controller.setUser(user);
                    controller.readFromDatabase();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    System.out.println("Użytkownik nie zarejestrowany");
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void registerAction() {
        System.out.println("Register");

        if (!textName.getText().isEmpty()
                && !textSecondName.getText().isEmpty()
                && textPesel.getText().length() == 11) {
            User user = new User();
            user.setName(textName.getText());
            user.setSecondName(textSecondName.getText());
            user.setPesel(Long.parseLong(textPesel.getText()));

            try {
                if (!DatabaseConnector.findUser(user)) {
                    DatabaseConnector.register(user);
                } else {
                    System.out.println("User exists");
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void textPeselKeyTyped(KeyEvent evt) {
        String character = evt.getCharacter();
        if (character.hashCode() < 48 || character.hashCode() > 57) {
            evt.consume();
        }
    }

}
