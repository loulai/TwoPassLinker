package twopasslinker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class twopasslinker {

	public static void main(String[] args) {
		File file = new File("src/input-1");
		// System.out.println(file.getAbsolutePath()); //DB
		int numModules;
		try {
			Scanner sc = new Scanner (file);
			int i = 0;
			ArrayList<Integer> allAddresses = new ArrayList<Integer>();
			ArrayList<String> lineListArray = new ArrayList<String>();
			while(sc.hasNextLine()) {
				lineListArray.add(sc.nextLine());
				String[] lineArray = lineListArray.get(i).split(" ");
			
				// If first pass, take it as number of modules. Else, take as size of module (if mod 3 == 0)
				if(i == 0) {
					// Number of modules 
					numModules = Integer.parseInt(lineArray[i]);
					System.out.println("num modules: " + numModules );
				} else {
					// Module size
					if(!lineArray[0].isEmpty() ) {
						allAddresses.add(Integer.parseInt(lineArray[0]));
					}
					
				}
				i++;
				
			}
			
			// Printing out base address
			for(int k = 0; k < allAddresses.size(); k++) {
				//System.out.println(allAddresses.get(k));
				System.out.println(lineListArray.get(k));
			}
			sc.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
}

