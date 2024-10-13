import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Controller {

    @FXML
    private Button button;

    @FXML
    private GridPane grid;

    @FXML
    private Line linewin;

    Table t = new Table(3);

    @FXML
    public void onPressed(ActionEvent event){
        t = new Table(3);
        for (int i = 0; i<t.getTable().length; i++){
            for (int j = 0; j<t.getTable().length; j++){
                t.getTable()[i][j] = 0;
                Color stroke = new Color(0,0,0,1);
                Color fill = new Color(0,0,0,0);          
                Circle circle = new Circle(15, fill);
                circle.setStroke(stroke);
                //Button button = new Button(Integer.toString(t.getTable()[i][j]));
                GridPane.setConstraints(circle,i,j);
                grid.getChildren().addAll(circle);
            }
        }
        linewin.setStartX(0);
        linewin.setStartY(0);
        linewin.setEndX(-100);
        linewin.setEndY(-100);
        linewin.setVisible(true);
    }

}
