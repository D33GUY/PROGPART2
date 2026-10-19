/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart1;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author Student
 */
public class PoePart1 {
   
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        
        Login login = new Login();
        boolean running = true;
        
        
          
        
        System.out.println("==========  WELCOME!!   =========");
        System.out.println("==========  MENU!!      =========");
        
        while(running){
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = input.nextLine();
            
            switch(choice){
                case "1":
                    registerMenu(input, login);
                    break;
                case "2":
                    loginMenu(input, login);
                    break;
                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid Option, please choose 1,2 or 3.");
            }
        }
        
        input.close();
        
    } 
    
    private static void registerMenu(Scanner input, Login login){
        System.out.println("======== USER REGISTRATION ==========");
        
        System.out.print("Enter a username: ");
        String userName = input.nextLine();
        
        System.out.print("Enter a password: ");
        String password = input.nextLine();
        
        System.out.print("Enter a South African cell phone number e.g: +27723456789. ");
        String cellPhoneNumber = input.nextLine();
        
        System.out.print("Enter your name: ");
        String firstName = input.nextLine();
        
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();
        
        String registrationMessage = login.registerUser(userName, password, cellPhoneNumber, firstName, lastName);
        
        System.out.println(registrationMessage);
    }
    
    private static void loginMenu(Scanner input, Login login) throws IOException{
        if(!login.isRegistered()){
            System.out.println("No registered user found. Please register first.");
            return;
        }
        
        System.out.println("========= USER LOGIN ============");
        
        boolean logedIn = false;
        
        while(!logedIn){
                 System.out.print("Enter your username: ");
        String userName = input.nextLine();
        
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        
        String loginMessage = login.returnLoginStatus(userName, password);
        
        System.out.println(loginMessage);
        
        if(!logedIn){
            System.out.print("Try again?(y/n)");
            String retry = input.nextLine();
            
            if(!retry.equalsIgnoreCase("y")){
                break;
            }
        }
        }
      
        System.out.println("\nWelcome to QuickChat");
        
        int maxMessages = askForMessageCount(input);
        int messagesEntered = 0;
        boolean quit = false;
        
        while(!quit){
            System.out.println("\n======== QUICKCHAT MENU ==========");
            System.out.println("1. Send Message");
            System.out.println("2. Show recently sent messages ");
            System.out.println("3. Quit. ");
            System.out.println("Choose an option: ");
            String choice = input.nextLine().trim();
            
            switch(choice){
                case "1": 
                    if(messagesEntered >= maxMessages){
                        System.out.println("You have already eneterd all" + maxMessages + "messages you set at the start.");
                    }else{
                        messagesEntered = sendMessages(input, maxMessages, messagesEntered);
                    }
                    break;
                case "2": 
                    System.out.println("Coming Soon");
                    break;
                case "3": 
                    quit = true;
                    break;
                default: 
                    System.out.println("Invalid option, please choose 1, 2, or 3.");
            }
        }
    }
        
    
    private static int askForMessageCount(Scanner scanner){
        while(true){
            System.out.println("How many messages do you want to enter?");
            try{
                int count = Integer.parseInt(scanner.nextLine().trim());
                if(count > 0){
                    return count;
                }
            }catch (NumberFormatException e){
                
            }
            System.out.println("Please enter a whole number greater than 0.");
        }
    }
    
    private static int sendMessages(Scanner scanner, int maxMessages, int messagesEntered) throws IOException{
        while(messagesEntered < maxMessages){
            System.out.println("\n----Message " + (messagesEntered + 1) + "of " + maxMessages + "------");
            
            String recipient = askForRecipient(scanner, messagesEntered);
            String text = askForMessageText(scanner,messagesEntered);
             String messageText = null;
             String messageHash = null;
            
            Message message = new Message(recipient, text, messagesEntered);
            System.out.println("MessageID generated: "+ message.getMessageID());
            
            handleMessageChoice(scanner, message);
            messagesEntered++;
        }
        
        System.out.println("======= SUMMARY =========");
        System.out.println(Message.printMessage());
        System.out.println("\nTotal number of messages sent "+ Message.returnTotalMessages());
        
        return messagesEntered;
    }
    
    private static String askForRecipient(Scanner scanner, int messageNumber){
        while(true){
            System.out.println("Enter the recipients cell phone number +27: ");
            String recipient = scanner.nextLine();
            
            String status = new Message(recipient, "", messageNumber).checkRecipientCell();
            System.out.println(status);
            
            if(status.equals(Message.cellSuccess)){
                return recipient;
            }
        }
    }
    
    private static String askForMessageText(Scanner scanner, int messageNumber){
        while (true){
            System.out.println("Enter your message (max 250 characters): ");
            String text = scanner.nextLine();
            
            
             String status = new Message( "", text, messageNumber).checkMessageLength();
             System.out.println(status);
             
             if(status.equals(Message.messageReady)){
                return text;
            }
        }
    }
    
    private static void handleMessageChoice(Scanner scanner, Message message) throws IOException{
        boolean handled = false;
        
        while(!handled){
            System.out.println("\nwhat would you like to do with thuis message? ");
            System.out.println("1. Send message.");
            System.out.println("2. Disregard message. ");
            System.out.println("3. Store message to send later. ");
            System.out.println("Choose an option: ");
            int choice;
            
            try{
                choice = Integer.parseInt(scanner.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println(Message.invalidOption);
                
                continue;
            }
            
            String result = message.sentMessage(choice);
            System.out.println(result);
            
            switch (choice) {
                case Message.Send:
                    System.out.println("\n" + message.getDetails());
                    handled = true;
                    break;
                case Message.Disregard:
                    String confirm = scanner.nextLine().trim();
                    if(confirm.equals("0")){
                        System.out.println("Message deleted.");
                        handled = true;
                    }   break;
                case Message.Store:
                    handled = result.equals(Message.messageStored);
                    break;
                default:
                    break;
            }
        }
    }
}
