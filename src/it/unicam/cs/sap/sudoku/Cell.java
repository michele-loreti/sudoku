/**
 * 
 */
package it.unicam.cs.sap.sudoku;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author loreti
 *
 */
public class Cell {
	
	private final Group rowGroup;
	private final Group columnGroup;
	private final Group squareGroup;
	private int value;
	private int row;
	private int column;
	
	public Cell(int row, int column, Group rowGroup, Group columnGroup, Group squareGroup) {
		this.rowGroup = rowGroup;
		this.columnGroup = columnGroup;
		this.squareGroup = squareGroup;
		this.value = 0;
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isValid(int value) {
		return (this.value==0)&&
				(!rowGroup.contains(value)&&
				(!columnGroup.contains(value)&&
				(!squareGroup.contains(value))));
	}
	
	public int clear() {
		if (!isEmpty()) {
			rowGroup.clear(this.value);
			columnGroup.clear(this.value);
			squareGroup.clear(this.value);
		} 
		int oldValue = value;
		this.value = 0;
		return oldValue;
	}
	
	public boolean setValue(int value) {
		if (value <= 0) {
			throw new IllegalArgumentException("Valore illegale!"); 
		} 
		if ((this.value == 0)&&(this.isValid(value))) {
			this.value = value;
			registerValueToGroups();
		} else {
			return false;
		}			
		return true;
	}

	private void registerValueToGroups() {
		rowGroup.register(this);
		columnGroup.register(this);
		squareGroup.register(this);
	}

	public boolean isEmpty() {
		return this.value==0;
	}

	public Set<Integer> validValues() {
		TreeSet<Integer> validValues = new TreeSet<>();
		validValues.addAll(columnGroup.values());
		validValues.retainAll(rowGroup.values());
		validValues.retainAll(squareGroup.values());
		return validValues;
	}

	@Override
	public String toString() {
		return (value==0?" ":value+"");
	}
	
	
}
