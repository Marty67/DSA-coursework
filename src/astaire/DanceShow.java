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

public class DanceShow implements Controller {
	
	//TODO:Check ArrayList efficiency and replace if needed
	private ArrayList<DanceGroup> danceGroups;
	private ArrayList<Dance> dances;
	
	public DanceShow() {
		danceGroups = new ArrayList<DanceGroup>();
		dances = new ArrayList<Dance>();
		init();
	}

	@Override
	public String listAllDancersIn(String dance) {
		String names = "";
		for(Dance d:dances) {
			if(d.getName().equals(dance)) {
				for(String m:d.getMembers()) {
					names += m +" ";
				}
			}
		}
		return names;
	}

	@Override
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

	//TODO:Check dancegroups aswell
	@Override
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
		
		//Integer is gap from last dance
		HashMap<String,Integer> dancers = new HashMap<String,Integer>();
		for(Dance d:runningOrder) {
			for(String m:d.getMembers()) {
				if(dancers.containsKey(m)) {
					if(dancers.get(m)<gaps) {
						issues += m+" didn't have enough time" +"\n";
					}
				}
				dancers.put(m, 0);
			}
			for(HashMap.Entry<String,Integer> entry:dancers.entrySet()) {
				int n = entry.getValue() +1;
				dancers.put(entry.getKey(),n);
				}
		}
		return issues;
	}

	@Override
	public String generateRunningOrder(int gaps) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	private void init2(String file,ArrayList<T> list) {
//		BufferedReader br = null;
//		String line = "";
//		String csvSplit = ",";
//		Boolean first = true;
//		//TODO:Check array efficiency and replace if necessary
//				//Make danceGroups from danceGroups.csv
//				try {
//					br = new BufferedReader(new FileReader(file));
//					while((line = br.readLine()) != null) {
//						if(!first) {
//						String[] lineArr = line.split("\t");
//						if(lineArr[0] != null) {
//							lineArr[0] = lineArr[0].trim();
//							DanceGroup d = new DanceGroup(lineArr[0]);
//							danceGroups.add(d);
//							String[] nameArr = lineArr[1].split(csvSplit);
//							for(String s:nameArr) {
//								s = s.trim();
//								d.addMember(s);
//							}
//						}
//					}
//						first = false;
//					}
//				} catch(FileNotFoundException e) {
//					e.printStackTrace();
//				} catch(IOException e) {
//					e.printStackTrace();
//				} finally {
//					if(br != null) {
//						try {
//							br.close();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//				first = true;
//	}
	//Initialises with dance data from 'dances' and danceGroups
	private void init() {
		String groupFile = "/Users/admin/eclipse-workspace/DSA/csv/danceShowData_danceGroups.csv";
		String danceFile = "/Users/admin/eclipse-workspace/DSA/csv/danceShowData_dances.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplit = ",";
		Boolean first = true;
		
		//TODO:Check array efficiency and replace if necessary
		//Make danceGroups from danceGroups.csv
		try {
			br = new BufferedReader(new FileReader(groupFile));
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
		
		//Make dances from dances.csv
		try {
			br = new BufferedReader(new FileReader(danceFile));
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
	}

}
