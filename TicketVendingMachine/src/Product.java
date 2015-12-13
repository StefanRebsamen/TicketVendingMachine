
/**
 * Abstrakte Basisklasse für ein Produkt
 */
public abstract class Product {	

	// was angezeigt wird
	private final String display;
	
	// was es kostet
	private final float price;
	
	// auf welchem Drucker es gedruckt wird
	protected final Printer printer;
	
	/**
	 * Konstruktor
	 * @param display Anzeige
	 * @param price Preis
	 * @param printer Printer
	 */
	public Product(String display, float price, Printer printer)
	{
		// behalte die übergebenen Werte
		this.display = display;
		this.price = price;
		this.printer = printer;
	}

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @return the printer
	 */
	public Printer getPrinter() {
		return printer;
	}	
	
	/**
	 * Druckt das Ticket auf seinem Drucker
	 * @param print
	 */
	public abstract void print(); // da die Klasse noch keine konkretes Ticket abbildet,
								  // haben wir auch noch keine Ahnung wie dieses aussieht
								  // und können daher noch nicht implementieren.
}		
