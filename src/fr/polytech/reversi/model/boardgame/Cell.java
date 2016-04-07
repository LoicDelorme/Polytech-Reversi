package fr.polytech.reversi.model.boardgame;

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
	EMPTY(""),

	/**
	 * The cell is a white pawn.
	 */
	WHITE_PAWN(""),

	/**
	 * The cell is a black pawn.
	 */
	BLACK_PAWN("");

	/**
	 * The image path.
	 */
	private final String imagePath;

	/**
	 * Private constructor.
	 * 
	 * @param imagePath
	 *            The image path.
	 */
	private Cell(String imagePath)
	{
		this.imagePath = imagePath;
	}

	/**
	 * Get the image path.
	 * 
	 * @return The image path.
	 */
	public String getImagePath()
	{
		return this.imagePath;
	}

	/**
	 * Get a cell representation using a color value.
	 * 
	 * @param color
	 *            The color value.
	 * @return The corresponding cell value.
	 */
	public static Cell getCellRepresentationByColor(Color color)
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