package mazesolver;

import javax.swing.JOptionPane;

public class Input {
	
	//get number of rows from user
	 public int getRow() {            
	      String rowStr = JOptionPane.showInputDialog("Enter number of rows needed :");   // Read input String from dialog box
	      Integer row = Integer.parseInt(rowStr);   
	      
	      return row;
	        }
	   
	 //get walls separated by comma
	   public String getWallcells() {
		      String rowStr;
		      // Read input String from dialog box
		      rowStr = JOptionPane.showInputDialog("Enter comma separated wall numbers :");
		      
		      return rowStr;
		      
		   }
		   
	   
	   // get number of column 
	   public int getColumn() {
		      String columnStr = JOptionPane.showInputDialog("Enter number of columns needed :");
		      Integer column = Integer.parseInt(columnStr);   // Convert String to double
		      
		      return column;
		      
		   }
	   
	   public int getStartingCell() {
		      String StartingCellStr = JOptionPane.showInputDialog("Enter the starting cell :");
		      Integer StartingCell = Integer.parseInt(StartingCellStr);   // Convert String to double
		      
		      return StartingCell;
		      
		   }
	   public int getEndCell() {
		      String getEndCellStr = JOptionPane.showInputDialog("Enter the ending cell :");
		      Integer getEndCell = Integer.parseInt(getEndCellStr);   // Convert String to double
		      
		      return getEndCell;
		      
		   }

}
