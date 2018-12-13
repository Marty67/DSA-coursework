package astaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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

	private Parser parser;

	/**
	 * Initialises {@link #danceGroups}, {@link #dances}, and runs the initialise method
	 */
	public DanceShow() {
		danceGroups = new ArrayList<DanceGroup>();
		dances = new ArrayList<Dance>();
		parser = new Parser();
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
	 * Method lists all the dance numbers and the name of the respective performers in alphabetical order
	 * @return  String representation of dance numbers and the respective performers in alphabetical order. 
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
	 * Method involves the checking of the feasibility of a given running order
	 * @param String filename of CSV File
	 * @param Integer gaps the required number of gaps between the dances for each of the dancer.
	 * @return String representation of Issues
	 */
	public String checkFeasibilityOfRunningOrder(String filename, int gaps) {
		// csv/danceShowData_runningOrder.csv
		// Use for testing method
		String issues = "";
		ArrayList<Dance> runningOrder = new ArrayList<Dance>();

		runningOrder = parser.parseDance(filename, runningOrder);

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


	/**
	 * Generates a running order of the dances for the dance show.
	 * @param gaps the required number of gaps between dances for each dancer
	 * @return a String representation of the generate running order
	 */
	public String generateRunningOrder(int gaps) {
		Boolean sorted = false;
		String order = "";
		ArrayList<Dance> runningOrder = new ArrayList<Dance>();
		HashSet<Dance> danceSet = new HashSet<Dance>();
		HashMap<String,HashSet<Dance>> dancers = new HashMap<String,HashSet<Dance>>();

		//Initialises dancers; a map where the key is the dancer, and the values are all the dances they are members of
		for(Dance dance:dances) {
			for(String member:dance.getMembers()) {
				//If key already exists
				if(dancers.containsKey(member)) {
					danceSet = dancers.get(member);
					danceSet.add(dance);
					dancers.put(member, danceSet);
				} 
				//Else create new key
				else {
					danceSet = new HashSet<Dance>();
					danceSet.add(dance);
					dancers.put(member,danceSet);
				}
			}
		}
		
		//The Integer value is the total number of unique dances that the members of this dance are a part of 
		HashMap<Dance,Integer> danceSort = new HashMap<Dance,Integer>(); 
		for(Dance dance:dances) {
			HashSet<Dance> unique = new HashSet<Dance>();
			for(String member:dance.getMembers()) {
				unique.addAll(dancers.get(member));
			}
			int uniqueDances = unique.size();
			danceSort.put(dance, uniqueDances);
		}
		
		runningOrder = dances;
		
		//Initial sorting 
		//Sorts running order according to number of unique dances the members are a part of
		//The reasoning for this is that as the number of unique dances increases, so does the possibility of complications
		Collections.sort(runningOrder,new Comparator<Dance>() {
			public int compare(final Dance d1,final Dance d2) {
				return danceSort.get(d1).compareTo(danceSort.get(d2));
			}
		});
		
		if(checkFeasibilityOfRunningOrder(runningOrder,gaps)) {
			//Running order found
			for(Dance dance:runningOrder) {
			order += dance.getName() +": ";
			for(String member:dance.getMembers()) {
				order += member +" ";
			}
			order += "\n";
//			order += ": "+danceSort.get(dance)+"\n";
			}
		}
		else if(!sorted) {
			order = "Could not generate running order"+"\n";
		}
		
		return order;
	}
	
	/*
	 * Method involves the checking of the feasibility of a given running order
	 * @param ArrayList dances is the list of dances in their running order
	 * @param Integer gaps the required number of gaps between the dances for each of the dancer.
	 * @return String representation of Issues
	 */
	private boolean checkFeasibilityOfRunningOrder(ArrayList<Dance> dances, int gaps) {
		boolean sorted = false;
		HashMap<String,Integer> dancers = new HashMap<String,Integer>();
		for(Dance dance:dances) {
			for(String m:dance.getMembers()) {
				if((dancers.containsKey(m)) && (dancers.get(m)<gaps)) {
					sorted = false;
					return sorted;
				}
				dancers.put(m, 0);
			}
			for(HashMap.Entry<String,Integer> entry:dancers.entrySet()) {
				int n = entry.getValue() + 1;
				dancers.put(entry.getKey(),n);
				}
		}
		sorted = true;
		return sorted;
	}

	/**
	 * Method Initialises the data about dances, groups and their members - from 'dances' and 'dancegroups' into Arraylists.
	 */
	private void init() {
		String groupFile = "csv/danceShowData_danceGroups.csv";
		String danceFile = "csv/danceShowData_dances.csv";
		//TODO:Check array efficiency and replace if necessary
		//Make danceGroups from danceGroups.csv
		//		parseGroup(groupFile);
		danceGroups = parser.parseGroup(groupFile, danceGroups);
		//Make dances from dances.csv
		//		parseDance(danceFile);
		dances = parser.parseDance(danceFile, dances, danceGroups);
	}
}
