import java.util.*;

public class CoinInserter implements IPaymentDevice 
{
	/**
	 * Interface f�r Klassen, welche an Events interessiert sind 
	 */
	public interface EventListener
	{
		/**
		 * Ein M�nze wurde eingeworfen
		 * @param value
		 */
		void coinInserted(float value);
		
		/**
		 * Retourgeld wurde zur�ckgegeben
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
		System.out.println("M�nzeinwurf wurde initialisiert");
	}

	@Override
	public void encash(float amount) 
	{
		inserted = 0.0f;
		
		System.out.println(String.format("Bares ist Wahres! Bitte M�nzen einwerfen." ));		
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
			System.out.println(String.format("R�ckgeld: %.2f", inserted - amount));
			
			// informiere alle Listener
			for(EventListener listener : eventListeners)
			{
				listener.changeReturned(inserted);
			}
		}		
	}

	@Override
	public String getName() {
		return "M�nzeinwurf";
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
