package fr.polytech.reversi.model.boardgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.polytech.reversi.model.boardgame.exceptions.AlreadyMarkedCellBoardGameException;
import fr.polytech.reversi.model.boardgame.exceptions.BoardGameException;
import fr.polytech.reversi.model.boardgame.exceptions.InvalidMoveBoardGameException;
import fr.polytech.reversi.model.players.IPlayer;
import fr.polytech.reversi.model.players.PlayerType;
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
	 * The evaluation board.
	 */
	private static final int[][] EVALUATION_BOARD = new int[][] { { 50, -20, 5, 5, 5, 5, -20, 50 }, { -20, -20, 5, 5, 5, 5, -20, -20 }, { 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5 }, { -20, -20, 5, 5, 5, 5, -20, -20 }, { 50, -20, 5, 5, 5, 5, -20, 50 } };

	/**
	 * The board game.
	 */
	private Cell[][] boardGame;

	/**
	 * The number of cells remaining.
	 */
	private int nbCellsRemaining;

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
		this.nbCellsRemaining = width * height - 4;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.moves = new HashMap<IPlayer, Integer>();
		this.reversiView = reversiView;
	}

	/**
	 * Create a board game.
	 * 
	 * @param boardGame
	 *            An another board game.
	 */
	public BoardGame(BoardGame boardGame)
	{
		this.boardGame = boardGame.getBoardGame();
		this.nbCellsRemaining = boardGame.getNbCellsRemaining();
		this.playerOne = boardGame.getPlayerOne();
		this.playerTwo = boardGame.getPlayerTwo();
		this.moves = null;
		this.reversiView = null;
	}

	/**
	 * Init the board game.
	 */
	public void init()
	{
		for (int x = 0; x < this.boardGame.length; x++)
		{
			for (int y = 0; y < this.boardGame[0].length; y++)
			{
				this.boardGame[x][y] = Cell.EMPTY;
			}
		}

		this.boardGame[3][3] = Cell.WHITE_PAWN;
		this.boardGame[3][4] = Cell.BLACK_PAWN;
		this.boardGame[4][3] = Cell.BLACK_PAWN;
		this.boardGame[4][4] = Cell.WHITE_PAWN;

		this.moves.put(this.playerOne, DEFAULT_MOVE_VALUE);
		this.moves.put(this.playerTwo, DEFAULT_MOVE_VALUE);

		updateCurrentPlayer();
		this.reversiView.notifyUpdateBoardGame(this);
	}

	/**
	 * Update the current player.
	 */
	private void updateCurrentPlayer()
	{
		if (this.currentPlayer != null)
		{
			this.currentPlayer = (this.currentPlayer == this.playerOne ? this.playerTwo : this.playerOne);
		}
		else
		{
			this.currentPlayer = this.playerOne;
		}

		this.reversiView.notifyCurrentPlayer(this.currentPlayer == this.playerOne ? 1 : 2);
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
		checkMoveIsLegal(position.getX(), position.getY(), this.currentPlayer.getCellRepresentation());
		applyMove(position.getX(), position.getY(), this.currentPlayer.getCellRepresentation());
		this.nbCellsRemaining--;
		this.moves.put(this.currentPlayer, this.moves.get(this.currentPlayer) + 1);

		updateCurrentPlayer();
		this.reversiView.notifyUpdateBoardGame(this);
		this.reversiView.notifyUpdateScore(1, getNbCellsByPawn(this.playerOne.getCellRepresentation()));
		this.reversiView.notifyUpdateScore(2, getNbCellsByPawn(this.playerTwo.getCellRepresentation()));
		this.reversiView.notifyUpdateMoves(1, this.moves.get(this.playerOne));
		this.reversiView.notifyUpdateMoves(2, this.moves.get(this.playerTwo));

		final int playerOneScore = getNbCellsByPawn(this.playerOne.getCellRepresentation());
		final int playerTwoScore = getNbCellsByPawn(this.playerTwo.getCellRepresentation());
		if (areAllCellsMarked() || (playerOneScore == 0) || (playerTwoScore == 0))
		{
			String computedMessage = null;
			if (playerOneScore == playerTwoScore)
			{
				computedMessage = String.format("Egalité entre les joueurs avec %d pions chacun", playerOneScore);
			}
			else
			{
				computedMessage = String.format("Joueur %d gagne avec %d pions", (playerOneScore > playerTwoScore ? 1 : 2), (playerOneScore > playerTwoScore ? playerOneScore : playerTwoScore));
			}

			this.reversiView.notifyMessage(computedMessage);
			return;
		}

		if (!playerCanPlay(this.currentPlayer.getCellRepresentation()))
		{
			this.reversiView.notifyMessage(String.format("Joueur %d ne peut pas jouer...", (this.currentPlayer == this.playerOne ? 1 : 2)));
			updateCurrentPlayer();
		}

		if (this.currentPlayer.getPlayerType() == PlayerType.COMPUTER)
		{
			markCell(this.currentPlayer.getNextChoice(new BoardGame(this)));
		}
	}

	/**
	 * Check if the move is legal.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @param playerPawn
	 *            The player pawn.
	 * @throws BoardGameException
	 *             If an error occurred.
	 */
	public void checkMoveIsLegal(int x, int y, Cell playerPawn) throws BoardGameException
	{
		if (!isInBounds(x, y))
		{
			throw new InvalidMoveBoardGameException(x, y);
		}

		if (!cellIsEmpty(x, y))
		{
			throw new AlreadyMarkedCellBoardGameException(x, y);
		}

		if (!moveCanBePlayed(x, y, playerPawn))
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
	 * @param playerPawn
	 *            The player pawn.
	 * @return True or False.
	 */
	private boolean moveCanBePlayed(int x, int y, Cell playerPawn)
	{
		Cell currentPawn = null;

		boolean sawOther;
		int xTemp;
		int yTemp;

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
	 * @param playerPawn
	 *            The player pawn.
	 */
	private void applyMove(int x, int y, Cell playerPawn)
	{
		Cell currentPawn;

		final List<Position> positionsToFlip = new ArrayList<Position>();
		int xTemp;
		int yTemp;

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
	 * Check if all cells are marked.
	 * 
	 * @return True or False.
	 */
	private boolean areAllCellsMarked()
	{
		return this.nbCellsRemaining == 0;
	}

	/**
	 * Check that the player can play.
	 * 
	 * @param playerPawn
	 *            The player pawn.
	 * 
	 * @return True or False.
	 */
	public boolean playerCanPlay(Cell playerPawn)
	{
		final List<Position> availablePositions = new ArrayList<Position>();
		for (int x = 0; x < this.boardGame.length; x++)
		{
			for (int y = 0; y < this.boardGame[0].length; y++)
			{
				try
				{
					checkMoveIsLegal(x, y, playerPawn);
					availablePositions.add(new Position(x, y));
				}
				catch (BoardGameException e)
				{
					// Nothing.
				}
			}
		}

		return !availablePositions.isEmpty();
	}

	/**
	 * Get the number of cells for a specific pawn.
	 * 
	 * @param pawn
	 *            The specific pawn.
	 * @return The number of cells corresponding to the spawn.
	 */
	public int getNbCellsByPawn(Cell pawn)
	{
		int nbCells = 0;
		for (int x = 0; x < this.boardGame.length; x++)
		{
			for (int y = 0; y < this.boardGame[0].length; y++)
			{
				if (this.boardGame[x][y] == pawn)
				{
					nbCells++;
				}
			}
		}

		return nbCells;
	}

	/**
	 * Get the number of points for a specific pawn.
	 * 
	 * @param pawn
	 *            The specific pawn.
	 * @return The number of points corresponding to the spawn.
	 */
	public int getNbPointsByPawn(Cell pawn)
	{
		int nbPoints = 0;
		for (int x = 0; x < this.boardGame.length; x++)
		{
			for (int y = 0; y < this.boardGame[0].length; y++)
			{
				if (this.boardGame[x][y] == pawn)
				{
					nbPoints += EVALUATION_BOARD[x][y];
				}
			}
		}

		return nbPoints;
	}

	/**
	 * Get the board game.
	 * 
	 * @return The board game.
	 */
	public Cell[][] getBoardGame()
	{
		final Cell[][] clonedBoardGame = new Cell[this.boardGame.length][this.boardGame[0].length];
		for (int offset = 0; offset < this.boardGame.length; offset++)
		{
			clonedBoardGame[offset] = this.boardGame[offset].clone();
		}

		return clonedBoardGame;
	}

	/**
	 * Get the number of cells remaining.
	 * 
	 * @return The number of cells remaining.
	 */
	private int getNbCellsRemaining()
	{
		return this.nbCellsRemaining;
	}

	/**
	 * Get the current player.
	 * 
	 * @return The current player.
	 */
	public IPlayer getCurrentPlayer()
	{
		return this.currentPlayer;
	}

	/**
	 * Get the player one.
	 * 
	 * @return The player one.
	 */
	public IPlayer getPlayerOne()
	{
		return this.playerOne;
	}

	/**
	 * Get the player two.
	 * 
	 * @return The player two.
	 */
	public IPlayer getPlayerTwo()
	{
		return this.playerTwo;
	}

	/////////////////////////////////////////// USED FOR AI ///////////////////////////////////////////
	/**
	 * Check if the game is over.
	 * 
	 * @return True of False.
	 */
	public boolean gameOver()
	{
		return areAllCellsMarked() || !playerCanPlay(this.playerOne.getCellRepresentation()) || !playerCanPlay(this.playerTwo.getCellRepresentation());
	}

	/**
	 * Mark a cell for AI.
	 * 
	 * @param position
	 *            The position to mark.
	 * @param playerPawn
	 *            The player pawn.
	 * @throws BoardGameException
	 *             If an error occurred.
	 */
	public void markCellAI(Position position, Cell playerPawn) throws BoardGameException
	{
		checkMoveIsLegal(position.getX(), position.getY(), playerPawn);
		applyMove(position.getX(), position.getY(), playerPawn);
		this.nbCellsRemaining--;
	}

	/**
	 * Reset the board game.
	 * 
	 * @param boardGame
	 *            The board game.
	 */
	public void resetBoardGame(Cell[][] boardGame)
	{
		this.boardGame = boardGame;
	}
	/////////////////////////////////////////// END ///////////////////////////////////////////
}