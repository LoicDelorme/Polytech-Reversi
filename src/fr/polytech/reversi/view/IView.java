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
	 * Notify the view to update the board game.
	 * 
	 * @param boardGame
	 *            The board game.
	 */
	public void notifyUpdateBoardGame(BoardGame boardGame);

	/**
	 * Notify the view to update the score.
	 * 
	 * @param playerNumber
	 *            The player number.
	 * @param score
	 *            The score.
	 */
	public void notifyUpdateScore(int playerNumber, int score);

	/**
	 * Notify the view to update the moves.
	 * 
	 * @param playerNumber
	 *            The player number.
	 * @param moves
	 *            The moves.
	 */
	public void notifyUpdateMoves(int playerNumber, int moves);

	/**
	 * Notify the view with a message.
	 * 
	 * @param message
	 *            The message.
	 */
	public void notifyMessage(String message);

	/**
	 * Notify the view the current player.
	 * 
	 * @param representation
	 *            The player representation.
	 */
	public void notifyCurrentPlayer(int representation);
}