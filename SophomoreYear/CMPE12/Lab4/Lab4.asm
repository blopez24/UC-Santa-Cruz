##########################################################################
# Created by: Lopez, Bryan
# blopez24
# 20 February 2019
#
# Assignment: Lab 4: ASCII Conversion
# CMPE 012, Computer Systems and Assembly Language
# UC Santa Cruz, Winter 2019
#
# Description: This program will read a string input and convert ASCII
# characters into a base 4 number and print it to the console.
#
# Notes: This program is intended to be run from the MARS IDE.
##########################################################################
# Part A
# Psuedo Code
# Main
# {
#	# Read two 8-bit 2SC program arguments
#	functionRead(Argument one, Argument two)
#	{
#		String1 = one
#		String2 = two
#	}
#	
#	# Print the user inputs. 
#	print()
#	{
#		print("You entered the numbers:")
#		print(newline)
#		print(one + " " + two)
#	}
#	
#	# Identify each number
#	prefix()
#	{
#		if( second character of String1 equals b )
#			String1 is binary. Leave as is
#		else
#			String1 is hex. Convert to hex 
#			String1 = convertHexToBinary( String1 )
#		
#		if( second character of String2 equals b )
#			String2 is binary
#		else
#			String2 is hex. Convert to hex 
#			String2 = convertHexToBinary( String2 )
#	}
#	
#	# Convert String from hex to binary
#	convertHexToBinary(String s)
#	{
#		String temp = "0b"
#		
#		for(int i = 0; i < 2; i++)
#		{
#			if( i character of s  equals 0)
#				temp += 0000
#			else if( i character of s  equals 1 )
#				temp += 0001
#			else if( i character of s  equals 2 )
#				temp += 0010
#				
#			...
#			
#			else if( i character of s  equals E )
#				temp += 1110
#			...
#			
#			else
#				temp += 1111
#		}
#		
#	}
#	
#	# Convert the ASCII strings into two sign-extended integer values. 
#	convert()
#	{
#		# Cut strings to just numbers
#		# Ex: 0b00110011 to 00110011
#		cut()
#	
#		String1 = binaryConvert( String1 )
#		String2 = binaryConvert( String2 )
#	}
#	
#	# Add the two integer values, store the sum in $s0. 
#	add()
#	{
#		String $s0 = String1 + String2
#	}
#	
#	convertToBase4()
#	{
#		if( first character is 1 )
#			String $s0 is negative
#			magnitude = value of String $s0 without first character
#			convert magnitude to base 4
#			println("-" + magnitude)
#			
#		else
#			String $s0 is positive
#			magnitude = value of String $s0 without first character
#			convert magnitude to base 4
#			println(magnitude)
#	}
#	
#	# Cut the Strings
#	cut()
#	{
#		String1 = substring(2, 10)	# Ex: 0b00110011 to 00110011
#			
#		String2 = substring(2, 10)	# Ex: 0b00110011 to 00110011
#	}
#	
#	# String is 8-bit 2SC binary
#	binaryConvert(String s)
#	{
#		String temp = ""
#		if( first charcter is 0 )
#		{
#			8-bit 2SC is positive
#			temp += just add 24 0's in front + s
#		}
#		else	# is negative
#		{
#			8-bit 2SC is positive
#			temp +=  sign extending, repeat the MSB + s
#		}
#	}
#	
# }
##########################################################################
.data
	message1:	.asciiz "You entered the numbers:"
	message2:	.asciiz "\nThe sum in base 4 is:"
	
	newLine:	.asciiz "\n"
	space:		.asciiz " "
.text
	main:
		# Loads Arguments
		jal loadArgs
		
		# Prints message1
		jal printMessage1
		
		# Prints User Inputs
		jal printUserInput
		
		# Prints  new line
		jal printNL
		
		# Prints message2
		jal printMessage2
		
		# Determine arguments 1
		jal arg1
		
		# Determine arguments 2
		jal arg2
		
		# Note To Self:
		# DO NOT CHANGE values in $s1 and $s2
		
		
		# Storing the sum of $s1 and $s2 in $s0
		add $t7 $s1 $s2
		move $s0 $t7
		
		# Note To Self:
		# DO NOT CHANGE value $s0
		
		# Converting decimal value to base 4
		j convertToBase4
		convertToBase4Back:
		
		# Print Base4 values
		j printInBase4
		printInBase4Back:
	
	end:
		# Prints last empty new line
		jal printNL
			
	# Tells the OS that this is the end of the program
	li $v0, 10
	syscall
		

#-------------------------------------------------------------------------------------------------
# Methods:
	
	# Load Arguments
	loadArgs:
		FirstArg:
			lw $s1, ($a1)	# Loads the address of the first argument
			lb $t1, 1($s1)	# Loads second char of Argument1 to $t1
			
		SecondArg:
			lw $s2, 4($a1)	# Loads the address of the second argument
			lb $t2, 1($s2)	# Loads second char of Argument2 to $t2
		jr $ra
		
	# Prints new line
	printNL:
		li $v0, 4
		la $a0 newLine
		syscall
		jr $ra
	
	# Prints Message1
	printMessage1:
		# Prints message1
		li $v0, 4
		la $a0, message1
		syscall
		
		# Prints new line
		li $v0, 4
		la $a0, newLine
		syscall
		
		jr $ra
		
	# Print User Inputs
	printUserInput:
		# Print the contents of the first argument
		li $a0, 4
		la $a0, ($s1)
		syscall
		
		# Prints space
		li $v0, 4
		la $a0, space
		syscall
		
		# Print the contents of the second argument
		li $a0, 4
		la $a0, ($s2)
		syscall
		
		jr $ra
		
	# Prints Message2
	printMessage2:
		# Prints message2
		li $v0, 4
		la $a0, message2
		syscall
		
		# Prints new line
		li $v0, 4
		la $a0, newLine
		syscall
		
		jr $ra

	# Determines if argument1 is binary or hex
	# $t1 contains second char of Argument 1
	arg1:
		beq $t1 0x62 itsBinary1		# Second char of Argument1 == b
		
		beq $t1 0x78 itsHex1		# Else second char of Argument1 == x
		
		itsBinary1Back:			# Return from itsBinary1
		itsHex1Back:			# Return from itsHexBack1
		
		jr $ra				# Returns to main
	
	# Determines if argument1 is binary or hex
	# $t2 contains first char of Argument 2
	arg2:
		beq $t2 0x62 itsBinary2		# Second char of Argument2 == b
		
		beq $t2 0x78 itsHex2		# Else second char of Argument2 == x
		
		itsBinary2Back:			# Return from itsBinary2
		itsHex2Back:			# Return from itsHexBack2
		
		jr $ra				# Returns to main
	
	# Converting decimal value to int
	# Int is stored in $t7		
	convertToBase4:
		addi $t6 $zero 4		# $t6 contains 4
						# $t6 is going to be used to divide 
		
		# First check if decimal value is negative
		blt $t7 $zero, negativeToPositive
		negativeToPositiveBack:		# Label to return to convertToBase4
		
		# Make space to store base 4 outputs
		# MIN of $s0 = -256
		# MAX of $s0 = 254
		jal fake_stack
		
		# Time to convert
		# Math Below
		addi $t5 $zero 1		# Counter to keep track of stack placement
		base4loop:
			div $t7 $t6 		# Int / 4: LO = quotient, HI = remainder
			mflo $t7		# Int = $t7 = LO = quotient
			j storeInFakeStack	# Remainder is stored in $t(counter)
			storeBack:			# Returns from store label
			addi $t5 $t5 1		# Increase counter
			bne $t7 $zero base4loop	# If quotient is not zero loop again
			
		j convertToBase4Back
		
	# Converts negative decimal value to positive decimal value
	negativeToPositive:
		mul $t7 $t7 -1			# Multiply negative value by -1
						# -$t7 * -1 = $t7 
		# Prints new negative sign "-"
		li $v0 11			
		la $a0 0x2D
		syscall
		
		j negativeToPositiveBack
	
	# Turns registers $t0 - $t4 to hold 4
	# Highest value in base 4 is 3
	fake_stack:
		addi $t4 $zero 4
		addi $t3 $zero 4
		addi $t2 $zero 4
		addi $t1 $zero 4
		addi $t0 $zero 4
		
		jr $ra
	
	# Store remainders in correct spot
	storeInFakeStack:
		beq $t5 1 at_5	# If counter ($t6) == 1
		beq $t5 2 at_4	# If counter ($t6) == 2
		beq $t5 3 at_3	# If counter ($t6) == 3
		beq $t5 4 at_2	# If counter ($t6) == 4
		beq $t5 5 at_1	# If counter ($t6) == 5
		
		at_5:		# Store remainder in $t4
		mfhi $t4 
		j storeBack	# Return to store label
		
		at_4:		# Store remainder in $t3
		mfhi $t3
		j storeBack	# Return to store label
		
		at_3:		# Store remainder in $t2
		mfhi $t2
		j storeBack	# Return to store label
		
		at_2:		# Store remainder in $t1
		mfhi $t1
		j storeBack	# Return to store label
		
		at_1:		# Store remainder in $t0
		mfhi $t0
		j storeBack	# Return to store label
		
	# Prints Base4 values
	printInBase4:
		beq $t0 4 nextValue2
		move $t7 $t0
		jal printChar
			
		nextValue2:
		beq $t1 4 nextValue3
		move $t7 $t1
		jal printChar
			
		nextValue3:
		beq $t2 4 nextValue4
		move $t7 $t2
		jal printChar
			
		nextValue4:
		beq $t3 4 nextValue5
		move $t7 $t3
		jal printChar
		
		nextValue5:
		beq $t4 4 end
		move $t7 $t4
		jal printChar
		
		j end
			
	# Arguments: $t7 
	# Determins what value is in $t7 and prints its char 
	printChar:
		beq $t7 0 char_0	# Value = 0
		beq $t7 1 char_1	# Value = 1
		beq $t7 2 char_2	# Value = 2
		beq $t7 3 char_3	# Value = 3
		
		char_0:			# Value = 0
		li $v0 11
		la $a0 0x30		# Print '0'
		syscall
		jr $ra
		
		char_1:			# Value = 1
		li $v0 11
		la $a0 0x31		# Print '1'
		syscall
		jr $ra
		
		char_2:			# Value = 2
		li $v0 11
		la $a0 0x32		# Print '2'
		syscall
		jr $ra
		
		char_3:			# Value = 3
		li $v0 11
		la $a0 0x33		# Print '3'
		syscall
		jr $ra
		
#-------------------------------------------------------------------------------------------------
# Ugly Methods:
# Reads and Calculates Argument 1

	# First Argument is binary
	# Loads character from argument1 (3rd - 10th)
	# Adds the characters that are "1" with correct value
	itsBinary1:
			addi $t7 $zero 0	# Hold the value of binary input
						# $t7 = 0, initially
			step1:
			lb $t0, 2($s1)		# Loads 3rd char of Argument1 to $t1
			bne $t0, 0x31, step2	# 3rd char is 0
			addi $t7 $t7 -128	# 3rd char is 1, $t7 += -128
			
			step2:
			lb $t0, 3($s1)		# Loads 4th char of Argument1 to $t1
			bne $t0, 0x31, step3	# 4th char is 0
			addi $t7 $t7 64		# 4th char is 1, $t7 += 64
			
			step3:
			lb $t0, 4($s1)		# Loads 5th char of Argument1 to $t1
			bne $t0, 0x31, step4	# 5th char is 0
			addi $t7 $t7 32		# 5th char is 1, $t7 += 32
			
			step4:
			lb $t0, 5($s1)		# Loads 6th char of Argument1 to $t1
			bne $t0, 0x31, step5	# 6th char is 0
			addi $t7 $t7 16		# 6th char is 1, $t7 += 16
			
			step5:
			lb $t0, 6($s1)		# Loads 7th char of Argument1 to $t1
			bne $t0, 0x31, step6	# 7th char is 0
			addi $t7 $t7 8		# 7th char is 1, $t7 += 8
			
			step6:
			lb $t0, 7($s1)		# Loads 8th char of Argument1 to $t1
			bne $t0, 0x31, step7	# 8th char is 0
			addi $t7 $t7 4		# 8th char is 1, $t7 += 4
			
			step7:
			lb $t0, 8($s1)		# Loads 9th char of Argument1 to $t1
			bne $t0, 0x31, step8	# 9th char is 0
			addi $t7 $t7 2		# 9th char is 1, $t7 += 2
			
			step8:
			lb $t0, 9($s1)		# Loads 10th char of Argument1 to $t1
			bne $t0, 0x31, step9	# 10th char is 0
			addi $t7 $t7 1		# 10th char is 1, $t7 += 1
			
			step9:
			move $s1 $t7
			
			j itsBinary1Back	# Returns to itsBinary1 Label
			
	# First Argument is hex
	# Loads character from argument1 (3rd - 10th)
	# Determines character and adds it 
	itsHex1:
			addi $t7 $zero 0	# Hold the value of hex input
						# $t7 = 0, initially
			
			lb $t0, 2($s1)	# Loads 3rd char of Argument1 to $t1	
			j itsHex1Math1a
			itsHex1Math1Back:
			
			lb $t0, 3($s1)	# Loads 3rd char of Argument1 to $t1
			j itsHex1Math1b
			itsHex1Math1bBack:
			
			move $s1 $t7
			
			j itsHex1Back		# Returns itsHex1 Label
	
	# Helper Method to itsHex1
	# Determines chars and adds it 		
	itsHex1Math1a:
		beq $t0, 0x46, hex1a
		beq $t0, 0x45, hex2a
		beq $t0, 0x44, hex3a
		beq $t0, 0x43, hex4a
		beq $t0, 0x42, hex5a
		beq $t0, 0x41, hex6a
		beq $t0, 0x39, hex7a
		beq $t0, 0x38, hex8a
		beq $t0, 0x37, hex9a
		beq $t0, 0x36, hex10a
		beq $t0, 0x35, hex11a
		beq $t0, 0x34, hex12a
		beq $t0, 0x33, hex13a
		beq $t0, 0x32, hex14a
		beq $t0, 0x31, hex15a
		beq $t0, 0x30, hex16a
		
		hex1a:
			addi $t7 $t7 -16
			j itsHex1Math1Back
		hex2a:
			addi $t7 $t7 -32
			j itsHex1Math1Back
		hex3a:
			addi $t7 $t7 -48
			j itsHex1Math1Back
		hex4a:
			addi $t7 $t7 -64
			j itsHex1Math1Back
		hex5a:
			addi $t7 $t7 -80
			j itsHex1Math1Back
		hex6a:
			addi $t7 $t7 -96
			j itsHex1Math1Back
		hex7a:
			addi $t7 $t7 -112
			j itsHex1Math1Back
		hex8a:
			addi $t7 $t7 -128
			j itsHex1Math1Back
		hex9a:
			addi $t7 $t7 112
			j itsHex1Math1Back
		hex10a:
			addi $t7 $t7 96
			j itsHex1Math1Back
		hex11a:
			addi $t7 $t7 80
			j itsHex1Math1Back
		hex12a:
			addi $t7 $t7 64
			j itsHex1Math1Back
		hex13a:
			addi $t7 $t7 48
			j itsHex1Math1Back
		hex14a:
			addi $t7 $t7 32
			j itsHex1Math1Back
		hex15a:
			addi $t7 $t7 16
			j itsHex1Math1Back
		hex16a:
			addi $t7 $t7 0
			j itsHex1Math1Back
			
	# Helper Method to itsHex1
	# Determines chars and adds it 		
	itsHex1Math1b:
		beq $t0, 0x46, hex1b
		beq $t0, 0x45, hex2b
		beq $t0, 0x44, hex3b
		beq $t0, 0x43, hex4b
		beq $t0, 0x42, hex5b
		beq $t0, 0x41, hex6b
		beq $t0, 0x39, hex7b
		beq $t0, 0x38, hex8b
		beq $t0, 0x37, hex9b
		beq $t0, 0x36, hex10b
		beq $t0, 0x35, hex11b
		beq $t0, 0x34, hex12b
		beq $t0, 0x33, hex13b
		beq $t0, 0x32, hex14b
		beq $t0, 0x31, hex15b
		beq $t0, 0x30, hex16b
		
		hex1b:
			addi $t7 $t7 15
			j itsHex1Math1bBack
		hex2b:
			addi $t7 $t7 14
			j itsHex1Math1bBack
		hex3b:
			addi $t7 $t7 13
			j itsHex1Math1bBack
		hex4b:
			addi $t7 $t7 12
			j itsHex1Math1bBack
		hex5b:
			addi $t7 $t7 11
			j itsHex1Math1bBack
		hex6b:
			addi $t7 $t7 10
			j itsHex1Math1bBack
		hex7b:
			addi $t7 $t7 9
			j itsHex1Math1bBack
		hex8b:
			addi $t7 $t7 8
			j itsHex1Math1bBack
		hex9b:
			addi $t7 $t7 7
			j itsHex1Math1bBack
		hex10b:
			addi $t7 $t7 6
			j itsHex1Math1bBack
		hex11b:
			addi $t7 $t7 5
			j itsHex1Math1bBack
		hex12b:
			addi $t7 $t7 4
			j itsHex1Math1bBack
		hex13b:
			addi $t7 $t7 3
			j itsHex1Math1bBack
		hex14b:
			addi $t7 $t7 2
			j itsHex1Math1bBack
		hex15b:
			addi $t7 $t7 1
			j itsHex1Math1bBack
		hex16b:
			addi $t7 $t7 0
			j itsHex1Math1bBack
			
#-------------------------------------------------------------------------------------------------
# Ugly Methods:
# Reads and Calculates Argument 2

	# Second Argument is binary
	# Loads character from argument1 (3rd - 10th)
	# Adds the characters that are "1" with correct value
	itsBinary2:
			addi $t7 $zero 0	# Hold the value of binary input
						# $t7 = 0, initially
			step1b:
			lb $t0, 2($s2)		# Loads 3rd char of Argument1 to $t1
			bne $t0, 0x31, step2b	# 3rd char is 0
			addi $t7 $t7 -128	# 3rd char is 1, $t7 += -128
			
			step2b:
			lb $t0, 3($s2)		# Loads 4th char of Argument1 to $t1
			bne $t0, 0x31, step3b	# 4th char is 0
			addi $t7 $t7 64		# 4th char is 1, $t7 += 64
			
			step3b:
			lb $t0, 4($s2)		# Loads 5th char of Argument1 to $t1
			bne $t0, 0x31, step4b	# 5th char is 0
			addi $t7 $t7 32		# 5th char is 1, $t7 += 32
			
			step4b:
			lb $t0, 5($s2)		# Loads 6th char of Argument1 to $t1
			bne $t0, 0x31, step5b	# 6th char is 0
			addi $t7 $t7 16		# 6th char is 1, $t7 += 16
			
			step5b:
			lb $t0, 6($s2)		# Loads 7th char of Argument1 to $t1
			bne $t0, 0x31, step6b	# 7th char is 0
			addi $t7 $t7 8		# 7th char is 1, $t7 += 8
			
			step6b:
			lb $t0, 7($s2)		# Loads 8th char of Argument1 to $t1
			bne $t0, 0x31, step7b	# 8th char is 0
			addi $t7 $t7 4		# 8th char is 1, $t7 += 4
			
			step7b:
			lb $t0, 8($s2)		# Loads 9th char of Argument1 to $t1
			bne $t0, 0x31, step8b	# 9th char is 0
			addi $t7 $t7 2		# 9th char is 1, $t7 += 2
			
			step8b:
			lb $t0, 9($s2)		# Loads 10th char of Argument1 to $t1
			bne $t0, 0x31, step9b	# 10th char is 0
			addi $t7 $t7 1		# 10th char is 1, $t7 += 1
			
			step9b:
			
			move $s2 $t7
			
			j itsBinary2Back	# Returns to itsBinary1 Label
			
	# First Argument is hex
	# Loads character from argument1 (3rd - 10th)
	# Determines character and adds it 
	itsHex2:
			addi $t7 $zero 0	# Hold the value of hex input
						# $t7 = 0, initially
			
			lb $t0, 2($s2)	# Loads 3rd char of Argument1 to $t1	
			j itsHex2Math1a
			itsHex2Math1Back:
			
			lb $t0, 3($s2)	# Loads 3rd char of Argument1 to $t1
			j itsHex2Math1b
			itsHex2Math1bBack:
			
			move $s2 $t7
			
			j itsHex2Back		# Returns itsHex1 Label
	
	# Helper Method to itsHex1
	# Determines chars and adds it 		
	itsHex2Math1a:
		beq $t0, 0x46, hex1aa
		beq $t0, 0x45, hex2aa
		beq $t0, 0x44, hex3aa
		beq $t0, 0x43, hex4aa
		beq $t0, 0x42, hex5aa
		beq $t0, 0x41, hex6aa
		beq $t0, 0x39, hex7aa
		beq $t0, 0x38, hex8aa
		beq $t0, 0x37, hex9aa
		beq $t0, 0x36, hex10aa
		beq $t0, 0x35, hex11aa
		beq $t0, 0x34, hex12aa
		beq $t0, 0x33, hex13aa
		beq $t0, 0x32, hex14aa
		beq $t0, 0x31, hex15aa
		beq $t0, 0x30, hex16aa
		
		hex1aa:
			addi $t7 $t7 -16
			j itsHex2Math1Back
		hex2aa:
			addi $t7 $t7 -32
			j itsHex2Math1Back
		hex3aa:
			addi $t7 $t7 -48
			j itsHex2Math1Back
		hex4aa:
			addi $t7 $t7 -64
			j itsHex2Math1Back
		hex5aa:
			addi $t7 $t7 -80
			j itsHex2Math1Back
		hex6aa:
			addi $t7 $t7 -96
			j itsHex2Math1Back
		hex7aa:
			addi $t7 $t7 -112
			j itsHex2Math1Back
		hex8aa:
			addi $t7 $t7 -128
			j itsHex2Math1Back
		hex9aa:
			addi $t7 $t7 112
			j itsHex2Math1Back
		hex10aa:
			addi $t7 $t7 96
			j itsHex2Math1Back
		hex11aa:
			addi $t7 $t7 80
			j itsHex2Math1Back
		hex12aa:
			addi $t7 $t7 64
			j itsHex2Math1Back
		hex13aa:
			addi $t7 $t7 48
			j itsHex2Math1Back
		hex14aa:
			addi $t7 $t7 32
			j itsHex2Math1Back
		hex15aa:
			addi $t7 $t7 16
			j itsHex2Math1Back
		hex16aa:
			addi $t7 $t7 0
			j itsHex2Math1Back
			
	# Helper Method to itsHex1
	# Determines chars and adds it 		
	itsHex2Math1b:
		beq $t0, 0x46, hex1bb
		beq $t0, 0x45, hex2bb
		beq $t0, 0x44, hex3bb
		beq $t0, 0x43, hex4bb
		beq $t0, 0x42, hex5bb
		beq $t0, 0x41, hex6bb
		beq $t0, 0x39, hex7bb
		beq $t0, 0x38, hex8bb
		beq $t0, 0x37, hex9bb
		beq $t0, 0x36, hex10bb
		beq $t0, 0x35, hex11bb
		beq $t0, 0x34, hex12bb
		beq $t0, 0x33, hex13bb
		beq $t0, 0x32, hex14bb
		beq $t0, 0x31, hex15bb
		beq $t0, 0x30, hex16bb
		
		hex1bb:
			addi $t7 $t7 15
			j itsHex2Math1bBack
		hex2bb:
			addi $t7 $t7 14
			j itsHex2Math1bBack
		hex3bb:
			addi $t7 $t7 13
			j itsHex2Math1bBack
		hex4bb:
			addi $t7 $t7 12
			j itsHex2Math1bBack
		hex5bb:
			addi $t7 $t7 11
			j itsHex2Math1bBack
		hex6bb:
			addi $t7 $t7 10
			j itsHex2Math1bBack
		hex7bb:
			addi $t7 $t7 9
			j itsHex2Math1bBack
		hex8bb:
			addi $t7 $t7 8
			j itsHex2Math1bBack
		hex9bb:
			addi $t7 $t7 7
			j itsHex2Math1bBack
		hex10bb:
			addi $t7 $t7 6
			j itsHex2Math1bBack
		hex11bb:
			addi $t7 $t7 5
			j itsHex2Math1bBack
		hex12bb:
			addi $t7 $t7 4
			j itsHex2Math1bBack
		hex13bb:
			addi $t7 $t7 3
			j itsHex2Math1bBack
		hex14bb:
			addi $t7 $t7 2
			j itsHex2Math1bBack
		hex15bb:
			addi $t7 $t7 1
			j itsHex2Math1bBack
		hex16bb:
			addi $t7 $t7 0
			j itsHex2Math1bBack
#-------------------------------------------------------------------------------------------------