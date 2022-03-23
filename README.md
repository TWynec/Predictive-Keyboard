# Predictive-Keyboard
A keyboard application capable of predicting more than half a million English words. Please run src/AutoCompleteStudent.java for a demo. 

# Demo
![Example](PredictiveKeyboard-Demo.gif)

# Description 
This program can predict the words that the user is typing through the use of a prefix tree. The tree has been filled with hundreds of thousands of English words that were scraped from Wikipedia. The tree is then iterated through according to the prefix provided by the user and by sorting the results occurence/popularity to find the most accurate result. The project was entirely developed in Java and uses data structures such as trees, queues, linkedlists, and more. 

AutoCompleteStudent.java contains the code to predict the words as well as the code to create the GUI. WordProcessor.java and WordItem.java are used to scrape the words from a text file.
