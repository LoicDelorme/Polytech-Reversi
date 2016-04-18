package fr.polytech.reversi.view;

import java.net.URL;
import java.util.ResourceBundle;

import fr.polytech.reversi.model.boardgame.BoardGame;
import fr.polytech.reversi.model.boardgame.Cell;
import fr.polytech.reversi.model.boardgame.Position;
import fr.polytech.reversi.model.boardgame.exceptions.BoardGameException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * This class represents The JavaFX reversi view.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class JavaFXReversiView implements IView, Initializable
{
	/**
	 * The player text.
	 */
	private static final String PLAYER_TEXT = "Joueur %d : %d";

	/**
	 * The version text.
	 */
	private static final String VERSION_TEXT = "version 1.0";

	/**
	 * The board game.
	 */
	@FXML
	private GridPane boardGame;

	/**
	 * The reversi version.
	 */
	@FXML
	private Label version;

	/**
	 * The player one score.
	 */
	@FXML
	private Label playerOneScore;

	/**
	 * The player two score.
	 */
	@FXML
	private Label playerTwoScore;

	/**
	 * The player one moves.
	 */
	@FXML
	private Label playerOneMoves;

	/**
	 * The player two moves.
	 */
	@FXML
	private Label playerTwoMoves;

	/**
	 * The player one HBox.
	 */
	@FXML
	private HBox playerOneHBox;

	/**
	 * The player two HBox.
	 */
	@FXML
	private HBox playerTwoHBox;

	/**
	 * The message.
	 */
	@FXML
	private Label message;

	/**
	 * @see fr.polytech.reversi.view.IView#notifyUpdateBoardGame(fr.polytech.reversi.model.boardgame.BoardGame)
	 */
	@Override
	public void notifyUpdateBoardGame(BoardGame boardGame)
	{
		this.boardGame.getChildren().remove(1, this.boardGame.getChildren().size());

		final Cell[][] boardGameRepresentation = boardGame.getBoardGame();
		final Cell currentPlayerPawn = boardGame.getCurrentPlayer().getCellRepresentation();
		Cell currentCell;

		for (int x = 0; x < boardGameRepresentation.length; x++)
		{
			for (int y = 0; y < boardGameRepresentation[0].length; y++)
			{
				final int xTemp = x;
				final int yTemp = y;

				currentCell = boardGameRepresentation[xTemp][yTemp];
				final ImageView imageView = new ImageView(currentCell.getImagePath());
				final Pane imagePane = new Pane(imageView);

				imageView.fitWidthProperty().bind(imagePane.widthProperty());
				imageView.fitHeightProperty().bind(imagePane.heightProperty());

				if (currentCell == Cell.EMPTY)
				{
					imagePane.setOnMouseClicked(e ->
					{
						try
						{
							boardGame.markCell(new Position(xTemp, yTemp));
						}
						catch (Exception e1)
						{
							// Nothing.
						}
					});

					imagePane.setOnMouseEntered(e ->
					{
						try
						{
							boardGame.checkMoveIsLegal(xTemp, yTemp, currentPlayerPawn);
							imageView.setImage(new Image("/fr/polytech/reversi/view/resources/images/grey_pawn.png"));
						}
						catch (BoardGameException e2)
						{
							// Nothing.
						}
					});

					imagePane.setOnMouseExited(e ->
					{
						imageView.setImage(new Image("/fr/polytech/reversi/view/resources/images/invisible_pawn.png"));
					});
				}

				this.boardGame.add(imagePane, y, x);
			}
		}
	}

	/**
	 * @see fr.polytech.reversi.view.IView#notifyUpdateScore(int, int)
	 */
	@Override
	public void notifyUpdateScore(int playerNumber, int score)
	{
		final Label textToRefresh = (playerNumber == 1 ? this.playerOneScore : this.playerTwoScore);
		textToRefresh.setText(String.format(PLAYER_TEXT, playerNumber, score));
	}

	/**
	 * @see fr.polytech.reversi.view.IView#notifyUpdateMoves(int, int)
	 */
	@Override
	public void notifyUpdateMoves(int playerNumber, int moves)
	{
		final Label textToRefresh = (playerNumber == 1 ? this.playerOneMoves : this.playerTwoMoves);
		textToRefresh.setText(String.format(PLAYER_TEXT, playerNumber, moves));
	}

	/**
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.version.setText(VERSION_TEXT);
		this.playerOneScore.setText(String.format(PLAYER_TEXT, 1, 0));
		this.playerTwoScore.setText(String.format(PLAYER_TEXT, 2, 0));
		this.playerOneMoves.setText(String.format(PLAYER_TEXT, 1, 0));
		this.playerTwoMoves.setText(String.format(PLAYER_TEXT, 2, 0));
		this.message.setText("Aucun message");
	}

	/**
	 * @see fr.polytech.reversi.view.IView#notifyMessage(java.lang.String)
	 */
	@Override
	public void notifyMessage(String message)
	{
		this.message.setText(message);
	}

	/**
	 * @see fr.polytech.reversi.view.IView#notifyCurrentPlayer(int)
	 */
	@Override
	public void notifyCurrentPlayer(int representation)
	{
		if (representation == 1)
		{
			this.playerOneHBox.setOpacity(1.0);
			this.playerTwoHBox.setOpacity(0.3);
		}
		else
		{
			this.playerOneHBox.setOpacity(0.3);
			this.playerTwoHBox.setOpacity(1.0);
		}
	}
}