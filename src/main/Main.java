package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.Loader;

public class Main extends Application {

    private Scene menuScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane root = Loader.getInstance().getLoginScreen();
        menuScene = new Scene(root);
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Test na prawo jazdy");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
