package astaire;

import java.util.ArrayList;

/**
 * - Provides methods and variables for the {@link DanceShow} Dance Arraylist. 
 * 
 * @author Martynas Sreideris, Benjamin Dewhurst, Denash Sathanantheswaran
 * @version  11/12/2018
 */

public class Dance {
	
	/**
	 * Name of dance
	 */
	private String danceName;
	/**
	 * ArrayList of Members
	 */
	private ArrayList<String> members;
	

	public Dance(String name) {
		this.danceName = name;
		members = new ArrayList<String>();
	}
	
	/**
	 * Returns {@link #danceName}
	 * @return <code>String</code>
	 */
	public String getName() {
		return danceName;
	}
	
	/**
	 * Adds a Members
	 * @param danceMember Members to add 
	 */
	public void addMember(String danceMember) {
		members.add(danceMember);
	}
	
	/**
	 * Adds a group to the Array list
	 * @param group 
	 */
	public void addGroup(ArrayList<String> group) {
		members.addAll(group);
	}
	
	/**
	 * Returns {@link #members}
	 * @return <code>String</code>
	 */
	public ArrayList<String> getMembers() {
		return members;
	}
	
	/**
	 * Method Checks if members contain
	 * @param s
	 * @return  b
	 */
	public boolean contains(String s) {
		boolean b = false;
		if(members.contains(s)) {
			b = true;
		}
		return b;
	}

}
