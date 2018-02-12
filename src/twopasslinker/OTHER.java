//package linker;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileNotFoundException;

public class twoPass{
	static File file  ;
	static int currBaseAddr=0;
	static String useSym = null; 
	static Scanner fPass = null;
	static String[][] symbol = null;
	static int i=0,j=0, k=0; 
	static int numOfModules = 0;
	static int numDefinition = 0;
	static int numUses;
	static int totaluses = 0; 
	static String def = null;
	static int val = 0;
	static String value;
	static int[] baseAddrs;
	static int[] sizeofModule = null;
	static String[] progtxt = null;
	static ArrayList<Integer> address = new ArrayList<Integer>();
	static ArrayList<String> addressElement = new ArrayList<String>();
	static TreeMap<String, Integer> symtable = new TreeMap<String, Integer>();
	static TreeMap<String,Integer> modules = new TreeMap<String, Integer>();
	static String[][] usetable;
	public static void main(String[] args){
		//String input ="/Users/anandinichawla/Documents/workspace/linker/src/linker/";
		 //System.out.println(args.length);
		for(int i = 0; i < args.length;i++){
		     
			 System.out.println(args[i]);
		      
		       String input =  args[i];
		       
		      Pass1(input);
		      Pass2();
		    //  reset();
		 }
		
		
		//firstPass f1 = new firstPass();
		//Pass1(input);
		
		
		/*for(int i = 0; i < args.length;i++){
		      System.out.println("-------------------------------");
		      Pass1(args[i]);
		}*/
		
		
	}//main
	
	
	public static void Pass1(String input){
		
		//ArrayList<String> usedsym = new ArrayList<String>();
		//int updatedsize
		file = new File(input);
		try {
			
			fPass = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		numOfModules = fPass.nextInt();
		//System.out.println("numOfModules="+numOfModules);
		usetable = new String[numOfModules][numOfModules];
		baseAddrs = new int[numOfModules]; 
		sizeofModule = new int[numOfModules];
		
		baseAddrs[0]=0;
		
		 
		
		System.out.println("Symbol Table");
		System.out.println();
		while(fPass.hasNext()){
			
			//System.out.println("im back here now");
			//go through a single module
			//definition list 
			numDefinition = fPass.nextInt();
			symbol = new String[numDefinition][2];
			//System.out.println("numdef="+numDefinition);
			for(i=0;i<numDefinition;i++){
				//System.out.println("i="+i);
			 	def = fPass.next();
			 	//System.out.println("im here in def");
			 
			
			 	val =  fPass.nextInt(); 
			 	//System.out.println(def+"="+val);
				//	 System.out.println("fpass=" +val);
			 	
			 	//System.out.println(def+"="+val);
			 	value = Integer.toString(val);
			 	
			 	if(!symtable.isEmpty()){
			 		
			 		Set set = symtable.entrySet();
					Iterator iterator = set.iterator();
					
				 	//System.out.println("im in symtable if ");
				 	while(iterator.hasNext()) {
				 		//System.out.println("im back right after while loop");
				 		Map.Entry mentry = (Map.Entry)iterator.next();
				    	if(mentry.getKey().equals(def)){
				    		System.out.println("Error: This variable is multiply defined; first value used.");
				    		break;
				    	}
				    	
				    	else{
				    		//System.out.println("im putting stuff in symtable");
				    		symtable.put(def,  val + currBaseAddr);
				    		break;
				    	}
				    	
				    }//end of while
			 		
			 	}
			 	else {
			 		//System.out.println("im in symtable for the very first time");
			 		symtable.put(def,val + currBaseAddr);
			 	}
			 	 //System.out.println("im here now for symtable2");
				    
				    /*Set set2 = symtable.entrySet();
					Iterator iterator2 = set2.iterator();
					
				    
				    while(iterator2.hasNext()) {
				    	 Map.Entry mentry2 = (Map.Entry)iterator2.next();
				         System.out.print("key is: "+ mentry2.getKey() + " & Value is: ");
				         System.out.println(mentry2.getValue());
				      }*/
			 
				modules.put(def, j);
			 	
			}//end of for 
		

		    //uselist
		   
		   //System.out.println(def + j);
		    
			
		    numUses = fPass.nextInt();
		   // System.out.println("numUses="+numUses);
		   totaluses = totaluses + numUses; 
		    //System.out.println("j="+j);
		    //System.out.println("k="+k);
		    for(i=0;i<numUses;i++){
		    	usetable[j][i] = fPass.next();
		    	//usedsym.add(usetable[j][i]);
		    }//end of for 
			//System.out.println("numUses="+numUses);
			//System.out.println("useSym="+useSym);
		    //j++;
		   // System.out.println("im here now");
		    for(i=0;i<numOfModules;i++){
		    	for(k=0;k<numOfModules;k++){
		    		if(usetable[i][k]!=null){
		    			//System.out.println("use="+usetable[i][k]);
		    			
		    		}//end of if 
				}//end of inner for 
			}//end of outer for 
		    sizeofModule[j] = fPass.nextInt();
		    //System.out.println("val="+val);
		    if(val>sizeofModule[j]){
		     
		    	
		    	System.out.println("Error: In module " + j + " the def of " + def +  " exceeds the module size; zero (relative) used.");
		    	System.out.println();
		    	symtable.put(def,currBaseAddr);
		    }
//System.out.println("im here now for symtable");
		    
		    
		
		   // System.out.println("size="+sizeofModule[j]);
		    baseAddrs[j] = currBaseAddr;
		    currBaseAddr = currBaseAddr + sizeofModule[j];
		   // System.out.println("baseAddrs=" + baseAddrs[j]);
		  
		   
		  
		    //System.out.println("current base address="+currBaseAddr);
		    //System.out.println("sizeofmodule="+ sizeofModule[j]);
		    
		    for(i=0;i<sizeofModule[j];i++){
		    	addressElement.add(fPass.next());
		    	address.add(fPass.nextInt());
		    
		   
		    	
		    }//end of for
		    
		    j++;
					
		}	//while
		//System.out.println("im here now");
		Set set2 = symtable.entrySet();
		Iterator iterator2 = set2.iterator();
		
	    
	    while(iterator2.hasNext()) {
	    	 Map.Entry mentry2 = (Map.Entry)iterator2.next();
	         System.out.print(mentry2.getKey() + "=");
	         System.out.println(mentry2.getValue());
	      }
		
		
		
//		for(i=0;i<numDefinition;i++){
//			System.out.println(symbol[i][0]);
//			System.out.println(symbol[i][1]);
//			System.out.println("im in for");
//		}
//		for (Entry<String, Integer> entry : symtable.entrySet()) {
//		    String key = entry.getKey();
//		    Object value = entry.getValue();
//		    System.out.println("key="+key+"value="+value);
//		}
		/*for(i=0;i<numOfModules;i++){
			for(j=0;j<numOfModules;j++){
				System.out.print(usetable[i][j]+" ");
			}
			System.out.println();
		}*/
		
		j=0; //j keeps track of which module we are on here
		/*for(i=0;i<numOfModules;i++){
			System.out.println("size of module="+sizeofModule[i]);
		}*/
		//System.out.println("totaluses=" + totaluses);
	}//Pass1 

public static void Pass2(){
	HashMap<String,Integer> usedsym = new HashMap<>();
	//Multimapvalue<String,Integer> allusesym = new MultiMapvalue();
   //Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
    String[][] allusesym = new String[totaluses][3];
    //int[] numOfE = new int[numOfModules]
    final int maxmem = 199; 
	int i=0,k=0,j=0;
	//String j = 0; 
	boolean check1;
	boolean check2;
	
	for(i=0;i<numOfModules;i++){
		for(j=0;j<numOfModules;j++){
			if(usetable[i][j]!=null){
				allusesym[k][0] = usetable[i][j];
				allusesym[k][1] = Integer.toString(i);
				allusesym[k][2] = Integer.toString(0);
				//System.out.println("allusessym=" + 	allusesym[k][0]);
				k++;
			}
		}
	}
	i=0;
	j=0;
	k=0;
	
	Set set;
	  Iterator iterator;
	  
	int[] newaddr = new int[addressElement.size()];
	System.out.println();
	System.out.println("Memory Map");
	System.out.println();
	for(i=0;i<addressElement.size();i++){
		
		if((j-1)<numOfModules && i<baseAddrs[j]+sizeofModule[j]){
			//System.out.println("im in switch");
			//System.out.println("i="+i+"j="+j+"baseaddr="+baseAddrs[j]);
			//System.out.print(addressElement.get(i)+"=");
			//System.out.println(address.get(i));
			
			switch(addressElement.get(i)){
		
			case "R" : {
				
				newaddr[i] = address.get(i) + baseAddrs[j];
				
			//	int flag = newaddr[i];
				//System.out.println("address.get(i)= "+address.get(i) + "newaddr= " + newaddr[i]+ "baseaddrs="+ baseAddrs[j]);
				//System.out.println("newaddr[i]="+newaddr[i]+ "base="+baseAddrs[j]);
				if((newaddr[i]%1000)>sizeofModule[j] + baseAddrs[j]){
					newaddr[i] = newaddr[i] - newaddr[i]%1000;
					System.out.print(i+ ": " +addressElement.get(i)+" ");
			    	System.out.print(newaddr[i]);
					System.out.println(" Error: Relative address exceeds module size; zero used.");
					
				}
				else{
					
				
				System.out.print(i+ ": " +addressElement.get(i)+" ");
		    	System.out.println(newaddr[i]);
				//System.out.println(" Error: Relative address exceeds module size; zero used.");
			}
				
				break;
			
			}
			case "I":{
				newaddr[i]=address.get(i);
				System.out.print(i+ ": " + addressElement.get(i)+" ");
		    	System.out.println(newaddr[i]);
				break;
			}
			case "E":{
				
				int flag;
				flag = address.get(i);
				int addrofuse =0; 
				//System.out.println("flag"+flag);
				
				flag = address.get(i)%10;
				
				//System.out.println("size of hash map" + symtable.size());
				if(flag>(symtable.size()-1)){
					newaddr[i] = address.get(i);
					System.out.print(i+ ": " + addressElement.get(i)+" ");
					System.out.print(newaddr[i]);
					System.out.println(" Error: External address exceeds length of use list; treated as immediate.");
					
					break;
					
				}
				
				
				else{
					String use = usetable[j][flag];
					
					
					
					
					
					
					if(symtable.get(use)==null){
						addrofuse = 0;
						newaddr[i]=address.get(i)-flag+addrofuse;
						System.out.print(i + ": " + addressElement.get(i)+" ");
				    	System.out.print(newaddr[i]);
						System.out.println(" Error:" + use +" is not defined; zero used");
						
						usedsym.remove(use);
						//map.remove(use);
						
					
					}
					
					else{
						//System.out.println("use="+use+"flag="+flag+"j="+j+symtable.get(use));
						addrofuse = symtable.get(use);
						newaddr[i]=address.get(i)-flag+addrofuse;
						System.out.print(i + ": " + addressElement.get(i)+" ");
				    	System.out.println(newaddr[i]);
				    	usedsym.put(use,j);
				    	//System.out.println("use=" + use);
				    	for(int m =0;m<totaluses;m++){
				    		//System.out.println("use=" + use);
				    		//System.out.println("allusesym=" + allusesym[m][0] );
				    		//System.out.println("allusesym[m][1] =" + allusesym[m][1]);
				    		//System.out.println("j=" + j);
				    		if((allusesym[m][0].equals(use) && allusesym[m][1].equals(Integer.toString(j)))){
				    			//allusesym[k][1] = Integer.toString(j);
				    			//System.out.println("im here in if ");
				    			allusesym[m][2] = "1";
				    		}
				    	}
				    	
				    	//List<Integer> list = Ijj);
				    	//map.put(use,j);
					}
					
				
					
					
					
					
				}
					
				
					
					
					break;
				
				
			}
				
				
				
				
				
				
				
				
				
				
				
				
				
			case "A":{
			  
				
				
				if((address.get(i)%1000)>maxmem){
					newaddr[i] = address.get(i)-address.get(i)%1000;
					System.out.print(i + ": " + addressElement.get(i)+" ");
			    	System.out.print(newaddr[i]);
					System.out.println(" Error: Absolute address exceeds machine size; zero used.");
					
				}
				else{
					newaddr[i] = address.get(i);
					System.out.print(i + ": " + addressElement.get(i)+" ");
			    	System.out.println(newaddr[i]);
				}
				
				break;
			}
			}//switch
		}//if 
		else{
			//System.out.println("this way now"+"i="+i+"j="+j+"baseaddr="+baseAddrs[j]);
			j++;
			i--;
		}
		
		 
	}//for 
	//System.out.println("all uses sym");
	for(k=0;k<totaluses;k++){
		//System.out.println(allusesym[k][0] + " " + "  " + allusesym[k][1] + " " + allusesym[k][2]);
        String temporary = allusesym[k][2];
		if((temporary.equals("0"))==true){
			System.out.println("Warning: In Module " + allusesym[k][1] + " " + allusesym[k][0] +  " appeared in use list but was not actually used");
		}
		
	}
	
	
	/*set = usedsym.entrySet();
		iterator = set.iterator();
		
		while(iterator.hasNext()){
			Map.Entry mentry = (Map.Entry)iterator.next();
			System.out.println(mentry.getKey());
			System.out.println(mentry.getValue());
		}
		
		while(iterator.hasNext()){
			Map.Entry mentry = (Map.Entry)iterator.next();
			check1 = false;
			check2 = false;
			int val = (int) mentry.getValue();
			System.out.println(mentry.getKey());
			System.out.println(mentry.getValue());
			for(int m=0;m<numOfModules;m++){
				check1 = false;
 			check2 = false;
				for(int n =0;n<numOfModules;n++){
					if((mentry.getKey()).equals(usetable[m][n])){
						
						check1 = true;
						if(m == val){
							check2=true;
							break;
						}
					}
						
				}
				if(check1==true && check2==false){
 				System.out.println("Warning: In Module " + m + " " +  mentry.getKey() +  " appeared in use list but was not actually used");
 			}
			}
			
				
			}*/
 
	
	
	//System.out.println(symtable.size());
	
	 Set set2 = symtable.entrySet();
		Iterator iterator2 = set2.iterator();
	
	  
		 
	    Object temp = null; 
	    int key_val;
	    while(iterator2.hasNext()) {
	    	check1 = false; 
	    	 Map.Entry mentry2 = (Map.Entry)iterator2.next();
	    	// System.out.println("mentry2.getKey()=" + mentry2.getKey());
	    	 set = usedsym.entrySet();
	 		 iterator = set.iterator();
	 		 temp = mentry2.getKey();
	    	  while(iterator.hasNext()){
	    		  Map.Entry mentry = (Map.Entry)iterator.next();
	    		 if((mentry2.getKey().equals(mentry.getKey()))){
	    			 //System.out.println("usedsym=" + usedsym.get(i));
		        	 check1 =true;
		        	 //System.out.println("im here");
		       }
	    		 
	    	 }
	    	 if(check1==false && temp!=null){
			    	System.out.println("Warning: " + temp + " was defined" + " in module " + modules.get(temp) + " but not used");
			    }
	         
	     }
	    
	   // System.out.println("im here now");
	  
	    
	   /* for(i=0;i<numOfModules;i++){
			for(j=0;j<numOfModules;j++){
				System.out.print(usetable[i][j]+" ");
			}
			System.out.println();
	    }*/
	    
	   
 		
	    
	    /*for(k=0;k<usedsym.size();k++){
	    	System.out.println("im here" + usedsym.get(k));
	    }*/
	    
	    
	  }//pass2 


	
}//twoPass 
	 
	 
	
	