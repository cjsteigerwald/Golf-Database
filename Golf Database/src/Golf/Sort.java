package Golf;

import static Golf.GolfGUI.addNewGolferComplete;
import static Golf.GolfGUI.tempNewGolfer;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Sort.java
 * 
 * Sort class is used to sort through the arrayList and display golfers 
 * either by last name, or ranking.  Sorting methods are called by
 * displayGolfers in GUI
 * <pre>
 *    Project: Shipping Hub
 *    Platform: jdk 1.7.0_17; NetBeans IDE 7.3; Windows 7
 *    Course:
 *    Hourse: 2 hours and 45 minutes
 *    Created on Apr 24, 2013, 10:45:39 PM
 *    Revised on
 * </pre>
 * @see     java.util.StringTokenizer;
 * @see     java.util.ArrayList;
 */
public class Sort
{    
    // Allocates memory for Member and Golfer
    Member myMember;
    Golfer myGolfer;
    
   
      /**
     * Sets tempNewMember and addNewMemberComplete with values taken from the
     * AddMemberPrompt.
     * @param newMember: Member to be added to database
     * @param isCompleted: Boolean y/n if the add member prompt was successful
     * Pre-condition: A valid member object and a boolean value y/n
     * Post-condition: tempNewMember now holds the member object to be added to
     * the database as well as a boolean variable indicating if the adding process
     * was completed.
     */
    protected static void setNewGolfer(Golfer newGolfer, boolean isCompleted)
    {
        tempNewGolfer = newGolfer;
        addNewGolferComplete = isCompleted;
    }
    
    /**
     * <pre>
     * Method: insertionSort
     * Sorts ArrayList myGolfersList in ascending order by name. Uses the insertion
     * sort algorithm which inserts golfer at correct position and shuffles
     * everyone else below that position.
     * @parem ArrayList: myGolfersList
     * @return void
     * pre-condition: ArrayList myGolfersList filled-in with City objects.
     * post-condition: myGolfersList ArrayList is sorted by name.
     * 
     * @author 
     * Chris Steigerwald
     * </pre>
     */
    public static void insertionSort(ArrayList <Golfer> myGolfersList)
    {
	int i, j;
	for(i = 0; i < myGolfersList.size(); i++)
	{
            Golfer temp = myGolfersList.get(i);
            j = i - 1;
            while (j >=0 && myGolfersList.get(j).getLastName().compareToIgnoreCase(temp.getLastName()) > 0)
            {
                myGolfersList.set(j + 1, myGolfersList.get(j));
		j--;
            }
            myGolfersList.set(j + 1, temp);
	}
    }
    
    /**
     * <pre>
     * Method: selectionSort
     * Sorts ArrayList cities in descending order by population. Calls
     * findsMaximum to find city with maximum population in each pass
     * and swap to exchange cities when necessary.
     * @parem ArrayList: cities
     * @return void
     * pre-condition: ArrayList cities filled-in with City objects.
     * post-condition: cities ArrayList is sorted by population.
     * @author Chris Steigerwald
     * </pre>
     */
    public void selectionSort(ArrayList < Golfer > myGolfersList)
    {
       for(int i = 0; i < myGolfersList.size(); i++)
       {
          int max = findMinimum(myGolfersList, i);
          swap(myGolfersList, i, max);
       }
    }
    
    /**
     * Method: findMaximum
     * Called by selectionSort to find the index of the member with the maximum
     * population from a given index to the end of the ArrayList
     * @parem ArrayList: cities
     * @parem  int i: index from which to search for the max >= 0
     * @return int: position or index  where maximum is located
     * pre-condition: ArrayList members filled-in with members objects, int i >= 0.
     * post-condition: members ArrayList is sorted by level.
     */
    public int findMinimum(ArrayList < Golfer > myGolfersList, int i)
    {
       int j, min = i;
       for(j = i + 1; j < myGolfersList.size(); j++)
       {
           if (myGolfersList.get(j).getRating() < myGolfersList.get(min).getRating())
               min = j;
       }
       return min;
    }
    
     /**
     * Method: swap
     * Called by selectionSort to find the index of the member with the maximum
     * level from a given index to the end of the ArrayList
     * @parem ArrayList: members
     * @parem  int i: index of element to be swapped >= 0
     * @parem  int j: index of element to be swapped >= 0
     * @return void
     * pre-condition: ArrayList members filled-in with members objects, int i, j >= 0.
     * post-condition: members ArrayList with two members swapped.
     */
    public void swap(ArrayList < Golfer > myGolfersList, int i, int j)
    {
       Golfer temp = myGolfersList.get(i);    
       myGolfersList.set(i, myGolfersList.get(j));       
       myGolfersList.set(j, temp);
    }
    
}
