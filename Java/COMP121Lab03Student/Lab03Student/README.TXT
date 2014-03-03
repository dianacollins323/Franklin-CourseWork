Ready To Grade
   
AUTHOR: Diana Collins
     DATE: Mach 10, 2013
GRADE NOW: yes

1.  What test cases are needed, and in what order?

For the ABCRentals class writeToFile and readFromFile methods I needed to create a class that tested the following;
- reading from a file and writing to a file that does not exist
- the data was un altered during the reading and writing process
- updating the inventory would not result in errors and the reading and writing would   
  still occur as before

For the BAtchProcessor class I had to create a couple helper methods to prcess the commands and I had to test the following;
- adding a type other than the specified permitted types
- id numbers which are not in inventory
- commands that are not writen as required
- adding just the item with no other parameters
- passing a file to the process(String string) method

2.  What did you not anticipate (e.g. time allocation, test cases,
    algorithms, methods)?

Testing is still a challenge for me. I did not anticipate not being able to figure out how to test for some cases after being given hints from Web-CAT, but I could not figure out how to test for a ClassNotFoundException or IOException.
	
3.  What would you do differently next time?

I actually think I did really well this time as farr as time management, designing before beginning, and TDD. I'm not sure I would change anything except I would like to know how to test for the above mentioned exceptions.
