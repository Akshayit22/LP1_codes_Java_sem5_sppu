		// NAME - AKSHAY TELANG 
		// TE-B-73
		// LP-1 LAB ASSIGNMENT - 1
		// CPU SCHEDULING ALGORTHM - 4 types
		// 11/09/22


import java.lang.*;
import java.util.*;

public class cpu_scheduling_algo{
	class proccesse{
    
		int prid;// process id
		int bur;// burst time
		int arr;// arrival time
		int prior;// process priority
		int com;// completion time
		int wait;// waiting time 
		int tat;// turn around time
	
	}

	
    public static void main(String args[])throws Exception{
			
			cpu_scheduling_algo obj = new cpu_scheduling_algo();
			boolean exitt=true;
			char ch;
            Scanner s = new Scanner(System.in);
        //do{
            
            System.out.println("\n\nENTER WHICH SCHEDULING ALGORTHM TO EXICUTE :\n");
            System.out.println("\t\t1. (FCFS) FRIST COME FRIST SEREVE ."); 
            System.out.println("\t\t2. (SJF) SHORTEST JOB FIRST ."); 
            System.out.println("\t\t3. PRIORITY  ."); 
            System.out.println("\t\t4. ROUND ROBIN .\n\t\t5.TO EXIT. ->|\n"); 
            System.out.println("Enter the choice : ");
			ch = s.next().charAt(0);
			
            
            switch(ch){
                    case '1':
                        obj.fcfs_f();
                        break;
                    
                    case '2':
                        obj.sjf_f();
                        break; 
                    
                    case '3':
                        obj.prior_fuction(); //priority
                        break; 
                        
                    case '4':
                        obj.rr_func();//round robin
                        break;
                        
                    case '5':
						//exitt = false;
                        //return;
                        break;
					case '0':
						//exitt = false;
						break;
                    default:
                        System.out.println("Enter correct choice  "); 
                        break;                              
            }

        //}while(exitt);
		//s.close();
    }

	void fcfs_f(){

		int n,ele;
		int arr[] = new int[20];
		int bur[] = new int[20];
		int wait[] = new int[20];
		int tat[] = new int[20];
		Scanner sc=new Scanner(System.in);

		System.out.println("Enter number of processes : ");
 		n = sc.nextInt();

		for(int i=0;i<n;i++){
			System.out.println("Enter Arrival time for process " + (i+1) + " :" );
			ele = sc.nextInt();
			arr[i] = ele;
			System.out.println("Enter Burst time for process " + (i+1) + " :" );
			ele = sc.nextInt();
			bur[i] = ele;
		}
		sc.close();

		wait[0] = 0; //first process with waiting time zero
		tat[0] = bur[0];
		
		int sum_wait = wait[0] , sum_tat = tat[0];

		for(int i=1;i<n;i++){
			wait[i] = bur[i-1] + wait[i-1];
			sum_wait += wait[i];
			
			
			tat[i] = wait[i] +  bur[i] ;
			sum_tat += tat[i]; 
		}
		System.out.println("(FCFS) FRIST COME FRIST SEREVE :  \n");
		System.out.println("Process NO. \tArrival time \tBrust time \tWaiting time \tTurn around time"); 
		for(int i=0;i<n;i++){
			System.out.println("\t"+(i+1)+"\t\t"+arr[i]+"\t\t"+bur[i]+"\t\t"+wait[i]+"\t\t"+tat[i]); 
		}


		System.out.println("avg waiting time  :  "+(sum_wait / n)  );
		System.out.println("avg  turn around time :  " + (sum_tat /n) +"\n\n");

		
	}

	void sjf_f(){

		int n,ele;
		int prid[] = new int[20];
		int bur[] = new int[20];
		int wait[] = new int[20];
		int tat[] = new int[20];
		System.out.println("Enter number of processes : ");
		Scanner sc1 =new Scanner(System.in);
 		n = sc1.nextInt();
		
		
		for(int i=0;i<n;i++){
			prid[i] = i+1;
			System.out.println("Enter Burst time for process " + (i+1)+ " :" );
			ele = sc1.nextInt();
			bur[i] = ele;
		}
		sc1.close();

		int temp;
		for (int i = 0; i < n; i++) {     
            for (int j = i+1; j < n; j++) {     
			    if(bur[i] > bur[j]) {    
				   temp = bur[i];    
				   bur[i] = bur[j];    
				   bur[j] = temp;
				   
				   temp = prid[i];
				   prid[i] = prid[j];
				   prid[j] = temp;
			    }     
        	}     
    	}
		
		
		
		wait[0] = 0; //first process with waiting time zero
		tat[0] = bur[0];
		
		float sum_wait = wait[0] , sum_tat = tat[0];
		for(int i=1;i<n;i++){
			wait[i] = bur[i-1] + wait[i-1];
			sum_wait += wait[i];
			
			tat[i] = wait[i] + bur[i];
			sum_tat += tat[i];
		}
		
		System.out.println("(SJF) SHORTEST JOB FIRST :\n");
		System.out.println("Process NO. \tBrust time \tWaiting time \tTurn around time"); 
		for(int i=0;i<n;i++){
			System.out.println("\tP"+prid[i]+"\t\t"+bur[i]+"\t\t"+wait[i]+"\t\t"+tat[i]); 
		}


		System.out.println("avg waiting time  :  "+(sum_wait / n)  );
		System.out.println("avg  turn around time :  " + (sum_tat /n) +"\n\n");		
		System.out.print("Gantt chart : "+"\n\t"); 
		for(int i=0;i<n;i++){
			System.out.print(" -----> P"+prid[i]); 
		}
	
	}

	void prior_fuction(){

        int n,ele;
        Scanner sc2 =new Scanner(System.in);
        System.out.println("Enter number of processes : ");
 		n = sc2 .nextInt();
		
        proccesse[] objarr;
        objarr = new proccesse[n];

		for(int i=0;i<n;i++){
            objarr[i] = new proccesse();
            objarr[i].prid = i+1;

			System.out.println("Enter Burst time for process " + (i+1)+ " :" );
			ele = sc2.nextInt();
			objarr[i].bur = ele;

            System.out.println("Enter Priority for process " + (i+1)+ " :" );
			ele = sc2.nextInt();
			objarr[i].prior = ele;
		}
		sc2.close();

        proccesse temp = new proccesse();
		for (int i = 0; i < n; i++) {     
            for (int j = i+1; j < n; j++) {     
			    if(objarr[i].prior > objarr[j].prior) {    
				   temp = objarr[i];    
				   objarr[i] = objarr[j];    
				   objarr[j] = temp;
				   
			    }     
        	}     
    	}
        //System.out.println(objarr[0].bur);

        objarr[0].wait = 0; //first process with waiting time zero
		objarr[0].tat = objarr[0].bur;
		
		float sum_wait = objarr[0].wait , sum_tat = objarr[0].tat;
		for(int i=1;i<n;i++){
			objarr[i].wait = objarr[i-1].bur + objarr[i-1].wait;
			sum_wait += objarr[i].wait;
			
			objarr[i].tat = objarr[i].wait + objarr[i].bur;
			sum_tat += objarr[i].tat;
		}
		System.out.println("PRIORITY ALGO : \n");
        System.out.println("Process NO. \tBrust time \tWaiting time \tTurn around time"); 
		for(int i=0;i<n;i++){
			System.out.println("\tP"+objarr[i].prid+"\t\t"+objarr[i].bur+"\t\t"+objarr[i].wait+"\t\t"+objarr[i].tat); 
		}


		System.out.println("avg waiting time  :  "+(sum_wait / n)  );
		System.out.println("avg  turn around time :  " + (sum_tat /n) +"\n\n");		
		System.out.print("Gantt chart : "+"\n\t"); 
		for(int i=0;i<n;i++){
			System.out.print(" -----> P"+objarr[i].prid); 
		}
		System.out.println("\n"); 
    }

	void rr_func(){
        int n,ele,Q;
        Scanner sc3 =new Scanner(System.in);
        System.out.println("Enter number of processes : ");
        n = sc3.nextInt();
        
        System.out.println("Enter Quantaum constant : ");
        Q = sc3.nextInt();
		
        int initial[] = new int[n];
        proccesse[] objarr;
        objarr = new proccesse[n];

		for(int i=0;i<n;i++){
            objarr[i] = new proccesse();
            objarr[i].prid = i+1;

			System.out.println("Enter Burst time for process " + (i+1)+ " :" );
			ele = sc3.nextInt();
			objarr[i].bur = ele;
            initial[i] = ele;
            
		}
		sc3.close();
        System.out.print("\nGantt chart : "+"\n\t"); 
        
        //proccesse temp = new proccesse();
        float sum_wait = objarr[0].wait , sum_tat = objarr[0].tat;
		int j=0,cnt=0,run=0;
        
            while(true){
                j %= n;
                //System.out.println("  "+ (j+1) + "  " + objarr[j].bur + " " + run); 
                
                    if(objarr[j].bur == 0){
                        if(cnt == (n+1)){
                            System.out.print(" ("+run+")");
                            break;
                        }
                        cnt++;
                    }
                    else{
                        cnt = 0;
                        System.out.print(" ("+run+") -----> P"+objarr[j].prid);
                        if(objarr[j].bur < (Q+1)){
                            run += objarr[j].bur;
                            objarr[j].bur = 0;
                            objarr[j].com = run;

                            objarr[j].com =run;
                        }
                        else{
                            objarr[j].bur -= Q;
                            run += Q;
                            objarr[j].com = run;
                        }

                    }
                j++;
                //run++;
            }
            
            for(int i=0;i<n;i++){
                objarr[i].wait = objarr[i].com - initial[i];
                objarr[i].tat = objarr[i].wait + initial[i];
                sum_wait += objarr[i].wait;
                sum_tat += objarr[i].tat;
                //System.out.println("\n"+objarr[i].com); 
            }

            System.out.println("ROUND ROBING SCHEDULING ALGO :\n");
            System.out.println("\n\nProcess NO. \tBrust time \tCompletion \tWaiting time \tTurn around time"); 
		for(int i=0;i<n;i++){
			System.out.println("\tP"+objarr[i].prid+"\t\t"+initial[i]+"\t\t"+objarr[i].com+"\t\t"+objarr[i].wait+"\t\t"+objarr[i].tat); 
		}


		System.out.println("\n\navg waiting time  :  "+(sum_wait / n)  );
		System.out.println("avg  turn around time :  " + (sum_tat /n) +"\n\n");		
        
		
            
    }
}

		// NAME - AKSHAY TELANG 
		// TE-B-73
		// LP-1 LAB ASSIGNMENT - 1
		// CPU SCHEDULING ALGORTHM - 4 types
		// 11/09/22


/*
how to use built in Array.sort function


class SortByCPU implements Comparator<Task> {
        public int compare(Task a, Task b)
        {
            return a.cpuTime - b.cpuTime;
        }
    }

    public void shortestJobFirstWithAllAtArivalZero(){
 	    // Sort the tasks array
 	    Arrays.sort(tasks, new SortByCPU());



*/
