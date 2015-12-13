
public class CoinInserter implements IPaymentDevice 
{
	// Betrag, welcher im aktuelleb Bezahlvorgang bereits eingegeben wurde
	private float inserted;
	
	/**
	 * Konstruktor
	 */
	public CoinInserter() 
	{
		System.out.println("Münzenwurf wurde initialisiert");
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
		}
		if (inserted > amount)
		{
			System.out.println(String.format("Rückgeld: %.2f", inserted - amount));
		}		
	}

	@Override
	public String getName() {
		return "Münzeinwurf";
	}

}
