import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    private Button button;

    @FXML
    private GridPane grid;

    @FXML
    private Label label;

    Table t = new Table(3);

    @FXML
    public void onPressed(ActionEvent event){
        t = new Table(3);
        for (int i = 0; i<t.getTable().length; i++){
            t.getTable()[i] = 0;
            System.out.println("yeah");
        }
        Label label2 = new Label("O");
        Label label3 = new Label("O");
        Label label4 = new Label("O");
        GridPane.setConstraints(label2, 0, 0);
        GridPane.setConstraints(label3, 1, 0);
        GridPane.setConstraints(label4, 2, 0);

        grid.getChildren().addAll(label2, label3, label4);
    }

}
