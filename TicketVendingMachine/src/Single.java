import java.util.Date;
import org.apache.commons.lang3.time.*;

/**
 * Klasse, welche ein Einzelticket für eine Anzahl Zonen implementiert
 */
public class Single extends Product {

	// Anzahl Zonen
	private final int zones;
	
	/**
	 * Konstruktor
	 * @param zones
	 * @param price
	 * @param printer
	 */
	public Single(int zones, float price, Printer printer) {
		// rufe Konstuktor der Basislasse auf
		super("Einzelticket " + zones + " Zonen", price, printer);
		
		this.zones = zones;
	}

	@Override	// überschreibt die durch die Basisklasse definierte Methode
	public void print() {
		printer.beginTicket();
		printer.println("Einzelticket");
		printer.println(zones + " Zonen");
		printer.println();
		printer.println(Controller.getInstance().getLocation());
		printer.println("Gültig ab " + DateFormatUtils.format(new Date(), "dd.MM.yyyy HH:mm"));
		printer.endTicket();
	}	
	
}
