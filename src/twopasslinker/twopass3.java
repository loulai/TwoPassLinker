package twopasslinker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class twopass3 {

	public static void main(String[] args) {
		File file = new File("src/input-1");
		
		int currentBase = 0;
		String useSym = null;
		
		int numModules = 0;
	
		int[] base = new int[0];
		
		int opcode;
	
		HashMap<String, Integer> defTable = new HashMap<String, Integer>();
		HashMap<String, Integer> programText = new HashMap<String, Integer>();
		
		Scanner sc = null; 
		try {sc = new Scanner(file);} catch (FileNotFoundException e) {}
		
		// Getting total number of modules (will always be the first argument).
		numModules = sc.nextInt();
		
		while(sc.hasNext()) {

			opcode = sc.nextInt();
			process(opcode, sc, currentBase, defTable);
			
			// 2) Use table
			opcode = sc.nextInt();
			System.out.printf("opcode (%d) ", opcode);
		
			for(int i = 0; i < opcode; i++) {
				System.out.print(sc.next() + "  "); // just increment n times. Likely need to change later.
			}
			System.out.println();
			
			// 3) Program Text
			opcode = sc.nextInt();
			System.out.printf("opCode (%d)\n", opcode);
			
			for(int i = 0; i < opcode; i++) {
				
				String def = sc.next(); // definition
				int absValue = sc.nextInt() + currentBase; // absolute value
				defTable.put(def, absValue); // if want in one big file, change to defTable
				
				System.out.printf("\t%3s = %4d\n", def, absValue);
		
			}
		}
		
		System.out.println(defTable.toString());
		
		
		/*
		 *
		
		String[] progtxt = null;
		
		ArrayList<Integer> address = new ArrayList<Integer>();
		ArrayList<String> addressElement = new ArrayList<String>();
		
	
		*/
	}
	
	private static void process(int opcode, Scanner sc, int currentBase, HashMap table) {
		// 1) Definitions
		System.out.printf("opCode (%d)\n", opcode);
		
		for(int i = 0; i < opcode; i++) {
			String def = sc.next(); // definition
			int absValue = sc.nextInt() + currentBase; // absolute value
			table.put(def, absValue);
			System.out.printf("\t%3s = %4d\n", def, absValue);
		}
	}
}
