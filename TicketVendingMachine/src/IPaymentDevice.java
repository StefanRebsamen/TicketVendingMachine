
/**
 * Definiert die Schnittstelle f�r ein Bezahlungsger�t 
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
	 * Gibt den Namen des Bezahltger�tes wieder
	 */
	String getName();	
}
