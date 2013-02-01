/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	
	public void init() {
		
		// NORTH
		name = new JLabel ("Name");
		add (name, NORTH);
		textFieldNorth = new JTextField(TEXT_FIELD_SIZE);
		add (textFieldNorth, NORTH);
		add = new JButton ("Add");
		add (add, NORTH);
		delete = new JButton ("Delete");
		add (delete, NORTH);
		lookup = new JButton ("Lookup");
		add (lookup, NORTH);
		
		// WEST
		// Change Status
		textFieldWest1 = new JTextField (TEXT_FIELD_SIZE);
		add (textFieldWest1, WEST);
		status = new JButton ("Change Status");
		add (status, WEST);
		add( new JLabel(EMPTY_LABEL_TEXT), WEST);
		// Change Picture
		textFieldWest2 = new JTextField (TEXT_FIELD_SIZE);
		add (textFieldWest2, WEST);
		picture = new JButton ("Change Picture");
		add (picture, WEST);
		add( new JLabel(EMPTY_LABEL_TEXT), WEST);
		// Add Friend
		textFieldWest3 = new JTextField (TEXT_FIELD_SIZE);
		add (textFieldWest3, WEST);
		friend = new JButton ("Add Friend");
		add (friend, WEST);
		add( new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		// West region need "Enter" function
		textFieldWest1.addActionListener(this);
		textFieldWest2.addActionListener(this);  
		textFieldWest3.addActionListener(this);
		
		// ActionListerners
		addActionListeners();
		
		// canvas
		add(canvas);
    }
	
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    
	public void actionPerformed(ActionEvent e) {
    	
		String cmd = e.getActionCommand();
    	
		// North region
    	// Add button
    	if (cmd.equals("Add")) {
    		if (data.containsProfile(textFieldNorth.getText())){ 
    			currentProfile = data.getProfile(textFieldNorth.getText());      // if exist, add = look up
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("A profile with the name<" + currentProfile.getName() + ">already exists");
    		}
    		else {
    				currentProfile = new FacePamphletProfile(textFieldNorth.getText());// if do not exist, new a FacePamphletProfile
    				data.addProfile(currentProfile);
    				canvas.displayProfile(currentProfile);
    				canvas.showMessage("New profile created");
    		}
    	}
    	
    	// Delete button
    	if (cmd.equals("Delete")) {
    		if (data.containsProfile(textFieldNorth.getText())){
    			data.deleteProfile(textFieldNorth.getText());
    			canvas.removeAll();  // clear any existing profile from the display
    			canvas.showMessage("Profile of <" +textFieldNorth.getText()+ ">deleted");
    			currentProfile = null;	
    		} else {
    			canvas.removeAll();
    			canvas.showMessage("A profile with the name<" + textFieldNorth.getText() + ">does not exist");
    			currentProfile = null;
    		}
    	}
    	
    	// Lookup button 
    	if (cmd.equals("Lookup")) {
    		if (data.containsProfile(textFieldNorth.getText())) {
    			currentProfile = data.getProfile(textFieldNorth.getText());
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Displaying<" + currentProfile.getName() + ">");
    		} else {
    			canvas.removeAll();
    			canvas.showMessage("A profile with the name<" + textFieldNorth.getText() + ">does not exist");
    			currentProfile = null;
    		}
    	}
    	
    	// West region
    	// Change Status button
    	if ((cmd.equals("Change Status"))||(e.getSource() == textFieldWest1)) {    // Change status button
    		if (currentProfile != null){
    			currentProfile.setStatus(textFieldWest1.getText());
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Status updated to<" + currentProfile.getStatus() + ">");
    		}
    		else {
    			canvas.showMessage("Please select a profile to change status");
    		}
    	}
    	
    	// Change Picture button
    	if ((cmd.equals("Change Picture"))||(e.getSource() == textFieldWest2)) {    // Change picture button
    		if (currentProfile != null){
    		GImage image = null;
    		try{
    			image = new GImage (textFieldWest2.getText());
        		currentProfile.setImage(image);
        		canvas.displayProfile(currentProfile);
        		canvas.showMessage("Picture updated");
    		} 	catch (ErrorException ex) {
    				canvas.showMessage("Unable to open image file:<" + textFieldWest2.getText() + ">");
    			}

    		} else {
    			canvas.removeAll();
    			canvas.showMessage("Please select a profile to change picture");
    		}
    	}
    	
    	// Add Friend button
    	if ((cmd.equals("Add Friend"))||(e.getSource() == textFieldWest3)) {
    		if (currentProfile != null) {
    			if (data.containsProfile(textFieldWest3.getText())){ // judge the status of added profile
    				boolean b = currentProfile.addFriend(textFieldWest3.getText());
    					if (b == false) {
    						canvas.showMessage("<" + currentProfile.getName() + ">already has " + "<" + textFieldWest3.getText() + ">as a friend");
    					}
    					if (b == true) {
    						(data.getProfile(textFieldWest3.getText())).addFriend(currentProfile.getName()); // revise adding friend! IMPORTANT
    						canvas.displayProfile(currentProfile);
    						canvas.showMessage ("<" + textFieldWest3.getText() + ">added as a friend");						
    					}
    			}  else  {
    				canvas.showMessage("<" + textFieldWest3.getText() + ">does not exist");
    			}
    		}  else {
    			canvas.removeAll();
    			canvas.showMessage("Please select a profile to add friend");
    		}
    	}
	}
	
    /** instance variable*/
    // North
    private JLabel name;
    private JTextField textFieldNorth;
    private JButton add;
    private JButton delete;
    private JButton lookup;
    // West
    private JTextField textFieldWest1;
    private JTextField textFieldWest2;
    private JTextField textFieldWest3;
    private JButton status;
    private JButton picture;
    private JButton friend;
    // Current Profile
    private FacePamphletProfile currentProfile;
    private FacePamphletDatabase data = new FacePamphletDatabase();
    // Canvas
    private FacePamphletCanvas canvas = new FacePamphletCanvas();
}
