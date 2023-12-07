package amazons;


import amazons.controller.GameController;
import amazons.game.Game;
import amazons.player.AIBasicPlayer;
import amazons.player.AIGreedyPlayer;
import amazons.player.GUIPLayer;
import amazons.util.ImageUtil;
import amazons.viewer.BoardView;
import amazons.viewer.MenuView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class AppGUI extends Application {


    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        GameController controller = new GameController(game, new GUIPLayer(), new AIBasicPlayer());
        int numberOfColumns =  game.getNumberOfColumns();
        int numberOfRows =  game.getNumberOfRows();

        primaryStage.setTitle("Amazons");
        primaryStage.getIcons().add(ImageUtil.loadImage("images/icon.png", 16, 16));



        //main BorderPane
        BorderPane pane = new BorderPane();

        //board with column and row markings
        GridPane table = new GridPane();
        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            table.add(newRowLabel(rowIndex), 0, rowIndex + 1, 1, 1);
            table.add(newRowLabel(rowIndex), 9, rowIndex + 1, 1, 1);
            table.add(newColLabel(rowIndex), rowIndex + 1, 0, 1, 1);
            table.add(newColLabel(rowIndex), rowIndex + 1, 9, 1, 1);
        }
        BoardView boardView;
        table.add(boardView = new BoardView(controller), 1, 1, numberOfColumns, numberOfRows);
        table.setAlignment(Pos.CENTER);
        pane.setCenter(table);
        controller.setView(boardView);

        //menu on the bottom with status text and option buttons
        MenuView menuView;
        pane.setBottom(menuView = new MenuView(controller));
        controller.setMenu(menuView);

        //scene
        Scene scene = new Scene(pane, 540, 575);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());

        // Go !!
        controller.startGame();

    }


    private Label newRowLabel(int i) {
        Label l = new Label(String.valueOf(i));
        l.setMinSize(20, 50);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    private Label newColLabel(int i) {
        Label l = new Label(String.valueOf((char) (i + 65)));
        l.setMinSize(50, 20);
        l.setAlignment(Pos.CENTER);
        return l;
    }
}
