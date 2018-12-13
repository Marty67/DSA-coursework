
package astaire;

import java.util.ArrayList;

/**
 * - Provides methods and variables for the {@link DanceShow} DanceGroup Arraylist. 
 * 
 * @author Martynas Sreideris, Benjamin Dewhurst, Denash Sathanantheswaran
 * @version  11/12/2018
 */

public class DanceGroup {
	
	/**
	 * Name of dance group
	 */
	private String name;
	/**
	 * Arraylist of Members
	 */
	private ArrayList<String> members;
	

	public DanceGroup(String name) {
		this.name = name;
		members = new ArrayList<String>();
	}
	
	/**
	 * Returns {@link #name}
	 * @return <code>String</code>
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds a Members
	 * @param member Members to add 
	 */
	public void addMember(String member) {
		members.add(member);
	}
	
	/**
	 * Gets {@link #members} from Arraylist
	 * @return #members
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
