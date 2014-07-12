package mazesolver;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class MazeBoard extends JPanel {

	private int inputRow;
	private int inputColumn;

	private GridBagLayout Mazegrid;
	private GridBagConstraints gbc;
	private CellGrid maze_cells;
	private int margin_cells;

	public MazeBoard(int row, int col) {

		this.inputRow = row;
		this.inputColumn = col;
		

		this.Mazegrid = new GridBagLayout();
		this.gbc = new GridBagConstraints();
		this.maze_cells = new CellGrid(inputRow, inputColumn);
		this.margin_cells = this.maze_cells.getMarginCells();
		

		Init();

	}

	public void Init() {
		setLayout(Mazegrid);
		
		for (int row = 0; row < inputRow + 2*margin_cells; row++) {
			for (int col = 0; col < inputColumn + 2*margin_cells; col++) {

				gbc.gridx = col;
				gbc.gridy = row;

				add(maze_cells.ReturnGridCell(row, col), gbc);

			}
		}

	}

	public void AddWall(int[] wallcells) {
		int row,col;
		for(int i=0;i<wallcells.length;i++){
			
			 row= (int)((wallcells[i]-1)/(this.inputRow))+1;
			 col = ((wallcells[i]-1) % (this.inputRow))+1;
			 System.out.println(row+" "+col);
			 maze_cells.setCellAs(row,col,"wall");
		}
		
		

		Init();

	}
}
