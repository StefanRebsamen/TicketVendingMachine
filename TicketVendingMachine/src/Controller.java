import java.util.*;

/**
 * Ablaufsteuerung (Singleton)
 */
public class Controller {

	// enthält die einzige Instanz der Klasse
	private static Controller instance;

	/**
	 * Gibt die einzige Instanz zurück (Singleton)
	 * @return
	 */
	public static Controller getInstance()
	{
		// erstelle Instanz, falls es sie noch nicht gibt
		if (instance == null)
		{
			instance = new Controller();
		}
		// gib Instanz zurück
		return instance;
	}
	
	// Drucker für normale Tickets
	private final Printer printerNormal = new Printer(40, 5);
	
	// Drucker für entwertbare Tickets
	private final Printer printerPunchable = new Printer(30, 20);

	// Enthält alle Produkte, welche an diesem Automaten gekauft werden können
	private final List<Product> products = new ArrayList<Product>();

	// Aktueller Standort
	private String Location;
	
	/**
	 * @return Standort
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * @param Setze den aktuellen Standort (durch GPS)
	 */
	public void setLocation(String location) {
		Location = location;
	}

	/**
	 * Konstruktor
	 */
	private Controller()				// private, (Singleton)
	{
		// definiere Produkte, welche verfügbar sind 	
		products.add(new Single(2, 2.80f, printerNormal));
		products.add(new Single(3, 3.50f, printerNormal));
		products.add(new Multiple(2, 15.20f, printerPunchable));
		products.add(new Multiple(3, 18.30f, printerPunchable));	
		products.add(new Daily(9.9f, printerPunchable));			
	}
	
	/**
	 * Führt die Ablaufkontrolle aus
	 */
	public void run()
	{		
		// bis zum St.Nimmerleinstag
		while (true)
		{
			//
			Scanner input = new Scanner(System.in);
			
			// Benutzer muss ein Produkt auswählen
			System.out.println("===================================================================================");
			System.out.println("Bitte wähle ein Produkt aus:");
			for (Product product : products)
			{
				System.out.println(String.format("%2d  %-30s %.2f", products.indexOf(product), product.getDisplay(), product.getPrice()));
			}
			System.out.print("> ");
			int choice = input.nextInt();
			if (choice < 0 || choice >= products.size())
			{
				continue;
			}			
			Product selectedProduct = products.get(choice);
									
			// TODO Benutzer muss Zahlungsmittel wählen
			
			// TODO Zahlung wird durchgeführt
			
			// Ticket wird ausgedruckt
			selectedProduct.print();
			
			// close input scanner
			input.close();
		}		
	}
	
}
