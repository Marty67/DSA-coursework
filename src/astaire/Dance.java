package astaire;

import java.util.ArrayList;

public class Dance {
	
	private String name;
	private ArrayList<String> members;
	
	public Dance(String name) {
		this.name = name;
		members = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	
	public void addMember(String member) {
		members.add(member);
	}
	
	public void addGroup(ArrayList<String> group) {
		members.addAll(group);
	}
	
	public ArrayList<String> getMembers() {
		return members;
	}
	
	public boolean contains(String s) {
		boolean b = false;
		if(members.contains(s)) {
			b = true;
		}
		return b;
	}

}
