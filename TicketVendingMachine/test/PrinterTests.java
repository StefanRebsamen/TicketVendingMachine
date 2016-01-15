import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Klasse, welche UnitTest f�r die Klasse Printer definiert. 
 */
public class PrinterTests {
	
	// zu testendes objekt
	private Printer printer = null;
	
	// wird aufgerufen, bevor Test durchgef�hrt wird
	@Before
	public void setUp() throws Exception 
	{
		printer = new Printer(40, 10);
	}

	/**
	 * Testet, das beim korrekter Verwendung das Hochz�hlen der Zeilen funktioniert
	 */
	@Test
	public void linesAreCounted() 
	{
		printer.beginTicket();		
		// pr�ft, ob der Z�hler dem angenommenen Wert entspricht
		Assert.assertEquals(0, printer.getPrintedLines());
		
		printer.println("Zeile 1");
		printer.println("Zeile 2, etwas l�nger");
		Assert.assertEquals(2, printer.getPrintedLines());
	
		printer.endTicket();
	}

	
	/**
	 * Pr�ft, ob bei �berschreiten der L�nge eines Ticket eine Eception ausgeworfen wird 
	 */
	@Test(expected = IllegalStateException.class)
	public void exceptionIfLengthIsExceeded() 
	{
		printer.beginTicket();
		for (int i=1; i<=11; i++)
		{
			printer.println("Zeile " + i);
		}		
	}
	
}
