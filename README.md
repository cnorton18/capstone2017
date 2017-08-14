# capstone2017
Team C: CS Capstone 2017
For now, view properly formated instructions under with Raw. 

                                      Map Entry Instructions
I. Adding a Building 

II. Adding a Floor to an Existing Building
	1. Navigate to ...\OfficeLocator\app\src\main\assets\data.xml
	2. Locate desired building; floors are a child element of a building, and should be placed 
	   immediately under the opening tag for the appropriate building, or under the closing 
	   tag of prior floor of that building.  
	3. The open tag for a floor is <floor level = "#" src="filename">
		i. Attribute floor level requires the floor number, in double quotes.
		ii. Atribute src requires the filename for the floor layout, in double quotes.
			a. Expected style for floor names is the name of the building in all lower case, 
			   followed by the floor number. There should be no spaces. 
		iii. Ex. <floor level = "1" src = "floorname1">
	4. The closing tag for a floor is <\floor>
	5. Navigate to ...\OfficeLocator\app\src\main\res\layout
	6. Create a copy of the file activity_floorplan.xml in the directory; name
           the new file so as to match the filename from step 3ii, adding an .xml extension. 
		i. Ex. floorname1.xml 
	7. Open the created file, and navigate to design view. 
	8. Select the floorPlanimage (ID of the image will be floorPlanImage, viewable in Properties). 
	9. In Properties, edit srcCompat view to read: drawable/imagename
		i. imagename should match that entered in 3iii. Ex. floorname1
	10. Navigate to ...\OfficeLocator\app\src\main\res\drawable
	11. Add the image file for the floor, matching the naming convention of 3iii. 
	 
III. Adding a Room to an Existing Floor  
	1. Navigate to ...\OfficeLocator\app\src\main\assets\data.xml
	2. Locate desired building and floor; rooms are a child element of a floor. 
	3. The tag for a room is <room roomName="Room Name"/>
		i. Attribute room name should be written as one wishes to display, including 
		   following normal capitalization and spacing conventions, in double quotes. 
		ii. Ex. <room roomName="Awesome Room/>
	4. Navigate to ...\OfficeLocator\app\src\main\res\layout
	5. Locate the desired floor layout .xml file in which your room is to be placed. 
	6. Open the file and navigate to design view. 
	7. Use the Palette to drag and drop an ImageView onto the location of the room; this 
	   image will appear as a marker in the application when the room is selected.  
	8. When given the option, select the desired marker image from 
           ...\OfficeLocator\app\src\main\res\drawable
        9. In Properties, edit the ID field of the ImageView and add the name of the room, 
           all in lower case, with no spaces. 
        	i. Ex. awesomeroom
	10. In Properties, set visiblity of the ImageView to invisible. 
