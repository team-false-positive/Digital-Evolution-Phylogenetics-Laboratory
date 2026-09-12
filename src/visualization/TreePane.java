package visualization;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class TreePane extends StackPane {

    public TreePane() {
        Canvas canvas = new Canvas(600, 700);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Placeholder tree lines (fake branches)
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(300, 650, 300, 400);
        gc.strokeLine(300, 400, 200, 250);
        gc.strokeLine(300, 400, 400, 250);
        gc.strokeLine(200, 250, 150, 100);
        gc.strokeLine(200, 250, 250, 100);

        this.getChildren().add(canvas);
    }
}