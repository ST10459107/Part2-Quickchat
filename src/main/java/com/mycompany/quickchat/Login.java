/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author lab_services_student
 */

import javax.swing.JOptionPane;
import java.util.Scanner; 
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Login {
    // Instance variables to store user information
    String firstName;
    String lastName;
    String userName;
    String passWord;
    String cellPhoneNumber;

    // Method to capture and validate the user's name and username
    public void checkUserName() {
        firstName = JOptionPane.showInputDialog("Enter your first name:");
        lastName = JOptionPane.showInputDialog("Please enter your last name:");

        while (true) {
            userName = JOptionPane.showInputDialog("Please enter your username (must contain an underscore and be 5 characters or less):");
            if (userName != null && userName.contains("_") && userName.length() <= 5) {
                JOptionPane.showMessageDialog(null, "Username successfully captured");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }
    }

    // Method to capture and validate password complexity
    public void checkPasswordComplexity() {
        while (true) {
            passWord = JOptionPane.showInputDialog("Please enter a password (at least 8 characters, 1 uppercase letter, 1 number, and 1 special character):");
            if (passWord != null && containsSpecialCharacter(passWord) && passWord.length() >= 8 && containsDigit(passWord) && containsUpperCase(passWord)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }
    }

    // Method to validate a South African phone number with international code
    public void checkCellPhoneNumber() {
        while (true) {
            String cellPhone = JOptionPane.showInputDialog("Please enter your cell phone number, include international code, e.g., +27734664307:");
            String regex = "^\\+27\\d{9}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(cellPhone);
            if (matcher.matches()) {
                cellPhoneNumber = cellPhone;
                JOptionPane.showMessageDialog(null, "Cell phone number successfully added");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code, please correct the number and try again.");
            }
        }
    }

    private boolean containsUpperCase(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) return true;
        }
        return false;
    }

    private boolean containsDigit(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) return true;
        }
        return false;
    }

    private boolean containsSpecialCharacter(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) return true;
        }
        return false;
    }

    public boolean loginUser() {
        JOptionPane.showMessageDialog(null, "Login to your account using the same username and password");
        while (true) {
            String loginUserName = JOptionPane.showInputDialog("Enter your username:");
            String loginPassWord = JOptionPane.showInputDialog("Enter your password:");
            if (loginUserName.equals(userName) && loginPassWord.equals(passWord)) {
                JOptionPane.showMessageDialog(null, "Welcome to QuickChat :)");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again.");
            }
        }
    }
}
