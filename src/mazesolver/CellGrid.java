package mazesolver;


public class CellGrid {

	private int row, col;
	private  int margin_cells = 1;

	private Cell[][] cellPane;
	//constructor
	public CellGrid(int row,int col){
		this.row=row;
		this.col=col;
		
		cellPane = new Cell[this.row + 2*margin_cells][this.col + 2*margin_cells];
		int count = 1;

		for (int row1 = 0; row1 < this.row + 2; row1++) {
			for (int col1 = 0; col1 < this.col + 2; col1++) {
				cellPane[row1][col1] = new Cell();

				

				

				if (row1 == 0 || row1 == this.row + 1 || col1 == 0
						|| col1 == this.col + 1) {

					cellPane[row1][col1].setAsWall();
				} else {

					cellPane[row1][col1].setLabel(count);
					count++;
				}
			}
		}

	}
	
	public Cell ReturnGridCell(int row,int col){
		return this.cellPane[row][col];
		
	}
	
	//set specific cell
	public void setCellAs(int row,int col,String celltype) {
		if(celltype == "wall"){
			cellPane[row][col].setAsWall();
		}else if(celltype == "path"){
			cellPane[row][col].setAsPath();
		}else if(celltype == "end"){
			cellPane[row][col].setAsEnd();
		}
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @param col
	 *            the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the margin_cells
	 */
	public int getMarginCells() {
		
		return this.margin_cells;
	}

	/**
	 * @param margin_cells the margin_cells to set
	 */
	public void setMarginCells(int margin_cells) {
		this.margin_cells = margin_cells;
	}

	
}
