
public class CoinInserter implements IPaymentDevice 
{
	// Betrag, welcher im aktuelleb Bezahlvorgang bereits eingegeben wurde
	private float inserted;
	
	/**
	 * Konstruktor
	 */
	public CoinInserter() 
	{
		System.out.println("M�nzenwurf wurde initialisiert");
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
		}
		if (inserted > amount)
		{
			System.out.println(String.format("R�ckgeld: %.2f", inserted - amount));
		}		
	}

	@Override
	public String getName() {
		return "M�nzeinwurf";
	}

}
