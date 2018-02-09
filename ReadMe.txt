The project can be run by entering following link to web browser that supports Java. 

localhost:8080/SJ/index.html

The site was tested on google chrome and it is the recommended browser for this app. 
The first page is index.php . Here, a visitor can either play as a guest by entering his name or log in with 
his/her username and password. Whatever is chosen, the entered credentials are processed in a servlet 
(LoginServlet). 

The servlet generates all session variables and two random numbers and then it takes the visitor to the 
gameview.jsp page where the game board is loaded. The board shows player's name, active score, 
question categories, value of each questions, and clickable buttons posed as questions. The buttons are 
arranged in a grid format and clicking them activates a form which sends the value of button to another 
servlet called GetQ. The GetQ servlet receives the hidden numeric value in the button and turns it to the 
server and user is redirected to game board. Now the game board as a number and it uses that number 
to generate a pop up window to show the question and answer choices. If the question was related to 
the random numbers generated, then with the choices, player also has a field to enter a numeric value 
where the player can enter a custom value that he would earn if he correctly answers a question. There 
is also a choice to skip the question. Whether the question was answered or skip, that button related to 
the question would be gone from the game board next. When the player answers the question, the 
answer is sent to a servlet called Process where the answer is processed, the player's score is updated 
based on whether the answer was right. If there are still questions to answer player is directed to the 
game board, otherwise when there are no more questions left for answering, the player will be taken to 
result.jsp page where the final message is shown. 

The app could use a window where new player can register, have old scores recorded, and compare 
scores with other players. In the future, more questions can be added so that the player can have 
different experience every time he plays the game.  
