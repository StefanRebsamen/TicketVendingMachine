

/**
 * Klasse, welche eine Mehrfahrtenkarte für eine Anzahl Zonen implementiert
 */
public class Multiple extends Product {

	// Anzahl Zonen
	private final int zones;
	
	/**
	 * Konstruktor
	 * @param zones
	 * @param price
	 * @param printer
	 */
	public Multiple(int zones, float price, Printer printer) {
		// rufe Konstuktor der Basislasse auf
		super("Mehrfahrtenkarte " + zones + " Zonen", price, printer);
		
		this.zones = zones;
	}

	@Override
	public void print() {
		printer.beginTicket();
		printer.println("Mehrfahrtenkarte");
		printer.println(zones + " Zonen");
		printer.println();
		for (int i = 1; i <= 6; i++)
		{
			printer.println();
			printer.println(i + " ____________________");			
		}				
		printer.endTicket();
	}	
	
}
