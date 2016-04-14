package fr.polytech.reversi.model.players;

import fr.polytech.reversi.model.boardgame.BoardGame;
import fr.polytech.reversi.model.boardgame.Cell;
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
	 * The cell representation.
	 */
	private final Cell cellRepresentation;

	/**
	 * Create a computer player.
	 * 
	 * @param cellRepresentation
	 *            The cell representation.
	 */
	public ComputerPlayer(Cell cellRepresentation)
	{
		this.cellRepresentation = cellRepresentation;
	}

	/**
	 * @see fr.polytech.reversi.model.players.IPlayer#getNextChoice(fr.polytech.reversi.model.boardgame.BoardGame)
	 */
	@Override
	public Position getNextChoice(BoardGame boardGame)
	{
		// TODO Calculate next choice.
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
	 * @see fr.polytech.reversi.model.players.IPlayer#getCellRepresentation()
	 */
	@Override
	public Cell getCellRepresentation()
	{
		return this.cellRepresentation;
	}
}