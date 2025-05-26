package com.mycompany.quickchat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lab_services_student
 */

public class MessagesTest {

    // ----------- Static methods (message validators) ----------------

    public static String validateMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    public static String validatePhoneNumber(String number) {
        if (number.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public static String generateMessageHash(String message) {
        String[] words = message.trim().split("\\s+");
        if (words.length >= 2) {
            return "00:0:" + (words[0] + words[words.length - 1]).toUpperCase();
        }
        return "00:0:" + words[0].toUpperCase();
    }

    public static String generateMessageID() {
        return "Message ID generated: <Message ID>";
    }

    public static String handleSendOption(String option) {
        switch (option) {
            case "Send Message":
                return "Message successfully sent.";
            case "Disregard Message":
                return "Press 0 to delete message.";
            case "Store Message":
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    // ------------------- Test methods --------------------

    @Test
    public void testMessageLength_Success() {
        String message = "Hi Mike, can you join us for dinner tonight ";
        assertEquals("Message ready to send.", validateMessageLength(message));
    }

    @Test
    public void testMessageLength_Failure() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 260; i++) sb.append("a");
        String longMessage = sb.toString();

        assertEquals("Message exceeds 250 characters by 10, please reduce size.",
                     validateMessageLength(longMessage));
    }

    @Test
    public void testPhoneNumberFormat_Success() {
        String number = "+27718693002";
        assertEquals("Cell phone number successfully captured.", validatePhoneNumber(number));
    }

    @Test
    public void testPhoneNumberFormat_Failure() {
        String number = "08575975889";
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                     validatePhoneNumber(number));
    }

    @Test
    public void testMessageHash_Success() {
        String message = "Hi Mike, can you join us for dinner tonight ";
        assertEquals("00:0:HITONIGHT", generateMessageHash(message));
    }

    @Test
    public void testGenerateMessageID() {
        assertEquals("Message ID generated: <Message ID>", generateMessageID());
    }

    @Test
    public void testSendOptions() {
        assertEquals("Message successfully sent.", handleSendOption("Send Message"));
        assertEquals("Press 0 to delete message.", handleSendOption("Disregard Message"));
        assertEquals("Message successfully stored.", handleSendOption("Store Message"));
    }

}

