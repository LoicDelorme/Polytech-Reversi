package fr.polytech.reversi.model.boardgame;

import fr.polytech.reversi.model.game.Color;

/**
 * This enumeration represents the available cells' values (Empty, White Pawn, Black Pawn).
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public enum Cell
{
    /**
     * The cell is empty.
     */
	EMPTY,

	/**
	 * The cell is a white pawn.
	 */
	WHITE_PAWN,

	/**
	 * The cell is a black pawn.
	 */
	BLACK_PAWN;

	/**
	 * Get a cell representation using a color value.
	 * 
	 * @param color
	 *            The color value.
	 * @return The corresponding cell value.
	 */
	public static Cell getCellByColor(Color color)
	{
		if (Color.WHITE == color)
		{
			return WHITE_PAWN;
		}

		if (Color.BLACK == color)
		{
			return BLACK_PAWN;
		}

		return EMPTY;
	}
}