
/*		NAME - AKSHAY TELANG 
		TE-B-73
		LP-1 LAB ASSIGNMENT - 1
		pass 2 of 2-pass assembler
		6/11/22


		Problem Statement: 
				Implement Pass-II of two pass assembler for pseudo-machine in Java using object oriented
				features. The output of assignment-1 (intermediate file and symbol table) should be
				input for this assignment.
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class pass2 {

	public static String sym[][] = new String[10][2];
	public static String lit[][] = new String[10][2];


	public static void main(String[] Args) throws IOException{

		 String s = "";
		 int i=0;
		 read_symbols();
		 read_literals();
		 BufferedReader b1 = new BufferedReader(new FileReader("IC.txt"));

	     //FileWriter f1 = new FileWriter("Pass2.txt");

	     String line;
	     while((s=b1.readLine())!=null){
	     	 line = "";

	    	 if(s.contains("DL")){
	    	 	line = "+ 00 0 00" + s.substring(10,11);
	    	 	//System.out.println("+ 00 0 00" + s.substring(10,11));
	    	 	System.out.println(line);
	    	 	continue;

	    	 }
	    	 if(s.contains("AD")){
	    	 	System.out.println();
	    	 	continue;
	    	 }
	    	 if(s.contains("(IS,00)")){
	    	 	line = "+ 00 0 000";
	    	 	System.out.println(line);
	    	 	continue;
	    	 }
	    	 if(s.contains("IS")){
	    	 	line = "+ "+s.substring(4,6)+" "+s.substring(8,9)+" ";
	    	 	System.out.print(line);
	    	 	if(s.charAt(11) == 'L'){
	    	 		int id = Integer.parseInt(s.substring(13,14));
	    	 		id--;
	    	 		//get address for this litral
	    	 		line += lit[id][1]; 
	    	 		System.out.println(lit[id][1]);
	    	 	}
	    	 	if(s.charAt(11) == 'S'){
	    	 		int id = Integer.parseInt(s.substring(13,14));
	    	 		id--;
	    	 		//get address for this symbol
	    	 		line += sym[id][1];
	    	 		System.out.println(sym[id][1]);
	    	 	}
	    	 }
	     }
	     
	}

	static void read_symbols()throws IOException{
	     BufferedReader b2 = new BufferedReader(new FileReader("SYBTAB.txt"));
	     String s = "";int i=0;
		 while((s=b2.readLine())!=null){
	    	 String word[]=s.split("\t\t\t");
	    	 sym[i][0] = word[0];
	    	 sym[i][1] = word[1];
	    	 i++;
	     }
	     // for(int m=0;m<i;m++){
	     // 	System.out.println(sym[m][0] + "  " + sym[m][1]);
	     // }
	}

	static void read_literals()throws IOException{
	     BufferedReader b3 = new BufferedReader(new FileReader("LITTAB.txt"));
	     
	     String s = "";int i=0;
		 while((s=b3.readLine())!=null){
	    	 String word[]=s.split("\t\t");
	    	 lit[i][0] = word[0];
	    	 lit[i][1] = word[1];
	    	 i++;
	     }
	     // for(int m=0;m<i;m++){
	     // 	System.out.println(lit[m][0] + "  " + lit[m][1]);
	     // }
	}

}