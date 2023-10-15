.data
	String_Arr_A: .space 100
	String_Arr_x:	 .space 100
	Int_Arr_A: .space 100
	Int_Arr_x: .space 100
	Int_Arr_b: .space 100
.asciiz
	space: " "
	nextLine: "\n"
	inputA: "Input 5 X 5 Matrix - A \n : "
	inputX: "Input 5 X 1 Matrix - x \n : "
	printA: "\n= 5 X 5 Matrix - A ="
	printx: "\n= 5 X 5 Matrix - x =\n"
	printb: "\n= 5 X 1 Matrix - b ="

.text
main:
	li $a1 100
	li $s3 5
	
	li $v0 4 # print String
	la $a0 inputA 
	syscall

	li $v0 8 # Input String
	la $a0 String_Arr_A
	syscall
	move $s5 $a0		# s5 = "1 2 3 4 5 ..."
	
	li $v0 4 
	la $a0 inputX
	syscall

	li $v0 8
	la $a0 String_Arr_x
	syscall
	
	move $s6 $a0		# s6 = "1 2 3 4 5"
	move $t2 $s5
	
countChr_A:  			#for(int i=0; i<=A.size(); i++);
  	lb $t0 0($t2) 		# int t0 = (char)s5[i]; .... t0 == ascii
   	beqz $t0 set_A   	# if t0 == null, then Go to rest and end
    	addi $t2 $t2 1     	# else i++;
   	addi $t7 $t7 1 		# countChr++;
	j countChr_A 	  
    
set_A:	
	addi $t7 $t7 -1		# i-=1
	
	la $s0 Int_Arr_A 	# s0 = malloc(sizeof(100)), MatrixA's address
	move $s1 $s0		# *s1 = s0; 
	
	move $t2 $s5
	li $t3 ' '	
	li $t4 0			# 10's
	
Loop_A:				# while
	lb $t0 0($t2) 			
	beq $t1 $t7 endLoop_A	# if countChr == i, then do break after moveOn;
    	beq $t0 $t3 space_A	# elif Matrix A's ASCII == ' ', then isSpace
  	
    	jal transform		# char -> int
    	jal increment		# i++
    		
    	j Loop_A
    	
space_A:
	sw $t4 0($s1)	# s0[i] = t4; // input - Integer in Matrix A
    	addi $s1 $s1 4			
    	
    	li $t4 0
    	jal increment
    	
	j Loop_A

endLoop_A:
	sw $t4 0($s1)		# last chr == '\n', not isSpace So, s1[i] = t4;
	la $s1 Int_Arr_x 
		
	move $s2 $s1	
	move $t2 $s6
	li $t3  ' '	
	li $t7 0

countChr_x:  
  	lb $t0 0($t2)  		# int t0 = (char)t2[i];
   	beqz $t0 set_x   	# if t0 == null, then Go to label and end  
    	addi $t2 $t2 1      
	addi $t7 $t7 1 
   	j countChr_x

set_x:				# same set_A
	addi $t7 $t7 -1
	
	li $t1 0
	move $t2 $s6
	li $t3 ' '
	li $t4 0

Loop_x:				#same Loop_A
	lb $t0 0($t2)	
	beq $t1 $t7 endLoop_x
    	beq $t0 $t3 space_x
    	
    	jal transform
    	jal increment
    	
    	j Loop_x
    	
space_x:			#same space_A
	sw $t4 0($s2)
    	addi $s2 $s2 4	
    	li $t4 0		
    	
    	jal increment
    	
	j Loop_x

transform:
	andi $t0 $t0 0x0F	# t0 = (int)t0
    	mul $t4 $t4 10			
    	add $t4 $t4 $t0		# ex) '23' -> 2*10 + 3
    	jr $ra
  
 increment:			#increment
  	addi $t1 $t1 1
  	addi $t2 $t2 1
  	jr $ra
    	
endLoop_x:
	sw $t4 0($s2)		#s2's last chr is '\n', So, s2[i] = t4;
		
	la $s2 Int_Arr_b 	#s2 = Ax = b
	
	li $t0 0
	li $t1 25
	move $t2 $s0
	move $t3 $s1
	move $t4 $s2
	
	li $v0 4
	la $a0 printA
	syscall

nL:
	li $t0 0
	li $v0 4
	la $a0 nextLine
	syscall

print_A:				# print Matrix A 
	beq $s3 $t0 nL
	beq $a3 $t1 print_x
	li $v0 1
	lw $a0 0($t2)
	syscall
	
	li $v0 11
	li $a0 9
	syscall 
	
	addi $t2 $t2 4
	addi $t0 $t0 1
	addi $a3 $a3 1
	j print_A

print_x:
	li $t1 5
	li $v0 4
	la $a0 printx
	syscall
	
print_x2:			# print Matrix x
	beqz $t1 setmulti
	li $v0 1
	lw $a0 0($t3)
	syscall
	
	li $v0 4
	la $a0 nextLine
	syscall
	
	addi $t3 $t3 4
	addi $t1 $t1 -1
	j print_x2
	
setmulti:
	move $t2 $s0
	move $t3 $s1
	move $t4 $s2
	
multiply:
	beq $s3 $t0 resetColumn	# if five_loop, then label and end
	
	lw $t5 0($t2)			# t5 = MatrixA[i]
	lw $t6 0($t3)			# t6 = MatrixX[i]
	
	mul $t7 $t5 $t6			# A_in * x_ni
	add $s4 $s4 $t7		# n=1 -> n=5,  s4 = SUM(A_in * x_ni)
	
	
	addi $t0 $t0 1			# i++
	addi $t2 $t2 4			# A's next index
	addi $t3 $t3 4			# x's next index
	
	j multiply
	
resetColumn:
	sw $s4 0($t4)			# matrix b[i] = s4
	addi $t4 $t4 4			# *b[i] = &b[i+4]
	
	addi $t8 $t8 1				
	beq $s3 $t8 printExit		# exit
	
	move $t3 $s1
	li $t0 0
	li $s4 0
	j multiply
	
printExit:	
	li $v0 4 
	la $a0 nextLine 
	syscall
	
	li $v0 4 
	la $a0 printb 
	syscall
	
	li $v0 4 
	la $a0 nextLine
	syscall
	
printResult:				#print Matrix b
	beqz $s3 exit
	
	li $v0 1
	lw $a0 0($s2)
	syscall
	
	addi $s2 $s2 4
	addi $s3 $s3 -1
	
	li $v0 4 
	la $a0 nextLine 
	syscall

	j printResult

exit:
	li $v0 10
	syscall