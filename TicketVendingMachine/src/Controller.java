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

	// Enthält alle Bezalt-Geräte
	private final List<IPaymentDevice> paymentDevices = new ArrayList<IPaymentDevice>();
		
	// Scanner, um Benutzereingaben zu kriegen
	private final Scanner input = new Scanner(System.in);
	
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
	 * Fragt nach einer Eingabe, bis eine gültige ganze Zahl eingegeben wurde
	 * @return Vom Benutzer eigegebene ganze Zahl
	 */
	public int getIntUserInput()
	{
		while (true)
		{
			try
			{
				System.out.print("> ");
				return input.nextInt();
			}
			catch (InputMismatchException ex)
			{
				System.out.print("Ungültige Zahl!");				
			}
		}
	}

	/**
	 * Fragt nach einer Eingabe, bis eine gültige Zahl eingegeben wurde
	 * @return Vom Benutzer eigegebene Zahl
	 */
	public float getFloatUserInput()
	{
		while (true)
		{
			try
			{
				System.out.print("> ");
				return input.nextFloat();
			}
			catch (InputMismatchException ex)
			{
				System.out.print("Ungültige Zahl!");				
			}
		}
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
		
		// erstelle Bezahl-Systeme
		paymentDevices.add(new CoinInserter());
		paymentDevices.add(new DebitCard());
	}
	
	/**
	 * Führt die Ablaufkontrolle aus
	 */
	public void run()
	{			
		// bis zum St.Nimmerleinstag
		while (true)
		{			
			int choice;
			
			// Benutzer muss ein Produkt auswählen
			System.out.println("===================================================================================");
			System.out.println("Bitte wähle ein Produkt aus:");
			for (Product product : products)
			{
				System.out.println(String.format("%2d  %-30s %.2f", products.indexOf(product), product.getDisplay(), product.getPrice()));
			}
			choice = getIntUserInput();
			if (choice < 0 || choice >= products.size())
			{
				continue;
			}			
			Product selectedProduct = products.get(choice);
									
			// Benutzer muss Zahlungsmittel wählen
			System.out.println("Mit was möchtest du bezahlen?");
			for (IPaymentDevice pd : paymentDevices)
			{
				System.out.println(String.format("%2d  %-30s", paymentDevices.indexOf(pd), pd.getName()));
			}
			choice = getIntUserInput();
			if (choice < 0 || choice >= paymentDevices.size())
			{
				continue;
			}			
			IPaymentDevice selectedPayment = paymentDevices.get(choice);
			
			// Zahlung wird durchgeführt
			selectedPayment.encash(selectedProduct.getPrice());
			
			// Ticket wird ausgedruckt
			selectedProduct.print();
			
		}		
	}
	
}
