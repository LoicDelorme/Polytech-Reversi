package fr.polytech.reversi.model.boardgame.exceptions;

/**
 * This class represents an already marked cell board game exception.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class AlreadyMarkedCellBoardGameException extends BoardGameException
{
	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = -1273792229578331599L;

	/**
	 * Create an already marked cell board game exception.
	 */
	public AlreadyMarkedCellBoardGameException()
	{
		super();
	}

	/**
	 * Create an already marked cell board game exception.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 */
	public AlreadyMarkedCellBoardGameException(int x, int y)
	{
		super(String.format("Cell is already marked (%d, %d)", x, y));
	}
}