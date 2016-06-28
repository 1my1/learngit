package demo001.test;

import java.util.Scanner;

class Node{
	String val;
	Node next;
}
public class ListLinked {
    static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head=null;
        print(createListLink(head));
	}
	public static Node createListLink(Node head){
		Node p;
		Node p1=new Node();
		head=p1;
		String val;
		while(!"#".equals(val=sc.next())){
			p=new Node();
			p.val=val;
			p.next=null;
			p1.next=p;p1=p;
		}
		return head;
	}
	public static void print(Node head){
		Node p=head.next;
		while(p!=null){
			System.out.println(p.val);
			p=p.next;
		}
	}

}
