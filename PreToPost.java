/* Christian Znidarsic
 * EN.605.202.86.SP22 Data Structures
 * 
 * The PreToPost Class. 
 * 
 * 	This class includes one method called preToPost, which takes BufferedReader, Scanner, and PrintWriter 
 * objects as inputs. The BufferedReader reads from the input file, and the PrintWriter writes to the 
 * output .txt file. The Scanner copies the contents from the input file to the output file, so that 
 * the user can view both the input and output files next to each other.
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PreToPost {
	
	/* the preToPost method is static, so it can be called from main easily without creating an object
	 * It takes a BufferedReader item, which is the input .txt file, as input. It also takes a
	 * PrintWriter object which holds the output .txt file, and a Scanner object which copies 
	 * from the input to the output file.
	 * preToPost implements two stacks to convert prefix expressions into postfix expressions.
	*/
	public static void preToPost(BufferedReader input, Scanner input2, PrintWriter output) throws IOException {
		
		output.println("Prefix to Postfix Results\n");
		//print labels and lines in the output file
		output.println("-------------------------------------------------------------------------------------------");
		output.println("Input Prefix Expressions:\n");

		//copy input file to output file
		while (input2.hasNextLine()) {
			String line = input2.nextLine();
			output.println(line);
		}
		//print spacing after the prefix expressions
		output.println("-------------------------------------------------------------------------------------------");
		output.print("\n\n\n\n");
		
		
		output.println("Output Postfix Expressions:\n");
		//local variables
		Stack stack = new Stack();
		Stack preStack = new Stack();

	
		//increment across all characters in input file using bufferedReader
		int c = 0;
		
		while((c = input.read()) != -1) {
			//System.out.println("WHILE HAPPENED");
			char character = (char) c;
			
			//do not push spaces, tabs or newline chars outright
			if (character != '\n' && !isSpaceOrTab(character)) {
				
				//push the item to the stack preStack, which is used to reverse the input chars
				preStack.push(Character.toString(character));
				
			}
			/*if input character is a newline char, then we start emptying preStack to read 
			 * input in reverse order. We must read in reverse to convert from pre to postfix.*/
			else if (character == '\n') {
				
				/*this while loop iterates across preStack and empties it one item at a time.
				 * As each item is emptied, it is either pushed to a new stack, or it is combined
				 * with the top two items in the new stack and pushed onto the stack.*/
				while (preStack.peek() != null) {
					//if the char is an operand, push it onto stack
					if (isOperand(preStack.peek())) {
						stack.push(preStack.pop());
					}
					
					//if the char is an operator, combine it with top two items on stack and push.
					else if (isOperator(preStack.peek())) {
						//concatenate the top two items on the stack with the new char from preStack
						String temp = stack.pop() + stack.pop() + preStack.pop();
						stack.push(temp);
					}
					
					/*if there is an item in preStack that is neither an operator nor operand, 
					then pop it out of the stack and discard.*/
					else {
						preStack.pop();
					}
					
				}
				
				//pop an item from stack and determine if it is the last item
				String potentialExpression = stack.pop();

				//if it is not the last item, then there was something wrong with the prefix expression
				if (potentialExpression == "") {
					output.println("Invalid prefix expression or invalid characters. Valid operators are +, -, *, /, and $.");
				}
				//if there are characters remaining in stack after popping, then the prefix expression was invalid
				else if (stack.peek() != null) {
					output.println("Invalid prefix expression or invalid characters. Valid operators are +, -, *, /, and $.");
				}
				/*if potentialExpression was the last item on the stack, then it is the resulting 
				postfix expression. Print it here.*/
				else {
					output.println(potentialExpression);
				}
				
							
				//empty the stack for the next line
				while (stack.peek() != null) {
					stack.pop();
				}
				while (preStack.peek() != null) {
					preStack.pop();
				}

			}
			
		}
		
		output.println("-------------------------------------------------------------------------------------------");
		
	}
	
	

	
	//returns true if input String is a valid operator
	//inputs: String
	//outputs: boolean
	private static boolean isOperator(String input) {
		switch (input) {
		case "+":
			return true;
		case "-":
			return true;
		case "*":
			return true;
		case "/":
			return true;
		case "$":
			return true;
		default:
			return false;
		}
	}
	
	//isOperand returns true if the input String is a valid operand, and false otherwise
	//inputs: String
	//outputs: boolean
	private static boolean isOperand(String input) {
		
		char c = input.charAt(0);
		
		//determine if the input is a valid letter operand
		if (Character.isLetter(c)) {
			return true;
		}
		else {
			return false;
		}
	}

	
	//isSpaceOrTab determines if input is a space or tab
	//inputs: char
	//outputs: boolean
	private static boolean isSpaceOrTab(char input) {
		
		//determine if the input is a space or tab
		if (input == ' ' || input == '\t') {
			return true;
		}
		else {
			return false;
		}
	}
}
