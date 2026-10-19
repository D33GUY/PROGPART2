/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;
import java.util.regex.Pattern;
/**
 *
 * @author Student
 */
public class Login {
    private String userName;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    
    private boolean isRegistered = false;
    
    public Login(){
    }
    
    public Login(String userName, String password, String cellPhoneNumber, String firstName, String lastName){
        this.userName = userName;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public boolean checkUserName(String userName){
        if(userName == null){
            return false;
        }
        return userName.contains("_") && userName.length()<=5;
    }
    
    public boolean checkPassword(String password){
        if(password == null){
            return false;
        }
        boolean hasMinLength = password.length()>=8;
        boolean hasCapital = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasNumber = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecialCharacter = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
        
        return hasMinLength && hasCapital && hasNumber && hasSpecialCharacter;
    }
    
    public boolean checkCellPhoneNumber(String cellPhoneNumber){
        if(cellPhoneNumber == null){
            return false;
        }
        String regex = "\\+27\\d{9}$";
        
        return cellPhoneNumber.matches(regex);
    }
    
    public String registerUser(String userName, String password, String cellPhoneNumber, String firstName, String lastName){
        boolean userNameValid = checkUserName(userName);
        boolean passwordValid = checkPassword(password);
        boolean cellValid = checkCellPhoneNumber(cellPhoneNumber);
        
        if(!userNameValid){
            return "Username is not correctly formatted; "
                    + "please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if(!passwordValid){
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters,"
                    + " a capital letter, a number, and a special character.";
        }
        if(!cellValid){
            return "Cell phone number incorrectly formatted or does not contain interantional code.";
        }
        
        this.userName = userName;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isRegistered = true;
        
        return "Username successfully captured. Password successfully captured. Cellphone number successfully captured.";
    }
    
    public boolean loginUser(String userName, String password){
        if(!isRegistered){
            return false;
        }
        return this.userName.equals(userName)&& this.password.equals(password);
    }
    
    public String returnLoginStatus(String userName, String password){
        if(loginUser(userName,password)){
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        }else {
        return "Username or password incorrect, please try again.";
    }
    }
    
    public String getUserName(){
        return userName;
    }
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public boolean isRegistered(){
        return isRegistered;
    }
    
     public String getUserMessage(){
      if(checkUserName(userName)){
          return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
      }
      return "Username is not correctly formatted please ensure that your username contains an underscore and is no more than five characters in length.";
  }
     
     public String getPasswordMessage(){
      if(checkPassword(password)){
          return "Password is successfully capture!";
      }
      return "Password is not correctly formatted; please ensure that the password contains at least eight characters,"
                    + " a capital letter, a number, and a special character.";
  }
     
      public String getCellNumberMessage(){
      if(checkCellPhoneNumber(cellPhoneNumber)){
          return "Cellphone Number successfully captured!";
      }
      return "Cell phone number incorrectly formatted or does not contain interantional code, please correct the number and try again.";
  }
}
