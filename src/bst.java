import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Arrays;

// Test binary search trees (bst).
public class bst {

	private static int N = 20;
	
    // Test program
	public static void test(int [] keys, int [] ops, long [] times ) {
		int i;
		
		long start, finish;
		
		if (N <= 600000) {
			start = System.currentTimeMillis();
			treeNode bst = treeNode.nullnode;
			for(i = 0; i < N; i++) { 
				switch (ops[i]) {
				case 0:   // insert
				case 1: bst = bst.insert( keys[i] ); break;
				case 2: bst = bst.remove( keys[i] ); break;
				case 3: bst.find(keys[i]); break;
				}
			}		
//			if (N < 100) {
//				System.out.println( "Binary search tree:");
//				bst.printTree( ); System.out.println();
//			}
			bst = treeNode.nullnode;	
			finish = System.currentTimeMillis();
			times[0] = finish-start;
			//System.out.println("Binary search tree: " + (finish-start) + " milliseconds.");
		} else
			//System.out.println("Binary search tree skipped due to large size.");
		
		start = System.currentTimeMillis();
		avltree avl = new avltree();
		for(i = 0; i < N; i++) { 
			switch (ops[i]) {
			case 0:   // insert
			case 1: avl.insert( keys[i] ); break;
			case 2: avl.remove( keys[i] ); break;
			case 3: avl.find( keys[i] ); break;
			}
		}		
//		if (N < 100) {
//			System.out.println( "\nAVL search tree:");
//		    avl.getRoot().printTree( ); System.out.println();
//		}
		avl.clear();
		finish = System.currentTimeMillis();
		times[1] = finish-start;
        //System.out.println("AVL search tree: " + (finish-start) + " milliseconds.");
		
		start = System.currentTimeMillis();
		redblacktree rbt = new redblacktree();
		for(i = 0; i < N; i++) { 
			switch (ops[i]) {
			case 0:   // insert
			case 1: rbt.insert( keys[i] ); break;
			case 2: rbt.remove( keys[i] ); break;  
			case 3: rbt.find( keys[i] ); break;
			}
			//rbt.getRoot().printTree( ); System.out.println("RB");
		}
		if (N < 100) {
//			System.out.println( "\nRed-Black search tree:");
//		    rbt.getRoot().printTree( ); System.out.println();
		}
		finish = System.currentTimeMillis();
		times[2] = finish-start;
		rbt.checkRedBlack(); 
		rbt.clear();
        //System.out.println("Red-Black search tree: " + (finish-start) + " milliseconds.");
        
		start = System.currentTimeMillis();
		splaytree spt = new splaytree();
		for(i = 0; i < N; i++) { 
			for(i = 0; i < N; i++) { 
				switch (ops[i]) {
				case 0:   // insert
				case 1: spt.insert( keys[i] ); break;
				case 2: spt.remove( keys[i] ); break;
				case 3: spt.find( keys[i] ); break;
				}
			}
		}
		if (N < 100) {
//			System.out.println( "\nSplay search tree:");
//		    spt.getRoot().printTree( ); System.out.println();
		}
		spt.clear();
		finish = System.currentTimeMillis();
		times[3] = finish-start;
        //System.out.println("Splay search tree: " + (finish-start) + " milliseconds.");
        
        start = System.currentTimeMillis();
        Arrays.sort(keys);
		finish = System.currentTimeMillis();
        System.out.println("Sorting: " + (finish-start) + " milliseconds.");
       
	}

	public static void test1(int [] keys, int [] ops, long [] times ) {
		int i;
		long[] returnLong = new long[2];

		long startI, finishI, startR, finishR;

		if (N <= 600000) {
			startI = System.currentTimeMillis();
			treeNode bstI = treeNode.nullnode;
			for(i = 0; i < N; i++) {
				switch (ops[i]) {
					case 0:   // insert
					case 1:
						bstI = bstI.insert1( keys[i] );
						break;
					case 2:
						bstI = bstI.remove1( keys[i] );
						break;
					case 3:
						bstI.find(keys[i]);
						break;
				}
			}
			bstI = treeNode.nullnode;
			finishI = System.currentTimeMillis();
			times[0] = finishI-startI;
		}

		if (N <= 600000) {
			startR = System.currentTimeMillis();
			treeNode bstR = treeNode.nullnode;
			for(i = 0; i < N; i++) {
				switch (ops[i]) {
					case 0:   // insert
					case 1:
						bstR = bstR.insert(keys[i]);
						break;
					case 2:
						bstR = bstR.remove(keys[i]);
						break;
					case 3:
						bstR.find(keys[i]);
						break;
				}
			}
			bstR = treeNode.nullnode;
			finishR = System.currentTimeMillis();
			times[1] = finishR - startR;
		}
	}

	public static void test2(int [] keys, int [] ops, long [] times ) {
		int i;

		long start, finish;


			start = System.currentTimeMillis();
		avltree avl = new avltree();
		for(i = 0; i < N; i++) {
			switch (ops[i]) {
				case 0:   // insert
				case 1: avl.insert( keys[i] ); break;
				case 2: avl.remove( keys[i] ); break;
				case 3: avl.find( keys[i] ); break;
			}
		}
		avl.clear();
		finish = System.currentTimeMillis();
		times[0] = finish-start;

		start = System.currentTimeMillis();
		redblacktree rbt = new redblacktree();
		for(i = 0; i < N; i++) {
			switch (ops[i]) {
				case 0:   // insert
				case 1: rbt.insert( keys[i] ); break;
				case 2: rbt.remove( keys[i] ); break;
				case 3: rbt.find( keys[i] ); break;
			}
		}
		finish = System.currentTimeMillis();
		times[1] = finish-start;
		rbt.checkRedBlack();
		rbt.clear();

		start = System.currentTimeMillis();
		splaytree spt = new splaytree();
		for(i = 0; i < N; i++) {
			for(i = 0; i < N; i++) {
				switch (ops[i]) {
					case 0:   // insert
					case 1: spt.insert( keys[i] ); break;
					case 2: spt.remove( keys[i] ); break;
					case 3: spt.find( keys[i] ); break;
				}
			}
		}
		spt.clear();
		finish = System.currentTimeMillis();
		times[2] = finish-start;
	}

	public static void sort(int[] keys, int[] res, long[] times){
		int i;

		long start, finish;


		start = System.currentTimeMillis();
		avltree avl = new avltree();
		for(i = 0; i < N; i++) {
			avl.insert( keys[i] );
		}
		avl.getRoot().inorder(0, new int[res.length]);
		avl.clear();
		finish = System.currentTimeMillis();
		times[0] = finish-start;

		start = System.currentTimeMillis();
		redblacktree rbt = new redblacktree();
		for(i = 0; i < N; i++) {
			rbt.insert( keys[i] );
		}
		finish = System.currentTimeMillis();
		times[1] = finish-start;
		rbt.getRoot().inorder(0, new int[res.length]);
		//rbt.checkRedBlack();
		rbt.clear();

		start = System.currentTimeMillis();
		splaytree spt = new splaytree();
		for(i = 0; i < N; i++) {
			for(i = 0; i < N; i++) {
				spt.insert( keys[i] );
			}
		}
		spt.getRoot().inorder(0, new int[res.length]);
		spt.clear();
		finish = System.currentTimeMillis();
		times[2] = finish-start;
	}

	public static void question0() {
		int NUMS; 
		final int GAP  = 37;
		int i, j;
		long[] times = new long[4];

		int[] keys = new int[N];
        int[] ops = new int[N];
        NUMS = ((N & 1) == 1)? N+1 : N;
        Random randomGenerator = new Random();
        
  		//System.out.println( "Checking... " );
		j = 0;
		for(i = 0; i < N; i++) {
			j = ( j + GAP ) % NUMS;
			keys[i] = j;
			ops[i] = randomGenerator.nextInt(4);
//			if (N < 100) {
//				switch (ops[i]) {
//				case 0:   // both 0 and 1 are "insert"
//				case 1: System.out.print(" i" + keys[i] ); break;
//				case 2: System.out.print(" r" + keys[i] ); break;
//				case 3: System.out.print(" f" + keys[i] ); break;
//				}
//			}
		}
		//if (N<100) System.out.println("\nNote: i=insert; r=remove; f=find.\n");

		test( keys, ops, times);
	}

	public static void question1()
	{
		long btsI = 0;
		long btsR = 0;
		for(int q = 0; q < 500; q++){
			//System.out.println(q);

			int NUMS;
			final int GAP  = 37;
			int i, j;
			long[] times = new long[4];


			int[] keys = new int[N];
			int[] ops = new int[N];
			NUMS = ((N & 1) == 1)? N+1 : N;
			Random randomGenerator = new Random();

			//System.out.println( "Checking... " );
			j = 0;
			for(i = 0; i < N; i++) {
				j = ( j + GAP ) % NUMS;
				keys[i] = j;
				ops[i] = randomGenerator.nextInt(4);
			}
			test1(keys, ops, times);
			btsI += times[0];
			btsR += times[1];
		}
		System.out.println("Iterative Binary Search Tree Total: " + btsI + " Milliseconds.");
		System.out.println("Recursive Binary Search Tree Total: " + btsR + " Milliseconds.");
	}

	public static void question2(){
		long avl = 0;
		long rb = 0;
		long sly = 0;

		for(int q = 0; q < 500; q++){
			//System.out.println(q);

			int NUMS;
			final int GAP  = 37;
			int i, j;
			long[] times = new long[4];


			int[] keys = new int[N];
			int[] ops = new int[N];
			NUMS = ((N & 1) == 1)? N+1 : N;
			Random randomGenerator = new Random();

			j = 0;
			for(i = 0; i < N; i++) {
				j = ( j + GAP ) % NUMS;
				keys[i] = j;
				ops[i] = randomGenerator.nextInt(4);
			}
			test2(keys, ops, times);
			avl += times[0];
			rb += times[1];
			sly += times[2];
		}
		System.out.println("AVL Search Tree Total: " + avl + " Milliseconds.");
		System.out.println("Red Black Search Tree Total: " + rb + " Milliseconds.");
		System.out.println("Splay Search Tree Total: " + sly + " Milliseconds.");

	}

	public static void question3(){
		long avl = 0;
		long rb = 0;
		long sly = 0;

		for(int q = 0; q < 500; q++){
			//System.out.println(q);

			int NUMS;
			final int GAP  = 37;
			int i, j;
			long[] times = new long[4];


			int[] keys = new int[N];
			int[] ops = new int[N];
			NUMS = ((N & 1) == 1)? N+1 : N;
			Random randomGenerator = new Random();

			j = 0;
			for(i = 0; i < N; i++) {
				j = ( j + GAP ) % NUMS;
				keys[i] = j;
				ops[i] = randomGenerator.nextInt(4);
			}
			sort(keys, ops, times);
			avl += times[0];
			rb += times[1];
			sly += times[2];
		}
		System.out.println("AVL Search Tree Total: " + avl + " Milliseconds.");
		System.out.println("Red Black Search Tree Total: " + rb + " Milliseconds.");
		System.out.println("Splay Search Tree Total: " + sly + " Milliseconds.");
	}
	
	public static void main( String [ ] args ) {

		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            System.out.print("Please give number of elements to be inserted : ");
            N = Integer.parseInt(read.readLine());
        } catch(Exception ex){
            ex.printStackTrace();
        }

        System.out.println("Question0: ");
        question0();
        System.out.println("Question1: ");
        question1();
        System.out.println("Question2: ");
        question2();
        System.out.println("Question3: ");
        question3();
	}
}
