package fr.polytech.reversi.view;

import fr.polytech.reversi.model.boardgame.BoardGame;

/**
 * This interface represents a reversi view.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public interface IView
{
	/**
	 * Notify the view to update the board game representation.
	 * 
	 * @param boardGame
	 *            The board game.
	 */
	public void notifyUpdateBoardGameRepresentation(BoardGame boardGame);

	/**
	 * Notify the view to reset the score board.
	 */
	public void notifyResetScoreBoard();
}