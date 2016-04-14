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
	EMPTY("/fr/polytech/reversi/view/resources/images/invisible_pawn.png"),

	/**
	 * The cell is a white pawn.
	 */
	WHITE_PAWN("/fr/polytech/reversi/view/resources/images/white_pawn.png"),

	/**
	 * The cell is a black pawn.
	 */
	BLACK_PAWN("/fr/polytech/reversi/view/resources/images/black_pawn.png");

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
}