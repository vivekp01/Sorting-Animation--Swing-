import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;
import javax.swing.event.*;
import javax.swing.SwingUtilities.*;
import java.lang.Runnable;
import java.util.Random;
import java.lang.Object;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;

/***************************************************************
Class: SortPanel

Use:  JPanel for displaying the sort components.

Arguments: N/A

Returns: N/A
***************************************************************/
public class SortPanel extends JPanel
{	
	//Sort animation panel
	private SortAnimationPanel amimationPanel = new SortAnimationPanel();
	
	//Array full button
	private JButton fillArray = new JButton("Populate Array");
	
	//Sort button
	private JButton sortButton = new JButton("Sort");
	
	//Stop button to stop sorting entirely
	private JButton stopButton = new JButton("Stop");
	
	//Array for the sort types
	private String[] sortMethods = { "Selection", "Quick", "Bubble", "Cocktail", "Shell", "Insertion", "Gnome", "Cycle"};
	
	//Combo box to list sort types
	private JComboBox sortTypes = new JComboBox<>(sortMethods);
	
	//Array for the sort types
	private String[] initialArray = { "Random", "Ascending", "Descending"};
	
	//Combo box to list sort types
	private JComboBox initialOrdering = new JComboBox<>(initialArray);
	
	//Integer array to be sorted
	private int[] integerArray = new int[525];
	
	//Speed integer
	int speed = 500; 
	
	//Array for the speeds
	private String[] speedArray = { "Slow", "Medium", "Fast"};
	
	//Combo box to list various speeds
	private JComboBox speeds = new JComboBox<>(speedArray);
	
	/***************************************************************
	Constructor: Default
	
	Use:  Create a SortAnimationPanel object and add it to the sort panel. 
		  This constructor also creates a control panel and adds the component data members to it. 
		  The control panel is added to the sort panel
	
	Arguments: N/A
	
	Returns: N/A
	***************************************************************/
	public SortPanel()
	{
		//Create a SortAnimationPanel and add it to the sort panel
		final SortAnimationPanel topPanel = new SortAnimationPanel();
		this.add(topPanel);
		
		//Create a panel to hold the controls
		JPanel controlPanel = new JPanel();
		
		//Set the control panel layout to centred flow layout
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Add the controls to the control panel
		controlPanel.add(new JLabel("Initial: "));
		controlPanel.add(initialOrdering);
		controlPanel.add(fillArray);
		controlPanel.add(sortButton);
		controlPanel.add(sortTypes);
		controlPanel.add(speeds);
		
		//Disable the sort button and stop button
		sortButton.setEnabled(false);
		stopButton.setEnabled(false);
		
		//Add the control panel to the sort panel
		this.add(controlPanel);
		controlPanel.setVisible(true);
		
		//When the populate array button is pressed fill it with random numbers and display it. 
		//Also enable the sort button and disable the populate button
		fillArray.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
            {
				
				//New random object
				Random rand = new Random();
				for(int i=0; i< integerArray.length; i++)
				{
					//Generate a random integer and add it to the array
					integerArray[i] = rand.nextInt((530 - 1) + 1) + 1;
				}
				
				//Grab the initial array ordering
				String selectedValue = initialOrdering.getSelectedItem().toString();
				
				//Show the array in the selected ordering
				if(selectedValue.equals("Ascending"))
				{
					Arrays.sort(integerArray);
					repaint();
				}

				if(selectedValue.equals("Descending"))
				{
					int temp;
					int start = 0;
					int end = integerArray.length-1;
					
					//Sort the array in ascending order
					Arrays.sort(integerArray);
					
					//Reverse the array so it is not in descending order
					while(start < end)
					{
						temp = integerArray[start];  
						integerArray[start] = integerArray[end];
						integerArray[end] = temp;
						start++;
						end--;
					}   
						repaint();
				}
				
				if(selectedValue.equals("Random"))
				{
					repaint();
				}
				
				
				//Disable populate button and enable sort button
				sortButton.setEnabled(true);
				fillArray.setEnabled(false);
			}
		});
		
		//When the sort button is pressed call the appropriate sort method
		sortButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent f)
            {
				
				Thread t1 = new Thread(topPanel);
				t1.start();
				
			}
		});
		
		//When the stop button is pressed stop the sorting
		stopButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent f)
            {
				//Thread.interrupt();
			}
		});
	}
	
		
	/***************************************************************
	Class: SortAnimationPanel
	
	Use:  JPanel for displaying the sorting animation.
	
	Arguments: N/A
	
	Returns: N/A
	***************************************************************/
	private class SortAnimationPanel extends JPanel implements Runnable 
	{
		public int number;
		public int[] numbers;
		/***************************************************************
		Method: Run

		Use:  Call the sort methods to run the animation. 

		Arguments: String

		Returns: N/A
		***************************************************************/
		public void run()
		{
			//Get the values of sort type and speed
			String selectedValue = sortTypes.getSelectedItem().toString();
			String selectedSpeed = speeds.getSelectedItem().toString();
		
			sortButton.setEnabled(false);
			
			if(selectedSpeed.equals("Slow"))
			{
				speed = 500;
			}
			
			if(selectedSpeed.equals("Medium"))
			{
				speed = 100;
			}
			
			if(selectedSpeed.equals("Fast"))
			{
				speed = 10;
			}
			
			//Run the selected sort algorithm
			if(selectedValue.equals("Selection"))
			{
				selectionSort(integerArray);
			}
			
			if(selectedValue.equals("Quick"))
			{
				quickSort(integerArray, 0, integerArray.length - 1);
				fillArray.setEnabled(true);
			}
			
			
			if(selectedValue.equals("Bubble"))
			{
				bubbleSort(integerArray);
			}
			
			if(selectedValue.equals("Shell"))
			{
				shellSort(integerArray);
			}
			
			if(selectedValue.equals("Insertion"))
			{
				insertionSort(integerArray);
			}
			
			if(selectedValue.equals("Gnome"))
			{
				gnomeSort(integerArray);
			}
			
			if(selectedValue.equals("Cocktail"))
			{
				cocktailSort(integerArray);
			}
			
			if(selectedValue.equals("Cycle"))
			{
				cycleSort(integerArray);
			}
		}
		
		public SortAnimationPanel()
		{
			//set the size of the animation window
			this.setPreferredSize(new Dimension(530, 525));
			
			//set the background to white
			this.setBackground(new Color(255,255,255));
			
		}
		
		//Paint method to draw the lines
		public void paintComponent( Graphics g )
		{	
			if(integerArray[0] !=0)
			{
				
				for(int i=0; i<integerArray.length; i++)
				{
					g.drawLine(i,530,i,530-integerArray[i]);
					g.setColor(Color.BLUE); 
				}
			}
		}	
	
	}
	
	/***************************************************************
	Method: SelectionSort
	
	Use:  Sort the array of integers using selection sort and display the lines after every swap
	
	Arguments: Array
	
	Returns: N/A
	***************************************************************/
	public void selectionSort(int[] intArray) 
	{
		try
		{
		
			for (int i=0; i<intArray.length-1; i++) 
			{
				for (int j=i+1; j<intArray.length; j++) 
				{
					if (intArray[i] > intArray[j]) 
					{
						int temp = intArray[i];
						intArray[i] = intArray[j];
						intArray[j] = temp;
						
						//Redraw the lines after swapping elements
						repaint();
					}
				}
				Thread.sleep(speed);
			}
			
			//Re-Enable array button
			fillArray.setEnabled(true);
		}
		
		catch (InterruptedException e) 
		{
			e.printStackTrace();
    
		}
	}
	
	/***************************************************************
	Method: quickSort, swap
	
	Use:  Sort the array of integers using quickSort and call swap to swap the elements
	
	Arguments: Array, array start, array end
	
	Returns: N/A
	***************************************************************/
	public void quickSort(int intArray[], int start, int end)
	{
		int i = start;                          
		int k = end;                            
	
		try
		{
			if (end - start >= 1)               
			{
				int pivot = intArray[start];       
			
				while (k > i)                   
				{
					while (intArray[i] <= pivot && i <= end && k > i)  
					{
						i++;                                    
					}
					
					while (intArray[k] > pivot && k >= start && k >= i) 
					{	
						k--;                                        
					}
					if (k > i) 
					{
						int temp = intArray[i];           
						intArray[i] = intArray[k];      
						intArray[k] = temp; 
						repaint();
					}
				}
				
				int temp1 = intArray[start];           
				intArray[start] = intArray[k];      
				intArray[k] = temp1; 
				repaint();

				quickSort(intArray, start, k - 1);
				Thread.sleep(speed);

				quickSort(intArray, k + 1, end);   
			}
			else 
			{
				return;
			}
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
    
		}
	}
	
	/***************************************************************
	Method: bubbleSort
	
	Use:  Sort the array of integers using bubbleSort. Redraw the lines after every swap
	
	Arguments: Array
	
	Returns: N/A
	***************************************************************/
	public void bubbleSort(int[] intArray) 
	{
		
		try
		{
			int n = intArray.length;
			int temp = 0;
		
			for(int i=0; i < n; i++)
			{
                for(int j=1; j < (n-i); j++)
				{           
                    if(intArray[j-1] > intArray[j])
					{
						//swap the elements!
						temp = intArray[j-1];
						intArray[j-1] = intArray[j];
						intArray[j] = temp;
						repaint();
                    }          
                }
				Thread.sleep(speed);
            }

			//Re-Enable array button
			fillArray.setEnabled(true);
		}
		
		catch (InterruptedException e) 
		{
			e.printStackTrace();
    
		}
		fillArray.setEnabled(true);
    }
	
	/***************************************************************
	Method: shellSort
	
	Use:  Sort the array of integers using shellSort. Redraw the lines after every swap
	
	Arguments: Array
	
	Returns: N/A
	***************************************************************/
	public void shellSort(int[] intArray) 
	{
		try
		{
			int increment = intArray.length / 2;
			while (increment > 0) 
			{
				for (int i = increment; i < intArray.length; i++) 
				{
					int j = i;
					int temp = intArray[i];
					while (j >= increment && intArray[j - increment] > temp) 
					{
						intArray[j] = intArray[j - increment];
						j = j - increment;
						repaint();
					}
					intArray[j] = temp;
					repaint();
					Thread.sleep(speed/2);
				}
				
				if (increment == 2) 
				{
					increment = 1;
				}
				else 
				{
					increment *= (5.0 / 11);
				}
			}
					
			//Re-Enable array button
			fillArray.setEnabled(true);
		}
		
		catch (InterruptedException e) 
		{
			e.printStackTrace();
    
		}
		
	}
	
	/***************************************************************
	Method: insertionSort
	
	Use:  Sort the array of integers using insertionSort. Redraw the lines after every swap
	
	Arguments: Array
	
	Returns: N/A
	***************************************************************/
	public void insertionSort(int[] intArray) 
	{	
		try
		{
			for (int i = 1; i < intArray.length; i++) 
			{	
				int j = i;
				int temp = intArray[i];
				
				while ((j > 0) && (intArray[j-1] > temp)) {
					
					intArray[j] = intArray[j-1];
					j--;
					repaint();
				}
				
				intArray[j] = temp;
				repaint();
				Thread.sleep(speed);
			}
			
			//Re-Enable array button
			fillArray.setEnabled(true);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
    
		}
	}

	/***************************************************************
	Method: gnomeSort
	
	Use:  Sort the array of integers using gnomeSort. Redraw the lines after every swap
	
	Arguments: Array
	
	Returns: N/A
	***************************************************************/
	public void gnomeSort(int[] a)
	{
		int i=1;
		int j=2;
		
		try
		{
			while(i < a.length) 
			{
				if ( a[i-1] <= a[i] ) 
				{
					i = j; j++;
					Thread.sleep(speed);
				} 
				else 
				{
					int temp = a[i-1];
					a[i-1] = a[i];
					a[i--] = temp;
					repaint();
					i = (i==0) ? j++ : i;
				}
			}
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
    
		}
		
		//Re-Enable array button
		fillArray.setEnabled(true);
	}

	/***************************************************************
	Method: cocktailSort
	
	Use:  Sort the array of integers using cocktailSort. Redraw the lines after every swap
	
	Arguments: Array
	
	Returns: N/A
	***************************************************************/
	public void cocktailSort( int[] intArray )
	{
		try
		{
			boolean swapped;
			do 
			{
				swapped = false;
				
				for (int i =0; i<=  intArray.length  - 2;i++) 
				{
					if (intArray[ i ] > intArray[ i + 1 ]) 
					{
						int temp = intArray[i];
						intArray[i] = intArray[i+1];
						intArray[i+1]=temp;
						swapped = true;
						repaint();
					}
				}
				if (!swapped) 
				{
					break;
				}
				
				swapped = false;
				Thread.sleep(speed);
				for (int i= intArray.length - 2;i>=0;i--) 
				{
					if (intArray[ i ] > intArray[ i + 1 ]) 
					{
						int temp = intArray[i];
						intArray[i] = intArray[i+1];
						intArray[i+1]=temp;
						swapped = true;
						repaint();
					}
				}
			} while (swapped);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();    
		}
		//Re-Enable array button
		fillArray.setEnabled(true);
	}
	

	/***************************************************************
	Method: cycleSort
	
	Use:  Sort the array of integers using cycleSort. Redraw the lines after every swap
	
	Arguments: Array
	
	Returns: N/A
	***************************************************************/	
    public void cycleSort(int[] intArray) 
	{
		try
		{
			for (int cycleStart = 0; cycleStart < intArray.length - 1; cycleStart++) 
			{
				int val = intArray[cycleStart];
				int pos = cycleStart;
				
				for (int i = cycleStart + 1; i < intArray.length; i++)
				{
					if (intArray[i] < val)
					{
						pos++;
					}
				}
				
				
				if (pos == cycleStart)
				{
					continue;
				}
	
				while (val == intArray[pos])
				{
					pos++;
				}
	
				int temp = intArray[pos];
				intArray[pos] = val;
				val = temp;
				repaint();

				while (pos != cycleStart) 
				{
					pos = cycleStart;
					for (int i = cycleStart + 1; i < intArray.length; i++)
					{
						if (intArray[i] < val)
						{
							pos++;
						}
					}
					
					while (val == intArray[pos])
					{
						pos++;
					}
	
					temp = intArray[pos];
					intArray[pos] = val;
					val = temp;
					repaint();
					Thread.sleep(speed);
				}
			//	Thread.sleep(speed);
			}
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();    
		}
		//Re-Enable array button
		fillArray.setEnabled(true);
    }		
} 

	



