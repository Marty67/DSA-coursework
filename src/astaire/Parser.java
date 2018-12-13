package astaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	
	private String line = "";
	private String csvSplit = ",";
	
	public Parser() {}

	/**
	 * Parsing for Dance
	 * @param String file
	 */
	public ArrayList<Dance> parseDance(String file, ArrayList<Dance> dances, ArrayList<DanceGroup> danceGroups) {
		BufferedReader br = null;
		line = "";
		Boolean first = true;
		ArrayList<Dance> danceList = dances;
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
					danceList.add(d);
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
				return danceList;
	}
	
	public ArrayList<Dance> parseDance(String file,ArrayList<Dance> dances){
		BufferedReader br = null;
		line = "";
		Boolean first = true;
		ArrayList<Dance> danceList = dances;
		try {
			br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null) {
				if(!first) {
				String[] lineArr = line.split("\t");
				if(lineArr[0] != null) {
					lineArr[0] = lineArr[0].trim();
					String[] nameArr = lineArr[1].split(csvSplit);
					Dance d = new Dance(lineArr[0]);
					danceList.add(d);
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
		return danceList;
	}
	
	/**
	 * Parsing for Dance Group
	 * @param String file
	 */
	public ArrayList<DanceGroup> parseGroup(String file,ArrayList<DanceGroup> danceGroups) {
		BufferedReader br = null;
		line = "";
		Boolean first = true;
		ArrayList<DanceGroup> groupList = danceGroups;
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
							groupList.add(d);
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
				return groupList;
		}
}
