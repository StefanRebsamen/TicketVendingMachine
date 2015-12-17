
public class DebitCard implements IPaymentDevice 
{
	/**
	 * Konstruktor
	 */
	public DebitCard() 
	{
		System.out.println("Debitkarten-Gerät wurde initialisiert");
	}

	@Override
	public void encash(float amount) 
	{	
		System.out.println(String.format("Bitte Debitkarte einstecken" ));
		System.out.println(String.format("Baue Verbindung auf ...." ));
		System.out.println(String.format("Buchung erfolgreich." ));		
	}
	
	@Override
	public String getName() {
		return "Debitkarte";
	}

}
