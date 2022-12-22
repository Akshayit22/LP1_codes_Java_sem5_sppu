import java.lang.*;
import java.util.*;
import java.io.*;
//import ../AkshayTelang/Document/sem5 javaprograms.*;

public class page_replacement_algo{

	public static ArrayList <Integer> alist = new ArrayList <Integer>();
	public static Scanner sc = new Scanner(System.in);
	public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public static int page_hit=0,page_fault=0;
	public static String[] state = new String[]{"Page Fault" , "Page Hit"};

	public static void main(String args[]) throws IOException{
		String s;
		String[] input;
		int page_frame,temp;

		page_replacement_algo obj = new page_replacement_algo();
		
		System.out.print("Enter page frames size :  ");
    	page_frame = sc.nextInt();
    		
    	int[] page_table = new int[page_frame];
    	
		System.out.println("Enter pages :  (please don't put extra spaces)");
		s = in.readLine();
		input = s.split(" ");
		// while accepting input please don't put extra spaces in-between or at the end of last number
		// or start of the first number --as it lead to exception while splite-converting to int
		for(int i=0 ; i < input.length;i++){
			temp = Integer.parseInt(input[i]); 
			alist.add(temp);
		}
		
		for(int i=0; i < page_frame;i++){
				page_table[i] = -1;    
		}
		
		System.out.println("\n\nInput data :-\npage frame size : "+page_frame);
		System.out.print("pages Entered : "); 
		for(int i=0;i<alist.size();i++){
	    	System.out.print(""+alist.get(i) + " ");  
		}
		
		System.out.println("\n");
		char c;
		boolean ex=true;
		do{
			System.out.println("1.First-In-First-Out\n2.Least Recently Used\n3.Optimal Algorithm\n4/0. To Exit\nEnter your choice"); 
			c = in.readLine().charAt(0);
			switch(c){
				case '1':
					System.out.println("First-In-First-Out");
					obj.fcfo(page_table,page_frame);
					break;
				case '2':
					System.out.println("Least Recently Used");
					obj.LRU(page_table, page_frame);
					break;
				case '3':
					System.out.println("Optimal Algorithm");
					obj.optimal(page_table,page_frame);
					break;
				case '0':
					ex = false;
					break;
				case '4':
					ex = false;
					break;
				default:
					System.out.println("please enter valid choice !!!"); 	
			}

		}while(ex);
	}


	void print_arr(int[] arr,int n,int idx){
		for(int i=0; i < n;i++){
				System.out.print(arr[i]+"\t");
		}System.out.println(state[idx]);
	}

  	void fcfo(int[] arr,int n){

  		int curr_page , get , temp=0 , state=0;
		page_hit=0;page_fault=0;
		Queue<Integer> q = new LinkedList<>();

		System.out.print("\tpages\t\t");
		for(int i=0; i < n;i++){
			System.out.print(i+"\t");
		}System.out.println("status\n");
		for(int i=0; i < n;i++){
			arr[i] = -1;    
		}
		//main working
		for(int i=0;i<alist.size();i++){
			state = 0;
			curr_page = alist.get(i);
	    	System.out.print("\t" + curr_page + "\t\t");
			get = 0;

			for(int page_index=0;page_index < n;page_index++){

				if(arr[page_index] == -1){
					arr[page_index] = curr_page;
					get = 1;	page_fault++;
					q.add(curr_page);
					break;
				}
				if(arr[page_index] == curr_page){
					get = 1;	page_hit++;		state = 1;
					//q.add(curr_page);
					break;
				}
			}
			// 7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1
			if(get == 0){
				
				temp = q.peek();
				for(int page_index=0;page_index < n;page_index++){
					if(arr[page_index] == temp){
						arr[page_index] = curr_page;
						get = 1;	page_fault++;
						q.remove();
						q.add(curr_page);
						break;
					}
				}
						
			}
			print_arr(arr, n, state);
		}


		System.out.println("\n\n"); 
		System.out.println("page hit : " + page_hit);
		System.out.println("page fault : " + page_fault); 
		System.out.println("\n\n"); 
 
  		q.clear();
		
  	}
  	
  	void LRU(int[] arr ,int n){
		int curr_page , get , temp,index , state=0;
		page_hit=0;page_fault=0;
		int[] distance = new int[alist.size()];

		System.out.print("\tpages\t\t");
		for(int i=0; i < n;i++){
			System.out.print(i+"\t");
		}System.out.println("status\n");
		for(int i=0; i < n;i++){
			arr[i] = -1;    
		}
		//main working
		for(int i=0;i<alist.size();i++){
			state = 0;
			curr_page = alist.get(i);
			
	    	System.out.print("\t" + curr_page + "\t\t");
			get = 0;

			for(int page_index=0;page_index < n;page_index++){
				
				if(arr[page_index] == -1){
					arr[page_index] = curr_page;
					get = 1;	page_fault++;	state = 0;
					break;
				}
				if(arr[page_index] == curr_page){
					get = 1;	page_hit++;		state = 1;
					break;
				}
				
			}// 7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1
			if(get == 0){
				temp = 0;
				index = 0;
				for(int m = 0;m < n; m++){
					distance[m] = 0;
					for(int j=(i-1);j>=0;j--){
						distance[m] += 1;
						if(arr[m] == alist.get(j)){
							break;
							
						}
					}
					if(distance[m] > temp){
						temp = distance[m];
						index = m;
					}
				}
				arr[index] = curr_page;
				page_fault++;

			}
			print_arr(arr, n, state);
		}
		
		System.out.println("\n\n"); 
		System.out.println("page hit : " + page_hit);
		System.out.println("page fault : " + page_fault); 
		System.out.println("\n\n"); 
	}

	void optimal(int[] arr , int n){
		int curr_page , get , temp,index , state=0;
		page_hit=0;page_fault=0;
		int[] distance = new int[alist.size()];

		System.out.print("\tpages\t\t");
		for(int i=0; i < n;i++){
			System.out.print(i+"\t");
		}System.out.println("status\n");
		for(int i=0; i < n;i++){
			arr[i] = -1;    
		}
		//main working
		for(int i=0;i<alist.size();i++){
			state = 0;
			curr_page = alist.get(i);
			
	    	System.out.print("\t" + curr_page + "\t\t");
			get = 0;

			for(int page_index=0;page_index < n;page_index++){
				
				if(arr[page_index] == -1){
					arr[page_index] = curr_page;
					get = 1;	page_fault++;	state = 0;
					break;
				}
				if(arr[page_index] == curr_page){
					get = 1;	page_hit++;		state = 1;
					break;
				}
				
			}// 7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1
			if(get == 0){
				temp = 0;
				index = 0;
				for(int m = 0;m < n; m++){
					distance[m] = 0;
					for(int j=(i+1);j<alist.size();j++){
						distance[m] += 1;
						if(arr[m] == alist.get(j)){
							break;
							
						}
					}
					if(distance[m] > temp){
						temp = distance[m];
						index = m;
					}
				}
				arr[index] = curr_page;
				page_fault++;

			}
			print_arr(arr, n, state);
		}
		
		System.out.println("\n\n"); 
		System.out.println("page hit : " + page_hit);
		System.out.println("page fault : " + page_fault); 
		System.out.println("\n\n"); 
	}
}

