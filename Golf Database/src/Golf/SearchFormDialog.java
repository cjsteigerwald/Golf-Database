package Golf;

import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


/**
 * SearchFormDialog.java
 * SearchFormDialog class is used to search for an existing golfer in the 
 * database.  When the user selects search from the GUI a JDialog form is 
 * created that allows the user to input a golfer last name.  As the user types
 * the name the JList field is constantly updated narrowing down the golfers
 * until one is found.  Unfortunately at this time the user is not able to 
 * select the golfer and have it dynamically display in GUI.
 * <pre>
 *  Project: Golf Database
 *  Platform: jdk 1.7.0_17; NetBeans IDE 7.3; Windows 7
 *  Coarse: CS 142 Java II
 *  Hours: 6 hours
 *  Created on May 15, 2013
 *  @author Chris Steigerwald
 *  Revised on:
 * </pre>
 * @see     java.awt.Toolkit
 * @see         java.util.ArrayList
 * @see         javax.swing.DefaultListModel;
 * @see         javax.swing.JOptionPane;
 */
public class SearchFormDialog extends javax.swing.JDialog
{
    int index;
    GolfGUI myGUI = new GolfGUI();
    
    Golfer newGolfer;
    ArrayList<Golfer> myGolf;
    DefaultListModel listModel = new DefaultListModel();    // declares and instantiates new listModel for JListField    
    String[] golferSearch;
    public SearchFormDialog(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/Resources/golfdb.txt"));
        setLocationRelativeTo(null);
        this.setTitle("Search Golf Database");
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

        titleJPanel = new javax.swing.JPanel();
        titleJLabel = new javax.swing.JLabel();
        instructionJPanel = new javax.swing.JPanel();
        instructionJLabel1 = new javax.swing.JLabel();
        controlJPanel = new javax.swing.JPanel();
        exiteJButton = new javax.swing.JButton();
        textJPanel = new javax.swing.JPanel();
        lastJLabel = new javax.swing.JLabel();
        lastSearchJTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        searchJList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        titleJLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleJLabel.setText("Golf Database Search");
        titleJPanel.add(titleJLabel);

        instructionJLabel1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        instructionJLabel1.setText("Search for by last name");
        instructionJPanel.add(instructionJLabel1);

        controlJPanel.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        exiteJButton.setMnemonic('x');
        exiteJButton.setText("Exit");
        exiteJButton.setToolTipText("Press to exit");
        exiteJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                exiteJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(exiteJButton);

        lastJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lastJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastJLabel.setText("Enter last name:");

        lastSearchJTextField.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                lastSearchJTextFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout textJPanelLayout = new javax.swing.GroupLayout(textJPanel);
        textJPanel.setLayout(textJPanelLayout);
        textJPanelLayout.setHorizontalGroup(
            textJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textJPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lastJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastSearchJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );
        textJPanelLayout.setVerticalGroup(
            textJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textJPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(textJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastSearchJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastJLabel)))
        );

        jScrollPane2.setToolTipText("Click on golfer to display");

        searchJList.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        searchJList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                searchJListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(searchJList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(instructionJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(textJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instructionJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exiteJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_exiteJButtonActionPerformed
    {//GEN-HEADEREND:event_exiteJButtonActionPerformed
        // Closes search JDialog
        this.dispose();
    }//GEN-LAST:event_exiteJButtonActionPerformed

    private void lastSearchJTextFieldKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_lastSearchJTextFieldKeyReleased
    {//GEN-HEADEREND:event_lastSearchJTextFieldKeyReleased
        /**
         * This method uses key pressed event to search for golfer by last 
         * name.  It iterates through the arrayList dynamically checking
         * for starts with match last name.  Unfortunately I was unable to
         * get the golfer to display in GUI.
         */

        listModel.removeAllElements();        
        String key = lastSearchJTextField.getText();  // dynmically gets last key typed
        boolean found = false;
        int index = 0; 
        
            for ( int i = 0; i < myGUI.myGolferList.size(); i++)                
            {
                index = i;                 
                String last = myGUI.myGolferList.get(i).getLastName();
                if (last.startsWith(key))
                {
                    found = true;
                    String temp = myGUI.myGolferList.get(index).getLastName() + ", " +
                            myGUI.myGolferList.get(index).getFirstName() + " ";
                    listModel.addElement("" + temp);                                
                    searchJList.setModel(listModel);                 
                }
                else{}          
            }
            
            if (found == false)
            {
                JOptionPane.showMessageDialog(null, key + " not Found in database", "Search Reslut",
                            JOptionPane.WARNING_MESSAGE);
            }
            else{}      
               
    }//GEN-LAST:event_lastSearchJTextFieldKeyReleased

    private void searchJListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_searchJListValueChanged
    {//GEN-HEADEREND:event_searchJListValueChanged
        // Tried to get selected index value to send to arrayList, but this
        // didn't work to display golfers as selected index is only of that
        // in the JList field.
        this.index = (searchJList.getSelectedIndex());
        index = (index >= 0 ? index : 0);
        System.out.println("" + searchJList.getSelectedValue().toString());
        
    }//GEN-LAST:event_searchJListValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
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
            java.util.logging.Logger.getLogger(SearchFormDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(SearchFormDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(SearchFormDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(SearchFormDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                SearchFormDialog dialog = new SearchFormDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controlJPanel;
    private javax.swing.JButton exiteJButton;
    private javax.swing.JLabel instructionJLabel1;
    private javax.swing.JPanel instructionJPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lastJLabel;
    private javax.swing.JTextField lastSearchJTextField;
    private javax.swing.JList searchJList;
    private javax.swing.JPanel textJPanel;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JPanel titleJPanel;
    // End of variables declaration//GEN-END:variables
}