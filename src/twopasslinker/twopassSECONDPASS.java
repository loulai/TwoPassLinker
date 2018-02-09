package twopasslinker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class twopassSECONDPASS {

	public static void main(String[] args) {
		File file = new File("src/input-5");
		
		// Initializing variables
		int currentBase = 0;
		int opcode;
		HashMap<String, Integer> defTable = new HashMap<String, Integer>();
		ArrayList<String> programTable = new ArrayList<>();
		HashMap<Integer, String> useTable = null;
	
		Scanner sc = null; 
		try {sc = new Scanner(file);} catch (FileNotFoundException e) {}
		
		// Getting total number of modules (will always be the first argument).
		int numModules = sc.nextInt();
		
		while(sc.hasNext()) {

			// 1) Definitions
			opcode = sc.nextInt();
			defTable = processDefinitions(opcode, sc, currentBase, defTable);
			
			// 2) Use table
			opcode = sc.nextInt();
			System.out.printf("(%d) ", opcode);
			
			
			for(int i = 0; i < opcode; i++) {
				String symbol = sc.next();
				System.out.print(symbol + "  "); // just increment n times. Likely need to change later.
			}
			System.out.println();
			
			// 3) Program Text
			opcode = sc.nextInt();
			processProgramText(opcode, sc, currentBase, programTable, useTable, defTable);
			currentBase = currentBase + opcode; // increment base
			//System.out.printf(">>>>>>>>>>> current base: %d\n" , currentBase);
		}
		System.out.println("\n=== FIRST PASS ===");
		System.out.println(defTable.toString());
		System.out.println(programTable + "\n==================");
		
		/* ------------SECOND PASS----------------- */
		Scanner sc2 = null; 
		try {sc2 = new Scanner(file);} catch (FileNotFoundException e) {}
		sc2.nextInt(); // num total modules
		currentBase = 0;
		
		while(sc2.hasNext()) {
			opcode = sc2.nextInt();
			//for(int i = 0; i < opcode; i++) {sc2.next(); sc2.nextInt();} // Definitions. Flying through
			defTable = processDefinitions(opcode, sc2, currentBase, defTable);
		
			// 2) Use table
			opcode = sc2.nextInt();
			System.out.printf("(%d) ", opcode);
			useTable = new HashMap<Integer, String>();
			
			for(int i = 0; i < opcode; i++) {
				String symbol = sc2.next();
				System.out.print(symbol + "  "); // just increment n times. Likely need to change later.
				useTable.put(i, symbol);
			}
			System.out.println();
			System.out.println("\t" + useTable.toString());
			
			// 3) Program Text
			opcode = sc2.nextInt();
			processProgramText(opcode, sc2, currentBase, programTable, useTable, defTable);
			//for(int i = 0; i < opcode; i++) {sc2.next(); sc2.nextInt();} // Again, Flying through
			currentBase = currentBase + opcode; // increment base
		}
		
	}
	
	private static HashMap<String, Integer> processDefinitions(int opcode, Scanner sc, int currentBase, HashMap table) {
		System.out.printf("(%d) ", opcode);
		
		for(int i = 0; i < opcode; i++) {
			String def = sc.next(); // definition 
			int relAddress = sc.nextInt();
			int absValue = relAddress + currentBase; // absolute value
			//System.out.printf("relative address: %d, current base: %d\n", relAddress, currentBase);
			table.put(def, absValue);
			System.out.printf("%s = %d", def, relAddress); 
		}
		System.out.println();
		return table;
	}
	
	private static void processProgramText(int opcode, Scanner sc, int currentBase, ArrayList programTable, HashMap<Integer, String> useTable, HashMap<String,Integer> defTable) {
		System.out.printf("(%d)\n", opcode);
		// System.out.printf(">>>>>>>>>>>>currentBase (%d)\n", currentBase); //DB
		int absValue = 0;
		for(int i = 0; i < opcode; i++) {
			String type = sc.next(); // type: R, E or I
			
			if(type.equals("R")) {
				absValue = sc.nextInt() + currentBase; 
			} else if (type.equals("E")) {
				if(useTable == null) {
					absValue = sc.nextInt();
				} else {
					int relAddress = sc.nextInt();
					int instruction = relAddress % 1000; // 0 or 1
					//System.out.printf("============== > instruction: %d\n", instruction);
					
					String symbol = useTable.get(instruction); // from {0:z, 1:xy} return xy
					//System.out.printf("============== > symbol: %s\n", symbol);
					
					int addition = defTable.get(symbol); // from {z=15, xy=2} return 2
					//System.out.printf("============== > addition: %s\n", addition);
					
					absValue = (relAddress - instruction) + addition;
				}
			} else {
				absValue = sc.nextInt();
			}
			programTable.add(type);
			programTable.add(absValue);
			System.out.printf("\t%3s %4d\n", type, absValue);
		}
	}

}
