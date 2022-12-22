/*		NAME - AKSHAY TELANG 
		TE-B-73
		LP-1 LAB ASSIGNMENT - 1
		pass 1 of 2-pass assembler
		6/11/22


		Problem Statement: 
			Design suitable data structures and implement pass-I of a two-pass assembler for pseudo-
			machine in Java using object oriented feature. Implementation should consist of a few
			instructions from each category and few assembler directives.
*/


import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.BreakIterator;

public class pass1{  

	public static int line_counter=0,symt_ptr=0,opct_ptr=0,litt_ptr=0,poolt_ptr=0;
	public static String line;
	public static final int MAX = 25;
	public static String SymbolTab[][] = new String[MAX][3];	//SYMBOL	ADDRESS		LENGTH
	//public static String OpcodeTab[][] = new String[MAX][3];	//MNEMONIC	CLASS		INFO
	public static String LiteralTab[][] = new String[MAX][2];	//LITERAL	ADDRESS
	public static int PoolTab[] = new int[MAX];		//NO.OF LITERAL ADDRESSED
	public static String IC_statm[] = new String[MAX]; // intermediate code 
	public static ArrayList<String> line_tokens = new ArrayList<String>();	//contains tokens of a line after spliting line

	public static void main(String args[])throws IOException{ 
		
		pass1 obj = new pass1();

		try{
			//String line;
			
			//readind from asm file
			FileInputStream input_file = new FileInputStream("sample.asm");       
			Scanner read_ptr = new Scanner(input_file);

			while(read_ptr.hasNextLine()){
			  	line = read_ptr.nextLine();
				
				//spliting line by spaces
				Scanner sc = new Scanner(line); 
				while (sc.hasNext()){  
					String tokens = sc.next();   // storing tokens in String array list which declared static 
					line_tokens.add(tokens);
					//System.out.print(tokens+" -- ");  
					
				}

				obj.processing();

				sc.close();
				line_tokens.clear(); // clearing array list for next line
			}
 			read_ptr.close();
		}  
		catch(IOException e){  
			System.out.println("Error occured : " + e);
		}  
		
		
	} 

	static void display()throws IOException{
		String line,dot_line="-------------------------------------------";

		FileWriter output_file = new FileWriter("output.txt");		// for writing into output file
 		BufferedWriter write_into = new BufferedWriter(output_file);

		write_into.write(dot_line + "\nSymbol table\n" + dot_line);
		write_into.newLine();

		System.out.println("\n\nsymbol table");
		for(int i=0;i<symt_ptr;i++){
			line = SymbolTab[i][0]+"\t"+SymbolTab[i][1]+"\t"+SymbolTab[i][2];
			System.out.println(line); 

			write_into.write(line); 	// wrtting into file
			write_into.newLine();
		}
		write_into.write(dot_line+"\nLitrel table\n"+dot_line);
		write_into.newLine();

		System.out.println("\n\nlitrel table");
		for(int i=0;i<litt_ptr;i++){
			line = LiteralTab[i][0]+"\t"+LiteralTab[i][1];
			System.out.println(line);

			write_into.write(line); 	// wrtting into file
			write_into.newLine(); 
		}

		write_into.write(dot_line + "\nPool table\n" + dot_line);
		write_into.newLine();

		System.out.println("\n\npool table");
		for(int i=0;i<symt_ptr;i++){
			line = Integer.toString(PoolTab[i]);
			System.out.println(line); 

			write_into.write(line); 	// wrtting into file
			write_into.newLine();
		}
		System.out.println();

		write_into.write(dot_line + "\nIC code\n" + dot_line);
		write_into.newLine();

		System.out.println("\n\nIC code ");
		for(int i=0;i<opct_ptr;i++){
			line = IC_statm[i];
			System.out.println(line); 

			write_into.write(line); 	// wrtting into file
			write_into.newLine();
		}
		System.out.println();


		write_into.close();
	}

	static void litrel_processing(){
		int addr = line_counter;
		for(int i=0;i<litt_ptr;i++){
			// is address is not assign to literal the assign address
			if(LiteralTab[i][1] == null){  
				
				LiteralTab[i][1] = Integer.toString(addr);
				addr++;

				IC_statm[opct_ptr] = "(DL,01)(C,"+LiteralTab[i][0].charAt(2)+")";
				opct_ptr++; //writting the Intermediate code

			}
			PoolTab[poolt_ptr] = (i+1); //pool table proceessing
		}
		poolt_ptr++;
	}

	void processing()throws IOException{
		int index = 0 , nu_tokens = line_tokens.size();
		System.out.println(line); 
	
		if(nu_tokens == 0){
			//do nothing
			return;
		}
		if(line.contains("START")){
			// LC initilizing
			index++;
			line_counter = Integer.parseInt(line_tokens.get(index));
			line_counter++;

			IC_statm[opct_ptr] = "(AD,01)(C," + line_tokens.get(index) + ")";
			opct_ptr++; //writting the Intermediate code
			return;
		}
		if(line.contains("END")){
			// pool table and litrel table
			litrel_processing();
			IC_statm[opct_ptr] = "\n(AD,02)";
			opct_ptr++; //writting the Intermediate code

			// writting into file
			display();
			return;
		}
		if(line.contains("DS") || line.contains("DC")){
			String symb_match = line_tokens.get(index);
			for(int i=0;i<symt_ptr;i++){
				if(SymbolTab[i][0].contains(symb_match)){
					SymbolTab[i][1] = Integer.toString(line_counter);
				}
			}
			if(line.contains("DS")){
				IC_statm[opct_ptr] = "(DL,02)(C," + line_tokens.get(index+2) + ")";
			}else{
				IC_statm[opct_ptr] = "(DC,01)(C," + line_tokens.get(index+2) + ")";
			}
			opct_ptr++; //writting the Intermediate code
		}
		if(nu_tokens == 4){
			// processing for lables
			SymbolTab[symt_ptr][0] = line_tokens.get(index);
			SymbolTab[symt_ptr][1] = Integer.toString(line_counter);
			SymbolTab[symt_ptr][2] = Integer.toString(1);
			symt_ptr++;
			index++;
		}
		// after lable processing 
		
		if(line.contains("LTORG")){
			// pool table and litrel table
			litrel_processing();
			line_counter++;
			return;
		}
		if(line_tokens.get(index).contains("MOVE")){
			int opcode = 0,reg_code =0;
			if(line_tokens.get(index).contains("MOVER")){
				opcode = 4;
			}else{
				opcode = 5;
			}index++;

			if(line_tokens.get(index).contains("AREG")){
				reg_code = 1;
			}else if(line_tokens.get(index).contains("BREG")){
				reg_code = 2;
			}else{
				reg_code = 3;
			}index++;

			if(line_tokens.get(index).contains("=")){
				LiteralTab[litt_ptr][0] = line_tokens.get(index);

				IC_statm[opct_ptr] = "(IS,0" +opcode+")("+reg_code+")(L,"+litt_ptr+")";
				opct_ptr++; //writting the Intermediate code
				
				litt_ptr++;
			}else{
				SymbolTab[symt_ptr][0] = line_tokens.get(index);
				SymbolTab[symt_ptr][2] = Integer.toString(1);

				IC_statm[opct_ptr] = "(IS,0" +opcode+")("+reg_code+")(S,"+symt_ptr+")";
				opct_ptr++; //writting the Intermediate code

				symt_ptr++;
			}

		}
		line_counter++;
		
	}
	
}  
