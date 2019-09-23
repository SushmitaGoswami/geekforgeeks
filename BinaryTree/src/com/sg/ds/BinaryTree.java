package com.sg.ds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<T extends Number> {
	private Node<T> root;
	private int index=0;
	
	public BinaryTree() {
		root = null;
	}
	
	private Node<T> createNode(T data) {
		return new Node<T>(data);
	}
	
	private void inorder(Node<T> node) {
		if(node == null)
			return;
		inorder(node.getLeft());
		System.out.print(node.getData() + "  ");
		inorder(node.getRight());
	}
	
	private int findPos(T[] arr, T data, int lb, int ub) {
		for(int i = lb; i<=ub; i++) {
			if(arr[i] == data) {
				return i;
			}
		}
		return -1;
	}
	private Node<T> formFullBinTreeFromPreAndPost(T[] pre, T[] post, int lb, int ub) {
		if(lb > ub)
			return null;
		Node<T> node = createNode(pre[index]);
		if(root == null)
			root = node;
		if(lb == ub) {
			index++;
			return node;
		}
		int pos = findPos(post, pre[index+1],lb, ub-1);
		index++;
		node.setLeft(formFullBinTreeFromPreAndPost(pre, post, lb, pos));
		node.setRight(formFullBinTreeFromPreAndPost(pre, post, pos + 1 , ub-1));
		return node;
	}

	private void  printPostOrderFromInOrderAndPreOrder(T[] pre, T[] in, int lb, int ub) {
		if(lb>ub)
			return;
		if(lb == ub) {
			System.out.print(pre[index ++] + " , ");
			return;
		}
		T data = pre[index++];
		int pos = findPos(in, data, lb, ub);
		printPostOrderFromInOrderAndPreOrder(pre, in, lb, pos-1);
		printPostOrderFromInOrderAndPreOrder(pre, in, pos+1, ub);
		System.out.print(data + " , ");
	}

	private int getHeight(Node<T> node) {
		if(node == null)
			return -1;
		int leftH = getHeight(node.getLeft());
		int rightH = getHeight(node.getRight());
		return (leftH < rightH) ? (rightH + 1) : (leftH + 1);
	}
	
	private void reverseLevelOrder(Queue<Node<T>> queue) {
		if(queue.isEmpty())
			return;
		Node<T> node = queue.poll();
		if(node.getRight()!=null)
			queue.add(node.getRight());
		if(node.getLeft()!=null)
			queue.add(node.getLeft());
		
		reverseLevelOrder(queue);
		System.out.print(node.getData() + " , ");
		
	}
	
	private void perfectBinTreeCustomLevelOrderFromBottomToTop(Queue<Node<T>> queue) {
		if(queue.isEmpty())
			return;
		Node<T> second = queue.poll();
		Node<T> first = queue.poll();
		if(second.getLeft()!=null){
			queue.add(second.getLeft());
		}
		if(first.getRight()!=null)
			queue.add(first.getRight());
		if(second.getRight()!=null)
			queue.add(second.getRight());
		
		if(first.getLeft()!=null)
			queue.add(first.getLeft());
		
		perfectBinTreeCustomLevelOrderFromBottomToTop(queue);
		System.out.print(first.getData() + " , " + second.getData() + " , ");
	}
	
	private void reverseAlternateLevelsOfPerfectBinTreeRecursive(Node<T> root1, Node<T> root2, int level) {
		if(root1 == null || root2 == null)
			return;
		if(level%2 == 0) {
			T data = root1.getData();
			root1.setData(root2.getData());
			root2.setData(data);
		}
			
		reverseAlternateLevelsOfPerfectBinTreeRecursive(root1.getLeft(), root2.getRight(), level+1);
		reverseAlternateLevelsOfPerfectBinTreeRecursive(root1.getRight(), root2.getLeft(), level+1);
	}
	
	private Node<T> getInorderPredeccesor(Node<T> node) {
		Node<T> n = node.getLeft();
		while(n.getRight() != null && n.getRight() != node) {
			n= n.getRight();
		}
		return n;
	}
	
	private Node<T> getInorderSuccesor(Node<T> node){
		Node<T> n = node.getRight();
		while(n.getLeft() != null) {
			n= n.getLeft();
		}
		return n;
	}
	private void preOrderUsingMorrisTraversal(Node<T> node) {
		while(node!=null) {
			if(node.getLeft()== null) {
				System.out.print(node.getData() + " , ");
				node = node.getRight();
			}
			else {
				Node<T> succesor = getInorderPredeccesor(node);
				if(succesor.getRight() == null) {
					succesor.setRight(node);
					System.out.print(node.getData() + " , ");
					node = node.getLeft();
				}
				else {
					succesor.setRight(null);
					node = node.getRight();
				}
				
			}
		}
	}

	private Node<T> formFullBinaryTreeFromInAndPost(T[] in, T[] post, int lb, int ub) {
		if(lb>ub)
			return null;
		Node<T> node = createNode(post[index]);
		if(root == null)
			root = node;
		if(lb == ub) {
			index--;
			return node;
		}
		int pos = findPos(in, post[index], lb, ub);
		index--;
		node.setRight(formFullBinaryTreeFromInAndPost(in, post,  pos+1, ub));
		node.setLeft(formFullBinaryTreeFromInAndPost(in, post, lb, pos-1));
		return node;
	}
	
	private Node<T> formFullBinTreeFromInAndPre(T[] in, T[] pre, int lb, int ub){
		if(lb>ub)
			return null;
		T data = pre[index++];
		Node<T> node = createNode(data);
		
		if(root == null)
			root = node;
		if(lb == ub)
			return node;
		
		int pos = findPos(in, data, lb, ub);
		node.setLeft(formFullBinTreeFromInAndPre(in, pre, lb, pos-1));
		node.setRight(formFullBinTreeFromInAndPre(in, pre, pos+1, ub));
		return node;	
	}
	

	private Node<T> formFullBinTreeFromInAndLevel(T[] in, T[] level, int lb, int ub){
		if( lb > ub )
			return null;
		T data = level[0];
		Node<T> node = createNode(data);
		
		if(root == null)
			root = node;
		if(lb == ub)
			return node;
		int p = findPos(in, data, lb, ub);
		T[]left = (T[])new Number[p - lb];
		T[]right = (T[])new Number[ub - p];
		
		//Put all the nodes in right subtree of inorder into hashset;
		HashSet<T> hashset = new HashSet<>();
		for(int i = p+1;i<=ub;i++)
			hashset.add(in[i]);
		int j,k;
		j=k=0;
		for(int i = 1; i<level.length ; i++) {
			if(hashset.contains(level[i]))
				right[j++] = level[i];
			else
				left[k++] = level[i];
		}
		node.setLeft(formFullBinTreeFromInAndLevel(in, left, lb, p-1));
		node.setRight(formFullBinTreeFromInAndLevel(in, right, p+1, ub));
		return node;
	}
	
	private void inorderTraversal(Node<T> root, ArrayList<T>inorder) {
		if(root == null)
			return;
		inorderTraversal(root.getLeft(), inorder);
		inorder.add(root.getData());
		inorderTraversal(root.getRight(), inorder);
	}
	
	@SuppressWarnings("unchecked")
	private void replaceEachNodeWithSumOfInOrderPredecessorAndSuccesor(Node<T> node, ArrayList<T> list) {
		if(node == null)
			return;
		replaceEachNodeWithSumOfInOrderPredecessorAndSuccesor(node.getLeft(), list);
		T data = null;
		if(index == 0)
			data = list.get(1);
		else if(index == list.size() - 1)
			data = list.get(index - 1);
		else {
			
			if(node.getData() instanceof Double) {
				data = (T)((Number)(list.get(index - 1).doubleValue() + list.get(index +1).doubleValue()));  
			}
			else if(node.getData() instanceof Float){
				data = (T)((Number)(list.get(index - 1).floatValue() + list.get(index +1).floatValue()));  
			}
			else
				data = (T)((Number)(list.get(index - 1).intValue() + list.get(index +1).intValue()));  
		}
		index++;
		node.setData(data);
		replaceEachNodeWithSumOfInOrderPredecessorAndSuccesor(node.getRight(), list);
	}
	
	private void populateInOrderSuccesor(Node<T> node, Node<T> next) {
//		if(node== null)
//			return;
//		if(node.getLeft() !=null) {
//			Node<T> succesor = getInorderPredeccesor(node);
//			succesor.setNext(node);
//			populateInOrderSuccesor(node.getLeft());
//		}
//		if(node.getRight() !=null) {
//			node.setNext(getInorderSuccesor(node));
//			populateInOrderSuccesor(node.getRight());
//		}
		if(node !=null) {
			populateInOrderSuccesor(node.getRight(), next);
			node.setNext(next);
			next = node;
			populateInOrderSuccesor(node.getLeft(), next);
		}
	}

	private void printInorderFollowingNextPtr(Node<T> node) {
		if(node == null)
			return;
		while(node.getLeft()!=null)
			node = node.getLeft();
		
		while(node.getNext()!=null) {
			System.out.print(node.getData() + " , ");
			node = node.getNext();
		}
			
	}
	
	/***
	 * All the public api
	 */
	
	
	public void addNode(T data) {
		Node<T> node = createNode(data);
		if(root == null)
			root = node;
		else {
			Queue<Node<T>> queue = new LinkedList<Node<T>>();
			queue.add(root);
			while(!queue.isEmpty()) {
				Node<T> n = queue.poll();
				if(n.getLeft()!=null) {
					queue.add(n.getLeft());
				}
				else {
					n.setLeft(node);
					break;
				}
				if(n.getRight()!=null) {
					queue.add(n.getRight());
				}
				else {
					n.setRight(node);
					break;
				}
				
			}
		}
		
	}

	public void deleteNode(T data) {
		if(root == null)
			return;
		else if(root.getLeft() == null && root.getRight() == null) {
			if(root.getData() == data)
				root = null;
			return;
		}
		else {
			Queue<Node<T>> queue = new LinkedList<Node<T>>();
			queue.add(root);
			Node<T> target,  prev;
			target = prev = null;
			while(!queue.isEmpty()) {
				Node<T> node = queue.poll();
				if(node.getData() == data) {
					target = node;
				}
				if(node.getLeft() != null) {
					prev = node;
					queue.add(node.getLeft());
				}
				else {	
					if(target != null) {
						if(prev.getLeft() == node) {
							prev.setLeft(null);
						}
						else {
							prev.setRight(null);
						}
						target.setData(node.getData());
						break;
					}
				}
				if(node.getRight() != null)
					queue.add(node.getRight());
				else {
					if(target != null) {
						node.setLeft(null);
						System.out.println("hello");
						target.setData(node.getLeft().getData());
						break;
					}
				}
			}
			
			System.out.println(target.getData());
		}
		
	}
	
	public void inorder() {
		System.out.println("Inorder traversal");
		inorder(root);
		System.out.println("");
	}
	
	public void inorderTraversalWithOutRecursion() {
		Stack<Node<T>> stack = new Stack<Node<T>>();
		if(root == null)
			return;
		Node<T> node = root;
		do {
			if(node == null)
				node = stack.pop();
			else {
				while(node.getLeft()!=null) {
					stack.add(node);
					node = node.getLeft();
				}
			}
			
			System.out.print(node.getData() + " , ");
			node = node.getRight();
		}while(!stack.isEmpty() || node!= null);
	}
	
	public void formFullBinTreeFromPreAndPost(T[] pre, T[] post) {
		if(pre ==null || post == null || pre.length == 0 || post.length == 0) {
			return;
		}
		formFullBinTreeFromPreAndPost(pre, post, 0, post.length -1);
	}
	
	public void printPostOrderFromInOrderAndPreOrder(T[] pre, T[] in) {
		index =0;
		printPostOrderFromInOrderAndPreOrder(pre, in, 0, in.length -1);
	}

	public void formFullBinTreeFromInAndPost(T[] in, T[] post) {
		index = post.length-1;
		formFullBinaryTreeFromInAndPost(in, post, 0, in.length - 1);
	}
	
	public void formFullBinTreeFromInAndPre(T[]pre, T[] in) {
		index =0;
		if(in == null || pre == null || in.length == 0 || pre.length ==0)
			return;
		formFullBinTreeFromInAndPre(in, pre, 0, in.length - 1);
	}
	
	public void formFullBinTreeFromInAndLevel(T[] in, T[] level) {
		if(in == null || level == null || in.length == 0 || level.length == 0)
			return;
		index =0;
		formFullBinTreeFromInAndLevel(in, level, 0, in.length - 1);
	}
	
	public void levelOrderInCustomizedMode() {
		Stack<Node<T>> stack = new Stack<>();
		Queue<Node<T>> queue = new LinkedList<>();
		stack.add(root);
		Node<T> node = null;
		boolean isLeft = true;
		while(!stack.isEmpty() || !queue.isEmpty()) {
			while(!stack.isEmpty()) {
				node = stack.pop();
				System.out.print(node.getData() + " ");
				if(isLeft) {
					if(node.getLeft() !=null)
						queue.add(node.getLeft());
					if(node.getRight() !=null)
						queue.add(node.getRight());
				}
				else {
					if(node.getRight() !=null)
						queue.add(node.getRight());
					if(node.getLeft() !=null)
						queue.add(node.getLeft());
					
				}
			}
			System.out.println("");
				while(!queue.isEmpty()) {
					node = queue.poll();
					System.out.print(node.getData() + " ");
					if(isLeft) {
						if(node.getLeft() !=null)
							stack.add(node.getLeft());
						if(node.getRight() !=null)
							stack.add(node.getRight());
					}
					else {
						if(node.getRight() !=null)
							stack.add(node.getRight());
						if(node.getLeft() !=null)
							stack.add(node.getLeft());
					}
				}
				
				System.out.println("");	
			isLeft = !isLeft;	
			}

		}
		
	public void levelOrder() {
		Queue<Node<T>> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node<T> node = queue.poll();
			System.out.print(node.getData() + " ");
			if(node.getLeft()!=null)
				queue.add(node.getLeft());
			if(node.getRight()!=null)
				queue.add(node.getRight());
			
		}
	}

	public int getHeight() {
		return getHeight(root);
	}
	
	public void reverseLevelOrder() {
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(root);
		reverseLevelOrder(queue);
	}

	public void perfectBinTreeCustomLevelOrder() {
		if(root == null)
			return;
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		System.out.print(root.getData() + " , ");
		if(root.getLeft() !=null)
			queue.add(root.getLeft());
		if(root.getRight() !=null)
			queue.add(root.getRight());
		if(queue.isEmpty())
			return;
		Node<T> first,second;
		first=second=null;
		while(!queue.isEmpty()) {
			first = queue.poll();
			second = queue.poll();
			
			System.out.print(first.getData() + " , " + second.getData() + " , ");
			
			if(first.getLeft()!=null)
				queue.add(first.getLeft());
			if(second.getRight()!=null)
				queue.add(second.getRight());
			if(first.getRight()!=null)
				queue.add(first.getRight());
			if(second.getLeft()!=null){
				queue.add(second.getLeft());
			}
		}
		
	}

	public void perfectBinTreeCustomLevelOrderFromBottomToTop() {
		if(root == null)
			return;
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		if(root.getRight() !=null)
			queue.add(root.getRight());
		if(root.getLeft() !=null)
			queue.add(root.getLeft());
		if(!queue.isEmpty())
			perfectBinTreeCustomLevelOrderFromBottomToTop(queue);
		
		System.out.print(root.getData() + " , ");
		
	}
	
	public void reverseAlternateLevelsOfPerfectBinTreeIterative() {
		if(root == null)
			return;
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		if(root.getLeft() !=null)
			queue.add(root.getLeft());
		if(root.getRight() !=null)
			queue.add(root.getRight());
		if(queue.isEmpty())
			return;
		Node<T> first,second;
		first=second=null;
		int i,size;
		T fd;
		i=size=0;
		while(!queue.isEmpty()) {
			size = queue.size();

			while(size>0) {
				first = queue.poll();
				second = queue.poll();
				
				if(first.getLeft()!=null) {
					queue.add(first.getLeft());
				}
				if(second.getRight()!=null) {
					queue.add(second.getRight());
				}
				if(first.getRight()!=null) {
					queue.add(first.getRight());
				}
				if(second.getLeft()!=null) {
					queue.add(second.getLeft());
				}
				
				if(i%2 == 0) {
					fd = first.getData();
					first.setData(second.getData());
					second.setData(fd);
					
				}
				size-=2;
			}
			
			i++;
		}

	}

	public void reverseAlternateLevelsOfPerfectBinTreeRecursive() {
		if(root == null)
			return;
		reverseAlternateLevelsOfPerfectBinTreeRecursive(root.getLeft(), root.getRight(), 0);
	}

	public void preOrderWithoutRecursion() {
		Stack<Node<T>> stack = new Stack<Node<T>>();
		if(root == null)
			return;
		Node<T> node = root;
		do {
			System.out.print(node.getData() + " , ");
			if(node.getRight()!= null)
				stack.add(node.getRight());
			if(node.getLeft() !=null)
				node = node.getLeft();
			else if(!stack.isEmpty())
				node = stack.pop();
			else
				node = null;
			
		} while(!stack.isEmpty() || node!=null);
	}
  
	public void preOrderUsingMorrisTraversal() {
		preOrderUsingMorrisTraversal(root);
	}

	public void replaceEachNodeWithSumOfInOrderPredecessorAndSuccesor() {
		index = 0;
		ArrayList<T> inorder = new ArrayList<>();
		inorderTraversal(root, inorder);
		replaceEachNodeWithSumOfInOrderPredecessorAndSuccesor(root, inorder);
	}

	public void populateInorderSuccesor() {
		populateInOrderSuccesor(root, null);
	}

	public void printInorderFollowingNextPtr() {
		printInorderFollowingNextPtr(root);
	}
}
