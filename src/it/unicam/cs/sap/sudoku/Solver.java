/**
 * 
 */
package it.unicam.cs.sap.sudoku;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author loreti
 *
 */
public class Solver implements SolverInterface {

	@Override
	public boolean solve(Schema schema) {
		Stack<Alternatives> stack = new Stack<>();
		Alternatives current = select(0,0,schema);
		while (current!=null) {
			if (current.hasNext()) {
				int row = current.getRow();
				int column = current.getColumn();
				int v = current.next();
				schema.set(row, column, v);
				stack.push(current);
				column++;
				current = select(row,column,schema);
			} else {
				current = (stack.isEmpty()?null:stack.pop());
			}
		}
		return schema.isFull();
	}
	
	private Alternatives select(int row, int column, Schema schema) {
		if (column == schema.getRange()) {
			row++;
			column = 0;
		}
		while (row<schema.getRange()) {
			if (schema.isEmpty(row,column)) {
				return new Alternatives(row, column, schema.validValuesAt(row, column));
			}
			column++;
			if (column == schema.getRange()) {
				row++;
				column = 0;
			}
		}
		return null;
	}
	
}
