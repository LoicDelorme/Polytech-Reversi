package fr.polytech.reversi.model.players;

import fr.polytech.reversi.model.boardgame.BoardGame;
import fr.polytech.reversi.model.boardgame.Color;
import fr.polytech.reversi.model.boardgame.Position;

/**
 * This class represents a computer player.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class ComputerPlayer implements IPlayer
{
	/**
	 * The player color.
	 */
	private final Color color;

	/**
	 * Create a computer player.
	 * 
	 * @param color
	 *            The computer color.
	 */
	public ComputerPlayer(Color color)
	{
		this.color = color;
	}

	/**
	 * @see fr.polytech.reversi.model.players.IPlayer#getNextChoice(fr.polytech.reversi.model.boardgame.BoardGame)
	 */
	@Override
	public Position getNextChoice(BoardGame boardGame)
	{
		// TODO Auto-generated method stub
		return null;
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
	 * @see fr.polytech.reversi.model.players.IPlayer#getPlayerColor()
	 */
	@Override
	public Color getPlayerColor()
	{
		return this.color;
	}
}