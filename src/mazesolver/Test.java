package mazesolver;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Testing");

		Input in = new Input();
		int row = in.getRow();
		int col = in.getColumn();

		MazeBoard ss = new MazeBoard(row, col);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(ss);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		//get wall cells
		String a = in.getcells();

		String[] strnum = a.split(",");
		int[][] wallcells = new int[strnum.length][2];

		//convert to integer values
		for (int i = 0; i < strnum.length; i++) {
			wallcells[i][0] = Integer.parseInt(strnum[i]);
		}

		//remove the current grid
		frame.remove(ss);
		frame.repaint();
		
		//get wall coordinates
		wallcells = getCordinates(wallcells, col);

		//add walls
		ss.AddWall(wallcells);

		//add the changed in grid to frame
		frame.add(ss);
		frame.repaint();
		
		//get starting and end cells
		int[][] startendcells = new int[2][2];
		startendcells[0][0]=in.getStartingCell();
		startendcells[1][0]=in.getEndCell();
		
		//get coordinates of them
		startendcells=getCordinates(startendcells, col);

		//create a maze in order to use in PathGen class
		int[][] maze = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				//add free space here - free space-0
				maze[i][j] = 0;
				//add walls here - wall - 1
				for (int k = 0; k < wallcells.length; k++) {
					if(wallcells[k][0]==i && wallcells[k][1]==j)
						maze[i][j]=1;
				}

			}

		}

		//create a pathGen instance
		PathGen path = new PathGen(maze);
		
		//do a test here - just print the solved maze
		//path.Testrun(startendcells);

		
		

		

		ArrayList<String> pathcells = new ArrayList<String>();
		pathcells=path.getPathNodes(startendcells);
		
		
		   try {
			  
		        for(int i=0;i<pathcells.size();i++){
		    			String[] aa=pathcells.get(i).split(" ");
		    			int r=Integer.parseInt(aa[0]);
		    			int c=Integer.parseInt(aa[1]);
		    			
		    			frame.remove(ss);
			    		frame.repaint();
		    			ss.AddPathCells(r,c);
		    			ss.AddEndCell(startendcells[1][0], startendcells[1][1]);
		    			frame.add(ss);
			    		frame.repaint();
			    		
			            Thread.sleep(1 * 600);
		    		}
		    		
		       
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }

	}

	public static int[][] getCordinates(int[][] wallcells, int numbeofcol) {
		int row, col;
		int[][] wallcell = new int[wallcells.length][2];

		for (int i = 0; i < wallcells.length; i++) {

			row = (int) ((wallcells[i][0] - 1) / (numbeofcol));
			col = ((wallcells[i][0] - 1) % (numbeofcol));
			// System.out.println(row+" "+col);
			wallcell[i][0] = row;
			wallcell[i][1] = col;

		}
		return wallcell;

	}

}
