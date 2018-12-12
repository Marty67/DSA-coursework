package astaire;

import java.util.ArrayList;

/**
 * - Provides methods and variables for the {@link DanceShow} Dance Arraylist. 
 * 
 * @author Martynas Sreideris, Benjamin Dewhurst, Denash Sathanantheswaran
 * @version  11/12/2018
 */

public class Dance {
	
	private String name;
	private ArrayList<String> members;
	
	/**
	 * Method
	 * @param 
	 * @return  
	 */
	public Dance(String name) {
		this.name = name;
		members = new ArrayList<String>();
	}
	
	/**
	 * Method
	 * @param 
	 * @return  
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method
	 * @param 
	 * @return  
	 */
	public void addMember(String member) {
		members.add(member);
	}
	
	/**
	 * Method
	 * @param 
	 * @return  
	 */
	public void addGroup(ArrayList<String> group) {
		members.addAll(group);
	}
	
	/**
	 * Method
	 * @param 
	 * @return  
	 */
	public ArrayList<String> getMembers() {
		return members;
	}
	
	/**
	 * Method
	 * @param 
	 * @return  
	 */
	public boolean contains(String s) {
		boolean b = false;
		if(members.contains(s)) {
			b = true;
		}
		return b;
	}

}
