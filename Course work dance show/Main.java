import java.util.Scanner;
public class Main {
	private static DanceShow danceShow;
	public static void Main(String[] args) {
		// TODO Auto-generated method stub
		danceShow = new DanceShow();
		load();
	}
	public static void load() {
		//loads in data creates other classes with the imported data
	}
	public static void menu() {
		boolean loop = true;
		while(loop == true) {
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			System.out.print("Options: \n1) List Performers Names For A Specified Dance \n"
					+ "2) List Dance Numbers And Members In Alphabetical Order \n"
					+ "3) Give Show Running Order\n"
					+ "9) Exit");
			int n = reader.nextInt(); 
			//if statements
			if(n == 1) {
				danceShow.performList();
			}
			if(n == 2) {
				danceShow.danceList();
			}
			if(n == 3) {
				danceShow.getRunningOrder();
			}
			if(n == 9) {
				loop = false;
			}
			reader.close();
		}

	}
	

	
}
