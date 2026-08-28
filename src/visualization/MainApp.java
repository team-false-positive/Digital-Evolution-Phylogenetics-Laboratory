package visualization;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        EcosystemPane ecosystemPane = new EcosystemPane();
        TreePane treePane = new TreePane();
        StatsPane statsPane = new StatsPane();

        SplitPane centerSplit = new SplitPane(ecosystemPane, treePane);
        centerSplit.setDividerPositions(0.5);

        root.setCenter(centerSplit);
        root.setBottom(statsPane);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setTitle("EvoLab");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}