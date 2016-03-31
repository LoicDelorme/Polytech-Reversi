package fr.polytech.reversi.model.players;

import fr.polytech.reversi.model.boardgame.BoardGame;
import fr.polytech.reversi.model.boardgame.Position;
import fr.polytech.reversi.model.game.Color;

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
	 * Get the player color.
	 * 
	 * @return The player color.
	 */
	public Color getPlayerColor();
}