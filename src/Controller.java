import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Controller {

    @FXML
    private Button button;

    @FXML
    private Line linewin;

    @FXML
    private GridPane gridtest;

    @FXML
    private TextField selecttablesize;

    private Table t;

    @FXML
    public void onPressed(ActionEvent event) {
        t = new Table(Integer.parseInt(selecttablesize.getText()));
        for (int j = 0; j < t.getTable().length - 1; j++) {
            gridtest.getColumnConstraints().add(new ColumnConstraints(30));
        }
        for (int k = 0; k < t.getTable().length - 1; k++) {
            gridtest.getRowConstraints().add(new RowConstraints(30));
        }
        for (int i = 0; i < t.getTable().length; i++) {
            for (int j = 0; j < t.getTable().length; j++) {
                t.getTable()[i][j] = 0;
                if (i % 2 == 0) {
                    Color stroke = new Color(0, 0, 0, 1);
                    Color fill = new Color(0, 0, 0, 0);
                    Circle circle = new Circle(10, fill);
                    circle.setStroke(stroke);
                    GridPane.setHalignment(circle, HPos.CENTER);
                    GridPane.setValignment(circle, VPos.CENTER);
                    GridPane.setConstraints(circle, i, j);
                    gridtest.getChildren().add(circle);
                } else {
                    Line lineLeft = new Line(0, 0, 15, 15);
                    Line lineRight = new Line(0, 15, 15, 0);
                    // Button button = new Button(Integer.toString(t.getTable()[i][j]));
                    GridPane.setHalignment(lineLeft, HPos.CENTER);
                    GridPane.setValignment(lineLeft, VPos.CENTER);
                    GridPane.setHalignment(lineRight, HPos.CENTER);
                    GridPane.setValignment(lineRight, VPos.CENTER);
                    GridPane.setConstraints(lineLeft, i, j);
                    GridPane.setConstraints(lineRight, i, j);

                    gridtest.getChildren().addAll(lineLeft, lineRight);
                }
            }
        }
        linewin.setStartX(0);
        linewin.setStartY(0);
        linewin.setEndX(-100);
        linewin.setEndY(-100);
        linewin.setVisible(true);
    }

}
