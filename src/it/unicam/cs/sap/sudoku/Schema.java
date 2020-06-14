/**
 * 
 */
package it.unicam.cs.sap.sudoku;

import java.util.Arrays;
import java.util.Set;

/**
 * @author loreti
 *
 */
public class Schema {
	
	private final Group[] columnsGroup;
	private final Group[] rowsGroup;
	private final Group[][] squareGroup;
	private final boolean[][] readOnly;
	
	private Cell[][] cells;
	
	private final int size;
	private final int range;
	private int emptyCells;
	
	public Schema(int size) {
		this.size = size;
		this.range = size*size;
		this.emptyCells = this.range*this.range;
		this.cells = new Cell[range][range];
		this.columnsGroup = new Group[range];
		this.rowsGroup = new Group[range];
		this.squareGroup = new Group[size][size];
		this.readOnly = new boolean[range][range];
		createGroups();
		createSchema();
	}

	public Schema(int size, int[][] values) {
		this(size);
		fill(values);
	}

	private void fill(int[][] values) {
		for(int i=0;i<range;i++) {
			for(int j=0;j<range;j++) {
				if (values[i][j]!=0) {
					set(i,j,values[i][j]);
					this.readOnly[i][j] = true;
				}
			}
		}
	}

	public void set(int i, int j, int value) {
		if ((value<=0)||(value>range)) {
			throw new IllegalArgumentException("Valore fuori dal range!");
		}
		if (readOnly[i][j]) {
			throw new IllegalArgumentException("La cella in posizione ("+i+","+j+") è di sola lettura!");
		}
		this.cells[i][j].setValue(value);
		this.emptyCells--;
	}

	private void createGroups() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				columnsGroup[i*size+j] = new Group(range);
				rowsGroup[i*size+j] = new Group(range);
				squareGroup[i][j] = new Group(range);
			}
		}
	}

	private void createSchema() {
		for(int i=0;i<range;i++) {
			for(int j=0;j<range;j++) {
				this.cells[i][j] = new Cell(i,j,rowsGroup[i],columnsGroup[j],squareGroup[i/3][j/3]);
			}
		}
	}

	public Set<Integer> validValuesAt(int i, int j) {
		return this.cells[i][j].validValues();
	}
	
	public boolean isInconsistent() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if (this.cells[i][j].isEmpty()&&(this.cells[i][j].validValues().isEmpty())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isFull() {
		return this.emptyCells==0;
	}
	
	public boolean isReadOnly(int i, int j) {
		return this.readOnly[i][j];
	}

	public int[][] getValues() {
		int[][] values = new int[range][range];
		for(int i=0;i<range;i++) {
			for(int j=0;j<range;j++) {
				values[i][j] = getValue(i,j);
			}
		}
		return values;
	}

	public int getValue(int i, int j) {
		return this.cells[i][j].getValue();
	}

	public int getRange() {
		return this.range;
	}

	public boolean isEmpty(int row, int column) {
		return this.cells[row][column].isEmpty();
	}

	@Override
	public String toString() {
		return Arrays.deepToString(cells);
	}
	
	
}
