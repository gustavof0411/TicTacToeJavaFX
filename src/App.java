import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
/*         Table tabletest = new Table(3);
        for (int i = 0; i<tabletest.getTable().length; i++){
            String row = "";
            for (int j = 0; j < tabletest.getTable().length; j++){
                row = row + Integer.toString(tabletest.getTable()[i][j]) + " ";
            }
            System.out.println(row);
        } */
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout.fxml"));
        Parent root = fxmlLoader.load();
        Scene screen = new Scene(root);

        arg0.setTitle("This is a test");
        arg0.setScene(screen);
        arg0.show();
        // test
    }
}
