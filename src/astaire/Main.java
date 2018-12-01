package astaire;


public class Main {
	
	private static TUI tui;

	//Initialize UI with new Controller
	public static void main(String[] args) {
		tui = new TUI(new DanceShow());
	}

}
