##########################################################################
# Created by:  Lopez, Bryan
#              blopez24
#              15 March 2019
#
# Assignment:  Lab 5: Subroutines
#              CMPE 012, Computer Systems and Assembly Language
#              UC Santa Cruz, Winter 2019
# 
# Description: This program prints Encryptes and Decryptes user input.
# 
# Notes:       This program is intended to be run from the MARS IDE.
##########################################################################

#--------------------------------------------------------------------
# give_prompt
#
# This function should print the string in $a0 to the user, store the user’s input in
# an array, and return the address of that array in $v0. Use the prompt number in $a1
# to determine which array to store the user’s input in. Include error checking for
# the first prompt to see if user input E, D, or X if not print error message and ask
# again.
#
# arguments: $a0 - address of string prompt to be printed to user
# $a1 - prompt number (0, 1, or 2)
#
# note: prompt 0: Do you want to (E)ncrypt, (D)ecrypt, or e(X)it?
# prompt 1: What is the key?
# prompt 2: What is the string?
#
# return: $v0 - address of the corresponding user input data
#--------------------------------------------------------------------
.text
give_prompt:
	addi	$sp	$sp	-8		# Create Stack to contrain $a0 and $a1
	sw	$a0	0($sp)			# Store orignal $a0 in $t1
	sw	$a1	4($sp)			# Store orignal $a1 in $t2
		
	# Determine which array to use
	beq	$a1	0	edx		# If $t2 == 1, use array 1 
	beq	$a1	1	key_string	# If $t2 == 2, use array 2 
	beq	$a1	2	input_string	# If $t2 == 3, use array 3 
	
	
edx:
	li	$v0	4			# Print string
	lw	$a0	0($sp)			# Loads address of prompt0	
	syscall					# Prompt 0
						# "\nDo you want to (E)ncrypt, (D)ecrypt, or e(X)it? "
								
	li	$v0	8			# Read string
	la	$a0	array1			# Array 1 address
	li	$a1	3			# Maximium amount of characters: Single character + enter button + null		
	syscall							
		
	lb	$t3	($a0)			# $t3 contains the character input ( E, D, or X )
		
	beq	$t3	0x44	valid		# Character is valid, $t3 == D
	beq	$t3	0x45	valid		# Character is valid, $t3 == E
	beq	$t3	0x58	valid		# Character is valid, $t3 == X
	
	# Character is not valid	
	li		$v0	4		# Print string
	la		$a0 prompt3		# Prompt 3
	syscall					# "Invalid input: Please input E, D, or X.\n"
	
	j edx					# Repeat							
					
valid:
	move 	$v0	$a0			# Move address of  array1 in $v0. 
	j give_prompt_end			# Jump to give_prompt_end label
		
key_string:
	li 	$v0	4			# Print String
	lw	$a0	0($sp)			# Loads address of prompt1	
	syscall					# Prompt 1
						# "What is the key? "
								
	li	$v0	8			# Read string
	la	$a0	array2			# Array 2 address
	li	$a1	102			# Maximium amount of characters: 100 characters + enter button 
	syscall
	
	move	$v0	$a0			# Move address of  array2 in $v0. 
	j give_prompt_end			# Jump to give_prompt_end label
	
input_string:
	li 	$v0	4			# Print String
	lw	$a0	0($sp)			# Loads address of prompt2	
	syscall					# Prompt 2
						# "What is the string? "
								
	li	$v0	8			# Read string
	la	$a0	array3			# Array3 address
	li	$a1	102			# Maximium amount of characters: 100 characters + enter button 
	syscall
	
	move	$v0	$a0			# Move address of  array2 in $v0. 
	j give_prompt_end			# Jump to give_prompt_end label
		
give_prompt_end:	
	lw	$a1	4($sp)			# Load $a1 value from 4($sp)
	lw	$a0	0($sp)			# Load $a0 value from 0($sp)
	addi	$sp	$sp	8		# Destroy stack
	
	jr 	$ra				# Jump to return address
	
.data
array1:	.space	3
array2:	.space	102
array3:	.space	102

prompt3:	.asciiz	"Invalid input: Please input E, D, or X.\n"

#--------------------------------------------------------------------
# cipher
#
# Calls compute_checksum and encrypt or decrypt depending on if the user input E or
# D. The numerical key from compute_checksum is passed into either encrypt or decrypt
#
# note: this should call compute_checksum and then either encrypt or decrypt
#
# arguments: $a0 - address of E or D character
# $a1 - address of key string
# $a2 - address of user input string
#
# return: $v0 - address of resulting encrypted/decrypted string
#--------------------------------------------------------------------
.text
cipher:

	#---------------------------------------------------------------------------------------
	# Work for: compute_checksum
	addi	$sp	$sp	-4		# Create Stack to contrain $ra
	sw	$ra	0($sp)			# Store $ra value in 0($sp)
	
	addi	$sp	$sp	-4		# Create Stack to contain $a0
	sw	$a0	0($sp)			# Store $a0 value in 0($sp)
	
	move	$a0	$a1			# For compute_checksum
						# argumenets $a0 - address of key string
	
	jal compute_checksum			# compute_checksum:
						# Returns:	$v0 numerical checksum result
	
	lw	$a0	0($sp)			# Load $a0 value from 0($sp)
	addi	$sp	$sp	4		# Destory Stack
	
	lw	$ra	0($sp)			# Load $ra value from 0($sp)
	addi	$sp	$sp	4		# Destory Stack
	#---------------------------------------------------------------------------------------
	# Return: $v0 - numerical checksum result (value should be between 0 - 25)

	#---------------------------------------------------------------------------------------
	# Work for: encrypt and decrypt
	lb	$t1	($a0)			# Loads character: E or D into $t1
	la	$t2	($s2)			# Address of user input string
	la	$t3	array4			# Address of resulting encrypted/decrypted string
	
	addi	$t7	$zero	0x00		# $t7 =  null terminator
	
	beq	$t1	0x44	to_decrypt	# $t1 is D
	beq	$t1	0x45	to_encrypt	# $t1 is E
	
	#---------------------------------------------------------------------------------------

	#---------------------------------------------------------------------------------------
	to_decrypt:
	# Work for: decrypt
	addi	$sp	$sp	-4		# Create Stack to contrain $ra
	sw	$ra	0($sp)			# Store $ra value in 0($sp)
	
	addi	$sp	$sp	-8		# Create Stack to contain $a0 and $a1
	sw	$a0	0($sp)			# Store $a0 value in 0($sp)
	sw	$a1	4($sp)			# Store $a0 value in 0($sp)
			
	addi	$t4	$t4	26		# $t4 = 26
	sub	$a1	$t4	$v0		# $a1 = 26 - checksum result 
	
	decrypt_loop:
	lb	$a0	($t2)			# $a0 =  character to decrypt 
	
	beq	$a0	0x00	decrypt_done	# Character is null
	beq	$a0	0x0A	decrypt_done	# Character is new line ( ENTER )
	
	jal decrypt				# decrypt:
						# Returns:	 $v0 - decrypted character 
	
	sb	$v0	0($t3)			# Appends decrypted character
	addi	$t2	$t2	1		# Increase pointer on address of user input string
	addi	$t3	$t3	1		# Increases pointer on address of resulting decrypted string
	
	b	decrypt_loop
	
	decrypt_done:
	sb	$t7	0($t3)			# Appends null terminator
	
	lw	$a1	4($sp)			# Load $a1 value from 0($sp)
	lw	$a0	0($sp)			# Load $a0 value from 0($sp)
	addi	$sp	$sp	8		# Destory Stack
	
	lw	$ra	0($sp)			# Load $ra value from 0($sp)
	addi	$sp	$sp	4		# Destory Stack
	
	la	$v0	array4			# Address of resulting decrypted string
	
	jr	$ra				# Jump to return address
	#---------------------------------------------------------------------------------------

	#---------------------------------------------------------------------------------------
	to_encrypt:
	# Work for: encrypt
	addi	$sp	$sp	-4		# Create Stack to contrain $ra
	sw	$ra	0($sp)			# Store $ra value in 0($sp)
	
	addi	$sp	$sp	-8		# Create Stack to contain $a0 and $a1
	sw	$a0	0($sp)			# Store $a0 value in 0($sp)
	sw	$a1	4($sp)			# Store $a0 value in 0($sp)
	
	move	$a1	$v0			# $a1 = checksum result 
	
	encrypt_loop:
	lb	$a0	($t2)			# $a0 =  character to encrypt 
	
	beq	$a0	0x00	encrypt_done	# Character is null
	beq	$a0	0x0A	encrypt_done	# Character is new line ( ENTER )
	
	jal encrypt				# encrypt:
						# Returns:	 $v0 - encrypted character
	
	sb	$v0	0($t3)			# Appends encrypted character
	addi	$t2	$t2	1		# Increase pointer on address of user input string
	addi	$t3	$t3	1		# Increases pointer on address of resulting decrypted string
	
	b	encrypt_loop
	
	encrypt_done:
	sb	$t7	0($t3)			# Appends null terminator
	
	lw	$a1	4($sp)			# Load $a1 value from 0($sp)
	lw	$a0	0($sp)			# Load $a0 value from 0($sp)
	addi	$sp	$sp	8		# Destory Stack
	
	lw	$ra	0($sp)			# Load $ra value from 0($sp)
	addi	$sp	$sp	4		# Destory Stack
	
	la	$v0	array4			# Address of resulting decrypted string
	
	jr	$ra				# Jump to return address
	#---------------------------------------------------------------------------------------
	
.data
array4:	.space	102
#--------------------------------------------------------------------
# compute_checksum
#
# Computes the checksum by xor’ing each character in the key together. Then,
# use mod 26 in order to return a value between 0 and 25.
#
# arguments: $a0 - address of key string
#
# return: $v0 - numerical checksum result (value should be between 0 - 25)
#--------------------------------------------------------------------
.text
compute_checksum:
	addi	$sp	$sp	-4		# Create Stack to contain $a0
	sw	$a0	0($sp)			# Store $a0 value in 0($sp)
	
	lb	$t2	0($a0)			# $t2 holds the first character
	
	loop_word:
	addi	$a0	$a0	1		# $a0 points to the next character
	lb	$t3	0($a0)			# $t3 holds the character $a0 is pointing to
	beq	$t3	0x00	end_of_word	# Checks if its null
	beq	$t3	0x0A	end_of_word	# Checks if its a new line ( ENTER )
	xor	$t2	$t2	$t3		# Current value xor current character 
	j	loop_word			# Loop
	
	end_of_word:
	addi	$t3	$zero	26		# $t3 is set to be 26
	
	div	$t2	$t3			# $t2:xor value of word / $t3:26 , LO = Quotient and HI = Remainder
	mfhi	$v0				# $v0 holds the remainder
	
	lw	$a0	0($sp)			# Load $a0 value from 0($sp)
	addi	$sp	$sp	4		# Destroy Stack
	
	jr	$ra				# Return to ra
.data

#--------------------------------------------------------------------
# encrypt
#
# Uses a Caesar cipher to encrypt a character using the key returned from
# compute_checksum. This function should call check_ascii.
#
# arguments: $a0 - character to encrypt
# $a1 - checksum result
#
# return: $v0 - encrypted character
#--------------------------------------------------------------------
.text
encrypt:
	addi	$sp	$sp	-4		# Create Stack to contrain $a0 and $a1
	sw	$ra	0($sp)			# Store $ra value in 0($sp)
	
	addi	$sp	$sp	-8		# Create Stack to contain $t0 and $t1
	sw	$t0	0($sp)			# Store $t0 value in 0($sp)
	sw	$t1	4($sp)			# Store $t1 value in 0($sp)
	
	addi	$t0	$zero	0		# Counter, $t0 = 0
	move	$t1	$a0			# $t1 = Character to decrypt 
	
	jal check_ascii				# check_ascii:
						# Returns:	 $v0 - 0 if uppercase, 1 if lowercase, -1 if not letter
					
	
	beq	$v0	-1	e_symbol_shift	# $v0 is not a letter
	beq	$v0	0	e_upperc_shift	# $v0 is an uppercase letter
	beq	$v0	1	e_lowerc_shift	# $v0 is an lowercase letter
	
	e_symbol_shift:				# Character to decrypt stays the same
	b	encrypt_end			# Branch to decrypt_end label
	
	e_upperc_shift:				
	beq	$t0	$a1	encrypt_end	# Done shifting
	
	addi	$t0	$t0	1		# Counter increased by 1 
	addi	$t1	$t1	1		# Character shifted by 1
	
	bge	$t1	91	e_uc_shift_reset	# It passed character 'Z'
	e_uc_shift_reset_back:			# Returns from d_l_shift_reset_back label
	
	b 	e_upperc_shift			# Loops d_lowec_shift label

	e_lowerc_shift:
	beq	$t0	$a1	encrypt_end	# Done shifting
	
	addi	$t0	$t0	1		# Counter increased by 1 
	addi	$t1	$t1	1		# Character shifted by 1
	
	bge	$t1	123	e_lc_shift_reset	# It passed character 'z'
	e_lc_shift_reset_back:			# Returns from d_l_shift_reset_back label
	
	b 	e_lowerc_shift			# Loops d_lowec_shift label
	
	encrypt_end:
	move	$v0	$t1
	
	lw	$t1	4($sp)			# Load $t1 value from 4($sp)
	lw	$t0	0($sp)			# Load $t0 value from 0($sp)
	addi	$sp	$sp	8		# Destroy Stack
	
	lw	$ra	0($sp)			# Load $ra value from 0($sp)
	addi	$sp	$sp	4		# Destory Stack
	
	jr	$ra				# Jump to return address
	
	e_lc_shift_reset:
	addi	$t1	$zero	97
	b	e_lc_shift_reset_back
	
	e_uc_shift_reset:
	addi	$t1	$zero	65
	b	e_uc_shift_reset_back
.data

#--------------------------------------------------------------------
# decrypt
#
# Uses a Caesar cipher to decrypt a character using the key returned from
# compute_checksum. This function should call check_ascii.
#
# arguments: $a0 - character to decrypt
# $a1 - checksum result
#
# return: $v0 - decrypted character
#--------------------------------------------------------------------
.text
decrypt:
	addi	$sp	$sp	-4		# Create Stack to contrain $ra
	sw	$ra	0($sp)			# Store $ra value in 0($sp)
	
	addi	$sp	$sp	-8		# Create Stack to contain $t0 and $t1
	sw	$t0	0($sp)			# Store $t0 value in 0($sp)
	sw	$t1	4($sp)			# Store $t1 value in 0($sp)
	
	addi	$t0	$zero	0		# Counter, $t0 = 0
	move	$t1	$a0			# $t1 = Character to decrypt 
	
	jal check_ascii				# check_ascii:
						# Returns:	 $v0 - 0 if uppercase, 1 if lowercase, -1 if not letter
					
	beq	$v0	-1	d_symbol_shift	# $v0 is not a letter
	beq	$v0	0	d_upperc_shift	# $v0 is an uppercase letter
	beq	$v0	1	d_lowerc_shift	# $v0 is an lowercase letter
	
	d_symbol_shift:				# Character to decrypt stays the same
	b	decrypt_end			# Branch to decrypt_end label
	
	d_upperc_shift:				
	beq	$t0	$a1	decrypt_end	# Done shifting
	
	addi	$t0	$t0	1		# Counter increased by 1 
	addi	$t1	$t1	1		# Character shifted by 1
	
	bge	$t1	91	d_uc_shift_reset	# It passed character 'Z'
	d_uc_shift_reset_back:			# Returns from d_l_shift_reset_back label
	
	b 	d_upperc_shift			# Loops d_lowec_shift label

	d_lowerc_shift:
	beq	$t0	$a1	decrypt_end	# Done shifting
	
	addi	$t0	$t0	1		# Counter increased by 1 
	addi	$t1	$t1	1		# Character shifted by 1
	
	bge	$t1	123	d_lc_shift_reset	# It passed character 'z'
	d_lc_shift_reset_back:			# Returns from d_l_shift_reset_back label
	
	b 	d_lowerc_shift			# Loops d_lowec_shift label
	
	decrypt_end:
	move	$v0	$t1
	
	lw	$t1	4($sp)			# Load $t1 value from 4($sp)
	lw	$t0	0($sp)			# Load $t0 value from 0($sp)
	addi	$sp	$sp	8		# Destroy Stack
	
	lw	$ra	0($sp)			# Load $ra value from 0($sp)
	addi	$sp	$sp	4		# Destory Stack
	
	jr	$ra				# Jump to return address
	
	d_lc_shift_reset:
	addi	$t1	$zero	97
	b	d_lc_shift_reset_back
	
	d_uc_shift_reset:
	addi	$t1	$zero	65
	b	d_uc_shift_reset_back
.data

#--------------------------------------------------------------------
# check_ascii
#
# This checks if a character is an uppercase letter, lowercase letter, or
# not a letter at all. Returns 0, 1, or -1 for each case, respectively.
#
# arguments: $a0 - character to check
#
# return: $v0 - 0 if uppercase, 1 if lowercase, -1 if not letter
#--------------------------------------------------------------------
.text
check_ascii:
	addi	$sp	$sp	-4		# Create Stack to contain $a0
	sw	$a0	0($sp)			# Store $a0 value in 0($sp)

	bge	$a0	0x7B	symbol		# $a0 > z
	bge	$a0	0x61	lowercase	# z >= $a0 >= a
	bge	$a0	0x5B	symbol		# a > $a0 > Z
	bge	$a0	0x41	uppercase	# Z >= $a0 >= A
	j symbol					# A > $a0
	
	symbol:
	addi	$v0	$zero	-1		# Set $v0 to -1
	j check_ascii_end			# Jump to label
	
	lowercase:
	addi	$v0	$zero	1		# Set $v0 to 1
	j check_ascii_end			# Jump to label
	
	uppercase:
	addi	$v0	$zero	0		# Set $v0 to 0
	j check_ascii_end			# Jump to label
	
	check_ascii_end:		
	lw	$a0	0($sp)			# Load $a0 value from 0($sp)
	addi	$sp	$sp	4		# Destory Stack contains $a0
	
	jr	$ra				# Jump to return address
.data

#--------------------------------------------------------------------
# print_strings
#
# Determines if user input is the encrypted or decrypted string in order
# to print accordingly. Prints encrypted string and decrypted string. See
# example output for more detail.
#
# arguments: $a0 - address of user input string to be printed
# $a1 - address of resulting encrypted/decrypted string to be printed
# $a2 - address of E or D character
#
# return: prints to console
#--------------------------------------------------------------------
.text
print_strings:
	addi	$sp	$sp	-12		# Create Stack to contrain $a0, $a1, $a2
	sw	$a0	0($sp)			# Store $a0 value in 0($sp)
	sw	$a1	4($sp)			# Store $a1 value in 4($sp)
	sw	$a2	8($sp)			# Store $a2 value in 8($sp)

	lb	$t3	($a2)			# $t3 contains the character input ( E, D, or X )
	
	move	$t1	$a0
	
	beq	$t3	0x44	d_print		# User input is decrypted string
	beq	$t3	0x45	e_print		# User input is encrypted string
	
	d_print:
	li	$v0	4			# Prints string
	la	$a0	prompt4			# "\nHere is the encrypted and decrypted string"
	syscall
	
	li	$v0	4			# Prints string
	la	$a0	newline			# "\n"
	syscall
	
	li	$v0	4			# Prints string
	la	$a0	e_word			# "<Encrypted>"
	syscall
	
	li	$v0	4			# Prints string
	la	$a0	($t1)			# User input string 
	syscall
	
	li	$v0	4			# Prints string
	la	$a0	d_word			# "<Decrypted>"
	syscall
	
	li	$v0	4			# Prints string
	la	$a0	($a1)			# Decrypted string
	syscall			
	
	li	$v0	4			# Prints string
	la	$a0	newline			# "\n"
	syscall
	
	jr	$ra				# Jump to return address
	
	e_print:
	
	li	$v0	4			# Prints string
	la	$a0	prompt4			# "\nHere is the encrypted and decrypted string"
	syscall
	
	li	$v0	4			# Prints string
	la	$a0	newline			# "\n"
	syscall
	
	li	$v0	4			# Prints string
	la	$a0	e_word			# "<Encrypted>"
	syscall
	
	li	$v0	4			# Prints string
	la	$a0	($a1)			# User input string 
	syscall
	
	li	$v0	4			# Prints string
	la	$a0	newline			# "\n"
	syscall
	
	li	$v0	4			# Prints string
	la	$a0	d_word			# "<Decrypted>"
	syscall
	
	li	$v0	4			# Prints string
	la	$a0	($t1)			# Decrypted string
	syscall	
	
	sw	$a2	8($sp)			# Load $a2 value from 8($sp)
	sw	$a1	4($sp)			# Load $a2 value from 8($sp)
	sw	$a0	0($sp)			# Load $a2 value from 8($sp)
	addi	$sp	$sp	12		# Destory Stack to contrain $a0, $a1, $a2
	
	jr	$ra				# Jump to return address
.data
prompt4:	.asciiz "\nHere is the encrypted and decrypted string"
e_word:	.asciiz "<Encrypted> "
d_word:	.asciiz "<Decrypted> "
newline:	.asciiz "\n"
