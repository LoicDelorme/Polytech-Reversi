package fr.polytech.reversi.model.boardgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.polytech.reversi.model.boardgame.exceptions.AlreadyMarkedCellBoardGameException;
import fr.polytech.reversi.model.boardgame.exceptions.BoardGameException;
import fr.polytech.reversi.model.boardgame.exceptions.InvalidMoveBoardGameException;
import fr.polytech.reversi.model.players.IPlayer;
import fr.polytech.reversi.view.IView;

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
	 * The x direction value to add.
	 */
	private static final int[] DX = { -1, 0, 1, -1, 1, -1, 0, 1 };

	/**
	 * The y direction value to add.
	 */
	private static final int[] DY = { -1, -1, -1, 0, 0, 1, 1, 1 };

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
	 * The reversi view.
	 */
	private final IView reversiView;

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
	 * @param reversiView
	 *            The reversi view.
	 */
	public BoardGame(int width, int height, IPlayer playerOne, IPlayer playerTwo, IView reversiView)
	{
		this.boardGame = new Cell[width][height];
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.moves = new HashMap<IPlayer, Integer>();
		this.reversiView = reversiView;

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				this.boardGame[x][y] = Cell.EMPTY;
			}
		}

		this.boardGame[3][3] = Cell.WHITE_PAWN;
		this.boardGame[4][4] = Cell.WHITE_PAWN;
		this.boardGame[3][4] = Cell.BLACK_PAWN;
		this.boardGame[4][3] = Cell.BLACK_PAWN;

		this.currentPlayer = playerOne;

		this.moves.put(playerOne, DEFAULT_MOVE_VALUE);
		this.moves.put(playerTwo, DEFAULT_MOVE_VALUE);

		this.reversiView.notifyUpdateBoardGame(this);
	}

	/**
	 * Mark a cell.
	 * 
	 * @param position
	 *            The position to mark.
	 * @throws BoardGameException
	 *             An error occurred while trying to mark the cell.
	 */
	public void markCell(Position position) throws BoardGameException
	{
		checkMoveIsLegal(position.getX(), position.getY());
		applyMove(position.getX(), position.getY());
		this.moves.put(this.currentPlayer, this.moves.get(this.currentPlayer) + 1);
		this.currentPlayer = (this.currentPlayer == this.playerOne ? this.playerTwo : this.playerOne);

		this.reversiView.notifyUpdateBoardGame(this);
		this.reversiView.notifyUpdateScore(1, getNbCellsByColor(this.playerOne.getPlayerColor()));
		this.reversiView.notifyUpdateScore(2, getNbCellsByColor(this.playerTwo.getPlayerColor()));
		this.reversiView.notifyUpdateMoves(1, this.moves.get(this.playerOne));
		this.reversiView.notifyUpdateMoves(2, this.moves.get(this.playerTwo));
	}

	/**
	 * Check if the move is legal.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @throws BoardGameException
	 *             If an error occurred.
	 */
	public void checkMoveIsLegal(int x, int y) throws BoardGameException
	{
		if (!isInBounds(x, y))
		{
			throw new InvalidMoveBoardGameException(x, y);
		}

		if (!cellIsEmpty(x, y))
		{
			throw new AlreadyMarkedCellBoardGameException(x, y);
		}

		if (!moveCanBePlayed(x, y))
		{
			throw new InvalidMoveBoardGameException(x, y);
		}
	}

	/**
	 * Check if the position is not out the board game.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @return True or False.
	 */
	private boolean isInBounds(int x, int y)
	{
		return ((x >= 0) && (x < this.boardGame.length) && (y >= 0) && (y < this.boardGame[0].length));
	}

	/**
	 * Check if the cell is empty.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @return True or False.
	 */
	private boolean cellIsEmpty(int x, int y)
	{
		return this.boardGame[x][y] == Cell.EMPTY;
	}

	/**
	 * Check if the move can be played.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @return True or False.
	 */
	private boolean moveCanBePlayed(int x, int y)
	{
		final Cell playerPawn = Cell.getCellRepresentationByColor(this.currentPlayer.getPlayerColor());
		boolean sawOther;
		int xTemp;
		int yTemp;
		Cell currentPawn;

		for (int ii = 0; ii < DX.length; ii++)
		{
			sawOther = false;
			xTemp = x;
			yTemp = y;

			for (int dd = 0; dd < this.boardGame.length; dd++)
			{
				xTemp += DX[ii];
				yTemp += DY[ii];

				if (!isInBounds(xTemp, yTemp))
				{
					break;
				}

				currentPawn = this.boardGame[xTemp][yTemp];

				if (currentPawn == Cell.EMPTY)
				{
					break;
				}

				if (currentPawn != playerPawn)
				{
					sawOther = true;
				}
				else
				{
					if (sawOther)
					{
						return true;
					}

					break;
				}
			}
		}

		return false;
	}

	/**
	 * Apply a move.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 */
	private void applyMove(int x, int y)
	{
		final Cell playerPawn = Cell.getCellRepresentationByColor(this.currentPlayer.getPlayerColor());
		final List<Position> positionsToFlip = new ArrayList<Position>();
		int xTemp;
		int yTemp;
		Cell currentPawn;

		this.boardGame[x][y] = playerPawn;

		for (int ii = 0; ii < DX.length; ii++)
		{
			xTemp = x;
			yTemp = y;

			for (int dd = 0; dd < this.boardGame.length; dd++)
			{
				xTemp += DX[ii];
				yTemp += DY[ii];

				if (!isInBounds(xTemp, yTemp))
				{
					break;
				}

				currentPawn = this.boardGame[xTemp][yTemp];

				if (currentPawn == Cell.EMPTY)
				{
					break;
				}

				if (currentPawn != playerPawn)
				{
					positionsToFlip.add(new Position(xTemp, yTemp));
				}
				else
				{
					for (Position currentPosition : positionsToFlip)
					{
						this.boardGame[currentPosition.getX()][currentPosition.getY()] = playerPawn;
					}

					break;
				}
			}

			positionsToFlip.clear();
		}
	}

	/**
	 * Get the number of cells for a specific color.
	 * 
	 * @param color
	 *            The specific color.
	 * @return The number of cells corresponding to the color.
	 */
	private int getNbCellsByColor(Color color)
	{
		final Cell cellColor = Cell.getCellRepresentationByColor(color);
		int nbCells = 0;

		for (int x = 0; x < this.boardGame.length; x++)
		{
			for (int y = 0; y < this.boardGame[0].length; y++)
			{
				if (this.boardGame[x][y] == cellColor)
				{
					nbCells++;
				}
			}
		}

		return nbCells;
	}

	/**
	 * Get the board game.
	 * 
	 * @return The board game.
	 */
	public Cell[][] getBoardGame()
	{
		return this.boardGame.clone();
	}
}