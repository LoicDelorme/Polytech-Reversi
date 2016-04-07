package fr.polytech.reversi.model.players;

import fr.polytech.reversi.model.boardgame.BoardGame;
import fr.polytech.reversi.model.boardgame.Color;
import fr.polytech.reversi.model.boardgame.Position;

/**
 * This class represents a human player.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class HumanPlayer implements IPlayer
{
	/**
	 * The player color.
	 */
	private final Color color;

	/**
	 * Create a human player.
	 * 
	 * @param color
	 *            The human color.
	 */
	public HumanPlayer(Color color)
	{
		this.color = color;
	}

	/**
	 * @see fr.polytech.reversi.model.players.IPlayer#getNextChoice(fr.polytech.reversi.model.boardgame.BoardGame)
	 */
	@Override
	public Position getNextChoice(BoardGame boardGame)
	{
		return null;
	}

	/**
	 * @see fr.polytech.reversi.model.players.IPlayer#getPlayerType()
	 */
	@Override
	public PlayerType getPlayerType()
	{
		return PlayerType.HUMAN;
	}

	/**
	 * @see fr.polytech.reversi.model.players.IPlayer#getPlayerColor()
	 */
	@Override
	public Color getPlayerColor()
	{
		return this.color;
	}
}