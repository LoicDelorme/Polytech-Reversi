package fr.polytech.reversi.model.game;

import fr.polytech.reversi.model.boardgame.BoardGame;
import fr.polytech.reversi.model.players.IPlayer;

/**
 * This class represents a Reversi game.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class ReversiGame
{
	/**
	 * The Reversi matrix size.
	 */
	public static final int REVERSI_MATRIX_SIZE = 8;

	/**
	 * The board game.
	 */
	private final BoardGame boardGame;

	/**
	 * If the game is finished.
	 */
	private boolean isGameFinished;

	/**
	 * Create a Reversi game.
	 * 
	 * @param playerOne
	 *            The player one.
	 * @param playerTwo
	 *            The player two.
	 */
	public ReversiGame(IPlayer playerOne, IPlayer playerTwo)
	{
		this.boardGame = new BoardGame(REVERSI_MATRIX_SIZE, REVERSI_MATRIX_SIZE, playerOne, playerTwo);
		this.isGameFinished = false;
	}

	/**
	 * Launch the game.
	 */
	public void launchGame()
	{
		while (!this.isGameFinished)
		{
		}
	}
}