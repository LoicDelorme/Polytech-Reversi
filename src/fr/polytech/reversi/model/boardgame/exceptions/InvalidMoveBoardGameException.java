package fr.polytech.reversi.model.boardgame.exceptions;

/**
 * This class represents an invalid move board game exception board game exception.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class InvalidMoveBoardGameException extends BoardGameException
{
	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = -5839059207301654097L;

	/**
	 * Create an invalid move board game exception.
	 */
	public InvalidMoveBoardGameException()
	{
		super();
	}

	/**
	 * Create an invalid move board game exception.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 */
	public InvalidMoveBoardGameException(int x, int y)
	{
		super(String.format("Invalid move (%d, %d)", x, y));
	}
}