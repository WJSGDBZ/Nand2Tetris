// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//
// This program only needs to handle arguments that satisfy
// R0 >= 0, R1 >= 0, and R0*R1 < 32768.

// Put your code here.

@R0   // n = RAM[0]
D=M
@n
M=D   

@R1   // i = RAM[1]
D=M
@i
M=D   

@sum  // sum = 0
M=0   

(LOOP)
@i
D=M
@ZERO
D;JEQ  //if i==0 goto ZERO

D=D-1  //i--
@i
M=D

@sum   // sum = sum+n
D=M
@n
D=D+M
@sum
M=D

@LOOP
0;JMP

(ZERO)
@sum   //R2 = sum
D=M   
@R2
M=D

(END)
@END
0;JMP