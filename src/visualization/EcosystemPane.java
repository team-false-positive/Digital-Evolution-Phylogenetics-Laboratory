package visualization;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class EcosystemPane extends StackPane {

    public EcosystemPane() {
        Canvas canvas = new Canvas(600, 700);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Placeholder terrain background
        gc.setFill(Color.DARKSEAGREEN);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Placeholder organisms (just circles for now)
        gc.setFill(Color.CRIMSON);
        gc.fillOval(100, 150, 20, 20);
        gc.setFill(Color.DARKBLUE);
        gc.fillOval(250, 300, 20, 20);
        gc.setFill(Color.GOLD);
        gc.fillOval(400, 500, 20, 20);

        this.getChildren().add(canvas);
    }
}