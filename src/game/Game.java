package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        PianoPane p = new PianoPane();
        Scene sc = new Scene(p, 320, 600);
        primaryStage.setScene(sc);
        p.requestFocus();
        primaryStage.show();

    }
}
