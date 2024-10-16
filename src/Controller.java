import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private GridPane tableGrid;

    @FXML
    private TextField tableSize;

    private Table t;

    private int playerturn = 0;

    private int gridSize = 300;

    @FXML
    private Label won;

    @FXML
    public void setTable(ActionEvent event) {
        // Creating a new table of nxn size
        t = new Table(Integer.parseInt(tableSize.getText()));

        // Adding the columns to the table
        for (int j = 0; j < t.getTable().length - 1; j++) {
            tableGrid.getColumnConstraints().add(new ColumnConstraints(gridSize / t.getTable().length));
        }
        // Adding the rows to the table
        for (int k = 0; k < t.getTable().length - 1; k++) {
            tableGrid.getRowConstraints().add(new RowConstraints(gridSize / t.getTable().length));
        }
        // Adding the mouse listeners (invisible rectangles) to each i,j grid position
        for (int i = 0; i < t.getTable().length; i++) {
            for (int j = 0; j < t.getTable().length; j++) {
                Color fill = new Color(0, 0, 0, 0);
                Rectangle rectangle = new Rectangle(gridSize / t.getTable().length,
                        gridSize / t.getTable().length, fill);
                GridPane.setHalignment(rectangle, HPos.CENTER);
                GridPane.setValignment(rectangle, VPos.CENTER);
                GridPane.setConstraints(rectangle, i, j);

                rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent e) {
                        int row = GridPane.getRowIndex(rectangle);
                        int column = GridPane.getColumnIndex(rectangle);

                        // Checks if the position hasn't been occupied yet (equals 0) before adding
                        // elements to it
                        if (t.getTable()[row][column] == 0) {
                            if (playerturn == 0) {
                                addCircle(row, column);
                                System.out.println("Added Circle - Row: " + row + " Column: " + column);
                            } else {
                                addX(row, column);
                                System.out.println("Added X - Row: " + row + " Column: " + column);
                            }
                            verifyPosition(row, column);
                        }

                    }

                });

                // Drawing the table grid (the "#")
                if (i < t.getTable().length - 1) {
                    Line verticalLine = new Line(0, 0, 0, gridSize / t.getTable().length);
                    GridPane.setConstraints(verticalLine, i, j);
                    GridPane.setHalignment(verticalLine, HPos.RIGHT);
                    GridPane.setValignment(verticalLine, VPos.CENTER);
                    tableGrid.getChildren().add(verticalLine);
                }
                if (j < t.getTable().length - 1) {
                    Line horizontalLine = new Line(0, 0, gridSize / t.getTable().length, 0);
                    GridPane.setConstraints(horizontalLine, i, j);
                    GridPane.setHalignment(horizontalLine, HPos.CENTER);
                    GridPane.setValignment(horizontalLine, VPos.BOTTOM);
                    tableGrid.getChildren().add(horizontalLine);
                }

                tableGrid.getChildren().add(rectangle);

            }
        }
    }

    private void addCircle(int row, int column) {
        t.getTable()[row][column] = 1;
        Color stroke = new Color(0, 0, 0, 1); // Border at 100% opacity
        Color fill = new Color(0, 0, 0, 0); // 0% opacity circle filling
        double circleRadius = (gridSize / t.getTable().length) / 2;

        // Percentage of the radius to be subtracted so that the circle won't fill the
        // entire grid. 30% smaller.
        double circleBorderPercentage = circleRadius * 0.3;

        Circle circle = new Circle(circleRadius - circleBorderPercentage, fill);
        circle.setStroke(stroke);

        // Aligning the circle to the center and adding it into the grid
        GridPane.setHalignment(circle, HPos.CENTER);
        GridPane.setValignment(circle, VPos.CENTER);
        GridPane.setConstraints(circle, column, row);
        tableGrid.getChildren().add(circle);
        playerturn = 1;
    }

    private void addX(int row, int column) {
        t.getTable()[row][column] = 2;
        float lineSize = gridSize / t.getTable().length;

        // Percentage of the line length to be subtracted so that the "X" symbol won't
        // fill the entire grid. 30% smaller.
        double lineSizePercentage = lineSize * 0.3;

        // "\" part of the "X" symbol
        Line lineLeft = new Line(0, 0, lineSize - lineSizePercentage, lineSize - lineSizePercentage);
        // "/" part of the X symbol
        Line lineRight = new Line(0, lineSize - lineSizePercentage, lineSize - lineSizePercentage, 0);

        // The "\" and "/" lines above are overlapped to form the "X"

        // Aligning the lines to the center and adding it into the grid
        GridPane.setHalignment(lineLeft, HPos.CENTER);
        GridPane.setValignment(lineLeft, VPos.CENTER);
        GridPane.setHalignment(lineRight, HPos.CENTER);
        GridPane.setValignment(lineRight, VPos.CENTER);
        GridPane.setConstraints(lineLeft, column, row);
        GridPane.setConstraints(lineRight, column, row);
        tableGrid.getChildren().addAll(lineLeft, lineRight);
        playerturn = 0;
    }

    public void verifyPosition(int row, int column) {
        // Checks both vertical and horizontal table positions by default for every turn
        t.verifyHorizontal(row, column);
        t.verifyVertical(row, column);

        // Checks if the position belongs to the main diagonal (when i = j)
        if (row == column) {
            t.verifyMainDiagonal();
        }

        // Checks if the position belongs to the secondary diagonal (when
        // rowSize-[i+j] = 1 or columnSize-[i+j] = 1, since it is a squared matrix)
        if (t.getTable().length - (row + column) == 1) {
            t.verifySecondaryDiagonal();
        }

        if (t.getPlayerWon() != 0) {
            won.setText("Player who won: " + t.getPlayerWon());
            drawVictoryLine(row, column);
        }
    }

    public void drawVictoryLine(int row, int column) {
        // Draws the line over the elements according to a direction
        String winDirection = t.getWinDirection();
        if (winDirection.equals("Main Diagonal")) {

            for (int i = 0; i < t.getTable().length; i++) {
                float lineSize = gridSize / t.getTable().length;
                Line lineVictory = new Line(0, 0, lineSize, lineSize);
                GridPane.setHalignment(lineVictory, HPos.CENTER);
                GridPane.setValignment(lineVictory, VPos.CENTER);
                GridPane.setConstraints(lineVictory, i, i);
                tableGrid.getChildren().add(lineVictory);
            }

        } else if (winDirection.equals("Horizontal")) {
            for (int i = 0; i < t.getTable().length; i++) {
                float lineSize = gridSize / t.getTable().length;
                Line lineVictory = new Line(0, 0, lineSize, 0);
                GridPane.setHalignment(lineVictory, HPos.CENTER);
                GridPane.setValignment(lineVictory, VPos.CENTER);
                GridPane.setConstraints(lineVictory, i, row);
                tableGrid.getChildren().add(lineVictory);
            }
        } else if (winDirection.equals("Vertical")) {
            for (int i = 0; i < t.getTable().length; i++) {
                float lineSize = gridSize / t.getTable().length;
                Line lineVictory = new Line(0, 0, 0, lineSize);
                GridPane.setHalignment(lineVictory, HPos.CENTER);
                GridPane.setValignment(lineVictory, VPos.CENTER);
                GridPane.setConstraints(lineVictory, column, i);
                tableGrid.getChildren().add(lineVictory);
            }
        } else if (winDirection.equals("Secondary Diagonal")) {
            int columnLine = t.getTable().length - 1;
            for (int i = 0; i < t.getTable().length; i++) {
                float lineSize = gridSize / t.getTable().length;
                Line lineVictory = new Line(0, lineSize, lineSize, 0);
                GridPane.setHalignment(lineVictory, HPos.CENTER);
                GridPane.setValignment(lineVictory, VPos.CENTER);
                GridPane.setConstraints(lineVictory, columnLine, i);
                tableGrid.getChildren().add(lineVictory);
                columnLine--;
            }
        }
    }
}
