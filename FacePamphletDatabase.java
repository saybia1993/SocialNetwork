/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;

/** this class has 4 methods: "add, delete,get, contains" */

public class FacePamphletDatabase implements FacePamphletConstants {

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	
	public FacePamphletDatabase() {
		// null
	}
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
	
	public void addProfile(FacePamphletProfile profile) {
		if (database.containsKey(profile.getName())) database.remove(profile.getName());
		database.put(profile.getName(), profile);
	}

	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	
	public FacePamphletProfile getProfile(String name) {
		if (database.containsKey(name)) return database.get(name);
		return null;
	}
	
	/** 
	 * This method removes the profile associated with the given name
	 * from the database.  It also updates the list of friends of all
	 * other profiles in the database to make sure that this name is
	 * removed from the list of friends of any other profile.
	 * 
	 * If there is no profile in the database with the given name, then
	 * the database is unchanged after calling this method.
	 */
	
	public void deleteProfile(String name) {  // THIS IS IMPORTANT!
		if (database.containsKey(name)){
			Iterator<String> it = database.get(name).getFriends(); // set an iterator "it" which represents all of input name's friends
			while (it.hasNext()){             // it.hasNext() is a String of input name's friend's name
				database.get(it.next()).removeFriend(name);
			}
		}
		database.remove(name);
	}

	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	
	public boolean containsProfile(String name) { 
		if (database.containsKey(name)) return true;
		return false;
	}

	/** instance variable*/
	
	private HashMap<String, FacePamphletProfile> database = new HashMap<String, FacePamphletProfile>();   // correspond name & Profile in HashMap
}
