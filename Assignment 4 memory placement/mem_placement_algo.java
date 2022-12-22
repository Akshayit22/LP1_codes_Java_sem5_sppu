
		// NAME - AKSHAY TELANG 
		// TE-B-73
		// LP-1 LAB ASSIGNMENT - 1
		// CPU SCHEDULING ALGORTHM - 4 types
		// 11/09/22


import java.lang.*;
import java.util.*;
import java.io.*;

public class mem_placement_algo{

    //  5 300 200 100 400 500 5 200 100 100 400 250
    public static void main(String args[]){
        mem_placement_algo obj = new mem_placement_algo();
        Scanner input = new Scanner(System.in);
        char ch;
    
        
            System.out.println("\t\t1.first fit algorithm\n\t\t2.Best fit alforithm\n\t\t3.Next fit algorithm\n\t\t4.Worst fit algorithm"); 
            System.out.println("\t\t5. TO EXIT ->|\nEnter your choice : ");
            ch = input.next().charAt(0);
            
            
            switch(ch){

                case '1':
                    obj.first_fit_algo();
                    break;
                case '2':
                    obj.best_fit_algo();
                    break;            
                case '3':
                    obj.next_fit_algo();
                    break;
                case '4':
                    obj.worst_fit_algo();
                    break;
                case '5':
                    
                    break;
                default : 
                    System.out.println("Enter correct choice .");
                    break;
            }
        

    }

    void first_fit_algo(){

        int NoProcesses  , NoBlocks , temp;
        Scanner sc = new Scanner( System.in );

        int[] BlockSize = new int[20];
        int[] OrignalBlockSize = new int[20];
        int[] ProcessSize = new int[20];
        int[] BlockNO = new int[20];
        int[] ProcessNO = new int[20];
        int[] allocation = new int[20];

        System.out.println("Enter Number of Memory block : ");
        NoBlocks = sc.nextInt();

        for (int i = 0; i < NoBlocks ; i++) {
            System.out.println("Enter Memory size for block "+(i+1)+" : ");
            temp = sc.nextInt();

            BlockNO[i] = (i+1);
            BlockSize[i] = temp;
            OrignalBlockSize[i] = temp;
            allocation[i] = 0; //block is not allocated 

        }

        System.out.println("Enter Number of Processes : ");
        NoProcesses = sc.nextInt();

        for (int i = 0; i < NoProcesses ; i++) {
            System.out.println("Ente size for Process "+(i+1)+" : ");
            temp = sc.nextInt();

            ProcessNO[i] = (i+1);
            ProcessSize[i] = temp; 

        }
        sc.close();
        System.out.println("1.FIRST fit algorithm\n");
        System.out.println("\n\n\t\t  Process No. \t  Process Size \t BlockNo \tOrignalBlocksize \t currBlockSize");
        for (int i = 0; i < NoProcesses ; i++){

            for (int j = 0; j < NoBlocks ; j++){

                if(BlockSize[j] >= ProcessSize[i]){

                    allocation[i] = (j+1);

                    BlockSize[j] -= ProcessSize[i];

                    
                    System.out.println("passs "+(i+1)+" \t\t" + ProcessNO[i] + "\t\t " + ProcessSize[i] + " \t B-"+BlockNO[j]+" \t\t "+OrignalBlockSize[j]+ " \t\t\t "+BlockSize[j]);


                    break;

                }

            }

        }
        System.out.println("\nResult : "); 
        System.out.println("\t  Process No. \t  Process Size \t\tBlock No.");
        for (int i = 0; i < NoProcesses ; i++) {

            System.out.print(" \t\t" + ProcessNO[i] + ". \t\t " + ProcessSize[i] + " \t\t");

            if (allocation[i] == 0) {
                System.out.print("Not Allocated");
            } else {
                System.out.print("B- "+allocation[i]);
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }
    
    void best_fit_algo(){
        
        
        int NoProcesses  , NoBlocks , temp;
        Scanner sc = new Scanner( System.in );

        int[] BlockSize = new int[20];
        int[] OrignalBlockSize = new int[20];
        int[] ProcessSize = new int[20];
        int[] BlockNO = new int[20];
        int[] ProcessNO = new int[20];
        int[] allocation = new int[20];

        System.out.println("Enter Number of Memory block : ");
        NoBlocks = sc.nextInt();

        for (int i = 0; i < NoBlocks ; i++) {
            System.out.println("Enter Memory size for block "+(i+1)+" : ");
            temp = sc.nextInt();

            BlockNO[i] = (i+1);
            BlockSize[i] = temp;
            OrignalBlockSize[i] = temp;
            allocation[i] = 0; //block is not allocated 

        }

        System.out.println("Enter Number of Processes : ");
        NoProcesses = sc.nextInt();

        for (int i = 0; i < NoProcesses ; i++) {
            System.out.println("Ente size for Process "+(i+1)+" : ");
            temp = sc.nextInt();

            ProcessNO[i] = (i+1);
            ProcessSize[i] = temp; 

        }
        sc.close();
        System.out.println("1.BEST fit algorithm\n");
        System.out.println("\n\n\t\t  Process No. \t  Process Size \t BlockNo \tOrignalBlocksize \t currBlockSize");
        int best = -1;
        for (int i = 0; i < NoProcesses ; i++) {
            best = -1;

            for (int j = 0; j < NoBlocks ; j++) {

                if (BlockSize[j] >= ProcessSize[i]) {
                    if (best == -1) {
                        best = j;
                    } 
                    else if (BlockSize[best] > BlockSize[j]) {
                        best = j;
                    }
                }
            }

            if (best != -1) {
                allocation[i] = (best+1);
                BlockSize[best] -= ProcessSize[i];
                System.out.println("passs "+(i+1)+" \t\t" + ProcessNO[i] + "\t\t " + ProcessSize[i] + " \t B-"+BlockNO[best]+" \t\t "+OrignalBlockSize[best]+ " \t\t\t "+BlockSize[best]);
            }

        }



        System.out.println("\nResult : "); 
        System.out.println("\t  Process No. \t  Process Size \t\tBlock No.");
        for (int i = 0; i < NoProcesses ; i++) {

            System.out.print(" \t\t" + ProcessNO[i] + ". \t\t " + ProcessSize[i] + " \t\t");

            if (allocation[i] == 0) {
                System.out.print("Not Allocated");
            } else {
                System.out.print("B- "+allocation[i]);
            }
            System.out.println();
        }
        System.out.println("\n\n");

    }

    void next_fit_algo(){

        int NoProcesses  , NoBlocks , temp;
        Scanner sc = new Scanner( System.in );

        int[] BlockSize = new int[20];
        int[] OrignalBlockSize = new int[20];
        int[] ProcessSize = new int[20];
        int[] BlockNO = new int[20];
        int[] ProcessNO = new int[20];
        int[] allocation = new int[20];

        System.out.println("Enter Number of Memory block : ");
        NoBlocks = sc.nextInt();

        for (int i = 0; i < NoBlocks ; i++) {
            System.out.println("Enter Memory size for block "+(i+1)+" : ");
            temp = sc.nextInt();

            BlockNO[i] = (i+1);
            BlockSize[i] = temp;
            OrignalBlockSize[i] = temp;
            allocation[i] = 0; //block is not allocated 

        }

        System.out.println("Enter Number of Processes : ");
        NoProcesses = sc.nextInt();

        for (int i = 0; i < NoProcesses ; i++) {
            System.out.println("Ente size for Process "+(i+1)+" : ");
            temp = sc.nextInt();

            ProcessNO[i] = (i+1);
            ProcessSize[i] = temp; 

        }
        sc.close();
        System.out.println("1.NEXT fit algorithm\n");
        System.out.println("\n\n\t\t  Process No. \t  Process Size \t BlockNo \tOrignalBlocksize \t currBlockSize");
        int x=0;
        for (int i = 0; i < NoProcesses ; i++){

            for (int j = 0; j < NoBlocks ; j++){

                
                if(BlockSize[j] >= ProcessSize[i]){

                    allocation[i] = (j+1);
                    BlockSize[j] -= ProcessSize[i];
                    x = j;
                    System.out.println("passs "+(i+1)+" \t\t" + ProcessNO[i] + "\t\t " + ProcessSize[i] + " \t B-"+BlockNO[j]+" \t\t "+OrignalBlockSize[j]+ " \t\t\t "+BlockSize[j]);
                    break;
                }

            }

        }


        System.out.println("\nResult : "); 
        System.out.println("\t  Process No. \t  Process Size \t\tBlock No.");
        for (int i = 0; i < NoProcesses ; i++) {

            System.out.print(" \t\t" + ProcessNO[i] + ". \t\t " + ProcessSize[i] + " \t\t");

            if (allocation[i] == 0) {
                System.out.print("Not Allocated");
            } else {
                System.out.print("B- "+allocation[i]);
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

    void worst_fit_algo(){
        
        int NoProcesses  , NoBlocks , temp;
        Scanner sc = new Scanner( System.in );

        int[] BlockSize = new int[20];
        int[] OrignalBlockSize = new int[20];
        int[] ProcessSize = new int[20];
        int[] BlockNO = new int[20];
        int[] ProcessNO = new int[20];
        int[] allocation = new int[20];

        System.out.println("Enter Number of Memory block : ");
        NoBlocks = sc.nextInt();

        for (int i = 0; i < NoBlocks ; i++) {
            System.out.println("Enter Memory size for block "+(i+1)+" : ");
            temp = sc.nextInt();

            BlockNO[i] = (i+1);
            BlockSize[i] = temp;
            OrignalBlockSize[i] = temp;
            allocation[i] = 0; //block is not allocated 

        }

        System.out.println("Enter Number of Processes : ");
        NoProcesses = sc.nextInt();

        for (int i = 0; i < NoProcesses ; i++) {
            System.out.println("Ente size for Process "+(i+1)+" : ");
            temp = sc.nextInt();
            
            ProcessNO[i] = (i+1);
            ProcessSize[i] = temp; 
            
        }
        sc.close();
        System.out.println("1.WORST fit algorithm\n");
        System.out.println("\n\n\t\t  Process No. \t  Process Size \t BlockNo \tOrignalBlocksize \t currBlockSize");
        int worst = -1;
        for (int i = 0; i < NoProcesses ; i++) {
            worst = -1;
            
            for (int j = 0; j < NoBlocks ; j++) {

                if (BlockSize[j] > ProcessSize[i]) {
                    if (worst == -1) {
                        worst = j;
                    } 
                    else if (BlockSize[j] > BlockSize[worst]) {
                        worst = j;
                    }
                }
            }

            if (worst != -1) {
                allocation[i] = (worst + 1);
                BlockSize[worst] -= ProcessSize[i];
                System.out.println("passs "+(i+1)+" \t\t" + ProcessNO[i] + "\t\t " + ProcessSize[i] + " \t B-"+BlockNO[worst]+" \t\t "+OrignalBlockSize[worst]+ " \t\t\t "+BlockSize[worst]);
            }

        }

        
        
        System.out.println("\nResult : "); 
        System.out.println("\t  Process No. \t  Process Size \t\tBlock No.");
        for (int i = 0; i < NoProcesses ; i++) {
            
            System.out.print(" \t\t" + ProcessNO[i] + ". \t\t " + ProcessSize[i] + " \t\t");

            if (allocation[i] == 0) {
                System.out.print("Not Allocated");
            } else {
                System.out.print("B- "+allocation[i]);
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }   
    //  5 300 200 100 400 500 5 200 100 100 400 250
}
	

        // NAME - AKSHAY TELANG 
		// TE-B-73
		// LP-1 LAB ASSIGNMENT - 1
		// CPU SCHEDULING ALGORTHM - 4 types
		// 11/09/22
