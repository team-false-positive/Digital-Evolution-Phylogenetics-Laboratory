package visualization;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StatsPane extends HBox {

    public StatsPane() {
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        Label placeholder = new Label("Stats panel — coming Week 2 (allele frequency, generation count, etc.)");
        this.getChildren().add(placeholder);
    }
}