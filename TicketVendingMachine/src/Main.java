/**
 * Klasse, welche die Hauptmethode implementiert
 */
public abstract class Main {
	
	/**
	 * Hauptmethode (hier wird unser Programm gestartet)
	 * @param args
	 */
	public static void main(String[] args) {
		
		// hole Instanz von Ablaufsteuerung
		Controller ctrl = Controller.getInstance(); 
		
		// setze aktuellen Standort
		ctrl.setLocation("Ittigen, Ey 10");
		
		// Ablaufsteuerung soll sogleich übernehmen	
		ctrl.run();			
	}

}
