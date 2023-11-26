import java.util.Scanner;

/**
 * This class contains two method stubs dealing with infix and postfix
 * expressions
 * 
 * @author Rick Mercer and Andrew Dennison
 */

public class Expressions {

	// Return the value of a valid postfix expression. If post is not valid,
	// there could be an EmptyStackException thrown. We have assertions for this.
	//
	// valueOf("2 2 +") returns 4
	// valueOf("15 4 % 5 %") returns 3
	//
	// Precondition: All tokens are separated by whitespace and each token is
	// either a valid integer or one of the five arithmetic operands: + - * / or %
	//
	public int valueOf(String post) {
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		Scanner scanner = new Scanner(post);

		while (scanner.hasNext()) {
			String next = scanner.next();
			if (isInteger(next)) {
				stack.push(Integer.parseInt(next));
			} else {
				performOperation(stack, next);
			}
		}
		scanner.close();

		// The only thing left in the stack should be our single result
		return stack.pop();
	}

	/**
	 * Returns whether a String is an integer or not
	 * 
	 * @param str Input String to evaluate
	 * @return Boolean representation of whether the String is an Integer
	 */
	private boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Given a particular operand, perform the required operation and push the
	 * result back to the stack for further processing.
	 * 
	 * @param stack   Stack to perform the operation on
	 * @param operand String indicating required and valid mathematical operation.
	 */
	private void performOperation(LinkedStack<Integer> stack, String operand) {
		int first = stack.pop();
		int second = stack.pop();

		if (operand.equals("-")) {
			stack.push(second - first);
		} else if (operand.equals("+")) {
			stack.push(second + first);
		} else if (operand.equals("*")) {
			stack.push(second * first);
		} else if (operand.equals("/")) {
			stack.push(second / first);
		} else if (operand.equals("%")) {
			stack.push(second % first);
		} else if (operand.equals("^")){
			stack.push((int)Math.pow(second, first));
		} else {
			throw new IllegalArgumentException(operand + " is not an expected mathematical operator");
		}
	}

	// Given a valid infix expression, return the equivalent postfix expression
	//
	// e.inToPost("2 * 3")) returns "3 2 *"
	//
	// Precondition: infix has at least one space between each token. This means
	// you do not have to tokenize infix expressions into integers, parenthesis, and
	// operators. However, infix may not be a valid infix expression like "3 + 4 -"
	// which may throw an EmptyStackException/
	//

	/**
	 * This function takes a binary expression tree represented as an inorder
	 * String, and outputs a binary expression tree represented as a postorder
	 * String. Basic assumptions are that the user has input valid characters (any
	 * numeral, or an operator in {*, /, +, -, %}. This function will not
	 * intentionally error if an incorrectly formatted inorder string is input, but
	 * it will produce unexpected results or may error. Lastly, this function will
	 * throw an
	 * 
	 * @param infix Input string to evaluate.
	 * @return Output string in postorder
	 * @throws IllegalArgumentException If any of the characters are not a valid
	 *                                  input.
	 */
	public String inToPost(String infix) {
		LinkedStack<String> stack = new LinkedStack<String>();

		Scanner scanner = new Scanner(infix);
		String result = "";

		// While we still have more to evaluate
		while (scanner.hasNext()) {
			// Get the next String
			String next = scanner.next();

			// If it's an integer, add it to the result
			if (isInteger(next)) {
				result += " " + next;
			}

			// If it's an opening parenthesis, push it onto the stack
			else if (next.equals("(")) {
				stack.push(next);
			}

			// If it's a closing parenthesis, pop all existing operators on the stack.
			// When an opening parenthesis is reached, pop it into oblivion
			else if (next.equals(")")) {
				while (!stack.peek().equals("(")) {
					result += " " + stack.pop();
				}
				stack.pop();
			}

			// At this point, our String next must be an operator
			else {
				// If it's not a valid operator, error
				if ("-+*/%".indexOf(next) == -1) {
					throw new IllegalArgumentException(next + " is not an expected mathematical operator");
				}

				// While there's still operators in the stack and we haven't reached an opening
				// parenthesis
				while (!stack.isEmpty() && !(stack.peek().equals("("))) {
					// If the operator has the highest precedence, and the stack is a lower
					// precedence, quit
					if ("*/%".indexOf(next) != -1) {
						if ("+-".indexOf(stack.peek()) != -1) {
							break;
						}
					}

					// Otherwise, add the next item on the stack to the result
					result += " " + stack.pop();
				}
				stack.push(next);
			}
		}
		scanner.close();

		// Pop everything else from the stack into the result
		while (!stack.isEmpty()) {
			result += " " + stack.pop();
		}

		// Trim off those pesky whitespaces
		return result.trim();
	}
}
