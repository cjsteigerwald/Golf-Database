package Golf;

/**
 * Golfer.java
 * 
 * Golfer class extends the Member class and add a rating parameter so that 
 * golfers can be rated numerically on their abilities.  Golfer class takes in
 * first name, last name, email, phone, and rating, and passes all but rating
 * to the super (Member class).
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
public class Golfer extends Member
{
    private int rating = 0;  // class variable for golfer rating passed in from GUI
    
    /**
     * <pre>
     * Golpher
     * Default constructor with no parameters passed in, sets class variables
     * to their default values.
     * pre condition: class to be instantiated
     * post condition: that class variables to be set to default values
     * @param: void none expected
     * @return: void
     * @author: Chris Steigerwald
     * </pre>
     */
    public Golfer()
    {
        rating = 0;
    }
    
    /**
     * <pre>
     * Golfer
     * Overloaded constructor with parameters passed in from external file, or 
     * new member created by user.  A new golfer object will be created. 
     * Parameters first, last, mail, tele will be passed to parent class
     * Member through use of super.
     * pre condition: all parameters to be validated prior to passing in
     * post condition: a new golfer object is created
     * @param fist member first name will be passed to Member class
     * @param last member last  name will be passed to Member class
     * @param mail member email address will be passed to Member class
     * @param tele member telephone number will be passed to Member class
     * @param rate golfer rating will set rating class variable
     * @return: void
     * @author: Chris Steigerwald
     * </pre>
     */
    public Golfer(String first, String last, String mail, String tele, int rate)
    {
        super ( first, last, mail, tele );
        rating = rate;
    }
    /**
     * <pre>
     * Golfer
     * Overloaded constructor with Golfer object passed in from GUI or AddFormJDialog
     * when user inputs new user.  Golfer object is broken up and only rating 
     * stays in the class, the rest is sent to super (Member Class).
     * @param copyGolfer 
     * @return: void
     * @date: 5/15/13
     * @author: Chris Steigerwald
     */
    public Golfer(Golfer copyGolfer)
    {
        super ( copyGolfer.getFirstName(), copyGolfer.getLastName(), copyGolfer.getEmail(), copyGolfer.getPhone() );
        rating = copyGolfer.rating;
    }
    
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
    
    @Override
    public void setRating(int rate)
    {
        this.rating = rate;
    }
    
    /**
     * <pre>     
     * Method: equals
     * Checks to see if two golfer have the same last name, first name
     * and phone number.  Returns true or false
     * @param Golfer passed from constructor
     * @return true or false
     * @author: Chris Steigerwald
     * </pre>
     */    
     public boolean equals (Golfer golfer)
    {
//        String first = fName;
//        String last = lName;
//        String phone = ph;
//        if (this.getFirstName().equalsIgnoreCase(golfer.getFirstName()) && 
//                (this.getLastName().equalsIgnoreCase(golfer.getLastName())))
            if (this.getFirstName().equalsIgnoreCase(golfer.getFirstName()) &&
                    this.getLastName().equals(golfer.getLastName()) &&
                    this.getPhone().equals(golfer.getPhone()))
            
            return true;
        else
            return false;
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
    @Override
    public String toString()
    {
        return super.toString() + rating + ",";
    }
    
    
}
