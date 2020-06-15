package it.unicam.cs.sap.sudoku.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unicam.cs.sap.sudoku.Schema;

class TestSchema {

	@Test
	void ogniElementoEValidoNelloSchemaVuoto() {
		Schema schema = new Schema(2);
		for(int i=0;i<schema.getRange();i++) {
			for(int j=0;j<schema.getRange();j++) {
				for(int v=1;v<=schema.getRange();v++) {
					assertTrue(schema.isValid(i,j,v));
				}
			}
		}
	}

	@Test
	void testValiditaDopoInserimento() {
		Schema schema = new Schema(2);
		schema.set(0, 0, 1);
		for(int i=0;i<schema.getRange();i++) {
			for(int j=0;j<schema.getRange();j++) {
				if (i==0) {
					assertFalse(schema.isValid(i, j, 1));
				} else {
					if (j==0) {
						assertFalse(schema.isValid(i, j, 1));
					} else {
						if ((i==1)&&(j==1)) {
							assertFalse(schema.isValid(i, j, 1));
						} else {
							assertTrue(schema.isValid(i, j, 1));
						}
					}
				}
			}
		}
	}
	
	@Test
	void test3() {
		Schema schema = new Schema(2);
		schema.set(0, 0, 1);
		assertTrue(schema.validValuesAt(1, 1).containsAll(List.of(2,3,4)));
	}
	
	@Test
	void test4() {
		Schema schema = new Schema(2);
		schema.set(0, 0, 1);
		schema.set(1, 0, 2);
		assertTrue(schema.validValuesAt(1, 1).containsAll(List.of(3,4)));
	}

	@Test
	void test5() {
		Schema schema = new Schema(2);
		schema.set(0, 0, 1);
		schema.set(1, 0, 2);
		schema.set(0, 1, 3);
		assertTrue(schema.validValuesAt(1, 1).containsAll(List.of(4)));
	}

}
