# Golf-Database
A GUI implementation of a Golf Database
Edmonds Community College CS 142
Completed 5/15/2013

<b>Program is in dist file: Golf-Database.jar</b>

<strong><bold>Problem</strong><bold><br><br>
<p>A golf coach has asked for a quick and easy program to keep track of
all the players on the team.  Currently no such program exists and it is difficult to
contact golf players and to place them with appropriate ranking.</p>
<p>Write a Java program that allows the user to manage a simple golf database with
ability to display, add, edit/modify, and delete members of the golf team using a GUI</P>

<strong><bold>Solution</strong><bold><br><br>
<OL>
<LI>A Member class that contains in order at least first name, last name, email, phone and
level (team rating of 1-5, with 1 being the top seed).
<LI>Read and display the information about the members in a GUI-based driver. The back end: 
is a comma delimited text file.

<LI>Ability to add new members, modify existing ones, delete and search members. Checks
should be performed not to add duplicate members, and that all of the 4 required fields
(indicated with an asterisk (*) in the provided GUI) are valid and included when
adding/modifying a member. Note that email is not a required field; all the rest are
required.

<LI>An ArrayList is the data structure of Members to hold the members data.

<LI>Javadocs, description of the program, and comments.

<LI>Menus that synchronize with corresponding buttons and with at least the following menu
choices:
<UL>
<LI>File with New, Open, Save, Save As, Clear, Print, and Exit menu items.

<LI>Sort with Sort by Last Name and Sort by Rank menu items.

<LI>Action with Add, Delete, Edit (with choice to save or cancel changes), and Search
menu items.

<LI>Help with About menu item for an About form.

<LI>Splash Screen that closes itself after so many seconds and
it contains an About form activated from the Help menu.

<LI>Display the members sorted by last name or sorted by rank.

<LI>Well designed and efficient GUI with images and ease to use.

<LI>Print the information for a selected member.

<LI>separate forms for adding/editing members to the database.

