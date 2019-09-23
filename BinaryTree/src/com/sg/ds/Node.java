package com.sg.ds;

public class Node<T> {

	private T data;
	private Node<T> left;
	private Node<T> right;
	private Node<T> next;
	
	public Node(T d) {
		this.data = d;
		this.left = this.right = this.next = null;
	}
	
	public T getData() {
		return this.data;
	}
	
	public Node<T> getLeft(){
		return this.left;
	}
	
	public Node<T> getRight(){
		return this.right;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	
}
