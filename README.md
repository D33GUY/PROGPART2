# PROGPART2
QuickChat — Registration, Login and Messaging

A Java console application where a user registers an account, logs in, and then sends, stores or discards messages through the QuickChat menu.

Built for PROG5121 — Part 1 (Registration and login) and Part 2 (Sending messages).

Author: Osvaldo  Student number:ST10541318

Features

Part 1 — Registration and login

Menu-driven console interface (Register / Login / Exit)
Username, password complexity and cell phone number validation
Login that re-prompts on incorrect credentials (type exit as the username to go back)
Personalised welcome message on successful login

Part 2 — QuickChat

Messages can only be sent after a successful login
The user chooses at the start how many messages they wish to enter
Recipient cell number and message length (max 250 characters) are validated
Each message gets a random ten-digit message ID and an auto-generated message hash
Each message can be sent, disregarded, or stored to send later
Stored messages are written to a JSON file (storedMessages.json)
Full message details and the total number of messages sent are displayed
Project structure
File	Purpose
Login.java	Registration and login logic, plus all validation methods
Message.java	Message creation, validation, hashing, sending, storing (JSON)
Main.java	Entry point; runs the menus and handles user input/output
MessageTest.java	JUnit 5 unit tests for the Message class

Using the program
===== MENU =====
1. Register
2. Login
3. Exit
Register with a username, password, cell phone number, first name and last name.
Login with the same username and password.
After a successful login you will see Welcome to QuickChat. and be asked how many messages you wish to enter.
The QuickChat menu is then shown:
1) Send Messages
2) Show recently sent messages   (displays "Coming Soon.")
3) Quit

For each message you enter the recipient and the message text, then choose to send it, disregard it (press 0 to delete), or store it to send later. Selecting Quit ends the program.

Validation rules
Username
Contains an underscore (_) and is no more than five characters long. Example: kyl_1
Password
At least eight characters, with a capital letter, a number and a special character.
Cell phone number (registration and message recipient)
Starts with + and an international country code (one to three digits), followed by a number of no more than ten digits. Example: +27718693002
Validated with the regular expression ^\+\d{1,3}\d{1,10}$
Message
No more than 250 characters.
Message ID: a random ten-digit number.
Message hash: first two digits of the message ID, the message number, and the first and last words of the message in capitals. Example: 00:0:HITONIGHT
Methods
Login.java
Method	Returns	Description
checkUserName(String)	boolean	Username contains an underscore and is no more than five characters
checkPasswordComplexity(String)	boolean	Password meets all four complexity rules
checkCellPhoneNumber(String)	boolean	Number has an international code and the correct length
registerUser(...)	String	Runs all checks and returns the registration message
loginUser(String, String)	boolean	Checks entered details against the registered details
returnLoginStatus(String, String)	String	Returns the login success or failure message
Message.java
Method	Returns	Description
checkMessageID()	boolean	Message ID is no more than ten characters
checkRecipientCell()	String	Validates the recipient number (reuses Login.checkCellPhoneNumber)
checkMessageLength()	String	Message is ready to send, or by how many characters it is too long
createMessageHash()	String	Builds the message hash
sentMessage(int)	String	Sends (1), disregards (2) or stores (3) the message
storeMessage()	boolean	Writes stored messages to the JSON file
printMessages()	String	Details of all messages sent while the program is running
returnTotalMessages()	int	Total number of messages sent
Known limitations
Only one user can be registered per run. Registration details and sent messages are held in memory and are lost when the program exits.
Stored messages are saved to storedMessages.json in the folder the program runs from.
