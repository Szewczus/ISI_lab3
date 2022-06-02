package front;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = Paths.get("E:\\studia\\3_semestr_mgr\\ISI\\lab3\\src\\main\\resources\\sample.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("E:\\studia\\3_semestr_mgr\\ISI\\lab3\\src\\main\\resources\\sample.fxml")));
        primaryStage.setTitle("Integracja systemów - Ewa Szewczak");
        primaryStage.setScene(new Scene(root, 1480, 475));
        primaryStage.setX(0);
        primaryStage.setY(400);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
