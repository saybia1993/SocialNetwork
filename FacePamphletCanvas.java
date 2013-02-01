/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// null
	}

	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	
	public void showMessage(String msg) {   // if called, delete current message and add the new one
		message = new GLabel(msg,getWidth()/2, getHeight()-BOTTOM_MESSAGE_MARGIN);
		message.move(-message.getWidth()/2, 0);
		message.setFont(MESSAGE_FONT);
		if (getElementAt(getWidth()/2,getHeight()-BOTTOM_MESSAGE_MARGIN) != null) {
			remove(getElementAt(getWidth()/2,getHeight()-BOTTOM_MESSAGE_MARGIN));
		}
		add(message);
	}
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		
		// update name
		name = new GLabel(profile.getName(), LEFT_MARGIN, TOP_MARGIN);
		name.move(0, name.getHeight());
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.blue);
		add(name);
		
		// update picture
		add(new GRect(LEFT_MARGIN, TOP_MARGIN+IMAGE_MARGIN+name.getHeight(), IMAGE_WIDTH, IMAGE_HEIGHT));
		if (profile.getImage() == null){
			noPicture = new GLabel ("NO IMAGE!", LEFT_MARGIN+IMAGE_WIDTH/2, TOP_MARGIN+IMAGE_MARGIN+name.getHeight()+IMAGE_HEIGHT/2);
			noPicture.move(-noPicture.getWidth(), 0);
			noPicture.setFont(PROFILE_IMAGE_FONT);
			add(noPicture);
		}	else {
			image = profile.getImage();
			add(image, LEFT_MARGIN, TOP_MARGIN+IMAGE_MARGIN+name.getHeight());
		}
		
		// update status
		if (profile.getStatus()== ""){
			status = new GLabel("No current status", LEFT_MARGIN, TOP_MARGIN+IMAGE_MARGIN+name.getHeight()+IMAGE_HEIGHT+STATUS_MARGIN);
			status.move(0, status.getHeight());
			status.setFont(PROFILE_STATUS_FONT);
			add(status);
		} else {
			status = new GLabel(profile.getName()+" is "+profile.getStatus(), LEFT_MARGIN, TOP_MARGIN+IMAGE_MARGIN+name.getHeight()+IMAGE_HEIGHT+STATUS_MARGIN);
			status.move(0, status.getHeight());
			add(status);
		}
		
		// Friends Label
		friendsLabel = new GLabel("Friends: ", getWidth()/2, TOP_MARGIN+IMAGE_MARGIN+name.getHeight());
		friendsLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendsLabel);
		
		// update friend
		Iterator<String> it = profile.getFriends();
		double y = TOP_MARGIN+IMAGE_MARGIN+name.getHeight();
			while (it.hasNext()){
				friends = new GLabel (it.next());
				friends.setFont(PROFILE_FRIEND_FONT);
				y = y + friends.getHeight();
				add(friends, getWidth()/2,y);
			}
	}
	
	/** instance variable*/
	private GLabel message;
	private GLabel name;
	private GLabel noPicture;
	private GImage image;
	private GLabel status;
	private GLabel friendsLabel;
	private GLabel friends;
}
