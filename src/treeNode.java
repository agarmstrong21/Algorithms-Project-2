// Binary search tree nodes and standard methods:

public class treeNode {
	public int iData;             // data item (key)
	public int height;            // height of node
	public boolean isRed;         // for Red-black tree
	public treeNode left;         // this node's left child
	public treeNode right;        // this node's right child

	public static treeNode nullnode = new treeNode(0, -1);

	public boolean isEmpty() { return (this == nullnode); }
	
	public treeNode(int id, int h) {	  // constructor
	      iData = id;
	      height = h;       
	      isRed = false;
	      left = null;
	      right = null;
    }
	
	public treeNode(int id) {	  // constructor
	      iData = id;
	      height = 0;       
	      isRed = true;
	      left = nullnode;
	      right = nullnode;
    }
    
	public int size() {
        if (this == nullnode) return 0;
        else return 1 + left.size() + right.size();
    }
    
    public void adjHeight() {  // Adjust the height of the current node.
    	if (left == nullnode) 
    		if (right == nullnode) height = 0;
    		else this.height = this.right.height + 1;
    	else if (right == nullnode)
    		this.height = this.left.height + 1;
    	else this.height = Math.max(left.height, right.height) + 1;
    }
    
    public void getHeight() {  // compute the height of each node
    	if (left == nullnode) 
    		if (right == nullnode) height = 0;
    		else {
    			right.getHeight();
    			this.height = this.right.height + 1;
    		}
    	else if (right == nullnode) {
    		left.getHeight();
    		this.height = this.left.height + 1;    		
    	} else {
    		left.getHeight();
    		right.getHeight();
    		this.height = Math.max(left.height, right.height) + 1;
    	}
    }
    
    public int bheight() {  // return black height and check red-black violations   	if (left == nullnode) 
    	if (left == nullnode) {
    		if (right != nullnode) {
    			if (this.isRed && right.isRed) {
    				this.printTree();
    				//System.out.println( "\nred-red violation!");
    			}
    			int h = right.bheight();
    			if (h != 0) { 
    				this.printTree();
    				//System.out.println( "\nright bheight is higher!");
    			}
    		}
    		return (this.isRed)? 0 : 1;
    	} else if (right == nullnode) {
			if (this.isRed && left.isRed) {
				this.printTree();
				//System.out.println( "\nred-red left violation!");
			}
  			int h = left.bheight();
			if (h != 0) { 
				this.printTree();
				//System.out.println( "\nleft bheight is high!");
			}
			return (this.isRed)? 0 : 1;	
    	} else {
  			if (this.isRed && right.isRed) {
				this.printTree();
				//System.out.println( "\nred-red right violation!");
			}
  			if (this.isRed && left.isRed) {
				this.printTree();
				//System.out.println( "\nred-red left violation!");
			}
    		int h1 = left.bheight();
    		int h2 = right.bheight();
    		if (h1 != h2) { 
				this.printTree();
				//System.out.println( "\nbheight is diff!");
			}
    		return (isRed)? h1 : h1+1;
    	}
    }
    
    public treeNode rotateToRight() {
      treeNode p = this.left;  // left child of this node
      this.left = p.right;
      p.right = this;
      return p;
    }

    public treeNode rotateToLeft() {
      treeNode p = this.right;
      this.right = p.left;
      p.left = this;
      return p;
    }

    public treeNode doubleRotateToRight() {
      this.left = this.left.rotateToLeft();
      return this.rotateToRight();
    }

    public treeNode doubleRotateToLeft() {
      this.right = this.right.rotateToRight();
      return this.rotateToLeft();
    }

	public treeNode find0(int key) {  // RECURSIVE find node with given key
		if (this == nullnode) return nullnode;
		if (this.iData == key)
			return this;
		else if (this.iData < key) 
			return (this.right == nullnode)? nullnode : this.right.find0(key);
		else if (this.left == nullnode)
			return nullnode;
		else
			return this.left.find0(key);
	}  // end find0()

	public treeNode find(int key) {  // non-RECURSIVE find node with given key
		treeNode current = this;    
		while (true) {
	        if (current == nullnode) return current;  
	        if (current.iData == key) return current;
	        else if (current.iData < key) 
	            current = current.right;
	        else current = current.left;
		}
	}  // end find()

    public treeNode findMin( ) {  // return the node with minimum key
        if (left != nullnode) return left.findMin();
        return this;
    }

    public treeNode findMax( ) { // return the node with maximum key
        if (right != nullnode) return right.findMax();
        return this;
    }
    
    public treeNode predecessor( int x ) {
		// Search for the predecessor node of x in the tree rooted by this node.
		// The predecessor is the maximal node among all nodes in the tree whose iData is less than x.
		// Cases: external left, look up until you can take a left edge -- No par field :(
		//      external right, parent node
		//      internal node: farthest right child in left subtree
		treeNode pred = null;
		treeNode temp = this;
		while( true) {
			if( temp == null) {
				return pred; // x doesn’t appear in t
			}
			if (temp.iData < x){
				pred = temp; // pred remembers the last right turn
				temp = temp.right;
			}
			else if (this.iData > x) {
				temp = temp.right;
			}
			else {// key(t) = x
				if (temp.left == null) return pred;
				return pred.left.findMax();
			}
		}

	}
    public treeNode successor( int x ) {
    	// Search for the successor node of x in the tree rooted by this node.
    	// The successor is the minimal node among all nodes in the tree whose iData is greater than x.
		treeNode succ = null;
		treeNode temp = this;
		while( true) {
			if( temp == null) {
				return succ; // x doesn’t appear in t
			}
			if (temp.iData < x){
				succ = temp; // pred remembers the last right turn
				temp = temp.right;
			}
			else if (this.iData > x) {
				temp = temp.right;
			}
			else {// key(t) = x
				if (temp.right == null) return succ;
				return succ.right.findMax();
			}
		}
    }
    
    public treeNode deleteMax( treeNode par ) { 
     // PRE: par != nullnode
    	// delete the link from parent par and return the node with maximum key
        if (right != nullnode) return right.deleteMax(this);
        if (par.right == this) par.right = left;
        else par.left = left;
        return this;
    }
    
    public treeNode insert(int k) {
    	if (this == nullnode) return new treeNode(k);    	
 	    if (k < this.iData) {
 	        this.left = this.left.insert(k);
 	    } else { // branch right
 	    	this.right = this.right.insert(k);
 	    } 
 	    return this;
 	}  // end insert

	public treeNode insert1(int k){
		treeNode inserted = new treeNode(k);
		treeNode temp = this;
		treeNode parent = nullnode;

		if(temp == nullnode){
			return inserted;
		}

		while(true) {
			parent = temp;
			if(k < temp.iData){
				temp = temp.left;
				if(temp == nullnode){
					parent.left = inserted;
					return this;
				}
			}else if(k > temp.iData){
				temp = temp.right;
				if(temp == nullnode){
					parent.right = inserted;
					return this;
				}
			}
		}
	}
    
    public treeNode remove(int x) {
        if (this == nullnode ) return nullnode;   // Item not found; do nothing
        if (x < this.iData) {
            this.left = this.left.remove(x);
        } else if (x > this.iData) {
            this.right = this.right.remove(x);
        } else if (this.left == nullnode) {
        	return this.right;
        } else if (this.right == nullnode ) { 
        	return this.left;
        } else { // Two children
            this.iData = (this.left).deleteMax(this).iData;
        }
        return this;
    }

    public treeNode remove1(int k){
		treeNode temp = this;
		treeNode parent = nullnode;
		boolean leftChild = false;

		if(temp == nullnode){
			return temp;
		}

		while(temp != nullnode){
			if(temp.iData == k){
				break;
			}

			parent = temp;
			if(k > temp.iData){
				leftChild = false;
				temp = temp.right;
			}else {
				leftChild = true;
				temp = temp.left;
			}
		}

		if(parent == nullnode){
			return remove1Helper(temp);
		}

		if(leftChild) {
			parent.left = remove1Helper(temp);
		}else{
			parent.left = remove1Helper(temp);
		}
		return this;
	}

	public static treeNode remove1Helper(treeNode n){
		if(n != nullnode){
			if(n.left == nullnode && n.right == nullnode) {
				return nullnode;
			}
			if(n.left != nullnode && n.right != nullnode){
				treeNode returnNode = nullnode;
				treeNode parent = n;
				treeNode temp = n.right;
				boolean leftChild = false;

				if(temp.right == nullnode){
					leftChild = true;
				}

				while(temp.left != nullnode){
					parent = temp;
					temp = temp.left;
				}

				if(leftChild){
					parent.left = temp.right;
				}else{
					parent.right = temp.right;
				}

				temp.right = null;
				returnNode = temp;
				n.iData = returnNode.iData;
			}else if(n.left != null){
				n = n.left;
			}else {
				n = n.right;
			}
		}
		return n;
	}
                
    public boolean isBST() { // return true if this tree is a search tree
    	if (left != nullnode) {
    		if (left.iData > this.iData) return false;
    		if (left.isBST() == false) return false;
    	}
    	if (right != nullnode) {
    		if (right.iData < this.iData) return false;
    		if (right.isBST() == false) return false;
    	}
    	return true;
    }
    
    public void printTree() {
        if (this == nullnode) return;
        System.out.print("(");
        if (this.left != nullnode) this.left.printTree();
        System.out.print(" "+this.iData);
        if (isRed == false) System.out.print("b");
        if (this.right != nullnode) this.right.printTree();
        System.out.print(")");
    }


	public int inorder(int i, int[] res){
		treeNode temp = this;

		if(temp == nullnode){
			return 0;
		}

		for(i = i; i < res.length; i++){
			if(temp == nullnode){
				return i;
			}
			res[i] = temp.findMin().iData;
			temp = temp.remove(res[i]);
		}
		return i;
	}


}
