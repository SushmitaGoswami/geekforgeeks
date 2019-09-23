package com.sg.ds;

public class Main {
	public static void main(String [] args) {
		BinaryTree<Integer> tree = new BinaryTree<>();
//		tree.addNode(1);
//		tree.addNode(2);
//		tree.addNode(3);
//		tree.addNode(4);
//		tree.addNode(5);
//		tree.addNode(6);
//		tree.addNode(7);
//		tree.addNode(8);
//		tree.addNode(9);
//		tree.addNode(10);
//		tree.addNode(11);
//		tree.addNode(12);
//		tree.addNode(13);
//		tree.addNode(14);
//		tree.addNode(15);
//		tree.addNode(16);
//		tree.addNode(17);
//		tree.addNode(18);
//		tree.addNode(19);
//		tree.addNode(20);
//		tree.addNode(21);
//		tree.addNode(22);
//		tree.addNode(23);
//		tree.addNode(24);
//		tree.addNode(25);
//		tree.addNode(26);
//		tree.addNode(27);
//		tree.addNode(28);
//		tree.addNode(29);
//		tree.addNode(30);
//		tree.addNode(31);
		
//		
		Integer pre[] = {1, 2, 4, 8, 9, 5, 3, 6, 7};  
		Integer in[] = {8,  4,  9,  2,  5,  1,  6,  3,  7};
	    Integer post[] = {8, 9, 4, 5, 2, 6, 7, 3, 1};
	    Integer level[] = {1,2,3,4,5,6,7,8,9};
//	    tree.formFullBinTreeFromPreAndPost(pre,  post);
//	    tree.printPostOrderFromInOrderAndPreOrder(pre, in);
//		tree.inorder();
//	    System.out.println("level order in custom");
//		tree.levelOrderInCustomizedMode();
//	    System.out.println("Reverse Level Order ...");
//	    tree.reverseLevelOrder();
//	    System.out.println(" perfect bin tree in custom level order(top to bottom)");
//	    tree.perfectBinTreeCustomLevelOrder();
//	    System.out.println(" \nperfect bin tree in custom level order(b to t)");
//	    tree.perfectBinTreeCustomLevelOrderFromBottomToTop();
//	    tree.reverseAlternateLevelsOfPerfectBinTreeRecursive();
	    tree.formFullBinTreeFromInAndLevel(in, level);
	    System.out.println("level order");
	    tree.levelOrder();
	    tree.populateInorderSuccesor();
	    System.out.println("in order");
	    tree.printInorderFollowingNextPtr();
//	    System.out.println("pre order");
//	    tree.preOrderWithoutRecursion();
	    
	}
}
