package ds.linkedlist.random;

import java.util.HashMap;
import java.util.Map;

public class LinkedListWithRandomPointer <Type>
{
	public Node<Type> head;

	public insert insert;
	public delete delete;

	public LinkedListWithRandomPointer()
	{
		head = null;
		insert = new insert();
		delete = new delete();
	}

	public class insert
	{
		public void begnning(Node<Type> newNode )
		{
		    newNode.next = head;
		    head = newNode;
		}

		public void end(Node<Type> newNode )
		{
			newNode.next = null;
			if( !isEmpty() )
			{
				Node<Type> iterator = head;
				while(iterator.next != null)
					iterator = iterator.next;
				iterator.next = newNode;
			}
			else
				head = newNode;
		}
	}

	public class delete // TO-DO : free memory in c++
	{
		public void begnning()
		{
			if(!isEmpty())
				head = head.next;
		}

		public void end()
		{
			if( head == null || head.next == null)
			{
				head = null;
			}
			else
			{
				Node<Type> iterator = head;
				while(iterator.next.next != null)
					iterator = iterator.next;
				iterator.next = null;
			}
		}
	}

	public int size()
	{
		Node<Type> iterator = head;
		int len= 0 ;
		while(iterator != null)
		{
			len++;
			iterator=iterator.next;
		}
		return len;
	}

	public boolean isEmpty()
	{
		return (head == null);
	}

	public void printNormalOrder()
	{
		System.out.println("The linked list in normal order is : ");
		Node<Type> iter = this.head;
		while(iter != null)
		{
			System.out.print(iter.data +" ");
			iter = iter.next;
		}
		System.out.println(" ");
	}

	public void printRandomOrder()
	{
		System.out.println("The linked list in Random order is : ");
		Node<Type> iter = this.head;
		while(iter != null)
		{
			System.out.print(iter.data +" ");
			iter = iter.random;
		}
		System.out.println(" ");
	}

	/**
	 * Task:
	 * 	Makes a copy of the random pointer linked list.
	 * Approach :
	 * 	Copy the nornal pointer and keep the
	 * 		mapping of origianl to  new node in Hash map.
	 * 	Then iterate again and fix the random pointer.
	 */
	public LinkedListWithRandomPointer <Type> clone()
	{
		Node<Type> originalNode = this.head;
		LinkedListWithRandomPointer <Type> clonedList = new LinkedListWithRandomPointer<Type>();
		Map<Node<Type>, Node<Type>> map = new HashMap<Node<Type>, Node<Type>>();
		Node<Type> clonedNode = null;

		// Loop for normal pointer and saving the map
		while(originalNode!=null)
		{
			clonedNode = new Node<Type>(originalNode.data);
			clonedList.insert.end(clonedNode);
			map.put(originalNode, clonedNode);
			originalNode = originalNode.next;
		}

		originalNode = this.head;
		// Loop for random pointer
		while(originalNode != null)
		{
			map.get(originalNode).random = map.get( originalNode.random );
			originalNode = originalNode.next;
		}
		return clonedList;
	}

	/**
	 * Task:
	 * 	Makes a copy of the random pointer linked list.
	 * Approach :
	 * 	Copy the normal pointer and keep the
	 * 		mapping of original to  new node by connecting
	 * 		next of 1st list and random of 2nd list with each other.
	 * 	Then iterate again and fix the
	 * 		random pointer of 2nd list and
	 * 		the next pointer of first list.
	 */
	public LinkedListWithRandomPointer <Type> cloneWithoutExtraSpace()
	{
		Node<Type> originalNode = this.head;
		LinkedListWithRandomPointer <Type> clonedList = new LinkedListWithRandomPointer<Type>();
		Node<Type> clonedNode = null;

		// Loop for normal pointer and linking the two list
		while(originalNode!=null)
		{
			clonedNode = new Node<Type>(originalNode.data);
			clonedList.insert.end(clonedNode);
			Node<Type> nextNode = originalNode.next;
			originalNode.next = clonedNode;
			clonedNode.random = originalNode;
			originalNode = nextNode;
		}

		originalNode = this.head;
		// Loop for linking random pointer
		while(originalNode != null)
		{
			originalNode.next.random = (originalNode.random!=null)?originalNode.random.next:null;
			originalNode.next = (originalNode.next!=null)?originalNode.next.next:null;
			originalNode = originalNode.random;
		}
		return clonedList;
	}
}
