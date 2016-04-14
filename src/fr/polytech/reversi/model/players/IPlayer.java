package fr.polytech.reversi.model.players;

import fr.polytech.reversi.model.boardgame.BoardGame;
import fr.polytech.reversi.model.boardgame.Cell;
import fr.polytech.reversi.model.boardgame.Position;

/**
 * This interface represents a player.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public interface IPlayer
{
	/**
	 * Get the next choice.
	 * 
	 * @param boardGame
	 *            The board game.
	 * 
	 * @return The next choice.
	 */
	public Position getNextChoice(BoardGame boardGame);

	/**
	 * Get the player type.
	 * 
	 * @return The player type.
	 */
	public PlayerType getPlayerType();

	/**
	 * Get the cell representation.
	 * 
	 * @return The cell representation.
	 */
	public Cell getCellRepresentation();
}