/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;

import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class PoePart1Test {
    
    public PoePart1Test() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of main method, of class PoePart1.
     */
    @Test
    public void testMain() throws IOException {
        System.out.println("main");
        String[] args = null;
        PoePart1.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test 
    public void testUserNameMessageCorrect(){
        Login login = new Login("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle","Walter");
        
        assertEquals("Welcome, it is great to see you again.", login.getUserMessage());
    }
    
    @Test
     public void testUserNameMessageInCorrect(){
         Login login = new Login("Kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle","Walter");
         
         assertEquals( "Username is not correctly formatted please ensure that your username contains an underscore and is no more than five characters in length.", login.getUserMessage());
     }
     
     @Test
      public void testPasswordCorrectMessage(){
          Login login = new Login("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle","Walter");
          
          assertEquals("Password is successfully capture!", login.getPasswordMessage());
      }
      
      @Test
      public void testPasswordInCorrectMessage(){
           Login login = new Login("Kyl_1", "password", "+27838968976", "Kyle","Walter");
           
           assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters,"
                    + " a capital letter, a number, and a special character.",login.getPasswordMessage());
      }
      
      @Test
      public void testCellPhoneNumberCorrect(){
           Login login = new Login("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle","Walter");
           
           assertEquals("Cellphone Number successfully captured!",login.getCellNumberMessage());
      }
      
      @Test
      public void testCellPhoneNumberInCorrect(){
          Login login = new Login("Kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle","Walter");
          
          assertEquals("Cell phone number incorrectly formatted or does not contain interantional code, please correct the number and try again.", login.getCellNumberMessage());
      }
      
      @Test
      public void testLoginReturnFalse(){
          Login login = new Login("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle","Walter");
          String userName = "Kyl_1";
          String password = "Ch&&sec@ke99!";
          
          assertFalse(login.loginUser(userName, password));
      }
      
      @Test
       public void testUserNameReturnTrue(){
            Login login = new Login();
        String userName = "Kyl_1";
            
            assertTrue(login.checkUserName(userName));
       }
       
       @Test 
       public void testUserNameReturnFalse(){
           Login login = new Login();
           String userName = "Kyle!!!!!!!";
           
           assertFalse(login.checkUserName(userName));
       }
       
       @Test
       public void testPasswordReturnTrue(){
           Login login = new Login();
           String password = "Ch&&sec@ke99!";
           
           assertTrue(login.checkPassword(password));
       }
       
       @Test
       public void testPasswordReturnFalse(){
           Login login = new Login();
           String password = "password";
           
            assertFalse(login.checkPassword(password));
       }
       
       @Test
        public void testCellPhoneNumberReturnTrue(){
            Login login = new Login();
            String cellPhoneNumber = "+27838968976";
            
            assertTrue(login.checkCellPhoneNumber(cellPhoneNumber));
        }
        
        @Test
        public void testCellPhoneNumberReturnFalse(){
             Login login = new Login();
             String cellPhoneNumber = "08966553";
             
             assertFalse(login.checkCellPhoneNumber(cellPhoneNumber));
        }
        
        @Test
        
        public void testMessageLengthCorrect(){
            Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
            
            assertEquals("Message ready to send.", message.checkMessageLength());
        }
        
        @Test
       public void testRecipientNumberCorrect(){
             Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
             
             assertEquals("Cell number successfully captured.",message.checkRecipientCell());
       }
       
       @Test
       public void testRecipientNumberInCorrect(){
           Message message = new Message("0718693002", "Hi Mike, can you join us for dinner tonight?", 1);
           
           assertEquals("Cell number is incorrectly formatted or does not have an international code.",message.checkRecipientCell());
       }
       
       @Test
       public void testMessageHashCorrect(){
            Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
            
             assertEquals(" ",message.createMessageHash());
       }
        
        
    
}

