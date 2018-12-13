
package astaire;

/**
 * - Main class that allows the application to be executed by initialising {@link TUI}
 * 
 * @author Martynas Sreideris, Benjamin Dewhurst, Denash Sathanantheswaran
 * @version  11/12/2018
 */

public class Main {

	private static TUI tui;


	/**
	 * Initialise UI with {@link Controller}
	 * @param args
	 */
	public static void main(String[] args) {
		tui = new TUI(new DanceShow());
	}

}
