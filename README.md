# Golf-Database

<table><tr><td>
<a href="https://cloud.githubusercontent.com/assets/9287625/7329625/262f45f8-ea96-11e4-8cf4-b2f9c7c70e6b.jpg" target="_blank">
<img src="https://cloud.githubusercontent.com/assets/9287625/7329622/13dd4832-ea96-11e4-87f5-431864ce6c6c.jpg" width="100" height="131" border="0" alt="Thumbnail 1" /></a></td><td>

<a href="https://cloud.githubusercontent.com/assets/9287625/7329635/375f0232-ea96-11e4-9ed1-df9dae32b59c.jpg" target="_blank">
<img src="https://cloud.githubusercontent.com/assets/9287625/7329641/442d8038-ea96-11e4-963c-48c1b3445141.jpg" width="100" height="131" border="0" alt="Thumbnail 2" /></a></td><td>

<a href="https://cloud.githubusercontent.com/assets/9287625/7329647/4f1583b0-ea96-11e4-9fbd-74d5e902a86c.jpg" target="_blank">
<img src="https://cloud.githubusercontent.com/assets/9287625/7329651/593233b6-ea96-11e4-9863-a937d9fdf10b.jpg" width="100" height="131" border="0" alt="Thumbnail 3" /></a></td></tr></table><br>
A GUI implementation of a Golf Database
Edmonds Community College CS 142
Completed 5/15/2013

<b>Project file located: <a href="https://github.com/cjsteigerwald/Golf-Database/blob/master/Golf%20Database/dist/Golf_Database.jar">Here</a></b>

<strong><bold>Problem</strong><bold><br><br>
<p>A golf coach has asked for a quick and easy program to keep track of
all the players on the team.  Currently no such program exists and it is difficult to
contact golf players and to place them with appropriate ranking.</p>
<p>Write a Java program that allows the user to manage a simple golf database with
ability to display, add, edit/modify, and delete members of the golf team using a GUI</P>

<strong><bold>Solution</strong><bold><br><br>
<OL>
<LI>A <a href="https://github.com/cjsteigerwald/Golf-Database/blob/master/Golf%20Database/src/Golf/Member.java">Member</a> class that contains in order at least first name, last name, email, phone and a <a href="https://github.com/cjsteigerwald/Golf-Database/blob/master/Golf%20Database/src/Golf/Golfer.java">Golfer</a> class that extends from Member and adds level (team rating of 1-5, with 1 being the top seed).
<LI><a href="https://github.com/cjsteigerwald/Golf-Database/blob/master/Golf%20Database/src/Golf/ReadFile.java">Read</a> and display the information about the members in a <a href="https://github.com/cjsteigerwald/Golf-Database/blob/master/Golf%20Database/src/Golf/GolfGUI.java">GUI-based driver.</a> The back end: 
is a comma delimited text file.

<LI>Ability to <a href="https://github.com/cjsteigerwald/Golf-Database/blob/master/Golf%20Database/src/Golf/AddFormJDialog.java">add new members</a>, modify existing ones, delete and search members. Checks
should be performed not to add duplicate members, and that all of the 4 required fields
(indicated with an asterisk (*) in the provided GUI) are valid and included when
adding/modifying a member. Note that email is not a required field; all the rest are
required.

<LI>An ArrayList is the data structure of Members to hold the members data.

<LI><a href="https://github.com/cjsteigerwald/Golf-Database/tree/master/Golf%20Database/dist/javadoc">Javadocs</a>, description of the program, and comments.

<LI>Menus that synchronize with corresponding buttons and with at least the following menu
choices:
<UL>
<LI>File with New, Open, <a href="https://github.com/cjsteigerwald/Golf-Database/blob/master/Golf%20Database/src/Golf/WriteFile.java">Save</a>, Save As, Clear, <a href="https://github.com/cjsteigerwald/Golf-Database/blob/master/Golf%20Database/src/Golf/PrintUtilities.java">Print</a>, and Exit menu items.

<LI><a href="https://github.com/cjsteigerwald/Golf-Database/blob/master/Golf%20Database/src/Golf/Sort.java">Sort</a> with Sort by Last Name and Sort by Rank menu items.

<LI>Action with Add, Delete, Edit (with choice to save or cancel changes), and Search
menu items.

<LI>Help with About menu item for an About form.

<LI>Splash Screen that closes itself after so many seconds and
it contains an About form activated from the Help menu.

<LI>Display the members sorted by last name or sorted by rank.

<LI><a href="https://github.com/cjsteigerwald/Golf-Database/blob/master/Golf%20Database/src/Golf/SearchFormDialog.form">Search Form</a> for a member with dialogue box that updates as user enters information.

<LI>Well designed and efficient <a href="https://github.com/cjsteigerwald/Golf-Database/blob/master/Golf%20Database/src/Golf/GolfGUI.java">GUI</a> with images and ease to use.

<LI>Print the information for a selected member.

<LI>separate forms for adding/editing members to the database.

