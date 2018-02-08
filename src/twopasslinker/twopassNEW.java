package twopasslinker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class twopassNEW {

	public static void main(String[] args) {
		File file = new File("src/input-1");
		
		// Initializing variables
		int currentBase = 0;
		int[] base = new int[0];
		
		int opcode;
	
		HashMap<String, Integer> defTable = new HashMap<String, Integer>();
		ArrayList<String> programTable = new ArrayList<>();
		//HashMap<String, Integer> programText = new HashMap<String, Integer>();
		
		Scanner sc = null; 
		try {sc = new Scanner(file);} catch (FileNotFoundException e) {}
		
		// Getting total number of modules (will always be the first argument).
		int numModules = sc.nextInt();
		
		while(sc.hasNext()) {

			// 1) Definitions
			opcode = sc.nextInt();
			processDefinitions(opcode, sc, currentBase, defTable);
			
			// 2) Use table
			opcode = sc.nextInt();
			System.out.printf("opcode (%d) ", opcode);
		
			for(int i = 0; i < opcode; i++) {
				System.out.print(sc.next() + "  "); // just increment n times. Likely need to change later.
			}
			System.out.println();
			
			// 3) Program Text
			opcode = sc.nextInt();
			processProgramText(opcode, sc, currentBase, programTable);
		}
		System.out.println("\n=== FIRST PASS ===");
		System.out.println(defTable.toString());
		System.out.println(programTable + "\n=====================");
		
	}
	
	private static void processDefinitions(int opcode, Scanner sc, int currentBase, HashMap table) {
		System.out.printf("opCode (%d)\n", opcode);
		
		for(int i = 0; i < opcode; i++) {
			String def = sc.next(); // definition
			int absValue = sc.nextInt() + currentBase; // absolute value
			table.put(def, absValue);
			System.out.printf("\t%3s = %d\n", def, absValue);
		}
	}
	
	private static void processProgramText(int opcode, Scanner sc, int currentBase, ArrayList programTable) {
		System.out.printf("opCode (%d)\n", opcode);
		
		for(int i = 0; i < opcode; i++) {
			String def = sc.next(); // definition
			int absValue = sc.nextInt() + currentBase; // absolute value
			programTable.add(def);
			programTable.add(absValue);
			System.out.printf("\t%3s %4d\n", def, absValue);
		}
	}
}
