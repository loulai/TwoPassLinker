package twopasslinker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class twopasslinker2 {

	public static void main(String[] args) {
		File file = new File("src/input-3");
		
		int numModules;
		int s = -1;
		int b = 0;
		int currentBase = 0;
		String currentLine = "";
		HashMap<String, Integer> symbolTable = new HashMap<String, Integer>();
		
		try {
			Scanner sc = new Scanner (file);
			
			while(sc.hasNextLine()) {
				currentLine = sc.nextLine();
				//System.out.println(currentLine);
				//System.out.println(currentLine.isEmpty());
				if(!currentLine.isEmpty()) {
					if(s==-1) {
						
						// total number of modules
						numModules = currentLine.charAt(0);
						
					} else if(s%3==0 && currentLine.charAt(0) != '0' ) {
						
						// if there is a symbol defined, store in HashMap
						String[] splitCurrentLine = currentLine.split("\\s+");
						//System.out.println(currentLine);
						System.out.println(splitCurrentLine[2]);
						System.out.println(splitCurrentLine[3]);
						symbolTable.put(splitCurrentLine[1], Integer.parseInt(splitCurrentLine[2]) + currentBase); // <xy:2>
						
					} else if (b%3==0) {
						currentBase = currentBase + Integer.parseInt(currentLine.split(" ")[0]);
					}
					s++;
					b++;
				}
			}
			sc.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println(symbolTable.toString());
		
	}
	
	
	
	
}

