package mazesolver;

import java.util.ArrayList;

public class PathGen {
	
	

	
	
	int[][] m_iMaze;
	int m_iRows;
	int m_iCols;
	int iPath=10;
	boolean diagonal=false;
	
	

	
	/// Constructor 1: takes a 2D integer array
	
	public PathGen(int[][] iMaze)
	{
		m_iMaze=iMaze;
		m_iRows=iMaze.length;
		m_iCols=iMaze[0].length;
	}


	// Constructor 2: initializes the dimensions of maze, 
	// later, indexers may be used to set individual elements' values
	
	public  PathGen(int iRows, int iCols)
	{
		m_iMaze= new int[iRows][iCols];
		m_iRows=iRows;
		m_iCols=iCols;
	}
		
	
	// Properties:
	
	public int getRows()
	{
		 return m_iRows; 
	}

	public int getCols()
	{
		return m_iCols;
	}
	public int[][] GetMaze()
	{
		  return m_iMaze; 
	}
	
	public void setMaze(int row,int col, int val){
		this.m_iMaze[row][col]=val;
	}
	public int getPathCharacter(){ 
		return iPath; 
	}
	public void setPathCharacter(int value) throws Exception
		{
			if (value==0)
				throw new Exception("Invalid path character specified");
			else
				iPath=value;
		}
	
	public boolean getAllowDiagonals(){	
		
			return diagonal;	
		}
	public boolean setAllowDiagonals(boolean value) {
			return diagonal=value;		
		}
	



	
	//The function is used to get the contents of a given node in a given maze,
	// specified by its node no.
	
	private int GetNodeContents(int[][] iMaze, int iNodeNo)
	{
		int iCols=iMaze[0].length;
		return iMaze[iNodeNo/iCols][iNodeNo-iNodeNo/iCols*iCols];
	}

	
	// The function is used to change the contents of a given node in a given maze,
	// specified by its node no.
	
	private void ChangeNodeContents(int[][] iMaze, int iNodeNo, int iNewValue)
	{
		int iCols=iMaze[0].length;
		iMaze[iNodeNo/iCols][iNodeNo-iNodeNo/iCols*iCols]=iNewValue;
	}
	
	
	// This public function finds the shortest path between two points
	// in the maze and return the solution as an array with the path traced 
	// by "iPath" (can be changed using property "PathCharacter")
	// if no path exists, the function returns null

	public int[][] FindPath(int iFromY, int iFromX, int iToY, int iToX)
	{
		int iBeginningNode = iFromY*this.m_iCols + iFromX;
		int iEndingNode = iToY*this.m_iCols+ iToX;
		return ( Search(iBeginningNode, iEndingNode) ) ;
	}



	// Internal function for that finds the shortest path using a technique
	// similar to breadth-first search.
	// It assigns a node no. to each node(2D array element) and applies the algorithm
	
	private enum Status
	{	Ready,	Waiting,	Processed	}
	private int[][] Search(int iStart, int iStop)
	{
		final int empty=0;
	
		int iRows=m_iRows;
		int iCols=m_iCols;
		int iMax=iRows*iCols;
		int[] Queue=new int[iMax];
		int[] Origin=new int[iMax];
		int iFront=0, iRear=0;

		//check if starting and ending points are valid (open)
		if ( GetNodeContents(m_iMaze, iStart)!=empty || GetNodeContents(m_iMaze, iStop)!=empty )
		{
			return null;
		}
	
		//create dummy array for storing status
		int[][] iMazeStatus=new int[iRows][iCols];
		//initially all nodes are ready
		for(int i=0;i<iRows;i++)
			for(int j=0;j<iCols;j++)
				iMazeStatus[i][j]=(int)Status.Ready.ordinal();

		//add starting node to Q
		Queue[iRear]=iStart; 
		Origin[iRear]=-1; 
		iRear++;
		int iCurrent, iLeft, iRight, iTop, iDown, iRightUp, iRightDown, iLeftUp, iLeftDown;
		while(iFront!=iRear)	// while Q not empty	
		{
			if (Queue[iFront]==iStop)		// maze is solved
				break;

			iCurrent=Queue[iFront];
		
			iLeft=iCurrent-1;
			if (iLeft>=0 && iLeft/iCols==iCurrent/iCols) 	//if left node exists
				if ( GetNodeContents(m_iMaze, iLeft)==empty ) 	//if left node is open(a path exists)
					if (GetNodeContents(iMazeStatus, iLeft) == (int)Status.Ready.ordinal())	//if left node is ready
					{
						Queue[iRear]=iLeft; //add to Q
						Origin[iRear]=iCurrent;
						ChangeNodeContents(iMazeStatus, iLeft, (int)Status.Waiting.ordinal()); //change status to waiting
						iRear++;
					}

			iRight=iCurrent+1;
			if (iRight<iMax && iRight/iCols==iCurrent/iCols) 	//if right node exists
				if ( GetNodeContents(m_iMaze, iRight)==empty ) 	//if right node is open(a path exists)
					if (GetNodeContents(iMazeStatus, iRight) == (int)Status.Ready.ordinal())	//if right node is ready
					{
						Queue[iRear]=iRight; //add to Q
						Origin[iRear]=iCurrent;
						ChangeNodeContents(iMazeStatus, iRight, (int)Status.Waiting.ordinal()); //change status to waiting
						iRear++;
					}
	
			iTop=iCurrent-iCols;
			if (iTop>=0 ) 	//if top node exists
				if ( GetNodeContents(m_iMaze, iTop)==empty ) 	//if top node is open(a path exists)
					if (GetNodeContents(iMazeStatus, iTop) == (int)Status.Ready.ordinal())	//if top node is ready
					{
						Queue[iRear]=iTop; //add to Q
						Origin[iRear]=iCurrent;
						ChangeNodeContents(iMazeStatus, iTop, (int)Status.Waiting.ordinal() ); //change status to waiting
						iRear++;
					}

			iDown=iCurrent+iCols;
			if (iDown<iMax ) 	//if bottom node exists
				if ( GetNodeContents(m_iMaze, iDown)==empty ) 	//if bottom node is open(a path exists)
					if (GetNodeContents(iMazeStatus, iDown) == (int)Status.Ready.ordinal())	//if bottom node is ready
					{
						Queue[iRear]=iDown; //add to Q
						Origin[iRear]=iCurrent;
						ChangeNodeContents(iMazeStatus, iDown, (int)Status.Waiting.ordinal()); //change status to waiting
						iRear++;
					}
			if (diagonal==true)
			{
				iRightDown=iCurrent+iCols+1;
				if (iRightDown<iMax && iRightDown>=0 && iRightDown/iCols==iCurrent/iCols+1 ) 	//if bottom-right node exists
					if ( GetNodeContents(m_iMaze, iRightDown)==empty ) 	//if this node is open(a path exists)
						if (GetNodeContents(iMazeStatus, iRightDown) == (int)Status.Ready.ordinal())	//if this node is ready
						{
							Queue[iRear]=iRightDown; //add to Q
							Origin[iRear]=iCurrent;
							ChangeNodeContents(iMazeStatus, iRightDown, (int)Status.Waiting.ordinal()); //change status to waiting
							iRear++;
						}

				iRightUp=iCurrent-iCols+1;
				if (iRightUp>=0 && iRightUp<iMax && iRightUp/iCols==iCurrent/iCols-1) 	//if upper-right node exists
					if ( GetNodeContents(m_iMaze, iRightUp)==empty ) 	//if this node is open(a path exists)
						if (GetNodeContents(iMazeStatus, iRightUp) == (int)Status.Ready.ordinal())	//if this node is ready
						{
							Queue[iRear]=iRightUp; //add to Q
							Origin[iRear]=iCurrent;
							ChangeNodeContents(iMazeStatus, iRightUp, (int)Status.Waiting.ordinal()); //change status to waiting
							iRear++;
						}

				iLeftDown=iCurrent+iCols-1;
				if (iLeftDown<iMax && iLeftDown>=0 && iLeftDown/iCols==iCurrent/iCols+1) 	//if bottom-left node exists
					if ( GetNodeContents(m_iMaze, iLeftDown)==empty ) 	//if this node is open(a path exists)
						if (GetNodeContents(iMazeStatus, iLeftDown) == (int)Status.Ready.ordinal())	//if this node is ready
						{
							Queue[iRear]=iLeftDown; //add to Q
							Origin[iRear]=iCurrent;
							ChangeNodeContents(iMazeStatus, iLeftDown, (int)Status.Waiting.ordinal()); //change status to waiting
							iRear++;
						}

				iLeftUp=iCurrent-iCols-1;
				if (iLeftUp>=0 && iLeftUp<iMax && iLeftUp/iCols==iCurrent/iCols-1) 	//if upper-left node exists
					if ( GetNodeContents(m_iMaze, iLeftUp)==empty ) 	//if this node is open(a path exists)
						if (GetNodeContents(iMazeStatus, iLeftUp) == (int)Status.Ready.ordinal())	//if this node is ready
						{
							Queue[iRear]=iLeftUp; //add to Q
							Origin[iRear]=iCurrent;
							ChangeNodeContents(iMazeStatus, iLeftUp, (int)Status.Waiting.ordinal()); //change status to waiting
							iRear++;
						}
			}


			//change status of current node to processed
			ChangeNodeContents(iMazeStatus, iCurrent, (int)Status.Processed.ordinal());
			iFront++;
		
		}

		//create an array(maze) for solution
		int[][] iMazeSolved=new int[iRows][iCols];
		for(int i=0;i<iRows;i++)
			for(int j=0;j<iCols;j++)
				iMazeSolved[i][j]=m_iMaze[i][j];

		//make a path in the Solved Maze
		iCurrent=iStop;
		ChangeNodeContents(iMazeSolved, iCurrent, iPath);
		for(int i=iFront; i>=0; i--)
		{
			if (Queue[i]==iCurrent)
			{
				iCurrent=Origin[i];
				if (iCurrent==-1)		// maze is solved
					return ( iMazeSolved );
				ChangeNodeContents(iMazeSolved, iCurrent, iPath);
			}
		}

		//no path exists
		return null;
	}
	
	
	//get path nodes (from starting to end)
	public ArrayList<String> getPathNodes(int[][] data){
		
		//here use a array list - because path nodes cannot be predict
		ArrayList<String> pathcells = new ArrayList<String>();
		int [][] path= new int[this.m_iRows][this.m_iCols];
		int[] start=new int[2],end=new int[2],current=new int[2];
		
		//set starting nodes coordinates
		start[0]=data[0][0];
		start[1]=data[0][1];
		
		//set end nodes coordinates
		end[0]=data[1][0];
		end[1]=data[1][1];
		
		//find the solved path
		path= FindPath(start[0],start[1],end[0],end[1]);
		
		//set the current cell to start
		current[0]=start[0];
		current[1]=start[1];
		
		//add starting  cell to array list
		pathcells.add(current[0]+" "+current[1]);
		// remove ipath value form current node - avoid getting same node again and again
		path[current[0]][current[1]]=2;
		
		// adding other nodes to array list- finish the loop when current node = ending node
		while(!((current[0] == end[0]) && (current[1] == end[1]))){
			
					//check below node
					if(current[0]-1 >= 0 && path[current[0]-1][current[1]] == iPath){
						path[current[0]-1][current[1]]=2;
						current[0]=current[0]-1;
						current[1]=current[1];
						pathcells.add(current[0]+" "+current[1]);
						//System.out.println(current[0]+" "+current[1]);
					}
				
					//check above node
					if(current[0]+1 < m_iRows && path[current[0]+1][current[1]] == iPath){
						path[current[0]+1][current[1]]=2;
						current[0]=current[0]+1;
						current[1]=current[1];
						pathcells.add(current[0]+" "+current[1]);
						//System.out.println(current[0]+" "+current[1]);
					}
				
					//check left node
					if(current[1]-1 >= 0 && path[current[0]][current[1]-1] == iPath){
						path[current[0]][current[1]-1]=2;
						current[0]=current[0];
						current[1]=current[1]-1;
						pathcells.add(current[0]+" "+current[1]);
						//System.out.println(current[0]+" "+current[1]);
					}
				
					//check right node
					if(current[1]+1 < m_iCols && path[current[0]][current[1]+1] == iPath){
						path[current[0]][current[1]+1]=2;
						current[0]=current[0];
						current[1]=current[1]+1;
						pathcells.add(current[0]+" "+current[1]);
						//System.out.println(current[0]+" "+current[1]);
					}
				
				
			
			
		}
		
		return pathcells;
		
	}
	
	
	// this is for testing .. just print the solved maze
	public void Testrun(int[][] data){
		int[] start=new int[2],end=new int[2];
		start[0]=data[0][0];
		start[1]=data[0][1];
		
		end[0]=data[1][0];
		end[1]=data[1][1];
		
		
		
		for(int i=0;i<this.m_iRows;i++){
			for(int j=0;j<this.m_iCols;j++){
				
				System.out.print(FindPath(start[0],start[1],end[0],end[1])[i][j]+"\t");
			}
			System.out.println();
		}
		
		//System.out.println(start[1]+" "+start[0]+" "+end[1]+" "+end[0]);
	}
	
}


