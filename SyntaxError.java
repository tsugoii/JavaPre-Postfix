/*
Name: Tsugoii
Date: 28/08/2021
Description: 
checked exception SyntaxError should be thrown by the methods that perform the conversions if an empty stack is ever popped or the stack is not empty once the conversion is complete
*/

package Project1;

import Project1;
import java.io.*;

// user defined checked exception
public class SyntaxError extends Exception {
    public SyntaxError(String error) {
        super("Syntax was incorrect: " + error);
    }
}