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

	// Enth�lt alle Bezalt-Ger�te
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
	 * Fragt nach einer Eingabe, bis eine g�ltige ganze Zahl eingegeben wurde
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
				System.out.print("Ung�ltige Zahl!");				
			}
		}
	}

	/**
	 * Fragt nach einer Eingabe, bis eine g�ltige Zahl eingegeben wurde
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
				System.out.print("Ung�ltige Zahl!");				
			}
		}
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
		
		// erstelle Bezahl-Systeme
		paymentDevices.add(new CoinInserter());
		paymentDevices.add(new DebitCard());
	}
	
	/**
	 * F�hrt die Ablaufkontrolle aus
	 */
	public void run()
	{			
		// bis zum St.Nimmerleinstag
		while (true)
		{			
			int choice;
			
			// Benutzer muss ein Produkt ausw�hlen
			System.out.println("===================================================================================");
			System.out.println("Bitte w�hle ein Produkt aus:");
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
									
			// Benutzer muss Zahlungsmittel w�hlen
			System.out.println("Mit was m�chtest du bezahlen?");
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
			
			// Zahlung wird durchgef�hrt
			selectedPayment.encash(selectedProduct.getPrice());
			
			// Ticket wird ausgedruckt
			selectedProduct.print();
			
		}		
	}
	
}
