import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TopPanel topPanel = new TopPanel(Main.this);
        Scene prelimScene = new Scene(topPanel);
        //------------------------------------------------------------
        primaryStage.setScene(prelimScene);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Wolfram Code Viewer");
        //------------------------------------------------------------
        this.primaryStage = primaryStage;
        this.primaryStage.show();
    }

    public void startWolfram(int ruleNum, int verticalSize) {
        FullWindow fullWindow = new FullWindow(ruleNum, verticalSize);
        Scene wolframScene = new Scene(fullWindow);
        String styleSheet = "style.css";
        wolframScene.getStylesheets().add(styleSheet);
        primaryStage.setScene(wolframScene);
    }
}
