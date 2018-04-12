package com.moj.hierarchy;

/**
 * @author nilesh
 */

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class ReviewProcessHierarchyImplTest {
	@Test
	public void testExecute_byPassingNonExistFileName() {
		try {
			new ReviewProcessHierarchyImpl().execute("anything");
		}catch(IOException ex) {
			assertTrue(true);
		}
	}
	@Test
	public void testExecute_byPassingEmptyFile() throws IOException {
		String output=new ReviewProcessHierarchyImpl().execute("src/test/resources/empty.txt");
		assertTrue(output.trim().equals(""));
	}
	@Test
	public void testExecute_byPassingInvalidFormatInFile() throws IOException {
		String output=new ReviewProcessHierarchyImpl().execute("src/test/resources/invalid-formatted.txt");
		assertTrue(output.trim().equals(""));
	}
	@Test
	public void testExecute() throws IOException {
		String copy="Alfred Hitchcock (A9)\n" +
                	"| Cary Grant (A8)\n" +
                	"| | \\-Peter Lorre (A6)\n" +
                	"| | \\-Toshiro Mifune (A7)\n" +
                	"| | Hedy Lamarr (A6)\n" +
                	"| | | \\-Marie Dressler (A6)\n" +
                	"| | | Yul Brynner (A6)\n" +
                	"| | | | \\-Waheeda Rehman (A5)\n" +
                	"| | | | Robert Donat (A4)\n" +
                	"| | | | | \\-Lionel Barrymore (A3)\n" +
	                "| | | \\-Meena Kumari (A1)\n" +
	                "| | | \\-Hema Malini (A1)\n" +
	                "| Grace Kelly (A8)\n" +
	                "| | \\-Omar Sharif (A7)\n" +
	                "| \\-Jean Harlow (A8)\n" +
	                "| Vivien Leigh (A8)\n" +
	                "| | Steve McQueen (A7)\n" +
	                "| | | \\-Montgomery Clift (A6)\n" +
	                "| | | \\-Alain Delon (A6)\n" +
	                "| | | \\-Ethel Waters (A6)\n" +
	                "| | | \\-Anna May Wong (A6)\n" +
	                "| | \\-Doris Day (A7)\n" +
	                "| | \\-Carole Lombard (A6)\n" +
	                "| | \\-Sidney Poitier (A4)\n";
		String strHierarchy=new ReviewProcessHierarchyImpl().execute("src/test/resources/test-reviewers-and-reviewees.txt");
		assertTrue(strHierarchy.trim().equals(copy.trim()));
	}
	
	@Test
	public void testExecute_AddNewTopLevelReviewer() throws IOException {
		String copy="Robert Downey Jr (A10)\n" +
					"| Alfred Hitchcock (A9)\n" +
					"| | Cary Grant (A8)\n" +
					"| | | \\-Peter Lorre (A6)\n" +
	            	"| | | \\-Toshiro Mifune (A7)\n" +
	            	"| | | Hedy Lamarr (A6)\n" +
	            	"| | | | \\-Marie Dressler (A6)\n" +
	            	"| | | | Yul Brynner (A6)\n" +
	            	"| | | | | \\-Waheeda Rehman (A5)\n" +
	            	"| | | | | Robert Donat (A4)\n" +
	            	"| | | | | | \\-Lionel Barrymore (A3)\n" +
	                "| | | | \\-Meena Kumari (A1)\n" +
	                "| | | | \\-Hema Malini (A1)\n" +
	                "| | Grace Kelly (A8)\n" +
	                "| | | \\-Omar Sharif (A7)\n" +
	                "| | \\-Jean Harlow (A8)\n" +
	                "| | Vivien Leigh (A8)\n" +
	                "| | | Steve McQueen (A7)\n" +
	                "| | | | \\-Montgomery Clift (A6)\n" +
	                "| | | | \\-Alain Delon (A6)\n" +
	                "| | | | \\-Ethel Waters (A6)\n" +
	                "| | | | \\-Anna May Wong (A6)\n" +
	                "| | | \\-Doris Day (A7)\n" +
	                "| | | \\-Carole Lombard (A6)\n" +
	                "| | | \\-Sidney Poitier (A4)\n";
		String strHierarchy=new ReviewProcessHierarchyImpl().execute("src/test/resources/test-addnew-reviewers-and-reviewees.txt");
		assertTrue(strHierarchy.trim().equals(copy.trim()));
	}
}
