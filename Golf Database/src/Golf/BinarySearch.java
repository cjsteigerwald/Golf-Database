package Golf;

/**
 * BinarySearch.java
 * 
 * BinarySearch can be used to search for golfer by first, and last names.
 * At this point this search function is not being utilized.  BinarySearch
 * is called in the GUI by SearchName method.
 * <pre>
 *  Project: Golf Database
 *  Platform: jdk 1.7.0_17; NetBeans IDE 7.3; Windows 7
 *  Coarse: CS 142 Java II
 *  Hours: 1 hours
 *  Created on May 15, 2013
 *  @author Chris
 *  Revised on:
 * </pre>
 */
public class BinarySearch {    
    
    /**
     * <pre>
     * binarySearch
     * binarySearch is called by the SearchName method in the GUI.  It utilizes
     * a binary search to find a golfer by first and last names.  If golfer is 
     * found returns index location.  If not found returns -1 signalling golfer
     * does not exist in database.
     * precondition: valid array and String key
     * postcondition: either return index value if found or -1 if not found
     * @param array
     * @param key
     * @return int number of location, or -1 if not found
     * @date 5/15/13
     * @author Chris Steigerwald
     */
    public static int binarySearch(String[] array, String key)
    {
       int low = 0;                     // low subscript
       int high = array.length - 1;     // high subscript
       int middle;                      // middle subscript

       while ( low <= high ) 
       {
          middle = ( low + high ) / 2;
          if ( key.equalsIgnoreCase(array[middle]))     // match city
             return middle;
          else if ( key.compareToIgnoreCase(array[ middle ] ) < 0)
             high = middle - 1;  // search low end of array
          else
             low = middle + 1;   // search high end of array
       }
       return -1;   // searchKey not found
    
    }
}
