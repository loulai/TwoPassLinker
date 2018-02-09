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
		int opcode;
		HashMap<String, Integer> defTable = new HashMap<String, Integer>();
		ArrayList<String> programTable = new ArrayList<>();
		
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
			System.out.printf("(%d) ", opcode);
		
			for(int i = 0; i < opcode; i++) {
				System.out.print(sc.next() + "  "); // just increment n times. Likely need to change later.
			}
			System.out.println();
			
			// 3) Program Text
			opcode = sc.nextInt();
			processProgramText(opcode, sc, currentBase, programTable);
			currentBase = currentBase + opcode; // increment base
			//System.out.printf(">>>>>>>>>>> current base: %d\n" , currentBase);
		}
		System.out.println("\n=== FIRST PASS ===");
		System.out.println(defTable.toString());
		System.out.println(programTable + "\n==================");
	}
	
	private static void processDefinitions(int opcode, Scanner sc, int currentBase, HashMap table) {
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
	}
	
	private static void processProgramText(int opcode, Scanner sc, int currentBase, ArrayList programTable) {
		System.out.printf("(%d)\n", opcode);
		// System.out.printf(">>>>>>>>>>>>currentBase (%d)\n", currentBase); //DB
		int absValue = 0;
		for(int i = 0; i < opcode; i++) {
			String type = sc.next(); // type: R, E or I
			
			if(type.equals("R")) {
				absValue = sc.nextInt() + currentBase; 
			} else if (type.equals("E")) {
				absValue = sc.nextInt(); 
			} else {
				absValue = sc.nextInt();
			}
			programTable.add(type);
			programTable.add(absValue);
			System.out.printf("\t%3s %4d\n", type, absValue);
		}
	}
}
