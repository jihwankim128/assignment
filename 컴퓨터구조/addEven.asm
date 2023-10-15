.data
.asciiz 
	input : "Input N (1~100) : "
	output : "sum of even numbers from 1 to "
	colon :  " : "
	
.text 
main:
	li $v0 4					#print string
	la $a0 input				#printf("%s", input);
	syscall	
	
	li $v0 5					#scanf("%d", &v0);
	syscall
	move $t0 $v0		
	li $t1 0
	li $t2 2
	
loop:
	div $v0 $t2 			 	# mflo = v0/t2 ... mfhi = vo % t2
	mfhi $s0
	
	bge $s0 1 subfunction 	# if (v0 % t2 == 0) subfunction
	
	add $t1 $t1 $v0			# t1 += v0;
	addi $v0 $v0 -1			# v0 -= 1;
	
	bgt $v0 $zero loop		# if(!v0) break; 
	
	li $v0 4
	la $a0 output		
	syscall
		
	li $v0 1
	move $a0 $t0
	syscall
	
	li $v0 4
	la $a0 colon
	syscall
	
	li $v0 1
	move $a0 $t1
	syscall
	
	li $v0 10
	syscall

subfunction: 					# v0 == odd ? v0-=1 : continue
	addi $v0 $v0 -1
	j loop
	
	
