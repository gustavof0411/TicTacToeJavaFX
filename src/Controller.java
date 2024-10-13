import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

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

    private int playerturn = 0;

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
                System.out.println(t.getTable()[i][j]);
                Color fill = new Color(0, 0, 0, 0);
                Rectangle rec = new Rectangle(29, 29, fill);
                GridPane.setHalignment(rec, HPos.CENTER);
                GridPane.setValignment(rec, VPos.CENTER);
                GridPane.setConstraints(rec, i, j);

                rec.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent e) {

                        System.out.println(
                                "Row: " + GridPane.getRowIndex(rec) + " Column: " + GridPane.getColumnIndex(rec));
                        if (playerturn == 0) {
                            addCircle(GridPane.getRowIndex(rec), GridPane.getColumnIndex(rec));
                        } else {
                            addX(GridPane.getRowIndex(rec), GridPane.getColumnIndex(rec));
                        }

                    }

                });
                gridtest.getChildren().add(rec);
            }
        }
    }

    private void addCircle(int row, int column) {
        if (t.getTable()[row][column] == 0) {
            t.getTable()[row][column] = 1;
            Color stroke = new Color(0, 0, 0, 1);
            Color fill = new Color(0, 0, 0, 0);
            Circle circle = new Circle(10, fill);
            circle.setStroke(stroke);
            GridPane.setHalignment(circle, HPos.CENTER);
            GridPane.setValignment(circle, VPos.CENTER);
            GridPane.setConstraints(circle, column, row);
            gridtest.getChildren().add(circle);
            playerturn = 1;
        }
    }

    private void addX(int row, int column) {
        if (t.getTable()[row][column] == 0) {
            t.getTable()[row][column] = 2;
            Line lineLeft = new Line(0, 0, 15, 15);
            Line lineRight = new Line(0, 15, 15, 0);
            GridPane.setHalignment(lineLeft, HPos.CENTER);
            GridPane.setValignment(lineLeft, VPos.CENTER);
            GridPane.setHalignment(lineRight, HPos.CENTER);
            GridPane.setValignment(lineRight, VPos.CENTER);
            GridPane.setConstraints(lineLeft, column, row);
            GridPane.setConstraints(lineRight, column, row);
            gridtest.getChildren().addAll(lineLeft, lineRight);
            playerturn = 0;
        }
    }

}
