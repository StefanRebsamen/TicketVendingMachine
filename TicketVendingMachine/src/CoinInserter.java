import java.util.*;

public class CoinInserter implements IPaymentDevice 
{
	/**
	 * Interface für Klassen, welche an Events interessiert sind 
	 */
	public interface EventListener
	{
		/**
		 * Ein Münze wurde eingeworfen
		 * @param value
		 */
		void coinInserted(float value);
		
		/**
		 * Retourgeld wurde zurückgegeben
		 * @param value
		 */
		void changeReturned(float value);
	}
	
	// Betrag, welcher im aktuellen Bezahlvorgang bereits eingegeben wurde
	private float inserted;
	
	
	// Liste von Objekten, welche an Events interessiert sind
	private final List<EventListener> eventListeners = new ArrayList<EventListener>();
	
	/**
	 * Konstruktor
	 */
	public CoinInserter() 
	{
		System.out.println("Münzeinwurf wurde initialisiert");
	}

	@Override
	public void encash(float amount) 
	{
		inserted = 0.0f;
		
		System.out.println(String.format("Bares ist Wahres! Bitte Münzen einwerfen." ));		
		while (inserted < amount)
		{
			inserted += Controller.getInstance().getFloatUserInput();
			System.out.println(String.format("Eingeworfen: %.2f ", inserted));
			
			// informiere alle Listener
			for(EventListener listener : eventListeners)
			{
				listener.coinInserted(inserted);
			}
		}
		if (inserted > amount)
		{
			System.out.println(String.format("Rückgeld: %.2f", inserted - amount));
			
			// informiere alle Listener
			for(EventListener listener : eventListeners)
			{
				listener.changeReturned(inserted);
			}
		}		
	}

	@Override
	public String getName() {
		return "Münzeinwurf";
	}

	/**
	 * Registriert einen Event-Listener
	 * @param listener
	 */
	public void register(EventListener listener)
	{
		eventListeners.add(listener);
	}

	/**
	 * Entfernt einenen registierten Event-Listener
	 * @param listener
	 */
	public void unregister(EventListener listener)
	{
		eventListeners.remove(listener);
	}
	
}
