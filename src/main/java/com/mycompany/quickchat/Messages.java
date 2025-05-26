/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author lab_services_student
 */
import org.json.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Messages {
    int messageCounter = 0;
    List<String> messageList = new ArrayList<>();

    public void numericMenu() {
    while (true) {
        String option = JOptionPane.showInputDialog("""
            Select one of the following options:
            1. Send Message
            2. Show recently sent messages
            3. Quit""");

        if (option == null) continue;

        switch (option) {
            case "1":
                sendMessages();
                break;
            case "2":
                JOptionPane.showMessageDialog(null, "Coming Soon.");
                break;
            case "3":
                JOptionPane.showMessageDialog(null, "Exiting QuickChat. Goodbye!");
                return;
            default:
                JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
        }
    }
}


    public void sendMessages() {
        String input = JOptionPane.showInputDialog("How many messages would you like to process?");
        int totalMessages;

        try {
            totalMessages = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Returning to menu.");
            return;
        }

        for (int i = 0; i < totalMessages; i++) {
            String recipient;
            while (true) {
                recipient = JOptionPane.showInputDialog("Enter recipient cell number (starts with +27 and 9 more digits):");
                if (recipient != null && recipient.matches("\\+27\\d{9}")) break;
                JOptionPane.showMessageDialog(null, "Invalid recipient number. Please try again.");
            }

            String messageText;
            while (true) {
                messageText = JOptionPane.showInputDialog("Enter your message (1–250 characters):");
                if (messageText != null && messageText.length() >= 1 && messageText.length() <= 250) break;
                JOptionPane.showMessageDialog(null, "Invalid message length. Please try again.");
            }

            String action = JOptionPane.showInputDialog("""
                Choose an action for this message:
                1. Send
                2. Store
                3. Discard""");

            if (action == null) {
                JOptionPane.showMessageDialog(null, "Action canceled. Skipping message.");
                continue;
            }

            switch (action) {
                case "1":
                    String messageID = String.format("%010d", new Random().nextInt(1000000000));
                    String messageHash = messageID.substring(0, 2) + ":" + (messageCounter + 1) + ":" + getFirstLastWords(messageText).toUpperCase();

                    JOptionPane.showMessageDialog(null, "Message sent successfully!\n\n" +
                        "MessageID: " + messageID + "\n" +
                        "Recipient: " + recipient + "\n" +
                        "MessageHash: " + messageHash);

                    saveMessageToFile("[SENT] To: " + recipient + " | Message: " + messageText);
                    messageCounter++;
                    break;

                case "2":
                    saveMessageToFile("[STORED] To: " + recipient + " | Message: " + messageText);
                    JOptionPane.showMessageDialog(null, "Message stored for later.");
                    break;

                case "3":
                    JOptionPane.showMessageDialog(null, "Message discarded.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid action. Message skipped.");
            }
        }

        JOptionPane.showMessageDialog(null, "Total messages sent: " + returnTotalMessages());
    }

    private String getFirstLastWords(String message) {
        String[] words = message.trim().split("\\s+");
        return words[0] + words[words.length - 1];
    }

    private void saveMessageToFile(String message) {
        try (FileWriter fw = new FileWriter("messages.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(message);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage());
        }

        // Also store in memory for this session
        messageList.add(message);
    }

    public String printMessages() {
        File file = new File("messages.txt");
        if (!file.exists()) {
            return "No messages have been sent or stored.";
        }

        StringBuilder sb = new StringBuilder("Messages:\n\n");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            return "Error reading messages: " + e.getMessage();
        }

        return sb.toString();
    }

    public int returnTotalMessages() {
        return messageCounter;
    }
}

