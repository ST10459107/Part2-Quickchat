/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;
import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */
public class Quickchat {
    public static void main(String[] args) {
        Login user = new Login();
        user.checkUserName();
        user.checkPasswordComplexity();
        user.checkCellPhoneNumber();
        if (user.loginUser()) {
            Messages messenger = new Messages();
            messenger.numericMenu();
        }
    }
}
