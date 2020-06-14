/**
 * 
 */
package it.unicam.cs.sap.sudoku;

/**
 * @author loreti
 *
 */
public class Main {
	
	public static void main(String[] argv) {
		Solver solver = new Solver();
		Schema schema = new Schema(2);
		solver.solve(schema);
		for( int i=0 ; i<2;i++) {
			for( int j=0 ; j<2 ; j++) {
				System.out.print(schema.getValue(i, j));
			}
			System.out.println();
		}
	}

}
