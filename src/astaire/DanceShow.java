package astaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DanceShow implements Controller {
	
	private ArrayList<DanceGroup> danceGroups;
	
	public DanceShow() {
		danceGroups = new ArrayList<DanceGroup>();
		init();
	}

	@Override
	public String listAllDancersIn(String dance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listAllDancesAndPerformers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkFeasibilityOfRunningOrder(String filename, int gaps) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateRunningOrder(int gaps) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//Initialises with dance data from 'dances' and danceGroups
	private void init() {
		//Make danceGroups from csv
		String file = "/Users/admin/eclipse-workspace/DSA/csv/danceShowData_danceGroups.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplit = ",";
		Boolean first = true;
		
		//Check array efficiency and replace if necessary
		try {
			br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null) {
				if(!first) {
				String[] lineArr = line.split("\t");
				if(lineArr[0] != null) {
					DanceGroup d = new DanceGroup(lineArr[0]);
					danceGroups.add(d);
					String[] nameArr = lineArr[1].split(",");
					for(String s:nameArr) {
						s = s.trim();
						d.addMember(s);
					}
				}
			}
				first = false;
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
