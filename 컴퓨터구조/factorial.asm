.data
.asciiz
	input : "Input N ( 0  ~ 10 ) : "
	bang :  "! = "
.text
main:
	li $v0 4				#print string		
	la $a0 input			#printf("%s", input);
	syscall
	
	li $v0 5				#scanf("%d", &v0);	
	syscall
	move $a0 $v0		
	move $t0 $a0
	
	li $v0 1				#init result Variable
	jal factorial			#ra = PC+4; factorial(); // ra == next start line
	
	move $t1 $v0		#t1 = v0;
	li $v0 1
	move $a0 $t0		#printf("%d", t0); 
	syscall
	
	li $v0 4
	la $a0 bang			#printf("%s", bang);	
	syscall
	
	li $v0 1
	move $a0 $t1		#printf("%d", t1);
	syscall	
	
	li $v0 10				# return 0;
	syscall 

factorial:
	addi $sp $sp -4		#stack -> top++;
	sw $ra 0($sp)			#stack[stack -> top] = ra; // ra == PC address
	
	bne $a0 0 reccurssion	# if(n) reccurssion();
	
	lw $ra 0($sp)			#ra = stack[stack -> top] // ra is get next start line
	addi $sp $sp 4		#stack -> top--;
	jr $ra				#return next line address 
 
reccurssion:
	mul $v0 $v0 $a0		#v0 *= a0;  // if input==3 then, v0 = 1 * 3 * 2 * 1 = 6
	addi $a0 $a0 -1		#a0 -= 1;   // if input==3 then, a0 = 3 2 1
	jal factorial			#reccurssion factorial
	
	lw $ra 0($sp)			#ra = = stack[stack->top];
	addi $sp $sp 4		#stack->top--;
	jr $ra				#return address; 
