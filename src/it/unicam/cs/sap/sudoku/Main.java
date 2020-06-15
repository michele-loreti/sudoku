/**
 * 
 */
package it.unicam.cs.sap.sudoku;

/**
 * @author loreti
 *
 */
public class Main {
	
	public static final int[][] SCHEMA = new int[][] {
		new int[] { 0, 0, 0, 0, 0, 0, 0, 5, 0 } ,
		new int[] { 0, 0, 1, 0, 0, 9, 3, 0, 0 } ,
		new int[] { 9, 0, 0, 7, 0, 1, 0, 0, 0 } ,
		new int[] { 0, 0, 5, 0, 9, 0, 4, 0, 7 } ,
		new int[] { 6, 0, 0, 0, 2, 0, 0, 0, 3 } ,
		new int[] { 2, 0, 9, 0, 4, 0, 6, 0, 0 } ,
		new int[] { 0, 0, 0, 5, 0, 2, 0, 0, 1 } ,
		new int[] { 0, 0, 4, 8, 0, 0, 2, 0, 0 } ,
		new int[] { 0, 1, 0, 0, 0, 0, 0, 0, 0 } 
	};
	
	
	public static void main(String[] argv) {
		Solver solver = new Solver();
		Schema schema = new Schema(3,SCHEMA);
		solver.solve(schema);
		for( int i=0 ; i<schema.getRange();i++) {
			for( int j=0 ; j<schema.getRange() ; j++) {
				System.out.print(schema.getValue(i, j));
			}
			System.out.println();
		}
	}

}
