import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    private Button button;

    @FXML
    private GridPane grid;

    Table t = new Table(3);

    @FXML
    public void onPressed(ActionEvent event){
        t = new Table(3);
        for (int i = 0; i<t.getTable().length; i++){
            for (int j = 0; j<t.getTable().length; j++){
                t.getTable()[i][j] = 0;
                Button button = new Button(Integer.toString(t.getTable()[i][j]));
                GridPane.setConstraints(button,i,j);
                System.out.println(t.getTable()[i][j]);
                grid.getChildren().addAll(button);
            }
        }
    }

}
