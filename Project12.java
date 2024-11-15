/*
Name: Tsugoii
Date: 28/08/2021
Description: 
Convers prefix to postfix expressions and vice versa
*/

package Project1;

import Project1;
import java.util.*;

public class Project12 {

    // converting pre to post
    public static String preToPost(String pre_exp) throws Exception {
        Boolean isPostFix = false;

        Stack<String> reverse = new Stack<String>();
        // Tokenize the string and push onto reverse stack
        tokenizeString(pre_exp).forEach((token) -> reverse.push(token));

        Stack<String> operands = new Stack<String>();

        while (!reverse.empty()) {// When the stack isn't empty
            String value = reverse.pop(); // Pop the next token
            if (!isOperator(value)) // if it is an operand, push it on the operand stack
                operands.push(value);
            else// if it is an operator
                handleOperator(value, operands, isPostFix);
        }

        return createStringFromStack(operands);
    }

    // converting post to pre
    public static String postToPre(String post_exp) throws Exception {
        Boolean isPostFix = true;
        // Tokenize the string
        List<String> list = tokenizeString(post_exp);// tokenize the string containing the postfix expression
        Stack<String> operands = new Stack<String>();

        for (String token : list) {// while there are more tokens, get the next token
            if (token.trim().isEmpty()) // if it is a space, skip it
                continue;
            else if (!isOperator(token))// else if it is an operand, push it onto the operand stack
                operands.push(token);
            else // else it is an operator
                handleOperator(token, operands, isPostFix);
        }

        return createStringFromStack(operands); // pop the prefix expression off the stack
    }

    // creating a string from a stack
    private static String createStringFromStack(Stack<String> stackyStack) {
        StringBuilder sb = new StringBuilder();
        while (!stackyStack.empty()) {
            sb.append(stackyStack.pop());
            if (!stackyStack.empty())
                sb.append(" ");
        }
        return sb.toString();
    }

    // tokenizing a string and converting to list of tokens
    private static List<String> tokenizeString(String text) throws SyntaxError {
        List<String> list = new ArrayList<String>();
        StringBuilder aggregate = new StringBuilder();
        Integer operatorCount = 0, operandCount = 0;
        // pre : * 2 + 2 - / 12 9 2
        // post : 2 2 12 9 / 2 - + *
        for (int i = 0; i < text.length(); i++) {
            String character = String.valueOf(text.charAt(i));
            // If a character isn't a space
            if (!character.trim().isEmpty()) {
                if (isOperator(character)) {
                    operatorCount++;
                    // push onto stack
                    if (tryAdd(list, aggregate)) {
                        operandCount++;
                    }
                    list.add(character);
                    aggregate = new StringBuilder();
                } else {
                    aggregate.append(character);
                }
            } else {
                if (tryAdd(list, aggregate)) {
                    operandCount++;
                }
                aggregate = new StringBuilder();
            }
        }
        if (tryAdd(list, aggregate)) {
            operandCount++;
        }

        if (operandCount - operatorCount != 1) // checking for non-empty stack
            throw new SyntaxError("There should be exactly one more operand than operator.");

        return list;
    }

    // adding to a list
    private static Boolean tryAdd(List<String> list, StringBuilder sb) {
        String x = sb.toString();
        if (!x.trim().isEmpty()) {
            list.add(x);
            return true;
        }
        return false;
    }

    // actual stack operations for pre and post fix conversions
    private static void handleOperator(String value, Stack<String> operands, Boolean isPostFix) throws Exception {
        // pop two operands off the operand stack
        try {
            String op1 = operands.pop();
            String op2 = operands.pop();
            if (isPostFix) // push string of operator followed by two operands onto the operand stack
                operands.push(value + " " + op2 + " " + op1);
            else // push string of two operands followed by operator onto operand stack
                operands.push(op1 + " " + op2 + " " + value);
        } catch (EmptyStackException ex) { // checking if stack is popped while empty
            throw new SyntaxError("The stack was popped while empty. Did you try the wrong conversion?");
        } catch (Exception ex) {
            throw ex;
        }
    }

    // Checking for Operators
    private static boolean isOperator(String x) {
        switch (x) {
            case "+":
            case "-":
            case "/":
            case "*":
                return true;
        }
        return false;
    }
}
