/**
 * 
 */
package it.unicam.cs.sap.sudoku;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author loreti
 *
 */
public class Group {
	
	private Cell[] data;
	private Set<Integer> values;
	
	public Group(int size) {
		this.data = new Cell[size];
		this.values = createSet(size);
	}
	
	private Set<Integer> createSet(int size) {
		HashSet<Integer> values = new HashSet<>();
		for(int i=1 ; i<=size ; i++) {
			values.add(i);
		}
		return values;
	}

	public boolean contains(int value) {
		return data[value-1]!=null;
	}

	public void clear(int value) {
		data[value-1] = null;
		values.add(value);
	}

	public void register(Cell cell) {
		int v = cell.getValue();
		if (data[v-1]!=null) {
			throw new IllegalArgumentException("Valore già presente nel gruppo!");
		}
		data[v-1] = cell;
		values.remove(v);
	}

	public Collection<? extends Integer> values() {
		return this.values;
	}


}
