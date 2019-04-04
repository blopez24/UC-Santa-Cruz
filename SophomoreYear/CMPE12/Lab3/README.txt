------------------------ 
Lab 3: MIPS Looping ASCII Art
CMPE 012 Winter 2019 
Lopez, Bryan
blopez24
------------------------- 
The text of your prompts are stored in the processor’s memory. After assembling your program, what is the range of addresses in which these strings are stored? 
The range of addresses in which the prompt strings are stored in an asciiz.
An asciiz string requires 4 bytes of storage.
The max range of the addresses is 0xFFFF FFFF or 32bits.


What were the learning objectives of this lab?
This lab introduced MIPS ISA using ​MARS​.
The learning objectives of this lab were: learning to use registers, loops, user input, and print to console.
The lab introduced how to use registers to store data and integrate it with loops and user input.

Did you encounter any issues? Were there parts of this lab you found enjoyable?
I encounter may issue trying to complete lab 3.
The first issue I encountered dealt with loops.
I created an infinite loop, due to not correctly increase the counter.
Therefore, the branch was never used, creating an infinite loop.
The second issue I encountered involved in implementing conditions (if / else).
Parts of code that should have been skipped over were read.
I had to figure out how to make sure to only read the line of code when it meets the conditions.
The last issue I encounter was printing an extra newline at the end.


How would you redesign this lab to make it better?
I would make my code cleaner to read. 
Increase the number of comments to explain.

Did you collaborate with anyone on this lab?
Please list who you collaborated with and the nature of your collaboration.
Worked all alone.
Did not recieved help from anyone else.
The most useful help came from the youtube tutorials posted in piazza.
https://www.youtube.com/watch?v=u5Foo6mmW0I&list=PL5b07qlmA3P6zUdDf-o97ddfpvPFuNa5A