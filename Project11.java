/*  
Name: Tsugoii
Date: 28/08/2021
Description: 
GUI allowing user to input expression in prefix or postfix and convert
*/

package Project1;

import Project1;
import Project1.Project12;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project11 implements ActionListener {
    // Main
    public static void main(String args[]) {
        // Creating and Formatting GUI Elements
        JFrame f = new JFrame("Expression Converter");
        JLabel enterTextLabel, resultTextLabel;
        enterTextLabel = new JLabel("Enter Expression: ");
        enterTextLabel.setBounds(40, 25, 125, 20);
        resultTextLabel = new JLabel("Result: ");
        resultTextLabel.setBounds(40, 75, 100, 20);
        JTextField enterText = new JTextField();
        enterText.setBounds(150, 25, 175, 20);
        JTextField resultText = new JTextField();
        resultText.setBounds(100, 75, 225, 20);
        resultText.setEditable(false);
        JButton preButton = new JButton("Pre to Post");
        // Adding Functionality to the GUI (pre->post)
        preButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Getting input
                String equationString = enterText.getText();
                // Doing conversion, implementing custom exception
                try {
                    String resultString = Project12.preToPost(equationString);
                    // Displaying results
                    resultText.setText(resultString);
                } catch (SyntaxError exception) {
                    System.out.println(exception.getMessage());
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
        preButton.setBounds(98, 50, 100, 20);
        // Adding Functionality to the GUI (post->pre)
        JButton postButton = new JButton("Post to Pre");
        postButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Getting input
                String equationString = enterText.getText();
                // Doing conversion, implementing custom exception
                try {
                    String resultString = Project12.postToPre(equationString);
                    // Displaying results
                    resultText.setText(resultString);
                } catch (SyntaxError exception) {
                    System.out.println(exception.getMessage());
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
        postButton.setBounds(202, 50, 100, 20);
        // Actually add all the GUI elements
        f.add(enterTextLabel);
        f.add(resultTextLabel);
        f.add(preButton);
        f.add(postButton);
        f.add(enterText);
        f.add(resultText);
        f.setSize(400, 150);
        f.setLayout(null);
        f.setVisible(true);
    }
}