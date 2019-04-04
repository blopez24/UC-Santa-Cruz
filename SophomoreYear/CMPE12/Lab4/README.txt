 ------------------------
 Lab 4: ASCII (HEX or 2SC) to Base 4
 CMPE 012 Winter 2019

 Lopez, Bryan
 blopez24
 ------------------------- 
 What was your approach to converting each ASCII input to two’s complement form?
 I went around it. I read the strings and stored the values into sign decimal int 
 values. Depending on the type, I would either multiply it by binary and hex.
 After converting it, I would store it in its respected register ($s1 / $s2).
 What followed next was difficult to solve. The new problem was that the values
 were stored in ints, but the lab does not allow syscall 1.
 
 What did you learn in this lab?
 I learned how to read strings passed on the program arguments. I learned how to
 access the address of $a1 and print each word. I learned about sp and more about
 how it stores the strings in hex values.
 
 Did you encounter any issues? Were there parts of this lab you found enjoyable?
 Since I didn't convert each ASCII input to two's complement form, I had to figure
 out how to go from ints to char. I went from int to char by doing if statements
 and printing/storing them.
 
 How would you redesign this lab to make it better? 
 I would reduce the amount of if conditions. There are too many of them which make
 it hard to follow. I would try to use loops to reduce repetition.
 
 Did you collaborate with anyone on this lab? Please list who you collaborated with and the nature of your collaboration. 
 I did not collaborate with anyone else on this lab. All work is done by me.