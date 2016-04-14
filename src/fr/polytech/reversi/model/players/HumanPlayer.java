package fr.polytech.reversi.model.players;

import fr.polytech.reversi.model.boardgame.BoardGame;
import fr.polytech.reversi.model.boardgame.Cell;
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
	 * The cell representation.
	 */
	private final Cell cellRepresentation;

	/**
	 * Create a human player.
	 * 
	 * @param cellRepresentation
	 *            The cell representation.
	 */
	public HumanPlayer(Cell cellRepresentation)
	{
		this.cellRepresentation = cellRepresentation;
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
	 * @see fr.polytech.reversi.model.players.IPlayer#getCellRepresentation()
	 */
	@Override
	public Cell getCellRepresentation()
	{
		return this.cellRepresentation;
	}
}