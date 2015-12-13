import java.util.*;

/**
 * Ablaufsteuerung (Singleton)
 */
public class Controller {

	// enth�lt die einzige Instanz der Klasse
	private static Controller instance;

	/**
	 * Gibt die einzige Instanz zur�ck (Singleton)
	 * @return
	 */
	public static Controller getInstance()
	{
		// erstelle Instanz, falls es sie noch nicht gibt
		if (instance == null)
		{
			instance = new Controller();
		}
		// gib Instanz zur�ck
		return instance;
	}
	
	// Drucker f�r normale Tickets
	private final Printer printerNormal = new Printer(40, 5);
	
	// Drucker f�r entwertbare Tickets
	private final Printer printerPunchable = new Printer(30, 20);

	// Enth�lt alle Produkte, welche an diesem Automaten gekauft werden k�nnen
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
		// definiere Produkte, welche verf�gbar sind 	
		products.add(new Single(2, 2.80f, printerNormal));
		products.add(new Single(3, 3.50f, printerNormal));
		products.add(new Multiple(2, 15.20f, printerPunchable));
		products.add(new Multiple(3, 18.30f, printerPunchable));	
		products.add(new Daily(9.9f, printerPunchable));			
	}
	
	/**
	 * F�hrt die Ablaufkontrolle aus
	 */
	public void run()
	{		
		// bis zum St.Nimmerleinstag
		while (true)
		{
			//
			Scanner input = new Scanner(System.in);
			
			// Benutzer muss ein Produkt ausw�hlen
			System.out.println("===================================================================================");
			System.out.println("Bitte w�hle ein Produkt aus:");
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
									
			// TODO Benutzer muss Zahlungsmittel w�hlen
			
			// TODO Zahlung wird durchgef�hrt
			
			// Ticket wird ausgedruckt
			selectedProduct.print();
			
			// close input scanner
			input.close();
		}		
	}
	
}
