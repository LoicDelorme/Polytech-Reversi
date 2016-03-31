package fr.polytech.reversi.model.boardgame.exceptions;

/**
 * This class represents a board game exception.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class BoardGameException extends Exception
{
	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = 8594464681349756468L;

	/**
	 * Create a board game exception.
	 */
	public BoardGameException()
	{
		super();
	}

	/**
	 * Create a board game exception.
	 * 
	 * @param message
	 *            The exception's message.
	 */
	public BoardGameException(String message)
	{
		super(message);
	}
}