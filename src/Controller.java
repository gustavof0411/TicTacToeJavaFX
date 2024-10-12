import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    void onPressed(ActionEvent event) {
        label.setText("The text has been changed");
    }

}
