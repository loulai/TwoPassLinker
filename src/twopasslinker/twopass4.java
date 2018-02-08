package twopasslinker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class twopass3 {

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
						if(!currentLine.isEmpty()) {
							String[] splitLine = currentLine.split("\\s+");
							for (int i = 0; i<splitLine.length; i++) {
								System.out.print(i + ". " + splitLine[i] + " ");
								System.out.println(splitLine[i].equals(""));
								
							}
							if(s==-1) {
								numModules = currentLine.charAt(0);
								//System.out.println(splitLine[1]);
							}
						}
						s++;
					}
					
				}
				
				catch(FileNotFoundException e) {
					e.printStackTrace();
				}
	}
}
