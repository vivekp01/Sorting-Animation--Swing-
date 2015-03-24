import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.SwingUtilities.*;


/***************************************************************
Class: SortApplication

Use:  Create the GUI window for the sort application.
	  This class also runs the program using the methods from other classes
Arguments: N/A

Returns: N/A
***************************************************************/
public class SortApplication extends JFrame
{	
	//Two sort panel objects
	SortPanel panelA = new SortPanel();
	SortPanel panelB = new SortPanel();
	
	
	/***************************************************************
	Method: main

	Use:  Create the GUI window and set it's peramaters

	Arguments: String

	Returns: N/A
	***************************************************************/
	public static void main( String[] args )
	{
		//Create a class object (window)
		SortApplication sortApp  = new SortApplication();
		
		//GUI Window Information
		sortApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sortApp.setSize(1100,600);
		sortApp.setLayout(new GridLayout(1,2));
		sortApp.setTitle("Sorting Animation");
		sortApp.setVisible(true);
		sortApp.setResizable(true);
		
	}

	/***************************************************************
	Constructor: Default
	
	Use:  Add the sort panel objects to the GUI window
	
	Arguments: N/A
	
	Returns: N/A
	***************************************************************/
	public SortApplication()
	{	
		//Add the sort panels
		this.add(panelA);
		this.add(panelB);
	}
	
		
}


	