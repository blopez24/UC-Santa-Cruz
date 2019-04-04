#####################################################################
# Created by:	Lopez, Bryan
# blopez24
# 15 February 2019
# 
# Assignment:	Lab 3: Draw Triangles
#		CMPE 012, Computer Systems and Assembly Language
#		UC Santa Cruz, Winter 2019

# Description:	This program prints triangles to the console.

# Notes:	This program is intended to be run from MARS IDE.
#####################################################################

.data
	message1: .asciiz "Enter the length of one of the triangle legs: "
	message2: .asciiz "Enter the number of triangles to print: "
	
	theLength: .word 0
	theNumber: .word 0
	
	newLine: .asciiz "\n"
.text
	main:
		# Asks and stores data for the length of one of the triangle legs.
		jal prompt1
		
		# Asks and stores data for the number of triangles to print.
		jal prompt2
		
		# New line
		li $v0, 4
		la $a0, newLine
		syscall
		
		# line counter $t5 = 0
		addi $t5, $zero, 0
		
		# triangle counter $t2 = 0
		addi $t2, $zero, 0
		while1:
			# $t2 = triagnle counter, $t0 = theNumber
			# $t2 == $t0, trianle counter == theNumber
			# while( tCounter < theNumber )
			beq $t2, $t1, end1
			
			# i counter $t3 = 0
			addi $t3, $zero, 0
			while2:
				# $t3 = i counter, $t0 = theLength
				# $t3 == $t0, i == theLength
				# while( i < theLength )
				beq $t3, $t0, end2
				
				# j counter $t4 = 0
				addi $t4, $zero, 0  
				while3:
					# $t4 = j counter, $t3 = i
					# $t4 > $t3, j > i
					# while( j <= i )
					bgt $t4, $t3, end3
					
					# $t4 = j counter, $t3 = i
					# $t4 < $t3, j < i
					# if( j < i )
					blt $t4, $t3, printSpaceJal
					
					jal over1
					printSpaceJal:
						jal printSpace
					over1:
					
					# $t4 = j counter, $t3 = i
					# $t4 == $t3, j == i
					# if( j == i )
					beq $t4, $t3, printBackSlashJal
					
					jal over2
					printBackSlashJal:
						jal printBackSlash
					over2:
					
					# j ++ 
					addi $t4, $t4, 1
					
					# loop again
					j while3
				
				end3:
					
				# i ++
				addi $t3, $t3, 1
				
				j while2
			end2:
			
			# i--
			addi $t3, $t3, -1
			
			while4:
				# $t3 = i counter, $t0 = theLength
				# $t3 == $t0, i == theLength
				# while( i > -1 )
				beq $t3, -1, end4
				
				# j counter $t4 = 0
				addi $t4, $zero, 0
				while5:
					# $t4 = j counter, $t3 = i
					# $t4 > $t3, j > i
					# while( j <= i )
					bgt $t4, $t3, end5
					
					# $t4 = j counter, $t3 = i
					# $t4 < $t3, j < i
					# if( j < i )
					blt $t4, $t3, printSpaceJal2
					
					jal over3
					printSpaceJal2:
						jal printSpace
					over3:
					
					# $t4 = j counter, $t3 = i
					# $t4 == $t3, j == i
					# if( j == i )
					beq $t4, $t3, printFowardSlashJal
					
					jal over4
					printFowardSlashJal:
						jal printFowardSlash
					over4:
					
					# j ++ 
					addi $t4, $t4, 1
					
					# loop again
					j while5
				
				end5:
					
				# i --
				addi $t3, $t3, -1
				
				
				j while4
			end4:
			
			
			# $t2 ++ 
			addi $t2, $t2, 1
			
			# loop again
			j while1
		end1:
		
	# Tells the OS that this is the end of the program
	li $v0, 10
	syscall
	
#----------------------------------------------------------------
	
	# Asks and stores data for length
	prompt1:
		# Prompt the user to enter length of triangle legs.
		li $v0, 4
		la $a0, message1
		syscall
		# Get the user's input
		li $v0, 5
		syscall
		# Store the length in theLength
		sw $v0, theLength
		# store the number of triangles in $t0
		lw $t0, theLength
		
		jr $ra
	
	# Asks and stores data for amount
	prompt2:
		# Prompt the user to enter number of triangles.
		li $v0, 4
		la $a0, message2
		syscall
		# Get the user's input
		li $v0, 5
		syscall
		# Store the number of triangles in theNumber
		sw $v0, theNumber
		# store the number of triangles in $t1
		lw $t1, theNumber
		
		jr $ra
		

	# Prints " "
	printSpace:
		li $v0, 11
		li $a0, 32
		syscall
		
		jr $ra
		
	# Prints "\" and newLine
	printBackSlash:
		# line ++
		addi $t5, $t5, 1
		
		# Prints "\"
		li $v0, 11
		li $a0, 92
		syscall 
		
		# Prints newline
		li $v0, 4
		la $a0, newLine
		syscall
		
		jr $ra
	
	# Prints "/" and newLine
	printFowardSlash:
		# line ++
		addi $t5, $t5, 1
		
		# Prints "/"
		li $v0, 11
		li $a0, 47
		syscall
		
		# $t6 = theLength * theNumber
		mul $t6, $t0, $t1
		# $t6 = %t6 * 2
		sll $t6, $t6, 1
		
		# if line == theLength * theNumber
		# Skips printing newline
		beq $t5, $t6, skip
		
		# Print newline
		printLine:
		li $v0, 4
		la $a0, newLine
		syscall
		
		skip:
		
		jr $ra
