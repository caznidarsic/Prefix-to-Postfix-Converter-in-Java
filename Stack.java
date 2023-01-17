/* Christian Znidarsic
 * EN.605.202.86.SP22 Data Structures
 * The Stack Class
 * 
 * 	This class represents a stack. It implements an array called 
 * stack[] to hold the items in the stack. It has fields of arraySize, 
 * head, and stack[]. arraySize defines the maximum size of the stack.
 * head is an integer that keeps track of what index the head of the
 * stack is at. Pop() and Push() methods alter the head integer
 * appropriately.*/
public class Stack {
	//data
	/*arraySize is 100 by default. It is assumed that no inputs will
	* exceed 100 elements*/
	private int arraySize = 100;
	private int head = -1;
	public String[] stack = new String[arraySize];
	
	
	//push method. Adds item to top of Stack
	//inputs: String
	//outputs: void
	public void push(String item) {
		//only allow push() if index does not exceed array bounds
		if (head < arraySize) {
			head += 1;
			stack[head] = item;
		}
		else {
			System.out.println("index exceeds array bounds");
		}
	}
	
	//pop method. Decreases head pointer and returns head
	//inputs: none
	//outputs: String
	public String pop() {
		//only allow a pop() if the array is not empty
		if (head >= 0) {
			head -= 1;
			return stack[head + 1];
		}
		/*return a blank string if the stack is empty. This is necessary
		when the strings are concatenated in preToPost*/
		else {
			return "";
		}
	}
	
	//peek method. Returns head of stack
	//inputs: none
	//outputs: String
	public String peek() {
		//if the stack is empty, return null. This is use often in pretoPost
		if (head == -1) {
			return null;
		}
		else {
			return stack[head];
		}
	}

}