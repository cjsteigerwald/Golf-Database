package Golf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.*;
/**
 *Class:               Validation
* File:                Validation.java
* Description:         Validates entered values
* @author:             Chris Steigerwald
* Environment:         PC, Windows Vista Business, jdk 7.0, NetBeans 7.0.1
* Date:                04/26/2012
* @version             7.0.1
* @see                 java.util.regex.Matcher
* @see                 java.util.regex.Pattern
 */
public class Validation {
    GolfGUI myGUI = new GolfGUI();
    int arraySize = myGUI.myGolferList.size() + 2; 
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method:		validName()
    *	Description:	Validates that name is valid
    *	@return:	boolean 
    *	@param:		String: fieldValue
    *	@author:	Niko Culevski
    *   @see:           java.util.regex.Matcher
    *   @see:           java.util.regex.Pattern
    *	Date:		5/15/2013
    *<pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public static boolean validName(String fieldValue)
{
    String first = fieldValue;
    String alphaPattern = "(?i)^[a-z/i]*$";  //String pattern to check incoming string against
    
    if(!first.matches(alphaPattern) || (first.equals("")))
    {
        return false;
    }        
    else
    {
        return true;
    }        
}
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method:		validText()
    *	Description:	Validates that charcters are valid
    *	@return:	boolean 
    *	@param:		String: char c
    *	@author:	Chris Steigerwald
    *   @see:           java.util.regex.Matcher
    *   @see:           java.util.regex.Pattern
    *	Date:		5/15/2013
    *<pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean validText(char c)
    {
        boolean isValid = false;

        if(Character.isLetter(c) || Character.isISOControl(c))
            return true;
        else
            return false;

    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method:		vaildEmail()
    *	Description:	Validates that email is valid
    *	@return:	boolean 
    *	@param:		String: String check
    *	@author:	Chris Steigerwald
    *   @see:           java.util.regex.Matcher
    *   @see:           java.util.regex.Pattern
    *	Date:		5/15/2013
    *<pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean vaildEmail(String check)
    {
        String email = check;
        String alphaPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
        if (!email.equals("") && !email.matches(alphaPattern))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method:		isPhone()
    *	Description:	Validates that phone number is valid
    *	@return:	boolean 
    *	@param:		String: String check
    *	@author:	Chris Steigerwald
    *   @see:           java.util.regex.Matcher
    *   @see:           java.util.regex.Pattern
    *	Date:		5/15/2013
    *<pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isPhone(String check)
    {
        String phone = check;
        String alphaPattern = "^\\(\\d{3}\\) ?\\d{3}( |-)?\\d{4}|^\\d{3}( |-)?\\d{3}( |-)?\\d{4}$";
        if(!phone.matches(alphaPattern))
        {
            return false;   
        }            
        else
        {
            return true;
        }        
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method:		checkEmail()
    *	Description:	Validates that email is valid
    *	@return:	boolean 
    *	@param:		String: String check
    *	@author:	Chris Steigerwald
    *   @see:           java.util.regex.Matcher
    *   @see:           java.util.regex.Pattern
    *	Date:		5/15/2013
    *<pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
     public static boolean checkEmail(String check)
     {
         String email = check; 
         String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

         Pattern p = Pattern.compile(regEx);
         Matcher m = p.matcher(email);

         if(m.find()) 
         {
             return true; 
         }

         else
         {
             return false;
         }
     }
     
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method:		checkComplete()
    *	Description:	Validates that Golfer object is valid
    *	@return:	boolean 
    *	@param:		String: String first, String last, String mail, String ph, int rate
    *	@author:	Chris Steigerwald
    *   @see:           java.util.regex.Matcher
    *   @see:           java.util.regex.Pattern
    *	Date:		5/15/2013
    *<pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
     public static boolean checkComplete(String first, String last, String mail, String ph, int rate)
     {
         
         String firstName = first;
         String lastName = last;
         String email = mail;
         String phone = ph;
         int level = rate;
         
         if(firstName.equals("") || lastName.equals("") || phone.equals("") || level == 0)
         {
             return false;
         }
         else
         {
             return true;
         }         
     }
     
    public static boolean checkRating(int rate, int size)
    {
        int arraySize = size;
        int rating = rate;
        if (rating < 1 || rating > arraySize)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
