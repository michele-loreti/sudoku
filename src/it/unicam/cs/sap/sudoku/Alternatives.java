/**
 * 
 */
package it.unicam.cs.sap.sudoku;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author loreti
 *
 */
public class Alternatives {
	private int i;
	private int j;
	Queue<Integer> values;
	private int current;
	
	public Alternatives(int i, int j, Set<Integer> values) {
		this.i = i;
		this.j = j;
		this.values = new LinkedList<>(values);
		this.current = -1;
	}
	
	public int current() {
		return current;
	}
	
	public boolean hasNext() {
		return !values.isEmpty();
	}
	
	public int next() {
		if (values.isEmpty()) {
			throw new IllegalStateException("Non ci sono alternative!");
		}
		current = values.poll();
		return current;
	}
	
	public int getRow() {
		return i;
	}
	
	public int getColumn() {
		return j;
	}
}