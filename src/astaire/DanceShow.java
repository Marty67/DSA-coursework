package astaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * - Returns requested data from CSV files and handles parsing 
 * 
 * @author Martynas Sreideris, Benjamin Dewhurst, Denash Sathanantheswaran
 * @version  11/12/2018
 */

public class DanceShow implements Controller {
	
	
	//TODO:Check ArrayList efficiency and replace if needed
	/**
	 * Array of {@link DanceGroup} objects
	 */
	private ArrayList<DanceGroup> danceGroups;
	/**
	 * Array of {@link Dance} objects
	 */
	private ArrayList<Dance> dances;
	
	/**
	 * Initialises {@link #danceGroups}, {@link #dances}, and runs the initialise method
	 */
	public DanceShow() {
		danceGroups = new ArrayList<DanceGroup>();
		dances = new ArrayList<Dance>();
		init();
	}

	/**
	 * List the names of all performers in a specified dance
	 * @param Specified dance in the dance show 
	 * @return returns the list of dancers in the specified dance. 
	 */
	public String listAllDancersIn(String dance) {
		String names = "";
		for(Dance d:dances) {
			if(d.getName().equals(dance)) {
				for(String m:d.getMembers()) {
					names += m + " ";
				}
			}
		}
		return names;
	}

	/**
	 * Method
	 * @param 
	 * @return  
	 */
	public String listAllDancesAndPerformers() {
		String dancesPerformers = "";
		ArrayList<Dance> dancesAlphabetical = dances;
		Collections.sort(dancesAlphabetical, new Comparator<Dance>() {
			public int compare(final Dance d1,final Dance d2) {
				return d1.getName().compareTo(d2.getName());
			}
		});
		for(Dance d:dancesAlphabetical) {
			Collections.sort(d.getMembers());
			dancesPerformers += d.getName() + ": ";
			for(String s:d.getMembers()) {
				dancesPerformers += s + " ";
			}
			dancesPerformers += "\n";
		}
		return dancesPerformers;
	}
	/**
	 * Method
	 * @param 
	 * @return  
	 */
	public String checkFeasibilityOfRunningOrder(String filename, int gaps) {
		BufferedReader br = null;
		String line = "";
		String csvSplit = ",";
		String issues = "";
		Boolean first = true;
		ArrayList<Dance> runningOrder = new ArrayList<Dance>();
		
		try {
			br = new BufferedReader(new FileReader(filename));
			while((line = br.readLine()) != null) {
				if(!first) {
				String[] lineArr = line.split("\t");
				if(lineArr[0] != null) {
					lineArr[0] = lineArr[0].trim();
					String[] nameArr = lineArr[1].split(csvSplit);
					Dance d = new Dance(lineArr[0]);
					runningOrder.add(d);
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
		
		//Integer value is gap from last dance member(key) performed
		
		HashMap<String,Integer> dancers = new HashMap<String,Integer>();
		for(Dance d:runningOrder) {
			for(String m:d.getMembers()) {
				if(dancers.containsKey(m)) {
					if(dancers.get(m)<gaps) {
						issues += m+" didn't have enough time to get ready for '"+d.getName()+"'" + "\n";
					}
				}
				dancers.put(m, 0);
			}
			for(HashMap.Entry<String,Integer> entry:dancers.entrySet()) {
				int n = entry.getValue() + 1;
				dancers.put(entry.getKey(),n);
				}
		}
		return issues;
	}

	@Override
	/**
	 * Method
	 * @param 
	 * @return  
	 */
	public String generateRunningOrder(int gaps) {
		// TODO Auto-generated method stub
		ArrayList<Dance> runningOrder = new ArrayList<Dance>();
		HashMap<String,Integer> dancers = new HashMap<String,Integer>();
		
		return null;
	}
	
	//Initialises with data about dances, groups and their members (from 'dances' and 'danceGroups') into arraylists
	/**
	 * Method
	 * @param 
	 * @return  
	 */
	private void init() {
		String groupFile = "csv/danceShowData_danceGroups.csv";
		String danceFile = "csv/danceShowData_dances.csv";
		//TODO:Check array efficiency and replace if necessary
		//Make danceGroups from danceGroups.csv
		parseGroup(groupFile);
		//Make dances from dances.csv
		parseDance(danceFile);
	}

	
	
	private void parseDance(String file) {
		BufferedReader br = null;
		String line = "";
		String csvSplit = ",";
		Boolean first = true;
		//TODO:Check array efficiency and replace if necessary
		//Make dances from dance.csv
		try {
			br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null) {
				if(!first) {
				String[] lineArr = line.split("\t");
				if(lineArr[0] != null) {
					lineArr[0] = lineArr[0].trim();
					Dance d = new Dance(lineArr[0]);
					dances.add(d);
					String[] memberArr = lineArr[1].split(csvSplit);
					for(String s:memberArr) {
						s = s.trim();
						boolean isGroup = false;
						for(DanceGroup dG:danceGroups) {
							if(dG.getName().equals(s)) {
								d.addGroup(dG.getMembers());
								isGroup = true;
								break;
							}
						}
						if(!isGroup) {
							d.addMember(s);
						}
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
				first = true;
	}
	

	private void parseGroup(String file) {
		BufferedReader br = null;
		String line = "";
		String csvSplit = ",";
		Boolean first = true;
		//TODO:Check array efficiency and replace if necessary
		//Make danceGroups from danceGroups.csv
				try {
					br = new BufferedReader(new FileReader(file));
					while((line = br.readLine()) != null) {
						if(!first) {
						String[] lineArr = line.split("\t");
						if(lineArr[0] != null) {
							lineArr[0] = lineArr[0].trim();
							DanceGroup d = new DanceGroup(lineArr[0]);
							danceGroups.add(d);
							String[] nameArr = lineArr[1].split(csvSplit);
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
				first = true;
		}
}
