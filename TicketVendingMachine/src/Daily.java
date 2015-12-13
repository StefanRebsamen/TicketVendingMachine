
/**
 * Klasse, welche eine Tageskarte implementiert
 */
public class Daily extends Product {

	/**
	 * Konstruktor
	 * @param price
	 * @param printer
	 */
	public Daily(float price, Printer printer) {
		// rufe Konstuktor der Basislasse auf
		super("Tageskarte", price, printer);
	}

	@Override	// überschreibt die durch die Basisklasse definierte Methode
	public void print() {
		printer.beginTicket();
		printer.println("Tageskarte");
		printer.println("ganzes Netz");
		printer.println();
		printer.println();
		printer.println("1 ____________________");			
		printer.println();
		printer.println();
		printer.println();
		printer.println();
		printer.println();
		printer.println();
		printer.println();
		printer.println("Tageskarte ist vor der");			
		printer.println("ersten Fahrt zu");			
		printer.println("entwerten");			
		printer.endTicket();
	}	
	
	
}
