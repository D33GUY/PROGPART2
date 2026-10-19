/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 * @author Student
 */
public class Message {
   public static final int Send = 1;
   public static final int Disregard = 2;
   public static final int Store = 3;
   
   public static final int maxMessageLength = 250;
   
   public static final String messageReady = "Message ready to send.";
   public static final String cellSuccess = "Cell number successfully captured.";
   public static final String cellFailure = "Cell number is incorrectly formatted or does not have an international code.";
   public static final String messageSent = "Message successfully sent.";
   public static final String messageDelete = "Press 0 to delete the message.";
   public static final String messageStored = "Message successfully stored.";
   public static final String messageNotStored = "Message could not be stored.";
   public static final String invalidOption = "Invalid option. Please choose 1(send), 2(Disregard), 3(store)";
   
   private static final List<Message> sentMessage = new ArrayList<>();
    private static final List<Message> storedMessage = new ArrayList<>();
    private static String storageFile = "storedMessage.json";
    
    private String messageID;
    private String recipient;
    private String messageText;
    private int messageNumber;
    private String messageHash;
    String createMessageHash;
   
    public Message(String recipient, String messageText, int messageNumber){
        this(generateMessageID(), recipient, messageText, messageNumber);
        
        
    }
    public Message(String messageID, String recipient,String messageText, int messageNumber){
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageHash = createMessageHash;
    }
    
    private static String generateMessageID(){
        Random random = new Random();
        String id="";
        
        for(int i = 0; i<10; i++){
            int digit = random.nextInt(10);
            id += digit;
        }
        return id;
    }
    
    public boolean checkMessageID(){
        return messageID != null && messageID.length() <=10;
    }
    
    public String checkRecipientCell(){
        Login login = new Login();
        
        if(login.checkCellPhoneNumber(recipient)){
            return cellSuccess;
        }
            return cellFailure; 
    }
    
    public String checkMessageLength(){
        int length = messageText.length();
        if(length <= maxMessageLength){
            return messageReady;
        }
        int excess = length - maxMessageLength;
        return "Message exceeds 250 characters by " + excess + ", please reduce the size.";
    }
    
    public String createMessageHash(){
        String idPrefix = messageID.length() >=2 ? messageID.substring(0,2) : messageID;
        
        String [] words = messageText.trim().split("\\s+");
        String firstWord = stripPunctuation(words[0]);
        String lastWord = stripPunctuation(words[words.length -1]);
        
        return (idPrefix + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }
    
    private static String stripPunctuation(String word){
        return word.replaceAll("[A-Za-z0-9]", "");
    }
    
    public String sentMessage(int choice) throws IOException{
        switch (choice){
            case Send:
                sentMessage.add(this);
                return messageSent;
            case Disregard:
                return messageDelete;
            case Store:
                return storeMessage() ? messageStored : messageNotStored;
            default:
                return invalidOption;
        }
    }
    
    public boolean storeMessage() throws IOException{
        storedMessage.add(this);
        if(writeStoredMessageToFile()){
            return true;
        }
        storedMessage.remove(this);
        return false;
    }
    
    private static boolean writeStoredMessageToFile() throws IOException{
        StringBuilder json = new StringBuilder("\n");
         for(int i = 0; i<storedMessage.size(); i++){
             Message m = storedMessage.get(i);
             json.append(" {\n");
             json.append( "\"messageID\": \"").append(escapeJson(m.messageID)).append("\",\n");
             json.append( "\"messageHash\":\"").append(escapeJson(m.messageHash)).append("\",\n");
             json.append( "\"recipient\": \"").append(escapeJson(m.recipient)).append("\",\n");
             json.append( "\"message\": \"").append(escapeJson(m.messageText)).append("\",\n");
             
             json.append( " }");
             if(i < storedMessage.size() -1 ){
                 json.append(",");
             }
             json.append("\n");   
         }
         json.append("]\n");
        try{
             Files.write(Paths.get(storageFile), json.toString().getBytes(StandardCharsets.UTF_8));
             return true;
        } catch(IOException e){
            return false;
        }    
    }  
    private static String escapeJson(String text){
       StringBuilder escaped = new StringBuilder();
       for (char c : text.toCharArray()){
          switch (c) {
              case '"':
                  escaped.append("\\\"");
                  break;
              case '\\':
                  escaped.append("\\\\");
                  break;
              case '\n':
                 escaped.append("\\n");
                 break;
              case 'r':
                  escaped.append("\\r");
                  break;
              case 't':
                  escaped.append("\\t");
                  break;
              default:
                  if(c <0*20){
                      escaped.append(String.format("\\u%04x", (int) c));
                  }else {
                      escaped.append(c);
                  }
          } 
       }
       return escaped.toString();
    }
    
    public String getDetails(){
        return "MessageID: "+ messageID
                +"\nMessageHash: " + messageHash
                +"\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }
    
    public static String printMessage(){
        if(sentMessage.isEmpty()){
            return "No messages have been sent.";
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < sentMessage.size(); i++){
           all.append(sentMessage.get(i).getDetails());
           if(i < sentMessage.size() -1){
               all.append("\n\n");
           }
        }
        return all.toString();
    }
    
    public static int returnTotalMessages(){
        return sentMessage.size();
    }
    
    public void resetSession(){
       sentMessage.clear();
       storedMessage.clear();
    }
    
    public static void setStorageFile(String fileName){
        storageFile = fileName;
    } 
    
    public String getMessageID(){
        return messageID;
    }
    
    public String getMessageHash(){
        return messageHash;
    }
    
    public String getRecipient(){
        return recipient;
    }
    
    public String getMessageText(){
        return messageText;
    }
    
    public int getMessageNumber(){
        return messageNumber;
    }

 
}
