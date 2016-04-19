package fr.polytech.reversi.model.players;

import fr.polytech.reversi.model.boardgame.BoardGame;
import fr.polytech.reversi.model.boardgame.Cell;
import fr.polytech.reversi.model.boardgame.Position;
import fr.polytech.reversi.model.boardgame.exceptions.BoardGameException;

/**
 * This class represents a computer player.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class ComputerPlayer implements IPlayer
{
	/**
	 * The abort time threshold.
	 */
	private static final int ABORT_TIME_THRESHOLD = 3000; // 3 seconds

	/**
	 * The board game size.
	 */
	private static final int SIZE = 10;

	/**
	 * The own representation.
	 */
	private final Cell ownRepresentation;

	/**
	 * The opponent representation.
	 */
	private final Cell opponentRepresentation;

	/**
	 * The max depth.
	 */
	private final int maxDepth;

	/**
	 * The start time.
	 */
	private long startTime;

	/**
	 * The best move.
	 */
	private Position bestMove;

	/**
	 * Create a computer player.
	 * 
	 * @param cellRepresentation
	 *            The cell representation.
	 * @param maxDepth
	 *            The max depth.
	 */
	public ComputerPlayer(Cell cellRepresentation, int maxDepth)
	{
		this.ownRepresentation = cellRepresentation;
		this.maxDepth = maxDepth;
		this.opponentRepresentation = (this.ownRepresentation == Cell.BLACK_PAWN ? Cell.WHITE_PAWN : Cell.BLACK_PAWN);
	}

	/**
	 * @see fr.polytech.reversi.model.players.IPlayer#getNextChoice(fr.polytech.reversi.model.boardgame.BoardGame)
	 */
	@Override
	public Position getNextChoice(BoardGame boardGame)
	{
		this.startTime = System.currentTimeMillis();
		maxValue(boardGame, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);

		return this.bestMove;
	}

	/**
	 * Calculate the max value.
	 * 
	 * @param boardGame
	 *            The board game.
	 * @param alpha
	 *            The alpha value.
	 * @param beta
	 *            The beta value.
	 * @param depth
	 *            The depth.
	 * @return The max value.
	 */
	private double maxValue(BoardGame boardGame, double alpha, double beta, int depth)
	{
		if (depth >= this.maxDepth || !isTimerOk() || boardGame.gameOver())
		{
			return evaluate(boardGame);
		}

		BoardGame boardGameAfterNextMove = new BoardGame(boardGame);

		if (!boardGame.playerCanPlay(this.ownRepresentation))
		{
			return minValue(boardGame, alpha, beta, depth + 1);
		}

		Position position = null;
		Position localBestMove = null;
		double bestResult = Double.NEGATIVE_INFINITY;

		for (int x = 0; x < SIZE; x++)
		{
			for (int y = 0; y < SIZE; y++)
			{
				position = new Position(x, y);

				try
				{
					boardGameAfterNextMove.markCellAI(position, this.ownRepresentation);
					alpha = minValue(boardGameAfterNextMove, alpha, beta, depth + 1);

					if (alpha > bestResult)
					{
						localBestMove = position;
						bestResult = alpha;
					}
				}
				catch (BoardGameException e)
				{
					// Do nothing.
				}

				if (alpha >= beta)
				{
					break;
				}

				boardGameAfterNextMove.resetBoardGame(boardGame.getBoardGame());
			}
		}

		this.bestMove = localBestMove;

		return alpha;
	}

	/**
	 * Calculate the min value.
	 * 
	 * @param boardGame
	 *            The board game.
	 * @param alpha
	 *            The alpha value.
	 * @param beta
	 *            The beta value.
	 * @param depth
	 *            The depth.
	 * @return The min value.
	 */
	private double minValue(BoardGame boardGame, double alpha, double beta, int depth)
	{
		if (depth >= this.maxDepth || !isTimerOk() || boardGame.gameOver())
		{
			return evaluate(boardGame);
		}

		BoardGame boardGameAfterNextMove = new BoardGame(boardGame);

		if (!boardGame.playerCanPlay(this.opponentRepresentation))
		{
			return maxValue(boardGame, alpha, beta, depth + 1);
		}

		Position position = null;

		for (int x = 0; x < SIZE; x++)
		{
			for (int y = 0; y < SIZE; y++)
			{
				position = new Position(x, y);

				try
				{
					boardGameAfterNextMove.markCellAI(position, this.opponentRepresentation);
					beta = Math.min(beta, maxValue(boardGameAfterNextMove, alpha, beta, depth + 1));
				}
				catch (BoardGameException e)
				{
					// Do nothing.
				}

				if (beta <= alpha)
				{
					break;
				}

				boardGameAfterNextMove.resetBoardGame(boardGame.getBoardGame());
			}
		}

		return beta;
	}

	/**
	 * Check if the timer is OK.
	 * 
	 * @return True or False.
	 */
	private boolean isTimerOk()
	{
		return (System.currentTimeMillis() - this.startTime) < ABORT_TIME_THRESHOLD;
	}

	/**
	 * Evaluate the board game.
	 * 
	 * @param boardGame
	 *            The board game.
	 * @return The evaluation.
	 */
	private double evaluate(BoardGame boardGame)
	{
		return boardGame.getNbCellsByPawn(this.ownRepresentation) - boardGame.getNbCellsByPawn(this.opponentRepresentation);
	}

	/**
	 * @see fr.polytech.reversi.model.players.IPlayer#getPlayerType()
	 */
	@Override
	public PlayerType getPlayerType()
	{
		return PlayerType.COMPUTER;
	}

	/**
	 * @see fr.polytech.reversi.model.players.IPlayer#getCellRepresentation()
	 */
	@Override
	public Cell getCellRepresentation()
	{
		return this.ownRepresentation;
	}
}