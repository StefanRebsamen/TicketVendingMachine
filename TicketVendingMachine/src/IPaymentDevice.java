
/**
 * Definiert die Schnittstelle für ein Bezahlungsgerät 
 */
public interface IPaymentDevice 
{
	/**
	 * Kasiert den angegebeben Betrag ein
	 * @param amount Betrag, welche einzukassieren ist
	 * @return
	 */
	void encash(float amount);
	
	/**
	 * Gibt den Namen des Bezahltgerätes wieder
	 */
	String getName();	
}
