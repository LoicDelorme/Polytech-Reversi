package fr.polytech.reversi.model.boardgame;

import java.util.HashMap;
import java.util.Map;

import fr.polytech.reversi.model.players.IPlayer;

/**
 * This class represents a board game.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class BoardGame
{
	/**
	 * The default move value.
	 */
	public static final int DEFAULT_MOVE_VALUE = 0;

	/**
	 * The board game.
	 */
	private final Cell[][] boardGame;

	/**
	 * The player one.
	 */
	private final IPlayer playerOne;

	/**
	 * The player two.
	 */
	private final IPlayer playerTwo;

	/**
	 * The current player.
	 */
	private IPlayer currentPlayer;

	/**
	 * The carried out moves.
	 */
	private final Map<IPlayer, Integer> moves;

	/**
	 * Create a board game.
	 * 
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 * @param playerOne
	 *            The player one.
	 * @param playerTwo
	 *            The player two.
	 */
	public BoardGame(int width, int height, IPlayer playerOne, IPlayer playerTwo)
	{
		this.boardGame = new Cell[width][height];
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.moves = new HashMap<IPlayer, Integer>();

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				this.boardGame[x][y] = Cell.EMPTY;
			}
		}

		this.currentPlayer = playerOne;

		this.moves.put(playerOne, DEFAULT_MOVE_VALUE);
		this.moves.put(playerTwo, DEFAULT_MOVE_VALUE);
	}
}