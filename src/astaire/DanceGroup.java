package astaire;

import java.util.ArrayList;

public class DanceGroup {
	
	private String name;
	private ArrayList<String> members;
	
	public DanceGroup(String name) {
		this.name = name;
		members = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	
	public void addMember(String member) {
		members.add(member);
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
