package fr.polytech.reversi.model.boardgame;

/**
 * This class represents a position.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Position
{
	/**
	 * The x coordinate.
	 */
	private final int x;

	/**
	 * The y coordinate.
	 */
	private final int y;

	/**
	 * Create a position.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 */
	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the x coordinate.
	 * 
	 * @return The x coordinate.
	 */
	public int getX()
	{
		return this.x;
	}

	/**
	 * Get the y coordinate.
	 * 
	 * @return The y coordinate.
	 */
	public int getY()
	{
		return this.y;
	}
}