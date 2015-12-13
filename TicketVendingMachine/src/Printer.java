import org.apache.commons.lang3.*;

/**
 * Ein einzelner Drucker, welcher Tickets mit einer bestimmten Breite und Höhe drucken kann.
 */
public class Printer {

	// Breite und Länge der Tickets (in Spalten/Zeilen, ohne Rand)
	private final int ticketWidth;
	private final int ticketLength;
	
	// Anzahl Zeilen, welche für das aktuelle Ticket bereits bereits gedruckt wurden
	private int printedLines = 0;	// ZUSTAND

	
	/**
	 * Konstruktor
	 * @param width	Breite der Tickets
	 * @param length Länge der Tickets
	 */
	public Printer(int width, int length)		// KONSTUKTOR (Initialisierung)
	{
		this.ticketWidth = width;
		this.ticketLength = length;
	}

	
	/**
	 * @return Breite des Tickets
	 */
	public int getTicketWidth() {		// GETTER
		return ticketWidth;
	}


	/**
	 * @return Länge des Tickets
	 */
	public int getTicketLength() {		// GETTER
		return ticketLength;
	}

	
	/**
	 * @return Anzahl der bereis gedruckten Zeilen
	 */
	public int getPrintedLines() {			// GETTER
		return printedLines;
	}
	
	
	/**
	 * Beginnt eine neues Ticket
	 */
	public void beginTicket()			// METHODE
	{
		System.out.println("┌" +  StringUtils.repeat('─', ticketWidth) + "┐");		
		
		printedLines = 0;				// hat Einluss auf Zustand
	}

	
	/**
	 * Druckt eine Zeile
	 * @param text Inhalt der Zeile
	 */
	public void println(String text)	// METHODE
	{
		// reklamiere wenn Länge des Tickets bereits erreicht wurde
		if (printedLines >= ticketLength)
		{
			throw new IllegalStateException("ticket length already reached");
		}

		// reklamiere wenn zu druckende Zeile zu lang ist
		if (text.length() > ticketWidth)
		{
			throw new IllegalArgumentException("line length exceeds ticket width");
		}

		// Anfang
		System.out.println("│" +  StringUtils.rightPad(text, ticketWidth) + "│");		
	
		printedLines++;			// hat Einluss auf Zustand
	}	

	
	/**
	 * Druckt eine leere Zeile
	 */
	public void println()				// METHODE (überladen)
	{
		println("");
	}

	
	/**
	 * Beendet das Ticket
	 */
	public void endTicket()				// METHODE
	{
		// fülle verbleibende Länge des Tickets auf
		for (int i = printedLines; i < ticketLength; i++)
		{
			println();
		}
		// Ende
		System.out.println("└" +  StringUtils.repeat('─', ticketWidth) + "┘");		
	}
	
	
}
