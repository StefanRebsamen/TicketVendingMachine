import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrinterTests {

	private Printer printer = null;
	
	@Before
	public void setUp() throws Exception 
	{
		printer = new Printer(40, 10);
	}

	@Test
	public void testNormal() 
	{
		printer.beginTicket();		
		printer.println("Zeile 1");
		printer.println("Zeile 2, etwas länger");
		printer.endTicket();				
	}

}
