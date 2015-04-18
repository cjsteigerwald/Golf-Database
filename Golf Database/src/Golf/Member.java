package Golf;

/**
 * Member.java
 * 
 * Member class is a parent of Golfer class for creating members of the golf club.  
 * Some members may not be golfers so this class creates member objects that 
 * have first name last name, email, and phone number.  
 * <pre>
 *  Project: Golf Database
 *  Platform: jdk 1.7.0_17; NetBeans IDE 7.3; Windows 7
 *  Coarse: CS 142 Java II
 *  Hours: 2 hours
 *  Created on May 15, 2013
 *  @author Chris
 *  Revised on:
 * </pre>
 */

public class Member
{
    // class variables
    private String firstName = ""; // variable for first name passed in from GUI
    private String lastName = "";  // variable for last name passed in from GUI
    private String email = " ";  // variable for email passed in from GUI
    private String phone = "";  // variable for phone number passed in from GUI
    private int rating = 0;
//    private int rating = 0; 
    
    /**
     * <pre>
     * Member
     * Default constructor with no parameters passed in, sets class variables
     * to their default values.
     * pre condition: class to be instantiated
     * post condition: that class variables to be set to default values
     * @param: void none expected
     * @return: void
     * @author: Chris Steigerwald
     * </pre>
     */
    public Member()
    {
        firstName = ""; 
        lastName = "";
        email = "";
        phone = "";
        
    }
    
    /**
     * <pre>
     * Member
     * Overloaded constructor with parameters passed in from external file, or 
     * new golfer created by user.  A new member object will be created
     * pre condition: parametes to be passed in are String firsName, String
     * lastName, String email, String phone
     * post condition: a new golfer object is created
     * @param fist member first name 
     * @param last member last  name 
     * @param mail member email address 
     * @param tele member telephone number      
     * lastName, String email, String phone
     * @return: void
     * @author: Chris Steigerwald
     * </pre>
     */
    public Member ( String first, String last, String mail, String tele )
    {
        firstName = first; 
        lastName = last;
        email = mail;
        phone = tele;  
    }
    
    public Member ( String first, String last, String mail, String tele, int rate )
    {
        firstName = first; 
        lastName = last;
        email = mail;
        phone = tele; 
        rating = rate;
    }
    
    /**
     * <pre>     
     * Method: getFirstName
     * Gets the first name of member
     * pre condition: parameter validated prior to being passed in
     * post condition: gets firstName when called
     * @param void
     * @return firstName
     * @author: Chris Steigerwald
     * </pre>
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * <pre>     
     * Method: setFirstName
     * Sets the first name of member
     * pre condition: parameter validated prior to being passed in
     * post condition: sets firstName class variable
     * @param first
     * @return firstName
     * @author: Chris Steigerwald
     * </pre>
     */
    public void setFirstName(String first)
    {
        this.firstName = first;
    }
   
    /**
     * <pre>     
     * Method: getLastName
     * Gets the last name of member
     * pre condition: parameter validated prior to being passed in
     * post condition: gets lastName when called
     * @param void
     * @return lastName
     * @author: Chris Steigerwald
     * </pre>
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * <pre>     
     * Method: setLastName
     * Sets the first name of member
     * pre condition: parameter validated prior to being passed in
     * post condition: sets lastName when called
     * @param last
     * @return lastName
     * @author: Chris Steigerwald
     * </pre>
     */
    public void setLastName(String last)
    {
        this.lastName = last;
    }    
     
    /**
     * <pre>     
     * Method: getPhone
     * Sets the first name of member
     * pre condition: parameter validated prior to being passed in
     * post condition: gets phone number when called
     * @param void
     * @return getPhone
     * @author: Chris Steigerwald
     * </pre>
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * <pre>     
     * Method: setPhone
     * Gets the phone number of member
     * pre condition: parameter validated prior to being passed in
     * post condition: sets telephone number when called
     * @param void
     * @return phone
     * @author: Chris Steigerwald
     * </pre>
     */
    public void setPhone(String tele)
    {
        this.phone = tele;
    }

    /**
     * <pre>     
     * Method: getEmail
     * Gets the email address of member
     * pre condition: parameter validated prior to being passed in
     * post condition: gets email address when called
     * @param void
     * @return email
     * @author: Chris Steigerwald
     * </pre>
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * <pre>     
     * Method: setEamil
     * Sets the email address of member
     * pre condition: parameter validated prior to being passed in
     * post condition: sets email adress when called
     * @param void
     * @return setEamil
     * @author: Chris Steigerwald
     * </pre>
     */
    public void setEamil(String mail)
    {
        this.email = mail;
    }   
  
    /**
     * <pre>     
     * Method: toString
     * Overrides default toString, and calls Member toString to concatanate with
     * Golfer toString
     * @param super.toString
     * @return toString
     * @author: Chris Steigerwald
     * </pre>
     */
    
    /**
     * <pre>     
     * Method: getRating
     * Gets the rating for the golfer
     * @param void
     * @return rating
     * @author: Chris Steigerwald
     * </pre>
     */
    public int getRating()
    {
        return rating;
    }
    
    /**
     * <pre>     
     * Method: setRating
     * Sets the rating for the golfer
     * @param rate passed from constructor
     * @return rating
     * @author: Chris Steigerwald
     * </pre>
     */    
    
    public void setRating(int rate)
    {
        this.rating = rate;
    }
    
    /**
     * <pre>
     * Method toString Overriden
     * This toString over rides default to string to show first name, last 
     * name, email, and phone number
     * @return toString
     */
    @Override
    public String toString()
    {
        return firstName + "," + lastName + "," + email + "," + phone + ",";
    }
   
    
}
