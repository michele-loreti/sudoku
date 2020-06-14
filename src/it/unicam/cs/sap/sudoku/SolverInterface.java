/**
 * 
 */
package it.unicam.cs.sap.sudoku;

/**
 * @author loreti
 *
 */
public interface SolverInterface {

	default int[][] solve(int size, int[][] values) {
		Schema schema = new Schema(size,values);
		if (solve(schema)) {
			return schema.getValues();
		} else {
			return null;
		}
	}

	boolean solve(Schema schema);
	
}
