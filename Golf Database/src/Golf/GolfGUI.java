package Golf;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * GolferGUI.java
 * 
 * GolferGUI is the user interface for a database program that allows the user
 * to save student golfers in a database that keeps track of their first, and 
 * last names, email address, phone number, and current ranking in the school.
 * The program allows user to add, delete, and edit golfer and dynamically 
 * display them. There is also a search feature that allow user to search for
 * golfer by last name.  User may display golfers by name or rank.
 * 
 * At startup program reads in from a text delimited file and populates an 
 * arrayList and GUI.  There is multiple levels of validation for user inputs.
 * <pre>
 *  Project: Golf Database
 *  Platform: jdk 1.7.0_17; NetBeans IDE 7.3; Windows 7
 *  Coarse: CS 142 Java II
 *  Hours: 50 hours
 *  Created on May 15, 2013
 *  @author Chris
 *  Revised on:
 * </pre>
 * @see:        java.awt.Toolkit
 * @see         java.util.ArrayList
 * @see         java.util.StringTokenizer;
 * @see         java.awt.event.ActionEvent;
 * @see         java.util.logging.Level;
 * @see         java.util.logging.Logger;
 * @see         javax.swing.DefaultListModel;
 * @see         javax.swing.JOptionPane;
 * @see         javax.swing.JDialog;
 * @see         javax.swing.JFileChooser;
 */
public class GolfGUI extends javax.swing.JFrame
{
    
    // class instance ArrayList for Golfer objects    
    protected ArrayList<Golfer> myGolferList = new ArrayList<Golfer>();
    
//    public final int FIRST_NAME = 1;
//    public  final int LAST_NAME  = 2;
//    static int index;
    // Allocates memory for Member and Golfer
    //GolfDataLibrary myGolfData = new GolfDataLibrary();
    Member myMember;
    Golfer myGolfer;
    JFileChooser chooser;
    Sort mySort = new Sort();
    
    // For use in adding new golfers
    public static Golfer tempNewGolfer = new Golfer();
    public static boolean addNewGolferComplete;
    
    
    
    // Declares and instantiates a new listModel for JListField  
    DefaultListModel listModel = new DefaultListModel();
    
    // external file name of cities
    private String fileName = "src/Resources/golfdb.txt";

    /**
     * Creates new form GolfGUI
     */
    public GolfGUI()
    {
        initComponents();
        // class startUp method
        startUp();
        
    }
        
    /**
     * <pre>
     * startUp     
     * This method is called at start of GUI by PugetSoundEnergyGUI().
     * Sets background color
     * Sets the displayBillJButton as default
     * Sets title for application
     * Sets image icon for main GUI
     * Sets the Date used in Arrival Time
     * Loads last parcel saved and displays it in text fields
     * @see java.awt.Toolkit;
     * @see java.awt.event.ActionEvent;
     * @return void
     * @author Chris Steigerwald
     * </pre>
     */
    private void startUp()
    {
        // center form at start.
        setLocationRelativeTo(null);
        // sets title for application
        this.setTitle("Golf Database");
        // Sets the icon image for main GUI
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Resources/golfSplash.jpg")); 
        // Set addJButton as default
        this.getRootPane().setDefaultButton(addJButton);
        // reads from text file
        readFromFile(fileName);
        // displays golfers from text file
        displayGolfer();
        // populates playerJList with golfer
        showGolferData(playerjList.getSelectedIndex());
        //disables all text fields at startup        
        enableJTextFields(false);
    }
    
   
    /**
     * <pre>
     * readFromFile     
     * This method reads from an external file to start myParcelList Array 
     * through a string tokenizer reads from a text file and sends to the 
     * Parcel class and fill the GUI.
     * @param file: String
     * @return void
     * pre-condition: a valid file name, Data.txt is expected
     * for input with comma separated text fields (but no spaces) for
     * ParcelID, Arrival Time, Name, Address, City, Zip, State, StateIndex
     * post-condition: a new Parcel is created with the read fields
     * and added to the myParcelList Parcel.
     * @see java.util.StringTokenizer;
     * @see ReadFile
     * @see Member
     * @date 5/15/2012
     * @author Chris Steigerwald
     * </pre>
     */
    private void readFromFile(String file)
    {
        ReadFile rf = new ReadFile(file);
        String input = rf.readRecord();
        
        String field = "";
        //local variables            
        StringTokenizer st;
        
        while (input != null)       //line at a time
        {
            st = new StringTokenizer(input, ",");
            Golfer tempGolf = new Golfer();
            while (st.hasMoreElements())    // record(field) at a time reads tokens            
            {
                try
                {
                    field = st.nextElement().toString();    //read First Name
                    tempGolf.setFirstName(field);
                    field = st.nextElement().toString();    // read Last Name
                    tempGolf.setLastName(field);
                    field = st.nextElement().toString();    // read Email
                    tempGolf.setEamil(field);
                    field = st.nextElement().toString();    // read Address
                    tempGolf.setPhone(field);
                    field = st.nextElement().toString();    // read Arrival Time
                    tempGolf.setRating(Integer.parseInt(field));
                }
                catch(Exception IO)
                {
                    IO.getMessage();
                }              
            }
            //add golfer to golfer ArrayList
            myGolferList.add(tempGolf);            
            // read next line
            input = rf.readRecord();          
        }       
        rf.close();
    }
    
    /**
     * <pre>
     * Method: newGolfer
     * Sets tempNewMember and addNewMemberComplete with values taken from the
     * AddMemberPrompt.
     * @param tempnewMember: Member to be added to database
     * @param addNewGolferComplete: Boolean y/n if the add member prompt was successful
     * Pre-condition: A valid member object and a boolean value y/n
     * Post-condition: tempNewMember now holds the member object to be added to
     * the database as well as a boolean variable indicating if the adding process
     * was completed.
     * @date 5/15/2012
     * @author Chris Steigerwald
     * </pre>
     */
    public static void newGolfer(Golfer newGolfer, boolean isCompleted)
    {
        tempNewGolfer = newGolfer;
        addNewGolferComplete = isCompleted;
    }
    

    
    /**
     * <pre>
     * Method: clearComponents     
     * This method is called by various methods to clear all text fields in
     * the GUI by setting text to null
     * post-condition: text fields in GUI will be cleared
     * @return void
     * @date 5/15/2012
     * @author Chris Steigerwald
     * </pre>
     */
    private void clearComponents()
    {
        firstJTextField.setText("");
        lastJTextField.setText("");
        emailJTextField.setText("");
        phoneJTextField.setText("");        
        levelJSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, (myGolferList.size() + 1), 1));    
    }
    
   
    /**
     * <pre>
     * Method: displaycitiesList
     * Displays cities in JList sorted by level = 0 using selection sort
     * algorithm or last name = 1 using the insertion sort algorithm.
     * @parem void
     * @return void
     * pre-condition: Uses the ArrayList cities.
     * post-condition: cities ArrayList is sorted and displayed either by
     * level or last name.
     * @see #selectionSort
     * @see #insetionSort
     * @date 5/15/2012
     * @author Chris Steigerwald
     * </pre>
     */
    protected void displayGolfer()
    {
        
        //String[] golferList = new String[myGolfData.myGolfers.size()];
        String[] golferList = new String[myGolferList.size()];
        if ( rankJRadioButtonMenuItem.isSelected())
        {
            // Sort by golfer ranking using selection sort in descending order
            
            mySort.selectionSort(myGolferList);
            for ( int index = 0; index < myGolferList.size(); index++)
            {
                golferList[index] = myGolferList.get(index).getRating() + " ";                
            }
        }
        else
        {
            //sort golfer by name using insertion sort
            mySort.insertionSort(myGolferList);
            for ( int index = 0; index < myGolferList.size(); index++)
            {
                golferList[index] = myGolferList.get(index).getFirstName() + " " +
                        myGolferList.get(index).getLastName() + ", " + 
                        myGolferList.get(index).getRating() + " ";                
            }   
        }
        // displays golfer in GUI
        playerjList.setListData(golferList);
        playerjList.setSelectedIndex(0);
    }

    /**
     * <pre>
     * Method: showGolferData
     * Called by citiesJListValueChanged and many others to refresh the
     * displayed highlighted city
     * @parem int: index of cities to be displayed
     * @return void: 
     * pre-condition: int index in the correct range of [0,array.length - 1]
     * post-condition: city displayed
     * @date 5/15/2012
     * @author Chris Steigerwald
     * </pre>
     */
    private void showGolferData(int index)
    {
        firstJTextField.setText(myGolferList.get(index).getFirstName());       
        lastJTextField.setText(myGolferList.get(index).getLastName());
        emailJTextField.setText(myGolferList.get(index).getEmail());
        phoneJTextField.setText(myGolferList.get(index).getPhone());        
        levelJSpinner.setValue(myGolferList.get(index).getRating());
    }
    
     /**
     * <pre>
     * saveGolfers
     * </pre>
     * This method saves parcel data to an external file that can later
     * be read  back into the program.
     * pre condition: that the array has at least one element
     * post condition: that data.txt is populated with array info
     * @param void
     * @return void
     * @date 5/15/2012
     * @author Chris Steigerwald
     */
    private void saveGolfers()
    {
        try
        {
          WriteFile wf = new WriteFile(fileName);
          Golfer tempGolfer;
          for(int index = 0; index < myGolferList.size(); index++)
          {
              tempGolfer = myGolferList.get(index);
              String output = tempGolfer.getFirstName() + "," + tempGolfer.getLastName()+
                      "," + tempGolfer.getEmail()+ "," + tempGolfer.getPhone() + "," +
                      tempGolfer.getRating() + ",";
              wf.write(output);
          }
          wf.close();   // close the file
        }
        catch(Exception ex)
        {
            Logger.getLogger(Golfer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * <pre>
     * Method: golferExists
     * This method is called before adding a new golfer into database to check
     * if golfer currently exists by looking at golfers; first name, last name,
     * and phone number.  If golfer does not exist boolean expression is returned
     * notifiying program to save new golfer, if golfer does exist boolean expression
     * is returned telling program to not save golfer.
     * preconditions: that all text fields have been validated and packaged
     * into a Golfer object.
     * postconditions: if golfer exists return true, if golfer does not
     * exist in database return false.
     * @parem in: Golfer object from AddFormJDialog
     * @return thereIsOne a boolean expression 
     * @date 5/15/2012
     * @author Chris Steigerwald
     * </pre>
     */
    private boolean golferExists(Golfer newGolfer)
    {
        boolean thereIsOne = false;
        for ( int index = 0; index < myGolferList.size(); index++ )
        {
            if ( myGolferList.get(index).equals(newGolfer))
                thereIsOne = true;
        }
        return thereIsOne;
    }
  

//    //search for name using binary Search
//    public int searchName(String searchName, int lastNameOrFirstName)
//    {
//        String[] golfNameArray = new String[myGolferList.size()];
//        
//        if(lastNameOrFirstName == LAST_NAME)
//        {
//            for (int i = 0; i < golfNameArray.length; i++)
//                {
//                    golfNameArray[i] = myGolferList.get(i).getLastName();
//
//                }
//        }
//        else if(lastNameOrFirstName == FIRST_NAME) 
//        {
//            for (int i = 0; i < golfNameArray.length; i++)
//                {
//                    golfNameArray[i] = myGolferList.get(i).getFirstName();
//
//                }
//        }
//                //Find index of city in newly sorted array of cities
//                //And select the index where it sits
//                
//              //  BinarySearch binSearch = new BinarySearch();
//                return BinarySearch.binarySearch(golfNameArray, searchName);
//    }
    
    /**
     * <pre>
     * Method: getIndex
     * GetIndex method gets the index value from the playerJList and returns
     * an int value to caller, of location in arrayList that selected item is.
     * preconditions: that arrayList is valid
     * postconditions: location of selected item in arrayList is returned
     * @parem void
     * @return int index  
     * @date 5/15/2012
     * @author Chris Steigerwald
     * </pre>
     */
    private int getIndex()
    {
        int index = (playerjList.getSelectedIndex());
        index = (index >= 0 ? index : 0);           
        return index;  
        
    }
    
    /**
     * <pre>
     * Method: enableJTextFields
     * When text fields are disabled, this enable all text fields with a 
     * boolean true expression
     * preconditions: text fields are disabled
     * postconditions: all text fields are enabled
     * @parem boolean expression true
     * @return void
     * @date 5/15/2012
     * @author Chris Steigerwald
     * </pre>
     */
    private void enableJTextFields(boolean status)
    {
        firstJTextField.setEditable(status);
        lastJTextField.setEditable(status);
        emailJTextField.setEditable(status);
        phoneJTextField.setEditable(status);        
        levelJSpinner.setEnabled(status);
        levelJSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, (myGolferList.size()), 1));
        
    }
    
     /**
     * <pre>
     * Method: enableJButtons
     * When JButons are disabled, this enable all JButtons with a 
     * boolean true expression
     * preconditions: JBuutons are disabled
     * postconditions: all JButtons are enabled
     * @parem boolean expression true
     * @return void
     * @date 5/15/2012
     * @author Chris Steigerwald
     * </pre>
     */
    private void enableJButtons(boolean status)
    {
        addJButton.setEnabled(status);
        editJButton.setEnabled(status);
        searchJButton.setEnabled(status);
        deleteJButton.setEnabled(status);
        quickJButton.setEnabled(status);
        addJMenuItem.setEnabled(status);
        deleteJMenuItem.setEnabled(status);
        searchJMenuItem.setEnabled(status);
        aboutJMenuItem.setEnabled(status);
        nameJRadioButtonMenuItem.setEnabled(status);
        rankJRadioButtonMenuItem.setEnabled(status);
        printJMenuItem.setEnabled(status);
        saveJMenuItem.setEnabled(status);
    }
    
    /**
     * <pre>
     * Method: confirmChanges
     * ConfirmChanges is used to send user a JOptionPane requesting if changes
     * are to be made.  If user selects "yes" program will validate all inputs
     * and create a new golfer.  If inputs are not valid user will be prompted
     * that not all fields are competed.  If user selects "no" edit funtion
     * will be cancelled.
     * preconditions: user has filled out proper fields
     * postconditions: a new golfer object will be added
     * @parem void
     * @return void
     * @date 5/15/2012
     * @author Chris Steigerwald
     * </pre>
     */
    private void confirmChanges()
    {
        // ---local variables for holding user input ---
        
        String first = firstJTextField.getText();
        String last = lastJTextField.getText();                
        String email = emailJTextField.getText();         
        String phone = phoneJTextField.getText();
        int rank = Integer.parseInt(levelJSpinner.getValue().toString());
        int index = playerjList.getSelectedIndex();
        int result = JOptionPane.showConfirmDialog(null, "Save changes?", "Update City",
            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        // if  user selects "yes" program will validate input and create new
        // golfer object.  If user selects "no" will go to else.
        if (result == JOptionPane.YES_OPTION)   // Save changes
        {
            // try block to catch number format exception
            try
            {
               if(Validation.checkComplete(first, last, email, phone, rank))
               {
                  // Create the updated city
                    Golfer updatedGolfer = new Golfer(first, last, email, phone, rank);

                    // Delete old city and replace it with a city with new data
                    myGolferList.remove(index);
                    myGolferList.add(updatedGolfer);
                    saveGolfers();
                    displayGolfer();         
               }
               // if all fields are no competed user will be prompted to complete fields
               else 
               {
                    messageJLabel.setVisible(true);
                    messageJLabel.setText("All fields with * must be completed!"); 
               }                        
            }
            catch (NumberFormatException e) // catch block for number format exception
            {
               JOptionPane.showMessageDialog(null, "Golfer not updated", "Input Error",
                   JOptionPane.WARNING_MESSAGE);               
            }            
        }
        else        // Roll back changes
        {
            displayGolfer();
        }
        // display newly added golfer and reset buttons and disable text fields
        playerjList.setSelectedIndex(index);
        enableJButtons(true);
        enableJTextFields(false);           
    }

    protected void changeRating()
    {
       // System.out.println("" + myGolferList.get(getIndex()).getRating());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        sortbuttonGroup = new javax.swing.ButtonGroup();
        titleJPanel = new javax.swing.JPanel();
        logoJLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        informationJPanel = new javax.swing.JPanel();
        firstJLabel = new javax.swing.JLabel();
        firstJTextField = new javax.swing.JTextField();
        lastJLabel = new javax.swing.JLabel();
        lastJTextField = new javax.swing.JTextField();
        emailJLabel = new javax.swing.JLabel();
        emailJTextField = new javax.swing.JTextField();
        phoneJLabel = new javax.swing.JLabel();
        phoneJTextField = new javax.swing.JTextField();
        levelJLabel = new javax.swing.JLabel();
        levelJSpinner = new javax.swing.JSpinner();
        buttonJPanel = new javax.swing.JPanel();
        addJButton = new javax.swing.JButton();
        editJButton = new javax.swing.JButton();
        deleteJButton = new javax.swing.JButton();
        searchJButton = new javax.swing.JButton();
        quickJButton = new javax.swing.JButton();
        displayJPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playerjList = new javax.swing.JList();
        messageJPanel = new javax.swing.JPanel();
        messageJLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        printJMenuItem = new javax.swing.JMenuItem();
        saveJMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitJMenuItem = new javax.swing.JMenuItem();
        sortJMenu = new javax.swing.JMenu();
        nameJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        rankJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        actionJMenu = new javax.swing.JMenu();
        addJMenuItem = new javax.swing.JMenuItem();
        deleteJMenuItem = new javax.swing.JMenuItem();
        editJMenuItem = new javax.swing.JMenuItem();
        searchJMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        logoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/golfLogo.jpg"))); // NOI18N

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titleLabel.setText("Golf Database");

        javax.swing.GroupLayout titleJPanelLayout = new javax.swing.GroupLayout(titleJPanel);
        titleJPanel.setLayout(titleJPanelLayout);
        titleJPanelLayout.setHorizontalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoJLabel)
                .addGap(74, 74, 74)
                .addComponent(titleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titleJPanelLayout.setVerticalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleJPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addGap(39, 39, 39))
        );

        firstJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        firstJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstJLabel.setText("First Name *:");

        firstJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        firstJTextField.setToolTipText("Press enter to update");
        firstJTextField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                firstJTextFieldActionPerformed(evt);
            }
        });

        lastJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lastJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastJLabel.setText("Last Name *:");

        lastJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lastJTextField.setToolTipText("Press enter to update");
        lastJTextField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                lastJTextFieldActionPerformed(evt);
            }
        });

        emailJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        emailJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        emailJLabel.setText("Email Address:");

        emailJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        emailJTextField.setToolTipText("Press enter to update");
        emailJTextField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                emailJTextFieldActionPerformed(evt);
            }
        });

        phoneJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        phoneJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        phoneJLabel.setText("Phone Number *:");

        phoneJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        phoneJTextField.setToolTipText("Press enter to update");
        phoneJTextField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                phoneJTextFieldActionPerformed(evt);
            }
        });

        levelJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        levelJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        levelJLabel.setText("Level *:");

        levelJSpinner.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        levelJSpinner.setToolTipText("Choose golfers level");

        javax.swing.GroupLayout informationJPanelLayout = new javax.swing.GroupLayout(informationJPanel);
        informationJPanel.setLayout(informationJPanelLayout);
        informationJPanelLayout.setHorizontalGroup(
            informationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationJPanelLayout.createSequentialGroup()
                .addComponent(firstJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(firstJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(informationJPanelLayout.createSequentialGroup()
                .addComponent(lastJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(lastJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(informationJPanelLayout.createSequentialGroup()
                .addComponent(emailJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(emailJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(informationJPanelLayout.createSequentialGroup()
                .addComponent(phoneJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(phoneJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(informationJPanelLayout.createSequentialGroup()
                .addComponent(levelJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(levelJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        informationJPanelLayout.setVerticalGroup(
            informationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationJPanelLayout.createSequentialGroup()
                .addGroup(informationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(informationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(informationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(informationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(informationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(levelJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(levelJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        buttonJPanel.setLayout(new java.awt.GridLayout(1, 5, 5, 0));

        addJButton.setMnemonic('a');
        addJButton.setText("Add");
        addJButton.setToolTipText("Click to add golfer");
        addJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addJButtonActionPerformed(evt);
            }
        });
        buttonJPanel.add(addJButton);

        editJButton.setMnemonic('e');
        editJButton.setText("Edit");
        editJButton.setToolTipText("Click to edit golfer");
        editJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editJButtonActionPerformed(evt);
            }
        });
        buttonJPanel.add(editJButton);

        deleteJButton.setMnemonic('d');
        deleteJButton.setText("Delete");
        deleteJButton.setToolTipText("Click to delete golfer");
        deleteJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteJButtonActionPerformed(evt);
            }
        });
        buttonJPanel.add(deleteJButton);

        searchJButton.setMnemonic('s');
        searchJButton.setText("Search");
        searchJButton.setToolTipText("Click to search for golfer");
        searchJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                searchJButtonActionPerformed(evt);
            }
        });
        buttonJPanel.add(searchJButton);

        quickJButton.setMnemonic('q');
        quickJButton.setText("Quit");
        quickJButton.setToolTipText("click to quit program");
        quickJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                quickJButtonActionPerformed(evt);
            }
        });
        buttonJPanel.add(quickJButton);

        displayJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Players"));

        playerjList.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        playerjList.setToolTipText("Click to select golfer");
        playerjList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                playerjListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(playerjList);

        javax.swing.GroupLayout displayJPanelLayout = new javax.swing.GroupLayout(displayJPanel);
        displayJPanel.setLayout(displayJPanelLayout);
        displayJPanelLayout.setHorizontalGroup(
            displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
            .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayJPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        displayJPanelLayout.setVerticalGroup(
            displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(displayJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayJPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        messageJLabel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        messageJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout messageJPanelLayout = new javax.swing.GroupLayout(messageJPanel);
        messageJPanel.setLayout(messageJPanelLayout);
        messageJPanelLayout.setHorizontalGroup(
            messageJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(messageJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(messageJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
        );
        messageJPanelLayout.setVerticalGroup(
            messageJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 62, Short.MAX_VALUE)
            .addGroup(messageJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(messageJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
        );

        fileJMenu.setText("File");

        printJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        printJMenuItem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        printJMenuItem.setText("Print");
        printJMenuItem.setToolTipText("Click to print");
        printJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                printJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printJMenuItem);

        saveJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveJMenuItem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        saveJMenuItem.setText("Save");
        saveJMenuItem.setToolTipText("Click to save ");
        saveJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(saveJMenuItem);
        fileJMenu.add(jSeparator1);

        exitJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitJMenuItem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        exitJMenuItem.setText("Exit");
        exitJMenuItem.setToolTipText("Click to exit");
        fileJMenu.add(exitJMenuItem);

        jMenuBar1.add(fileJMenu);

        sortJMenu.setText("Sort");

        sortbuttonGroup.add(nameJRadioButtonMenuItem);
        nameJRadioButtonMenuItem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        nameJRadioButtonMenuItem.setSelected(true);
        nameJRadioButtonMenuItem.setText("Name");
        nameJRadioButtonMenuItem.setToolTipText("Sort by name");
        nameJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                nameJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        sortJMenu.add(nameJRadioButtonMenuItem);

        sortbuttonGroup.add(rankJRadioButtonMenuItem);
        rankJRadioButtonMenuItem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        rankJRadioButtonMenuItem.setText("Rank");
        rankJRadioButtonMenuItem.setToolTipText("Sort by rank");
        rankJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                rankJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        sortJMenu.add(rankJRadioButtonMenuItem);

        jMenuBar1.add(sortJMenu);

        actionJMenu.setText("Action");

        addJMenuItem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        addJMenuItem.setText("Add New Golfer");
        addJMenuItem.setToolTipText("Add new golfer");
        addJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(addJMenuItem);

        deleteJMenuItem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        deleteJMenuItem.setText("Delete Golfer");
        deleteJMenuItem.setToolTipText("Delete Golfer");
        deleteJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(deleteJMenuItem);

        editJMenuItem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        editJMenuItem.setText("Edit Golfer");
        editJMenuItem.setToolTipText("Edit golfer");
        editJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(editJMenuItem);

        searchJMenuItem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        searchJMenuItem.setText("Search");
        searchJMenuItem.setToolTipText("Search for golfer");
        searchJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                searchJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(searchJMenuItem);

        jMenuBar1.add(actionJMenu);

        helpMenu.setText("Help");

        aboutJMenuItem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        aboutJMenuItem.setText("About");
        aboutJMenuItem.setToolTipText("About the program");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                aboutJMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutJMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(informationJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(messageJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(informationJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(buttonJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(displayJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_nameJRadioButtonMenuItemActionPerformed
    {//GEN-HEADEREND:event_nameJRadioButtonMenuItemActionPerformed
        // Displays golfer sorted by last name
        displayGolfer();
    }//GEN-LAST:event_nameJRadioButtonMenuItemActionPerformed

    private void rankJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_rankJRadioButtonMenuItemActionPerformed
    {//GEN-HEADEREND:event_rankJRadioButtonMenuItemActionPerformed
        // Displays golfers based on rank in ascending order
        displayGolfer();
    }//GEN-LAST:event_rankJRadioButtonMenuItemActionPerformed

    private void playerjListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_playerjListValueChanged
    {//GEN-HEADEREND:event_playerjListValueChanged
        /**
         * When user selects golfer in the jListBox it has an event handler
         * that will display golfer info in text fields.
         */
        showGolferData(getIndex());        
    }//GEN-LAST:event_playerjListValueChanged

    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addJButtonActionPerformed
    {//GEN-HEADEREND:event_addJButtonActionPerformed
        /**
         * When user clicks add button a new JDialog frame is instantiated 
         * allowing the user to input a new golfer or member.  The AddFormJDialog
         * validates user input before passing it back to create new golfer.
         * If golfer is already in database user will be notified via an 
         * option pane the golfer already exists.
         */
        JDialog addDialog = new AddFormJDialog(this, true);
        addDialog.setVisible(true);
        if (addNewGolferComplete)
        {
            if (!(golferExists(tempNewGolfer)))
            {
                myGolferList.add(tempNewGolfer);
                displayGolfer();
                saveGolfers();;
                // Grab the name of the new golfer and create an array of
                // all the other golfers
//                String golferName = tempNewGolfer.getLastName();
                //int index = searchName (golferName, LAST_NAME);
                playerjList.setSelectedIndex(getIndex());
            }
            else    //golfer already exists
            {
                JOptionPane.showMessageDialog(null, "Golfer already exists!", "Error",
                    JOptionPane.WARNING_MESSAGE);
                playerjList.setSelectedIndex(0);
            }

            }
        
    }//GEN-LAST:event_addJButtonActionPerformed

    private void addJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addJMenuItemActionPerformed
    {//GEN-HEADEREND:event_addJMenuItemActionPerformed
        // Call the addJButton
        addJButtonActionPerformed(evt);
    }//GEN-LAST:event_addJMenuItemActionPerformed

    private void searchJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_searchJMenuItemActionPerformed
    {//GEN-HEADEREND:event_searchJMenuItemActionPerformed
        // Searches for Golpher by last name
        searchJButtonActionPerformed(evt);
    }//GEN-LAST:event_searchJMenuItemActionPerformed

    private void searchJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_searchJButtonActionPerformed
    {//GEN-HEADEREND:event_searchJButtonActionPerformed
        // By pressing search button brings up search serachJDialogBox
        
        JDialog searchDialog = new SearchFormDialog(this, true);
        searchDialog.setVisible(true);
    }//GEN-LAST:event_searchJButtonActionPerformed

    private void quickJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_quickJButtonActionPerformed
    {//GEN-HEADEREND:event_quickJButtonActionPerformed
        // Exits program
        System.exit(0);
    }//GEN-LAST:event_quickJButtonActionPerformed

    private void deleteJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteJButtonActionPerformed
    {//GEN-HEADEREND:event_deleteJButtonActionPerformed
         // User can delete currently selected golfer
        int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete Golfer",
            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (result == JOptionPane.YES_OPTION)  //Confirmed with yes for deleting selected city
        {
           int index = playerjList.getSelectedIndex();            
            myGolferList.remove(index);
            displayGolfer();
            saveGolfers();
        }
        
    }//GEN-LAST:event_deleteJButtonActionPerformed

    private void editJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editJButtonActionPerformed
    {//GEN-HEADEREND:event_editJButtonActionPerformed
        /**
         * When user clicks edit button it disables all button and and menu items
         * not associated with editing a golfer.  All text fields are enabled
         * so user can edit
         */
        enableJTextFields(true);
        firstJTextField.requestFocus();
        firstJTextField.selectAll();
        
        addJButton.setEnabled(false);
        searchJButton.setEnabled(false);
        deleteJButton.setEnabled(false);
        quickJButton.setEnabled(false);
        addJMenuItem.setEnabled(false);
        deleteJMenuItem.setEnabled(false);
        searchJMenuItem.setEnabled(false);
        aboutJMenuItem.setEnabled(false);
        nameJRadioButtonMenuItem.setEnabled(false);
        rankJRadioButtonMenuItem.setEnabled(false);
        printJMenuItem.setEnabled(false);
        saveJMenuItem.setEnabled(false);
        
        
    }//GEN-LAST:event_editJButtonActionPerformed

    private void firstJTextFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_firstJTextFieldActionPerformed
    {//GEN-HEADEREND:event_firstJTextFieldActionPerformed
         /**
         * When edit is selected this method will read in the golfers first name
         * and send it to the validation class validation.
         *  If validated user will be prompted to save info.  If 
         * invalid user will be displayed a message stating invalid text.
         * When users presses enter user is prompted to save.
         */
        String first = firstJTextField.getText();
        if (Validation.validName(first) == false)
        {
            firstJTextField.setText("");
            messageJLabel.setVisible(true);
            messageJLabel.setText("Invalid text, use only letters!");
        }
        else
        {
            messageJLabel.setVisible(false);
            messageJLabel.setText("");
            confirmChanges();
        }
        
    }//GEN-LAST:event_firstJTextFieldActionPerformed

    private void lastJTextFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_lastJTextFieldActionPerformed
    {//GEN-HEADEREND:event_lastJTextFieldActionPerformed
        /**
         * When edit is selected this method will read in the golfers last name
         * and send it to the validation class validation.
         *  If validated user will be prompted to save info.  If 
         * invalid user will be displayed a message stating invalid text.
         * When users presses enter user is prompted to save.
         */
        String last = lastJTextField.getText();
        if (Validation.validName(last) == false)
        {
            lastJTextField.setText("");
            messageJLabel.setVisible(true);
            messageJLabel.setText("Invalid text, use only letters!");
        }
        else
        {
            messageJLabel.setVisible(false);
            messageJLabel.setText("");
            confirmChanges();
        }
    }//GEN-LAST:event_lastJTextFieldActionPerformed

    private void emailJTextFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emailJTextFieldActionPerformed
    {//GEN-HEADEREND:event_emailJTextFieldActionPerformed
        /**
         * When edit is selected this method will read in the email address
         * and if it is not blank will send it to the validation class to be
         * validated.  If validated user will be prompted to save info.  If 
         * invalid user will be displayed a message stating invalid email.
         * When users presses enter user is prompted to save.
         */
        String email = emailJTextField.getText();
        if(email.equals(""))
        {
            confirmChanges();            
        }
        else
        {
            if (Validation.checkEmail(email) == false)
            {
                emailJTextField.setText(" ");
                //emailJTextField.selectAll();           
                messageJLabel.setVisible(true);
                messageJLabel.setText("Invalid Email, please try again!");
            }
            else
            {
                messageJLabel.setVisible(false);
                messageJLabel.setText("");
                emailJTextField.setText(email);   
                confirmChanges();
            }        
        }
        
        
    }//GEN-LAST:event_emailJTextFieldActionPerformed

    private void phoneJTextFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_phoneJTextFieldActionPerformed
    {//GEN-HEADEREND:event_phoneJTextFieldActionPerformed
        /**
         * When edit is selected this method will read in the phone number
         * and will send it to the validation class to be for validation.
         *  If validated user will be prompted to save info.  If 
         * invalid user will be displayed a message stating invalid phone number.
         * phone replace code generates phone number into format with hyphens
         * so it will save uniformly with all other input.
         * When users presses enter user is prompted to save.
         */
        String phone = phoneJTextField.getText();        
        
        if(Validation.isPhone(phone) == false)
        {
            messageJLabel.setVisible(true);
            messageJLabel.setText("Invalid phone number, please try again!");            
        }
        else
        {
            messageJLabel.setVisible(false);
            messageJLabel.setText("");
            phone = phone.replaceFirst("^(\\d{3})(\\d{3})(\\d{4})$", "$1-$2-$3");
            phoneJTextField.setText(phone);
            confirmChanges();
        }
    }//GEN-LAST:event_phoneJTextFieldActionPerformed

    private void deleteJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteJMenuItemActionPerformed
    {//GEN-HEADEREND:event_deleteJMenuItemActionPerformed
        // call delete button so user can delete golfers
        deleteJButtonActionPerformed(evt);
    }//GEN-LAST:event_deleteJMenuItemActionPerformed

    private void editJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editJMenuItemActionPerformed
    {//GEN-HEADEREND:event_editJMenuItemActionPerformed
        // Call edit button so user can edit golfers
        editJButtonActionPerformed(evt);
    }//GEN-LAST:event_editJMenuItemActionPerformed

    private void printJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_printJMenuItemActionPerformed
    {//GEN-HEADEREND:event_printJMenuItemActionPerformed
        // Print a screen shot of program, calls PrintUtilities class
        try
        {
            //Prints the GUI
            PrintUtilities.printComponent(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }        
    }//GEN-LAST:event_printJMenuItemActionPerformed

    private void saveJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveJMenuItemActionPerformed
    {//GEN-HEADEREND:event_saveJMenuItemActionPerformed
        // Saves golfers to disk
        saveGolfers();
    }//GEN-LAST:event_saveJMenuItemActionPerformed

    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_aboutJMenuItemActionPerformed
    {//GEN-HEADEREND:event_aboutJMenuItemActionPerformed
        // Dispalys about form
        AboutForm aboutWindow = new AboutForm();
        aboutWindow.setVisible(true);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        // Instantiates splash screen
        // Splash screen set to visible for 2000 milliseconds
        // After 2000 milliseconds GUI is instantiated.
        Splash mySplash = new Splash(0000);
        mySplash.showSplash();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(GolfGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(GolfGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(GolfGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(GolfGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new GolfGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JMenu actionJMenu;
    private javax.swing.JButton addJButton;
    private javax.swing.JMenuItem addJMenuItem;
    private javax.swing.JPanel buttonJPanel;
    private javax.swing.JButton deleteJButton;
    private javax.swing.JMenuItem deleteJMenuItem;
    private javax.swing.JPanel displayJPanel;
    private javax.swing.JButton editJButton;
    private javax.swing.JMenuItem editJMenuItem;
    private javax.swing.JLabel emailJLabel;
    private javax.swing.JTextField emailJTextField;
    private javax.swing.JMenuItem exitJMenuItem;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JLabel firstJLabel;
    private javax.swing.JTextField firstJTextField;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel informationJPanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lastJLabel;
    private javax.swing.JTextField lastJTextField;
    private javax.swing.JLabel levelJLabel;
    private javax.swing.JSpinner levelJSpinner;
    private javax.swing.JLabel logoJLabel;
    private javax.swing.JLabel messageJLabel;
    private javax.swing.JPanel messageJPanel;
    private javax.swing.JRadioButtonMenuItem nameJRadioButtonMenuItem;
    private javax.swing.JLabel phoneJLabel;
    private javax.swing.JTextField phoneJTextField;
    private javax.swing.JList playerjList;
    private javax.swing.JMenuItem printJMenuItem;
    private javax.swing.JButton quickJButton;
    private javax.swing.JRadioButtonMenuItem rankJRadioButtonMenuItem;
    private javax.swing.JMenuItem saveJMenuItem;
    private javax.swing.JButton searchJButton;
    private javax.swing.JMenuItem searchJMenuItem;
    private javax.swing.JMenu sortJMenu;
    private javax.swing.ButtonGroup sortbuttonGroup;
    private javax.swing.JPanel titleJPanel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
