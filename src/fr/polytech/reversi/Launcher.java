package fr.polytech.reversi;

import java.io.IOException;

import fr.polytech.reversi.model.boardgame.BoardGame;
import fr.polytech.reversi.model.boardgame.Cell;
import fr.polytech.reversi.model.players.HumanPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The launcher of the application.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Launcher extends Application
{
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/polytech/reversi/view/resources/views/Reversi.fxml"));
		final Parent root = loader.load();
		final BoardGame boardGame = new BoardGame(8, 8, new HumanPlayer(Cell.BLACK_PAWN), new HumanPlayer(Cell.WHITE_PAWN), loader.getController());
		boardGame.init();

		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * The entry of the application.
	 * 
	 * @param args
	 *            The arguments.
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}