package astaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
